package superlord.little_beasties.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import superlord.little_beasties.LittleBeasties;
import superlord.little_beasties.common.entity.BlueManefish;
import superlord.little_beasties.common.entity.Coinfrog;
import superlord.little_beasties.common.entity.CoinfrogTadpole;
import superlord.little_beasties.common.entity.DaydreamRay;
import superlord.little_beasties.common.entity.Mohomooho;
import superlord.little_beasties.common.entity.ProboscisFish;
import superlord.little_beasties.common.entity.Rainwitch;
import superlord.little_beasties.common.entity.Saildrifter;
import superlord.little_beasties.common.entity.Sealight;
import superlord.little_beasties.common.entity.SnappyWoolbug;
import superlord.little_beasties.common.entity.TropicalDartfish;
import superlord.little_beasties.common.entity.TropicalSeadragon;
import superlord.little_beasties.common.entity.WaveHornglider;

public class LBEntities {
	
	public static final DeferredRegister<EntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, LittleBeasties.MOD_ID);
	
	public static final RegistryObject<EntityType<TropicalDartfish>> TROPICAL_DARTFISH = REGISTER.register("tropical_dartfish", () -> EntityType.Builder.<TropicalDartfish>of(TropicalDartfish::new, MobCategory.WATER_AMBIENT).sized(0.5F, 0.1875F).build(new ResourceLocation(LittleBeasties.MOD_ID, "tropical_dartfish").toString()));
	public static final RegistryObject<EntityType<BlueManefish>> BLUE_MANEFISH = REGISTER.register("blue_manefish", () -> EntityType.Builder.<BlueManefish>of(BlueManefish::new, MobCategory.WATER_AMBIENT).sized(0.1875F, 0.1875F).build(new ResourceLocation(LittleBeasties.MOD_ID, "blue_manefish").toString()));
	public static final RegistryObject<EntityType<Saildrifter>> SAILDRIFTER = REGISTER.register("saildrifter", () -> EntityType.Builder.<Saildrifter>of(Saildrifter::new, MobCategory.WATER_AMBIENT).sized(0.375F, 0.375F).build(new ResourceLocation(LittleBeasties.MOD_ID, "saildrifter").toString()));
	public static final RegistryObject<EntityType<TropicalSeadragon>> TROPICAL_SEADRAGON = REGISTER.register("tropical_seadragon", () -> EntityType.Builder.<TropicalSeadragon>of(TropicalSeadragon::new, MobCategory.WATER_AMBIENT).sized(0.5F, 0.3125F).build(new ResourceLocation(LittleBeasties.MOD_ID, "tropical_seadragon").toString()));
	public static final RegistryObject<EntityType<ProboscisFish>> PROBOSCIS_FISH = REGISTER.register("proboscis_fish", () -> EntityType.Builder.<ProboscisFish>of(ProboscisFish::new, MobCategory.WATER_AMBIENT).sized(0.3125F, 0.5625F).build(new ResourceLocation(LittleBeasties.MOD_ID, "proboscis_fish").toString()));
	public static final RegistryObject<EntityType<WaveHornglider>> WAVE_HORNGLIDER = REGISTER.register("wave_hornglider", () -> EntityType.Builder.<WaveHornglider>of(WaveHornglider::new, MobCategory.WATER_AMBIENT).sized(1.3125F, 0.375F).build(new ResourceLocation(LittleBeasties.MOD_ID, "wave_hornglider").toString()));
	public static final RegistryObject<EntityType<Sealight>> SEA_LIGHT = REGISTER.register("sealight", () -> EntityType.Builder.<Sealight>of(Sealight::new, MobCategory.WATER_AMBIENT).sized(0.3125F, 0.5F).build(new ResourceLocation(LittleBeasties.MOD_ID, "sealight").toString()));
	public static final RegistryObject<EntityType<SnappyWoolbug>> SNAPPY_WOOLBUG = REGISTER.register("snappy_woolbug", () -> EntityType.Builder.<SnappyWoolbug>of(SnappyWoolbug::new, MobCategory.WATER_AMBIENT).sized(0.4375F, 0.4375F).build(new ResourceLocation(LittleBeasties.MOD_ID, "snappy_woolbug").toString()));
	public static final RegistryObject<EntityType<Rainwitch>> RAINWITCH = REGISTER.register("rainwitch", () -> EntityType.Builder.<Rainwitch>of(Rainwitch::new, MobCategory.WATER_AMBIENT).sized(0.1875F, 0.125F).build(new ResourceLocation(LittleBeasties.MOD_ID, "rainwitch").toString()));	
	public static final RegistryObject<EntityType<Coinfrog>> COINFROG = REGISTER.register("coinfrog", () -> EntityType.Builder.<Coinfrog>of(Coinfrog::new, MobCategory.WATER_AMBIENT).sized(0.4375F, 0.125F).build(new ResourceLocation(LittleBeasties.MOD_ID, "coinfrog").toString()));
	public static final RegistryObject<EntityType<CoinfrogTadpole>> COINFROG_TADPOLE = REGISTER.register("coinfrog_tadpole", () -> EntityType.Builder.<CoinfrogTadpole>of(CoinfrogTadpole::new, MobCategory.WATER_AMBIENT).sized(0.125F, 0.125F).build(new ResourceLocation(LittleBeasties.MOD_ID, "coinfrog_tadpole").toString()));
	public static final RegistryObject<EntityType<Mohomooho>> MOHOMOOHO = REGISTER.register("mohomooho", () -> EntityType.Builder.<Mohomooho>of(Mohomooho::new, MobCategory.WATER_AMBIENT).sized(0.4375F, 0.4375F).build(new ResourceLocation(LittleBeasties.MOD_ID, "mohomooho").toString()));
	public static final RegistryObject<EntityType<DaydreamRay>> DAYDREAM_RAY = REGISTER.register("daydream_ray", () -> EntityType.Builder.<DaydreamRay>of(DaydreamRay::new, MobCategory.WATER_AMBIENT).sized(0.875F, 0.3125F).build(new ResourceLocation(LittleBeasties.MOD_ID, "daydream_ray").toString()));
	
}
