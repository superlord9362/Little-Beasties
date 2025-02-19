package superlord.little_beasties.common.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import superlord.little_beasties.init.LBItems;

public class SnappyWoolbug extends PathfinderMob {
	private static final EntityDataAccessor<Boolean> FROM_ITEM = SynchedEntityData.defineId(SnappyWoolbug.class, EntityDataSerializers.BOOLEAN);

	public SnappyWoolbug(EntityType<? extends PathfinderMob> p_27557_, Level p_27558_) {
		super(p_27557_, p_27558_);
	}

	public void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, (double)1.2F, true));
		this.goalSelector.addGoal(1, new AttackFeetGoal(this, Player.class, true));
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
	}

	public boolean requiresCustomPersistence() {
		return super.requiresCustomPersistence() || this.fromItem();
	}

	public boolean removeWhenFarAway(double p_27492_) {
		return !this.fromItem() && !this.hasCustomName();
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 8.0D).add(Attributes.MOVEMENT_SPEED, (double)0.2F).add(Attributes.ATTACK_DAMAGE, 1.0D);
	}

	public boolean doHurtTarget(Entity p_28319_) {
		boolean flag = p_28319_.hurt(this.damageSources().mobAttack(this), (float)((int)this.getAttributeValue(Attributes.ATTACK_DAMAGE)));
		if (flag) {
			this.doEnchantDamageEffects(this, p_28319_);
		}

		return flag;
	}
	
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(FROM_ITEM, false);
	}
	
	
	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putBoolean("FromItem", this.fromItem());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		this.setFromItem(tag.getBoolean("FromItem"));
	}

	public void aiStep() {
		super.aiStep();
		for (Player entity : this.level().getEntitiesOfClass(Player.class, this.getBoundingBox().inflate(8, 8, 8))) {
			if (entity.getItemBySlot(EquipmentSlot.FEET).getItem() == Items.AIR && !entity.isCreative()) {
				this.setTarget(entity);
			} else this.setTarget(null);
		}
	}
	
	public boolean fromItem() {
		return this.entityData.get(FROM_ITEM);
	}

	public void setFromItem(boolean p_148834_) {
		this.entityData.set(FROM_ITEM, p_148834_);
	}

	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		Item item = stack.getItem();
		if (item == Items.AIR && this.getTarget() == null) {
			ItemStack dropStack = new ItemStack(LBItems.SNAPPY_WOOLBUG.get());
			if (this.hasCustomName()) {
				dropStack.setHoverName(getCustomName());
			}
			player.setItemInHand(InteractionHand.MAIN_HAND, dropStack);
			this.discard();
			return InteractionResult.SUCCESS;
		} else {
			return super.mobInteract(player, hand);
		}
	}

	@SuppressWarnings("rawtypes")
	public class AttackFeetGoal extends NearestAttackableTargetGoal {
		SnappyWoolbug woolbug;

		@SuppressWarnings("unchecked")
		public AttackFeetGoal(SnappyWoolbug goalOwner, Class target, boolean checkSight) {
			super(goalOwner, target, checkSight);
			this.woolbug = goalOwner;
		}

		public boolean canUse() {
			if (woolbug.getTarget() instanceof Player player) {
				if (player.getItemBySlot(EquipmentSlot.FEET).getItem() == null) {
					return super.canUse();
				} else return false;
			} else return false;
		}

	}

	public static boolean checkSnappyWoolbugSpawnRules(EntityType<? extends SnappyWoolbug> p_218105_, LevelAccessor p_218106_, MobSpawnType p_218107_, BlockPos p_218108_, RandomSource p_218109_) {
		return p_218106_.getBlockState(p_218108_.below()).is(BlockTags.ANIMALS_SPAWNABLE_ON) && isBrightEnoughToSpawn(p_218106_, p_218108_);
	}

	protected static boolean isBrightEnoughToSpawn(BlockAndTintGetter p_186210_, BlockPos p_186211_) {
		return p_186210_.getRawBrightness(p_186211_, 0) > 8;
	}


}
