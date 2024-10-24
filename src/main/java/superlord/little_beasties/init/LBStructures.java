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
import superlord.little_beasties.common.structure.CollectorsHutAdventureStructure;
import superlord.little_beasties.common.structure.CollectorsHutBotanyStructure;
import superlord.little_beasties.common.structure.piece.CollectorsHutAdventurePieces.CollectorsHutAdventurePiece;
import superlord.little_beasties.common.structure.piece.CollectorsHutBotanyPieces.CollectorsHutBotanyPiece;

public class LBStructures {
	public static final ResourceKey<Structure> COLLECTORS_HUT_ADVENTURE = createKey("collectors_hut_adventure");
	public static final ResourceKey<Structure> COLLECTORS_HUT_BOTANY = createKey("collectors_hut_botany");

	public static void bootstrap(BootstapContext<Structure> bootstap) {
		HolderGetter<Biome> holdergetter = bootstap.lookup(Registries.BIOME);

		bootstap.register(COLLECTORS_HUT_ADVENTURE, new CollectorsHutAdventureStructure(structure(holdergetter.getOrThrow(LBTags.HAS_COLLECTOR_HUT), Map.of(MobCategory.WATER_CREATURE, new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.PIECE, WeightedRandomList.create(new MobSpawnSettings.SpawnerData(LBEntities.COLLECTOR.get(), 1, 1, 1)))), GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.NONE)));
		bootstap.register(COLLECTORS_HUT_BOTANY, new CollectorsHutBotanyStructure(structure(holdergetter.getOrThrow(LBTags.HAS_COLLECTOR_HUT), Map.of(MobCategory.WATER_CREATURE, new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.PIECE, WeightedRandomList.create(new MobSpawnSettings.SpawnerData(LBEntities.COLLECTOR.get(), 1, 1, 1)))), GenerationStep.Decoration.SURFACE_STRUCTURES, TerrainAdjustment.NONE)));
	}

	public static final ResourceKey<StructureSet> COLLECTOR_HUTS = registerStructureSet("collector_huts");


	static void bootstrapStructureSet(BootstapContext<StructureSet> bootstap) {
		HolderGetter<Structure> holdergetter = bootstap.lookup(Registries.STRUCTURE);
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
		DeferredRegister<StructureType<?>> REGISTRY = DeferredRegister.create(Registries.STRUCTURE_TYPE, LittleBeasties.MOD_ID);

		RegistryObject<StructureType<CollectorsHutAdventureStructure>> COLLECTORS_HUT_ADVENTURE = register("collectors_hut_adventure", CollectorsHutAdventureStructure.CODEC);
		RegistryObject<StructureType<CollectorsHutBotanyStructure>> COLLECTORS_HUT_BOTANY = register("collectors_hut_botany", CollectorsHutBotanyStructure.CODEC);

		private static <S extends Structure> RegistryObject<StructureType<S>> register(String string, Codec<S> codec) {
			return REGISTRY.register(string, () -> StructureType.register(string, codec));
		}
	}

	public interface LBStructurePieceType {
		DeferredRegister<StructurePieceType> REGISTRY = DeferredRegister.create(Registries.STRUCTURE_PIECE, LittleBeasties.MOD_ID);

		RegistryObject<StructurePieceType> COLLECTORS_HUT_ADVENTURE = register(CollectorsHutAdventurePiece::new, "collectorshutadventure");
		RegistryObject<StructurePieceType> COLLECTORS_HUT_BOTANY = register(CollectorsHutBotanyPiece::new, "collectorshutbotany");

		private static RegistryObject<StructurePieceType> register(StructurePieceType.StructureTemplateType type, String string) {
			return REGISTRY.register(string, () -> StructurePieceType.setTemplatePieceId(type, string.toLowerCase(Locale.ROOT)));
		}

	}

}
