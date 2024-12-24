package superlord.little_beasties.init;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import superlord.little_beasties.LittleBeasties;
import superlord.little_beasties.common.entity.block.ManefishHiveBlockEntity;

public class LBBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, LittleBeasties.MOD_ID);

	public static final RegistryObject<BlockEntityType<ManefishHiveBlockEntity>> MANEFISH_HIVE = REGISTER.register("manefish_hive", () -> BlockEntityType.Builder.of(ManefishHiveBlockEntity::new, LBBlocks.MANEFISH_HIVE.get()).build(null));

	
}
