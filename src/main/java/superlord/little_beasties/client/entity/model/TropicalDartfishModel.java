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
import superlord.little_beasties.common.entity.TropicalDartfish;

public class TropicalDartfishModel extends EntityModel<TropicalDartfish> {
	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart rightSiphon;
	private final ModelPart leftSiphon;
	private final ModelPart dorsalFin;

	public TropicalDartfishModel(ModelPart root) {
		this.root = root.getChild("root");
		this.body = this.root.getChild("body");
		this.leftSiphon = body.getChild("leftSiphon");
		this.rightSiphon = body.getChild("rightSiphon");
		this.dorsalFin = body.getChild("dorsalFin");
	}

	@SuppressWarnings("unused")
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 22.5F, -0.5F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -1.5F, -4.5F, 3.0F, 3.0F, 9.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).mirror().addBox(0.0F, -0.5F, -8.5F, 0.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition rightSiphon = body.addOrReplaceChild("rightSiphon", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.5F, -0.5F, 0.0F, 0.0F, 0.1745F));

		PartDefinition leftSiphon = body.addOrReplaceChild("leftSiphon", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-1.0F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0F, 0.5F, -0.5F, 0.0F, 0.0F, -0.1745F));

		PartDefinition dorsalFin = body.addOrReplaceChild("dorsalFin", CubeListBuilder.create().texOffs(3, 6).mirror().addBox(0.0F, -2.0F, -2.0F, 0.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -1.5F, 2.5F));

		PartDefinition caudalFin = dorsalFin.addOrReplaceChild("caudalFin", CubeListBuilder.create().texOffs(0, 9).mirror().addBox(0.0F, 0.0F, 0.0F, 0.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 2.0F));

		PartDefinition analFin = caudalFin.addOrReplaceChild("analFin", CubeListBuilder.create().texOffs(3, 13).mirror().addBox(0.0F, 0.0F, -1.0F, 0.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 3.0F, -1.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(TropicalDartfish entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.rightSiphon.yScale = Mth.abs(Mth.sin(0.15F * ageInTicks) / 5.75F) + 1;
		this.rightSiphon.zScale = Mth.abs(Mth.sin(0.15F * ageInTicks) / 5.75F) + 1;
		this.leftSiphon.yScale = Mth.abs(Mth.sin(0.15F * ageInTicks) / 5.75F) + 1;
		this.leftSiphon.zScale = Mth.abs(Mth.sin(0.15F * ageInTicks) / 5.75F) + 1;
		this.dorsalFin.yRot = Mth.cos(0.25F * ageInTicks) / 5;
		this.dorsalFin.zRot = Mth.cos(0.25F * ageInTicks) / 5;
		this.body.xRot = headPitch * ((float)Math.PI / 180F);
		this.body.yRot = netHeadYaw * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}