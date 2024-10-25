package superlord.little_beasties.client;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import superlord.little_beasties.LittleBeasties;
import superlord.little_beasties.client.entity.model.*;
import superlord.little_beasties.client.entity.render.*;
import superlord.little_beasties.init.LBEntities;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = LittleBeasties.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {
	public static ModelLayerLocation TROPICAL_DARTFISH = modelLayer("tropical_dartfish");
	public static ModelLayerLocation BLUE_MANEFISH = modelLayer("blue_manefish");
	public static ModelLayerLocation SAILDRIFTER = modelLayer("saildrifter");
	public static ModelLayerLocation TROPICAL_SEADRAGON = modelLayer("tropical_seadragon");
	public static ModelLayerLocation PROBOSCIS_FISH = modelLayer("proboscis_fish");
	public static ModelLayerLocation WAVE_HORNGLIDER = modelLayer("wave_hornglider");
	public static ModelLayerLocation SEALIGHT = modelLayer("sealight");
	public static ModelLayerLocation SNAPPY_WOOLBUG = modelLayer("snappy_woolbug");
	public static ModelLayerLocation RAINWITCH = modelLayer("rainwitch");
	public static ModelLayerLocation COINFROG = modelLayer("coinfrog");
	public static ModelLayerLocation COINFROG_TADPOLE = modelLayer("coinfrog_tadpole");
	public static ModelLayerLocation MOHOMOOHO = modelLayer("mohomooho");
	public static ModelLayerLocation DAYDREAM_RAY = modelLayer("daydream_ray");
	public static ModelLayerLocation COLLECTOR = modelLayer("collector");

	private static ModelLayerLocation modelLayer(String name) {
		return new ModelLayerLocation(new ResourceLocation(LittleBeasties.MOD_ID, name), "main");
	}

	@SubscribeEvent
	public static void init(final FMLClientSetupEvent event) {

	}

	@SubscribeEvent
	public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(LBEntities.TROPICAL_DARTFISH.get(), TropicalDartfishRenderer::new);
		event.registerEntityRenderer(LBEntities.BLUE_MANEFISH.get(), BlueManefishRenderer::new);
		event.registerEntityRenderer(LBEntities.SAILDRIFTER.get(), SaildrifterRenderer::new);
		event.registerEntityRenderer(LBEntities.TROPICAL_SEADRAGON.get(), TropicalSeadragonRenderer::new);
		event.registerEntityRenderer(LBEntities.PROBOSCIS_FISH.get(), ProboscisFishRenderer::new);
		event.registerEntityRenderer(LBEntities.WAVE_HORNGLIDER.get(), WaveHorngliderRenderer::new);
		event.registerEntityRenderer(LBEntities.SEA_LIGHT.get(), SealightRenderer::new);
		event.registerEntityRenderer(LBEntities.SNAPPY_WOOLBUG.get(), SnappyWoolbugRenderer::new);
		event.registerEntityRenderer(LBEntities.RAINWITCH.get(), RainwitchRenderer::new);
		event.registerEntityRenderer(LBEntities.COINFROG.get(), CoinfrogRenderer::new);
		event.registerEntityRenderer(LBEntities.COINFROG_TADPOLE.get(), CoinfrogTadpoleRenderer::new);	
		event.registerEntityRenderer(LBEntities.MOHOMOOHO.get(), MohomoohoRenderer::new);
		event.registerEntityRenderer(LBEntities.DAYDREAM_RAY.get(), DaydreamRayRenderer::new);
		event.registerEntityRenderer(LBEntities.COLLECTOR.get(), CollectorRenderer::new);
	}
	
	@SubscribeEvent
	public static void registerLayerDefinition(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(TROPICAL_DARTFISH, TropicalDartfishModel::createBodyLayer);
		event.registerLayerDefinition(BLUE_MANEFISH, BlueManefishModel::createBodyLayer);
		event.registerLayerDefinition(SAILDRIFTER, SaildrifterModel::createBodyLayer);
		event.registerLayerDefinition(TROPICAL_SEADRAGON, TropicalSeadragonModel::createBodyLayer);
		event.registerLayerDefinition(PROBOSCIS_FISH, ProboscisFishModel::createBodyLayer);
		event.registerLayerDefinition(WAVE_HORNGLIDER, WaveHorngliderModel::createBodyLayer);
		event.registerLayerDefinition(SEALIGHT, SealightModel::createBodyLayer);	
		event.registerLayerDefinition(SNAPPY_WOOLBUG, SnappyWoolbugModel::createBodyLayer);
		event.registerLayerDefinition(RAINWITCH, RainwitchModel::createBodyLayer);
		event.registerLayerDefinition(COINFROG, CoinfrogModel::createBodyLayer);
		event.registerLayerDefinition(COINFROG_TADPOLE, CoinfrogTadpoleModel::createBodyLayer);	
		event.registerLayerDefinition(MOHOMOOHO, MohomoohoModel::createBodyLayer);
		event.registerLayerDefinition(DAYDREAM_RAY, DaydreamRayModel::createBodyLayer);
		event.registerLayerDefinition(COLLECTOR, CollectorModel::createBodyLayer);
	}
	
}
