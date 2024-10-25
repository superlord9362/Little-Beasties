package superlord.little_beasties.common.entity;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
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
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.MushroomCow;
import net.minecraft.world.entity.animal.Salmon;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.animal.sniffer.Sniffer;
import net.minecraft.world.entity.monster.Strider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import superlord.little_beasties.init.LBItems;
import superlord.little_beasties.init.LBTags;

public class Mohomooho extends WaterAnimal implements Bucketable {
	private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(Mohomooho.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Integer> COLOR = SynchedEntityData.defineId(Mohomooho.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Boolean> STUNNED = SynchedEntityData.defineId(Mohomooho.class, EntityDataSerializers.BOOLEAN);

	public Mohomooho(EntityType<? extends WaterAnimal> p_30341_, Level p_30342_) {
		super(p_30341_, p_30342_);
		this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 0.02F, 0.1F, true);
		this.lookControl = new SmoothSwimmingLookControl(this, 10);
	}

	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1, 10));
		this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
		this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(0, new ChargeRedBlockGoal(1.4F, 6, 6));
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, (double)1.2F, true));
		this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
	}

	public boolean doHurtTarget(Entity p_28319_) {
		boolean flag = p_28319_.hurt(this.damageSources().mobAttack(this), (float)((int)this.getAttributeValue(Attributes.ATTACK_DAMAGE)));
		if (flag) {
			this.doEnchantDamageEffects(this, p_28319_);
		}

		return flag;
	}

	public boolean requiresCustomPersistence() {
		return super.requiresCustomPersistence() || this.fromBucket();
	}

	public boolean removeWhenFarAway(double p_27492_) {
		return !this.fromBucket() && !this.hasCustomName();
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

	protected SoundEvent getFlopSound() {
		return SoundEvents.COD_FLOP;
	}

	protected SoundEvent getSwimSound() {
		return SoundEvents.FISH_SWIM;
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0D).add(Attributes.ATTACK_DAMAGE, 1);
	}

	public void aiStep() {
		if (!this.isInWater() && this.onGround() && this.verticalCollision) {
			this.setDeltaMovement(this.getDeltaMovement().add((double)((this.random.nextFloat() * 2.0F - 1.0F) * 0.05F), (double)0.4F, (double)((this.random.nextFloat() * 2.0F - 1.0F) * 0.05F)));
			this.setOnGround(false);
			this.hasImpulse = true;
			this.playSound(this.getFlopSound(), this.getSoundVolume(), this.getVoicePitch());
		}
		if (this.isStunned()) {
			for (int i = 200; i > 0; i--) {
				if (i == 0) this.setStunned(false);
			}
			this.navigation.stop();
		}
		for (LivingEntity entity : this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(6, 6, 6))) {
			if (entity instanceof Mohomooho mohomooho) {
				if (mohomooho.getColor() == 1 && this.getColor() != 1) this.setTarget(mohomooho);
			}
			if (entity instanceof Salmon || entity instanceof TropicalDartfish || entity instanceof Sniffer || entity instanceof MushroomCow || entity instanceof Strider) this.setTarget(entity);
		}
		super.aiStep();
	}

	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(COLOR, 0);
		this.entityData.define(STUNNED, false);
		this.entityData.define(FROM_BUCKET, false);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putInt("Color", this.getColor());
		tag.putBoolean("FromBucket", this.fromBucket());
		tag.putBoolean("Stunned", this.isStunned());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		this.setColor(tag.getInt("Color"));
		this.setStunned(tag.getBoolean("Stunned"));
		this.setFromBucket(tag.getBoolean("FromBucket"));
	}

	public int getColor() {
		return this.entityData.get(COLOR);
	}

	public void setColor(int color) {
		this.entityData.set(COLOR, color);
	}

	public boolean isStunned() {
		return this.entityData.get(STUNNED);
	}

	public void setStunned(boolean stunned) {
		this.entityData.set(STUNNED, stunned);
	}

	@SuppressWarnings("deprecation")
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
		this.setColor(random.nextInt(4));
		return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	public class ChargeRedBlockGoal extends MoveToBlockGoal {
		protected int ticksWaited;

		public ChargeRedBlockGoal(double p_28675_, int p_28676_, int p_28677_) {
			super(Mohomooho.this, p_28675_, p_28676_, p_28677_);
		}

		public double acceptedDistance() {
			return 2.0D;
		}

		public boolean shouldRecalculatePath() {
			return this.tryTicks % 100 == 0;
		}

		protected boolean isValidTarget(LevelReader p_28680_, BlockPos p_28681_) {
			BlockState blockstate = p_28680_.getBlockState(p_28681_);
			return blockstate.is(LBTags.RED_BLOCKS); // todo - use block map color? would be better for compat
		}

		public void tick() {
			if (this.isReachedTarget()) {
				if (this.ticksWaited >= 40) {
					Mohomooho.this.setStunned(true);
				} else {
					++this.ticksWaited;
				}
			}

			super.tick();
		}

		public boolean canUse() {
			return super.canUse();
		}

		public void start() {
			this.ticksWaited = 0;
			super.start();
		}
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
		return new ItemStack(LBItems.MOHOMOOHO_BUCKET.get());
	}

	@Override
	public SoundEvent getPickupSound() {
		return SoundEvents.BUCKET_FILL_FISH;
	}

}
