package superlord.little_beasties.common.entity;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import javax.annotation.Nullable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mojang.datafixers.DataFixUtils;

import net.minecraft.commands.arguments.EntityAnchorArgument;
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
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import superlord.little_beasties.init.LBItems;

public class TropicalDartfish extends WaterAnimal implements Bucketable {
	private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(TropicalDartfish.class, EntityDataSerializers.BOOLEAN);
	private TropicalDartfish leader;
	private int schoolSize = 1;

	public TropicalDartfish(EntityType<? extends WaterAnimal> p_30341_, Level p_30342_) {
		super(p_30341_, p_30342_);
		this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 0.02F, 0.1F, true);
		this.lookControl = new SmoothSwimmingLookControl(this, 10);
	}
	
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(FROM_BUCKET, false);
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
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 4.0D).add(Attributes.ATTACK_DAMAGE, 1.0D);
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

	public SoundEvent getPickupSound() {
		return SoundEvents.BUCKET_FILL_FISH;
	}

	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1, 10));
		this.goalSelector.addGoal(5, new TropicalDartfishFollowFlockLeaderGoal(this));
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, (double)1.2F, true));
		this.targetSelector.addGoal(0, (new HurtByTargetGoal(this)).setAlertOthers());
		this.goalSelector.addGoal(5, new BoidGoal(this, 0.5f, 0.9f, 8 / 20f, 1 / 20f));
		this.goalSelector.addGoal(3, new StayInWaterGoal(this));
		this.goalSelector.addGoal(2, new LimitSpeedAndLookInVelocityDirectionGoal(this, 0.3f, 0.8f));
	}

	protected SoundEvent getFlopSound() {
		return SoundEvents.COD_FLOP;
	}

	protected SoundEvent getSwimSound() {
		return SoundEvents.FISH_SWIM;
	}

	public boolean doHurtTarget(Entity p_28319_) {
		boolean flag = p_28319_.hurt(this.damageSources().mobAttack(this), (float)((int)this.getAttributeValue(Attributes.ATTACK_DAMAGE)));
		if (flag) {
			this.doEnchantDamageEffects(this, p_28319_);
		}

		return flag;
	}

	protected void playStepSound(BlockPos p_27482_, BlockState p_27483_) {
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

	public TropicalDartfish startFollowing(TropicalDartfish p_27526_) {
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
			List<? extends TropicalDartfish> list = this.level().getEntitiesOfClass(this.getClass(), this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D));
			if (list.size() <= 1) {
				this.schoolSize = 1;
			}
		}

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

	public void addFollowers(Stream<? extends TropicalDartfish> p_27534_) {
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
			p_27531_ = new TropicalDartfish.SchoolSpawnGroupData(this);
		} else {
			this.startFollowing(((TropicalDartfish.SchoolSpawnGroupData)p_27531_).leader);
		}

		return p_27531_;
	}

	public static class SchoolSpawnGroupData implements SpawnGroupData {
		public final TropicalDartfish leader;

		public SchoolSpawnGroupData(TropicalDartfish p_27553_) {
			this.leader = p_27553_;
		}
	}

	class TropicalDartfishFollowFlockLeaderGoal extends Goal {
		@SuppressWarnings("unused")
		private static final int INTERVAL_TICKS = 200;
		private final TropicalDartfish mob;
		private int timeToRecalcPath;
		private int nextStartTick;

		public TropicalDartfishFollowFlockLeaderGoal(TropicalDartfish p_25249_) {
			this.mob = p_25249_;
			this.nextStartTick = this.nextStartTick(p_25249_);
		}

		protected int nextStartTick(TropicalDartfish p_25252_) {
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
				Predicate<TropicalDartfish> predicate = (p_25258_) -> {
					return p_25258_.canBeFollowed() || !p_25258_.isFollower();
				};
				List<? extends TropicalDartfish> list = this.mob.level().getEntitiesOfClass(this.mob.getClass(), this.mob.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), predicate);
				TropicalDartfish abstractschoolingfish = DataFixUtils.orElse(list.stream().filter(TropicalDartfish::canBeFollowed).findAny(), this.mob);
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

	class BoidGoal extends Goal {
		public static final Logger LOGGER = LogManager.getLogger();

		public final float separationInfluence;
		public final float separationRange;
		public final float alignmentInfluence;
		public final float cohesionInfluence;
		private final Mob mob;
		private int timeToFindNearbyEntities;
		List<? extends Mob> nearbyMobs;
		private boolean enabled = true;

		public BoidGoal(Mob mob, float separationInfluence, float separationRange, float alignmentInfluence, float cohesionInfluence) {
			timeToFindNearbyEntities = 0;

			this.mob = mob;
			this.separationInfluence = separationInfluence;
			this.separationRange = separationRange;
			this.alignmentInfluence = alignmentInfluence;
			this.cohesionInfluence = cohesionInfluence;
		}

		@Override
		public boolean canUse() {
			return true;
		}

		public void tick() {
			if (!enabled) {
				return;
			}

			if (--this.timeToFindNearbyEntities <= 0) {
				this.timeToFindNearbyEntities = this.adjustedTickDelay(40);
				nearbyMobs = getNearbyEntitiesOfSameClass(mob);
			} else {
				nearbyMobs.removeIf(LivingEntity::isDeadOrDying);
			}

			if (nearbyMobs.isEmpty()) {
				LOGGER.warn("No nearby entities found. There should always be at least the entity itself. Will disable behavior for this entity instead of crash for compatibility reasons");
				enabled = false;
			}

			mob.addDeltaMovement(random());
			mob.addDeltaMovement(cohesion());
			mob.addDeltaMovement(alignment());
			mob.addDeltaMovement(separation());
		}

		public static List<? extends Mob> getNearbyEntitiesOfSameClass(Mob mob) {
			Predicate<Mob> predicate = (_mob) -> true;

			return mob.level().getEntitiesOfClass(mob.getClass(), mob.getBoundingBox().inflate(4.0, 4.0, 4.0), predicate);
		}

		public Vec3 random() {
			var velocity = mob.getDeltaMovement();

			if (Mth.abs((float) velocity.x) < 0.1 && Mth.abs((float) velocity.z) < 0.1)
				return new Vec3(randomSign() * 0.2, 0, randomSign() * 0.2);

			return Vec3.ZERO;
		}

		public int randomSign() {
			var isNegative = mob.getRandom().nextBoolean();

			if (isNegative) {
				return -1;
			}

			return 1;
		}

		public Vec3 separation() {
			var c = Vec3.ZERO;

			for (Mob nearbyMob : nearbyMobs) {
				if ((nearbyMob.position().subtract(mob.position()).length()) < separationRange) {
					c = c.subtract(nearbyMob.position().subtract(mob.position()));
				}
			}

			return c.scale(separationInfluence);
		}

		public Vec3 alignment() {
			var c = Vec3.ZERO;

			for (Mob nearbyMob : nearbyMobs) {
				c = c.add(nearbyMob.getDeltaMovement());
			}

			c = c.scale(1f / nearbyMobs.size());
			c = c.subtract(mob.getDeltaMovement());
			return c.scale(alignmentInfluence);
		}

		public Vec3 cohesion() {
			var c = Vec3.ZERO;

			for (Mob nearbyMob : nearbyMobs) {
				c = c.add(nearbyMob.position());
			}

			c = c.scale(1f / nearbyMobs.size());
			c = c.subtract(mob.position());
			return c.scale(cohesionInfluence);
		}
	}

	class StayInWaterGoal extends Goal {
		private final Mob mob;

		public StayInWaterGoal(Mob mob) {
			this.mob = mob;
		}

		@Override
		public boolean canUse() {
			return true;
		}

		@Override
		public void tick() {
			var blockPos = mob.blockPosition();
			var blockAbove = mob.level().getBlockState(blockPos.above(2));
			var blockBelow = mob.level().getBlockState(blockPos.below(1));
			var amount = amount();

			if(blockAbove.getFluidState().isEmpty()) {
				mob.addDeltaMovement(new Vec3(0, -amount, 0));
			}

			if(blockBelow.getFluidState().isEmpty()) {
				mob.addDeltaMovement(new Vec3(0, amount, 0));
			}
		}

		float amount() {
			var amount = 0.1f;
			var dY = Mth.abs((float) mob.getDeltaMovement().y);

			if (dY > amount) {
				amount = dY;
			}

			return amount;
		}
	}

	class LimitSpeedAndLookInVelocityDirectionGoal extends Goal {
		private final Mob mob;
		private final float minSpeed;
		private final float maxSpeed;

		public LimitSpeedAndLookInVelocityDirectionGoal(Mob mob, float minSpeed, float maxSpeed) {
			this.mob = mob;
			this.minSpeed = minSpeed;
			this.maxSpeed = maxSpeed;
		}

		@Override
		public boolean canUse() {
			return true;
		}

		@Override
		public void tick() {
			var velocity = mob.getDeltaMovement();
			var speed = velocity.length();

			if (speed < minSpeed)
				velocity = velocity.normalize().scale(minSpeed);
			if (speed > maxSpeed)
				velocity = velocity.normalize().scale(maxSpeed);

			mob.setDeltaMovement(velocity);
			mob.lookAt(EntityAnchorArgument.Anchor.EYES, mob.position().add(velocity.scale(3))); // Scale by 3 just to be sure it is roughly the right direction
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
		return new ItemStack(LBItems.TROPICAL_DARTFISH_BUCKET.get());
	}

}
