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
import superlord.little_beasties.common.entity.SnappyWoolbug;

public class SnappyWoolbugModel extends EntityModel<SnappyWoolbug> {
	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart abdomen;
	private final ModelPart rightLeg;
	private final ModelPart rightLeg2;
	private final ModelPart rightLeg3;
	private final ModelPart leftLeg;
	private final ModelPart leftLeg2;
	private final ModelPart leftLeg3;

	public SnappyWoolbugModel(ModelPart root) {
		this.root = root.getChild("root");
		this.body = this.root.getChild("body");
		this.abdomen = body.getChild("abdomen");
		this.rightLeg = body.getChild("rightLeg");
		this.rightLeg2 = body.getChild("rightLeg2");
		this.rightLeg3 = body.getChild("rightLeg3");
		this.leftLeg = body.getChild("leftLeg");
		this.leftLeg2 = body.getChild("leftLeg2");
		this.leftLeg3 = body.getChild("leftLeg3");
	}

	@SuppressWarnings("unused")
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 25.0F, 0.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -3.0F, -3.5F, 7.0F, 6.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(16, 13).addBox(-2.5F, 0.0F, -5.5F, 5.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(19, 18).addBox(-1.5F, 3.0F, -5.5F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(0, 20).addBox(-2.5F, -1.0F, -4.5F, 5.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, -0.5F));

		PartDefinition abdomen = body.addOrReplaceChild("abdomen", CubeListBuilder.create().texOffs(0, 13).addBox(-2.5F, 0.0F, 0.0F, 5.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 3.5F));

		PartDefinition rightLeg = body.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(-1, 0).mirror().addBox(0.0F, 0.0F, -0.5F, 3.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.5F, 3.0F, -1.0F, 0.0F, 0.3927F, 0.3927F));

		PartDefinition rightLeg2 = body.addOrReplaceChild("rightLeg2", CubeListBuilder.create().texOffs(-1, 0).mirror().addBox(0.0F, 0.0F, -0.5F, 3.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.5F, 3.0F, 0.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition rightLeg3 = body.addOrReplaceChild("rightLeg3", CubeListBuilder.create().texOffs(-1, 0).mirror().addBox(0.0F, 0.0F, -0.5F, 3.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.5F, 3.0F, 1.0F, 0.0F, -0.3927F, 0.3927F));

		PartDefinition leftLeg = body.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(-1, 0).addBox(-3.0F, 0.0F, -0.5F, 3.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 3.0F, -1.0F, 0.0F, -0.3927F, -0.3927F));

		PartDefinition leftLeg2 = body.addOrReplaceChild("leftLeg2", CubeListBuilder.create().texOffs(-1, 0).addBox(-3.0F, 0.0F, -0.5F, 3.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 3.0F, 0.0F, 0.0F, 0.0F, -0.3927F));

		PartDefinition leftLeg3 = body.addOrReplaceChild("leftLeg3", CubeListBuilder.create().texOffs(-1, 0).addBox(-3.0F, 0.0F, -0.5F, 3.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 3.0F, 1.0F, 0.0F, 0.3927F, -0.3927F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(SnappyWoolbug entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float walkSpeed = 3.0f;
		float walkDegree = 2.0f;
		this.leftLeg3.zRot = -Math.abs((Mth.cos(limbSwing * walkSpeed + 10) * walkDegree * 0.5F * limbSwingAmount)) + 0.1767F;
		this.rightLeg3.zRot = Math.abs(-(Mth.cos(limbSwing * walkSpeed) * walkDegree * 0.5F * limbSwingAmount)) - 0.1767F;
		this.leftLeg2.zRot = -Math.abs((Mth.cos(limbSwing * walkSpeed + 5) * walkDegree * 0.5F * limbSwingAmount)) + 0.1767F;
		this.rightLeg2.zRot = Math.abs(-(Mth.cos(limbSwing * walkSpeed + 15) * walkDegree * 0.5F * limbSwingAmount)) - 0.1767F;
		this.leftLeg.zRot = -Math.abs((Mth.cos(limbSwing * walkSpeed) * walkDegree * 0.5F * limbSwingAmount)) + 0.1767F;
		this.rightLeg.zRot = Math.abs(-(Mth.cos(limbSwing * walkSpeed + 10) * walkDegree * 0.5F * limbSwingAmount)) - 0.1767F;
		this.leftLeg3.xRot = -(Mth.cos(limbSwing * walkSpeed + 10) * walkDegree * 0.5F * limbSwingAmount);
		this.rightLeg3.xRot = Mth.cos(limbSwing * walkSpeed) * walkDegree * 0.5F * limbSwingAmount;
		this.leftLeg2.xRot = -(Mth.cos(limbSwing * walkSpeed + 5) * walkDegree * 0.5F * limbSwingAmount);
		this.rightLeg2.xRot = Mth.cos(limbSwing * walkSpeed + 15) * walkDegree * 0.5F * limbSwingAmount;
		this.leftLeg.xRot = -(Mth.cos(limbSwing * walkSpeed) * walkDegree * 0.5F * limbSwingAmount);
		this.rightLeg.xRot = Mth.cos(limbSwing * walkSpeed + 10) * walkDegree * 0.5F * limbSwingAmount;
		this.abdomen.xRot = Mth.sin(limbSwing * walkSpeed) * walkDegree * 0.2F * limbSwingAmount;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}