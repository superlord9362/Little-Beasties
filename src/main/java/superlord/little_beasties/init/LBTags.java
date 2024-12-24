package superlord.little_beasties.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import superlord.little_beasties.LittleBeasties;

public class LBTags {

	public static final TagKey<Block> RED_BLOCKS = registerBlockTag("red_blocks");
	
	public static final TagKey<Biome> HAS_BATHYAL_HOLLOW_REMAINS = registerBiomeTag("has_structure/bathyal_hollow_remain");
	public static final TagKey<Biome> HAS_INTERTIDAL_HOLLOW_REMAINS = registerBiomeTag("has_structure/intertidal_hollow_remain");
	public static final TagKey<Biome> HAS_MIDNIGHT_HOLLOW_REMAINS = registerBiomeTag("has_structure/midnight_hollow_remain");
	public static final TagKey<Biome> HAS_SANDY_HOLLOW_REMAINS = registerBiomeTag("has_structure/sandy_hollow_remain");
	public static final TagKey<Biome> HAS_SPUME_HOLLOW_REMAINS = registerBiomeTag("has_structure/spume_hollow_remain");
	public static final TagKey<Biome> HAS_COLLECTOR_HUT = registerBiomeTag("has_structure/collectors_hut");
	
	public static final TagKey<PoiType> MANEFISH_HIVES = registerPoiTag("manefish_hives"); 
	
	private static TagKey<Block> registerBlockTag(String name) {
		return TagKey.create(Registries.BLOCK, new ResourceLocation(LittleBeasties.MOD_ID, name));
	}
	
	private static TagKey<Biome> registerBiomeTag(String name) {
		return TagKey.create(Registries.BIOME, new ResourceLocation(LittleBeasties.MOD_ID, name));
	}
	
	private static TagKey<PoiType> registerPoiTag(String name) {
		return TagKey.create(Registries.POINT_OF_INTEREST_TYPE, new ResourceLocation(LittleBeasties.MOD_ID, name));
	}
	
}
