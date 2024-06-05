package superlord.little_beasties.client.entity.render;

import javax.annotation.Nullable;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import superlord.little_beasties.LittleBeasties;
import superlord.little_beasties.client.ClientEvents;
import superlord.little_beasties.client.entity.model.SaildrifterModel;
import superlord.little_beasties.common.entity.Saildrifter;

public class SaildrifterRenderer extends MobRenderer<Saildrifter, EntityModel<Saildrifter>> {

	private static final ResourceLocation BIO_LUMO = new ResourceLocation(LittleBeasties.MOD_ID, "textures/entity/saildrifter/bio_lumo.png");
	private static final ResourceLocation BLUE_NEON = new ResourceLocation(LittleBeasties.MOD_ID, "textures/entity/saildrifter/blue_neon.png");
	private static final ResourceLocation DEEP_KELP = new ResourceLocation(LittleBeasties.MOD_ID, "textures/entity/saildrifter/deep_kelp.png");
	private static final ResourceLocation FLAMBOYANT = new ResourceLocation(LittleBeasties.MOD_ID, "textures/entity/saildrifter/flamboyant.png");
	private static final ResourceLocation HADAL = new ResourceLocation(LittleBeasties.MOD_ID, "textures/entity/saildrifter/hadal.png");
	private static final ResourceLocation KELP = new ResourceLocation(LittleBeasties.MOD_ID, "textures/entity/saildrifter/kelp.png");
	private static final ResourceLocation MASKED = new ResourceLocation(LittleBeasties.MOD_ID, "textures/entity/saildrifter/masked.png");
	private static final ResourceLocation NEON = new ResourceLocation(LittleBeasties.MOD_ID, "textures/entity/saildrifter/neon.png");
	private static final ResourceLocation SPRAY = new ResourceLocation(LittleBeasties.MOD_ID, "textures/entity/saildrifter/spray.png");
	private static final ResourceLocation SUN_MASKED = new ResourceLocation(LittleBeasties.MOD_ID, "textures/entity/saildrifter/sun_masked.png");
	private static final ResourceLocation SUNNY_KELP = new ResourceLocation(LittleBeasties.MOD_ID, "textures/entity/saildrifter/sunny_kelp.png");
	private static final ResourceLocation TRI_BANDED = new ResourceLocation(LittleBeasties.MOD_ID, "textures/entity/saildrifter/tri_banded.png");
	private static final ResourceLocation WILD = new ResourceLocation(LittleBeasties.MOD_ID, "textures/entity/saildrifter/wild.png");
	private static final ResourceLocation WILD_BLUE = new ResourceLocation(LittleBeasties.MOD_ID, "textures/entity/saildrifter/wild_blue.png");
	private static final ResourceLocation WILD_SPRAY = new ResourceLocation(LittleBeasties.MOD_ID, "textures/entity/saildrifter/wild_spray.png");
	private static final ResourceLocation WILD_WAVE = new ResourceLocation(LittleBeasties.MOD_ID, "textures/entity/saildrifter/wild_wave.png");

	public SaildrifterRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new SaildrifterModel(renderManager.bakeLayer(ClientEvents.SAILDRIFTER)), 0.1875F);
	}
	
	public ResourceLocation getTextureLocation(Saildrifter entity) {
		if (entity.getColor() == 0) return BIO_LUMO;
		if (entity.getColor() == 1) return BLUE_NEON;
		if (entity.getColor() == 2) return DEEP_KELP;
		if (entity.getColor() == 3) return FLAMBOYANT;
		if (entity.getColor() == 4) return HADAL;
		if (entity.getColor() == 5) return KELP;
		if (entity.getColor() == 6) return MASKED;
		if (entity.getColor() == 7) return NEON;
		if (entity.getColor() == 8) return SPRAY;
		if (entity.getColor() == 9) return SUN_MASKED;
		if (entity.getColor() == 10) return SUNNY_KELP;
		if (entity.getColor() == 11) return TRI_BANDED;
		if (entity.getColor() == 12) return WILD;
		if (entity.getColor() == 13) return WILD_BLUE;
		if (entity.getColor() == 14) return WILD_SPRAY;
		else return WILD_WAVE;
	}
	
	@Nullable
	protected RenderType getRenderType(Saildrifter p_113806_, boolean p_113807_, boolean p_113808_, boolean p_113809_) {
		ResourceLocation resourcelocation = this.getTextureLocation(p_113806_);
		return RenderType.entityTranslucent(resourcelocation, false);
	}
	
}
