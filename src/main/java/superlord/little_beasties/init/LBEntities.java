package superlord.little_beasties.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import superlord.little_beasties.LittleBeasties;
import superlord.little_beasties.common.entity.BlueManefish;
import superlord.little_beasties.common.entity.Saildrifter;
import superlord.little_beasties.common.entity.TropicalDartfish;
import superlord.little_beasties.common.entity.TropicalSeadragon;

public class LBEntities {
	
	public static final DeferredRegister<EntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, LittleBeasties.MOD_ID);
	
	public static final RegistryObject<EntityType<TropicalDartfish>> TROPICAL_DARTFISH = REGISTER.register("tropical_dartfish", () -> EntityType.Builder.<TropicalDartfish>of(TropicalDartfish::new, MobCategory.WATER_AMBIENT).sized(0.5F, 0.1875F).build(new ResourceLocation(LittleBeasties.MOD_ID, "tropical_dartfish").toString()));
	public static final RegistryObject<EntityType<BlueManefish>> BLUE_MANEFISH = REGISTER.register("blue_manefish", () -> EntityType.Builder.<BlueManefish>of(BlueManefish::new, MobCategory.WATER_AMBIENT).sized(0.1875F, 0.1875F).build(new ResourceLocation(LittleBeasties.MOD_ID, "blue_manefish").toString()));
	public static final RegistryObject<EntityType<Saildrifter>> SAILDRIFTER = REGISTER.register("saildrifter", () -> EntityType.Builder.<Saildrifter>of(Saildrifter::new, MobCategory.WATER_AMBIENT).sized(0.375F, 0.1875F).build(new ResourceLocation(LittleBeasties.MOD_ID, "saildrifter").toString()));
	public static final RegistryObject<EntityType<TropicalSeadragon>> TROPICAL_SEADRAGON = REGISTER.register("tropical_seadragon", () -> EntityType.Builder.<TropicalSeadragon>of(TropicalSeadragon::new, MobCategory.WATER_AMBIENT).sized(0.25F, 0.25F).build(new ResourceLocation(LittleBeasties.MOD_ID, "tropical_seadragon").toString()));
	
}
