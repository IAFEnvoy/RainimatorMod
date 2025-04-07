package com.iafenvoy.rainimator.registry;

import com.iafenvoy.rainimator.RainimatorMod;
import com.iafenvoy.rainimator.enchantment.MagicProtectionEnchantment;
import com.iafenvoy.rainimator.enchantment.ManaRegenerationEnchantment;
import com.iafenvoy.rainimator.enchantment.ManaUpgradeEnchantment;
import com.iafenvoy.rainimator.enchantment.TetanusEnchantment;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKeys;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class RainimatorEnchantments {
    public static final DeferredRegister<Enchantment> REGISTRY = DeferredRegister.create(RainimatorMod.MOD_ID, RegistryKeys.ENCHANTMENT);
    public static final RegistrySupplier<Enchantment> MANA_REGENERATION = register("mana_regeneration", ManaRegenerationEnchantment::new);
    public static final RegistrySupplier<Enchantment> MANA_UPGRADE = register("mana_upgrade", ManaUpgradeEnchantment::new);
    public static final RegistrySupplier<Enchantment> MAGIC_PROTECTION = register("mana_protection", MagicProtectionEnchantment::new);
    public static final RegistrySupplier<Enchantment> TETANUS = register("tetanus", TetanusEnchantment::new);

    private static <T extends Enchantment> RegistrySupplier<T> register(String name, Supplier<T> enchantment) {
        return REGISTRY.register(name, enchantment);
    }
}
