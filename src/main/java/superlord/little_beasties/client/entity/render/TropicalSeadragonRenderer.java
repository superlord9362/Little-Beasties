package superlord.little_beasties.client.entity.render;

import javax.annotation.Nullable;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import superlord.little_beasties.LittleBeasties;
import superlord.little_beasties.client.ClientEvents;
import superlord.little_beasties.client.entity.model.TropicalSeadragonModel;
import superlord.little_beasties.common.entity.TropicalSeadragon;

public class TropicalSeadragonRenderer extends MobRenderer<TropicalSeadragon, EntityModel<TropicalSeadragon>> {

	private static final ResourceLocation ORANGE = new ResourceLocation(LittleBeasties.MOD_ID, "textures/entity/tropical_seadragon/orange.png");
	private static final ResourceLocation OPAL = new ResourceLocation(LittleBeasties.MOD_ID, "textures/entity/tropical_seadragon/opaline.png");
	private static final ResourceLocation RAINBOW = new ResourceLocation(LittleBeasties.MOD_ID, "textures/entity/tropical_seadragon/rainbow.png");
	private static final ResourceLocation GOLDEN = new ResourceLocation(LittleBeasties.MOD_ID, "textures/entity/tropical_seadragon/golden.png");
	
	public TropicalSeadragonRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new TropicalSeadragonModel(renderManager.bakeLayer(ClientEvents.TROPICAL_SEADRAGON)), 0.4F);
	}
	
	public ResourceLocation getTextureLocation(TropicalSeadragon entity) {
		if (entity.getColor() == 0) return ORANGE;
		if (entity.getColor() == 1) return OPAL;
		if (entity.getColor() == 2) return RAINBOW;
		else return GOLDEN;
	}
	
	@Nullable
	protected RenderType getRenderType(TropicalSeadragon p_113806_, boolean p_113807_, boolean p_113808_, boolean p_113809_) {
		ResourceLocation resourcelocation = this.getTextureLocation(p_113806_);
		return RenderType.entityTranslucent(resourcelocation, false);
	}
	
}
