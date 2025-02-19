package com.iafenvoy.rainimator.registry;

import com.iafenvoy.neptune.render.SkullRenderRegistry;
import com.iafenvoy.neptune.render.Stage;
import com.iafenvoy.neptune.render.armor.IArmorRendererBase;
import com.iafenvoy.rainimator.entity.*;
import com.iafenvoy.rainimator.particle.*;
import com.iafenvoy.rainimator.renderer.armor.*;
import com.iafenvoy.rainimator.renderer.model.*;
import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import dev.architectury.registry.client.particle.ParticleProviderRegistry;
import dev.architectury.registry.client.rendering.RenderTypeRegistry;
import dev.architectury.registry.item.ItemPropertiesRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class RainimatorRenderers {
    public static void registerEntityRenderers() {
        EntityRendererRegistry.register(RainimatorEntities.HEROBRINE, HerobrineEntity.TEXTURE::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.CERIS, CerisEntity.TEXTURE::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.ELITE_ZOMBIE, EliteZombieEntity.TEXTURE::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.NAEUS, NaeusEntity.TEXTURE::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.RAIN, RainEntity.TEXTURE::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.RAIN_PROJECTILE, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(RainimatorEntities.ABIGAIL, AbigailEntity.TEXTURE::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.ABIGAIL_PROJECTILE, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(RainimatorEntities.PATRICK, PatrickEntity.TEXTURE::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.PATRICK_PROJECTILE, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(RainimatorEntities.BLACKBONE, BlackBoneEntity.TEXTURE::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.HOGSWORTH, HogsworthEntity.TEXTURE::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.SOLDIERS, SoldiersEntity.TEXTURE::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.SOLDIERS_PROJECTILE, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(RainimatorEntities.CIARA, CiaraEntity.TEXTURE::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.CIARA_PROJECTILE, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(RainimatorEntities.HILDA, HildaEntity.TEXTURE::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.HILDA_PROJECTILE, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(RainimatorEntities.WITHERED_SKELETONS, WitheredSkeletonsEntity.TEXTURE::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.END_STAFF, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(RainimatorEntities.DARK_ZOMBIE, DarkZombieEntity.TEXTURE::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.DARK_SHIELD, DarkShieldEntity.TEXTURE::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.WITHER_SHIELD, WitherShieldEntity.TEXTURE::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.SKELETON_SNOW, SkeletonSnowEntity.TEXTURE::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.ARABELLA, ArabellaEntity.TEXTURE::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.AZALEA, AzaleaEntity.TEXTURE::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.NULL_LIKE, NullLikeEntity.TEXTURE::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.ZOMBIE_PIGLIN_KING, ZombiesPiglinKingEntity.TEXTURE::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.GLUTTON, GluttonEntity.TEXTURE::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.PIGLIN_COMMANDER, PiglinCommanderEntity.TEXTURE::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.DARYLL, DaryllEntity.TEXTURE::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.DARYLL_PROJECTILE, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(RainimatorEntities.NAEUS_KING, NaeusKingEntity.TEXTURE::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.TUSK, TuskEntity.TEXTURE::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.BROTS, BrotsEntity.TEXTURE::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.ZOMBIE_PIGLIN_ART, ZombiePiglinArtEntity.TEXTURE::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.MUTATED, MutatedEntity.TEXTURE::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.NAMTAR, NamtarEntity.TEXTURE::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.AGETHA, AgethaEntity.TEXTURE::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.ARCHER, ArcherEntity.TEXTURE::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.GIGABONE, GigaBoneEntity.TEXTURE::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.KLAUS, KlausEntity.TEXTURE::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.KLAUS_2, Klaus2Entity.TEXTURE::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.KRALOS, KralosEntity.TEXTURE::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.KYLE, KyleEntity.TEXTURE::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.SCORCH, ScorchEntity.TEXTURE::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.STELLA, StellaEntity.TEXTURE::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.STELLA_DEMON, StellaDemonEntity.TEXTURE::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.SORA, SoraEntity.TEXTURE::createRenderer);
    }

    public static void registerLayerDefinitions() {
        EntityModelLayerRegistry.register(PorkshireKingCrownModel.LAYER_LOCATION, PorkshireKingCrownModel::createBodyLayer);
        EntityModelLayerRegistry.register(MagicHatModel.LAYER_LOCATION, MagicHatModel::createBodyLayer);
        EntityModelLayerRegistry.register(GluttonHelmetModel.LAYER_LOCATION, GluttonHelmetModel::createBodyLayer);
        EntityModelLayerRegistry.register(KingNormalCrownModel.LAYER_LOCATION, KingNormalCrownModel::createBodyLayer);
        EntityModelLayerRegistry.register(NetherTheCrownModel.LAYER_LOCATION, NetherTheCrownModel::createBodyLayer);
        EntityModelLayerRegistry.register(PiglinKingCrownModel.LAYER_LOCATION, PiglinKingCrownModel::createBodyLayer);
    }

    public static void registerArmorRenderers() {
        IArmorRendererBase.register(new GluttonHelmetRenderer(), RainimatorItems.GLUTTON_HELMET.get());
        IArmorRendererBase.register(new KingNormalCrownRenderer(), RainimatorItems.KING_NORMAL_CROWN.get());
        IArmorRendererBase.register(new MagicHatRenderer(), RainimatorItems.MAGIC_HAT.get());
        IArmorRendererBase.register(new NetherTheCrownRenderer(), RainimatorItems.NETHER_THE_CROWN.get());
        IArmorRendererBase.register(new PiglinKingCrownRenderer(), RainimatorItems.PIGLIN_KING_CROWN.get());
        IArmorRendererBase.register(new PorkshireKingCrownRenderer(), RainimatorItems.PORKSHIRE_KING_CROWN.get());
    }

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

    public static void registerSkulls() {
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.HEROBRINE_1, HerobrineEntity.TEXTURE.getTexture(Stage.First), RainimatorSkulls.HEROBRINE_1_HEAD.get(), RainimatorSkulls.HEROBRINE_1_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.HEROBRINE_2, HerobrineEntity.TEXTURE.getTexture(Stage.Second), RainimatorSkulls.HEROBRINE_2_HEAD.get(), RainimatorSkulls.HEROBRINE_2_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.CERIS, CerisEntity.TEXTURE.getTexture(), RainimatorSkulls.CERIS_HEAD.get(), RainimatorSkulls.CERIS_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.ZOMBIES, EliteZombieEntity.TEXTURE.getTexture(), RainimatorSkulls.ELITE_ZOMBIE_HEAD.get(), RainimatorSkulls.ELITE_ZOMBIE_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.NAEUS, NaeusEntity.TEXTURE.getTexture(), RainimatorSkulls.NAEUS_HEAD.get(), RainimatorSkulls.NAEUS_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.RAIN, RainEntity.TEXTURE.getTexture(), RainimatorSkulls.RAIN_HEAD.get(), RainimatorSkulls.RAIN_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.ABIGAIL, AbigailEntity.TEXTURE.getTexture(), RainimatorSkulls.ABIGAIL_HEAD.get(), RainimatorSkulls.ABIGAIL_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.PATRICK, PatrickEntity.TEXTURE.getTexture(), RainimatorSkulls.PATRICK_HEAD.get(), RainimatorSkulls.PATRICK_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.BLACKBONE, BlackBoneEntity.TEXTURE.getTexture(), RainimatorSkulls.BLACKBONE_HEAD.get(), RainimatorSkulls.BLACKBONE_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.HOGSWORTH, HogsworthEntity.TEXTURE.getTexture(), RainimatorSkulls.HOGSWORTH_HEAD.get(), RainimatorSkulls.HOGSWORTH_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.CIARA, CiaraEntity.TEXTURE.getTexture(), RainimatorSkulls.CIARA_HEAD.get(), RainimatorSkulls.CIARA_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.HILDA, HildaEntity.TEXTURE.getTexture(), RainimatorSkulls.HILDA_HEAD.get(), RainimatorSkulls.HILDA_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.SOLDIERS, SoldiersEntity.TEXTURE.getTexture(), RainimatorSkulls.SOLDIERS_HEAD.get(), RainimatorSkulls.SOLDIERS_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.WITHERED_SKELETONS, WitheredSkeletonsEntity.TEXTURE.getTexture(), RainimatorSkulls.WITHERED_SKELETONS_HEAD.get(), RainimatorSkulls.WITHERED_SKELETONS_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.DARK_SHIELD, DarkShieldEntity.TEXTURE.getTexture(), RainimatorSkulls.DARK_SHIELD_HEAD.get(), RainimatorSkulls.DARK_SHIELD_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.DARK_ZOMBIE, DarkZombieEntity.TEXTURE.getTexture(), RainimatorSkulls.DARK_ZOMBIE_HEAD.get(), RainimatorSkulls.DARK_ZOMBIE_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.WITHER_SHIELD, WitherShieldEntity.TEXTURE.getTexture(), RainimatorSkulls.WITHER_SHIELD_HEAD.get(), RainimatorSkulls.WITHER_SHIELD_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.SKELETON_SNOW, SkeletonSnowEntity.TEXTURE.getTexture(), RainimatorSkulls.SKELETON_SNOW_HEAD.get(), RainimatorSkulls.SKELETON_SNOW_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.PIGLIN_KING, ZombiesPiglinKingEntity.TEXTURE.getTexture(), RainimatorSkulls.PIGLIN_KING_HEAD.get(), RainimatorSkulls.PIGLIN_KING_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.GLUTTON_1, GluttonEntity.TEXTURE.getTexture(Stage.First), RainimatorSkulls.GLUTTON_1_HEAD.get(), RainimatorSkulls.GLUTTON_1_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.GLUTTON_2, GluttonEntity.TEXTURE.getTexture(Stage.Second), RainimatorSkulls.GLUTTON_2_HEAD.get(), RainimatorSkulls.GLUTTON_2_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.PORKSHIRE, PiglinCommanderEntity.TEXTURE.getTexture(), RainimatorSkulls.PORKSHIRE_HEAD.get(), RainimatorSkulls.PORKSHIRE_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.NAEUS_KING, NaeusKingEntity.TEXTURE.getTexture(), RainimatorSkulls.NAEUS_KING_HEAD.get(), RainimatorSkulls.NAEUS_KING_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.TUSK, TuskEntity.TEXTURE.getTexture(), RainimatorSkulls.TUSK_HEAD.get(), RainimatorSkulls.TUSK_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.BROTS, BrotsEntity.TEXTURE.getTexture(), RainimatorSkulls.BROTS_HEAD.get(), RainimatorSkulls.BROTS_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.ZOMBIE_PIG, ZombiePiglinArtEntity.TEXTURE.getTexture(), RainimatorSkulls.ZOMBIE_PIG_HEAD.get(), RainimatorSkulls.ZOMBIE_PIG_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.MUTATED, MutatedEntity.TEXTURE.getTexture(), RainimatorSkulls.MUTATED_HEAD.get(), RainimatorSkulls.MUTATED_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.NAMTAR, NamtarEntity.TEXTURE.getTexture(), RainimatorSkulls.NAMTAR_HEAD.get(), RainimatorSkulls.NAMTAR_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.AGETHA, AgethaEntity.TEXTURE.getTexture(), RainimatorSkulls.AGETHA_HEAD.get(), RainimatorSkulls.AGETHA_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.ARCHER, ArcherEntity.TEXTURE.getTexture(), RainimatorSkulls.ARCHER_HEAD.get(), RainimatorSkulls.ARCHER_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.DARYLL, DaryllEntity.TEXTURE.getTexture(), RainimatorSkulls.DARYLL_HEAD.get(), RainimatorSkulls.DARYLL_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.NULL_LIKE, NullLikeEntity.TEXTURE.getTexture(), RainimatorSkulls.NULL_LIKE_HEAD.get(), RainimatorSkulls.NULL_LIKE_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.GIGABONE, GigaBoneEntity.TEXTURE.getTexture(), RainimatorSkulls.GIGABONE_HEAD.get(), RainimatorSkulls.GIGABONE_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.KLAUS, KlausEntity.TEXTURE.getTexture(), RainimatorSkulls.KLAUS_HEAD.get(), RainimatorSkulls.KLAUS_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.KLAUS_2, Klaus2Entity.TEXTURE.getTexture(), RainimatorSkulls.KLAUS_2_HEAD.get(), RainimatorSkulls.KLAUS_2_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.KRALOS, KralosEntity.TEXTURE.getTexture(), RainimatorSkulls.KRALOS_HEAD.get(), RainimatorSkulls.KRALOS_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.ARABELLA, ArabellaEntity.TEXTURE.getTexture(), RainimatorSkulls.ARABELLA_HEAD.get(), RainimatorSkulls.ARABELLA_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.AZALEA, AzaleaEntity.TEXTURE.getTexture(), RainimatorSkulls.AZALEA_HEAD.get(), RainimatorSkulls.AZALEA_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.KYLE, KyleEntity.TEXTURE.getTexture(), RainimatorSkulls.KYLE_HEAD.get(), RainimatorSkulls.KYLE_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.SCORCH, ScorchEntity.TEXTURE.getTexture(), RainimatorSkulls.SCORCH_HEAD.get(), RainimatorSkulls.SCORCH_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.STELLA, StellaEntity.TEXTURE.getTexture(), RainimatorSkulls.STELLA_HEAD.get(), RainimatorSkulls.STELLA_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.STELLA_DEMON, StellaDemonEntity.TEXTURE.getTexture(), RainimatorSkulls.STELLA_DEMON_HEAD.get(), RainimatorSkulls.STELLA_DEMON_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.SORA, SoraEntity.TEXTURE.getTexture(), RainimatorSkulls.SORA_HEAD.get(), RainimatorSkulls.SORA_WALL_HEAD.get());
    }

    public static void registerModelPredicates() {
        registerBlocking(RainimatorItems.SNOW_SHIELD.get());
        registerBlocking(RainimatorItems.BLUE_DIAMOND_SHIELD.get());
        registerBlocking(RainimatorItems.NETHERITE_SHIELD.get());
    }

    private static void registerBlocking(ItemConvertible item) {
        ItemPropertiesRegistry.register(item, new Identifier("blocking"), (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F);
    }

    public static void registerRenderLayers() {
        RenderTypeRegistry.register(RenderLayer.getCutout(), RainimatorBlocks.DARK_OBSIDIAN_BLOCK.get());
    }
}
