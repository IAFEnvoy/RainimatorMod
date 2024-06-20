package dev.rainimator.mod.impl.fabric;

import dev.rainimator.mod.registry.RainimatorFeatures;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.GenerationStep;

import java.util.function.Predicate;

public class RainimatorRegistryManagerImpl {
    public static void registerDefaultAttribute(EntityType<? extends LivingEntity> type, DefaultAttributeContainer.Builder builder) {
        FabricDefaultAttributeRegistry.register(type, builder);
    }

    public static <T extends MobEntity> void registerSpawner(EntityType<T> type, SpawnRestriction.Location location, Heightmap.Type heightmapType, SpawnRestriction.SpawnPredicate<T> predicate) {
        SpawnRestriction.register(type, location, heightmapType, predicate);
    }

    public static void addSpawnToBiome(Predicate<Identifier> biomeSelector, SpawnGroup spawnGroup, EntityType<?> entityType, int weight, int minGroupSize, int maxGroupSize) {
        BiomeModifications.addSpawn(context -> biomeSelector.test(context.getBiomeKey().getValue()), spawnGroup, entityType, weight, minGroupSize, maxGroupSize);
    }

    public static void addFeatures() {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_DECORATION, RainimatorFeatures.RUBY_ORE);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, RainimatorFeatures.SAPPHIRE_ORE);
        BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(), GenerationStep.Feature.UNDERGROUND_ORES, RainimatorFeatures.SUGILITE_ORE);
        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Feature.UNDERGROUND_ORES, RainimatorFeatures.TOPAZ_ORE);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, RainimatorFeatures.MYSTIC_ORE);
    }

    @Environment(EnvType.CLIENT)
    public static <E extends Entity> void registerRenderer(EntityType<? extends E> entityType, EntityRendererFactory<E> entityRendererFactory) {
        EntityRendererRegistry.register(entityType, entityRendererFactory);
    }
}
