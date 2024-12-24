package superlord.little_beasties.init;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import superlord.little_beasties.LittleBeasties;
import superlord.little_beasties.common.world.feature.HollowRemainFeature;
import superlord.little_beasties.common.world.feature.ManefishHiveFeature;
import superlord.little_beasties.common.world.feature.config.StructureFeatureConfiguration;

public class LBFeatures {
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, LittleBeasties.MOD_ID);

	public static final RegistryObject<Feature<StructureFeatureConfiguration>> HOLLOW_REMAIN = FEATURES.register("hollow_remain", () -> new HollowRemainFeature(StructureFeatureConfiguration.CODEC));
	public static final RegistryObject<Feature<NoneFeatureConfiguration>> MANEFISH_HIVE_TOWER = FEATURES.register("manefish_hive_tower", () -> new ManefishHiveFeature(NoneFeatureConfiguration.CODEC));
	
}