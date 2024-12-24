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
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedBlockStateProvider;
import superlord.little_beasties.LittleBeasties;

public class LBConfiguredFeatures {
	@SuppressWarnings("unused")
	private static final List<ResourceLocation> BATHYAL = List.of(
			LittleBeasties.rl("bathyal_hollow_remain"),
			LittleBeasties.rl("big_bathyal_hollow_remain"),
			LittleBeasties.rl("small_bathyal_hollow_remain")
			);
	@SuppressWarnings("unused")
	private static final List<ResourceLocation> INTERTIDAL = List.of(
			LittleBeasties.rl("intertidal_hollow_remain"),
			LittleBeasties.rl("big_intertidal_hollow_remain"),
			LittleBeasties.rl("small_intertidal_hollow_remain")
			);
	@SuppressWarnings("unused")
	private static final List<ResourceLocation> MIDNIGHT = List.of(
			LittleBeasties.rl("midnight_hollow_remain"),
			LittleBeasties.rl("big_midnight_hollow_remain"),
			LittleBeasties.rl("small_midnight_hollow_remain")
			);
	@SuppressWarnings("unused")
	private static final List<ResourceLocation> SANDY = List.of(
			LittleBeasties.rl("sandy_hollow_remain"),
			LittleBeasties.rl("big_sandy_hollow_remain"),
			LittleBeasties.rl("small_sandy_hollow_remain")
			);
	@SuppressWarnings("unused")
	private static final List<ResourceLocation> SPUME = List.of(
			LittleBeasties.rl("spume_hollow_remain"),
			LittleBeasties.rl("big_spume_hollow_remain"),
			LittleBeasties.rl("small_spume_hollow_remain")
			);

	public static final ResourceKey<ConfiguredFeature<?, ?>> MARINE_CLAY_DISK = registerConfiguredFeature("configured_marine_clay_disk");
	public static final ResourceKey<ConfiguredFeature<?, ?>> MANEFISH_HIVE_TOWER = registerConfiguredFeature("configured_manefish_hive_tower");

	public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> bootstapContext) {
		FeatureUtils.register(bootstapContext, MARINE_CLAY_DISK, Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(LBBlocks.MARINE_CLAY.get()), BlockPredicate.matchesBlocks(List.of(Blocks.GRAVEL, Blocks.SAND)), UniformInt.of(1, 3), 1));
		FeatureUtils.register(bootstapContext, MANEFISH_HIVE_TOWER, LBFeatures.MANEFISH_HIVE_TOWER.get(), new NoneFeatureConfiguration());
	}

	public static ResourceKey<ConfiguredFeature<?, ?>> registerConfiguredFeature(String id) {
		return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(LittleBeasties.MOD_ID, id));
	}
}
