package superlord.little_beasties.init;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;
import superlord.little_beasties.LittleBeasties;

public class LBBiomeFeatures {
	
	public static final ResourceKey<BiomeModifier> ADD_MARINE_CLAY_DISKS = register("add_marine_clay_disks");
	public static final ResourceKey<BiomeModifier> ADD_BATHYAL_HOLLOW_REMAINS = register("add_bathyal_hollow_remains");
	public static final ResourceKey<BiomeModifier> ADD_INTERTIDAL_HOLLOW_REMAINS = register("add_intertidal_hollow_remains");
	public static final ResourceKey<BiomeModifier> ADD_MIDNIGHT_HOLLOW_REMAINS = register("add_midnight_hollow_remains");
	public static final ResourceKey<BiomeModifier> ADD_SANDY_HOLLOW_REMAINS = register("add_sandy_hollow_remains");
	public static final ResourceKey<BiomeModifier> ADD_SPUME_HOLLOW_REMAINS = register("add_spume_hollow_remains");

	public static void bootstrap(BootstapContext<BiomeModifier> bootstapContext) {
		bootstapContext.register(ADD_MARINE_CLAY_DISKS, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(getBiome(bootstapContext, Biomes.DEEP_COLD_OCEAN, Biomes.DEEP_FROZEN_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN, Biomes.DEEP_OCEAN), getPlacedFeature(bootstapContext, LBPlacedFeatures.MARINE_CLAY_DISK), GenerationStep.Decoration.TOP_LAYER_MODIFICATION));
		// todo - make these use the tags. how the hell do holdersets work
		//bootstapContext.register(ADD_BATHYAL_HOLLOW_REMAINS, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(getBiome(bootstapContext, Biomes.DEEP_OCEAN, Biomes.LUKEWARM_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN, Biomes.WARM_OCEAN), getPlacedFeature(bootstapContext, LBPlacedFeatures.BATHYAL_HOLLOW_REMAINS), GenerationStep.Decoration.SURFACE_STRUCTURES));
		//bootstapContext.register(ADD_INTERTIDAL_HOLLOW_REMAINS, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(getBiome(bootstapContext, Biomes.DEEP_LUKEWARM_OCEAN, Biomes.WARM_OCEAN), getPlacedFeature(bootstapContext, LBPlacedFeatures.INTERTIDAL_HOLLOW_REMAINS), GenerationStep.Decoration.SURFACE_STRUCTURES));
		//bootstapContext.register(ADD_MIDNIGHT_HOLLOW_REMAINS, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(getBiome(bootstapContext, Biomes.DEEP_OCEAN), getPlacedFeature(bootstapContext, LBPlacedFeatures.MIDNIGHT_HOLLOW_REMAINS), GenerationStep.Decoration.SURFACE_STRUCTURES));
		//bootstapContext.register(ADD_SANDY_HOLLOW_REMAINS, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(getBiome(bootstapContext, Biomes.LUKEWARM_OCEAN, Biomes.WARM_OCEAN), getPlacedFeature(bootstapContext, LBPlacedFeatures.SANDY_HOLLOW_REMAINS), GenerationStep.Decoration.SURFACE_STRUCTURES));
		//bootstapContext.register(ADD_SPUME_HOLLOW_REMAINS, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(getBiome(bootstapContext, Biomes.LUKEWARM_OCEAN, Biomes.WARM_OCEAN), getPlacedFeature(bootstapContext, LBPlacedFeatures.SPUME_HOLLOW_REMAINS), GenerationStep.Decoration.SURFACE_STRUCTURES));
	}
	
	@SafeVarargs
	private static HolderSet.Direct<Biome> getBiome(BootstapContext<BiomeModifier> bootstapContext, ResourceKey<Biome>... biome) {
		return HolderSet.direct(Stream.of(biome).map(resourceKey -> bootstapContext.lookup(Registries.BIOME).getOrThrow(resourceKey)).collect(Collectors.toList()));
	}
	
	private static HolderSet.Direct<PlacedFeature> getPlacedFeature(BootstapContext<BiomeModifier> context, ResourceKey<PlacedFeature> placedFeature) {
		return HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(placedFeature));
	}
	
	private static ResourceKey<BiomeModifier> register(String name) {
		return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(LittleBeasties.MOD_ID, name));
	}

}
