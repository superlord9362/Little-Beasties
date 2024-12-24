package superlord.little_beasties.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import superlord.little_beasties.LittleBeasties;
import superlord.little_beasties.common.CommonProxy;
import superlord.little_beasties.common.particle.ImpactParticle;
import superlord.little_beasties.common.particle.StunnedParticle;
import superlord.little_beasties.init.LBParticles;

@Mod.EventBusSubscriber(modid = LittleBeasties.MOD_ID, value = Dist.CLIENT)
public class ClientProxy extends CommonProxy {
	public void commonInit() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		bus.addListener(this::setupParticles);
	}

	public void clientInit() {

	}
	
	public void setupParticles(RegisterParticleProvidersEvent registry) {
		registry.registerSpriteSet(LBParticles.STUNNED.get(), StunnedParticle.Provider::new);
		registry.registerSpriteSet(LBParticles.IMPACT.get(), ImpactParticle.Provider::new);
	}
	
}
