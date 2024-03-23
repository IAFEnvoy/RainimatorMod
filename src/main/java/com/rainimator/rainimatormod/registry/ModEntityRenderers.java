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
        event.registerEntityRenderer(ModEntities.CERIS.get(), c -> new EntityWithEyeRendererBase<>(c, Stage.ofProvider("ceris"), "ceris_eye"));
        event.registerEntityRenderer(ModEntities.ZOMBIES.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("zombies")));
        event.registerEntityRenderer(ModEntities.NAEUS.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("naeus")));
        event.registerEntityRenderer(ModEntities.RAIN.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("rain")));
        event.registerEntityRenderer(ModEntities.RAIN_PROJECTILE.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.ABIGAIL.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("abigail")));
        event.registerEntityRenderer(ModEntities.ABIGAIL_PROJECTILE.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.PATRICK.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("patrick")));
        event.registerEntityRenderer(ModEntities.PATRICK_PROJECTILE.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.BLACKBONE.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("blackbone")));
        event.registerEntityRenderer(ModEntities.HOGSWORTH.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("hogsworth")));
        event.registerEntityRenderer(ModEntities.SOLDIERS.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("soldiers")));
        event.registerEntityRenderer(ModEntities.SOLDIERS_PROJECTILE.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.CIARA.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("ciara")));
        event.registerEntityRenderer(ModEntities.CIARA_PROJECTILE.get(), net.minecraft.client.renderer.entity.ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.HILDA.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("hilda")));
        event.registerEntityRenderer(ModEntities.HILDA_PROJECTILE.get(), net.minecraft.client.renderer.entity.ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.WITHERED_SKELETONS.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("withered_skeletons")));
        event.registerEntityRenderer(ModEntities.END_STAFF.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.VORDUS.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("vordus")));
        event.registerEntityRenderer(ModEntities.DARK_ZOMBIE.get(), c -> new EntityWithEyeRendererBase<>(c, Stage.ofProvider("dark_zombie"), "dark_zombie_eye"));
        event.registerEntityRenderer(ModEntities.DARK_SHIELD.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("ender_shield")));
        event.registerEntityRenderer(ModEntities.WITHER_SHIELD.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("wither_shield")));
        event.registerEntityRenderer(ModEntities.SKELETON_SNOW.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("skeleton_snow")));
        event.registerEntityRenderer(ModEntities.ARABELLA.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("arabella")));
        event.registerEntityRenderer(ModEntities.AZALEA.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("azalea")));
        event.registerEntityRenderer(ModEntities.NULL_LIKE.get(), c -> new EntityWithEyeRendererBase<>(c, Stage.ofProvider("null_like"), "null_like_eye"));
        event.registerEntityRenderer(ModEntities.ZOMBIE_PIGLIN_KING.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("zombies_piglin_king")));
        event.registerEntityRenderer(ModEntities.PIGLIN_KING_ZOMBIES.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("piglin_king_zombies")));
        event.registerEntityRenderer(ModEntities.PIGLIN_KING_ZOMBIE.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("piglin_king_zombie")));
        event.registerEntityRenderer(ModEntities.PIGLIN_COMMANDER.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("piglin_commander")));
        event.registerEntityRenderer(ModEntities.DARYLL.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("daryll")));
        event.registerEntityRenderer(ModEntities.DARYLL_PROJECTILE.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.NAEUS_KING.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("naeus_king")));
        event.registerEntityRenderer(ModEntities.TUSK.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("tusk")));
        event.registerEntityRenderer(ModEntities.BROTS.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("brots")));
        event.registerEntityRenderer(ModEntities.ZOMBIE_PIGLIN_ART.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("zombie_piglin_art")));
        event.registerEntityRenderer(ModEntities.MUTATED.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("mutated")));
        event.registerEntityRenderer(ModEntities.NAMTAR.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("namtar")));
        event.registerEntityRenderer(ModEntities.AGETHA.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("agetha")));
        event.registerEntityRenderer(ModEntities.TRICER.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("tricer")));
        event.registerEntityRenderer(ModEntities.BIG_UNDEAD_SKELETON.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("big_blackbone")));
        event.registerEntityRenderer(ModEntities.ARCHER.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("archer")));
        event.registerEntityRenderer(ModEntities.GIGABONE.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("gigabone")));
        event.registerEntityRenderer(ModEntities.KLAUS.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("klaus")));
        event.registerEntityRenderer(ModEntities.KLAUS_2.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("klaus_2")));
        event.registerEntityRenderer(ModEntities.KRALOS.get(), c -> new EntityRendererBase<>(c, Stage.ofProvider("kralos")));
    }
}