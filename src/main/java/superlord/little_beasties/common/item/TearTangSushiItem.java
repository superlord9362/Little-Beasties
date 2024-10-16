package superlord.little_beasties.common.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class TearTangSushiItem extends Item {

	public TearTangSushiItem(Properties p_41383_) {
		super(p_41383_);
	}

	public ItemStack finishUsingItem(ItemStack p_41409_, Level p_41410_, LivingEntity p_41411_) {
		if (this.isEdible()) {
			if (p_41411_.isUnderWater()) {
				if (p_41411_.getAirSupply() + 60 >= p_41411_.getMaxAirSupply()) p_41411_.setAirSupply(p_41411_.getMaxAirSupply());
				else p_41411_.setAirSupply(p_41411_.getAirSupply() + 180);
			}
			return p_41411_.eat(p_41410_, p_41409_);
		}
		else return p_41409_;
	}
	
}
