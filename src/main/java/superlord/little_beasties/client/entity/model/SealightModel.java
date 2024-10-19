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
import superlord.little_beasties.common.entity.Sealight;

public class SealightModel extends EntityModel<Sealight> {
	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart fin;
	private final ModelPart rightFin;
	private final ModelPart leftFin;
	private final ModelPart rightTentacle;
	private final ModelPart rightTentacle2;
	private final ModelPart leftTentacle;
	private final ModelPart leftTentacle2;
	private final ModelPart middleTentacle;

	public SealightModel(ModelPart root) {
		this.root = root.getChild("root");
		this.body = this.root.getChild("body");
		this.fin = body.getChild("fin");
		this.rightFin = fin.getChild("rightFin");
		this.leftFin = fin.getChild("leftFin");
		this.rightTentacle = body.getChild("rightTentacle");
		this.rightTentacle2 = body.getChild("rightTentacle2");
		this.leftTentacle = body.getChild("leftTentacle");
		this.leftTentacle2 = body.getChild("leftTentacle2");
		this.middleTentacle = body.getChild("middleTentacle");
	}

	@SuppressWarnings("unused")
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 22.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -4.0F, -2.0F, 5.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition fin = body.addOrReplaceChild("fin", CubeListBuilder.create().texOffs(0, 17).addBox(-1.5F, -7.0F, 0.0F, 3.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 0.0F));

		PartDefinition rightFin = fin.addOrReplaceChild("rightFin", CubeListBuilder.create().texOffs(6, 17).addBox(-2.0F, -5.0F, 0.0F, 2.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 0.0F, 0.0F));

		PartDefinition leftFin = fin.addOrReplaceChild("leftFin", CubeListBuilder.create().texOffs(6, 17).mirror().addBox(0.0F, -5.0F, 0.0F, 2.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.5F, 0.0F, 0.0F));

		PartDefinition rightTentacle = body.addOrReplaceChild("rightTentacle", CubeListBuilder.create().texOffs(14, 0).addBox(-6.0F, -1.5F, 0.0F, 6.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, 0.5F, 0.0F));

		PartDefinition rightTentacle2 = body.addOrReplaceChild("rightTentacle2", CubeListBuilder.create().texOffs(0, 12).addBox(-5.0F, -0.5F, 0.0F, 5.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, 3.5F, 0.0F));

		PartDefinition leftTentacle = body.addOrReplaceChild("leftTentacle", CubeListBuilder.create().texOffs(14, 0).mirror().addBox(0.0F, -1.5F, 0.0F, 6.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.5F, 0.5F, 0.0F));

		PartDefinition leftTentacle2 = body.addOrReplaceChild("leftTentacle2", CubeListBuilder.create().texOffs(0, 12).mirror().addBox(0.0F, -0.5F, 0.0F, 5.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.5F, 3.5F, 0.0F));

		PartDefinition middleTentacle = body.addOrReplaceChild("middleTentacle", CubeListBuilder.create().texOffs(18, 3).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Sealight entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.body.xRot = headPitch * ((float)Math.PI / 180F);
		this.body.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.fin.yRot = 0.35F * Mth.sin(0.2F * ageInTicks);
		this.rightFin.yRot = 0.45F * Mth.cos(0.2F * ageInTicks);
		this.leftFin.yRot = -0.45F * Mth.cos(0.2F * ageInTicks);
		this.leftTentacle.zRot = 0.45F * Mth.sin(0.2F * ageInTicks);
		this.rightTentacle.zRot = -0.45F * Mth.sin(0.2F * ageInTicks);
		this.leftTentacle2.zRot = 0.45F * Mth.cos(0.2F * ageInTicks);
		this.rightTentacle2.zRot = -0.45F * Mth.cos(0.2F * ageInTicks);
		this.middleTentacle.yRot = 0.45F * Mth.sin(0.2F * ageInTicks);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}