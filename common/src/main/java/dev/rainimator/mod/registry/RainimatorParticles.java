package dev.rainimator.mod.registry;

import dev.architectury.registry.client.particle.ParticleProviderRegistry;
import dev.rainimator.mod.RainimatorMod;
import dev.rainimator.mod.particle.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class RainimatorParticles {
    public static final DefaultParticleType PURPLE_LIGHT = register("purple_light", new DefaultParticleType(false) {
    });
    public static final DefaultParticleType RED_FLOWER = register("red_flower", new DefaultParticleType(false) {
    });
    public static final DefaultParticleType SNOW = register("snow", new DefaultParticleType(false) {
    });
    public static final DefaultParticleType LIGHTING_1 = register("lightening_1", new DefaultParticleType(false) {
    });
    public static final DefaultParticleType LIGHTING_2 = register("lightening_2", new DefaultParticleType(false) {
    });
    public static final DefaultParticleType YELLOW_LIGHTENING = register("yellow_lightening", new DefaultParticleType(false) {
    });
    public static final DefaultParticleType LIGHTENING_ARC = register("lightening_arc", new DefaultParticleType(false) {
    });
    public static final DefaultParticleType ENDER_DAGGER = register("ender_dagger", new DefaultParticleType(false) {
    });
    public static final DefaultParticleType WHITE_CIRCLE = register("white_circle", new DefaultParticleType(false) {
    });
    public static final DefaultParticleType DARK_CIRCLE = register("dark_circle", new DefaultParticleType(false) {
    });
    public static final DefaultParticleType FLOWER_WHITE = register("flower_white", new DefaultParticleType(false) {
    });
    public static final DefaultParticleType SWEATER_SNOW = register("sweater_snow", new DefaultParticleType(false) {
    });
    public static final DefaultParticleType YELLOW_STARS = register("yellow_stars", new DefaultParticleType(false) {
    });

    private static DefaultParticleType register(String name, DefaultParticleType obj) {
        return Registry.register(Registries.PARTICLE_TYPE, new Identifier(RainimatorMod.MOD_ID, name), obj);
    }

    @Environment(EnvType.CLIENT)
    public static void registerParticles() {
        ParticleProviderRegistry.register(RainimatorParticles.PURPLE_LIGHT, PurpleLightParticle::provider);
        ParticleProviderRegistry.register(RainimatorParticles.RED_FLOWER, RedFlowerParticle::provider);
        ParticleProviderRegistry.register(RainimatorParticles.SNOW, SnowParticle::provider);
        ParticleProviderRegistry.register(RainimatorParticles.LIGHTING_1, Lighting1Particle::provider);
        ParticleProviderRegistry.register(RainimatorParticles.LIGHTING_2, Lighting2Particle::provider);
        ParticleProviderRegistry.register(RainimatorParticles.YELLOW_LIGHTENING, YellowLightingParticle::provider);
        ParticleProviderRegistry.register(RainimatorParticles.LIGHTENING_ARC, LightingArcParticle::provider);
        ParticleProviderRegistry.register(RainimatorParticles.ENDER_DAGGER, EnderDaggerParticle::provider);
        ParticleProviderRegistry.register(RainimatorParticles.WHITE_CIRCLE, WhiteCircleParticle::provider);
        ParticleProviderRegistry.register(RainimatorParticles.DARK_CIRCLE, DarkCircleParticle::provider);
        ParticleProviderRegistry.register(RainimatorParticles.FLOWER_WHITE, FlowerWriteParticle::provider);
        ParticleProviderRegistry.register(RainimatorParticles.SWEATER_SNOW, SweaterSnowParticle::provider);
        ParticleProviderRegistry.register(RainimatorParticles.YELLOW_STARS, YellowStarsParticle::provider);
    }
}

