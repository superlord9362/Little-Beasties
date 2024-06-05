package superlord.little_beasties.client.entity.render;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import superlord.little_beasties.LittleBeasties;
import superlord.little_beasties.client.ClientEvents;
import superlord.little_beasties.client.entity.model.BlueManefishModel;
import superlord.little_beasties.common.entity.BlueManefish;

public class BlueManefishRenderer extends MobRenderer<BlueManefish, EntityModel<BlueManefish>> {
	
	private static final ResourceLocation BLUE_MANEFISH = new ResourceLocation(LittleBeasties.MOD_ID, "textures/entity/blue_manefish.png");
	private static final ResourceLocation BLUE_MANEFISH_PUFFED = new ResourceLocation(LittleBeasties.MOD_ID, "textures/entity/blue_manefish_spread.png");
	
	public BlueManefishRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new BlueManefishModel(renderManager.bakeLayer(ClientEvents.BLUE_MANEFISH)), 0.4F);
	}
	
	public ResourceLocation getTextureLocation(BlueManefish entity) {
		if (entity.isPuffing()) return BLUE_MANEFISH_PUFFED;
		else return BLUE_MANEFISH;
	}

}
