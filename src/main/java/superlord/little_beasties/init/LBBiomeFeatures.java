package superlord.little_beasties.init;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jetbrains.annotations.NotNull;

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
	
	public static void bootstrap(BootstapContext<BiomeModifier> bootstapContext) {
		bootstapContext.register(ADD_MARINE_CLAY_DISKS, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(getBiome(bootstapContext, Biomes.DEEP_COLD_OCEAN, Biomes.DEEP_FROZEN_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN, Biomes.DEEP_OCEAN), getPlacedFeature(bootstapContext, LBPlacedFeatures.MARINE_CLAY_DISK), GenerationStep.Decoration.TOP_LAYER_MODIFICATION));
	}
	
	@SafeVarargs
	@NotNull
	private static HolderSet.Direct<Biome> getBiome(BootstapContext<BiomeModifier> bootstapContext, ResourceKey<Biome>... biome) {
		return HolderSet.direct(Stream.of(biome).map(resourceKey -> bootstapContext.lookup(Registries.BIOME).getOrThrow(resourceKey)).collect(Collectors.toList()));
	}
	
	@NotNull
	private static HolderSet.Direct<PlacedFeature> getPlacedFeature(BootstapContext<BiomeModifier> context, ResourceKey<PlacedFeature> placedFeature) {
		return HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(placedFeature));
	}
	
	@NotNull
	private static ResourceKey<BiomeModifier> register(String name) {
		return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(LittleBeasties.MOD_ID, name));
	}

}
