package superlord.little_beasties.common.entity.goal;

import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.player.Player;
import superlord.little_beasties.common.entity.Collector;

public class CollectorLookAtTradingPlayerGoal extends LookAtPlayerGoal {
	private final Collector collector;

	public CollectorLookAtTradingPlayerGoal(Collector collector) {
		super(collector, Player.class, 8.0F);
		this.collector = collector;
	}

	public boolean canUse() {
		if (this.collector.isTrading()) {
			this.lookAt = this.collector.getTradingPlayer();
			return true;
		} else {
			return false;
		}
	}
}
