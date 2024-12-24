package superlord.little_beasties.init;

import com.google.common.collect.ImmutableSet;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import superlord.little_beasties.LittleBeasties;

public class LBPoiTypes {
	
    public static final DeferredRegister<PoiType> POI_TYPES = DeferredRegister.create(Registries.POINT_OF_INTEREST_TYPE, LittleBeasties.MOD_ID);
	
    public static final RegistryObject<PoiType> MANEFISH_HIVE = POI_TYPES.register("manefish_hive", () -> new PoiType(ImmutableSet.copyOf(LBBlocks.MANEFISH_HIVE.get().getStateDefinition().getPossibleStates()), 0, 1));

}
