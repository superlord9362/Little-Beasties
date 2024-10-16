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
import superlord.little_beasties.common.entity.DaydreamRay;

public class DaydreamRayModel extends EntityModel<DaydreamRay> {
	private final ModelPart body;
	private final ModelPart rightWing;
	private final ModelPart rightWingTip;
	private final ModelPart leftWing;
	private final ModelPart leftWingTip;
	private final ModelPart rightFoot;
	private final ModelPart leftFoot;
	private final ModelPart tail;

	public DaydreamRayModel(ModelPart root) {
		this.body = root.getChild("body");
		this.rightWing = body.getChild("rightWing");
		this.rightWingTip = rightWing.getChild("rightWingTip");
		this.leftWing = body.getChild("leftWing");
		this.leftWingTip = leftWing.getChild("leftWingTip");
		this.rightFoot = body.getChild("rightFoot");
		this.leftFoot = body.getChild("leftFoot");
		this.tail = body.getChild("tail");
	}

	@SuppressWarnings("unused")
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -2.5F, -13.0F, 14.0F, 5.0F, 26.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.5F, 0.0F));

		PartDefinition rightWing = body.addOrReplaceChild("rightWing", CubeListBuilder.create().texOffs(33, 31).mirror().addBox(0.0F, -0.5F, -1.0F, 12.0F, 1.0F, 18.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(7.0F, 0.0F, -4.0F));

		PartDefinition rightWingTip = rightWing.addOrReplaceChild("rightWingTip", CubeListBuilder.create().texOffs(39, 0).mirror().addBox(0.0F, 0.0F, 0.0F, 16.0F, 0.0F, 15.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(12.0F, -0.5F, -1.0F));

		PartDefinition leftWing = body.addOrReplaceChild("leftWing", CubeListBuilder.create().texOffs(33, 31).addBox(-12.0F, -0.5F, -1.0F, 12.0F, 1.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, 0.0F, -4.0F));

		PartDefinition leftWingTip = leftWing.addOrReplaceChild("leftWingTip", CubeListBuilder.create().texOffs(39, 0).addBox(-16.0F, 0.0F, 0.0F, 16.0F, 0.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offset(-12.0F, -0.5F, -1.0F));

		PartDefinition rightFoot = body.addOrReplaceChild("rightFoot", CubeListBuilder.create().texOffs(46, 15).mirror().addBox(-1.0F, 0.0F, 0.0F, 15.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(3.0F, 1.5F, 13.0F));

		PartDefinition leftFoot = body.addOrReplaceChild("leftFoot", CubeListBuilder.create().texOffs(46, 15).addBox(-14.0F, 0.0F, 0.0F, 15.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 1.5F, 13.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 31).addBox(-2.0F, -1.0F, 0.0F, 4.0F, 3.0F, 25.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, 13.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(DaydreamRay entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.body.xRot = headPitch * ((float)Math.PI / 180F);
		this.body.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.rightWing.zRot = Mth.sin(0.15F * ageInTicks) * 0.2F;
		this.leftWing.zRot = Mth.sin(0.15F * ageInTicks) * -0.2F;
		this.rightWingTip.zRot = Mth.sin(0.15F * ageInTicks) * 0.1F;
		this.leftWingTip.zRot = Mth.sin(0.15F * ageInTicks) * -0.1F;
		this.tail.xRot = Mth.sin(0.15F * ageInTicks) * 0.25F;
		this.leftFoot.xRot = Mth.sin(0.15F * ageInTicks) * 0.2F;
		this.rightFoot.xRot = -Mth.sin(0.15F * ageInTicks) * 0.2F;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}