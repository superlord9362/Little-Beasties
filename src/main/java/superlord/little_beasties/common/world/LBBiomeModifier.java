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
					builder.getMobSpawnSettings().addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(LBEntities.BLUE_MANEFISH.get(), 10, 1, 3));
				}
			}
		}
	}
	
	@Override
	public Codec<? extends BiomeModifier> codec() {
		return LBBiomeModifiers.LB_BIOME_MODIFIER_TYPE.get();
	}

}
