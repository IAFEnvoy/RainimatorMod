package dev.rainimator.mod.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.rainimator.mod.RainimatorMod;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.registry.RegistryKeys;

import java.util.function.Supplier;

public class RainimatorPotions {
    public static final DeferredRegister<Potion> REGISTRY = DeferredRegister.create(RainimatorMod.MOD_ID, RegistryKeys.POTION);
    public static final RegistrySupplier<Potion> PURIFICATION_POTION = register("purification", () -> new Potion("purification", new StatusEffectInstance(RainimatorEffects.PURIFICATION.get(), 3000, 0)));

    private static RegistrySupplier<Potion> register(String name, Supplier<Potion> potion) {
        return REGISTRY.register(name, potion);
    }
}
