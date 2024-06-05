package superlord.little_beasties.client;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import superlord.little_beasties.LittleBeasties;
import superlord.little_beasties.client.entity.model.BlueManefishModel;
import superlord.little_beasties.client.entity.model.SaildrifterModel;
import superlord.little_beasties.client.entity.model.TropicalDartfishModel;
import superlord.little_beasties.client.entity.render.BlueManefishRenderer;
import superlord.little_beasties.client.entity.render.SaildrifterRenderer;
import superlord.little_beasties.client.entity.render.TropicalDartfishRenderer;
import superlord.little_beasties.init.LBEntities;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = LittleBeasties.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {
	
	public static ModelLayerLocation TROPICAL_DARTFISH = new ModelLayerLocation(new ResourceLocation(LittleBeasties.MOD_ID, "tropical_dartfish"), "tropical_dartfish");
	public static ModelLayerLocation BLUE_MANEFISH = new ModelLayerLocation(new ResourceLocation(LittleBeasties.MOD_ID, "blue_manefish"), "blue_manefish");
	public static ModelLayerLocation SAILDRIFTER = new ModelLayerLocation(new ResourceLocation(LittleBeasties.MOD_ID, "saildrifter"), "saildrifter");
	
	@SubscribeEvent
	public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(LBEntities.TROPICAL_DARTFISH.get(), TropicalDartfishRenderer::new);
		event.registerEntityRenderer(LBEntities.BLUE_MANEFISH.get(), BlueManefishRenderer::new);
		event.registerEntityRenderer(LBEntities.SAILDRIFTER.get(), SaildrifterRenderer::new);
	}
	
	@SubscribeEvent
	public static void registerLayerDefinition(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(TROPICAL_DARTFISH, TropicalDartfishModel::createBodyLayer);
		event.registerLayerDefinition(BLUE_MANEFISH, BlueManefishModel::createBodyLayer);
		event.registerLayerDefinition(SAILDRIFTER, SaildrifterModel::createBodyLayer);
	}
	
}
