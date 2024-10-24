package superlord.little_beasties.common.world.structure;

import java.util.Optional;

import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import superlord.little_beasties.common.world.structure.piece.CollectorsHutAdventurePieces;
import superlord.little_beasties.init.LBStructures.LBStructureType;

public class CollectorsHutAdventureStructure extends Structure {

	public static final Codec<CollectorsHutAdventureStructure> CODEC = simpleCodec(CollectorsHutAdventureStructure::new);

	public CollectorsHutAdventureStructure(Structure.StructureSettings p_229388_) {
		super(p_229388_);
	}

	public Optional<Structure.GenerationStub> findGenerationPoint(Structure.GenerationContext p_229391_) {
		return onTopOfChunkCenter(p_229391_, Types.OCEAN_FLOOR_WG, (p_229394_) -> {
			this.generatePieces(p_229394_, p_229391_);
		});
	}

	private void generatePieces(StructurePiecesBuilder p_229396_, Structure.GenerationContext p_229397_) {
		Rotation rotation = Rotation.getRandom(p_229397_.random());
		BlockPos blockpos = new BlockPos(p_229397_.chunkPos().getMinBlockX(), 90, p_229397_.chunkPos().getMinBlockZ());
		CollectorsHutAdventurePieces.addPieces(p_229397_.structureTemplateManager(), blockpos, rotation, p_229396_, p_229397_.random());
	}

	public StructureType<?> type() {
		return LBStructureType.COLLECTORS_HUT_ADVENTURE.get();
	}

}