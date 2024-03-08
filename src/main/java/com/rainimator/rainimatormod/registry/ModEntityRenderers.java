package com.rainimator.rainimatormod.registry;

import com.rainimator.rainimatormod.renderer.EntityRendererBase;
import com.rainimator.rainimatormod.renderer.EntityWithEyeRendererBase;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class ModEntityRenderers {
    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.HEROBRINE.get(), c -> new EntityWithEyeRendererBase<>(c, "him_1", "him_eye"));
        event.registerEntityRenderer(ModEntities.CERIS.get(), c -> new EntityWithEyeRendererBase<>(c, "92322e9678a21dcb10003e2860cc158f1684865940", "ceris"));
        event.registerEntityRenderer(ModEntities.ZOMBIES.get(), c -> new EntityRendererBase<>(c, "804a25cd156aec608f0947c8c2c40e3e1684865940"));
        event.registerEntityRenderer(ModEntities.NAEUS.get(), c -> new EntityRendererBase<>(c, "naeus"));
        event.registerEntityRenderer(ModEntities.RAIN.get(), c -> new EntityRendererBase<>(c, "skinseedskin_1696241642536"));
        event.registerEntityRenderer(ModEntities.RAIN_PROJECTILE.get(), net.minecraft.client.renderer.entity.ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.ABIGAIL.get(), c -> new EntityRendererBase<>(c, "abigail"));
        event.registerEntityRenderer(ModEntities.ABIGAIL_PROJECTILE.get(), net.minecraft.client.renderer.entity.ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.PATRICK.get(), c -> new EntityRendererBase<>(c, "po_sui_jiang_jun_"));
        event.registerEntityRenderer(ModEntities.PATRICK_PROJECTILE.get(), net.minecraft.client.renderer.entity.ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.BLACKBONE.get(), c -> new EntityRendererBase<>(c, "skinseedskin_1697352537047"));
        event.registerEntityRenderer(ModEntities.HOGSWORTH.get(), c -> new EntityRendererBase<>(c, "skinseedskin_1697352540032"));
        event.registerEntityRenderer(ModEntities.SOLDIERS.get(), c -> new EntityRendererBase<>(c, "11451444444"));
        event.registerEntityRenderer(ModEntities.SOLDIERS_PROJECTILE.get(), net.minecraft.client.renderer.entity.ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.CIARA.get(), c -> new EntityRendererBase<>(c, "skinseedskin_1695472867346"));
        event.registerEntityRenderer(ModEntities.CIARA_PROJECTILE.get(), net.minecraft.client.renderer.entity.ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.HILDA.get(), c -> new EntityRendererBase<>(c, "skinseedskin_1695540344035"));
        event.registerEntityRenderer(ModEntities.HILDA_PROJECTILE.get(), net.minecraft.client.renderer.entity.ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.WITHERED_SKELETONS.get(), c -> new EntityRendererBase<>(c, "diao_ling_ku_lou_"));
        event.registerEntityRenderer(ModEntities.END_SATFF.get(), net.minecraft.client.renderer.entity.ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.VORDUS.get(), c -> new EntityRendererBase<>(c, "qqtu_pian_20231105171117"));
        event.registerEntityRenderer(ModEntities.DARKZOMBIE.get(), c -> new EntityWithEyeRendererBase<>(c, "dark_zombie", "dark"));
        event.registerEntityRenderer(ModEntities.DARKSHIELD.get(), c -> new EntityRendererBase<>(c, "ender_shield"));
        event.registerEntityRenderer(ModEntities.WITHERSHIELD.get(), c -> new EntityRendererBase<>(c, "wither_shield"));
        event.registerEntityRenderer(ModEntities.SKELETONSNOW.get(), c -> new EntityRendererBase<>(c, "skeleton_snow"));
        event.registerEntityRenderer(ModEntities.ARABELLA.get(), c -> new EntityRendererBase<>(c, "abbledoo"));
        event.registerEntityRenderer(ModEntities.AZALEA.get(), c -> new EntityRendererBase<>(c, "azalea"));
        event.registerEntityRenderer(ModEntities.NULLLIKE.get(), c -> new EntityWithEyeRendererBase<>(c, "null", "eye_null"));
        event.registerEntityRenderer(ModEntities.ZOMBIESPLIGEKING.get(), c -> new EntityRendererBase<>(c, "zombies_plige_king"));
        event.registerEntityRenderer(ModEntities.PILGEKINGZOMBIES.get(), c -> new EntityRendererBase<>(c, "pilge_kingzombies_1"));
        event.registerEntityRenderer(ModEntities.PILGEKINGZOMBIE.get(), c -> new EntityRendererBase<>(c, "pilge_kingzombies_2"));
        event.registerEntityRenderer(ModEntities.PIGLINCOMMANDER.get(), c -> new EntityRendererBase<>(c, "piglin_commander"));
        event.registerEntityRenderer(ModEntities.DARYLL.get(), c -> new EntityRendererBase<>(c, "daryll"));
        event.registerEntityRenderer(ModEntities.DARYLL_PROJECTILE.get(), net.minecraft.client.renderer.entity.ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.NAEUSKING.get(), c -> new EntityRendererBase<>(c, "naeus_king"));
        event.registerEntityRenderer(ModEntities.TUSK.get(), c -> new EntityRendererBase<>(c, "tusk"));
        event.registerEntityRenderer(ModEntities.BROTS.get(), c -> new EntityRendererBase<>(c, "brots"));
        event.registerEntityRenderer(ModEntities.ZOMBIEPIGLINART.get(), c -> new EntityRendererBase<>(c, "zombie_piglin_art"));
        event.registerEntityRenderer(ModEntities.MUTATED.get(), c -> new EntityRendererBase<>(c, "mutated"));
        event.registerEntityRenderer(ModEntities.NAMTAR.get(), c -> new EntityRendererBase<>(c, "namtar"));
        event.registerEntityRenderer(ModEntities.AGETHA.get(), c -> new EntityRendererBase<>(c, "agetha"));
        event.registerEntityRenderer(ModEntities.TRICER.get(), c -> new EntityRendererBase<>(c, "tricer"));
        event.registerEntityRenderer(ModEntities.BIGUNDEADSKELETON.get(), c -> new EntityRendererBase<>(c, "big_blackbone"));
        event.registerEntityRenderer(ModEntities.ARCHER.get(), c -> new EntityRendererBase<>(c, "archer"));
        event.registerEntityRenderer(ModEntities.HIM.get(), c -> new EntityWithEyeRendererBase<>(c, "him_2", "him_eye"));
        event.registerEntityRenderer(ModEntities.GIGABONE.get(), c -> new EntityRendererBase<>(c, "gigabone"));
        event.registerEntityRenderer(ModEntities.KLAUS.get(), c -> new EntityRendererBase<>(c, "klaus"));
        event.registerEntityRenderer(ModEntities.KLAUS_2.get(), c -> new EntityRendererBase<>(c, "klaus_2"));
        event.registerEntityRenderer(ModEntities.KRALOS.get(), c -> new EntityRendererBase<>(c, "kralos"));
    }
}