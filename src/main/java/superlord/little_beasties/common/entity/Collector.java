package superlord.little_beasties.common.entity;

import java.util.EnumSet;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.InteractGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MoveTowardsRestrictionGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.npc.InventoryCarrier;
import net.minecraft.world.entity.npc.Npc;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import superlord.little_beasties.common.entity.goal.CollectorLookAtTradingPlayerGoal;
import superlord.little_beasties.common.entity.goal.CollectorTradeWithPlayerGoal;
import superlord.little_beasties.init.LBItems;

public class Collector extends WaterAnimal implements InventoryCarrier, Npc, Merchant {
	public static final int SLOT_OFFSET = 300;
	@Nullable
	private BlockPos wanderTarget;
	@Nullable
	private Player tradingPlayer;
	@Nullable
	protected MerchantOffers offers;
	private final SimpleContainer inventory = new SimpleContainer(8);

	public Collector(EntityType<? extends WaterAnimal> p_30341_, Level p_30342_) {
		super(p_30341_, p_30342_);
		this.moveControl = new MoveHelperController(this);
		this.setMaxUpStep(1.0F);
	}

	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
		this.goalSelector.addGoal(1, new PanicGoal(this, 1.0D));
		this.goalSelector.addGoal(1, new CollectorTradeWithPlayerGoal(this));
		this.goalSelector.addGoal(1, new CollectorLookAtTradingPlayerGoal(this));
		this.goalSelector.addGoal(2, new WanderToPositionGoal(this, 2.0D, 1.0D));
		this.goalSelector.addGoal(4, new MoveTowardsRestrictionGoal(this, 1.0D));
		this.goalSelector.addGoal(8, new RandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(9, new InteractGoal(this, Player.class, 3.0F, 1.0F));
		this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));
	}

	public InteractionResult mobInteract(Player p_35856_, InteractionHand p_35857_) {
		if (this.isAlive() && !this.isTrading()) {
			if (this.getOffers().isEmpty()) {
				return InteractionResult.sidedSuccess(this.level().isClientSide());
			} else {
				if (!this.level().isClientSide()) {
					this.setTradingPlayer(p_35856_);
					this.openTradingScreen(p_35856_, this.getDisplayName(), 1);
				}

				return InteractionResult.sidedSuccess(this.level().isClientSide());
			}
		} else {
			return super.mobInteract(p_35856_, p_35857_);
		}
	}

	public void setTradingPlayer(@Nullable Player p_35314_) {
		this.tradingPlayer = p_35314_;
	}

	@Nullable
	public Player getTradingPlayer() {
		return this.tradingPlayer;
	}

	public boolean isTrading() {
		return this.tradingPlayer != null;
	}

	public MerchantOffers getOffers() {
		if (this.offers == null) {
			this.offers = new MerchantOffers();
			this.updateTrades();
		}

		return this.offers;
	}

	public void overrideOffers(@Nullable MerchantOffers p_35276_) {
	}

	public void overrideXp(int p_35322_) {
	}

	public void notifyTrade(MerchantOffer p_35274_) {
		p_35274_.increaseUses();
		this.ambientSoundTime = -this.getAmbientSoundInterval();
		this.rewardTradeXp(p_35274_);
	}

	protected void rewardTradeXp(MerchantOffer p_35859_) {
		if (p_35859_.shouldRewardExp()) {
			int i = 3 + this.random.nextInt(4);
			this.level().addFreshEntity(new ExperienceOrb(this.level(), this.getX(), this.getY() + 0.5D, this.getZ(), i));
		}
	}

	public boolean showProgressBar() {
		return false;
	}

	public void notifyTradeUpdated(ItemStack p_35316_) {
		if (!this.level().isClientSide() && this.ambientSoundTime > -this.getAmbientSoundInterval() + 20) {
			this.ambientSoundTime = -this.getAmbientSoundInterval();
			this.playSound(this.getTradeUpdatedSound(!p_35316_.isEmpty()), this.getSoundVolume(), this.getVoicePitch());
		}
	}

	public SoundEvent getNotifyTradeSound() {
		return SoundEvents.VILLAGER_YES;
	}

	protected SoundEvent getTradeUpdatedSound(boolean p_35323_) {
		return p_35323_ ? SoundEvents.VILLAGER_YES : SoundEvents.VILLAGER_NO;
	}

	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		MerchantOffers merchantoffers = this.getOffers();
		if (!merchantoffers.isEmpty()) {
			tag.put("Offers", merchantoffers.createTag());
		}
		if (this.wanderTarget != null) {
			tag.put("WanderTarget", NbtUtils.writeBlockPos(this.wanderTarget));
		}
		this.writeInventoryToTag(tag);
	}

	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		if (tag.contains("Offers", 10)) {
			this.offers = new MerchantOffers(tag.getCompound("Offers"));
		}
		if (tag.contains("WanderTarget")) {
			this.wanderTarget = NbtUtils.readBlockPos(tag.getCompound("WanderTarget"));
		}
		this.readInventoryFromTag(tag);
	}

	public void setWanderTarget(@Nullable BlockPos p_35884_) {
		this.wanderTarget = p_35884_;
	}

	@Nullable
	BlockPos getWanderTarget() {
		return this.wanderTarget;
	}

	@Nullable
	public Entity changeDimension(ServerLevel p_35295_, net.minecraftforge.common.util.ITeleporter teleporter) {
		this.stopTrading();
		return super.changeDimension(p_35295_, teleporter);
	}

	protected void stopTrading() {
		this.setTradingPlayer((Player)null);
	}

	public void die(DamageSource p_35270_) {
		super.die(p_35270_);
		this.stopTrading();
	}

	public boolean canBeLeashed(Player p_35272_) {
		return false;
	}

	public SimpleContainer getInventory() {
		return this.inventory;
	}

	public SlotAccess getSlot(int p_149995_) {
		int i = p_149995_ - 300;
		return i >= 0 && i < this.inventory.getContainerSize() ? SlotAccess.forContainer(this.inventory, i) : super.getSlot(p_149995_);
	}

	protected void updateTrades() {
		CollectorTrades.ItemListing[] acollectortrades$itemlisting = CollectorTrades.COLLECTOR_TRADES.get(1);
		CollectorTrades.ItemListing[] acollectortrades$itemlisting1 = CollectorTrades.COLLECTOR_TRADES.get(2);
		if (acollectortrades$itemlisting != null && acollectortrades$itemlisting1 != null) {
			MerchantOffers merchantoffers = this.getOffers();
			this.addOffersFromItemListings(merchantoffers, acollectortrades$itemlisting, 5);
			int i = this.random.nextInt(acollectortrades$itemlisting1.length);
			CollectorTrades.ItemListing collectortrades$itemlisting = acollectortrades$itemlisting1[i];
			MerchantOffer merchantoffer = collectortrades$itemlisting.getOffer(this, this.random);
			if (merchantoffer != null) {
				merchantoffers.add(merchantoffer);
			}

		}
	}

	protected void addOffersFromItemListings(MerchantOffers p_35278_, CollectorTrades.ItemListing[] p_35279_, int p_35280_) {
		Set<Integer> set = Sets.newHashSet();
		if (p_35279_.length > p_35280_) {
			while(set.size() < p_35280_) {
				set.add(this.random.nextInt(p_35279_.length));
			}
		} else {
			for(int i = 0; i < p_35279_.length; ++i) {
				set.add(i);
			}
		}

		for(Integer integer : set) {
			CollectorTrades.ItemListing collectortrades$itemlisting = p_35279_[integer];
			MerchantOffer merchantoffer = collectortrades$itemlisting.getOffer(this, this.random);
			if (merchantoffer != null) {
				p_35278_.add(merchantoffer);
			}
		}

	}

	public Vec3 getRopeHoldPosition(float p_35318_) {
		float f = Mth.lerp(p_35318_, this.yBodyRotO, this.yBodyRot) * ((float)Math.PI / 180F);
		Vec3 vec3 = new Vec3(0.0D, this.getBoundingBox().getYsize() - 1.0D, 0.2D);
		return this.getPosition(p_35318_).add(vec3.yRot(-f));
	}

	public boolean isClientSide() {
		return this.level().isClientSide();
	}

	public boolean removeWhenFarAway(double p_35886_) {
		return false;
	}

	@Override
	public int getVillagerXp() {
		return 0;
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 20.0D).add(Attributes.MOVEMENT_SPEED, 0.2D);
	}

	static class CollectorTrades {
		public static final Int2ObjectMap<CollectorTrades.ItemListing[]> COLLECTOR_TRADES = toIntMap(ImmutableMap.of(
				1, new CollectorTrades.ItemListing[]{new CollectorTrades.ItemsForCoinfrogs(Items.SAND, 1, 16, 8), 
						new CollectorTrades.ItemsForCoinfrogs(Items.SEA_PICKLE, 1, 1, 5),
						new CollectorTrades.ItemsForCoinfrogs(LBItems.MARINE_CLAY.get(), 1, 8, 5), 
						new CollectorTrades.ItemsForCoinfrogs(Items.KELP, 1, 4, 12), 
						new CollectorTrades.ItemsForCoinfrogs(Items.SEAGRASS, 1, 4, 12), 
						new CollectorTrades.ItemsForCoinfrogs(LBItems.TEARTANG.get(), 1, 1, 8), 
						new CollectorTrades.ItemsForCoinfrogs(Items.IRON_INGOT, 1, 1, 4), 
						new CollectorTrades.ItemsForCoinfrogs(Items.GOLD_NUGGET, 1, 3, 12), 
						new CollectorTrades.ItemsForCoinfrogs(LBItems.WATERFLY.get(), 1, 1, 8), 
						new CollectorTrades.ItemsForCoinfrogs(LBItems.TEARTANG_FRUIT.get(), 2, 1, 12), 
						new CollectorTrades.ItemsForCoinfrogs(Items.NAUTILUS_SHELL, 3, 1, 4), 
						new CollectorTrades.ItemsForCoinfrogs(Items.SUGAR_CANE, 1, 1, 12), 
						new CollectorTrades.ItemsForCoinfrogs(Items.LILY_PAD, 1, 1, 12), 
						new CollectorTrades.ItemsForCoinfrogs(Items.LAPIS_LAZULI, 1, 1, 8), 
						new CollectorTrades.ItemsForCoinfrogs(LBItems.RAINBOW_SEADRAGON_SCALE.get(), 4, 2, 6), 
						new CollectorTrades.ItemsForCoinfrogs(LBItems.GOLDEN_SEADRAGON_SCALE.get(), 4, 2, 6), 
						new CollectorTrades.ItemsForCoinfrogs(LBItems.OPAL_SEADRAGON_SCALE.get(), 4, 2, 6), 
						new CollectorTrades.ItemsForCoinfrogs(LBItems.ORANGE_SEADRAGON_SCALE.get(), 4, 2, 6), 
						new CollectorTrades.ItemsForCoinfrogs(Items.TROPICAL_FISH_BUCKET, 4, 1, 4), 
						new CollectorTrades.ItemsForCoinfrogs(Items.PUFFERFISH_BUCKET, 4, 1, 4), 
						new CollectorTrades.ItemsForCoinfrogs(LBItems.TROPICAL_SEADRAGON_BUCKET.get(), 4, 1, 4), 
						new CollectorTrades.ItemsForCoinfrogs(LBItems.PROBOSCIS_FISH_BUCKET.get(), 4, 1, 4), 
						new CollectorTrades.ItemsForCoinfrogs(Items.BRAIN_CORAL_BLOCK, 5, 1, 8), 
						new CollectorTrades.ItemsForCoinfrogs(Items.BUBBLE_CORAL_BLOCK, 5, 1, 8), 
						new CollectorTrades.ItemsForCoinfrogs(Items.FIRE_CORAL_BLOCK, 5, 1, 8),
						new CollectorTrades.ItemsForCoinfrogs(Items.HORN_CORAL_BLOCK, 5, 1, 8), 
						new CollectorTrades.ItemsForCoinfrogs(Items.TUBE_CORAL_BLOCK, 5, 1, 8),
						new CollectorTrades.ItemsForCoinfrogs(LBItems.GOLDEN_TROPICAL_SCALEBLOCK.get(), 5, 1, 8),
						new CollectorTrades.ItemsForCoinfrogs(LBItems.OPAL_TROPICAL_SCALEBLOCK.get(), 5, 1, 8),
						new CollectorTrades.ItemsForCoinfrogs(LBItems.ORANGE_TROPICAL_SCALEBLOCK.get(), 5, 1, 8),
						new CollectorTrades.ItemsForCoinfrogs(LBItems.RAINBOW_TROPICAL_SCALEBLOCK.get(), 5, 1, 8)}, 
				2, new CollectorTrades.ItemListing[]{new CollectorTrades.ItemsForCoinfrogs(LBItems.HOLLOW_BATHYAL_SHELL.get(), 6, 2, 4), 
						new CollectorTrades.ItemsForCoinfrogs(LBItems.HOLLOW_INTERTIDAL_SHELL.get(), 6, 2, 4), 
						new CollectorTrades.ItemsForCoinfrogs(LBItems.HOLLOW_MIDNIGHT_SHELL.get(), 6, 2, 4), 
						new CollectorTrades.ItemsForCoinfrogs(LBItems.HOLLOW_SANDY_SHELL.get(), 6, 2, 4), 
						new CollectorTrades.ItemsForCoinfrogs(LBItems.HOLLOW_SPUME_SHELL.get(), 6, 2, 4)}));

		private static Int2ObjectMap<CollectorTrades.ItemListing[]> toIntMap(ImmutableMap<Integer, CollectorTrades.ItemListing[]> p_35631_) {
			return new Int2ObjectOpenHashMap<>(p_35631_);
		}

		public interface ItemListing {
			@Nullable
			MerchantOffer getOffer(Entity p_219693_, RandomSource p_219694_);
		}

		static class ItemsForCoinfrogs implements CollectorTrades.ItemListing {
			private final ItemStack itemStack;
			private final int coinFrogCost;
			private final int numberOfItems;
			private final int maxUses;
			private final float priceMultiplier;

			public ItemsForCoinfrogs(Item item, int p_35747_, int p_35748_, int p_35749_) {
				this(new ItemStack(item), p_35747_, p_35748_, p_35749_, 1);
			}

			public ItemsForCoinfrogs(ItemStack stack, int coinFrogCost, int numberOfItems, int maxUses, float priceMuliplier) {
				this.itemStack = stack;
				this.coinFrogCost = coinFrogCost;
				this.numberOfItems = numberOfItems;
				this.maxUses = maxUses;
				this.priceMultiplier = priceMuliplier;
			}

			public MerchantOffer getOffer(Entity p_219699_, RandomSource p_219700_) {
				return new MerchantOffer(new ItemStack(LBItems.COINFROG.get(), this.coinFrogCost), new ItemStack(this.itemStack.getItem(), this.numberOfItems), this.maxUses, 0, this.priceMultiplier);
			}
		}

	}

	class WanderToPositionGoal extends Goal {
		final Collector collector;
		final double stopDistance;
		final double speedModifier;

		WanderToPositionGoal(Collector collector, double p_35900_, double p_35901_) {
			this.collector = collector;
			this.stopDistance = p_35900_;
			this.speedModifier = p_35901_;
			this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		public void stop() {
			this.collector.setWanderTarget(null);
			Collector.this.navigation.stop();
		}

		public boolean canUse() {
			BlockPos blockpos = this.collector.getWanderTarget();
			return blockpos != null && this.isTooFarAway(blockpos, this.stopDistance);
		}

		public void tick() {
			BlockPos blockpos = this.collector.getWanderTarget();
			if (blockpos != null && Collector.this.navigation.isDone()) {
				if (this.isTooFarAway(blockpos, 10.0D)) {
					Vec3 vec3 = (new Vec3((double)blockpos.getX() - this.collector.getX(), (double)blockpos.getY() - this.collector.getY(), (double)blockpos.getZ() - this.collector.getZ())).normalize();
					Vec3 vec31 = vec3.scale(10.0D).add(this.collector.getX(), this.collector.getY(), this.collector.getZ());
					Collector.this.navigation.moveTo(vec31.x, vec31.y, vec31.z, this.speedModifier);
				} else {
					Collector.this.navigation.moveTo(blockpos.getX(), blockpos.getY(), blockpos.getZ(), this.speedModifier);
				}
			}

		}

		private boolean isTooFarAway(BlockPos p_35904_, double p_35905_) {
			return !p_35904_.closerToCenterThan(this.collector.position(), p_35905_);
		}
	}

	static class MoveHelperController extends MoveControl {
		private final Collector collector;

		MoveHelperController(Collector collector) {
			super(collector);
			this.collector = collector;
		}

		public void tick() {
			if (this.collector.horizontalCollision && this.collector.level().getBlockState(this.collector.blockPosition().above()).getBlock() == Blocks.WATER) {
				this.collector.setDeltaMovement(this.collector.getDeltaMovement().add(0.0D, 0.025D, 0.0D));
			}
			if (this.operation == MoveControl.Operation.MOVE_TO && !this.collector.getNavigation().isDone()) {
				double d0 = this.wantedX - this.collector.getX();
				double d1 = this.wantedY - this.collector.getY();
				double d2 = this.wantedZ - this.collector.getZ();
				double d3 = Mth.sqrt((float) (d0 * d0 + d1 * d1 + d2 * d2));
				d1 = d1 / d3;
				float f = (float) (Mth.atan2(d2, d0) * (double) (180F / (float) Math.PI)) - 90.0F;
				this.collector.setYRot(this.rotlerp(this.collector.getYRot(), f, 90.0F));
				this.collector.yBodyRot = this.collector.getYRot();

				float f1 = (float) (this.speedModifier * this.collector.getAttributeValue(Attributes.MOVEMENT_SPEED));
				if (collector.isInWater()) {
					float speedMod = 5.0F;
					f1 = f1 * speedMod;
				}

				this.collector.setSpeed(Mth.lerp(0.125F, this.collector.getSpeed(), f1));
				this.collector.setDeltaMovement(this.collector.getDeltaMovement().add(0.0D, (double) this.collector.getSpeed() * d1 * 0.1D, 0.0D));
			} else {
				this.collector.setSpeed(0.0F);
			}
		}
	}
}
