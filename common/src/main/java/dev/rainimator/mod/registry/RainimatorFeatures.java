package dev.rainimator.mod.registry;

import dev.rainimator.mod.RainimatorMod;
import dev.rainimator.mod.impl.RainimatorRegistryManager;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.PlacedFeature;

public class RainimatorFeatures {
    public static final RegistryKey<PlacedFeature> RUBY_ORE = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(RainimatorMod.MOD_ID, "ruby_ore"));
    public static final RegistryKey<PlacedFeature> SAPPHIRE_ORE = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(RainimatorMod.MOD_ID, "sapphire_ore"));
    public static final RegistryKey<PlacedFeature> SUGILITE_ORE = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(RainimatorMod.MOD_ID, "sugilite_ore"));
    public static final RegistryKey<PlacedFeature> TOPAZ_ORE = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(RainimatorMod.MOD_ID, "topaz_ore"));
    public static final RegistryKey<PlacedFeature> MYSTIC_ORE = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(RainimatorMod.MOD_ID, "mystic_ore"));

    public static void init() {
        RainimatorRegistryManager.addFeatures();
    }
}
