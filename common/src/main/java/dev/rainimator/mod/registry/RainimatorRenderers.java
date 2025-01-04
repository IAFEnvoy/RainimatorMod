package dev.rainimator.mod.registry;

import com.iafenvoy.neptune.render.SkullRenderRegistry;
import com.iafenvoy.neptune.render.Stage;
import com.iafenvoy.neptune.render.armor.IArmorRendererBase;
import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import dev.architectury.registry.client.particle.ParticleProviderRegistry;
import dev.architectury.registry.menu.MenuRegistry;
import dev.rainimator.mod.entity.*;
import dev.rainimator.mod.particle.*;
import dev.rainimator.mod.renderer.armor.*;
import dev.rainimator.mod.renderer.model.*;
import dev.rainimator.mod.screen.gui.EnderBookSkillScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

@Environment(EnvType.CLIENT)
public class RainimatorRenderers {
    public static void registerEntityRenderers() {
        EntityRendererRegistry.register(RainimatorEntities.HEROBRINE, HerobrineEntity.texture::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.CERIS, CerisEntity.texture::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.ELITE_ZOMBIE, EliteZombieEntity.texture::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.NAEUS, NaeusEntity.texture::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.RAIN, RainEntity.texture::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.RAIN_PROJECTILE, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(RainimatorEntities.ABIGAIL, AbigailEntity.texture::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.ABIGAIL_PROJECTILE, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(RainimatorEntities.PATRICK, PatrickEntity.texture::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.PATRICK_PROJECTILE, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(RainimatorEntities.BLACKBONE, BlackBoneEntity.texture::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.HOGSWORTH, HogsworthEntity.texture::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.SOLDIERS, SoldiersEntity.texture::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.SOLDIERS_PROJECTILE, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(RainimatorEntities.CIARA, CiaraEntity.texture::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.CIARA_PROJECTILE, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(RainimatorEntities.HILDA, HildaEntity.texture::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.HILDA_PROJECTILE, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(RainimatorEntities.WITHERED_SKELETONS, WitheredSkeletonsEntity.texture::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.END_STAFF, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(RainimatorEntities.VORDUS, VordusEntity.texture::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.DARK_ZOMBIE, DarkZombieEntity.texture::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.DARK_SHIELD, DarkShieldEntity.texture::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.WITHER_SHIELD, WitherShieldEntity.texture::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.SKELETON_SNOW, SkeletonSnowEntity.texture::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.ARABELLA, ArabellaEntity.texture::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.AZALEA, AzaleaEntity.texture::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.NULL_LIKE, NullLikeEntity.texture::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.ZOMBIE_PIGLIN_KING, ZombiesPiglinKingEntity.texture::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.GLUTTON, GluttonEntity.texture::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.PIGLIN_COMMANDER, PiglinCommanderEntity.texture::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.DARYLL, DaryllEntity.texture::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.DARYLL_PROJECTILE, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(RainimatorEntities.NAEUS_KING, NaeusKingEntity.texture::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.TUSK, TuskEntity.texture::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.BROTS, BrotsEntity.texture::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.ZOMBIE_PIGLIN_ART, ZombiePiglinArtEntity.texture::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.MUTATED, MutatedEntity.texture::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.NAMTAR, NamtarEntity.texture::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.AGETHA, AgethaEntity.texture::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.BIG_UNDEAD_SKELETON, BigUndeadSkeletonEntity.texture::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.ARCHER, ArcherEntity.texture::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.GIGABONE, GigaBoneEntity.texture::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.KLAUS, KlausEntity.texture::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.KLAUS_2, Klaus2Entity.texture::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.KRALOS, KralosEntity.texture::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.KYLE, KyleEntity.texture::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.SCORCH, ScorchEntity.texture::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.STELLA, StellaEntity.texture::createRenderer);
        EntityRendererRegistry.register(RainimatorEntities.STELLA_DEMON, StellaDemonEntity.texture::createRenderer);
    }

    public static void registerLayerDefinitions() {
        EntityModelLayerRegistry.register(ModelPorkshireKingCrown.LAYER_LOCATION, ModelPorkshireKingCrown::createBodyLayer);
        EntityModelLayerRegistry.register(ModelMagic.LAYER_LOCATION, ModelMagic::createBodyLayer);
        EntityModelLayerRegistry.register(ModelCustomModel.LAYER_LOCATION, ModelCustomModel::createBodyLayer);
        EntityModelLayerRegistry.register(ModelKingNormalCrown.LAYER_LOCATION, ModelKingNormalCrown::createBodyLayer);
        EntityModelLayerRegistry.register(ModelNetherCrown.LAYER_LOCATION, ModelNetherCrown::createBodyLayer);
        EntityModelLayerRegistry.register(ModelEnderman.LAYER_LOCATION, ModelEnderman::createBodyLayer);
        EntityModelLayerRegistry.register(ModelNetherKing2.LAYER_LOCATION, ModelNetherKing2::createBodyLayer);
    }

    public static void registerArmorRenderers() {
        IArmorRendererBase.register(new GluttonArmorHelmetRenderer(), RainimatorItems.GLUTTON_HELMET.get());
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

    public static void registerSkull() {
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.HEROBRINE_1, HerobrineEntity.texture.getTexture(Stage.First), RainimatorSkulls.HEROBRINE_1_HEAD.get(), RainimatorSkulls.HEROBRINE_1_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.HEROBRINE_2, HerobrineEntity.texture.getTexture(Stage.Second), RainimatorSkulls.HEROBRINE_2_HEAD.get(), RainimatorSkulls.HEROBRINE_2_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.CERIS, CerisEntity.texture.getTexture(), RainimatorSkulls.CERIS_HEAD.get(), RainimatorSkulls.CERIS_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.ZOMBIES, EliteZombieEntity.texture.getTexture(), RainimatorSkulls.ELITE_ZOMBIE_HEAD.get(), RainimatorSkulls.ELITE_ZOMBIE_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.NAEUS, NaeusEntity.texture.getTexture(), RainimatorSkulls.NAEUS_HEAD.get(), RainimatorSkulls.NAEUS_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.RAIN, RainEntity.texture.getTexture(), RainimatorSkulls.RAIN_HEAD.get(), RainimatorSkulls.RAIN_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.ABIGAIL, AbigailEntity.texture.getTexture(), RainimatorSkulls.ABIGAIL_HEAD.get(), RainimatorSkulls.ABIGAIL_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.PATRICK, PatrickEntity.texture.getTexture(), RainimatorSkulls.PATRICK_HEAD.get(), RainimatorSkulls.PATRICK_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.BLACKBONE, BlackBoneEntity.texture.getTexture(), RainimatorSkulls.BLACKBONE_HEAD.get(), RainimatorSkulls.BLACKBONE_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.HOGSWORTH, HogsworthEntity.texture.getTexture(), RainimatorSkulls.HOGSWORTH_HEAD.get(), RainimatorSkulls.HOGSWORTH_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.CIARA, CiaraEntity.texture.getTexture(), RainimatorSkulls.CIARA_HEAD.get(), RainimatorSkulls.CIARA_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.HILDA, HildaEntity.texture.getTexture(), RainimatorSkulls.HILDA_HEAD.get(), RainimatorSkulls.HILDA_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.SOLDIERS, SoldiersEntity.texture.getTexture(), RainimatorSkulls.SOLDIERS_HEAD.get(), RainimatorSkulls.SOLDIERS_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.WITHERED_SKELETONS, WitheredSkeletonsEntity.texture.getTexture(), RainimatorSkulls.WITHERED_SKELETONS_HEAD.get(), RainimatorSkulls.WITHERED_SKELETONS_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.DARK_SHIELD, DarkShieldEntity.texture.getTexture(), RainimatorSkulls.DARK_SHIELD_HEAD.get(), RainimatorSkulls.DARK_SHIELD_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.DARK_ZOMBIE, DarkZombieEntity.texture.getTexture(), RainimatorSkulls.DARK_ZOMBIE_HEAD.get(), RainimatorSkulls.DARK_ZOMBIE_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.VORDUS, VordusEntity.texture.getTexture(), RainimatorSkulls.VORDUS_HEAD.get(), RainimatorSkulls.VORDUS_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.WITHER_SHIELD, WitherShieldEntity.texture.getTexture(), RainimatorSkulls.WITHER_SHIELD_HEAD.get(), RainimatorSkulls.WITHER_SHIELD_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.SKELETON_SNOW, SkeletonSnowEntity.texture.getTexture(), RainimatorSkulls.SKELETON_SNOW_HEAD.get(), RainimatorSkulls.SKELETON_SNOW_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.PIGLIN_KING, ZombiesPiglinKingEntity.texture.getTexture(), RainimatorSkulls.PIGLIN_KING_HEAD.get(), RainimatorSkulls.PIGLIN_KING_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.GLUTTON_1, GluttonEntity.texture.getTexture(Stage.First), RainimatorSkulls.GLUTTON_1_HEAD.get(), RainimatorSkulls.GLUTTON_1_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.GLUTTON_2, GluttonEntity.texture.getTexture(Stage.Second), RainimatorSkulls.GLUTTON_2_HEAD.get(), RainimatorSkulls.GLUTTON_2_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.PORKSHIRE, PiglinCommanderEntity.texture.getTexture(), RainimatorSkulls.PORKSHIRE_HEAD.get(), RainimatorSkulls.PORKSHIRE_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.NAEUS_KING, NaeusKingEntity.texture.getTexture(), RainimatorSkulls.NAEUS_KING_HEAD.get(), RainimatorSkulls.NAEUS_KING_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.TUSK, TuskEntity.texture.getTexture(), RainimatorSkulls.TUSK_HEAD.get(), RainimatorSkulls.TUSK_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.BROTS, BrotsEntity.texture.getTexture(), RainimatorSkulls.BROTS_HEAD.get(), RainimatorSkulls.BROTS_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.ZOMBIE_PIG, ZombiePiglinArtEntity.texture.getTexture(), RainimatorSkulls.ZOMBIE_PIG_HEAD.get(), RainimatorSkulls.ZOMBIE_PIG_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.MUTATED, MutatedEntity.texture.getTexture(), RainimatorSkulls.MUTATED_HEAD.get(), RainimatorSkulls.MUTATED_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.NAMTAR, NamtarEntity.texture.getTexture(), RainimatorSkulls.NAMTAR_HEAD.get(), RainimatorSkulls.NAMTAR_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.AGETHA, AgethaEntity.texture.getTexture(), RainimatorSkulls.AGETHA_HEAD.get(), RainimatorSkulls.AGETHA_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.BIG_BLACK_SKELETON, BigUndeadSkeletonEntity.texture.getTexture(), RainimatorSkulls.BIG_BLACK_SKELETON_HEAD.get(), RainimatorSkulls.BIG_BLACK_SKELETON_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.ARCHER, ArcherEntity.texture.getTexture(), RainimatorSkulls.ARCHER_HEAD.get(), RainimatorSkulls.ARCHER_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.DARYLL, DaryllEntity.texture.getTexture(), RainimatorSkulls.DARYLL_HEAD.get(), RainimatorSkulls.DARYLL_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.NULL_LIKE, NullLikeEntity.texture.getTexture(), RainimatorSkulls.NULL_LIKE_HEAD.get(), RainimatorSkulls.NULL_LIKE_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.GIGABONE, GigaBoneEntity.texture.getTexture(), RainimatorSkulls.GIGABONE_HEAD.get(), RainimatorSkulls.GIGABONE_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.KLAUS, KlausEntity.texture.getTexture(), RainimatorSkulls.KLAUS_HEAD.get(), RainimatorSkulls.KLAUS_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.KLAUS_2, Klaus2Entity.texture.getTexture(), RainimatorSkulls.KLAUS_2_HEAD.get(), RainimatorSkulls.KLAUS_2_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.KRALOS, KralosEntity.texture.getTexture(), RainimatorSkulls.KRALOS_HEAD.get(), RainimatorSkulls.KRALOS_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.ARABELLA, ArabellaEntity.texture.getTexture(), RainimatorSkulls.ARABELLA_HEAD.get(), RainimatorSkulls.ARABELLA_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.AZALEA, AzaleaEntity.texture.getTexture(), RainimatorSkulls.AZALEA_HEAD.get(), RainimatorSkulls.AZALEA_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.KYLE, KyleEntity.texture.getTexture(), RainimatorSkulls.KYLE_HEAD.get(), RainimatorSkulls.KYLE_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.SCORCH, ScorchEntity.texture.getTexture(), RainimatorSkulls.SCORCH_HEAD.get(), RainimatorSkulls.SCORCH_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.STELLA, StellaEntity.texture.getTexture(), RainimatorSkulls.STELLA_HEAD.get(), RainimatorSkulls.STELLA_WALL_HEAD.get());
        SkullRenderRegistry.register(RainimatorSkulls.SkullType.STELLA_DEMON, StellaDemonEntity.texture.getTexture(), RainimatorSkulls.STELLA_DEMON_HEAD.get(), RainimatorSkulls.STELLA_DEMON_WALL_HEAD.get());
    }

    public static void registerGui() {
        MenuRegistry.registerScreenFactory(RainimatorScreenHandlers.ENDER_BOOK.get(), EnderBookSkillScreen::new);
    }
}
