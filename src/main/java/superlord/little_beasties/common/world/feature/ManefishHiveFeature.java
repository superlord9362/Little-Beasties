package superlord.little_beasties.common.world.feature;

import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import superlord.little_beasties.common.entity.block.ManefishHiveBlockEntity;
import superlord.little_beasties.init.LBBlocks;
import superlord.little_beasties.init.LBEntities;

public class ManefishHiveFeature extends Feature<NoneFeatureConfiguration> {

	public ManefishHiveFeature(Codec<NoneFeatureConfiguration> p_65786_) {
		super(p_65786_);
	}
	
	public boolean isSoilBlock(BlockPos pos, WorldGenLevel level) {
		return level.getBlockState(pos).is(Blocks.SAND) || level.getBlockState(pos).is(Blocks.RED_SAND) || level.getBlockState(pos).is(Blocks.GRAVEL) || level.getBlockState(pos).is(Blocks.DIRT) || level.getBlockState(pos).is(Blocks.COARSE_DIRT) || level.getBlockState(pos).is(Blocks.GRASS_BLOCK) || level.getBlockState(pos).is(Blocks.PODZOL) || level.getBlockState(pos).is(Blocks.MYCELIUM);
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
		BlockPos origin = context.origin();
		RandomSource random = context.random();
		WorldGenLevel level = context.level();
		boolean flag = false;
		if (random.nextInt(45) == 0) {
			int y = origin.getY();
			for (int i = y; i < 320; i++) {
				BlockState state = level.getBlockState(new BlockPos(origin.getX(), i, origin.getZ()));
				if (state.is(Blocks.WATER) && this.isSoilBlock(new BlockPos(origin.getX(), i - 1, origin.getZ()), level)) {
					int i1 = random.nextInt(4) + 1;
					for (int j = 0; j <= i1; j++) {
						BlockPos pos = new BlockPos(origin.getX(), i + j, origin.getZ());
						level.setBlock(pos, LBBlocks.MANEFISH_HIVE.get().defaultBlockState(), 2);
						BlockEntity entity = level.getBlockEntity(pos);
						if (entity instanceof ManefishHiveBlockEntity hive) {
							int k = 1 + random.nextInt(5);
							for (int l = 0; l < k; l++) {
								CompoundTag compoundtag = new CompoundTag();
								compoundtag.putString("id", BuiltInRegistries.ENTITY_TYPE.getKey(LBEntities.BLUE_MANEFISH.get()).toString());
								hive.storeManefish(compoundtag, random.nextInt(599));
							}
						}
						if (j == i1)  {
							flag = true;
							break;
						}
					}
					if (flag) break;
				}
				if (flag) break;
			}
			return true;
		}
		return false;
	}

}
