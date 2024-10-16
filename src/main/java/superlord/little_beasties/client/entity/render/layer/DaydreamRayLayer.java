package superlord.little_beasties.client.entity.render.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.LightLayer;
import superlord.little_beasties.LittleBeasties;
import superlord.little_beasties.client.entity.model.DaydreamRayModel;
import superlord.little_beasties.common.entity.DaydreamRay;

public class DaydreamRayLayer extends RenderLayer<DaydreamRay, EntityModel<DaydreamRay>> {
	
	private static final RenderType TEXTURE = RenderType.eyes(new ResourceLocation(LittleBeasties.MOD_ID, "textures/entity/daydream_ray_layer.png"));
	private final RenderLayerParent<DaydreamRay, EntityModel<DaydreamRay>> daydreamRayRenderer;
	
	public DaydreamRayLayer(RenderLayerParent<DaydreamRay, EntityModel<DaydreamRay>> renderer) {
		super(renderer);
		this.daydreamRayRenderer = renderer;
	}
	
	@Override
	public void render(PoseStack matrixStack, MultiBufferSource buffer, int packedLight, DaydreamRay ray, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		if (!(daydreamRayRenderer.getModel() instanceof DaydreamRayModel)) {
			return;
		}
		RenderType tex = null;
		tex = TEXTURE;
		BlockPos rayPos = ray.blockPosition();
		int i = ray.level().getBrightness(LightLayer.SKY, rayPos);
		int j = ray.level().getBrightness(LightLayer.BLOCK, rayPos);
		int brightness = Math.max(i, j);
		if (brightness < 7) {
			if(tex != null){
	        	VertexConsumer ivertexbuilder = buffer.getBuffer(tex);
	            this.getParentModel().renderToBuffer(matrixStack, ivertexbuilder, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
	        }
		}
	}
	
}
