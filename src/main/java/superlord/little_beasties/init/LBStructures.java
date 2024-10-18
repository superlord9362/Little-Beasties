package superlord.little_beasties.init;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.mojang.serialization.Codec;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import superlord.little_beasties.LittleBeasties;
import superlord.little_beasties.common.structure.BathyalHollowRemainStructure;
import superlord.little_beasties.common.structure.BigBathyalHollowRemainStructure;
import superlord.little_beasties.common.structure.BigIntertidalHollowRemainStructure;
import superlord.little_beasties.common.structure.BigMidnightHollowRemainStructure;
import superlord.little_beasties.common.structure.BigSandyHollowRemainStructure;
import superlord.little_beasties.common.structure.BigSpumeHollowRemainStructure;
import superlord.little_beasties.common.structure.CollectorsHutAdventureStructure;
import superlord.little_beasties.common.structure.CollectorsHutBotanyStructure;
import superlord.little_beasties.common.structure.IntertidalHollowRemainStructure;
import superlord.little_beasties.common.structure.MidnightHollowRemainStructure;
import superlord.little_beasties.common.structure.SandyHollowRemainStructure;
import superlord.little_beasties.common.structure.SmallBathyalHollowRemainStructure;
import superlord.little_beasties.common.structure.SmallIntertidalHollowRemainStructure;
import superlord.little_beasties.common.structure.SmallMidnightHollowRemainStructure;
import superlord.little_beasties.common.structure.SmallSandyHollowRemainStructure;
import superlord.little_beasties.common.structure.SmallSpumeHollowRemainStructure;
import superlord.little_beasties.common.structure.SpumeHollowRemainStructure;
import superlord.little_beasties.common.structure.piece.BathyalHollowRemainPieces.BathyalHollowRemainPiece;
import superlord.little_beasties.common.structure.piece.BigBathyalHollowRemainPieces.BigBathyalHollowRemainPiece;
import superlord.little_beasties.common.structure.piece.BigIntertidalHollowRemainPieces.BigIntertidalHollowRemainPiece;
import superlord.little_beasties.common.structure.piece.BigMidnightHollowRemainPieces.BigMidnightHollowRemainPiece;
import superlord.little_beasties.common.structure.piece.BigSandyHollowRemainPieces;
import superlord.little_beasties.common.structure.piece.BigSpumeHollowRemainPieces.BigSpumeHollowRemainPiece;
import superlord.little_beasties.common.structure.piece.CollectorsHutAdventurePieces.CollectorsHutAdventurePiece;
import superlord.little_beasties.common.structure.piece.CollectorsHutBotanyPieces.CollectorsHutBotanyPiece;
import superlord.little_beasties.common.structure.piece.IntertidalHollowRemainPieces.IntertidalHollowRemainPiece;
import superlord.little_beasties.common.structure.piece.MidnightHollowRemainPieces.MidnightHollowRemainPiece;
import superlord.little_beasties.common.structure.piece.SandyHollowRemainPieces;
import superlord.little_beasties.common.structure.piece.SmallBathyalHollowRemainPieces.SmallBathyalHollowRemainPiece;
import superlord.little_beasties.common.structure.piece.SmallIntertidalHollowRemainPieces.SmallIntertidalHollowRemainPiece;
import superlord.little_beasties.common.structure.piece.SmallMidnightHollowRemainPieces.SmallMidnightHollowRemainPiece;
import superlord.little_beasties.common.structure.piece.SmallSandyHollowRemainPieces;
import superlord.little_beasties.common.structure.piece.SmallSpumeHollowRemainPieces.SmallSpumeHollowRemainPiece;
import superlord.little_beasties.common.structure.piece.SpumeHollowRemainPieces.SpumeHollowRemainPiece;

public class LBStructures {

	public static final ResourceKey<Structure> SANDY_HOLLOW_REMAIN = createKey("sandy_hollow_remain");
	public static final ResourceKey<Structure> BIG_SANDY_HOLLOW_REMAIN = createKey("big_sandy_hollow_remain");
	public static final ResourceKey<Structure> SMALL_SANDY_HOLLOW_REMAIN = createKey("small_sandy_hollow_remain");

	public static final ResourceKey<Structure> SPUME_HOLLOW_REMAIN = createKey("spume_hollow_remain");
	public static final ResourceKey<Structure> BIG_SPUME_HOLLOW_REMAIN = createKey("big_spume_hollow_remain");
	public static final ResourceKey<Structure> SMALL_SPUME_HOLLOW_REMAIN = createKey("small_spume_hollow_remain");

	public static final ResourceKey<Structure> INTERTIDAL_HOLLOW_REMAIN = createKey("intertidal_hollow_remain");
	public static final ResourceKey<Structure> BIG_INTERTIDAL_HOLLOW_REMAIN = createKey("big_intertidal_hollow_remain");
	public static final ResourceKey<Structure> SMALL_INTERTIDAL_HOLLOW_REMAIN = createKey("small_intertidal_hollow_remain");

	public static final ResourceKey<Structure> BATHYAL_HOLLOW_REMAIN = createKey("bathyal_hollow_remain");
	public static final ResourceKey<Structure> BIG_BATHYAL_HOLLOW_REMAIN = createKey("big_bathyal_hollow_remain");
	public static final ResourceKey<Structure> SMALL_BATHYAL_HOLLOW_REMAIN = createKey("small_bathyal_hollow_remain");

	public static final ResourceKey<Structure> MIDNIGHT_HOLLOW_REMAIN = createKey("midnight_hollow_remain");
	public static final ResourceKey<Structure> BIG_MIDNIGHT_HOLLOW_REMAIN = createKey("big_midnight_hollow_remain");
	public static final ResourceKey<Structure> SMALL_MIDNIGHT_HOLLOW_REMAIN = createKey("small_midnight_hollow_remain");

	public static final ResourceKey<Structure> COLLECTORS_HUT_ADVENTURE = createKey("collectors_hut_adventure");
	public static final ResourceKey<Structure> COLLECTORS_HUT_BOTANY = createKey("collectors_hut_botany");

	public static void bootstrap(BootstapContext<Structure> bootstap) {
		HolderGetter<Biome> holdergetter = bootstap.lookup(Registries.BIOME);
		bootstap.register(SANDY_HOLLOW_REMAIN, new SandyHollowRemainStructure(structure(holdergetter.getOrThrow(LBTags.HAS_SANDY_HOLLOW_REMAINS), TerrainAdjustment.NONE)));
		bootstap.register(BIG_SANDY_HOLLOW_REMAIN, new BigSandyHollowRemainStructure(structure(holdergetter.getOrThrow(LBTags.HAS_SANDY_HOLLOW_REMAINS), TerrainAdjustment.NONE)));
		bootstap.register(SMALL_SANDY_HOLLOW_REMAIN, new SmallSandyHollowRemainStructure(structure(holdergetter.getOrThrow(LBTags.HAS_SANDY_HOLLOW_REMAINS), TerrainAdjustment.NONE)));

		bootstap.register(SPUME_HOLLOW_REMAIN, new SpumeHollowRemainStructure(structure(holdergetter.getOrThrow(LBTags.HAS_SPUME_HOLLOW_REMAINS), TerrainAdjustment.NONE)));
		bootstap.register(BIG_SPUME_HOLLOW_REMAIN, new BigSpumeHollowRemainStructure(structure(holdergetter.getOrThrow(LBTags.HAS_SPUME_HOLLOW_REMAINS), TerrainAdjustment.NONE)));
		bootstap.register(SMALL_SPUME_HOLLOW_REMAIN, new SmallSpumeHollowRemainStructure(structure(holdergetter.getOrThrow(LBTags.HAS_SPUME_HOLLOW_REMAINS), TerrainAdjustment.NONE)));

		bootstap.register(INTERTIDAL_HOLLOW_REMAIN, new IntertidalHollowRemainStructure(structure(holdergetter.getOrThrow(LBTags.HAS_INTERTIDAL_HOLLOW_REMAINS), TerrainAdjustment.NONE)));
		bootstap.register(BIG_INTERTIDAL_HOLLOW_REMAIN, new BigIntertidalHollowRemainStructure(structure(holdergetter.getOrThrow(LBTags.HAS_INTERTIDAL_HOLLOW_REMAINS), TerrainAdjustment.NONE)));
		bootstap.register(SMALL_INTERTIDAL_HOLLOW_REMAIN, new SmallIntertidalHollowRemainStructure(structure(holdergetter.getOrThrow(LBTags.HAS_INTERTIDAL_HOLLOW_REMAINS), TerrainAdjustment.NONE)));

		bootstap.register(BATHYAL_HOLLOW_REMAIN, new BathyalHollowRemainStructure(structure(holdergetter.getOrThrow(LBTags.HAS_BATHYAL_HOLLOW_REMAINS), TerrainAdjustment.NONE)));
		bootstap.register(BIG_BATHYAL_HOLLOW_REMAIN, new BigBathyalHollowRemainStructure(structure(holdergetter.getOrThrow(LBTags.HAS_BATHYAL_HOLLOW_REMAINS), TerrainAdjustment.NONE)));
		bootstap.register(SMALL_BATHYAL_HOLLOW_REMAIN, new SmallBathyalHollowRemainStructure(structure(holdergetter.getOrThrow(LBTags.HAS_BATHYAL_HOLLOW_REMAINS), TerrainAdjustment.NONE)));

		bootstap.register(MIDNIGHT_HOLLOW_REMAIN, new MidnightHollowRemainStructure(structure(holdergetter.getOrThrow(LBTags.HAS_MIDNIGHT_HOLLOW_REMAINS), TerrainAdjustment.NONE)));
		bootstap.register(BIG_MIDNIGHT_HOLLOW_REMAIN, new BigMidnightHollowRemainStructure(structure(holdergetter.getOrThrow(LBTags.HAS_MIDNIGHT_HOLLOW_REMAINS), TerrainAdjustment.NONE)));
		bootstap.register(SMALL_MIDNIGHT_HOLLOW_REMAIN, new SmallMidnightHollowRemainStructure(structure(holdergetter.getOrThrow(LBTags.HAS_MIDNIGHT_HOLLOW_REMAINS), TerrainAdjustment.NONE)));

		bootstap.register(COLLECTORS_HUT_ADVENTURE, new CollectorsHutAdventureStructure(structure(holdergetter.getOrThrow(LBTags.HAS_COLLECTOR_HUT), Map.of(MobCategory.WATER_CREATURE, new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.PIECE, WeightedRandomList.create(new MobSpawnSettings.SpawnerData(LBEntities.COLLECTOR.get(), 1, 1, 1)))), GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.NONE)));
		bootstap.register(COLLECTORS_HUT_BOTANY, new CollectorsHutBotanyStructure(structure(holdergetter.getOrThrow(LBTags.HAS_COLLECTOR_HUT), Map.of(MobCategory.WATER_CREATURE, new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.PIECE, WeightedRandomList.create(new MobSpawnSettings.SpawnerData(LBEntities.COLLECTOR.get(), 1, 1, 1)))), GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.NONE)));
	}

	public static final ResourceKey<StructureSet> BATHYAL_HOLLOW_REMAINS = registerStructureSet("bathyal_hollow_remains");
	public static final ResourceKey<StructureSet> INTERTIDAL_HOLLOW_REMAINS = registerStructureSet("intertidal_hollow_remains");
	public static final ResourceKey<StructureSet> MIDNIGHT_HOLLOW_REMAINS = registerStructureSet("midnight_hollow_remains");
	public static final ResourceKey<StructureSet> SANDY_HOLLOW_REMAINS = registerStructureSet("sandy_hollow_remains");
	public static final ResourceKey<StructureSet> SPUME_HOLLOW_REMAINS = registerStructureSet("spume_hollow_remains");
	public static final ResourceKey<StructureSet> COLLECTOR_HUTS = registerStructureSet("collector_huts");


	static void bootstrapStructureSet(BootstapContext<StructureSet> bootstap) {
		HolderGetter<Structure> holdergetter = bootstap.lookup(Registries.STRUCTURE);
		bootstap.register(BATHYAL_HOLLOW_REMAINS, new StructureSet(List.of(StructureSet.entry(holdergetter.getOrThrow(BATHYAL_HOLLOW_REMAIN), 4), StructureSet.entry(holdergetter.getOrThrow(SMALL_BATHYAL_HOLLOW_REMAIN)), StructureSet.entry(holdergetter.getOrThrow(BIG_BATHYAL_HOLLOW_REMAIN), 4)), new RandomSpreadStructurePlacement(24, 4, RandomSpreadType.LINEAR, 3512234)));
		bootstap.register(INTERTIDAL_HOLLOW_REMAINS, new StructureSet(List.of(StructureSet.entry(holdergetter.getOrThrow(INTERTIDAL_HOLLOW_REMAIN), 4), StructureSet.entry(holdergetter.getOrThrow(SMALL_INTERTIDAL_HOLLOW_REMAIN)), StructureSet.entry(holdergetter.getOrThrow(BIG_INTERTIDAL_HOLLOW_REMAIN), 4)), new RandomSpreadStructurePlacement(24, 4, RandomSpreadType.LINEAR, 97230123)));
		bootstap.register(MIDNIGHT_HOLLOW_REMAINS, new StructureSet(List.of(StructureSet.entry(holdergetter.getOrThrow(MIDNIGHT_HOLLOW_REMAIN), 4), StructureSet.entry(holdergetter.getOrThrow(SMALL_MIDNIGHT_HOLLOW_REMAIN)), StructureSet.entry(holdergetter.getOrThrow(BIG_MIDNIGHT_HOLLOW_REMAIN), 4)), new RandomSpreadStructurePlacement(24, 4, RandomSpreadType.LINEAR, 5601233)));
		bootstap.register(SANDY_HOLLOW_REMAINS, new StructureSet(List.of(StructureSet.entry(holdergetter.getOrThrow(SANDY_HOLLOW_REMAIN), 4), StructureSet.entry(holdergetter.getOrThrow(SMALL_SANDY_HOLLOW_REMAIN)), StructureSet.entry(holdergetter.getOrThrow(BIG_SANDY_HOLLOW_REMAIN), 4)), new RandomSpreadStructurePlacement(24, 4, RandomSpreadType.LINEAR, 210543122)));
		bootstap.register(SPUME_HOLLOW_REMAINS, new StructureSet(List.of(StructureSet.entry(holdergetter.getOrThrow(SPUME_HOLLOW_REMAIN), 4), StructureSet.entry(holdergetter.getOrThrow(SMALL_SPUME_HOLLOW_REMAIN)), StructureSet.entry(holdergetter.getOrThrow(BIG_SPUME_HOLLOW_REMAIN), 4)), new RandomSpreadStructurePlacement(24, 4, RandomSpreadType.LINEAR, 59138342)));
		bootstap.register(COLLECTOR_HUTS, new StructureSet(List.of(StructureSet.entry(holdergetter.getOrThrow(COLLECTORS_HUT_ADVENTURE)), StructureSet.entry(holdergetter.getOrThrow(COLLECTORS_HUT_BOTANY))), new RandomSpreadStructurePlacement(24, 4, RandomSpreadType.LINEAR, 98123401)));
	}

	private static Structure.StructureSettings structure(HolderSet<Biome> p_256015_, Map<MobCategory, StructureSpawnOverride> p_256297_, GenerationStep.Decoration p_255729_, TerrainAdjustment p_255865_) {
		return new Structure.StructureSettings(p_256015_, p_256297_, p_255729_, p_255865_);
	}

	private static Structure.StructureSettings structure(HolderSet<Biome> p_256501_, TerrainAdjustment p_255704_) {
		return structure(p_256501_, Map.of(), GenerationStep.Decoration.SURFACE_STRUCTURES, p_255704_);
	}

	private static ResourceKey<Structure> createKey(String p_209873_) {
		return ResourceKey.create(Registries.STRUCTURE, new ResourceLocation(LittleBeasties.MOD_ID, p_209873_));
	}

	private static ResourceKey<StructureSet> registerStructureSet(String p_209839_) {
		return ResourceKey.create(Registries.STRUCTURE_SET, new ResourceLocation(LittleBeasties.MOD_ID, p_209839_));
	}

	public interface LBStructureType<S extends Structure> extends StructureType<S> {
		public static final DeferredRegister<StructureType<?>> REGISTRY = DeferredRegister.create(Registries.STRUCTURE_TYPE, LittleBeasties.MOD_ID);

		RegistryObject<StructureType<SandyHollowRemainStructure>> SANDY_HOLLOW_REMAINS = register("sandy_hollow_remain", SandyHollowRemainStructure.CODEC);
		RegistryObject<StructureType<SmallSandyHollowRemainStructure>> SMALL_SANDY_HOLLOW_REMAINS = register("small_sandy_hollow_remains", SmallSandyHollowRemainStructure.CODEC);
		RegistryObject<StructureType<BigSandyHollowRemainStructure>> BIG_SANDY_HOLLOW_REMAINS = register("big_sandy_hollow_remains", BigSandyHollowRemainStructure.CODEC);
		RegistryObject<StructureType<SpumeHollowRemainStructure>> SPUME_HOLLOW_REMAINS = register("spume_hollow_remains", SpumeHollowRemainStructure.CODEC);
		RegistryObject<StructureType<SmallSpumeHollowRemainStructure>> SMALL_SPUME_HOLLOW_REMAINS = register("small_spume_hollow_remains", SmallSpumeHollowRemainStructure.CODEC);
		RegistryObject<StructureType<BigSpumeHollowRemainStructure>> BIG_SPUME_HOLLOW_REMAINS = register("big_spume_hollow_remains", BigSpumeHollowRemainStructure.CODEC);
		RegistryObject<StructureType<BathyalHollowRemainStructure>> BATHYAL_HOLLOW_REMAINS = register("bathyal_hollow_remains", BathyalHollowRemainStructure.CODEC);
		RegistryObject<StructureType<SmallBathyalHollowRemainStructure>> SMALL_BATHYAL_HOLLOW_REMAINS = register("small_bathyal_hollow_remains", SmallBathyalHollowRemainStructure.CODEC);
		RegistryObject<StructureType<BigBathyalHollowRemainStructure>> BIG_BATHYAL_HOLLOW_REMAINS = register("big_bathyal_hollow_remains", BigBathyalHollowRemainStructure.CODEC);
		RegistryObject<StructureType<IntertidalHollowRemainStructure>> INTERTIDAL_HOLLOW_REMAINS = register("intertidal_hollow_remains", IntertidalHollowRemainStructure.CODEC);
		RegistryObject<StructureType<SmallIntertidalHollowRemainStructure>> SMALL_INTERTIDAL_HOLLOW_REMAINS = register("small_intertidal_hollow_remains", SmallIntertidalHollowRemainStructure.CODEC);
		RegistryObject<StructureType<BigIntertidalHollowRemainStructure>> BIG_INTERTIDAL_HOLLOW_REMAINS = register("big_intertidal_hollow_remains", BigIntertidalHollowRemainStructure.CODEC);
		RegistryObject<StructureType<MidnightHollowRemainStructure>> MIDNIGHT_HOLLOW_REMAINS = register("midnight_hollow_remains", MidnightHollowRemainStructure.CODEC);
		RegistryObject<StructureType<SmallMidnightHollowRemainStructure>> SMALL_MIDNIGHT_HOLLOW_REMAINS = register("small_midnight_hollow_remains", SmallMidnightHollowRemainStructure.CODEC);
		RegistryObject<StructureType<BigMidnightHollowRemainStructure>> BIG_MIDNIGHT_HOLLOW_REMAINS = register("big_midnight_hollow_remains", BigMidnightHollowRemainStructure.CODEC);
		RegistryObject<StructureType<CollectorsHutAdventureStructure>> COLLECTORS_HUT_ADVENTURE = register("collectors_hut_adventure", CollectorsHutAdventureStructure.CODEC);
		RegistryObject<StructureType<CollectorsHutBotanyStructure>> COLLECTORS_HUT_BOTANY = register("collectors_hut_botany", CollectorsHutBotanyStructure.CODEC);

		private static <S extends Structure> RegistryObject<StructureType<S>> register(String string, Codec<S> codec) {
			return REGISTRY.register(string, () -> StructureType.register(string, codec));
		}
	}

	public interface LBStructurePieceType {

		public static final DeferredRegister<StructurePieceType> REGISTRY = DeferredRegister.create(Registries.STRUCTURE_PIECE, LittleBeasties.MOD_ID);

		RegistryObject<StructurePieceType> SANDY_HOLLOW_REMAIN = register(SandyHollowRemainPieces.SandyHollowRemainPiece::new, "sandyhollowremain");
		RegistryObject<StructurePieceType> SMALL_SANDY_HOLLOW_REMAIN = register(SmallSandyHollowRemainPieces.SmallSandyHollowRemainPiece::new, "smallsandyhollowremain");
		RegistryObject<StructurePieceType> BIG_SANDY_HOLLOW_REMAIN = register(BigSandyHollowRemainPieces.BigSandyHollowRemainPiece::new, "bigsandyhollowremain");
		RegistryObject<StructurePieceType> SPUME_HOLLOW_REMAIN = register(SpumeHollowRemainPiece::new, "spumehollowremain");
		RegistryObject<StructurePieceType> SMALL_SPUME_HOLLOW_REMAIN = register(SmallSpumeHollowRemainPiece::new, "smallspumehollowremain");
		RegistryObject<StructurePieceType> BIG_SPUME_HOLLOW_REMAIN = register(BigSpumeHollowRemainPiece::new, "bigspumehollowremain");
		RegistryObject<StructurePieceType> BATHYAL_HOLLOW_REMAIN = register(BathyalHollowRemainPiece::new, "bathyalhollowremain");
		RegistryObject<StructurePieceType> SMALL_BATHYAL_HOLLOW_REMAIN = register(SmallBathyalHollowRemainPiece::new, "smallbathyalhollowremain");
		RegistryObject<StructurePieceType> BIG_BATHYAL_HOLLOW_REMAIN = register(BigBathyalHollowRemainPiece::new, "bigbathyalhollowremain");
		RegistryObject<StructurePieceType> INTERTIDAL_HOLLOW_REMAIN = register(IntertidalHollowRemainPiece::new, "intertidalhollowremain");
		RegistryObject<StructurePieceType> SMALL_INTERTIDAL_HOLLOW_REMAIN = register(SmallIntertidalHollowRemainPiece::new, "smallintertidalhollowremain");
		RegistryObject<StructurePieceType> BIG_INTERTIDAL_HOLLOW_REMAIN = register(BigIntertidalHollowRemainPiece::new, "bigintertidalhollowremain");
		RegistryObject<StructurePieceType> MIDNIGHT_HOLLOW_REMAIN = register(MidnightHollowRemainPiece::new, "midnighthollowremain");
		RegistryObject<StructurePieceType> SMALL_MIDNIGHT_HOLLOW_REMAIN = register(SmallMidnightHollowRemainPiece::new, "smallmidnighthollowremain");
		RegistryObject<StructurePieceType> BIG_MIDNIGHT_HOLLOW_REMAIN = register(BigMidnightHollowRemainPiece::new, "bigmidnighthollowremain");
		RegistryObject<StructurePieceType> COLLECTORS_HUT_ADVENTURE = register(CollectorsHutAdventurePiece::new, "collectorshutadventure");
		RegistryObject<StructurePieceType> COLLECTORS_HUT_BOTANY = register(CollectorsHutBotanyPiece::new, "collectorshutbotany");

		private static RegistryObject<StructurePieceType> register(StructurePieceType.StructureTemplateType type, String string) {
			return REGISTRY.register(string, () -> StructurePieceType.setTemplatePieceId(type, string.toLowerCase(Locale.ROOT)));
		}

	}

}
