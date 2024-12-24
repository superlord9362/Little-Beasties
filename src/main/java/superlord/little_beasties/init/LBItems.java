package superlord.little_beasties.init;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.PlaceOnWaterBlockItem;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import superlord.little_beasties.LittleBeasties;
import superlord.little_beasties.common.item.CoinfrogItem;
import superlord.little_beasties.common.item.LBBucketItem;
import superlord.little_beasties.common.item.SnappyWoolbugItem;
import superlord.little_beasties.common.item.TearTangFruitItem;
import superlord.little_beasties.common.item.TearTangSushiItem;

public class LBItems {
	public static final DeferredRegister<Item> SPAWN_EGGS = DeferredRegister.create(ForgeRegistries.ITEMS, LittleBeasties.MOD_ID);
	public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, LittleBeasties.MOD_ID);
	public static final DeferredRegister<Item> BLOCKS = DeferredRegister.create(ForgeRegistries.ITEMS, LittleBeasties.MOD_ID);

	public static final RegistryObject<Item> TROPICAL_DARTFISH_SPAWN_EGG = SPAWN_EGGS.register("tropical_dartfish_spawn_egg", () -> new ForgeSpawnEggItem(LBEntities.TROPICAL_DARTFISH, 0xE70D01, 0x5D78BE, new Properties()));
	public static final RegistryObject<Item> BLUE_MANEFISH_SPAWN_EGG = SPAWN_EGGS.register("blue_manefish_spawn_egg", () -> new ForgeSpawnEggItem(LBEntities.BLUE_MANEFISH, 0x0A7BD1, 0x3A4782, new Properties()));
	public static final RegistryObject<Item> SAILDRIFTER_SPAWN_EGG = SPAWN_EGGS.register("saildrifter_spawn_egg", () -> new ForgeSpawnEggItem(LBEntities.SAILDRIFTER, 0x46A2CC, 0x0F102D, new Properties()));
	public static final RegistryObject<Item> TROPICAL_SEADRAGON_SPAWN_EGG = SPAWN_EGGS.register("tropical_seadragon_spawn_egg", () -> new ForgeSpawnEggItem(LBEntities.TROPICAL_SEADRAGON, 0xD85D04, 0x2E58A4, new Properties()));
	public static final RegistryObject<Item> PROBOSCIS_FISH_SPAWN_EGG = SPAWN_EGGS.register("proboscis_fish_spawn_egg", () -> new ForgeSpawnEggItem(LBEntities.PROBOSCIS_FISH, 0xEDDBD1, 0x40414A, new Properties()));
	public static final RegistryObject<Item> WAVE_HORNGLIDER_SPAWN_EGG = SPAWN_EGGS.register("wave_hornglider_spawn_egg", () -> new ForgeSpawnEggItem(LBEntities.WAVE_HORNGLIDER, 0x8AEDE4, 0x3DA5A9, new Properties()));
	public static final RegistryObject<Item> SEALIGHT_SPAWN_EGG = SPAWN_EGGS.register("sealight_spawn_egg", () -> new ForgeSpawnEggItem(LBEntities.SEA_LIGHT, 0x181CFF, 0xBCBFFF, new Properties()));
	public static final RegistryObject<Item> SNAPPY_WOOLBUG_SPAWN_EGG = SPAWN_EGGS.register("snappy_woolbug_spawn_egg", () -> new ForgeSpawnEggItem(LBEntities.SNAPPY_WOOLBUG, 0x311F16, 0xE6C9B6, new Properties()));
	public static final RegistryObject<Item> RAINWITCH_SPAWN_EGG = SPAWN_EGGS.register("rainwitch_spawn_egg", () -> new ForgeSpawnEggItem(LBEntities.RAINWITCH, 0xCDCCBD, 0xFAF160, new Properties()));
	public static final RegistryObject<Item> COINFROG_SPAWN_EGG = SPAWN_EGGS.register("coinfrog_spawn_egg", () -> new ForgeSpawnEggItem(LBEntities.COINFROG, 0x964E19, 0xC78A20, new Properties()));
	public static final RegistryObject<Item> COINFROG_TADPOLE_SPAWN_EEGG = SPAWN_EGGS.register("coinfrog_tadpole_spawn_egg", () -> new ForgeSpawnEggItem(LBEntities.COINFROG_TADPOLE, 0x7E4114, 0x4C2D16, new Properties()));
	public static final RegistryObject<Item> MOHOMOOHO_SPAWN_EGG = SPAWN_EGGS.register("mohomooho_spawn_egg", () -> new ForgeSpawnEggItem(LBEntities.MOHOMOOHO, 0x724935, 0xA3877B, new Properties()));
	public static final RegistryObject<Item> DAYDREAM_RAY_SPAWN_EGG = SPAWN_EGGS.register("daydream_ray_spawn_egg", () -> new ForgeSpawnEggItem(LBEntities.DAYDREAM_RAY, 0xFFEE6A, 0x0746B1, new Properties()));	
	public static final RegistryObject<Item> COLLECTOR_SPAWN_EGG = SPAWN_EGGS.register("collector_spawn_egg", () -> new ForgeSpawnEggItem(LBEntities.COLLECTOR, 0xB5C2D3, 0x29384D, new Properties()));
	
	public static final RegistryObject<Item> TROPICAL_DARTFISH_BUCKET = REGISTER.register("tropical_dartfish_bucket", () -> new LBBucketItem(LBEntities.TROPICAL_DARTFISH, () -> Fluids.WATER, new Properties().stacksTo(1)));
	public static final RegistryObject<Item> BLUE_MANEFISH_BUCKET = REGISTER.register("blue_manefish_bucket", () -> new LBBucketItem(LBEntities.BLUE_MANEFISH, () -> Fluids.WATER, new Properties().stacksTo(1)));
	public static final RegistryObject<Item> SAILDRIFTER_BUCKET = REGISTER.register("saildrifter_bucket", () -> new LBBucketItem(LBEntities.SAILDRIFTER, () -> Fluids.WATER, new Properties().stacksTo(1)));
	public static final RegistryObject<Item> TROPICAL_SEADRAGON_BUCKET = REGISTER.register("tropical_seadragon_bucket", () -> new LBBucketItem(LBEntities.TROPICAL_SEADRAGON, () -> Fluids.WATER, new Properties().stacksTo(1)));
	public static final RegistryObject<Item> PROBOSCIS_FISH_BUCKET = REGISTER.register("proboscis_fish_bucket", () -> new LBBucketItem(LBEntities.PROBOSCIS_FISH, () -> Fluids.WATER, new Properties().stacksTo(1)));
	public static final RegistryObject<Item> SEALIGHT_BUCKET = REGISTER.register("sealight_bucket", () -> new LBBucketItem(LBEntities.SEA_LIGHT, () -> Fluids.WATER, new Properties().stacksTo(1)));
	public static final RegistryObject<Item> RAINWITCH_BUCKET = REGISTER.register("rainwitch_bucket", () -> new LBBucketItem(LBEntities.RAINWITCH, () -> Fluids.WATER, new Properties().stacksTo(1)));
	public static final RegistryObject<Item> COINFROG_TADPOLE_BUCKET = REGISTER.register("coinfrog_tadpole_bucket", () -> new LBBucketItem(LBEntities.COINFROG_TADPOLE, () -> Fluids.WATER, new Properties().stacksTo(1)));
	public static final RegistryObject<Item> MOHOMOOHO_BUCKET = REGISTER.register("mohomooho_bucket", () -> new LBBucketItem(LBEntities.MOHOMOOHO, () -> Fluids.WATER, new Properties().stacksTo(1)));
	
	public static final RegistryObject<Item> GOLDEN_SEADRAGON_SCALE = REGISTER.register("golden_seadragon_scale", () -> new Item(new Properties()));
	public static final RegistryObject<Item> OPAL_SEADRAGON_SCALE = REGISTER.register("opal_seadragon_scale", () -> new Item(new Properties()));
	public static final RegistryObject<Item> ORANGE_SEADRAGON_SCALE = REGISTER.register("orange_seadragon_scale", () -> new Item(new Properties()));
	public static final RegistryObject<Item> RAINBOW_SEADRAGON_SCALE = REGISTER.register("rainbow_seadragon_scale", () -> new Item(new Properties()));
	
	public static final RegistryObject<Item> WATERFLY = REGISTER.register("waterfly", () -> new Item(new Properties()));
	
	public static final RegistryObject<Item> SNAPPY_WOOLBUG = REGISTER.register("snappy_woolbug", () -> new SnappyWoolbugItem(new Properties()));
	public static final RegistryObject<Item> COINFROG = REGISTER.register("frogcoin", () -> new CoinfrogItem(new Properties()));
	
	public static final RegistryObject<Item> TEARTANG_SPORES = REGISTER.register("teartang_spores", () -> new ItemNameBlockItem(LBBlocks.TEARTANG.get(), new Properties()));
	public static final RegistryObject<Item> TEARTANG = REGISTER.register("teartang", () -> new Item(new Properties()));
	public static final RegistryObject<Item> TEARTANG_FRUIT = REGISTER.register("teartang_fruit", () -> new TearTangFruitItem(new Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(0.3F).build())));
	public static final RegistryObject<Item> TEARTANG_SUSHI = REGISTER.register("teartang_sushi", () -> new TearTangSushiItem(new Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.3F).fast().build())));
	
	public static final RegistryObject<Item> GOLDEN_TROPICAL_SCALEBLOCK = BLOCKS.register("golden_tropical_scaleblock", () -> new BlockItem(LBBlocks.GOLDEN_TROPICAL_SCALEBLOCK.get(), new Properties()));
	public static final RegistryObject<Item> OPAL_TROPICAL_SCALEBLOCK = BLOCKS.register("opal_tropical_scaleblock", () -> new BlockItem(LBBlocks.OPAL_TROPICAL_SCALEBLOCK.get(), new Properties()));
	public static final RegistryObject<Item> ORANGE_TROPICAL_SCALEBLOCK = BLOCKS.register("orange_tropical_scaleblock", () -> new BlockItem(LBBlocks.ORANGE_TROPICAL_SCALEBLOCK.get(), new Properties()));
	public static final RegistryObject<Item> RAINBOW_TROPICAL_SCALEBLOCK = BLOCKS.register("rainbow_tropical_scaleblock", () -> new BlockItem(LBBlocks.RAINBOW_TROPICAL_SCALEBLOCK.get(), new Properties()));
	
	public static final RegistryObject<Item> TEARTANG_BLOCK = BLOCKS.register("teartang_block", () -> new BlockItem(LBBlocks.TEARTANG_BLOCK.get(), new Properties()));
	
	public static final RegistryObject<Item> COINFROG_SPAWN = BLOCKS.register("coinfrog_spawn", () -> new PlaceOnWaterBlockItem(LBBlocks.COINFROG_SPAWN.get(), new Properties()));
	
	public static final RegistryObject<Item> HOLLOW_BATHYAL_SHELL = BLOCKS.register("hollow_bathyal_shell", () -> new BlockItem(LBBlocks.HOLLOW_BATHYAL_SHELL.get(), new Properties()));
	public static final RegistryObject<Item> HOLLOW_INTERTIDAL_SHELL = BLOCKS.register("hollow_intertidal_shell", () -> new BlockItem(LBBlocks.HOLLOW_INTERTIDAL_SHELL.get(), new Properties()));
	public static final RegistryObject<Item> HOLLOW_MIDNIGHT_SHELL = BLOCKS.register("hollow_midnight_shell", () -> new BlockItem(LBBlocks.HOLLOW_MIDNIGHT_SHELL.get(), new Properties()));
	public static final RegistryObject<Item> HOLLOW_SANDY_SHELL = BLOCKS.register("hollow_sandy_shell", () -> new BlockItem(LBBlocks.HOLLOW_SANDY_SHELL.get(), new Properties()));
	public static final RegistryObject<Item> HOLLOW_SPUME_SHELL = BLOCKS.register("hollow_spume_shell", () -> new BlockItem(LBBlocks.HOLLOW_SPUME_SHELL.get(), new Properties()));
	
	public static final RegistryObject<Item> MANEFISH_HIVE = BLOCKS.register("manefish_hive", () -> new BlockItem(LBBlocks.MANEFISH_HIVE.get(), new Properties()));
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
