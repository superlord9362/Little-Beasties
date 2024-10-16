package superlord.little_beasties.common.entity;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.TropicalFish;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import superlord.little_beasties.LittleBeasties;
import superlord.little_beasties.init.LBItems;

public class TropicalSeadragon extends WaterAnimal implements Bucketable {
	private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(TropicalSeadragon.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> HUNTING = SynchedEntityData.defineId(TropicalSeadragon.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> RUBBING = SynchedEntityData.defineId(TropicalSeadragon.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> ACTIVE_RUBBING = SynchedEntityData.defineId(TropicalSeadragon.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Integer> COLOR = SynchedEntityData.defineId(TropicalSeadragon.class, EntityDataSerializers.INT);
	private int huntTime = 6000;
	private int coralRubTime = 6000;

	public TropicalSeadragon(EntityType<? extends WaterAnimal> entity, Level world) {
		super(entity, world);
		this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 0.02F, 0.1F, true);
		this.lookControl = new SmoothSwimmingLookControl(this, 10);
	}

	public boolean requiresCustomPersistence() {
		return super.requiresCustomPersistence() || this.fromBucket();
	}

	public boolean removeWhenFarAway(double p_27492_) {
		return !this.fromBucket() && !this.hasCustomName();
	}

	public void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1, 10));
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, (double)1.2F, true));
		this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
		this.goalSelector.addGoal(0, new HuntFishGoal(this, TropicalFish.class, true));
		this.goalSelector.addGoal(0, new HuntFishGoal(this, Saildrifter.class, true));
		this.goalSelector.addGoal(0, new HuntFishGoal(this, BlueManefish.class, true));
		this.goalSelector.addGoal(2, new TropicalSeadragon.TropicalSeadragonSwimToCoralGoal((double)1.2F, 12, 1));
		this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
	}

	protected PathNavigation createNavigation(Level p_28362_) {
		return new WaterBoundPathNavigation(this, p_28362_);
	}

	public boolean doHurtTarget(Entity p_28319_) {
		boolean flag = p_28319_.hurt(this.damageSources().mobAttack(this), (float)((int)this.getAttributeValue(Attributes.ATTACK_DAMAGE)));
		if (flag) {
			this.doEnchantDamageEffects(this, p_28319_);
		}

		return flag;
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

	protected SoundEvent getFlopSound() {
		return SoundEvents.COD_FLOP;
	}

	protected SoundEvent getSwimSound() {
		return SoundEvents.FISH_SWIM;
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 8.0D).add(Attributes.ATTACK_DAMAGE, 4.0D);
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

	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(COLOR, 0);
		this.entityData.define(FROM_BUCKET, false);
		this.entityData.define(HUNTING, false);
		this.entityData.define(RUBBING, false);
		this.entityData.define(ACTIVE_RUBBING, false);
	}

	public void tick() {
		super.tick();
		for (int i = huntTime; i > 0; i--) {
			if (i == 0) this.setHunting(true);
		}
		for (int i = coralRubTime; i > 0; i--) {
			if (i == 0) this.setRubbing(true);
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putInt("Color", this.getColor());
		tag.putBoolean("Hunting", this.isHunting());
		tag.putBoolean("Rubbing", this.isRubbing());
		tag.putBoolean("ActiveRubbing", this.isActivelyRubbing());
		tag.putBoolean("FromBucket", this.fromBucket());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		this.setFromBucket(tag.getBoolean("FromBucket"));
		this.setColor(tag.getInt("Color"));
		this.setHunting(tag.getBoolean("Hunting"));
		this.setRubbing(tag.getBoolean("Rubbing"));
		this.setActivelyRubbing(tag.getBoolean("ActiveRubbing"));
	}

	public int getColor() {
		return this.entityData.get(COLOR);
	}

	public void setColor(int color) {
		this.entityData.set(COLOR, color);
	}

	public boolean isHunting() {
		return this.entityData.get(HUNTING);
	}

	private void setHunting(boolean isHunting) {
		this.entityData.set(HUNTING, isHunting);
	}

	public boolean isRubbing() {
		return this.entityData.get(RUBBING);
	}

	private void setRubbing(boolean isRubbing) {
		this.entityData.set(RUBBING, isRubbing);
	}

	public boolean isActivelyRubbing() {
		return this.entityData.get(ACTIVE_RUBBING);
	}

	private void setActivelyRubbing(boolean isActivelyRubbing) {
		this.entityData.set(ACTIVE_RUBBING, isActivelyRubbing);
	}

	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		Item item = stack.getItem();
		if (item == Items.WATER_BUCKET) {
			return Bucketable.bucketMobPickup(player, hand, this).orElse(super.mobInteract(player, hand));
		} else {
			return super.mobInteract(player, hand);
		}
	}

	@Override
	public boolean fromBucket() {
		return this.entityData.get(FROM_BUCKET);
	}

	@Override
	public void setFromBucket(boolean p_148834_) {
		this.entityData.set(FROM_BUCKET, p_148834_);
	}

	@Override
	public void saveToBucketTag(ItemStack bucket) {
		CompoundTag nbt = bucket.getOrCreateTag();
		nbt.putInt("Color", this.getColor());
		if (this.hasCustomName()) {
			bucket.setHoverName(this.getCustomName());
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void loadFromBucketTag(CompoundTag p_148832_) {
		Bucketable.loadDefaultDataFromBucketTag(this, p_148832_);
	}

	@Override
	public ItemStack getBucketItemStack() {
		return new ItemStack(LBItems.TROPICAL_SEADRAGON_BUCKET.get());
	}

	@SuppressWarnings("deprecation")
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
		this.setColor(random.nextInt(4));
		return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	@Override
	public SoundEvent getPickupSound() {
		return SoundEvents.BUCKET_FILL_FISH;
	}

	@SuppressWarnings("rawtypes")
	public class HuntFishGoal extends NearestAttackableTargetGoal {
		double huntSpeed;
		TropicalSeadragon dragon;

		@SuppressWarnings("unchecked")
		public HuntFishGoal(TropicalSeadragon goalOwner, Class target, boolean checkSight) {
			super(goalOwner, target, checkSight);
			this.dragon = goalOwner;
		}

		public boolean canUse() {
			return super.canUse() && dragon.isHunting();
		}

		public boolean canContinueToUse() {
			return super.canContinueToUse() && dragon.isHunting();
		}

		public void tick() {
			super.tick();
			if (dragon.getTarget() != null) {
				LivingEntity target = dragon.getTarget();
				if (!target.is(null) && target.getHealth() == 0) {
					dragon.huntTime = 6000;
					dragon.setHunting(false);
				}
				if (target instanceof BlueManefish manefish) {
					if (manefish.isPuffing()) {
						dragon.huntTime = 6000;
						dragon.setHunting(false);
					}
				}
			}
		}
	}

	public class TropicalSeadragonSwimToCoralGoal extends MoveToBlockGoal {
		@SuppressWarnings("unused")
		private static final int WAIT_TICKS = 40;
		private static final ResourceLocation RUBBING_LOOT = new ResourceLocation(LittleBeasties.MOD_ID, "entities/tropical_seadragon");
		protected int ticksWaited;

		public TropicalSeadragonSwimToCoralGoal(double p_28675_, int p_28676_, int p_28677_) {
			super(TropicalSeadragon.this, p_28675_, p_28676_, p_28677_);
		}

		public double acceptedDistance() {
			return 2.0D;
		}

		public boolean shouldRecalculatePath() {
			return this.tryTicks % 100 == 0;
		}

		protected boolean isValidTarget(LevelReader p_28680_, BlockPos p_28681_) {
			BlockState blockstate = p_28680_.getBlockState(p_28681_);
			return blockstate.is(BlockTags.CORALS);
		}

		public void tick() {
			if (this.isReachedTarget()) {
				TropicalSeadragon.this.setActivelyRubbing(true);
				if (this.ticksWaited >= 40) {
					this.onReachedTarget();
				} else {
					++this.ticksWaited;
				}
			}

			super.tick();
		}

		protected void onReachedTarget() {
			RandomSource randomsource = TropicalSeadragon.this.getRandom();
			BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
			blockpos$mutableblockpos.set(TropicalSeadragon.this.isLeashed() ? TropicalSeadragon.this.getLeashHolder().blockPosition() : TropicalSeadragon.this.blockPosition());
			TropicalSeadragon.this.randomTeleport((double)(blockpos$mutableblockpos.getX() + randomsource.nextInt(11) - 5), (double)(blockpos$mutableblockpos.getY() + randomsource.nextInt(5) - 2), (double)(blockpos$mutableblockpos.getZ() + randomsource.nextInt(11) - 5), false);
			blockpos$mutableblockpos.set(TropicalSeadragon.this.blockPosition());
			LootTable loottable = TropicalSeadragon.this.level().getServer().getLootData().getLootTable(RUBBING_LOOT);
			LootParams lootparams = (new LootParams.Builder((ServerLevel)TropicalSeadragon.this.level())).withParameter(LootContextParams.ORIGIN, TropicalSeadragon.this.position()).withParameter(LootContextParams.THIS_ENTITY, TropicalSeadragon.this).create(LootContextParamSets.EMPTY);

			for(ItemStack itemstack : loottable.getRandomItems(lootparams)) {
				TropicalSeadragon.this.level().addFreshEntity(new ItemEntity(TropicalSeadragon.this.level(), (double)blockpos$mutableblockpos.getX() - (double)Mth.sin(TropicalSeadragon.this.yBodyRot * ((float)Math.PI / 180F)), (double)blockpos$mutableblockpos.getY(), (double)blockpos$mutableblockpos.getZ() + (double)Mth.cos(TropicalSeadragon.this.yBodyRot * ((float)Math.PI / 180F)), itemstack));
				TropicalSeadragon.this.coralRubTime = 6000;
				TropicalSeadragon.this.setActivelyRubbing(false);
				TropicalSeadragon.this.setRubbing(false);
				this.stop();
			}
		}

		public boolean canUse() {
			return super.canUse() && TropicalSeadragon.this.isRubbing();
		}

		public void start() {
			this.ticksWaited = 0;
			super.start();
		}
	}

}
