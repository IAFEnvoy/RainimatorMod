package com.rainimator.rainimatormod.registry;

import com.rainimator.rainimatormod.RainimatorMod;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModParticleTypes {
    public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, RainimatorMod.MOD_ID);
    public static final RegistryObject<ParticleType<SimpleParticleType>> PURPLELIGHT = REGISTRY.register("purplelight", () -> new SimpleParticleType(false));
    public static final RegistryObject<ParticleType<SimpleParticleType>> REDFLOWER = REGISTRY.register("redflower", () -> new SimpleParticleType(false));
    public static final RegistryObject<ParticleType<SimpleParticleType>> SNOW = REGISTRY.register("snow", () -> new SimpleParticleType(false));
    public static final RegistryObject<ParticleType<SimpleParticleType>> LIGHTING_1 = REGISTRY.register("lighting_1", () -> new SimpleParticleType(false));
    public static final RegistryObject<ParticleType<SimpleParticleType>> LIGHTING_2 = REGISTRY.register("lighting_2", () -> new SimpleParticleType(false));
    public static final RegistryObject<ParticleType<SimpleParticleType>> YELLOWLIGHTING = REGISTRY.register("yellowlighting", () -> new SimpleParticleType(false));
    public static final RegistryObject<ParticleType<SimpleParticleType>> LIGHTINGARC = REGISTRY.register("lightingarc", () -> new SimpleParticleType(false));
    public static final RegistryObject<ParticleType<SimpleParticleType>> ENDERDAGGERSS = REGISTRY.register("enderdaggerss", () -> new SimpleParticleType(false));
    public static final RegistryObject<ParticleType<SimpleParticleType>> WRITECRICLE = REGISTRY.register("writecricle", () -> new SimpleParticleType(false));
    public static final RegistryObject<ParticleType<SimpleParticleType>> DARKCIRCLE = REGISTRY.register("darkcircle", () -> new SimpleParticleType(false));
    public static final RegistryObject<ParticleType<SimpleParticleType>> FLOWERWRITE = REGISTRY.register("flowerwrite", () -> new SimpleParticleType(false));
    public static final RegistryObject<ParticleType<SimpleParticleType>> SWEATERSNOW = REGISTRY.register("sweatersnow", () -> new SimpleParticleType(false));
    public static final RegistryObject<ParticleType<SimpleParticleType>> YELLOWSTEARS = REGISTRY.register("yellowstears", () -> new SimpleParticleType(false));
}
