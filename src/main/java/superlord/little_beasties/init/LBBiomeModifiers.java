package superlord.little_beasties.init;

import com.mojang.serialization.Codec;

import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import superlord.little_beasties.LittleBeasties;
import superlord.little_beasties.common.world.LBBiomeModifier;

public class LBBiomeModifiers {
	
	public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, LittleBeasties.MOD_ID);
	
	public static final RegistryObject<Codec<LBBiomeModifier>> LB_BIOME_MODIFIER_TYPE = BIOME_MODIFIER_SERIALIZERS.register("little_beasties_biome_modifier", () -> Codec.unit(LBBiomeModifier.INSTANCE));

}
