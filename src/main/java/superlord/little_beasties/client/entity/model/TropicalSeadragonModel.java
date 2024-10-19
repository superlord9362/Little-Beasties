package superlord.little_beasties.client.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import superlord.little_beasties.common.entity.TropicalSeadragon;

public class TropicalSeadragonModel extends EntityModel<TropicalSeadragon> {
	private final ModelPart root;
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart body2;
	private final ModelPart body3;
	private final ModelPart rightGill;
	private final ModelPart leftGill;

	public TropicalSeadragonModel(ModelPart root) {
		this.root = root.getChild("root");
		this.head = this.root.getChild("head");
		this.rightGill = head.getChild("rightGill");
		this.leftGill = head.getChild("leftGill");
		this.body = head.getChild("body");
		this.body2 = body.getChild("body2");
		this.body3 = body2.getChild("body3");
	}

	@SuppressWarnings("unused")
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(-0.5F, 22.5F, -11.0F));

		PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 28).addBox(-1.5F, -1.5F, -3.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition rightGill = head.addOrReplaceChild("rightGill", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.0F, 0.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 10).addBox(0.5F, -2.0F, 1.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 0.5F, 2.0F));

		PartDefinition leftGill = head.addOrReplaceChild("leftGill", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.0F, -1.0F, 0.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 10).addBox(-0.5F, -2.0F, 1.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 0.5F, 2.0F));

		PartDefinition body = head.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(0, 16).addBox(0.0F, -3.5F, 1.0F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 16).addBox(0.0F, -3.5F, 6.0F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 3.0F));

		PartDefinition body2 = body.addOrReplaceChild("body2", CubeListBuilder.create().texOffs(27, 0).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(0, 16).addBox(0.0F, -3.5F, 1.0F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 16).addBox(0.0F, -3.5F, 6.0F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 10.0F));

		PartDefinition body3 = body2.addOrReplaceChild("body3", CubeListBuilder.create().texOffs(0, 14).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(0, 16).addBox(0.0F, -3.5F, 1.0F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(17, 6).addBox(0.0F, -3.5F, 6.0F, 0.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 10.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(TropicalSeadragon	 entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.xRot = headPitch * ((float)Math.PI / 180F);
		this.head.yRot = (netHeadYaw * ((float)Math.PI / 180F)) + (Mth.cos(0.15F * ageInTicks) * 0.3F);
		this.body.yRot = Mth.sin(0.15F * ageInTicks) * 0.3F;
		this.body2.yRot = Mth.cos(0.15F * ageInTicks) * -0.3F;
		this.body3.yRot = Mth.cos(0.15F * ageInTicks) * -0.3F;
		this.leftGill.yRot = -Mth.abs(Mth.sin(0.15F * ageInTicks) * 0.5F);
		this.rightGill.yRot = Mth.abs(Mth.sin(0.15F * ageInTicks + ((float)Math.PI / 2)) * 0.5F);
		if (entity.isActivelyRubbing()) {
			this.head.z = Mth.sin(0.2F * ageInTicks) * 6F;
		} else {
			this.head.z = 0;
		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}