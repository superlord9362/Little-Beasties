package superlord.little_beasties.init;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;
import superlord.little_beasties.LittleBeasties;

public class LBDataGen extends DatapackBuiltinEntriesProvider {
	
	private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
			.add(Registries.CONFIGURED_FEATURE, LBConfiguredFeatures::bootstrap)
			.add(Registries.PLACED_FEATURE, LBPlacedFeatures::bootstrap)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, LBBiomeFeatures::bootstrap)
            .add(Registries.STRUCTURE, LBStructures::bootstrap)
            .add(Registries.STRUCTURE_SET, LBStructures::bootstrapStructureSet);
	
	public LBDataGen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
		super(output, registries, BUILDER, Set.of(LittleBeasties.MOD_ID));
	}
}
