package superlord.little_beasties.client.entity.render;

import javax.annotation.Nullable;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import superlord.little_beasties.LittleBeasties;
import superlord.little_beasties.client.ClientEvents;
import superlord.little_beasties.client.entity.model.DaydreamRayModel;
import superlord.little_beasties.common.entity.DaydreamRay;

public class DaydreamRayRenderer extends MobRenderer<DaydreamRay, EntityModel<DaydreamRay>> {

	private static final ResourceLocation DAYDREAM_RAY = new ResourceLocation(LittleBeasties.MOD_ID, "textures/entity/daydream_ray.png");

	public DaydreamRayRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new DaydreamRayModel(renderManager.bakeLayer(ClientEvents.DAYDREAM_RAY)), 0.4F);
	}

	public ResourceLocation getTextureLocation(DaydreamRay entity) {
		return DAYDREAM_RAY;
	}

	@Nullable
	protected RenderType getRenderType(DaydreamRay p_113806_, boolean p_113807_, boolean p_113808_, boolean p_113809_) {
		ResourceLocation resourcelocation = this.getTextureLocation(p_113806_);
		return RenderType.entityTranslucent(resourcelocation, false);
	}


}
