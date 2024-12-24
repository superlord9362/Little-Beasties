package superlord.little_beasties.init;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GlazedTerracottaBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import superlord.little_beasties.LittleBeasties;
import superlord.little_beasties.common.block.CoinfrogSpawnBlock;
import superlord.little_beasties.common.block.ManefishHiveBlock;
import superlord.little_beasties.common.block.TearTangBlock;

public class LBBlocks {
	
	public static final DeferredRegister<Block> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, LittleBeasties.MOD_ID);
	
	public static final RegistryObject<Block> GOLDEN_TROPICAL_SCALEBLOCK = REGISTER.register("golden_tropical_scaleblock", () -> new Block(Properties.of().requiresCorrectToolForDrops().strength(1.5F, 4).sound(SoundType.NETHER_BRICKS)));
	public static final RegistryObject<Block> OPAL_TROPICAL_SCALEBLOCK = REGISTER.register("opal_tropical_scaleblock", () -> new Block(Properties.of().requiresCorrectToolForDrops().strength(1.5F, 4).sound(SoundType.NETHER_BRICKS)));
	public static final RegistryObject<Block> ORANGE_TROPICAL_SCALEBLOCK = REGISTER.register("orange_tropical_scaleblock", () -> new Block(Properties.of().requiresCorrectToolForDrops().strength(1.5F, 4).sound(SoundType.NETHER_BRICKS)));
	public static final RegistryObject<Block> RAINBOW_TROPICAL_SCALEBLOCK = REGISTER.register("rainbow_tropical_scaleblock", () -> new Block(Properties.of().requiresCorrectToolForDrops().strength(1.5F, 4).sound(SoundType.NETHER_BRICKS)));
	
	public static final RegistryObject<Block> HOLLOW_SANDY_SHELL = REGISTER.register("hollow_sandy_shell", () -> new RotatedPillarBlock(Properties.of().requiresCorrectToolForDrops().strength(1).sound(SoundType.BONE_BLOCK)));
	public static final RegistryObject<Block> HOLLOW_SPUME_SHELL = REGISTER.register("hollow_spume_shell", () -> new RotatedPillarBlock(Properties.of().requiresCorrectToolForDrops().strength(1).sound(SoundType.BONE_BLOCK)));
	public static final RegistryObject<Block> HOLLOW_INTERTIDAL_SHELL = REGISTER.register("hollow_intertidal_shell", () -> new RotatedPillarBlock(Properties.of().requiresCorrectToolForDrops().strength(1).sound(SoundType.BONE_BLOCK)));
	public static final RegistryObject<Block> HOLLOW_BATHYAL_SHELL = REGISTER.register("hollow_bathyal_shell", () -> new RotatedPillarBlock(Properties.of().requiresCorrectToolForDrops().strength(1).sound(SoundType.BONE_BLOCK)));
	public static final RegistryObject<Block> HOLLOW_MIDNIGHT_SHELL = REGISTER.register("hollow_midnight_shell", () -> new RotatedPillarBlock(Properties.of().requiresCorrectToolForDrops().strength(1).sound(SoundType.BONE_BLOCK)));
	
	public static final RegistryObject<Block> MARINE_CLAY = REGISTER.register("marine_clay", () -> new Block(Properties.of().mapColor(MapColor.COLOR_ORANGE).instrument(NoteBlockInstrument.FLUTE).strength(0.6F).sound(SoundType.GRAVEL)));
	
	public static final RegistryObject<Block> MARINE_TERRACOTTA = REGISTER.register("marine_terracotta", () -> new Block(Properties.of().mapColor(MapColor.COLOR_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)));
	public static final RegistryObject<Block> BLACK_MARINE_TERRACOTTA = REGISTER.register("black_marine_terracotta", () -> new Block(Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)));
	public static final RegistryObject<Block> BLUE_MARINE_TERRACOTTA = REGISTER.register("blue_marine_terracotta", () -> new Block(Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)));
	public static final RegistryObject<Block> BROWN_MARINE_TERRACOTTA = REGISTER.register("brown_marine_terracotta", () -> new Block(Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)));
	public static final RegistryObject<Block> CYAN_MARINE_TERRACOTTA = REGISTER.register("cyan_marine_terracotta", () -> new Block(Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)));
	public static final RegistryObject<Block> GRAY_MARINE_TERRACOTTA = REGISTER.register("gray_marine_terracotta", () -> new Block(Properties.of().mapColor(MapColor.TERRACOTTA_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)));
	public static final RegistryObject<Block> GREEN_MARINE_TERRACOTTA = REGISTER.register("green_marine_terracotta", () -> new Block(Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)));
	public static final RegistryObject<Block> LIGHT_BLUE_MARINE_TERRACOTTA = REGISTER.register("light_blue_marine_terracotta", () -> new Block(Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)));
	public static final RegistryObject<Block> LIGHT_GRAY_MARINE_TERRACOTTA = REGISTER.register("light_gray_marine_terracotta", () -> new Block(Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)));
	public static final RegistryObject<Block> LIME_MARINE_TERRACOTTA = REGISTER.register("lime_marine_terracotta", () -> new Block(Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)));
	public static final RegistryObject<Block> MAGENTA_MARINE_TERRACOTTA = REGISTER.register("magenta_marine_terracotta", () -> new Block(Properties.of().mapColor(MapColor.TERRACOTTA_MAGENTA).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)));
	public static final RegistryObject<Block> ORANGE_MARINE_TERRACOTTA = REGISTER.register("orange_marine_terracotta", () -> new Block(Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)));
	public static final RegistryObject<Block> PINK_MARINE_TERRACOTTA = REGISTER.register("pink_marine_terracotta", () -> new Block(Properties.of().mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)));
	public static final RegistryObject<Block> PURPLE_MARINE_TERRACOTTA = REGISTER.register("purple_marine_terracotta", () -> new Block(Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)));
	public static final RegistryObject<Block> RED_MARINE_TERRACOTTA = REGISTER.register("red_marine_terracotta", () -> new Block(Properties.of().mapColor(MapColor.TERRACOTTA_RED).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)));
	public static final RegistryObject<Block> WHITE_MARINE_TERRACOTTA = REGISTER.register("white_marine_terracotta", () -> new Block(Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)));
	public static final RegistryObject<Block> YELLOW_MARINE_TERRACOTTA = REGISTER.register("yellow_marine_terracotta", () -> new Block(Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)));
	
	public static final RegistryObject<Block> CHISELED_MARINE_TERRACOTTA = REGISTER.register("chiseled_marine_terracotta", () -> new RotatedPillarBlock(Properties.of().mapColor(MapColor.COLOR_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)));
	public static final RegistryObject<Block> CHISELED_BLACK_MARINE_TERRACOTTA = REGISTER.register("chiseled_black_marine_terracotta", () -> new RotatedPillarBlock(Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)));
	public static final RegistryObject<Block> CHISELED_BLUE_MARINE_TERRACOTTA = REGISTER.register("chiseled_blue_marine_terracotta", () -> new RotatedPillarBlock(Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)));
	public static final RegistryObject<Block> CHISELED_BROWN_MARINE_TERRACOTTA = REGISTER.register("chiseled_brown_marine_terracotta", () -> new RotatedPillarBlock(Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)));
	public static final RegistryObject<Block> CHISELED_CYAN_MARINE_TERRACOTTA = REGISTER.register("chiseled_cyan_marine_terracotta", () -> new RotatedPillarBlock(Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)));
	public static final RegistryObject<Block> CHISELED_GRAY_MARINE_TERRACOTTA = REGISTER.register("chiseled_gray_marine_terracotta", () -> new RotatedPillarBlock(Properties.of().mapColor(MapColor.TERRACOTTA_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)));
	public static final RegistryObject<Block> CHISELED_GREEN_MARINE_TERRACOTTA = REGISTER.register("chiseled_green_marine_terracotta", () -> new RotatedPillarBlock(Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)));
	public static final RegistryObject<Block> CHISELED_LIGHT_BLUE_MARINE_TERRACOTTA = REGISTER.register("chiseled_light_blue_marine_terracotta", () -> new RotatedPillarBlock(Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)));
	public static final RegistryObject<Block> CHISELED_LIGHT_GRAY_MARINE_TERRACOTTA = REGISTER.register("chiseled_light_gray_marine_terracotta", () -> new RotatedPillarBlock(Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)));
	public static final RegistryObject<Block> CHISELED_LIME_MARINE_TERRACOTTA = REGISTER.register("chiseled_lime_marine_terracotta", () -> new RotatedPillarBlock(Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)));
	public static final RegistryObject<Block> CHISELED_MAGENTA_MARINE_TERRACOTTA = REGISTER.register("chiseled_magenta_marine_terracotta", () -> new RotatedPillarBlock(Properties.of().mapColor(MapColor.TERRACOTTA_MAGENTA).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)));
	public static final RegistryObject<Block> CHISELED_ORANGE_MARINE_TERRACOTTA = REGISTER.register("chiseled_orange_marine_terracotta", () -> new RotatedPillarBlock(Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)));
	public static final RegistryObject<Block> CHISELED_PINK_MARINE_TERRACOTTA = REGISTER.register("chiseled_pink_marine_terracotta", () -> new RotatedPillarBlock(Properties.of().mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)));
	public static final RegistryObject<Block> CHISELED_PURPLE_MARINE_TERRACOTTA = REGISTER.register("chiseled_purple_marine_terracotta", () -> new RotatedPillarBlock(Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)));
	public static final RegistryObject<Block> CHISELED_RED_MARINE_TERRACOTTA = REGISTER.register("chiseled_red_marine_terracotta", () -> new RotatedPillarBlock(Properties.of().mapColor(MapColor.TERRACOTTA_RED).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)));
	public static final RegistryObject<Block> CHISELED_WHITE_MARINE_TERRACOTTA = REGISTER.register("chiseled_white_marine_terracotta", () -> new RotatedPillarBlock(Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)));
	public static final RegistryObject<Block> CHISELED_YELLOW_MARINE_TERRACOTTA = REGISTER.register("chiseled_yellow_marine_terracotta", () -> new RotatedPillarBlock(Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)));

	public static final RegistryObject<Block> MARINE_TERRACOTTA_PILLAR = REGISTER.register("marine_terracotta_pillar", () -> new RotatedPillarBlock(Properties.of().mapColor(MapColor.COLOR_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)));
	public static final RegistryObject<Block> BLACK_MARINE_TERRACOTTA_PILLAR = REGISTER.register("black_marine_terracotta_pillar", () -> new RotatedPillarBlock(Properties.of().mapColor(MapColor.TERRACOTTA_BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)));
	public static final RegistryObject<Block> BLUE_MARINE_TERRACOTTA_PILLAR = REGISTER.register("blue_marine_terracotta_pillar", () -> new RotatedPillarBlock(Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)));
	public static final RegistryObject<Block> BROWN_MARINE_TERRACOTTA_PILLAR = REGISTER.register("brown_marine_terracotta_pillar", () -> new RotatedPillarBlock(Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)));
	public static final RegistryObject<Block> CYAN_MARINE_TERRACOTTA_PILLAR = REGISTER.register("cyan_marine_terracotta_pillar", () -> new RotatedPillarBlock(Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)));
	public static final RegistryObject<Block> GRAY_MARINE_TERRACOTTA_PILLAR = REGISTER.register("gray_marine_terracotta_pillar", () -> new RotatedPillarBlock(Properties.of().mapColor(MapColor.TERRACOTTA_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)));
	public static final RegistryObject<Block> GREEN_MARINE_TERRACOTTA_PILLAR = REGISTER.register("green_marine_terracotta_pillar", () -> new RotatedPillarBlock(Properties.of().mapColor(MapColor.TERRACOTTA_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)));
	public static final RegistryObject<Block> LIGHT_BLUE_MARINE_TERRACOTTA_PILLAR = REGISTER.register("light_blue_marine_terracotta_pillar", () -> new RotatedPillarBlock(Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)));
	public static final RegistryObject<Block> LIGHT_GRAY_MARINE_TERRACOTTA_PILLAR = REGISTER.register("light_gray_marine_terracotta_pillar", () -> new RotatedPillarBlock(Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)));
	public static final RegistryObject<Block> LIME_MARINE_TERRACOTTA_PILLAR = REGISTER.register("lime_marine_terracotta_pillar", () -> new RotatedPillarBlock(Properties.of().mapColor(MapColor.TERRACOTTA_LIGHT_GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)));
	public static final RegistryObject<Block> MAGENTA_MARINE_TERRACOTTA_PILLAR = REGISTER.register("magenta_marine_terracotta_pillar", () -> new RotatedPillarBlock(Properties.of().mapColor(MapColor.TERRACOTTA_MAGENTA).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)));
	public static final RegistryObject<Block> ORANGE_MARINE_TERRACOTTA_PILLAR = REGISTER.register("orange_marine_terracotta_pillar", () -> new RotatedPillarBlock(Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)));
	public static final RegistryObject<Block> PINK_MARINE_TERRACOTTA_PILLAR = REGISTER.register("pink_marine_terracotta_pillar", () -> new RotatedPillarBlock(Properties.of().mapColor(MapColor.TERRACOTTA_PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)));
	public static final RegistryObject<Block> PURPLE_MARINE_TERRACOTTA_PILLAR = REGISTER.register("purple_marine_terracotta_pillar", () -> new RotatedPillarBlock(Properties.of().mapColor(MapColor.TERRACOTTA_PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)));
	public static final RegistryObject<Block> RED_MARINE_TERRACOTTA_PILLAR = REGISTER.register("red_marine_terracotta_pillar", () -> new RotatedPillarBlock(Properties.of().mapColor(MapColor.TERRACOTTA_RED).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)));
	public static final RegistryObject<Block> WHITE_MARINE_TERRACOTTA_PILLAR = REGISTER.register("white_marine_terracotta_pillar", () -> new RotatedPillarBlock(Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)));
	public static final RegistryObject<Block> YELLOW_MARINE_TERRACOTTA_PILLAR = REGISTER.register("yellow_marine_terracotta_pillar", () -> new RotatedPillarBlock(Properties.of().mapColor(MapColor.TERRACOTTA_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.25F, 4.2F)));
	
	public static final RegistryObject<Block> BLACK_GLAZED_MARINE_TERRACOTTA = REGISTER.register("black_glazed_marine_terracotta", () -> new GlazedTerracottaBlock(Properties.of().mapColor(DyeColor.BLACK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.4F).pushReaction(PushReaction.PUSH_ONLY)));
	public static final RegistryObject<Block> BLUE_GLAZED_MARINE_TERRACOTTA = REGISTER.register("blue_glazed_marine_terracotta", () -> new GlazedTerracottaBlock(Properties.of().mapColor(DyeColor.BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.4F).pushReaction(PushReaction.PUSH_ONLY)));
	public static final RegistryObject<Block> BROWN_GLAZED_MARINE_TERRACOTTA = REGISTER.register("brown_glazed_marine_terracotta", () -> new GlazedTerracottaBlock(Properties.of().mapColor(DyeColor.BROWN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.4F).pushReaction(PushReaction.PUSH_ONLY)));
	public static final RegistryObject<Block> CYAN_GLAZED_MARINE_TERRACOTTA = REGISTER.register("cyan_glazed_marine_terracotta", () -> new GlazedTerracottaBlock(Properties.of().mapColor(DyeColor.CYAN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.4F).pushReaction(PushReaction.PUSH_ONLY)));
	public static final RegistryObject<Block> GRAY_GLAZED_MARINE_TERRACOTTA = REGISTER.register("gray_glazed_marine_terracotta", () -> new GlazedTerracottaBlock(Properties.of().mapColor(DyeColor.GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.4F).pushReaction(PushReaction.PUSH_ONLY)));
	public static final RegistryObject<Block> GREEN_GLAZED_MARINE_TERRACOTTA = REGISTER.register("green_glazed_marine_terracotta", () -> new GlazedTerracottaBlock(Properties.of().mapColor(DyeColor.GREEN).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.4F).pushReaction(PushReaction.PUSH_ONLY)));
	public static final RegistryObject<Block> LIGHT_BLUE_GLAZED_MARINE_TERRACOTTA = REGISTER.register("light_blue_glazed_marine_terracotta", () -> new GlazedTerracottaBlock(Properties.of().mapColor(DyeColor.LIGHT_BLUE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.4F).pushReaction(PushReaction.PUSH_ONLY)));
	public static final RegistryObject<Block> LIGHT_GRAY_GLAZED_MARINE_TERRACOTTA = REGISTER.register("light_gray_glazed_marine_terracotta", () -> new GlazedTerracottaBlock(Properties.of().mapColor(DyeColor.LIGHT_GRAY).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.4F).pushReaction(PushReaction.PUSH_ONLY)));
	public static final RegistryObject<Block> LIME_GLAZED_MARINE_TERRACOTTA = REGISTER.register("lime_glazed_marine_terracotta", () -> new GlazedTerracottaBlock(Properties.of().mapColor(DyeColor.LIME).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.4F).pushReaction(PushReaction.PUSH_ONLY)));
	public static final RegistryObject<Block> MAGENTA_GLAZED_MARINE_TERRACOTTA = REGISTER.register("magenta_glazed_marine_terracotta", () -> new GlazedTerracottaBlock(Properties.of().mapColor(DyeColor.MAGENTA).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.4F).pushReaction(PushReaction.PUSH_ONLY)));
	public static final RegistryObject<Block> ORANGE_GLAZED_MARINE_TERRACOTTA = REGISTER.register("orange_glazed_marine_terracotta", () -> new GlazedTerracottaBlock(Properties.of().mapColor(DyeColor.ORANGE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.4F).pushReaction(PushReaction.PUSH_ONLY)));
	public static final RegistryObject<Block> PINK_GLAZED_MARINE_TERRACOTTA = REGISTER.register("pink_glazed_marine_terracotta", () -> new GlazedTerracottaBlock(Properties.of().mapColor(DyeColor.PINK).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.4F).pushReaction(PushReaction.PUSH_ONLY)));
	public static final RegistryObject<Block> PURPLE_GLAZED_MARINE_TERRACOTTA = REGISTER.register("purple_glazed_marine_terracotta", () -> new GlazedTerracottaBlock(Properties.of().mapColor(DyeColor.PURPLE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.4F).pushReaction(PushReaction.PUSH_ONLY)));
	public static final RegistryObject<Block> RED_GLAZED_MARINE_TERRACOTTA = REGISTER.register("red_glazed_marine_terracotta", () -> new GlazedTerracottaBlock(Properties.of().mapColor(DyeColor.RED).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.4F).pushReaction(PushReaction.PUSH_ONLY)));
	public static final RegistryObject<Block> WHITE_GLAZED_MARINE_TERRACOTTA = REGISTER.register("white_glazed_marine_terracotta", () -> new GlazedTerracottaBlock(Properties.of().mapColor(DyeColor.WHITE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.4F).pushReaction(PushReaction.PUSH_ONLY)));
	public static final RegistryObject<Block> YELLOW_GLAZED_MARINE_TERRACOTTA = REGISTER.register("yellow_glazed_marine_terracotta", () -> new GlazedTerracottaBlock(Properties.of().mapColor(DyeColor.YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.4F).pushReaction(PushReaction.PUSH_ONLY)));

	public static final RegistryObject<Block> MANEFISH_HIVE = REGISTER.register("manefish_hive", () -> new ManefishHiveBlock(Properties.of().mapColor(MapColor.SAND).requiresCorrectToolForDrops().strength(0.5F).pushReaction(PushReaction.DESTROY).instrument(NoteBlockInstrument.FLUTE)));
	
	public static final RegistryObject<Block> TEARTANG = REGISTER.register("teartang", () -> new TearTangBlock(Properties.of().instabreak().noCollission().sound(SoundType.SLIME_BLOCK).pushReaction(PushReaction.DESTROY)));

	public static final RegistryObject<Block> TEARTANG_BLOCK = REGISTER.register("teartang_block", () -> new Block(Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).sound(SoundType.WET_GRASS)));
	
	public static final RegistryObject<Block> COINFROG_SPAWN = REGISTER.register("coinfrog_spawn", () -> new CoinfrogSpawnBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WATER).instabreak().noOcclusion().noCollission().sound(SoundType.FROGSPAWN).pushReaction(PushReaction.DESTROY)));
	
}
