package superlord.little_beasties.common.item;

import java.util.List;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import superlord.little_beasties.common.entity.BlueManefish;
import superlord.little_beasties.common.entity.CoinfrogTadpole;
import superlord.little_beasties.common.entity.Mohomooho;
import superlord.little_beasties.common.entity.ProboscisFish;
import superlord.little_beasties.common.entity.Rainwitch;
import superlord.little_beasties.common.entity.Saildrifter;
import superlord.little_beasties.common.entity.Sealight;
import superlord.little_beasties.common.entity.TropicalDartfish;
import superlord.little_beasties.common.entity.TropicalSeadragon;

public class LBBucketItem extends BucketItem {
	private final Supplier<? extends EntityType<?>> entityTypeSupplier;
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
	
	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> toolTip, TooltipFlag flag) {
	}

	private void spawn(ServerLevel world, ItemStack stack, BlockPos pos) {
		Entity entity = this.entityTypeSupplier.get().spawn(world, stack, (Player)null, pos, MobSpawnType.BUCKET, true, false);
		if (stack.hasCustomHoverName()) entity.setCustomName(stack.getHoverName());
		if (entity != null) {
			if (entity instanceof TropicalSeadragon) {
				((TropicalSeadragon)entity).setFromBucket(true);
				if (stack.hasTag()) ((TropicalSeadragon)entity).setColor(stack.getTag().getInt("Color"));
			}
			if (entity instanceof TropicalDartfish) {
				((TropicalDartfish)entity).setFromBucket(true);
			}
			if (entity instanceof BlueManefish) {
				((BlueManefish)entity).setFromBucket(true);
			}
			if (entity instanceof Saildrifter) {
				((Saildrifter)entity).setFromBucket(true);
				if (stack.hasTag()) ((Saildrifter)entity).setColor(stack.getTag().getInt("Color"));
			}
			if (entity instanceof ProboscisFish) {
				((ProboscisFish)entity).setFromBucket(true);
			}
			if (entity instanceof Sealight) {
				((Sealight)entity).setFromBucket(true);
				if (stack.hasTag()) ((Sealight)entity).setColor(stack.getTag().getInt("Color"));
			}
			if (entity instanceof Rainwitch) {
				((Rainwitch)entity).setFromBucket(true);
			}
			if (entity instanceof CoinfrogTadpole) {
				((CoinfrogTadpole)entity).setFromBucket(true);
			}
			if (entity instanceof Mohomooho) {
				((Mohomooho)entity).setFromBucket(true);
				if (stack.hasTag()) ((Mohomooho)entity).setColor(stack.getTag().getInt("Color"));
			}
		}
	}
}
