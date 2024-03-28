package com.rainimator.rainimatormod.registry;

import com.rainimator.rainimatormod.entity.*;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class ModEntityRenderers {
    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.HEROBRINE.get(), HerobrineEntity.texture::createRenderer);
        event.registerEntityRenderer(ModEntities.CERIS.get(), CerisEntity.texture::createRenderer);
        event.registerEntityRenderer(ModEntities.ZOMBIES.get(), ZombiesEntity.texture::createRenderer);
        event.registerEntityRenderer(ModEntities.NAEUS.get(), NaeusEntity.texture::createRenderer);
        event.registerEntityRenderer(ModEntities.RAIN.get(), RainEntity.texture::createRenderer);
        event.registerEntityRenderer(ModEntities.RAIN_PROJECTILE.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.ABIGAIL.get(), AbigailEntity.texture::createRenderer);
        event.registerEntityRenderer(ModEntities.ABIGAIL_PROJECTILE.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.PATRICK.get(), PatrickEntity.texture::createRenderer);
        event.registerEntityRenderer(ModEntities.PATRICK_PROJECTILE.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.BLACKBONE.get(), BlackBoneEntity.texture::createRenderer);
        event.registerEntityRenderer(ModEntities.HOGSWORTH.get(), HogsworthEntity.texture::createRenderer);
        event.registerEntityRenderer(ModEntities.SOLDIERS.get(), SoldiersEntity.texture::createRenderer);
        event.registerEntityRenderer(ModEntities.SOLDIERS_PROJECTILE.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.CIARA.get(), CiaraEntity.texture::createRenderer);
        event.registerEntityRenderer(ModEntities.CIARA_PROJECTILE.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.HILDA.get(), HildaEntity.texture::createRenderer);
        event.registerEntityRenderer(ModEntities.HILDA_PROJECTILE.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.WITHERED_SKELETONS.get(), WitheredSkeletonsEntity.texture::createRenderer);
        event.registerEntityRenderer(ModEntities.END_STAFF.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.VORDUS.get(), VordusEntity.texture::createRenderer);
        event.registerEntityRenderer(ModEntities.DARK_ZOMBIE.get(), DarkZombieEntity.texture::createRenderer);
        event.registerEntityRenderer(ModEntities.DARK_SHIELD.get(), DarkShieldEntity.texture::createRenderer);
        event.registerEntityRenderer(ModEntities.WITHER_SHIELD.get(), WitherShieldEntity.texture::createRenderer);
        event.registerEntityRenderer(ModEntities.SKELETON_SNOW.get(), SkeletonSnowEntity.texture::createRenderer);
        event.registerEntityRenderer(ModEntities.ARABELLA.get(), ArabellaEntity.texture::createRenderer);
        event.registerEntityRenderer(ModEntities.AZALEA.get(), AzaleaEntity.texture::createRenderer);
        event.registerEntityRenderer(ModEntities.NULL_LIKE.get(), NullLikeEntity.texture::createRenderer);
        event.registerEntityRenderer(ModEntities.ZOMBIE_PIGLIN_KING.get(), ZombiesPiglinKingEntity.texture::createRenderer);
        event.registerEntityRenderer(ModEntities.PIGLIN_KING_ZOMBIES.get(), PiglinKingZombiesEntity.texture::createRenderer);
        event.registerEntityRenderer(ModEntities.PIGLIN_KING_ZOMBIE.get(), PiglinKingZombieEntity.texture::createRenderer);
        event.registerEntityRenderer(ModEntities.PIGLIN_COMMANDER.get(), PiglinCommanderEntity.texture::createRenderer);
        event.registerEntityRenderer(ModEntities.DARYLL.get(), DaryllEntity.texture::createRenderer);
        event.registerEntityRenderer(ModEntities.DARYLL_PROJECTILE.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ModEntities.NAEUS_KING.get(), NaeusKingEntity.texture::createRenderer);
        event.registerEntityRenderer(ModEntities.TUSK.get(), TuskEntity.texture::createRenderer);
        event.registerEntityRenderer(ModEntities.BROTS.get(), BrotsEntity.texture::createRenderer);
        event.registerEntityRenderer(ModEntities.ZOMBIE_PIGLIN_ART.get(), ZombiePiglinArtEntity.texture::createRenderer);
        event.registerEntityRenderer(ModEntities.MUTATED.get(), MutatedEntity.texture::createRenderer);
        event.registerEntityRenderer(ModEntities.NAMTAR.get(), NamtarEntity.texture::createRenderer);
        event.registerEntityRenderer(ModEntities.AGETHA.get(), AgethaEntity.texture::createRenderer);
        event.registerEntityRenderer(ModEntities.TRICER.get(), TricerEntity.texture::createRenderer);
        event.registerEntityRenderer(ModEntities.BIG_UNDEAD_SKELETON.get(), BigUndeadSkeletonEntity.texture::createRenderer);
        event.registerEntityRenderer(ModEntities.ARCHER.get(), ArcherEntity.texture::createRenderer);
        event.registerEntityRenderer(ModEntities.GIGABONE.get(), GigaBoneEntity.texture::createRenderer);
        event.registerEntityRenderer(ModEntities.KLAUS.get(), KlausEntity.texture::createRenderer);
        event.registerEntityRenderer(ModEntities.KLAUS_2.get(), Klaus2Entity.texture::createRenderer);
        event.registerEntityRenderer(ModEntities.KRALOS.get(), KralosEntity.texture::createRenderer);
    }
}