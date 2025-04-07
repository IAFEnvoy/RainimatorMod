package com.iafenvoy.rainimator.registry;

import com.iafenvoy.rainimator.RainimatorMod;
import com.iafenvoy.rainimator.effect.*;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.RegistryKeys;

import java.util.function.Supplier;

public class RainimatorEffects {
    public static final DeferredRegister<StatusEffect> REGISTRY = DeferredRegister.create(RainimatorMod.MOD_ID, RegistryKeys.STATUS_EFFECT);
    public static final RegistrySupplier<StatusEffect> FEAR_DARK = register("fear_dark", FearDarkEffect::new);
    public static final RegistrySupplier<StatusEffect> ICE_PEOPLE = register("ice_people", IcePeopleEffect::new);
    public static final RegistrySupplier<StatusEffect> SOUL_DEATH = register("soul_death", SoulDeathEffect::new);
    public static final RegistrySupplier<StatusEffect> PURIFICATION = register("purification", PurificationEffect::new);
    public static final RegistrySupplier<StatusEffect> STUNNED = register("stunned", StunnedEffect::new);
    public static final RegistrySupplier<StatusEffect> SHADOW_EROSION = register("shadow_erosion", ShadowErosionEffect::new);
    public static final RegistrySupplier<StatusEffect> WATCHERS_PROVIDENCE = register("watchers_providence", WatchersProvidenceEffect::new);
    public static final RegistrySupplier<StatusEffect> WITHER_HEART_CURSE = register("wither_heart_curse", WitherHeartCurseEffect::new);

    private static <T extends StatusEffect> RegistrySupplier<T> register(String name, Supplier<T> effect) {
        return REGISTRY.register(name, effect);
    }
}
