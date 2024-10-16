package superlord.little_beasties.client.entity.render;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import superlord.little_beasties.LittleBeasties;
import superlord.little_beasties.client.ClientEvents;
import superlord.little_beasties.client.entity.model.RainwitchModel;
import superlord.little_beasties.common.entity.Rainwitch;

public class RainwitchRenderer extends MobRenderer<Rainwitch, EntityModel<Rainwitch>> {
	
	private static final ResourceLocation RAINWITCH = new ResourceLocation(LittleBeasties.MOD_ID, "textures/entity/rainwitch/rainwitch.png");
	private static final ResourceLocation FLYING_RAINWITCH = new ResourceLocation(LittleBeasties.MOD_ID, "textures/entity/rainwitch/flying_rainwitch.png");
	
	public RainwitchRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new RainwitchModel(renderManager.bakeLayer(ClientEvents.RAINWITCH)), 0.1875F);
	}
	
	public ResourceLocation getTextureLocation(Rainwitch entity) {
		if (entity.isRaining()) return FLYING_RAINWITCH;
		else return RAINWITCH;
	}

}
