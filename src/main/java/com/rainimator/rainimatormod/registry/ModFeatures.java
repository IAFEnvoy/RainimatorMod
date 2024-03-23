package com.rainimator.rainimatormod.registry;

import com.rainimator.rainimatormod.RainimatorMod;
import com.rainimator.rainimatormod.feature.DarkweryFeature;
import com.rainimator.rainimatormod.feature.PiglinTeamFeature;
import com.rainimator.rainimatormod.feature.SkillFeature;
import com.rainimator.rainimatormod.feature.SpeedFeature;
import com.rainimator.rainimatormod.feature.ore.*;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;


@EventBusSubscriber
public class ModFeatures {
    public static final DeferredRegister<Feature<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.FEATURES, RainimatorMod.MOD_ID);
    private static final List<FeatureRegistration> FEATURE_REGISTRATIONS = new ArrayList<>();
    public static final RegistryObject<Feature<?>> RUBY_ORE = register("ruby_ore", RubyOreFeature::feature, new FeatureRegistration(GenerationStep.Decoration.UNDERGROUND_ORES, RubyOreFeature.GENERATE_BIOMES, RubyOreFeature::placedFeature));
    public static final RegistryObject<Feature<?>> SAPPHIRE_ORE = register("sapphire_ore", SapphireOreFeature::feature, new FeatureRegistration(GenerationStep.Decoration.UNDERGROUND_ORES, SapphireOreFeature.GENERATE_BIOMES, SapphireOreFeature::placedFeature));
    public static final RegistryObject<Feature<?>> DEEPSLATE_SAPPHIRE_ORE = register("deepslate_sapphire_ore", DeepslateSapphireOreFeature::feature, new FeatureRegistration(GenerationStep.Decoration.UNDERGROUND_ORES, DeepslateSapphireOreFeature.GENERATE_BIOMES, DeepslateSapphireOreFeature::placedFeature));
    public static final RegistryObject<Feature<?>> DEEPSLATE_RUBY_ORE = register("deepslate_ruby_ore", DeepslateRubyOreFeature::feature, new FeatureRegistration(GenerationStep.Decoration.UNDERGROUND_ORES, DeepslateRubyOreFeature.GENERATE_BIOMES, DeepslateRubyOreFeature::placedFeature));
    public static final RegistryObject<Feature<?>> DARK_WERY = register("dark_wery", DarkweryFeature::feature, new FeatureRegistration(GenerationStep.Decoration.SURFACE_STRUCTURES, DarkweryFeature.GENERATE_BIOMES, DarkweryFeature::placedFeature));
    public static final RegistryObject<Feature<?>> MYSTIC_ORE = register("mystic_ore", MysticoreFeature::feature, new FeatureRegistration(GenerationStep.Decoration.UNDERGROUND_ORES, MysticoreFeature.GENERATE_BIOMES, MysticoreFeature::placedFeature));
    public static final RegistryObject<Feature<?>> PIGLIN_TEAM = register("piglin_team", PiglinTeamFeature::feature, new FeatureRegistration(GenerationStep.Decoration.SURFACE_STRUCTURES, PiglinTeamFeature.GENERATE_BIOMES, PiglinTeamFeature::placedFeature));
    public static final RegistryObject<Feature<?>> SPEED = register("speed", SpeedFeature::feature, new FeatureRegistration(GenerationStep.Decoration.SURFACE_STRUCTURES, SpeedFeature.GENERATE_BIOMES, SpeedFeature::placedFeature));
    public static final RegistryObject<Feature<?>> SKILL = register("skill", SkillFeature::feature, new FeatureRegistration(GenerationStep.Decoration.SURFACE_STRUCTURES, SkillFeature.GENERATE_BIOMES, SkillFeature::placedFeature));

    private static RegistryObject<Feature<?>> register(String registryName, Supplier<Feature<?>> feature, FeatureRegistration featureRegistration) {
        FEATURE_REGISTRATIONS.add(featureRegistration);
        return REGISTRY.register(registryName, feature);
    }

    @SubscribeEvent
    public static void addFeaturesToBiomes(BiomeLoadingEvent event) {
        for (FeatureRegistration registration : FEATURE_REGISTRATIONS)
            if (registration.biomes() == null || registration.biomes().contains(event.getName()))
                event.getGeneration().getFeatures(registration.stage()).add(registration.placedFeature().get());
    }

    private record FeatureRegistration(GenerationStep.Decoration stage, Set<ResourceLocation> biomes,
                                       Supplier<Holder<PlacedFeature>> placedFeature) {
    }
}
