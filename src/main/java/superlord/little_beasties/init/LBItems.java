package superlord.little_beasties.init;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import superlord.little_beasties.LittleBeasties;
import superlord.little_beasties.common.item.LBBucketItem;

public class LBItems {
	public static final DeferredRegister<Item> SPAWN_EGGS = DeferredRegister.create(ForgeRegistries.ITEMS, LittleBeasties.MOD_ID);
	public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, LittleBeasties.MOD_ID);
	public static final DeferredRegister<Item> BLOCKS = DeferredRegister.create(ForgeRegistries.ITEMS, LittleBeasties.MOD_ID);

	public static final RegistryObject<Item> TROPICAL_DARTFISH_SPAWN_EGG = SPAWN_EGGS.register("tropical_dartfish_spawn_egg", () -> new ForgeSpawnEggItem(LBEntities.TROPICAL_DARTFISH, 0xE70D01, 0x5D78BE, new Item.Properties()));
	public static final RegistryObject<Item> BLUE_MANEFISH_SPAWN_EGG = SPAWN_EGGS.register("blue_manefish_spawn_egg", () -> new ForgeSpawnEggItem(LBEntities.BLUE_MANEFISH, 0x0A7BD1, 0x3A4782, new Item.Properties()));
	public static final RegistryObject<Item> SAILDRIFTER_SPAWN_EGG = SPAWN_EGGS.register("saildrifter_spawn_egg", () -> new ForgeSpawnEggItem(LBEntities.SAILDRIFTER, 0x46A2CC, 0x0F102D, new Item.Properties()));
	public static final RegistryObject<Item> TROPICAL_SEADRAGON_SPAWN_EGG = SPAWN_EGGS.register("tropical_seadragon_spawn_egg", () -> new ForgeSpawnEggItem(LBEntities.TROPICAL_SEADRAGON, 0xD85D04, 0x2E58A4, new Item.Properties()));
	
	public static final RegistryObject<Item> TROPICAL_DARTFISH_BUCKET = REGISTER.register("tropical_dartfish_bucket", () -> new LBBucketItem(LBEntities.TROPICAL_DARTFISH, () -> Fluids.WATER, new Item.Properties().stacksTo(1)));
	public static final RegistryObject<Item> BLUE_MANEFISH_BUCKET = REGISTER.register("blue_manefish_bucket", () -> new LBBucketItem(LBEntities.BLUE_MANEFISH, () -> Fluids.WATER, new Item.Properties().stacksTo(1)));
	public static final RegistryObject<Item> SAILDRIFTER_BUCKET = REGISTER.register("saildrifter_bucket", () -> new LBBucketItem(LBEntities.SAILDRIFTER, () -> Fluids.WATER, new Item.Properties().stacksTo(1)));
	public static final RegistryObject<Item> TROPICAL_SEADRAGON_BUCKET = REGISTER.register("tropical_seadragon_bucket", () -> new LBBucketItem(LBEntities.TROPICAL_SEADRAGON, () -> Fluids.WATER, new Item.Properties().stacksTo(1)));

	public static final RegistryObject<Item> MARINE_CLAY = BLOCKS.register("marine_clay", () -> new BlockItem(LBBlocks.MARINE_CLAY.get(), new Properties()));
	public static final RegistryObject<Item> MARINE_TERRACOTTA = BLOCKS.register("marine_terracotta", () -> new BlockItem(LBBlocks.MARINE_TERRACOTTA.get(), new Properties()));
	public static final RegistryObject<Item> CHISELED_MARINE_TERRACOTTA = BLOCKS.register("chiseled_marine_terracotta", () -> new BlockItem(LBBlocks.CHISELED_MARINE_TERRACOTTA.get(), new Properties()));
	public static final RegistryObject<Item> MARINE_TERRACOTTA_PILLAR = BLOCKS.register("marine_terracotta_pillar", () -> new BlockItem(LBBlocks.MARINE_TERRACOTTA_PILLAR.get(), new Properties()));
	
	public static final RegistryObject<Item> BLACK_MARINE_TERRACOTTA = BLOCKS.register("black_marine_terracotta", () -> new BlockItem(LBBlocks.BLACK_MARINE_TERRACOTTA.get(), new Properties()));
	public static final RegistryObject<Item> CHISELED_BLACK_MARINE_TERRACOTTA = BLOCKS.register("chiseled_black_marine_terracotta", () -> new BlockItem(LBBlocks.CHISELED_BLACK_MARINE_TERRACOTTA.get(), new Properties()));
	public static final RegistryObject<Item> BLACK_MARINE_TERRACOTTA_PILLAR = BLOCKS.register("black_marine_terracotta_pillar", () -> new BlockItem(LBBlocks.BLACK_MARINE_TERRACOTTA_PILLAR.get(), new Properties()));
	public static final RegistryObject<Item> BLACK_GLAZED_MARINE_TERRACOTTA = BLOCKS.register("black_glazed_marine_terracotta", () -> new BlockItem(LBBlocks.BLACK_GLAZED_MARINE_TERRACOTTA.get(), new Properties()));

	public static final RegistryObject<Item> BLUE_MARINE_TERRACOTTA = BLOCKS.register("blue_marine_terracotta", () -> new BlockItem(LBBlocks.BLUE_MARINE_TERRACOTTA.get(), new Properties()));
	public static final RegistryObject<Item> CHISELED_BLUE_MARINE_TERRACOTTA = BLOCKS.register("chiseled_blue_marine_terracotta", () -> new BlockItem(LBBlocks.CHISELED_BLUE_MARINE_TERRACOTTA.get(), new Properties()));
	public static final RegistryObject<Item> BLUE_MARINE_TERRACOTTA_PILLAR = BLOCKS.register("blue_marine_terracotta_pillar", () -> new BlockItem(LBBlocks.BLUE_MARINE_TERRACOTTA_PILLAR.get(), new Properties()));
	public static final RegistryObject<Item> BLUE_GLAZED_MARINE_TERRACOTTA = BLOCKS.register("blue_glazed_marine_terracotta", () -> new BlockItem(LBBlocks.BLUE_GLAZED_MARINE_TERRACOTTA.get(), new Properties()));

	public static final RegistryObject<Item> BROWN_MARINE_TERRACOTTA = BLOCKS.register("brown_marine_terracotta", () -> new BlockItem(LBBlocks.BROWN_MARINE_TERRACOTTA.get(), new Properties()));
	public static final RegistryObject<Item> CHISELED_BROWN_MARINE_TERRACOTTA = BLOCKS.register("chiseled_brown_marine_terracotta", () -> new BlockItem(LBBlocks.CHISELED_BROWN_MARINE_TERRACOTTA.get(), new Properties()));
	public static final RegistryObject<Item> BROWN_MARINE_TERRACOTTA_PILLAR = BLOCKS.register("brown_marine_terracotta_pillar", () -> new BlockItem(LBBlocks.BROWN_MARINE_TERRACOTTA_PILLAR.get(), new Properties()));
	public static final RegistryObject<Item> BROWN_GLAZED_MARINE_TERRACOTTA = BLOCKS.register("brown_glazed_marine_terracotta", () -> new BlockItem(LBBlocks.BROWN_GLAZED_MARINE_TERRACOTTA.get(), new Properties()));

	public static final RegistryObject<Item> CYAN_MARINE_TERRACOTTA = BLOCKS.register("cyan_marine_terracotta", () -> new BlockItem(LBBlocks.CYAN_MARINE_TERRACOTTA.get(), new Properties()));
	public static final RegistryObject<Item> CHISELED_CYAN_MARINE_TERRACOTTA = BLOCKS.register("chiseled_cyan_marine_terracotta", () -> new BlockItem(LBBlocks.CHISELED_CYAN_MARINE_TERRACOTTA.get(), new Properties()));
	public static final RegistryObject<Item> CYAN_MARINE_TERRACOTTA_PILLAR = BLOCKS.register("cyan_marine_terracotta_pillar", () -> new BlockItem(LBBlocks.CYAN_MARINE_TERRACOTTA_PILLAR.get(), new Properties()));
	public static final RegistryObject<Item> CYAN_GLAZED_MARINE_TERRACOTTA = BLOCKS.register("cyan_glazed_marine_terracotta", () -> new BlockItem(LBBlocks.CYAN_GLAZED_MARINE_TERRACOTTA.get(), new Properties()));

	public static final RegistryObject<Item> GRAY_MARINE_TERRACOTTA = BLOCKS.register("gray_marine_terracotta", () -> new BlockItem(LBBlocks.GRAY_MARINE_TERRACOTTA.get(), new Properties()));
	public static final RegistryObject<Item> CHISELED_GRAY_MARINE_TERRACOTTA = BLOCKS.register("chiseled_gray_marine_terracotta", () -> new BlockItem(LBBlocks.CHISELED_GRAY_MARINE_TERRACOTTA.get(), new Properties()));
	public static final RegistryObject<Item> GRAY_MARINE_TERRACOTTA_PILLAR = BLOCKS.register("gray_marine_terracotta_pillar", () -> new BlockItem(LBBlocks.GRAY_MARINE_TERRACOTTA_PILLAR.get(), new Properties()));
	public static final RegistryObject<Item> GRAY_GLAZED_MARINE_TERRACOTTA = BLOCKS.register("gray_glazed_marine_terracotta", () -> new BlockItem(LBBlocks.GRAY_GLAZED_MARINE_TERRACOTTA.get(), new Properties()));

	public static final RegistryObject<Item> GREEN_MARINE_TERRACOTTA = BLOCKS.register("green_marine_terracotta", () -> new BlockItem(LBBlocks.GREEN_MARINE_TERRACOTTA.get(), new Properties()));
	public static final RegistryObject<Item> CHISELED_GREEN_MARINE_TERRACOTTA = BLOCKS.register("chiseled_green_marine_terracotta", () -> new BlockItem(LBBlocks.CHISELED_GREEN_MARINE_TERRACOTTA.get(), new Properties()));
	public static final RegistryObject<Item> GREEN_MARINE_TERRACOTTA_PILLAR = BLOCKS.register("green_marine_terracotta_pillar", () -> new BlockItem(LBBlocks.GREEN_MARINE_TERRACOTTA_PILLAR.get(), new Properties()));
	public static final RegistryObject<Item> GREEN_GLAZED_MARINE_TERRACOTTA = BLOCKS.register("green_glazed_marine_terracotta", () -> new BlockItem(LBBlocks.GREEN_GLAZED_MARINE_TERRACOTTA.get(), new Properties()));

	public static final RegistryObject<Item> LIGHT_BLUE_MARINE_TERRACOTTA = BLOCKS.register("light_blue_marine_terracotta", () -> new BlockItem(LBBlocks.LIGHT_BLUE_MARINE_TERRACOTTA.get(), new Properties()));
	public static final RegistryObject<Item> CHISELED_LIGHT_BLUE_MARINE_TERRACOTTA = BLOCKS.register("chiseled_light_blue_marine_terracotta", () -> new BlockItem(LBBlocks.CHISELED_LIGHT_BLUE_MARINE_TERRACOTTA.get(), new Properties()));
	public static final RegistryObject<Item> LIGHT_BLUE_MARINE_TERRACOTTA_PILLAR = BLOCKS.register("light_blue_marine_terracotta_pillar", () -> new BlockItem(LBBlocks.LIGHT_BLUE_MARINE_TERRACOTTA_PILLAR.get(), new Properties()));
	public static final RegistryObject<Item> LIGHT_BLUE_GLAZED_MARINE_TERRACOTTA = BLOCKS.register("light_blue_glazed_marine_terracotta", () -> new BlockItem(LBBlocks.LIGHT_BLUE_GLAZED_MARINE_TERRACOTTA.get(), new Properties()));

	public static final RegistryObject<Item> LIGHT_GRAY_MARINE_TERRACOTTA = BLOCKS.register("light_gray_marine_terracotta", () -> new BlockItem(LBBlocks.LIGHT_GRAY_MARINE_TERRACOTTA.get(), new Properties()));
	public static final RegistryObject<Item> CHISELED_LIGHT_GRAY_MARINE_TERRACOTTA = BLOCKS.register("chiseled_light_gray_marine_terracotta", () -> new BlockItem(LBBlocks.CHISELED_LIGHT_GRAY_MARINE_TERRACOTTA.get(), new Properties()));
	public static final RegistryObject<Item> LIGHT_GRAY_MARINE_TERRACOTTA_PILLAR = BLOCKS.register("light_gray_marine_terracotta_pillar", () -> new BlockItem(LBBlocks.LIGHT_GRAY_MARINE_TERRACOTTA_PILLAR.get(), new Properties()));
	public static final RegistryObject<Item> LIGHT_GRAY_GLAZED_MARINE_TERRACOTTA = BLOCKS.register("light_gray_glazed_marine_terracotta", () -> new BlockItem(LBBlocks.LIGHT_GRAY_GLAZED_MARINE_TERRACOTTA.get(), new Properties()));

	public static final RegistryObject<Item> LIME_MARINE_TERRACOTTA = BLOCKS.register("lime_marine_terracotta", () -> new BlockItem(LBBlocks.LIME_MARINE_TERRACOTTA.get(), new Properties()));
	public static final RegistryObject<Item> CHISELED_LIME_MARINE_TERRACOTTA = BLOCKS.register("chiseled_lime_marine_terracotta", () -> new BlockItem(LBBlocks.CHISELED_LIME_MARINE_TERRACOTTA.get(), new Properties()));
	public static final RegistryObject<Item> LIME_MARINE_TERRACOTTA_PILLAR = BLOCKS.register("lime_marine_terracotta_pillar", () -> new BlockItem(LBBlocks.LIME_MARINE_TERRACOTTA_PILLAR.get(), new Properties()));
	public static final RegistryObject<Item> LIME_GLAZED_MARINE_TERRACOTTA = BLOCKS.register("lime_glazed_marine_terracotta", () -> new BlockItem(LBBlocks.LIME_GLAZED_MARINE_TERRACOTTA.get(), new Properties()));

	public static final RegistryObject<Item> MAGENTA_MARINE_TERRACOTTA = BLOCKS.register("magenta_marine_terracotta", () -> new BlockItem(LBBlocks.MAGENTA_MARINE_TERRACOTTA.get(), new Properties()));
	public static final RegistryObject<Item> CHISELED_MAGENTA_MARINE_TERRACOTTA = BLOCKS.register("chiseled_magenta_marine_terracotta", () -> new BlockItem(LBBlocks.CHISELED_MAGENTA_MARINE_TERRACOTTA.get(), new Properties()));
	public static final RegistryObject<Item> MAGENTA_MARINE_TERRACOTTA_PILLAR = BLOCKS.register("magenta_marine_terracotta_pillar", () -> new BlockItem(LBBlocks.MAGENTA_MARINE_TERRACOTTA_PILLAR.get(), new Properties()));
	public static final RegistryObject<Item> MAGENTA_GLAZED_MARINE_TERRACOTTA = BLOCKS.register("magenta_glazed_marine_terracotta", () -> new BlockItem(LBBlocks.MAGENTA_GLAZED_MARINE_TERRACOTTA.get(), new Properties()));

	public static final RegistryObject<Item> ORANGE_MARINE_TERRACOTTA = BLOCKS.register("orange_marine_terracotta", () -> new BlockItem(LBBlocks.ORANGE_MARINE_TERRACOTTA.get(), new Properties()));
	public static final RegistryObject<Item> CHISELED_ORANGE_MARINE_TERRACOTTA = BLOCKS.register("chiseled_orange_marine_terracotta", () -> new BlockItem(LBBlocks.CHISELED_ORANGE_MARINE_TERRACOTTA.get(), new Properties()));
	public static final RegistryObject<Item> ORANGE_MARINE_TERRACOTTA_PILLAR = BLOCKS.register("orange_marine_terracotta_pillar", () -> new BlockItem(LBBlocks.ORANGE_MARINE_TERRACOTTA_PILLAR.get(), new Properties()));
	public static final RegistryObject<Item> ORANGE_GLAZED_MARINE_TERRACOTTA = BLOCKS.register("orange_glazed_marine_terracotta", () -> new BlockItem(LBBlocks.ORANGE_GLAZED_MARINE_TERRACOTTA.get(), new Properties()));

	public static final RegistryObject<Item> PINK_MARINE_TERRACOTTA = BLOCKS.register("pink_marine_terracotta", () -> new BlockItem(LBBlocks.PINK_MARINE_TERRACOTTA.get(), new Properties()));
	public static final RegistryObject<Item> CHISELED_PINK_MARINE_TERRACOTTA = BLOCKS.register("chiseled_pink_marine_terracotta", () -> new BlockItem(LBBlocks.CHISELED_PINK_MARINE_TERRACOTTA.get(), new Properties()));
	public static final RegistryObject<Item> PINK_MARINE_TERRACOTTA_PILLAR = BLOCKS.register("pink_marine_terracotta_pillar", () -> new BlockItem(LBBlocks.PINK_MARINE_TERRACOTTA_PILLAR.get(), new Properties()));
	public static final RegistryObject<Item> PINK_GLAZED_MARINE_TERRACOTTA = BLOCKS.register("pink_glazed_marine_terracotta", () -> new BlockItem(LBBlocks.PINK_GLAZED_MARINE_TERRACOTTA.get(), new Properties()));

	public static final RegistryObject<Item> PURPLE_MARINE_TERRACOTTA = BLOCKS.register("purple_marine_terracotta", () -> new BlockItem(LBBlocks.PURPLE_MARINE_TERRACOTTA.get(), new Properties()));
	public static final RegistryObject<Item> CHISELED_PURPLE_MARINE_TERRACOTTA = BLOCKS.register("chiseled_purple_marine_terracotta", () -> new BlockItem(LBBlocks.CHISELED_PURPLE_MARINE_TERRACOTTA.get(), new Properties()));
	public static final RegistryObject<Item> PURPLE_MARINE_TERRACOTTA_PILLAR = BLOCKS.register("purple_marine_terracotta_pillar", () -> new BlockItem(LBBlocks.PURPLE_MARINE_TERRACOTTA_PILLAR.get(), new Properties()));
	public static final RegistryObject<Item> PURPLE_GLAZED_MARINE_TERRACOTTA = BLOCKS.register("purple_glazed_marine_terracotta", () -> new BlockItem(LBBlocks.PURPLE_GLAZED_MARINE_TERRACOTTA.get(), new Properties()));

	public static final RegistryObject<Item> RED_MARINE_TERRACOTTA = BLOCKS.register("red_marine_terracotta", () -> new BlockItem(LBBlocks.RED_MARINE_TERRACOTTA.get(), new Properties()));
	public static final RegistryObject<Item> CHISELED_RED_MARINE_TERRACOTTA = BLOCKS.register("chiseled_red_marine_terracotta", () -> new BlockItem(LBBlocks.CHISELED_RED_MARINE_TERRACOTTA.get(), new Properties()));
	public static final RegistryObject<Item> RED_MARINE_TERRACOTTA_PILLAR = BLOCKS.register("red_marine_terracotta_pillar", () -> new BlockItem(LBBlocks.RED_MARINE_TERRACOTTA_PILLAR.get(), new Properties()));
	public static final RegistryObject<Item> RED_GLAZED_MARINE_TERRACOTTA = BLOCKS.register("red_glazed_marine_terracotta", () -> new BlockItem(LBBlocks.RED_GLAZED_MARINE_TERRACOTTA.get(), new Properties()));

	public static final RegistryObject<Item> WHITE_MARINE_TERRACOTTA = BLOCKS.register("white_marine_terracotta", () -> new BlockItem(LBBlocks.WHITE_MARINE_TERRACOTTA.get(), new Properties()));
	public static final RegistryObject<Item> CHISELED_WHITE_MARINE_TERRACOTTA = BLOCKS.register("chiseled_white_marine_terracotta", () -> new BlockItem(LBBlocks.CHISELED_WHITE_MARINE_TERRACOTTA.get(), new Properties()));
	public static final RegistryObject<Item> WHITE_MARINE_TERRACOTTA_PILLAR = BLOCKS.register("white_marine_terracotta_pillar", () -> new BlockItem(LBBlocks.WHITE_MARINE_TERRACOTTA_PILLAR.get(), new Properties()));
	public static final RegistryObject<Item> WHITE_GLAZED_MARINE_TERRACOTTA = BLOCKS.register("white_glazed_marine_terracotta", () -> new BlockItem(LBBlocks.WHITE_GLAZED_MARINE_TERRACOTTA.get(), new Properties()));

	public static final RegistryObject<Item> YELLOW_MARINE_TERRACOTTA = BLOCKS.register("yellow_marine_terracotta", () -> new BlockItem(LBBlocks.YELLOW_MARINE_TERRACOTTA.get(), new Properties()));
	public static final RegistryObject<Item> CHISELED_YELLOW_MARINE_TERRACOTTA = BLOCKS.register("chiseled_yellow_marine_terracotta", () -> new BlockItem(LBBlocks.CHISELED_YELLOW_MARINE_TERRACOTTA.get(), new Properties()));
	public static final RegistryObject<Item> YELLOW_MARINE_TERRACOTTA_PILLAR = BLOCKS.register("yellow_marine_terracotta_pillar", () -> new BlockItem(LBBlocks.YELLOW_MARINE_TERRACOTTA_PILLAR.get(), new Properties()));
	public static final RegistryObject<Item> YELLOW_GLAZED_MARINE_TERRACOTTA = BLOCKS.register("yellow_glazed_marine_terracotta", () -> new BlockItem(LBBlocks.YELLOW_GLAZED_MARINE_TERRACOTTA.get(), new Properties()));
	
}
