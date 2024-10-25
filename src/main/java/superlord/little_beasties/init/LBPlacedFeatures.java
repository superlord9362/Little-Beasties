package superlord.little_beasties.init;

import java.util.List;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.placement.*;
import superlord.little_beasties.LittleBeasties;

public class LBPlacedFeatures {
	
	public static final ResourceKey<PlacedFeature> MARINE_CLAY_DISK = registerPlacedFeature("placed_marine_clay_disk");
	//public static final ResourceKey<PlacedFeature> BATHYAL_HOLLOW_REMAINS = registerPlacedFeature("placed_bathyal_hollow_remains");
	//public static final ResourceKey<PlacedFeature> INTERTIDAL_HOLLOW_REMAINS = registerPlacedFeature("placed_intertidal_hollow_remains");
	//public static final ResourceKey<PlacedFeature> MIDNIGHT_HOLLOW_REMAINS = registerPlacedFeature("placed_midnight_hollow_remains");
	//public static final ResourceKey<PlacedFeature> SANDY_HOLLOW_REMAINS = registerPlacedFeature("placed_sandy_hollow_remains");
	//public static final ResourceKey<PlacedFeature> SPUME_HOLLOW_REMAINS = registerPlacedFeature("placed_spume_hollow_remains");

	public static void bootstrap(BootstapContext<PlacedFeature> bootstapContext) {
		HolderGetter<ConfiguredFeature<?, ?>> configHolderGetter = bootstapContext.lookup(Registries.CONFIGURED_FEATURE);
		PlacementUtils.register(bootstapContext, MARINE_CLAY_DISK, configHolderGetter.getOrThrow(LBConfiguredFeatures.MARINE_CLAY_DISK), List.of(CountPlacement.of(1), BiomeFilter.biome()));
	}
	
	public static ResourceKey<PlacedFeature> registerPlacedFeature(String id) {
		return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(LittleBeasties.MOD_ID, id));
	}

}
