package superlord.little_beasties.common.item;

import java.util.List;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class LBBucketItem extends BucketItem {
	
	@SuppressWarnings("unused")
	private final boolean hasTooltip;
	
	public LBBucketItem(Supplier<? extends EntityType<?>> entityType, Supplier<? extends Fluid> fluid, Properties builder) {
		this(entityType, fluid, builder, true);
	}
	
	public LBBucketItem(Supplier<? extends EntityType<?>> entityType, Supplier<? extends Fluid> fluid, Properties builder, boolean hasToolTip) {
		super(fluid, builder);
		this.hasTooltip = hasToolTip;
		this.entityTypeSupplier = entityType;
	}
	
	public void checkExtraContent(@Nullable Player player, Level world, ItemStack stack, BlockPos pos) {
		if (world instanceof ServerLevel) {
			this.spawn((ServerLevel) world, stack, pos);
			world.gameEvent(player, GameEvent.ENTITY_PLACE, pos);
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> toolTip, TooltipFlag flag) {
		super.appendHoverText(stack, world, toolTip, flag);

	}
	
	private void spawn(ServerLevel world, ItemStack stack, BlockPos pos) {
		this.entityTypeSupplier.get().spawn(world, stack, (Player)null, pos, MobSpawnType.BUCKET, true, false);
	}
	
	private final Supplier<? extends EntityType<?>> entityTypeSupplier;
	protected EntityType<?> getEntityType() {
		return entityTypeSupplier.get();
	}

}
