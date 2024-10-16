package superlord.little_beasties.common.entity;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import javax.annotation.Nullable;

import com.mojang.datafixers.DataFixUtils;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import superlord.little_beasties.init.LBItems;

public class Rainwitch extends WaterAnimal implements Bucketable {
	private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(Rainwitch.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> RAINING = SynchedEntityData.defineId(Rainwitch.class, EntityDataSerializers.BOOLEAN);
	private Rainwitch leader;
	private int schoolSize = 1;
	@Nullable
	private BlockPos targetPosition;

	public Rainwitch(EntityType<? extends WaterAnimal> p_30341_, Level p_30342_) {
		super(p_30341_, p_30342_);
		this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 0.02F, 0.1F, true);
		this.lookControl = new SmoothSwimmingLookControl(this, 10);
	}

	public boolean requiresCustomPersistence() {
		return super.requiresCustomPersistence() || this.fromBucket();
	}

	public boolean removeWhenFarAway(double p_27492_) {
		return !this.fromBucket() && !this.hasCustomName();
	}

	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1, 10));
		//		this.goalSelector.addGoal(5, new RainwitchFollowFlockLeaderGoal(this));
		//		this.goalSelector.addGoal(5, new BoidGoal(this, 0.5f, 0.9f, 8 / 20f, 1 / 20f));
		//		this.goalSelector.addGoal(3, new StayInWaterGoal(this));
		//		this.goalSelector.addGoal(2, new LimitSpeedAndLookInVelocityDirectionGoal(this, 0.3f, 0.8f));
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(FROM_BUCKET, false);
		this.entityData.define(RAINING, false);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putBoolean("Raining", this.isRaining());
		tag.putBoolean("FromBucket", this.fromBucket());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		this.setRaining(tag.getBoolean("Raining"));
		this.setFromBucket(tag.getBoolean("FromBucket"));
	}

	public boolean isRaining() {
		return this.entityData.get(RAINING);
	}

	public void setRaining(boolean isRaining) {
		this.entityData.set(RAINING, isRaining);
	}

	protected PathNavigation createNavigation(Level p_28362_) {
		return new WaterBoundPathNavigation(this, p_28362_);
	}

	public void travel(Vec3 p_28383_) {
		if (this.isEffectiveAi() && this.isInWaterOrRain()) {
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
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 3.0D);
	}

	public void aiStep() {
		if (!this.isInWaterOrRain() && this.onGround() && this.verticalCollision) {
			this.setDeltaMovement(this.getDeltaMovement().add((double)((this.random.nextFloat() * 2.0F - 1.0F) * 0.05F), (double)0.4F, (double)((this.random.nextFloat() * 2.0F - 1.0F) * 0.05F)));
			this.setOnGround(false);
			this.hasImpulse = true;
			this.playSound(this.getFlopSound(), this.getSoundVolume(), this.getVoicePitch());
		}

		super.aiStep();
	}

	public SoundEvent getPickupSound() {
		return SoundEvents.BUCKET_FILL_FISH;
	}

	protected SoundEvent getFlopSound() {
		return SoundEvents.COD_FLOP;
	}

	protected SoundEvent getSwimSound() {
		return SoundEvents.FISH_SWIM;
	}

	public int getMaxSpawnClusterSize() {
		return this.getMaxSchoolSize();
	}

	public int getMaxSchoolSize() {
		return super.getMaxSpawnClusterSize();
	}

	protected boolean canRandomSwim() {
		return !this.isFollower();
	}

	public boolean isFollower() {
		return this.leader != null && this.leader.isAlive();
	}

	public Rainwitch startFollowing(Rainwitch p_27526_) {
		this.leader = p_27526_;
		p_27526_.addFollower();
		return p_27526_;
	}

	public void stopFollowing() {
		this.leader.removeFollower();
		this.leader = null;
	}

	private void addFollower() {
		++this.schoolSize;
	}

	private void removeFollower() {
		--this.schoolSize;
	}

	public boolean canBeFollowed() {
		return this.hasFollowers() && this.schoolSize < this.getMaxSchoolSize();
	}

	public void tick() {
		super.tick();
		if (this.hasFollowers() && this.level().random.nextInt(200) == 1) {
			List<? extends Rainwitch> list = this.level().getEntitiesOfClass(this.getClass(), this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D));
			if (list.size() <= 1) {
				this.schoolSize = 1;
			}
		}
		if (this.level().isRainingAt(this.blockPosition())) {
			this.setRaining(true);
		} else this.setRaining(false);

	}

	public boolean hasFollowers() {
		return this.schoolSize > 1;
	}

	public boolean inRangeOfLeader() {
		return this.distanceToSqr(this.leader) <= 121.0D;
	}

	public void pathToLeader() {
		if (this.isFollower()) {
			this.getNavigation().moveTo(this.leader, 1.0D);
		}

	}

	public void addFollowers(Stream<? extends Rainwitch> p_27534_) {
		p_27534_.limit((long)(this.getMaxSchoolSize() - this.schoolSize)).filter((p_27538_) -> {
			return p_27538_ != this;
		}).forEach((p_27536_) -> {
			p_27536_.startFollowing(this);
		});
	}

	@SuppressWarnings("deprecation")
	@Nullable
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_27528_, DifficultyInstance p_27529_, MobSpawnType p_27530_, @Nullable SpawnGroupData p_27531_, @Nullable CompoundTag p_27532_) {
		super.finalizeSpawn(p_27528_, p_27529_, p_27530_, p_27531_, p_27532_);
		if (p_27531_ == null) {
			p_27531_ = new Rainwitch.SchoolSpawnGroupData(this);
		} else {
			this.startFollowing(((Rainwitch.SchoolSpawnGroupData)p_27531_).leader);
		}

		return p_27531_;
	}

	public static class SchoolSpawnGroupData implements SpawnGroupData {
		public final Rainwitch leader;

		public SchoolSpawnGroupData(Rainwitch p_27553_) {
			this.leader = p_27553_;
		}
	}

	class RainwitchFollowFlockLeaderGoal extends Goal {
		@SuppressWarnings("unused")
		private static final int INTERVAL_TICKS = 200;
		private final Rainwitch mob;
		private int timeToRecalcPath;
		private int nextStartTick;

		public RainwitchFollowFlockLeaderGoal(Rainwitch p_25249_) {
			this.mob = p_25249_;
			this.nextStartTick = this.nextStartTick(p_25249_);
		}

		protected int nextStartTick(Rainwitch p_25252_) {
			return reducedTickDelay(200 + p_25252_.getRandom().nextInt(200) % 20);
		}

		public boolean canUse() {
			if (this.mob.hasFollowers()) {
				return false;
			} else if (this.mob.isFollower()) {
				return true;
			} else if (this.nextStartTick > 0) {
				--this.nextStartTick;
				return false;
			} else {
				this.nextStartTick = this.nextStartTick(this.mob);
				Predicate<Rainwitch> predicate = (p_25258_) -> {
					return p_25258_.canBeFollowed() || !p_25258_.isFollower();
				};
				List<? extends Rainwitch> list = this.mob.level().getEntitiesOfClass(this.mob.getClass(), this.mob.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), predicate);
				Rainwitch abstractschoolingfish = DataFixUtils.orElse(list.stream().filter(Rainwitch::canBeFollowed).findAny(), this.mob);
				abstractschoolingfish.addFollowers(list.stream().filter((p_25255_) -> {
					return !p_25255_.isFollower();
				}));
				return this.mob.isFollower();
			}
		}

		public boolean canContinueToUse() {
			return this.mob.isFollower() && this.mob.inRangeOfLeader();
		}

		public void start() {
			this.timeToRecalcPath = 0;
		}

		public void stop() {
			this.mob.stopFollowing();
		}

		public void tick() {
			if (--this.timeToRecalcPath <= 0) {
				this.timeToRecalcPath = this.adjustedTickDelay(10);
				this.mob.pathToLeader();
			}
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

	protected void handleAirSupply(int p_30344_) {
		if (this.isInWaterOrRain()) {
			if (this.getAirSupply() < 300) {
				this.setAirSupply(p_30344_ + 1);
			} else this.setAirSupply(300);
		} else {
			this.setAirSupply(p_30344_ - 1);
			if (this.getAirSupply() == -20) {
				this.setAirSupply(0);
				this.hurt(this.damageSources().drown(), 2.0F);
			}
		}
	}

	protected void customServerAiStep() {
		super.customServerAiStep();
		if (this.level().isRaining()) {
			if (this.targetPosition != null && (!this.level().isEmptyBlock(this.targetPosition) || this.targetPosition.getY() <= this.level().getMinBuildHeight())) {
				this.targetPosition = null;
			}

			if (this.targetPosition == null || this.random.nextInt(30) == 0 || this.targetPosition.closerToCenterThan(this.position(), 2.0D)) {
				this.targetPosition = BlockPos.containing(this.getX() + (double)this.random.nextInt(7) - (double)this.random.nextInt(7), this.getY() + (double)this.random.nextInt(6) - 2.0D, this.getZ() + (double)this.random.nextInt(7) - (double)this.random.nextInt(7));
			}

			double d2 = (double)this.targetPosition.getX() + 0.5D - this.getX();
			double d0 = (double)this.targetPosition.getY() + 0.1D - this.getY();
			double d1 = (double)this.targetPosition.getZ() + 0.5D - this.getZ();
			Vec3 vec3 = this.getDeltaMovement();
			Vec3 vec31 = vec3.add((Math.signum(d2) * 0.5D - vec3.x) * (double)0.1F, (Math.signum(d0) * (double)0.7F - vec3.y) * (double)0.1F, (Math.signum(d1) * 0.5D - vec3.z) * (double)0.1F);
			this.setDeltaMovement(vec31);
			float f = (float)(Mth.atan2(vec31.z, vec31.x) * (double)(180F / (float)Math.PI)) - 90.0F;
			float f1 = Mth.wrapDegrees(f - this.getYRot());
			this.zza = 0.5F;
			this.setYRot(this.getYRot() + f1);
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
		return new ItemStack(LBItems.RAINWITCH_BUCKET.get());
	}

}
