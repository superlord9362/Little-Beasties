package superlord.little_beasties.common.structure.piece;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePieceAccessor;
import net.minecraft.world.level.levelgen.structure.TemplateStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import superlord.little_beasties.LittleBeasties;
import superlord.little_beasties.common.entity.Collector;
import superlord.little_beasties.init.LBEntities;
import superlord.little_beasties.init.LBStructures.LBStructurePieceType;

public class CollectorsHutAdventurePieces {
	static final BlockPos PIVOT = new BlockPos(0, 0, 0);
	private static final ResourceLocation LOCATION = new ResourceLocation(LittleBeasties.MOD_ID, "collectors_hut_adventure");

	public static void addPieces(StructureTemplateManager p_229346_, BlockPos p_229347_, Rotation p_229348_, StructurePieceAccessor p_229349_, RandomSource p_229350_) {
		p_229349_.addPiece(new CollectorsHutAdventurePieces.CollectorsHutAdventurePiece(p_229346_, LOCATION, p_229347_, p_229348_));
	}

	public static class CollectorsHutAdventurePiece extends TemplateStructurePiece {
		private boolean spawnedCollector;
		public CollectorsHutAdventurePiece(StructureTemplateManager p_229354_, ResourceLocation p_229355_, BlockPos p_229356_, Rotation p_229357_) {
			super(LBStructurePieceType.COLLECTORS_HUT_ADVENTURE.get(), 0, p_229354_, p_229355_, p_229355_.toString(), makeSettings(p_229357_), p_229356_);
		}

		public CollectorsHutAdventurePiece(StructureTemplateManager p_229360_, CompoundTag p_229361_) {
			super(LBStructurePieceType.COLLECTORS_HUT_ADVENTURE.get(), p_229361_, p_229360_, (p_229383_) -> {
				return makeSettings(Rotation.valueOf(p_229361_.getString("Rot")));
			});
			this.spawnedCollector = p_229361_.getBoolean("Collector");
		}

		protected void addAdditionalSaveData(StructurePieceSerializationContext p_229373_, CompoundTag p_229374_) {
			super.addAdditionalSaveData(p_229373_, p_229374_);
			p_229374_.putString("Rot", this.placeSettings.getRotation().name());
			p_229374_.putBoolean("Collector", this.spawnedCollector);
		}

		private static StructurePlaceSettings makeSettings(Rotation p_229371_) {
			return (new StructurePlaceSettings()).setRotation(p_229371_).setMirror(Mirror.NONE).setRotationPivot(CollectorsHutAdventurePieces.PIVOT).addProcessor(BlockIgnoreProcessor.STRUCTURE_AND_AIR);
		}

		@SuppressWarnings("deprecation")
		public void postProcess(WorldGenLevel p_229363_, StructureManager p_229364_, ChunkGenerator p_229365_, RandomSource p_229366_, BoundingBox p_229367_, ChunkPos p_229368_, BlockPos p_229369_) {
			int i = p_229363_.getMaxBuildHeight();
			int j = 0;
			Vec3i vec3i = this.template.getSize();
			int k = vec3i.getX() * vec3i.getZ();
			if (k == 0) {
				j = p_229363_.getHeight(Heightmap.Types.OCEAN_FLOOR_WG, this.templatePosition.getX(), this.templatePosition.getZ());
			} else {
				BlockPos blockpos = this.templatePosition.offset(vec3i.getX() - 1, 0, vec3i.getZ() - 1);

				for(BlockPos blockpos1 : BlockPos.betweenClosed(this.templatePosition, blockpos)) {
					int l = p_229363_.getHeight(Heightmap.Types.OCEAN_FLOOR_WG, blockpos1.getX(), blockpos1.getZ());
					j += l;
					i = Math.min(i, l);
				}

				j /= k;
			}

			this.templatePosition = new BlockPos(this.templatePosition.getX(), j, this.templatePosition.getZ());

			if (!this.spawnedCollector) {
				BlockPos blockpos = this.getWorldPos(2, 2, 5);
				if (p_229367_.isInside(blockpos)) {
					this.spawnedCollector = true;
					Collector collector = LBEntities.COLLECTOR.get().create(p_229363_.getLevel());
					if (collector != null) {
						collector.moveTo((double)blockpos.getX() + 0.5D, (double)blockpos.getY(), (double)blockpos.getZ() + 0.5D, 0.0F, 0.0F);
						collector.finalizeSpawn(p_229363_, p_229363_.getCurrentDifficultyAt(blockpos), MobSpawnType.STRUCTURE, (SpawnGroupData)null, (CompoundTag)null);
						p_229363_.addFreshEntityWithPassengers(collector);
					}
				}
			}
			super.postProcess(p_229363_, p_229364_, p_229365_, p_229366_, p_229367_, p_229368_, p_229369_);
		}

		@Override
		protected void handleDataMarker(String p_226906_, BlockPos p_226907_, ServerLevelAccessor p_226908_, RandomSource p_226909_, BoundingBox p_226910_) {

		}
	}
}
