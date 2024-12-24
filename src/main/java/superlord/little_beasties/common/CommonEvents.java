package superlord.little_beasties.common;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import superlord.little_beasties.LittleBeasties;
import superlord.little_beasties.common.entity.goal.FollowWaveHorngliderGoal;

@Mod.EventBusSubscriber(modid = LittleBeasties.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonEvents {

	@SubscribeEvent
	public static void onLootLoad(LootTableLoadEvent event) {
		ResourceLocation name = event.getName();
		LootPool pool = event.getTable().getPool("main");
		if (name.equals(BuiltInLootTables.FISHING_FISH)) {
			addEntry(pool, getInjectEntry(new ResourceLocation(LittleBeasties.MOD_ID, "inject/fishing"), 10, 1));
		}
	}

	private static LootPoolEntryContainer getInjectEntry(ResourceLocation location, int weight, int quality) {
		return LootTableReference.lootTableReference(location).setWeight(weight).setQuality(quality).build();
	}

	private static void addEntry(LootPool pool, LootPoolEntryContainer entry) {
		LootPoolEntryContainer[] newEntries = new LootPoolEntryContainer[pool.entries.length + 1];
		System.arraycopy(pool.entries, 0, newEntries, 0, pool.entries.length);
		newEntries[pool.entries.length] = entry;
		pool.entries = newEntries;
	}
	
	@SubscribeEvent
	public void onEntityJoinWorld(MobSpawnEvent.FinalizeSpawn  event) {
		try {
			if (event.getEntity() instanceof AbstractFish fish) {
				fish.goalSelector.addGoal(3, new FollowWaveHorngliderGoal(fish));
			}
		} catch (Exception e) {
			LittleBeasties.LOGGER.warn("Could not allow Fish to follow Wave Horngliders!");
		}
	}

}
