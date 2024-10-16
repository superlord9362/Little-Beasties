package superlord.little_beasties.common.item;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import superlord.little_beasties.common.entity.Coinfrog;
import superlord.little_beasties.init.LBEntities;

public class CoinfrogItem extends Item {

	public CoinfrogItem(Properties p_41383_) {
		super(p_41383_);
	}

	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		HitResult raytraceresult = getPlayerPOVHitResult(world, player, ClipContext.Fluid.SOURCE_ONLY);
		if (raytraceresult.getType() != HitResult.Type.BLOCK) {
			return InteractionResultHolder.pass(itemstack);
		} else if (!(world instanceof ServerLevel)) {
			return InteractionResultHolder.success(itemstack);
		} else {
			BlockHitResult blockraytraceresult = (BlockHitResult)raytraceresult;
			BlockPos blockpos = blockraytraceresult.getBlockPos();
			if (world.mayInteract(player, blockpos) && player.mayUseItemAt(blockpos, blockraytraceresult.getDirection(), itemstack)) {
				Coinfrog coinfrog = new Coinfrog(LBEntities.COINFROG.get(), world);
				coinfrog.setPos(raytraceresult.getLocation());
				if (itemstack.hasCustomHoverName()) coinfrog.setCustomName(itemstack.getHoverName());
				world.addFreshEntity(coinfrog);
				if (!player.getAbilities().instabuild) {
					itemstack.shrink(1);
				}
				player.awardStat(Stats.ITEM_USED.get(this));
				return InteractionResultHolder.consume(itemstack);	
			} else return InteractionResultHolder.fail(itemstack);
		}
	}

}
