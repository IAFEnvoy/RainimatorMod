package dev.rainimator.mod.registry;

import dev.architectury.registry.level.biome.BiomeModifications;
import dev.rainimator.mod.RainimatorMod;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

public class RainimatorFeatures {
    public static final RegistryKey<PlacedFeature> RUBY_ORE = RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(RainimatorMod.MOD_ID, "ruby_ore"));
    public static final RegistryKey<PlacedFeature> SAPPHIRE_ORE = RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(RainimatorMod.MOD_ID, "sapphire_ore"));
    public static final RegistryKey<PlacedFeature> SUGILITE_ORE = RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(RainimatorMod.MOD_ID, "sugilite_ore"));
    public static final RegistryKey<PlacedFeature> TOPAZ_ORE = RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(RainimatorMod.MOD_ID, "topaz_ore"));
    public static final RegistryKey<PlacedFeature> MYSTIC_ORE = RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(RainimatorMod.MOD_ID, "mystic_ore"));

    public static void init() {
        BiomeModifications.addProperties(context -> context.hasTag(BiomeTags.IS_OVERWORLD), (context, mutable) -> {
            mutable.getGenerationProperties().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, RainimatorFeatures.RUBY_ORE);
            mutable.getGenerationProperties().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, RainimatorFeatures.SAPPHIRE_ORE);
            mutable.getGenerationProperties().addFeature(GenerationStep.Feature.UNDERGROUND_ORES, RainimatorFeatures.MYSTIC_ORE);
        });
        BiomeModifications.addProperties(context -> context.hasTag(BiomeTags.IS_END), (context, mutable) -> {
            mutable.getGenerationProperties().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, RainimatorFeatures.SUGILITE_ORE);
        });
        BiomeModifications.addProperties(context -> context.hasTag(BiomeTags.IS_NETHER), (context, mutable) -> {
            mutable.getGenerationProperties().addFeature(GenerationStep.Feature.UNDERGROUND_DECORATION, RainimatorFeatures.TOPAZ_ORE);
        });
    }
}
