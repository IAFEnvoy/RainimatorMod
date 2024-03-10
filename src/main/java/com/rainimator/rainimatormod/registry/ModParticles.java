package com.rainimator.rainimatormod.registry;

import com.rainimator.rainimatormod.particle.*;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class ModParticles {
    @SubscribeEvent
    public static void registerParticles(ParticleFactoryRegisterEvent event) {
        (Minecraft.getInstance()).particleEngine.register(ModParticleTypes.PURPLELIGHT.get(), PurpleLightParticle::provider);
        (Minecraft.getInstance()).particleEngine.register(ModParticleTypes.REDFLOWER.get(), RedFlowerParticle::provider);
        (Minecraft.getInstance()).particleEngine.register(ModParticleTypes.SNOW.get(), SnowParticle::provider);
        (Minecraft.getInstance()).particleEngine.register(ModParticleTypes.LIGHTING_1.get(), Lighting1Particle::provider);
        (Minecraft.getInstance()).particleEngine.register(ModParticleTypes.LIGHTING_2.get(), Lighting2Particle::provider);
        (Minecraft.getInstance()).particleEngine.register(ModParticleTypes.YELLOWLIGHTING.get(), YellowLightingParticle::provider);
        (Minecraft.getInstance()).particleEngine.register(ModParticleTypes.LIGHTINGARC.get(), LightingArcParticle::provider);
        (Minecraft.getInstance()).particleEngine.register(ModParticleTypes.ENDERDAGGERSS.get(), EnderDaggerParticle::provider);
        (Minecraft.getInstance()).particleEngine.register(ModParticleTypes.WRITECRICLE.get(), WriteCricleParticle::provider);
        (Minecraft.getInstance()).particleEngine.register(ModParticleTypes.DARKCIRCLE.get(), DarkCircleParticle::provider);
        (Minecraft.getInstance()).particleEngine.register(ModParticleTypes.FLOWERWRITE.get(), FlowerWriteParticle::provider);
        (Minecraft.getInstance()).particleEngine.register(ModParticleTypes.SWEATERSNOW.get(), SweaterSnowParticle::provider);
        (Minecraft.getInstance()).particleEngine.register(ModParticleTypes.YELLOWSTEARS.get(), YellowStearsParticle::provider);
    }
}
