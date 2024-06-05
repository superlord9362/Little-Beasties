package superlord.little_beasties;

import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import superlord.little_beasties.common.entity.BlueManefish;
import superlord.little_beasties.common.entity.Saildrifter;
import superlord.little_beasties.common.entity.TropicalDartfish;
import superlord.little_beasties.init.LBBiomeModifiers;
import superlord.little_beasties.init.LBEntities;
import superlord.little_beasties.init.LBItems;
import superlord.little_beasties.init.LBTabs;

@Mod(LittleBeasties.MOD_ID)
public class LittleBeasties {

	public static final String MOD_ID = "little_beasties";
	
	public LittleBeasties() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		bus.addListener(this::commonSetup);
		bus.addListener(this::registerEntityAttributes);
		
		LBEntities.REGISTER.register(bus);
		LBItems.SPAWN_EGGS.register(bus);
		LBItems.REGISTER.register(bus);
		LBTabs.REGISTER.register(bus);
		LBBiomeModifiers.BIOME_MODIFIER_SERIALIZERS.register(bus);
	}
	
	@SuppressWarnings("deprecation")
	private void commonSetup(FMLCommonSetupEvent event) {
		SpawnPlacements.register(LBEntities.TROPICAL_DARTFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
		SpawnPlacements.register(LBEntities.BLUE_MANEFISH.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
		SpawnPlacements.register(LBEntities.SAILDRIFTER.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkSurfaceWaterAnimalSpawnRules);
	}
	
	private void registerEntityAttributes(EntityAttributeCreationEvent event) {
		event.put(LBEntities.TROPICAL_DARTFISH.get(), TropicalDartfish.createAttributes().build());
		event.put(LBEntities.BLUE_MANEFISH.get(), BlueManefish.createAttributes().build());
		event.put(LBEntities.SAILDRIFTER.get(), Saildrifter.createAttributes().build());
	}
	
}
