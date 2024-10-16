package superlord.little_beasties.client.entity.render;

import javax.annotation.Nullable;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import superlord.little_beasties.LittleBeasties;
import superlord.little_beasties.client.ClientEvents;
import superlord.little_beasties.client.entity.model.CoinfrogModel;
import superlord.little_beasties.common.entity.Coinfrog;

public class CoinfrogRenderer extends MobRenderer<Coinfrog, EntityModel<Coinfrog>> {
	
	private static final ResourceLocation COINFROG = new ResourceLocation(LittleBeasties.MOD_ID, "textures/entity/coinfrog/coinfrog.png");
	
	public CoinfrogRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new CoinfrogModel(renderManager.bakeLayer(ClientEvents.COINFROG)), 0.4375F);
	}
	
	public ResourceLocation getTextureLocation(Coinfrog entity) {
		return COINFROG;
	}
	
	@Nullable
	protected RenderType getRenderType(Coinfrog p_113806_, boolean p_113807_, boolean p_113808_, boolean p_113809_) {
		ResourceLocation resourcelocation = this.getTextureLocation(p_113806_);
		return RenderType.entityTranslucent(resourcelocation, false);
	}

}
