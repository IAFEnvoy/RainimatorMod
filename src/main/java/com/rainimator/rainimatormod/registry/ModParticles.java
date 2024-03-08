package com.rainimator.rainimatormod.registry;

import com.rainimator.rainimatormod.particle.*;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class ModParticles
{
    @SubscribeEvent
    public static void registerParticles(ParticleFactoryRegisterEvent event) {
        (Minecraft.getInstance()).particleEngine.register(ModParticleTypes.PURPLELIGHT.get(), PurplelightParticle::provider);
        (Minecraft.getInstance()).particleEngine.register(ModParticleTypes.REDFLOWER.get(), RedflowerParticle::provider);
        (Minecraft.getInstance()).particleEngine.register(ModParticleTypes.SNOW.get(), SnowParticle::provider);
        (Minecraft.getInstance()).particleEngine.register(ModParticleTypes.LIGHTING_1.get(), Lighting1Particle::provider);
        (Minecraft.getInstance()).particleEngine.register(ModParticleTypes.LIGHTING_2.get(), Lighting2Particle::provider);
        (Minecraft.getInstance()).particleEngine.register(ModParticleTypes.YELLOWLIGHTING.get(), YellowlightingParticle::provider);
        (Minecraft.getInstance()).particleEngine.register(ModParticleTypes.LIGHTINGARC.get(), LightingarcParticle::provider);
        (Minecraft.getInstance()).particleEngine.register(ModParticleTypes.ENDERDAGGERSS.get(), EnderdaggerssParticle::provider);
        (Minecraft.getInstance()).particleEngine.register(ModParticleTypes.WRITECRICLE.get(), WritecricleParticle::provider);
        (Minecraft.getInstance()).particleEngine.register(ModParticleTypes.DARKCIRCLE.get(), DarkcircleParticle::provider);
        (Minecraft.getInstance()).particleEngine.register(ModParticleTypes.FLOWERWRITE.get(), FlowerwriteParticle::provider);
        (Minecraft.getInstance()).particleEngine.register(ModParticleTypes.SWEATERSNOW.get(), SweatersnowParticle::provider);
        (Minecraft.getInstance()).particleEngine.register(ModParticleTypes.YELLOWSTEARS.get(), YellowstearsParticle::provider);
    }
}
