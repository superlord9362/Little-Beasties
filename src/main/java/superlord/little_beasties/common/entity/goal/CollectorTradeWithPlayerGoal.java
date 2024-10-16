package superlord.little_beasties.common.entity.goal;

import java.util.EnumSet;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import superlord.little_beasties.common.entity.Collector;

public class CollectorTradeWithPlayerGoal extends Goal {
	private final Collector collector;

	public CollectorTradeWithPlayerGoal(Collector collector) {
		this.collector = collector;
		this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
	}

	public boolean canUse() {
		if (!this.collector.isAlive()) {
			return false;
		} else if (this.collector.isInWater()) {
			return false;
		} else if (!this.collector.onGround()) {
			return false;
		} else if (this.collector.hurtMarked) {
			return false;
		} else {
			Player player = this.collector.getTradingPlayer();
			if (player == null) {
				return false;
			} else if (this.collector.distanceToSqr(player) > 16.0D) {
				return false;
			} else {
				return player.containerMenu != null;
			}
		}
	}
	
	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 20.0D).add(Attributes.MOVEMENT_SPEED, 0.2D);
	}

	public void start() {
		this.collector.getNavigation().stop();
	}

	public void stop() {
		this.collector.setTradingPlayer((Player)null);
	}
}
