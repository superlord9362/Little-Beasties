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
import superlord.little_beasties.common.entity.Collector;

@SuppressWarnings("unused")
public class CollectorModel extends EntityModel<Collector> {
	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart rightEye;
	private final ModelPart leftEye;
	private final ModelPart rightAntenna;
	private final ModelPart leftAntenna;
	private final ModelPart tail;
	private final ModelPart tailEnd;
	private final ModelPart rightArm;
	private final ModelPart leftArm;
	private final ModelPart rightLeg;
	private final ModelPart leftLeg;

	public CollectorModel(ModelPart root) {
		this.root = root.getChild("root");
		this.body = this.root.getChild("body");
		this.rightEye = this.body.getChild("rightEye");
		this.leftEye = this.body.getChild("leftEye");
		this.rightAntenna = this.body.getChild("rightAntenna");
		this.leftAntenna = this.body.getChild("leftAntenna");
		this.tail = this.body.getChild("tail");
		this.tailEnd = this.tail.getChild("tailEnd");
		this.rightArm = this.body.getChild("rightArm");
		this.leftArm = this.body.getChild("leftArm");
		this.rightLeg = this.root.getChild("rightLeg");
		this.leftLeg = this.root.getChild("leftLeg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -15.0F, -6.5F, 12.0F, 15.0F, 13.0F, new CubeDeformation(0.0F))
				.texOffs(37, 0).addBox(-3.0F, -15.0F, -13.5F, 6.0F, 5.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 0.5F));

		PartDefinition rightEye = body.addOrReplaceChild("rightEye", CubeListBuilder.create().texOffs(0, 42).addBox(-2.5F, -2.5F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(5.5F, -14.5F, -2.0F));

		PartDefinition leftEye = body.addOrReplaceChild("leftEye", CubeListBuilder.create().texOffs(0, 42).mirror().addBox(-2.5F, -2.5F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-5.5F, -14.5F, -2.0F));

		PartDefinition rightAntenna = body.addOrReplaceChild("rightAntenna", CubeListBuilder.create().texOffs(0, 14).addBox(0.0F, -7.0F, -0.5F, 0.0F, 7.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -15.0F, -12.0F, 0.0F, 0.3927F, 0.0F));

		PartDefinition leftAntenna = body.addOrReplaceChild("leftAntenna", CubeListBuilder.create().texOffs(0, 14).addBox(0.0F, -7.0F, -0.5F, 0.0F, 7.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, -15.0F, -12.0F, 0.0F, -0.3927F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(21, 38).addBox(-3.0F, -1.0F, -1.0F, 6.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, 6.5F));

		PartDefinition tailEnd = tail.addOrReplaceChild("tailEnd", CubeListBuilder.create().texOffs(28, 28).addBox(-5.0F, 0.0F, -7.0F, 10.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.0F, 6.0F));

		PartDefinition rightArm = body.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(0, 6).addBox(-1.5F, 0.0F, -6.0F, 3.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, -6.0F, -6.5F, 1.1781F, 0.0F, 0.0F));

		PartDefinition leftArm = body.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(0, 6).mirror().addBox(-1.5F, 0.0F, -6.0F, 3.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.5F, -6.0F, -6.5F, 1.1781F, 0.0F, 0.0F));

		PartDefinition rightLeg = root.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(47, 48).mirror().addBox(-1.5F, 0.0F, -3.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.5F, -4.0F, 0.0F));

		PartDefinition leftLeg = root.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(47, 48).addBox(-1.5F, 0.0F, -3.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, -4.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(Collector entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.body.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.body.xRot = headPitch * ((float)Math.PI / 180F);
		this.leftAntenna.xRot = Mth.sin(ageInTicks / 5 * 0.2F) * 0.05F;
		this.rightAntenna.xRot = Mth.sin(ageInTicks / 5 * 0.2F + (float)Math.PI) * 0.05F;
		this.leftArm.xRot = Mth.sin(ageInTicks * 0.1F) * 0.1F + 1.1781F;
		this.rightArm.xRot = Mth.sin(ageInTicks * 0.1F) * 0.1F + 1.1781F;
		this.tailEnd.xRot = Mth.sin(ageInTicks * 0.1F + (float)Math.PI) * 0.1F;
		if (entity.isInWater()) {
			this.tail.yRot = Mth.cos(limbSwing * 0.6662F * 2.5F) * 1.5F * limbSwingAmount * 0.5F;
			this.body.zRot = Mth.cos(limbSwing * 0.6662F * 2.5F) * 0.35F * limbSwingAmount * 0.5F;
			this.rightLeg.xRot = Mth.cos(limbSwing * 0.6662F * 2.5F) * 1.4F * limbSwingAmount * 0.5F;
			this.leftLeg.xRot = Mth.cos(limbSwing * 0.6662F * 2.5F + (float)Math.PI) * 1.4F * limbSwingAmount * 0.5F;
		} else {
			this.tail.yRot = Mth.cos(limbSwing * 0.6662F) * 1.5F * limbSwingAmount * 0.5F;
			this.body.zRot = Mth.cos(limbSwing * 0.6662F) * 0.35F * limbSwingAmount * 0.5F;
			this.rightLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount * 0.5F;
			this.leftLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount * 0.5F;
		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
