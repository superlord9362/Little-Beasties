package superlord.little_beasties.client.entity.render;

import javax.annotation.Nullable;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import superlord.little_beasties.LittleBeasties;
import superlord.little_beasties.client.ClientEvents;
import superlord.little_beasties.client.entity.model.SealightModel;
import superlord.little_beasties.common.entity.Sealight;

public class SealightRenderer extends MobRenderer<Sealight, EntityModel<Sealight>> {

	private static final ResourceLocation EMERALD = new ResourceLocation(LittleBeasties.MOD_ID, "textures/entity/sealight/emerald.png");
	private static final ResourceLocation SUN = new ResourceLocation(LittleBeasties.MOD_ID, "textures/entity/sealight/sun.png");
	private static final ResourceLocation MELON = new ResourceLocation(LittleBeasties.MOD_ID, "textures/entity/sealight/melon.png");
	private static final ResourceLocation STRAWBERRY = new ResourceLocation(LittleBeasties.MOD_ID, "textures/entity/sealight/strawberry.png");
	private static final ResourceLocation MIDNIGHT = new ResourceLocation(LittleBeasties.MOD_ID, "textures/entity/sealight/midnight.png");

	public SealightRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new SealightModel(renderManager.bakeLayer(ClientEvents.SEALIGHT)), 0.4F);
	}

	public ResourceLocation getTextureLocation(Sealight entity) {
		if (entity.getColor() == 0) return EMERALD;
		if (entity.getColor() == 1) return SUN;
		if (entity.getColor() == 2) return MELON;
		if (entity.getColor() == 3) return STRAWBERRY;
		return MIDNIGHT;
	}

	public int getBlockLightLevel(Sealight p_174146_, BlockPos p_174147_) {
		int i = (int)Mth.clampedLerp(0.0F, 15.0F, 1.0F - (float)p_174146_.getDarkTicksRemaining() / 10.0F);
		return i == 15 ? 15 : Math.max(i, super.getBlockLightLevel(p_174146_, p_174147_));
	}
	
	@Nullable
	protected RenderType getRenderType(Sealight p_113806_, boolean p_113807_, boolean p_113808_, boolean p_113809_) {
		ResourceLocation resourcelocation = this.getTextureLocation(p_113806_);
		return RenderType.entityTranslucent(resourcelocation, false);
	}

}
