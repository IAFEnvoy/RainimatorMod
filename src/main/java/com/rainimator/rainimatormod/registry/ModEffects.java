package com.rainimator.rainimatormod.registry;

import com.rainimator.rainimatormod.effect.*;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, "rainimator");
    public static final RegistryObject<MobEffect> FEARDARK = REGISTRY.register("feardark", FeardarkMobEffect::new);
    public static final RegistryObject<MobEffect> ICEPEOPLE = REGISTRY.register("icepeople", IcepeopleMobEffect::new);
    public static final RegistryObject<MobEffect> SOULDEATH = REGISTRY.register("souldeath", SouldeathMobEffect::new);
    public static final RegistryObject<MobEffect> PURIFICATION = REGISTRY.register("purification", PurificationMobEffect::new);
    public static final RegistryObject<MobEffect> STUNNED = REGISTRY.register("stunned", StunnedMobEffect::new);
    public static final RegistryObject<MobEffect> SHADOWEROSION = REGISTRY.register("shadowerosion", ShadowerosionMobEffect::new);
}
