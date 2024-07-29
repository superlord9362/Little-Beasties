package superlord.little_beasties.client.entity.render;

import javax.annotation.Nullable;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import superlord.little_beasties.LittleBeasties;
import superlord.little_beasties.client.ClientEvents;
import superlord.little_beasties.client.entity.model.WaveHorngliderModel;
import superlord.little_beasties.common.entity.WaveHornglider;

public class WaveHorngliderRenderer extends MobRenderer<WaveHornglider, EntityModel<WaveHornglider>> {
	
	private static final ResourceLocation WAVE_HORNGLIDER = new ResourceLocation(LittleBeasties.MOD_ID, "textures/entity/wave_hornglider.png");
	
	public WaveHorngliderRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new WaveHorngliderModel(renderManager.bakeLayer(ClientEvents.WAVE_HORNGLIDER)), 0.4F);
	}
	
	public ResourceLocation getTextureLocation(WaveHornglider entity) {
		return WAVE_HORNGLIDER;
	}
	
	@Nullable
	protected RenderType getRenderType(WaveHornglider p_113806_, boolean p_113807_, boolean p_113808_, boolean p_113809_) {
		ResourceLocation resourcelocation = this.getTextureLocation(p_113806_);
		return RenderType.entityTranslucent(resourcelocation, false);
	}

}
