package dev.rainimator.mod.util;

import net.minecraft.particle.ParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

public class ParticleUtil {
    public static void spawnCircleParticles(World level, ParticleEffect particle, double x, double y, double z, double r, double angle, double num) {
        if (level instanceof ServerWorld) {
            ServerWorld _level = (ServerWorld) level;
            for (int index0 = 0; index0 < (int) num; index0++) {
                _level.spawnParticles(particle, x + r * Math.cos(angle), y + 1.0D, z + r * Math.sin(angle), 1, 0.0D, 0.0D, 0.0D, 0.0D);
                angle += 360.0D / num;
            }
        }
    }
}
