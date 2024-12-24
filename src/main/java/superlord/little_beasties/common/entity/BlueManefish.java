package superlord.little_beasties.common.entity;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nullable;

import org.apache.commons.compress.utils.Lists;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.ai.util.AirRandomPos;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.ai.village.poi.PoiRecord;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;
import superlord.little_beasties.common.entity.block.ManefishHiveBlockEntity;
import superlord.little_beasties.init.LBBlocks;
import superlord.little_beasties.init.LBItems;
import superlord.little_beasties.init.LBTags;

@SuppressWarnings("unused")
public class BlueManefish extends WaterAnimal implements Bucketable {
	private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(BlueManefish.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> PUFFING = SynchedEntityData.defineId(BlueManefish.class, EntityDataSerializers.BOOLEAN);

	private static final int TOO_FAR_DISTANCE = 32;
	private static final int PATHFIND_TO_MANEFISH_WHEN_CLOSER_THAN = 16;
	private static final int MANEFISH_SEARCH_DISTANCE = 20;
	public static final String TAG_HIVE_POS = "HivePos";
	private int stayOutOfHiveCountdown;
	private static final int COOLDOWN_BEFORE_LOCATION_NEW_HIVE = 200;
	public int remainingCooldownBeforeLocatingNewHive;
	@Nullable
	public BlockPos hivePos;
	BlueManefish.GoToHiveGoal goToHiveGoal;

	public BlueManefish(EntityType<? extends WaterAnimal> p_30341_, Level p_30342_) {
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
		this.goToHiveGoal = new GoToHiveGoal();
		this.goalSelector.addGoal(0, this.goToHiveGoal);
		this.goalSelector.addGoal(0, new ManefishEnterHiveGoal());
		this.goalSelector.addGoal(0, new ManefishLocateHiveGoal());
		this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
		//		this.goalSelector.addGoal(2, new BlueManefish.BlueManefishSwimToCoralGoal((double)1.2F, 12, 1));
		this.goalSelector.addGoal(0, new BlueManefish.PuffOutGoal());
		this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 8.0F, 1.6D, 1.4D, EntitySelector.NO_SPECTATORS::test));
		this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1, 10));
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
			if (this.level().getBlockState(this.blockPosition().above(4)).is(Blocks.AIR)) this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
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
		if (!this.level().isClientSide()) {
			if (this.stayOutOfHiveCountdown > 0) {
				--this.stayOutOfHiveCountdown;
			}
			if (this.remainingCooldownBeforeLocatingNewHive > 0) {
				--this.remainingCooldownBeforeLocatingNewHive;
			}
			if (this.tickCount % 20 == 0 && !this.isHiveValid()) {
				this.hivePos = null;
			}
		}
		super.aiStep();
	}

	public class BlueManefishSwimToCoralGoal extends MoveToBlockGoal {
		private static final int WAIT_TICKS = 40;
		protected int ticksWaited;

		public BlueManefishSwimToCoralGoal(double p_28675_, int p_28676_, int p_28677_) {
			super(BlueManefish.this, p_28675_, p_28676_, p_28677_);
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
				if (this.ticksWaited >= 40) {
					this.stop();
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

	public boolean isPuffing() {
		return this.entityData.get(PUFFING);
	}

	public void setPuffing(boolean puffing) {
		this.entityData.set(PUFFING, puffing);
	}

	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(PUFFING, false);
		this.entityData.define(FROM_BUCKET, false);
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

	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putBoolean("Puffing", this.isPuffing());
		tag.putBoolean("FromBucket", this.fromBucket());
		if (this.hasHive()) {
			tag.put("HivePos", NbtUtils.writeBlockPos(this.getHivePos()));
		}
		tag.putInt("CannotEnterHiveTicks", this.stayOutOfHiveCountdown);
	}

	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		this.setPuffing(tag.getBoolean("Puffing"));
		this.setFromBucket(tag.getBoolean("FromBucket"));
		this.hivePos = null;
		if (tag.contains("HivePos")) {
			this.hivePos = NbtUtils.readBlockPos(tag.getCompound("HivePos"));
		}
		this.stayOutOfHiveCountdown = tag.getInt("CannotEntityHiveTicks");
	}

	public boolean wantsToEnterHive() {
		if (this.stayOutOfHiveCountdown <= 0 && this.getTarget() == null) {
			boolean flag = this.level().isRaining() || this.level().isNight();
			return flag && !this.isHiveNearFire();
		} else return false;
	}

	public void setStayOutOfHiveCountdown(int countdown) {
		this.stayOutOfHiveCountdown = countdown;
	}

	private boolean isHiveNearFire() {
		if (this.hivePos == null) return false;
		else {
			BlockEntity blockEntity = this.level().getBlockEntity(this.hivePos);
			return blockEntity instanceof ManefishHiveBlockEntity && ((ManefishHiveBlockEntity)blockEntity).isFireNearby();
		}
	}

	private boolean doesHiveHaveSpace(BlockPos pos) {
		BlockEntity blockEntity = this.level().getBlockEntity(pos);
		if (blockEntity instanceof ManefishHiveBlockEntity) {
			return !((ManefishHiveBlockEntity)blockEntity).isFull();
		} else return false;
	}

	public boolean hasHive() {
		return this.hivePos != null;
	}

	@Nullable
	public BlockPos getHivePos() {
		return this.hivePos;
	}

	public GoalSelector getGoalSelector() {
		return this.goalSelector;
	}

	boolean isHiveValid() {
		if (!this.hasHive()) {
			return false;
		} else if (this.isTooFarAway(this.hivePos)) {
			return false;
		} else {
			BlockEntity blockEntity = this.level().getBlockEntity(this.hivePos);
			return blockEntity instanceof ManefishHiveBlockEntity;
		}
	}

	public boolean closerThan(BlockPos pos, int distance) {
		return pos.closerThan(this.blockPosition(), (double)distance);
	}

	public boolean isTooFarAway(BlockPos pos) {
		return !this.closerThan(pos, 32);
	}

	class ManefishEnterHiveGoal extends Goal {
		public boolean canUse() {
			if (BlueManefish.this.hasHive() && BlueManefish.this.wantsToEnterHive() && BlueManefish.this.hivePos.closerToCenterThan(BlueManefish.this.position(), 2)) {
				BlockEntity blockEntity = BlueManefish.this.level().getBlockEntity(BlueManefish.this.hivePos);
				if (blockEntity instanceof ManefishHiveBlockEntity hiveBlockEntity) {
					if (!hiveBlockEntity.isFull()) {
						return true;
					}
					BlueManefish.this.hivePos = null;
				}
			}
			return false;
		}
		
		public boolean canContinueToUse() {
			return false;
		}
		
		public void start() {
			BlockEntity blockEntity = BlueManefish.this.level().getBlockEntity(BlueManefish.this.hivePos);
			if (blockEntity instanceof ManefishHiveBlockEntity hiveBlockEntity) {
				hiveBlockEntity.addOccupant(BlueManefish.this);
			}
		}
		
	}
	
	public void pathfindRandomlyTowards(BlockPos pos) {
		Vec3 vec3 = Vec3.atBottomCenterOf(pos);
		int i = 0;
		BlockPos blockPos = this.blockPosition();
		int j = (int)vec3.y - blockPos.getY();
		if (j > 2) {
			i = 4;
		} else if (j < -2) {
			i = -4;
		}
		int k = 6;
		int l = 8;
		int i1 = blockPos.distManhattan(pos);
		if (i1 < 15) {
			k = i1 / 2;
			l = i1 / 2;
		}
		Vec3 vec31 = AirRandomPos.getPosTowards(this, k, l, i, vec3, (double)((float)Math.PI / 10));
		if (vec31 != null) {
			this.navigation.setMaxVisitedNodesMultiplier(0.5F);
			this.navigation.moveTo(vec31.x, vec31.y, vec31.z, 1);
		}
	}
	
	public class GoToHiveGoal extends Goal {
		public static final int MAX_TRAVELLING_TICKS = 600;
		int travellingTicks = BlueManefish.this.random.nextInt(10);
		private static final int MAX_BLACKLISTED_TARGETS = 5;
		final List<BlockPos> blacklistedTargets = Lists.newArrayList();
		@Nullable
		private Path lastPath;
		private static final int TICKS_BEFORE_HIVE_DROP = 60;
		private int ticksStuck;
		
		GoToHiveGoal() {
			this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		}
		
		public boolean canUse() {
			return BlueManefish.this.hivePos != null && !BlueManefish.this.hasRestriction() && BlueManefish.this.wantsToEnterHive() && !this.hasReachedTarget(BlueManefish.this.hivePos) && BlueManefish.this.level().getBlockState(BlueManefish.this.hivePos).is(LBBlocks.MANEFISH_HIVE.get());
		}
		
		public boolean canContinueToUse() {
			return BlueManefish.this.hivePos != null;
		}
		
		public void start() {
			this.travellingTicks = 0;
			this.ticksStuck = 0;
			super.start();
		}
		
		public void stop() {
			this.travellingTicks = 0;
			this.ticksStuck = 0;
			BlueManefish.this.navigation.stop();
			BlueManefish.this.navigation.resetMaxVisitedNodesMultiplier();
		}
		
		public void tick() {
			if (BlueManefish.this.hivePos != null) {
				++this.travellingTicks;
				if (this.travellingTicks > this.adjustedTickDelay(600)) {
					this.dropAndBlacklistHive();
				} else if (!BlueManefish.this.navigation.isInProgress()) {
					if (!BlueManefish.this.closerThan(BlueManefish.this.hivePos, 16)) {
						if (BlueManefish.this.isTooFarAway(BlueManefish.this.hivePos)) {
							this.dropHive();
						} else {
							BlueManefish.this.pathfindRandomlyTowards(BlueManefish.this.hivePos);
						}
					} else {
						boolean flag = this.pathfindDirectlyTowards(BlueManefish.this.hivePos);
						if (!flag) {
							this.dropAndBlacklistHive();
						} else if (this.lastPath != null && BlueManefish.this.navigation.getPath().sameAs(this.lastPath)) {
							++this.ticksStuck;
							if (this.ticksStuck > 60) {
								this.dropHive();
								this.ticksStuck = 0;
							}
						} else {
							this.lastPath = BlueManefish.this.navigation.getPath();
						}
					}
				}
			}
		}
		
		private boolean pathfindDirectlyTowards(BlockPos pos) {
			BlueManefish.this.navigation.setMaxVisitedNodesMultiplier(10);
			BlueManefish.this.navigation.moveTo((double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), 1);
			return BlueManefish.this.navigation.getPath() != null && BlueManefish.this.navigation.getPath().canReach();
		}
		
		boolean isTargetBlackListed(BlockPos pos) {
			return this.blacklistedTargets.contains(pos);
		}
		
		private void blacklistTarget(BlockPos pos) {
			this.blacklistedTargets.add(pos);
			while (this.blacklistedTargets.size() > 5) {
				this.blacklistedTargets.remove(0);
			}
		}
		
		void clearBlacklist() {
			this.blacklistedTargets.clear();
		}
		
		private void dropAndBlacklistHive() {
			if (BlueManefish.this.hivePos != null) {
				this.blacklistTarget(BlueManefish.this.hivePos);
			}
			this.dropHive();
		}
		
		private void dropHive() {
			BlueManefish.this.hivePos = null;
			BlueManefish.this.remainingCooldownBeforeLocatingNewHive = 200;
		}
		
		private boolean hasReachedTarget(BlockPos pos) {
			if (BlueManefish.this.closerThan(pos, 2)) {
				return true;
			} else {
				Path path = BlueManefish.this.navigation.getPath();
				return path != null && path.getTarget().equals(pos) && path.canReach() && path.isDone();
			}
		}
		
	}
	
	class ManefishLocateHiveGoal extends Goal {
		
		public boolean canUse() {
			return BlueManefish.this.remainingCooldownBeforeLocatingNewHive == 0 && !BlueManefish.this.hasHive() && BlueManefish.this.wantsToEnterHive();
		}
		
		public boolean canContinueToUse() {
			return false;
		}
		
		public void start() {
			BlueManefish.this.remainingCooldownBeforeLocatingNewHive = 200;
			List<BlockPos> list = this.findNearbyHiveWithSpace();
			if (!list.isEmpty()) {
				for (BlockPos pos : list) {
					if (!BlueManefish.this.goToHiveGoal.isTargetBlackListed(pos)) {
						BlueManefish.this.hivePos = pos;
						return;
					}
				}
				BlueManefish.this.goToHiveGoal.clearBlacklist();
				BlueManefish.this.hivePos = list.get(0);
			}
		}
		
		private List<BlockPos> findNearbyHiveWithSpace() {
			BlockPos pos = BlueManefish.this.blockPosition();
			PoiManager poimanager = ((ServerLevel)BlueManefish.this.level()).getPoiManager();
			Stream<PoiRecord> stream = poimanager.getInRange((p_218130_) -> {
				return p_218130_.is(LBTags.MANEFISH_HIVES);
			}, pos, 20, PoiManager.Occupancy.ANY);
			return stream.map(PoiRecord::getPos).filter(BlueManefish.this::doesHiveHaveSpace).sorted(Comparator.comparingDouble((p_148811_) -> {
				return p_148811_.distSqr(pos);
			})).collect(Collectors.toList());
		}
		
	}

	class PuffOutGoal extends Goal {
		public PuffOutGoal() {
			this.setFlags(EnumSet.of(Goal.Flag.TARGET));
		}

		public boolean canUse() {
			return (BlueManefish.this.getLastAttacker() != null);
		}

		public void start() {
			BlueManefish.this.setPuffing(true);
			if (BlueManefish.this.getRandom().nextInt(2) == 0) {
				LivingEntity attacker = BlueManefish.this.getLastAttacker();
				if (attacker instanceof Mob mob) {
					mob.setTarget(null);
				}
			}
		}

		public void stop() {
			BlueManefish.this.setPuffing(false);
		}

		public boolean canContinueToUse() {
			return !BlueManefish.this.getNavigation().isDone();
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
		return new ItemStack(LBItems.BLUE_MANEFISH_BUCKET.get());
	}

	@Override
	public SoundEvent getPickupSound() {
		return SoundEvents.BUCKET_FILL_FISH;
	}

}
