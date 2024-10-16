package superlord.little_beasties.common.world;

import com.mojang.serialization.Codec;

import net.minecraft.core.Holder;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;
import superlord.little_beasties.init.LBBiomeModifiers;
import superlord.little_beasties.init.LBEntities;

public class LBBiomeModifier implements BiomeModifier {
	
	public static final LBBiomeModifier INSTANCE = new LBBiomeModifier();
	
	@Override
	public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
		if (phase == Phase.ADD) {
			if (biome.is(BiomeTags.IS_OVERWORLD)) {
				if (biome.is(Biomes.LUKEWARM_OCEAN)) {
					builder.getMobSpawnSettings().addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(LBEntities.TROPICAL_DARTFISH.get(), 12, 5, 10));
					builder.getMobSpawnSettings().addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(LBEntities.SAILDRIFTER.get(), 12, 5, 10));
				}
				if (biome.is(Biomes.WARM_OCEAN)) {
					builder.getMobSpawnSettings().addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(LBEntities.BLUE_MANEFISH.get(), 14, 1, 3));
					builder.getMobSpawnSettings().addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(LBEntities.TROPICAL_SEADRAGON.get(), 5, 1, 2));
					builder.getMobSpawnSettings().addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(LBEntities.PROBOSCIS_FISH.get(), 12, 3, 6));
					builder.getMobSpawnSettings().addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(LBEntities.WAVE_HORNGLIDER.get(), 2, 1, 2));
				}
				if (biome.is(BiomeTags.IS_OVERWORLD) && !biome.is(Biomes.OCEAN) && !biome.is(Biomes.COLD_OCEAN) && !biome.is(Biomes.FROZEN_OCEAN) && !biome.is(Biomes.DEEP_FROZEN_OCEAN) && !biome.is(Biomes.DEEP_LUKEWARM_OCEAN) && !biome.is(Biomes.DEEP_OCEAN) && !biome.is(Biomes.LUKEWARM_OCEAN) && !biome.is(Biomes.WARM_OCEAN)) {
					builder.getMobSpawnSettings().addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(LBEntities.SEA_LIGHT.get(), 6, 1, 4));
				}
				if (biome.is(BiomeTags.IS_TAIGA)) {
					builder.getMobSpawnSettings().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(LBEntities.SNAPPY_WOOLBUG.get(), 9, 1, 2));
				}
				if (biome.is(Biomes.SWAMP)) {
					builder.getMobSpawnSettings().addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(LBEntities.RAINWITCH.get(), 12, 2, 5));
					builder.getMobSpawnSettings().addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(LBEntities.MOHOMOOHO.get(), 4, 3, 6));
				}
				if (biome.is(Biomes.MANGROVE_SWAMP)) {
					builder.getMobSpawnSettings().addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(LBEntities.RAINWITCH.get(), 12, 2, 5));
				}
				if (biome.is(BiomeTags.IS_JUNGLE)) {
					builder.getMobSpawnSettings().addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(LBEntities.COINFROG.get(), 6, 1, 3));
				}
				if (biome.is(Biomes.DEEP_OCEAN)) {
					builder.getMobSpawnSettings().addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(LBEntities.DAYDREAM_RAY.get(), 2, 1, 1));
				}
				if (biome.is(Biomes.RIVER)) {
					builder.getMobSpawnSettings().addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(LBEntities.MOHOMOOHO.get(), 7, 3, 6));
				}
			}
		}
	}
	
	@Override
	public Codec<? extends BiomeModifier> codec() {
		return LBBiomeModifiers.LB_BIOME_MODIFIER_TYPE.get();
	}

}
