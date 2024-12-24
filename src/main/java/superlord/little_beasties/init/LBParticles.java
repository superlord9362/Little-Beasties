package superlord.little_beasties.init;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import superlord.little_beasties.LittleBeasties;

public class LBParticles {
    public static final DeferredRegister<ParticleType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, LittleBeasties.MOD_ID);

    public static final RegistryObject<SimpleParticleType> STUNNED = REGISTER.register("stunned", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> IMPACT = REGISTER.register("impact", () -> new SimpleParticleType(false));
    
}
