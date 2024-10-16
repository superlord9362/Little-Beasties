package superlord.little_beasties.client.entity.render;

import javax.annotation.Nullable;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import superlord.little_beasties.LittleBeasties;
import superlord.little_beasties.client.ClientEvents;
import superlord.little_beasties.client.entity.model.ProboscisFishModel;
import superlord.little_beasties.common.entity.ProboscisFish;

public class ProboscisFishRenderer extends MobRenderer<ProboscisFish, EntityModel<ProboscisFish>> {
	
	private static final ResourceLocation PROBOSCIS_FISH = new ResourceLocation(LittleBeasties.MOD_ID, "textures/entity/proboscis_fish.png");
	
	public ProboscisFishRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new ProboscisFishModel(renderManager.bakeLayer(ClientEvents.PROBOSCIS_FISH)), 0.3125F);
	}
	
	public ResourceLocation getTextureLocation(ProboscisFish entity) {
		return PROBOSCIS_FISH;
	}
	
	@Nullable
	protected RenderType getRenderType(ProboscisFish p_113806_, boolean p_113807_, boolean p_113808_, boolean p_113809_) {
		ResourceLocation resourcelocation = this.getTextureLocation(p_113806_);
		return RenderType.entityTranslucent(resourcelocation, false);
	}

}
