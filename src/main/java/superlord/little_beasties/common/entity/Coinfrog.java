package superlord.little_beasties.common.entity;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import superlord.little_beasties.init.LBBlocks;
import superlord.little_beasties.init.LBItems;

public class Coinfrog extends Animal {
	private static final EntityDataAccessor<Boolean> HAS_BABY = SynchedEntityData.defineId(Coinfrog.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> IS_BIRTHING = SynchedEntityData.defineId(Coinfrog.class, EntityDataSerializers.BOOLEAN);
	public int isBirthing;

	public Coinfrog(EntityType<? extends Animal> p_30341_, Level p_30342_) {
		super(p_30341_, p_30342_);
		this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 0.02F, 0.1F, true);
		this.lookControl = new SmoothSwimmingLookControl(this, 10);
		this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
	}

	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1, 10));
		this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(0, new CoinfrogMateGoal(this, 1.2F));
		this.goalSelector.addGoal(0, new LayEggGoal(this, 1.1F));
	}

	public boolean isFood(ItemStack stack) {
		return stack.getItem() == LBItems.WATERFLY.get();
	}

	public boolean canBreatheUnderwater() {
		return true;
	}

	protected PathNavigation createNavigation(Level p_28362_) {
		return new WaterBoundPathNavigation(this, p_28362_);
	}

	public void travel(Vec3 p_28383_) {
		if (this.isEffectiveAi() && this.isInWater()) {
			this.moveRelative(this.getSpeed(), p_28383_);
			this.move(MoverType.SELF, this.getDeltaMovement());
			this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
			if (this.getTarget() == null) {
				this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
			}
		} else {
			super.travel(p_28383_);
		}
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 4.0D);
	}

	protected SoundEvent getFlopSound() {
		return SoundEvents.COD_FLOP;
	}

	protected SoundEvent getSwimSound() {
		return SoundEvents.FISH_SWIM;
	}

	public void aiStep() {
		if (!this.isInWater() && this.onGround() && this.verticalCollision) {
			this.setDeltaMovement(this.getDeltaMovement().add((double)((this.random.nextFloat() * 2.0F - 1.0F) * 0.05F), (double)0.4F, (double)((this.random.nextFloat() * 2.0F - 1.0F) * 0.05F)));
			this.setOnGround(false);
			this.hasImpulse = true;
			this.playSound(this.getFlopSound(), this.getSoundVolume(), this.getVoicePitch());
		}
		super.aiStep();
	}

	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		Item item = stack.getItem();
		if (item == Items.AIR && this.getTarget() == null) {
			ItemEntity itementity = new ItemEntity(this.level(), this.getX(), this.getY() + 1.0D, this.getZ(), new ItemStack(LBItems.COINFROG.get()));
			this.level().addFreshEntity(itementity);
			this.discard();
			return InteractionResult.SUCCESS;
		} else {
			return super.mobInteract(player, hand);
		}
	}

	protected void handleAirSupply(int p_30344_) {
		if (this.isAlive() && !this.isInWaterOrBubble()) {
			this.setAirSupply(p_30344_ - 1);
			if (this.getAirSupply() == -20) {
				this.setAirSupply(0);
				this.hurt(this.damageSources().drown(), 2.0F);
			}
		} else {
			this.setAirSupply(300);
		}

	}

	public MobType getMobType() {
		return MobType.WATER;
	}

	public boolean checkSpawnObstruction(LevelReader p_30348_) {
		return p_30348_.isUnobstructed(this);
	}

	public int getAmbientSoundInterval() {
		return 120;
	}

	@SuppressWarnings("resource")
	public int getExperienceReward() {
		return 1 + this.level().random.nextInt(3);
	}


	public void baseTick() {
		int i = this.getAirSupply();
		super.baseTick();
		this.handleAirSupply(i);
	}

	public boolean isPushedByFluid() {
		return false;
	}

	public boolean canBeLeashed(Player p_30346_) {
		return false;
	}

	@SuppressWarnings("deprecation")
	public static boolean checkSurfaceWaterAnimalSpawnRules(EntityType<? extends Coinfrog> p_218283_, LevelAccessor p_218284_, MobSpawnType p_218285_, BlockPos p_218286_, RandomSource p_218287_) {
		int i = p_218284_.getSeaLevel();
		int j = i - 13;
		return p_218286_.getY() >= j && p_218286_.getY() <= i && p_218284_.getFluidState(p_218286_.below()).is(FluidTags.WATER) && p_218284_.getBlockState(p_218286_.above()).is(Blocks.WATER);
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
		return null;
	}

	public boolean hasBaby() {
		return this.entityData.get(HAS_BABY);
	}

	public void setHasBaby(boolean hasBaby) {
		this.entityData.set(HAS_BABY, hasBaby);
	}

	public boolean isBirthing() {
		return this.entityData.get(IS_BIRTHING);
	}

	public void setBirthing(boolean isBirthing) {
		this.isBirthing = isBirthing ? 1 : 0;
		this.entityData.set(IS_BIRTHING, isBirthing);
	}

	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(HAS_BABY, false);
		this.entityData.define(IS_BIRTHING, false);
	}


	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putBoolean("HasBaby", this.hasBaby());
	}

	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		this.setHasBaby(compound.getBoolean("HasBaby"));
	}

	public class CoinfrogMateGoal extends BreedGoal {
		private final Coinfrog coinfrog;

		public CoinfrogMateGoal(Coinfrog coinfrog, double speed) {
			super(coinfrog, speed);
			this.coinfrog = coinfrog;
		}

		public boolean canUse() {
			return super.canUse() && !this.coinfrog.hasBaby();
		}

		protected void breed() {
			ServerPlayer serverplayerentity = this.animal.getLoveCause();
			if (serverplayerentity == null && this.partner.getLoveCause() != null) {
				serverplayerentity = this.partner.getLoveCause();
			}
			if (serverplayerentity != null) {
				serverplayerentity.awardStat(Stats.ANIMALS_BRED);
				CriteriaTriggers.BRED_ANIMALS.trigger(serverplayerentity, this.animal, this.partner, (AgeableMob)null);
			}
			this.coinfrog.setHasBaby(true);
			this.animal.resetLove();
			this.partner.resetLove();
			RandomSource randomom = this.animal.getRandom();
			if (this.level.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT)) {
				this.level.addFreshEntity(new ExperienceOrb(this.level, this.animal.getX(), this.animal.getY(), this.animal.getZ(), randomom.nextInt(7) + 1));
			}
		}

	}

	public class LayEggGoal  extends MoveToBlockGoal {
		private final Coinfrog coinfrog;

		public LayEggGoal(Coinfrog coinfrog, double speed) {
			super(coinfrog, speed, 16);
			this.coinfrog = coinfrog;
		}

		public boolean canUse() {
			return this.coinfrog.hasBaby() ? super.canUse() : false;
		}

		public boolean canContinueToUse() {
			return super.canContinueToUse() && this.coinfrog.hasBaby();
		}

		public void tick() {
			super.tick();
			BlockPos blockpos = new BlockPos(this.coinfrog.blockPosition());
			if (this.isReachedTarget()) {
				if (this.coinfrog.isBirthing < 1) {
					this.coinfrog.setBirthing(true);
				} else if (this.coinfrog.isBirthing > 200) {
					Level world = this.coinfrog.level();
					world.playSound((Player)null, blockpos, SoundEvents.FROG_LAY_SPAWN, SoundSource.BLOCKS, 0.3F, 0.9F + world.random.nextFloat() * 0.2F);
					world.setBlock(this.blockPos.above(), LBBlocks.COINFROG_SPAWN.get().defaultBlockState(), 3);
					this.coinfrog.setHasBaby(false);
					this.coinfrog.setBirthing(false);
					this.coinfrog.setInLoveTime(600);
				}
				if (this.coinfrog.isBirthing()) {
					this.coinfrog.isBirthing++;
				}
			}
		}

		protected boolean isValidTarget(LevelReader worldIn, BlockPos pos) {
			if (!worldIn.isEmptyBlock(pos.above())) {
				return false;
			} else {
				Block block = worldIn.getBlockState(pos).getBlock();
				return block == Blocks.WATER && worldIn.getBlockState(pos.above()).getBlock() == Blocks.AIR;
			}
		}

	}



}
