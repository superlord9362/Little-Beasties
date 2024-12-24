package superlord.little_beasties.common.entity;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import javax.annotation.Nullable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mojang.datafixers.DataFixUtils;

import net.minecraft.commands.arguments.EntityAnchorArgument;
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
import net.minecraft.world.entity.EntitySelector;
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
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
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
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import superlord.little_beasties.init.LBItems;

public class Saildrifter extends WaterAnimal implements Bucketable {
	private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(Saildrifter.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Integer> COLOR = SynchedEntityData.defineId(Saildrifter.class, EntityDataSerializers.INT);
	private Saildrifter leader;
	private int schoolSize = 1;

	public Saildrifter(EntityType<? extends WaterAnimal> entity, Level world) {
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
		this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
		this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 8.0F, 1.6D, 1.4D, EntitySelector.NO_SPECTATORS::test));
		this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1, 10));
		this.goalSelector.addGoal(5, new SaildrifterFollowFlockLeaderGoal(this));
		this.goalSelector.addGoal(5, new BoidGoal(this, 0.5f, 0.5f, 19 / 20f, 1 / 20f));
		this.goalSelector.addGoal(3, new StayInWaterGoal(this));
		this.goalSelector.addGoal(2, new LimitSpeedAndLookInVelocityDirectionGoal(this, 0.1f, 0.5f));
	}

	protected PathNavigation createNavigation(Level p_28362_) {
		return new WaterBoundPathNavigation(this, p_28362_);
	}

	public void travel(Vec3 p_28383_) {
		if (this.isEffectiveAi() && this.isInWater()) {
			this.moveRelative(this.getSpeed(), p_28383_);
			this.move(MoverType.SELF, this.getDeltaMovement());
			this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
			if (this.getTarget() == null && !this.level().getBlockState(this.getOnPos().above()).is(Blocks.AIR)) {
				this.setDeltaMovement(this.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
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
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 3.0D);
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
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putInt("Color", this.getColor());
		tag.putBoolean("FromBucket", this.fromBucket());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		this.setColor(tag.getInt("Color"));
		this.setFromBucket(tag.getBoolean("FromBucket"));
	}

	public int getColor() {
		return this.entityData.get(COLOR);
	}

	public void setColor(int color) {
		this.entityData.set(COLOR, color);
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
		return new ItemStack(LBItems.SAILDRIFTER_BUCKET.get());
	}

	@SuppressWarnings("deprecation")
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
		this.setColor(random.nextInt(16));
		if (spawnDataIn == null) {
			spawnDataIn = new Saildrifter.SchoolSpawnGroupData(this);
		} else {
			this.startFollowing(((Saildrifter.SchoolSpawnGroupData)spawnDataIn).leader);
		}
		return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	@Override
	public SoundEvent getPickupSound() {
		return SoundEvents.BUCKET_FILL_FISH;
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

	public Saildrifter startFollowing(Saildrifter p_27526_) {
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
		if (this.hasFollowers() && this.random.nextInt(200) == 1) {
			List<? extends Saildrifter> list = this.level().getEntitiesOfClass(this.getClass(), this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D));
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

	public void addFollowers(Stream<? extends Saildrifter> p_27534_) {
		p_27534_.limit((long)(this.getMaxSchoolSize() - this.schoolSize)).filter((p_27538_) -> {
			return p_27538_ != this;
		}).forEach((p_27536_) -> {
			p_27536_.startFollowing(this);
		});
	}

	public static class SchoolSpawnGroupData implements SpawnGroupData {
		public final Saildrifter leader;

		public SchoolSpawnGroupData(Saildrifter p_27553_) {
			this.leader = p_27553_;
		}
	}

	class SaildrifterFollowFlockLeaderGoal extends Goal {
		@SuppressWarnings("unused")
		private static final int INTERVAL_TICKS = 200;
		private final Saildrifter mob;
		private int timeToRecalcPath;
		private int nextStartTick;

		public SaildrifterFollowFlockLeaderGoal(Saildrifter p_25249_) {
			this.mob = p_25249_;
			this.nextStartTick = this.nextStartTick(p_25249_);
		}

		protected int nextStartTick(Saildrifter p_25252_) {
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
				Predicate<Saildrifter> predicate = (p_25258_) -> {
					return p_25258_.canBeFollowed() || !p_25258_.isFollower();
				};
				List<? extends Saildrifter> list = this.mob.level().getEntitiesOfClass(this.mob.getClass(), this.mob.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), predicate);
				Saildrifter abstractschoolingfish = DataFixUtils.orElse(list.stream().filter(Saildrifter::canBeFollowed).findAny(), this.mob);
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
			var blockAbove = mob.level().getBlockState(blockPos.above(0));
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
			var amount = 0f;
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

}
