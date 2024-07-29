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
import superlord.little_beasties.common.entity.Coinfrog;

public class CoinfrogModel extends EntityModel<Coinfrog> {
	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart rightLeg;
	private final ModelPart rightLeg2;
	private final ModelPart leftLeg;
	private final ModelPart leftLeg2;

	public CoinfrogModel(ModelPart root) {
		this.root = root.getChild("root");
		this.body = this.root.getChild("body");
		this.rightLeg = body.getChild("rightLeg");
		this.rightLeg2 = body.getChild("rightLeg2");
		this.leftLeg = body.getChild("leftLeg");
		this.leftLeg2 = body.getChild("leftLeg2");
	}

	@SuppressWarnings("unused")
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, -1.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -1.0F, -3.5F, 6.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.5F));

		PartDefinition rightLeg = body.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, 0.0F, -2.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.5F, 0.5F, 0.0F, -0.3927F, 0.0F));

		PartDefinition rightLeg2 = body.addOrReplaceChild("rightLeg2", CubeListBuilder.create().texOffs(0, 9).addBox(0.0F, 0.0F, -0.5F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 0.5F, 3.0F));

		PartDefinition leftLeg = body.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, 0.0F, -2.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.5F, 0.5F, 0.0F, 0.3927F, 0.0F));

		PartDefinition leftLeg2 = body.addOrReplaceChild("leftLeg2", CubeListBuilder.create().texOffs(0, 9).mirror().addBox(-3.0F, 0.0F, -0.5F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-3.0F, 0.5F, 3.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Coinfrog entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.body.xRot = headPitch * ((float)Math.PI / 180F);
		this.body.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.leftLeg.yRot = 0.45F * Mth.sin(0.2F * ageInTicks) + 0.3927F;
		this.rightLeg.yRot = -0.45F * Mth.sin(0.2F * ageInTicks) - 0.3927F;
		this.leftLeg2.yRot = 0.45F * Mth.cos(0.2F * ageInTicks);
		this.rightLeg2.yRot = -0.45F * Mth.cos(0.2F * ageInTicks);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}