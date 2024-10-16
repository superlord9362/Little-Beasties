package superlord.little_beasties.client.entity.render;

import javax.annotation.Nullable;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import superlord.little_beasties.LittleBeasties;
import superlord.little_beasties.client.ClientEvents;
import superlord.little_beasties.client.entity.model.SnappyWoolbugModel;
import superlord.little_beasties.common.entity.SnappyWoolbug;

public class SnappyWoolbugRenderer extends MobRenderer<SnappyWoolbug, EntityModel<SnappyWoolbug>> {

	private static final ResourceLocation SNAPPY_WOOLBUG = new ResourceLocation(LittleBeasties.MOD_ID, "textures/entity/snappy_woolbug.png");

	public SnappyWoolbugRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new SnappyWoolbugModel(renderManager.bakeLayer(ClientEvents.SNAPPY_WOOLBUG)), 0.4375F);
	}

	public ResourceLocation getTextureLocation(SnappyWoolbug entity) {
		return SNAPPY_WOOLBUG;
	}

	@Nullable
	protected RenderType getRenderType(SnappyWoolbug p_113806_, boolean p_113807_, boolean p_113808_, boolean p_113809_) {
		ResourceLocation resourcelocation = this.getTextureLocation(p_113806_);
		return RenderType.entityTranslucent(resourcelocation, false);
	}


}
