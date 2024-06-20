package dev.rainimator.mod.impl;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.Heightmap;
import org.apache.commons.lang3.NotImplementedException;

import java.util.function.Predicate;

public class RainimatorRegistryManager {
    @ExpectPlatform
    public static void registerDefaultAttribute(EntityType<? extends LivingEntity> type, DefaultAttributeContainer.Builder builder) {
        throw new NotImplementedException("This method should be replaced by Architectury.");
    }

    @ExpectPlatform
    public static <T extends MobEntity> void registerSpawner(EntityType<T> type, SpawnRestriction.Location location, Heightmap.Type heightmapType, SpawnRestriction.SpawnPredicate<T> predicate) {
        throw new NotImplementedException("This method should be replaced by Architectury.");
    }

    @ExpectPlatform
    public static void addSpawnToBiome(Predicate<Identifier> biomeSelector, SpawnGroup spawnGroup, EntityType<?> entityType, int weight, int minGroupSize, int maxGroupSize) {
        throw new NotImplementedException("This method should be replaced by Architectury.");
    }

    @ExpectPlatform
    public static void addFeatures() {
        throw new NotImplementedException("This method should be replaced by Architectury.");
    }

    @Environment(EnvType.CLIENT)
    @ExpectPlatform
    public static <E extends Entity> void registerRenderer(EntityType<? extends E> entityType, EntityRendererFactory<E> entityRendererFactory) {
        throw new NotImplementedException("This method should be replaced by Architectury.");
    }
}
