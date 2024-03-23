package com.rainimator.rainimatormod.registry;

import com.rainimator.rainimatormod.RainimatorMod;
import com.rainimator.rainimatormod.effect.*;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, RainimatorMod.MOD_ID);
    public static final RegistryObject<MobEffect> FEAR_DARK = REGISTRY.register("fear_dark", FeardarkMobEffect::new);
    public static final RegistryObject<MobEffect> ICE_PEOPLE = REGISTRY.register("ice_people", IcePeopleMobEffect::new);
    public static final RegistryObject<MobEffect> SOUL_DEATH = REGISTRY.register("soul_death", SoulDeathMobEffect::new);
    public static final RegistryObject<MobEffect> PURIFICATION = REGISTRY.register("purification", PurificationMobEffect::new);
    public static final RegistryObject<MobEffect> STUNNED = REGISTRY.register("stunned", StunnedMobEffect::new);
    public static final RegistryObject<MobEffect> SHADOW_EROSION = REGISTRY.register("shadow_erosion", ShadowErosionMobEffect::new);
}
