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
import superlord.little_beasties.client.entity.model.BlueManefishModel;
import superlord.little_beasties.client.entity.model.CoinfrogModel;
import superlord.little_beasties.client.entity.model.CoinfrogTadpoleModel;
import superlord.little_beasties.client.entity.model.DaydreamRayModel;
import superlord.little_beasties.client.entity.model.MohomoohoModel;
import superlord.little_beasties.client.entity.model.ProboscisFishModel;
import superlord.little_beasties.client.entity.model.RainwitchModel;
import superlord.little_beasties.client.entity.model.SaildrifterModel;
import superlord.little_beasties.client.entity.model.SealightModel;
import superlord.little_beasties.client.entity.model.SnappyWoolbugModel;
import superlord.little_beasties.client.entity.model.TropicalDartfishModel;
import superlord.little_beasties.client.entity.model.TropicalSeadragonModel;
import superlord.little_beasties.client.entity.model.WaveHorngliderModel;
import superlord.little_beasties.client.entity.render.BlueManefishRenderer;
import superlord.little_beasties.client.entity.render.CoinfrogRenderer;
import superlord.little_beasties.client.entity.render.CoinfrogTadpoleRenderer;
import superlord.little_beasties.client.entity.render.DaydreamRayRenderer;
import superlord.little_beasties.client.entity.render.MohomoohoRenderer;
import superlord.little_beasties.client.entity.render.ProboscisFishRenderer;
import superlord.little_beasties.client.entity.render.RainwitchRenderer;
import superlord.little_beasties.client.entity.render.SaildrifterRenderer;
import superlord.little_beasties.client.entity.render.SealightRenderer;
import superlord.little_beasties.client.entity.render.SnappyWoolbugRenderer;
import superlord.little_beasties.client.entity.render.TropicalDartfishRenderer;
import superlord.little_beasties.client.entity.render.TropicalSeadragonRenderer;
import superlord.little_beasties.client.entity.render.WaveHorngliderRenderer;
import superlord.little_beasties.init.LBEntities;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = LittleBeasties.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {
	
	public static ModelLayerLocation TROPICAL_DARTFISH = new ModelLayerLocation(new ResourceLocation(LittleBeasties.MOD_ID, "tropical_dartfish"), "tropical_dartfish");
	public static ModelLayerLocation BLUE_MANEFISH = new ModelLayerLocation(new ResourceLocation(LittleBeasties.MOD_ID, "blue_manefish"), "blue_manefish");
	public static ModelLayerLocation SAILDRIFTER = new ModelLayerLocation(new ResourceLocation(LittleBeasties.MOD_ID, "saildrifter"), "saildrifter");
	public static ModelLayerLocation TROPICAL_SEADRAGON = new ModelLayerLocation(new ResourceLocation(LittleBeasties.MOD_ID, "tropical_seadragon"), "tropical_seadragon");
	public static ModelLayerLocation PROBOSCIS_FISH = new ModelLayerLocation(new ResourceLocation(LittleBeasties.MOD_ID, "proboscis_fish"), "proboscis_fish");
	public static ModelLayerLocation WAVE_HORNGLIDER = new ModelLayerLocation(new ResourceLocation(LittleBeasties.MOD_ID, "wave_hornglider"), "wave_hornglider");
	public static ModelLayerLocation SEALIGHT = new ModelLayerLocation(new ResourceLocation(LittleBeasties.MOD_ID, "sealight"), "sealight");
	public static ModelLayerLocation SNAPPY_WOOLBUG = new ModelLayerLocation(new ResourceLocation(LittleBeasties.MOD_ID, "snappy_woolbug"), "snappy_woolbug");
	public static ModelLayerLocation RAINWITCH = new ModelLayerLocation(new ResourceLocation(LittleBeasties.MOD_ID, "rainwitch"), "rainwitch");
	public static ModelLayerLocation COINFROG = new ModelLayerLocation(new ResourceLocation(LittleBeasties.MOD_ID, "coinfrog"), "coinfrog");
	public static ModelLayerLocation COINFROG_TADPOLE = new ModelLayerLocation(new ResourceLocation(LittleBeasties.MOD_ID, "coinfrog_tadpole"), "coinfrog_tadpole");
	public static ModelLayerLocation MOHOMOOHO = new ModelLayerLocation(new ResourceLocation(LittleBeasties.MOD_ID, "mohomooho"), "mohomooho");
	public static ModelLayerLocation DAYDREAM_RAY = new ModelLayerLocation(new ResourceLocation(LittleBeasties.MOD_ID, "daydream_ray"), "daydream_ray");
	
	@SubscribeEvent
	public static void init(final FMLClientSetupEvent event) {
		ClientProxy.setupBlockRenders();
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
	}
	
}
