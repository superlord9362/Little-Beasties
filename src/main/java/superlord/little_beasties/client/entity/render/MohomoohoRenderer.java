package superlord.little_beasties.client.entity.render;

import javax.annotation.Nullable;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import superlord.little_beasties.LittleBeasties;
import superlord.little_beasties.client.ClientEvents;
import superlord.little_beasties.client.entity.model.MohomoohoModel;
import superlord.little_beasties.common.entity.Mohomooho;

public class MohomoohoRenderer extends MobRenderer<Mohomooho, EntityModel<Mohomooho>> {

	private static final ResourceLocation BLACK = new ResourceLocation(LittleBeasties.MOD_ID, "textures/entity/mohomooho/black.png");
	private static final ResourceLocation RED = new ResourceLocation(LittleBeasties.MOD_ID, "textures/entity/mohomooho/red.png");
	private static final ResourceLocation TAN = new ResourceLocation(LittleBeasties.MOD_ID, "textures/entity/mohomooho/tan.png");
	private static final ResourceLocation BROWN = new ResourceLocation(LittleBeasties.MOD_ID, "textures/entity/mohomooho/brown.png");

	public MohomoohoRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new MohomoohoModel(renderManager.bakeLayer(ClientEvents.MOHOMOOHO)), 0.4F);
	}

	public ResourceLocation getTextureLocation(Mohomooho entity) {
		if (entity.getColor() == 0) return BLACK;
		if (entity.getColor() == 1) return RED;
		if (entity.getColor() == 2) return TAN;
		return BROWN;
	}
	
	@Nullable
	protected RenderType getRenderType(Mohomooho p_113806_, boolean p_113807_, boolean p_113808_, boolean p_113809_) {
		ResourceLocation resourcelocation = this.getTextureLocation(p_113806_);
		return RenderType.entityTranslucent(resourcelocation, false);
	}

}
