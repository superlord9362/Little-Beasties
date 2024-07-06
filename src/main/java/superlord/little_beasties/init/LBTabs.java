package superlord.little_beasties.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import superlord.little_beasties.LittleBeasties;

public class LBTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTER = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, LittleBeasties.MOD_ID);
	
	public static final RegistryObject<CreativeModeTab> ITEM_GROUP = REGISTER.register("little_beasties_item_group", () -> CreativeModeTab.builder().icon(() -> new ItemStack(LBItems.TROPICAL_DARTFISH_BUCKET.get()))
			.title(Component.translatable("itemGroup.little_beasties_item_group"))
			.displayItems((pParameters, pOutput) -> {
				for (var block : LBItems.REGISTER.getEntries()) {
					pOutput.accept(block.get());
				}
			}).build());
	
	public static final RegistryObject<CreativeModeTab> SPAWN_EGG_GROUP = REGISTER.register("little_beasties_spawn_item_group", () -> CreativeModeTab.builder().icon(() -> new ItemStack(LBItems.TROPICAL_DARTFISH_SPAWN_EGG.get()))
			.title(Component.translatable("itemGroup.little_beasties_spawn_item_group"))
			.displayItems((pParameters, pOutput) -> {
				for (var block : LBItems.SPAWN_EGGS.getEntries()) {
					pOutput.accept(block.get());
				}
			}).build());
	
	public static final RegistryObject<CreativeModeTab> BLOCKS_GROUP = REGISTER.register("little_beasties_block_item_group", () -> CreativeModeTab.builder().icon(() -> new ItemStack(LBItems.MARINE_CLAY.get()))
			.title(Component.translatable("itemGroup.little_beasties_block_item_group"))
			.displayItems((pParameters, pOutput) -> {
				for (var block : LBItems.BLOCKS.getEntries()) {
					pOutput.accept(block.get());
				}
			}).build());
	
}
