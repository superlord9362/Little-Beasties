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
import superlord.little_beasties.common.entity.ProboscisFish;

public class ProboscisFishModel extends EntityModel<ProboscisFish> {
	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart proboscis;
	private final ModelPart analFin;
	private final ModelPart rightFin1;
	private final ModelPart rightFin2;
	private final ModelPart rightFin3;
	private final ModelPart leftFin1;
	private final ModelPart leftFin2;
	private final ModelPart leftFin3;

	public ProboscisFishModel(ModelPart root) {
		this.root = root.getChild("root");
		this.body = this.root.getChild("body");
		this.proboscis = body.getChild("proboscis");
		this.analFin = body.getChild("analFin");
		this.rightFin1 = body.getChild("rightFin1");
		this.rightFin2 = body.getChild("rightFin2");
		this.rightFin3 = body.getChild("rightFin3");
		this.leftFin1 = body.getChild("leftFin1");
		this.leftFin2 = body.getChild("leftFin2");
		this.leftFin3 = body.getChild("leftFin3");
	}

	@SuppressWarnings("unused")
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -4.5F, -1.0F, 3.0F, 9.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(16, 0).addBox(-1.5F, -2.5F, -5.0F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.5F, -4.0F));

		PartDefinition proboscis = body.addOrReplaceChild("proboscis", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.5F, -5.0F));

		PartDefinition analFin = body.addOrReplaceChild("analFin", CubeListBuilder.create().texOffs(0, 16).addBox(0.0F, -4.5F, 0.0F, 0.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 9.0F));

		PartDefinition rightFin1 = body.addOrReplaceChild("rightFin1", CubeListBuilder.create().texOffs(7, 19).mirror().addBox(0.0F, -2.0F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.5F, -1.5F, 4.0F));

		PartDefinition rightFin2 = body.addOrReplaceChild("rightFin2", CubeListBuilder.create().texOffs(7, 19).mirror().addBox(0.0F, -2.0F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.5F, 1.0F, 3.0F));

		PartDefinition rightFin3 = body.addOrReplaceChild("rightFin3", CubeListBuilder.create().texOffs(7, 19).mirror().addBox(0.0F, -2.0F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.5F, 3.5F, 4.0F));

		PartDefinition leftFin1 = body.addOrReplaceChild("leftFin1", CubeListBuilder.create().texOffs(7, 19).addBox(-2.0F, -2.0F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, -1.5F, 4.0F));

		PartDefinition leftFin2 = body.addOrReplaceChild("leftFin2", CubeListBuilder.create().texOffs(7, 19).addBox(-2.0F, -2.0F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 1.0F, 3.0F));

		PartDefinition leftFin3 = body.addOrReplaceChild("leftFin3", CubeListBuilder.create().texOffs(7, 19).addBox(-2.0F, -2.0F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 3.5F, 4.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(ProboscisFish entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.body.xRot = (headPitch * ((float)Math.PI / 180F)) + (Mth.sin(0.15F * ageInTicks) * 0.1F);
		this.body.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.proboscis.xRot = Mth.sin(0.1F * ageInTicks) * -0.1F;
		this.analFin.yRot = Mth.sin(0.2F * ageInTicks) * 0.15F;
		this.rightFin1.yRot = Mth.sin(0.3F * ageInTicks) * 0.15F;
		this.rightFin2.yRot = Mth.cos(0.3F * ageInTicks) * 0.15F;
		this.rightFin3.yRot = Mth.sin(0.3F * ageInTicks) * 0.15F;
		this.leftFin1.yRot = Mth.sin(0.3F * ageInTicks) * 0.15F;
		this.leftFin2.yRot = Mth.cos(0.3F * ageInTicks) * 0.15F;
		this.leftFin3.yRot = Mth.sin(0.3F * ageInTicks) * 0.15F;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}