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
import superlord.little_beasties.common.entity.WaveHornglider;

public class WaveHorngliderModel extends EntityModel<WaveHornglider> {
	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart rightWing;
	private final ModelPart leftWing;
	private final ModelPart tail;
	private final ModelPart tailFin;

	public WaveHorngliderModel(ModelPart root) {
		this.root = root.getChild("root");
		this.body = this.root.getChild("body");
		this.rightWing = body.getChild("rightWing");
		this.leftWing = body.getChild("leftWing");
		this.tail = body.getChild("tail");
		this.tailFin = tail.getChild("tailFin");
	}

	@SuppressWarnings("unused")
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 40).addBox(-9.0F, -3.0F, -10.5F, 18.0F, 6.0F, 21.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, -3.0F, 0.5F));

		PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(68, 0).addBox(-1.5F, 0.0F, -14.0F, 3.0F, 3.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(68, 0).mirror().addBox(11.5F, 0.0F, -14.0F, 3.0F, 3.0F, 14.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-6.5F, -3.0F, -7.5F, -0.3927F, 0.0F, 0.0F));

		PartDefinition rightWing = body.addOrReplaceChild("rightWing", CubeListBuilder.create().texOffs(0, 20).mirror().addBox(-1.0F, -1.0F, -2.0F, 25.0F, 2.0F, 18.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(9.0F, -1.0F, -5.5F));

		PartDefinition leftWing = body.addOrReplaceChild("leftWing", CubeListBuilder.create().texOffs(0, 20).addBox(-25.0F, -1.0F, -2.0F, 25.0F, 2.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(-9.0F, -1.0F, -5.5F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(52, 41).addBox(-2.0F, -2.0F, -1.0F, 4.0F, 4.0F, 26.0F, new CubeDeformation(0.0F))
		.texOffs(36, 71).addBox(2.0F, 0.0F, 0.0F, 5.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(36, 71).mirror().addBox(-7.0F, 0.0F, 0.0F, 5.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -1.0F, 10.5F));

		PartDefinition tailFin = tail.addOrReplaceChild("tailFin", CubeListBuilder.create().texOffs(0, 67).addBox(-5.0F, -0.5F, -1.0F, 10.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.5F, 20.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(WaveHornglider entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.body.xRot = headPitch * ((float)Math.PI / 180F);
		this.body.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.rightWing.zRot = Mth.sin(0.15F * ageInTicks) * 0.2F;
		this.leftWing.zRot = Mth.sin(0.15F * ageInTicks) * -0.2F;
		this.tail.xRot = Mth.sin(0.15F * ageInTicks) * 0.45F;
		this.tailFin.xRot = Mth.sin(0.15F * ageInTicks) * 0.2F;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}