package dev.rainimator.mod.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.rainimator.mod.RainimatorMod;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleType;
import net.minecraft.registry.RegistryKeys;

import java.util.function.Supplier;

public class RainimatorParticles {
    public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(RainimatorMod.MOD_ID, RegistryKeys.PARTICLE_TYPE);

    public static final RegistrySupplier<DefaultParticleType> PURPLE_LIGHT = register("purple_light", () -> new DefaultParticleType(false) {
    });
    public static final RegistrySupplier<DefaultParticleType> RED_FLOWER = register("red_flower", () -> new DefaultParticleType(false) {
    });
    public static final RegistrySupplier<DefaultParticleType> SNOW = register("snow", () -> new DefaultParticleType(false) {
    });
    public static final RegistrySupplier<DefaultParticleType> LIGHTING_1 = register("lightening_1", () -> new DefaultParticleType(false) {
    });
    public static final RegistrySupplier<DefaultParticleType> LIGHTING_2 = register("lightening_2", () -> new DefaultParticleType(false) {
    });
    public static final RegistrySupplier<DefaultParticleType> YELLOW_LIGHTENING = register("yellow_lightening", () -> new DefaultParticleType(false) {
    });
    public static final RegistrySupplier<DefaultParticleType> LIGHTENING_ARC = register("lightening_arc", () -> new DefaultParticleType(false) {
    });
    public static final RegistrySupplier<DefaultParticleType> ENDER_DAGGER = register("ender_dagger", () -> new DefaultParticleType(false) {
    });
    public static final RegistrySupplier<DefaultParticleType> WHITE_CIRCLE = register("white_circle", () -> new DefaultParticleType(false) {
    });
    public static final RegistrySupplier<DefaultParticleType> DARK_CIRCLE = register("dark_circle", () -> new DefaultParticleType(false) {
    });
    public static final RegistrySupplier<DefaultParticleType> FLOWER_WHITE = register("flower_white", () -> new DefaultParticleType(false) {
    });
    public static final RegistrySupplier<DefaultParticleType> SWEATER_SNOW = register("sweater_snow", () -> new DefaultParticleType(false) {
    });
    public static final RegistrySupplier<DefaultParticleType> YELLOW_STARS = register("yellow_stars", () -> new DefaultParticleType(false) {
    });

    private static RegistrySupplier<DefaultParticleType> register(String name, Supplier<DefaultParticleType> type) {
        return REGISTRY.register(name, type);
    }
}

