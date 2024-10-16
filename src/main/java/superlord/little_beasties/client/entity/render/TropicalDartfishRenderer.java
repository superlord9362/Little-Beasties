package superlord.little_beasties.client.entity.render;

import javax.annotation.Nullable;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import superlord.little_beasties.LittleBeasties;
import superlord.little_beasties.client.ClientEvents;
import superlord.little_beasties.client.entity.model.TropicalDartfishModel;
import superlord.little_beasties.common.entity.TropicalDartfish;

public class TropicalDartfishRenderer extends MobRenderer<TropicalDartfish, EntityModel<TropicalDartfish>> {

	private static final ResourceLocation TROPICAL_DARTFISH = new ResourceLocation(LittleBeasties.MOD_ID, "textures/entity/tropical_dartfish.png");

	public TropicalDartfishRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new TropicalDartfishModel(renderManager.bakeLayer(ClientEvents.TROPICAL_DARTFISH)), 0.5F);
	}

	public ResourceLocation getTextureLocation(TropicalDartfish entity) {
		return TROPICAL_DARTFISH;
	}

	@Nullable
	protected RenderType getRenderType(TropicalDartfish p_113806_, boolean p_113807_, boolean p_113808_, boolean p_113809_) {
		ResourceLocation resourcelocation = this.getTextureLocation(p_113806_);
		return RenderType.entityTranslucent(resourcelocation, false);
	}


}
