package superlord.little_beasties;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import superlord.little_beasties.common.entity.BlueManefish;
import superlord.little_beasties.common.entity.Coinfrog;
import superlord.little_beasties.common.entity.CoinfrogTadpole;
import superlord.little_beasties.common.entity.Collector;
import superlord.little_beasties.common.entity.DaydreamRay;
import superlord.little_beasties.common.entity.Mohomooho;
import superlord.little_beasties.common.entity.ProboscisFish;
import superlord.little_beasties.common.entity.Rainwitch;
import superlord.little_beasties.common.entity.Saildrifter;
import superlord.little_beasties.common.entity.Sealight;
import superlord.little_beasties.common.entity.SnappyWoolbug;
import superlord.little_beasties.common.entity.TropicalDartfish;
import superlord.little_beasties.common.entity.TropicalSeadragon;
import superlord.little_beasties.common.entity.WaveHornglider;
import superlord.little_beasties.init.*;
import superlord.little_beasties.init.LBStructures.LBStructurePieceType;
import superlord.little_beasties.init.LBStructures.LBStructureType;

@Mod(LittleBeasties.MOD_ID)
public class LittleBeasties {
	public static final String MOD_ID = "little_beasties";

	public LittleBeasties() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		bus.addListener(this::registerSpawnPlacements);
		bus.addListener(this::registerEntityAttributes);
		
		LBEntities.REGISTER.register(bus);
		LBBlocks.REGISTER.register(bus);
		LBItems.SPAWN_EGGS.register(bus);
		LBItems.REGISTER.register(bus);
		LBItems.BLOCKS.register(bus);
		LBTabs.REGISTER.register(bus);
		LBBiomeModifiers.BIOME_MODIFIER_SERIALIZERS.register(bus);
		LBStructureType.REGISTRY.register(bus);
		LBStructurePieceType.REGISTRY.register(bus);
		LBFeatures.FEATURES.register(bus);
		bus.addListener(this::gatherData);
	}

	public static ResourceLocation rl(String name) {
		return new ResourceLocation(MOD_ID, name);
	}

	public void gatherData(GatherDataEvent event) {
		DataGenerator dataGenerator = event.getGenerator();
		PackOutput packOutput = dataGenerator.getPackOutput();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
		boolean server = event.includeServer();
		dataGenerator.addProvider(server, new LBDataGen(packOutput, lookupProvider));
	}
	
	private void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
		event.register(LBEntities.TROPICAL_DARTFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
		event.register(LBEntities.BLUE_MANEFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
		event.register(LBEntities.SAILDRIFTER.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
		event.register(LBEntities.TROPICAL_SEADRAGON.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
		event.register(LBEntities.PROBOSCIS_FISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
		event.register(LBEntities.WAVE_HORNGLIDER.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
		event.register(LBEntities.SEA_LIGHT.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Sealight::checkSealightSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
		event.register(LBEntities.RAINWITCH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
		event.register(LBEntities.COINFROG.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Coinfrog::checkSurfaceWaterAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
		event.register(LBEntities.COINFROG_TADPOLE.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
		event.register(LBEntities.SNAPPY_WOOLBUG.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SnappyWoolbug::checkSnappyWoolbugSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
		event.register(LBEntities.MOHOMOOHO.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
		event.register(LBEntities.DAYDREAM_RAY.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
		event.register(LBEntities.COLLECTOR.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.OR);
	}
	
	private void registerEntityAttributes(EntityAttributeCreationEvent event) {
		event.put(LBEntities.TROPICAL_DARTFISH.get(), TropicalDartfish.createAttributes().build());
		event.put(LBEntities.BLUE_MANEFISH.get(), BlueManefish.createAttributes().build());
		event.put(LBEntities.SAILDRIFTER.get(), Saildrifter.createAttributes().build());
		event.put(LBEntities.TROPICAL_SEADRAGON.get(), TropicalSeadragon.createAttributes().build());
		event.put(LBEntities.PROBOSCIS_FISH.get(), ProboscisFish.createAttributes().build());
		event.put(LBEntities.WAVE_HORNGLIDER.get(), WaveHornglider.createAttributes().build());
		event.put(LBEntities.SEA_LIGHT.get(), Sealight.createAttributes().build());
		event.put(LBEntities.SNAPPY_WOOLBUG.get(), SnappyWoolbug.createAttributes().build());
		event.put(LBEntities.RAINWITCH.get(), Rainwitch.createAttributes().build());
		event.put(LBEntities.COINFROG.get(), Coinfrog.createAttributes().build());
		event.put(LBEntities.COINFROG_TADPOLE.get(), CoinfrogTadpole.createAttributes().build());
		event.put(LBEntities.MOHOMOOHO.get(), Mohomooho.createAttributes().build());
		event.put(LBEntities.DAYDREAM_RAY.get(), DaydreamRay.createAttributes().build());
		event.put(LBEntities.COLLECTOR.get(), Collector.createAttributes().build());
	}
	
}
