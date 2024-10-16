package superlord.little_beasties.client.entity.render;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import superlord.little_beasties.LittleBeasties;
import superlord.little_beasties.client.ClientEvents;
import superlord.little_beasties.client.entity.model.CollectorModel;
import superlord.little_beasties.common.entity.Collector;

public class CollectorRenderer extends MobRenderer<Collector, EntityModel<Collector>> {
	
	private static final ResourceLocation COLLECTOR = new ResourceLocation(LittleBeasties.MOD_ID, "textures/entity/collector.png");
	
	public CollectorRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new CollectorModel(renderManager.bakeLayer(ClientEvents.COLLECTOR)), 0.4375F);
	}
	
	public ResourceLocation getTextureLocation(Collector entity) {
		return COLLECTOR;
	}

}
