package dev.rainimator.mod.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.rainimator.mod.RainimatorMod;
import dev.rainimator.mod.item.enchantment.MagicProtection;
import dev.rainimator.mod.item.enchantment.ManaRegeneration;
import dev.rainimator.mod.item.enchantment.ManaUpgrade;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKeys;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class RainimatorEnchantments {
    public static final DeferredRegister<Enchantment> REGISTRY = DeferredRegister.create(RainimatorMod.MOD_ID, RegistryKeys.ENCHANTMENT);
    public static final RegistrySupplier<Enchantment> MANA_REGENERATION = register("mana_regeneration", ManaRegeneration::new);
    public static final RegistrySupplier<Enchantment> MANA_UPGRADE = register("mana_upgrade", ManaUpgrade::new);
    public static final RegistrySupplier<Enchantment> MAGIC_PROTECTION = register("mana_protection", MagicProtection::new);

    private static <T extends Enchantment> RegistrySupplier<T> register(String name, Supplier<T> enchantment) {
        return REGISTRY.register(name, enchantment);
    }
}
