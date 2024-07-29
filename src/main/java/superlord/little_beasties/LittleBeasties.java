package superlord.little_beasties;

import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import superlord.little_beasties.client.ClientProxy;
import superlord.little_beasties.common.CommonProxy;
import superlord.little_beasties.common.entity.BlueManefish;
import superlord.little_beasties.common.entity.Coinfrog;
import superlord.little_beasties.common.entity.CoinfrogTadpole;
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
import superlord.little_beasties.init.LBBiomeModifiers;
import superlord.little_beasties.init.LBBlocks;
import superlord.little_beasties.init.LBEntities;
import superlord.little_beasties.init.LBItems;
import superlord.little_beasties.init.LBTabs;

@Mod(LittleBeasties.MOD_ID)
public class LittleBeasties {

	public static final String MOD_ID = "little_beasties";
	@SuppressWarnings("deprecation")
	public static CommonProxy PROXY = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);

	public LittleBeasties() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		bus.addListener(this::commonSetup);
		bus.addListener(this::registerEntityAttributes);
		
		LBEntities.REGISTER.register(bus);
		LBBlocks.REGISTER.register(bus);
		LBItems.SPAWN_EGGS.register(bus);
		LBItems.REGISTER.register(bus);
		LBItems.BLOCKS.register(bus);
		LBTabs.REGISTER.register(bus);
		LBBiomeModifiers.BIOME_MODIFIER_SERIALIZERS.register(bus);
	}
	
	@SuppressWarnings("deprecation")
	private void commonSetup(FMLCommonSetupEvent event) {
		SpawnPlacements.register(LBEntities.TROPICAL_DARTFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
		SpawnPlacements.register(LBEntities.BLUE_MANEFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
		SpawnPlacements.register(LBEntities.SAILDRIFTER.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
		SpawnPlacements.register(LBEntities.TROPICAL_SEADRAGON.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
		SpawnPlacements.register(LBEntities.PROBOSCIS_FISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
		SpawnPlacements.register(LBEntities.WAVE_HORNGLIDER.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
		SpawnPlacements.register(LBEntities.SEA_LIGHT.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Sealight::checkSealightSpawnRules);
		SpawnPlacements.register(LBEntities.RAINWITCH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
		SpawnPlacements.register(LBEntities.COINFROG.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Coinfrog::checkSurfaceWaterAnimalSpawnRules);
		SpawnPlacements.register(LBEntities.COINFROG_TADPOLE.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
		SpawnPlacements.register(LBEntities.SNAPPY_WOOLBUG.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SnappyWoolbug::checkSnappyWoolbugSpawnRules);
		SpawnPlacements.register(LBEntities.MOHOMOOHO.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
		SpawnPlacements.register(LBEntities.DAYDREAM_RAY.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
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
	}
	
}
