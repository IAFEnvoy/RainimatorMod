package com.rainimator.rainimatormod.registry;

import com.rainimator.rainimatormod.renderer.EntityRendererBase;
import com.rainimator.rainimatormod.renderer.EntityWithEyeRendererBase;
import com.rainimator.rainimatormod.util.Stage;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class ModEntityRenderers {
    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.HEROBRINE.get(), c -> new EntityWithEyeRendererBase<>(c, Stage.ofProvider("him_1", "him_2"), "him_eye"));
        event.registerEntityRenderer(ModEntities.CERIS.get(), c -> new EntityWithEyeRendererBase<>(c, Stage.ofProvider("92322e9678a21dcb10003e2860cc158f1684865940"), "ceris"));
        event.registerEntityRenderer(ModEntities.ZOMBIES.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("804a25cd156aec608f0947c8c2c40e3e1684865940")));
        event.registerEntityRenderer(ModEntities.NAEUS.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("naeus")));
        event.registerEntityRenderer(ModEntities.RAIN.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("skinseedskin_1696241642536")));
        event.registerEntityRenderer(ModEntities.RAIN_PROJECTILE.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.ABIGAIL.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("abigail")));
        event.registerEntityRenderer(ModEntities.ABIGAIL_PROJECTILE.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.PATRICK.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("po_sui_jiang_jun_")));
        event.registerEntityRenderer(ModEntities.PATRICK_PROJECTILE.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.BLACKBONE.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("skinseedskin_1697352537047")));
        event.registerEntityRenderer(ModEntities.HOGSWORTH.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("skinseedskin_1697352540032")));
        event.registerEntityRenderer(ModEntities.SOLDIERS.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("11451444444")));
        event.registerEntityRenderer(ModEntities.SOLDIERS_PROJECTILE.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.CIARA.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("skinseedskin_1695472867346")));
        event.registerEntityRenderer(ModEntities.CIARA_PROJECTILE.get(), net.minecraft.client.renderer.entity.ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.HILDA.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("skinseedskin_1695540344035")));
        event.registerEntityRenderer(ModEntities.HILDA_PROJECTILE.get(), net.minecraft.client.renderer.entity.ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.WITHERED_SKELETONS.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("diao_ling_ku_lou_")));
        event.registerEntityRenderer(ModEntities.END_SATFF.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.VORDUS.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("qqtu_pian_20231105171117")));
        event.registerEntityRenderer(ModEntities.DARKZOMBIE.get(), c -> new EntityWithEyeRendererBase<>(c, Stage.ofProvider("dark_zombie"), "dark"));
        event.registerEntityRenderer(ModEntities.DARKSHIELD.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("ender_shield")));
        event.registerEntityRenderer(ModEntities.WITHERSHIELD.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("wither_shield")));
        event.registerEntityRenderer(ModEntities.SKELETONSNOW.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("skeleton_snow")));
        event.registerEntityRenderer(ModEntities.ARABELLA.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("abbledoo")));
        event.registerEntityRenderer(ModEntities.AZALEA.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("azalea")));
        event.registerEntityRenderer(ModEntities.NULLLIKE.get(), c -> new EntityWithEyeRendererBase<>(c, Stage.ofProvider("null"), "eye_null"));
        event.registerEntityRenderer(ModEntities.ZOMBIESPLIGEKING.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("zombies_plige_king")));
        event.registerEntityRenderer(ModEntities.PILGEKINGZOMBIES.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("pilge_kingzombies_1")));
        event.registerEntityRenderer(ModEntities.PILGEKINGZOMBIE.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("pilge_kingzombies_2")));
        event.registerEntityRenderer(ModEntities.PIGLINCOMMANDER.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("piglin_commander")));
        event.registerEntityRenderer(ModEntities.DARYLL.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("daryll")));
        event.registerEntityRenderer(ModEntities.DARYLL_PROJECTILE.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.NAEUSKING.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("naeus_king")));
        event.registerEntityRenderer(ModEntities.TUSK.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("tusk")));
        event.registerEntityRenderer(ModEntities.BROTS.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("brots")));
        event.registerEntityRenderer(ModEntities.ZOMBIEPIGLINART.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("zombie_piglin_art")));
        event.registerEntityRenderer(ModEntities.MUTATED.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("mutated")));
        event.registerEntityRenderer(ModEntities.NAMTAR.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("namtar")));
        event.registerEntityRenderer(ModEntities.AGETHA.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("agetha")));
        event.registerEntityRenderer(ModEntities.TRICER.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("tricer")));
        event.registerEntityRenderer(ModEntities.BIGUNDEADSKELETON.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("big_blackbone")));
        event.registerEntityRenderer(ModEntities.ARCHER.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("archer")));
        event.registerEntityRenderer(ModEntities.GIGABONE.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("gigabone")));
        event.registerEntityRenderer(ModEntities.KLAUS.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("klaus")));
        event.registerEntityRenderer(ModEntities.KLAUS_2.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("klaus_2")));
        event.registerEntityRenderer(ModEntities.KRALOS.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("kralos")));
    }
}