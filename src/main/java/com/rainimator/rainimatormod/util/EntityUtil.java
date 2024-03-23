package com.rainimator.rainimatormod.util;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.phys.Vec3;

public class EntityUtil {
    public static <M extends Mob> void summon(EntityType<M> entityType, ServerLevel world, Vec3 vec3) {
        summon(entityType, world, vec3.x, vec3.y, vec3.z);
    }

    public static <M extends Mob> void summon(EntityType<M> entityType, ServerLevel world, double x, double y, double z) {
        Mob entityToSpawn = entityType.create(world);
        if (entityToSpawn != null) {
            entityToSpawn.moveTo(x, y, z, world.getRandom().nextFloat() * 360F, 0);
            entityToSpawn.finalizeSpawn(world, world.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
            world.addFreshEntity(entityToSpawn);
        }
    }
}
