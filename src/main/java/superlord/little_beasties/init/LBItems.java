package superlord.little_beasties.init;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import superlord.little_beasties.LittleBeasties;
import superlord.little_beasties.common.item.LBBucketItem;

public class LBItems {
	public static final DeferredRegister<Item> SPAWN_EGGS = DeferredRegister.create(ForgeRegistries.ITEMS, LittleBeasties.MOD_ID);
	public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, LittleBeasties.MOD_ID);

	public static final RegistryObject<Item> TROPICAL_DARTFISH_SPAWN_EGG = SPAWN_EGGS.register("tropical_dartfish_spawn_egg", () -> new ForgeSpawnEggItem(LBEntities.TROPICAL_DARTFISH, 0xE70D01, 0x5D78BE, new Item.Properties()));
	public static final RegistryObject<Item> BLUE_MANEFISH_SPAWN_EGG = SPAWN_EGGS.register("blue_manefish_spawn_egg", () -> new ForgeSpawnEggItem(LBEntities.BLUE_MANEFISH, 0x0A7BD1, 0x3A4782, new Item.Properties()));
	public static final RegistryObject<Item> SAILDRIFTER_SPAWN_EGG = SPAWN_EGGS.register("saildrifter_spawn_egg", () -> new ForgeSpawnEggItem(LBEntities.SAILDRIFTER, 0x46A2CC, 0x0F102D, new Item.Properties()));
	
	public static final RegistryObject<Item> TROPICAL_DARTFISH_BUCKET = REGISTER.register("tropical_dartfish_bucket", () -> new LBBucketItem(LBEntities.TROPICAL_DARTFISH, () -> Fluids.WATER, new Item.Properties().stacksTo(1)));
	public static final RegistryObject<Item> BLUE_MANEFISH_BUCKET = REGISTER.register("blue_manefish_bucket", () -> new LBBucketItem(LBEntities.BLUE_MANEFISH, () -> Fluids.WATER, new Item.Properties().stacksTo(1)));
	public static final RegistryObject<Item> SAILDRIFTER_BUCKET = REGISTER.register("saildrifter_bucket", () -> new LBBucketItem(LBEntities.SAILDRIFTER, () -> Fluids.WATER, new Item.Properties().stacksTo(1)));

}
