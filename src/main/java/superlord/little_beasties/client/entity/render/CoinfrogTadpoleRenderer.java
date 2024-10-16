package superlord.little_beasties.client.entity.render;

import javax.annotation.Nullable;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import superlord.little_beasties.LittleBeasties;
import superlord.little_beasties.client.ClientEvents;
import superlord.little_beasties.client.entity.model.CoinfrogTadpoleModel;
import superlord.little_beasties.common.entity.CoinfrogTadpole;

public class CoinfrogTadpoleRenderer extends MobRenderer<CoinfrogTadpole, EntityModel<CoinfrogTadpole>> {
	
	private static final ResourceLocation COINFROG_TADPOLE = new ResourceLocation(LittleBeasties.MOD_ID, "textures/entity/coinfrog/coinfrog_tadpole.png");
	
	public CoinfrogTadpoleRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new CoinfrogTadpoleModel(renderManager.bakeLayer(ClientEvents.COINFROG_TADPOLE)), 0.125F);
	}
	
	public ResourceLocation getTextureLocation(CoinfrogTadpole entity) {
		return COINFROG_TADPOLE;
	}
	
	@Nullable
	protected RenderType getRenderType(CoinfrogTadpole p_113806_, boolean p_113807_, boolean p_113808_, boolean p_113809_) {
		ResourceLocation resourcelocation = this.getTextureLocation(p_113806_);
		return RenderType.entityTranslucent(resourcelocation, false);
	}

}
