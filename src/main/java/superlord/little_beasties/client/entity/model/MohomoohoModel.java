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
import superlord.little_beasties.common.entity.Mohomooho;

public class MohomoohoModel extends EntityModel<Mohomooho> {
	private final ModelPart root;
	private final ModelPart bone;
	private final ModelPart caudalFin;
	private final ModelPart leftFin;
	private final ModelPart rightFin;

	public MohomoohoModel(ModelPart root) {
		this.root = root.getChild("root");
		this.bone = this.root.getChild("bone");
		this.caudalFin = bone.getChild("caudalFin");
		this.leftFin = bone.getChild("leftFin");
		this.rightFin = bone.getChild("rightFin");
	}

	@SuppressWarnings("unused")
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition bone = root.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -7.0F, -3.0F, 7.0F, 7.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(7, 18).addBox(2.0F, -7.0F, -2.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(7, 18).mirror().addBox(-8.0F, -7.0F, -2.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 0).addBox(-8.0F, -9.0F, -2.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(3.0F, -9.0F, -2.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 30).addBox(2.0F, -7.0F, -4.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 30).mirror().addBox(-8.0F, -7.0F, -4.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 39).addBox(2.0F, -9.0F, -4.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 39).mirror().addBox(-8.0F, -9.0F, -4.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(28, 1).addBox(2.0F, -9.0F, -2.0F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(28, 1).mirror().addBox(-7.0F, -9.0F, -2.0F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(50, 2).addBox(2.0F, -7.0F, -2.0F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(50, 2).mirror().addBox(-9.0F, -7.0F, -2.0F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition jaw = bone.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(0, 24).addBox(-2.5F, -3.5F, -1.5F, 5.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, -0.5F, -2.5F));

		PartDefinition caudalFin = bone.addOrReplaceChild("caudalFin", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 0.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, -7.0F, 8.0F));

		PartDefinition analFin = bone.addOrReplaceChild("analFin", CubeListBuilder.create().texOffs(0, 13).addBox(0.0F, 0.0F, -2.5F, 0.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 0.0F, 5.5F));

		PartDefinition rightFin = bone.addOrReplaceChild("rightFin", CubeListBuilder.create().texOffs(0, 18).addBox(0.0F, -1.0F, 0.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -2.0F, 3.0F, 0.0F, 0.3927F, 0.0F));

		PartDefinition leftFin = bone.addOrReplaceChild("leftFin", CubeListBuilder.create().texOffs(0, 18).addBox(0.0F, -1.0F, 0.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -2.0F, 3.0F, 0.0F, -0.3927F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Mohomooho entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.bone.xRot = headPitch * ((float)Math.PI / 180F);
		this.bone.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.caudalFin.yRot = 0.45F * Mth.sin(0.6F * ageInTicks);
		this.rightFin.yRot = -Mth.abs(0.45F * Mth.sin(0.15F * ageInTicks)) + 1.1781F;
		this.leftFin.yRot = Mth.abs(-0.45F * Mth.sin(0.15F * ageInTicks)) - 1.1781F;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}