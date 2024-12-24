package superlord.little_beasties.common.particle;

import java.util.function.Consumer;

import org.joml.Quaternionf;
import org.joml.Vector3f;

import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ImpactParticle extends TextureSheetParticle {
	private static final Vector3f TRANSFORM_VECTOR = new Vector3f(-1.0F, -1.0F, 0.0F);
	private int delay;

	ImpactParticle(ClientLevel p_233976_, double p_233977_, double p_233978_, double p_233979_, int p_233980_) {
		super(p_233976_, p_233977_, p_233978_, p_233979_, 0.0D, 0.0D, 0.0D);
		this.quadSize = 10.85F;
		this.delay = p_233980_;
		this.lifetime = 60;
		this.gravity = 0.0F;
		this.xd = 0.0D;
		this.yd = 0.0D;
		this.zd = 0.0D;
	}

	public float getQuadSize(float p_234003_) {
		return this.quadSize * Mth.clamp(((float)this.age + p_234003_) / (float)this.lifetime * 1.75F, 0.0F, 5.0F);
	}

	public void render(VertexConsumer p_233985_, Camera p_233986_, float p_233987_) {
		if (this.delay <= 0) {
			this.alpha = 1.0F - Mth.clamp(((float)this.age + p_233987_) / (float)this.lifetime, 0.0F, 1.0F);
			this.renderRotatedParticle(p_233985_, p_233986_, p_233987_, (p_253347_) -> {
				p_253347_.mul((new Quaternionf()).rotationX(-1.6F));
			});
			this.renderRotatedParticle(p_233985_, p_233986_, p_233987_, (p_253346_) -> {
				p_253346_.mul((new Quaternionf()).rotationYXZ(-(float)Math.PI, 1.6F, 0.0F));
			});
		}
	}

	private void renderRotatedParticle(VertexConsumer p_233989_, Camera p_233990_, float p_233991_, Consumer<Quaternionf> p_233992_) {
		Vec3 vec3 = p_233990_.getPosition();
		float f = (float)(Mth.lerp((double)p_233991_, this.xo, this.x) - vec3.x());
		float f1 = (float)(Mth.lerp((double)p_233991_, this.yo, this.y) - vec3.y());
		float f2 = (float)(Mth.lerp((double)p_233991_, this.zo, this.z) - vec3.z());
		Quaternionf quaternionf = (new Quaternionf()).setAngleAxis(0.0F, 0, 0, 0);
		p_233992_.accept(quaternionf);
		quaternionf.transform(TRANSFORM_VECTOR);
		Vector3f[] avector3f = new Vector3f[]{new Vector3f(-1.0F, -1.0F, 0.0F), new Vector3f(-1.0F, 1.0F, 0.0F), new Vector3f(1.0F, 1.0F, 0.0F), new Vector3f(1.0F, -1.0F, 0.0F)};
		float f3 = this.getQuadSize(p_233991_);

		for(int i = 0; i < 4; ++i) {
			Vector3f vector3f = avector3f[i];
			vector3f.rotate(quaternionf);
			vector3f.mul(f3);
			vector3f.add(f, f1, f2);
		}

		int j = this.getLightColor(p_233991_);
		this.makeCornerVertex(p_233989_, avector3f[0], this.getU1(), this.getV1(), j);
		this.makeCornerVertex(p_233989_, avector3f[1], this.getU1(), this.getV0(), j);
		this.makeCornerVertex(p_233989_, avector3f[2], this.getU0(), this.getV0(), j);
		this.makeCornerVertex(p_233989_, avector3f[3], this.getU0(), this.getV1(), j);
	}

	private void makeCornerVertex(VertexConsumer p_254493_, Vector3f p_253752_, float p_254250_, float p_254047_, int p_253814_) {
		p_254493_.vertex((double)p_253752_.x(), (double)p_253752_.y(), (double)p_253752_.z()).uv(p_254250_, p_254047_).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(p_253814_).endVertex();
	}

	public int getLightColor(float p_233983_) {
		return 240;
	}

	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
	}

	public void tick() {
		if (this.delay > 0) {
			--this.delay;
		} else {
			super.tick();
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class Provider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet sprite;

		public Provider(SpriteSet p_234008_) {
			this.sprite = p_234008_;
		}

		public Particle createParticle(SimpleParticleType p_234019_, ClientLevel p_234020_, double p_234021_, double p_234022_, double p_234023_, double p_234024_, double p_234025_, double p_234026_) {
			ImpactParticle impactParticle = new ImpactParticle(p_234020_, p_234021_, p_234022_, p_234023_, 0);
			impactParticle.pickSprite(this.sprite);
			impactParticle.setAlpha(1.0F);
			return impactParticle;
		}
	}
}