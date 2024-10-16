package superlord.little_beasties.init;

import java.util.List;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountOnEveryLayerPlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import superlord.little_beasties.LittleBeasties;

@SuppressWarnings("deprecation")
public class LBPlacedFeatures {
	
	public static final ResourceKey<PlacedFeature> MARINE_CLAY_DISK = registerPlacedFeature("placed_marine_clay_disk");
	
	public static void bootstrap(BootstapContext<PlacedFeature> bootstapContext) {
		HolderGetter<ConfiguredFeature<?, ?>> holderGetter = bootstapContext.lookup(Registries.CONFIGURED_FEATURE);
		PlacementUtils.register(bootstapContext, MARINE_CLAY_DISK, holderGetter.getOrThrow(LBConfiguredFeatures.MARINE_CLAY_DISK), List.of(CountOnEveryLayerPlacement.of(1), BiomeFilter.biome()));
	}
	
	public static ResourceKey<PlacedFeature> registerPlacedFeature(String id) {
		return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(LittleBeasties.MOD_ID, id));
	}

}
