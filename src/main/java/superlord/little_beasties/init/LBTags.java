package superlord.little_beasties.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import superlord.little_beasties.LittleBeasties;

public class LBTags {

	public static final TagKey<Block> RED_BLOCKS = registerBlockTag("red_blocks");
	
	private static TagKey<Block> registerBlockTag(String name) {
		return TagKey.create(Registries.BLOCK, new ResourceLocation(LittleBeasties.MOD_ID, name));
	}
	
}
