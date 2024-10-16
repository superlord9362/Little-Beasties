package superlord.little_beasties.init;

import java.util.List;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedBlockStateProvider;
import superlord.little_beasties.LittleBeasties;

public class LBConfiguredFeatures {
	
	public static final ResourceKey<ConfiguredFeature<?, ?>> MARINE_CLAY_DISK = registerConfiguredFeature("configured_marine_clay_disk");
	
	public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> bootstapContext) {
		FeatureUtils.register(bootstapContext, MARINE_CLAY_DISK, Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(LBBlocks.MARINE_CLAY.get()), BlockPredicate.matchesBlocks(List.of(Blocks.GRAVEL, Blocks.SAND)), UniformInt.of(1, 3), 1));
	}
	
	public static ResourceKey<ConfiguredFeature<?, ?>> registerConfiguredFeature(String id) {
		return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(LittleBeasties.MOD_ID, id));
	}

}
