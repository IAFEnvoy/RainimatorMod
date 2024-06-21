package dev.rainimator.mod.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.rainimator.mod.RainimatorMod;
import dev.rainimator.mod.effect.*;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.RegistryKeys;

import java.util.function.Supplier;

public class RainimatorEffects {
    public static final DeferredRegister<StatusEffect> REGISTRY = DeferredRegister.create(RainimatorMod.MOD_ID, RegistryKeys.STATUS_EFFECT);
    public static final RegistrySupplier<StatusEffect> FEAR_DARK = register("fear_dark", FeardarkMobEffect::new);
    public static final RegistrySupplier<StatusEffect> ICE_PEOPLE = register("ice_people", IcePeopleMobEffect::new);
    public static final RegistrySupplier<StatusEffect> SOUL_DEATH = register("soul_death", SoulDeathMobEffect::new);
    public static final RegistrySupplier<StatusEffect> PURIFICATION = register("purification", PurificationMobEffect::new);
    public static final RegistrySupplier<StatusEffect> STUNNED = register("stunned", StunnedMobEffect::new);
    public static final RegistrySupplier<StatusEffect> SHADOW_EROSION = register("shadow_erosion", ShadowErosionMobEffect::new);

    private static <T extends StatusEffect> RegistrySupplier<T> register(String name, Supplier<T> effect) {
        return REGISTRY.register(name, effect);
    }
}
