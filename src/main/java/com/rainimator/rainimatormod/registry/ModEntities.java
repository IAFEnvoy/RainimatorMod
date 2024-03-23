package com.rainimator.rainimatormod.registry;

import com.rainimator.rainimatormod.RainimatorMod;
import com.rainimator.rainimatormod.entity.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities {
    public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITIES, RainimatorMod.MOD_ID);

    private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryName, EntityType.Builder<T> entityTypeBuilder) {
        return REGISTRY.register(registryName, () -> entityTypeBuilder.build(registryName));
    }

    @SubscribeEvent
    public static void init(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ZombiesEntity.init();
            SoldiersEntity.init();
            HildaEntity.init();
            WitheredSkeletonsEntity.init();
            DarkZombieEntity.init();
            DarkShieldEntity.init();
            WitherShieldEntity.init();
            SkeletonSnowEntity.init();
            TuskEntity.init();
            BrotsEntity.init();
            AgethaEntity.init();
            ArcherEntity.init();
        });
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(HEROBRINE.get(), HerobrineEntity.createAttributes().build());
        event.put(CERIS.get(), CerisEntity.createAttributes().build());
        event.put(ZOMBIES.get(), ZombiesEntity.createAttributes().build());
        event.put(NAEUS.get(), NaeusEntity.createAttributes().build());
        event.put(RAIN.get(), RainEntity.createAttributes().build());
        event.put(ABIGAIL.get(), AbigailEntity.createAttributes().build());
        event.put(PATRICK.get(), PatrickEntity.createAttributes().build());
        event.put(BLACKBONE.get(), BlackBoneEntity.createAttributes().build());
        event.put(HOGSWORTH.get(), HogsworthEntity.createAttributes().build());
        event.put(SOLDIERS.get(), SoldiersEntity.createAttributes().build());
        event.put(CIARA.get(), CiaraEntity.createAttributes().build());
        event.put(HILDA.get(), HildaEntity.createAttributes().build());
        event.put(WITHERED_SKELETONS.get(), WitheredSkeletonsEntity.createAttributes().build());
        event.put(VORDUS.get(), VordusEntity.createAttributes().build());
        event.put(DARK_ZOMBIE.get(), DarkZombieEntity.createAttributes().build());
        event.put(DARK_SHIELD.get(), DarkShieldEntity.createAttributes().build());
        event.put(WITHER_SHIELD.get(), WitherShieldEntity.createAttributes().build());
        event.put(SKELETON_SNOW.get(), SkeletonSnowEntity.createAttributes().build());
        event.put(ARABELLA.get(), ArabellaEntity.createAttributes().build());
        event.put(AZALEA.get(), AzaleaEntity.createAttributes().build());
        event.put(NULL_LIKE.get(), NullLikeEntity.createAttributes().build());
        event.put(ZOMBIE_PIGLIN_KING.get(), ZombiesPiglinKingEntity.createAttributes().build());
        event.put(PIGLIN_KING_ZOMBIES.get(), PiglinKingZombiesEntity.createAttributes().build());
        event.put(PIGLIN_KING_ZOMBIE.get(), PiglinKingZombieEntity.createAttributes().build());
        event.put(PIGLIN_COMMANDER.get(), PiglinCommanderEntity.createAttributes().build());
        event.put(DARYLL.get(), DaryllEntity.createAttributes().build());
        event.put(NAEUS_KING.get(), NaeusKingEntity.createAttributes().build());
        event.put(TUSK.get(), TuskEntity.createAttributes().build());
        event.put(BROTS.get(), BrotsEntity.createAttributes().build());
        event.put(ZOMBIE_PIGLIN_ART.get(), ZombiePiglinArtEntity.createAttributes().build());
        event.put(MUTATED.get(), MutatedEntity.createAttributes().build());
        event.put(NAMTAR.get(), NamtarEntity.createAttributes().build());
        event.put(AGETHA.get(), AgethaEntity.createAttributes().build());
        event.put(TRICER.get(), TricerEntity.createAttributes().build());
        event.put(BIG_UNDEAD_SKELETON.get(), BiGunDeadSkeletonEntity.createAttributes().build());
        event.put(ARCHER.get(), ArcherEntity.createAttributes().build());
        event.put(GIGABONE.get(), GigaBoneEntity.createAttributes().build());
        event.put(KLAUS.get(), KlausEntity.createAttributes().build());
        event.put(KLAUS_2.get(), Klaus2Entity.createAttributes().build());
        event.put(KRALOS.get(), KralosEntity.createAttributes().build());
    }

    public static final RegistryObject<EntityType<HerobrineEntity>> HEROBRINE = register("herobrine", EntityType.Builder.<HerobrineEntity>of(HerobrineEntity::new, MobCategory.UNDERGROUND_WATER_CREATURE).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(HerobrineEntity::new).fireImmune().sized(0.6F, 1.8F));
    public static final RegistryObject<EntityType<CerisEntity>> CERIS = register("ceris", EntityType.Builder.<CerisEntity>of(CerisEntity::new, MobCategory.UNDERGROUND_WATER_CREATURE).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CerisEntity::new).fireImmune().sized(0.6F, 1.8F));
    public static final RegistryObject<EntityType<ZombiesEntity>> ZOMBIES = register("zombies", EntityType.Builder.<ZombiesEntity>of(ZombiesEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(32).setUpdateInterval(3).setCustomClientFactory(ZombiesEntity::new).sized(0.6F, 1.8F));
    public static final RegistryObject<EntityType<NaeusEntity>> NAEUS = register("naeus", EntityType.Builder.<NaeusEntity>of(NaeusEntity::new, MobCategory.UNDERGROUND_WATER_CREATURE).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(NaeusEntity::new).fireImmune().sized(0.6F, 1.8F));
    public static final RegistryObject<EntityType<RainEntity>> RAIN = register("rain", EntityType.Builder.<RainEntity>of(RainEntity::new, MobCategory.UNDERGROUND_WATER_CREATURE).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(RainEntity::new).sized(0.6F, 1.8F));
    public static final RegistryObject<EntityType<RainEntityProjectile>> RAIN_PROJECTILE = register("projectile_rain", EntityType.Builder.<RainEntityProjectile>of(RainEntityProjectile::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).setCustomClientFactory(RainEntityProjectile::new).sized(0.5F, 0.5F));
    public static final RegistryObject<EntityType<AbigailEntity>> ABIGAIL = register("abigail", EntityType.Builder.<AbigailEntity>of(AbigailEntity::new, MobCategory.UNDERGROUND_WATER_CREATURE).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(AbigailEntity::new).fireImmune().sized(0.6F, 1.8F));
    public static final RegistryObject<EntityType<AbigailEntityProjectile>> ABIGAIL_PROJECTILE = register("projectile_abigail", EntityType.Builder.<AbigailEntityProjectile>of(AbigailEntityProjectile::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).setCustomClientFactory(AbigailEntityProjectile::new).sized(0.5F, 0.5F));
    public static final RegistryObject<EntityType<PatrickEntity>> PATRICK = register("patrick", EntityType.Builder.<PatrickEntity>of(PatrickEntity::new, MobCategory.UNDERGROUND_WATER_CREATURE).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(PatrickEntity::new).sized(0.6F, 1.8F));
    public static final RegistryObject<EntityType<PatrickEntityProjectile>> PATRICK_PROJECTILE = register("projectile_patrick", EntityType.Builder.<PatrickEntityProjectile>of(PatrickEntityProjectile::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).setCustomClientFactory(PatrickEntityProjectile::new).sized(0.5F, 0.5F));
    public static final RegistryObject<EntityType<BlackBoneEntity>> BLACKBONE = register("blackbone", EntityType.Builder.<BlackBoneEntity>of(BlackBoneEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(BlackBoneEntity::new).fireImmune().sized(0.6F, 1.8F));
    public static final RegistryObject<EntityType<HogsworthEntity>> HOGSWORTH = register("hogsworth", EntityType.Builder.<HogsworthEntity>of(HogsworthEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(HogsworthEntity::new).fireImmune().sized(0.6F, 1.8F));
    public static final RegistryObject<EntityType<SoldiersEntity>> SOLDIERS = register("soldiers", EntityType.Builder.<SoldiersEntity>of(SoldiersEntity::new, MobCategory.UNDERGROUND_WATER_CREATURE).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(SoldiersEntity::new).sized(0.6F, 1.8F));
    public static final RegistryObject<EntityType<SoldiersEntityProjectile>> SOLDIERS_PROJECTILE = register("projectile_soldiers", EntityType.Builder.<SoldiersEntityProjectile>of(SoldiersEntityProjectile::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).setCustomClientFactory(SoldiersEntityProjectile::new).sized(0.5F, 0.5F));
    public static final RegistryObject<EntityType<CiaraEntity>> CIARA = register("ciara", EntityType.Builder.<CiaraEntity>of(CiaraEntity::new, MobCategory.UNDERGROUND_WATER_CREATURE).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CiaraEntity::new).sized(0.6F, 1.8F));
    public static final RegistryObject<EntityType<CiaraEntityProjectile>> CIARA_PROJECTILE = register("projectile_ciara", EntityType.Builder.<CiaraEntityProjectile>of(CiaraEntityProjectile::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).setCustomClientFactory(CiaraEntityProjectile::new).sized(0.5F, 0.5F));
    public static final RegistryObject<EntityType<HildaEntity>> HILDA = register("hilda", EntityType.Builder.<HildaEntity>of(HildaEntity::new, MobCategory.UNDERGROUND_WATER_CREATURE).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(HildaEntity::new).sized(0.6F, 1.8F));
    public static final RegistryObject<EntityType<HildaEntityProjectile>> HILDA_PROJECTILE = register("projectile_hilda", EntityType.Builder.<HildaEntityProjectile>of(HildaEntityProjectile::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).setCustomClientFactory(HildaEntityProjectile::new).sized(0.5F, 0.5F));
    public static final RegistryObject<EntityType<WitheredSkeletonsEntity>> WITHERED_SKELETONS = register("withered_skeletons", EntityType.Builder.<WitheredSkeletonsEntity>of(WitheredSkeletonsEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(32).setUpdateInterval(3).setCustomClientFactory(WitheredSkeletonsEntity::new).fireImmune().sized(0.6F, 1.8F));
    public static final RegistryObject<EntityType<EndStaffEntity>> END_STAFF = register("projectile_end_staff", EntityType.Builder.<EndStaffEntity>of(EndStaffEntity::new, MobCategory.MISC).setCustomClientFactory(EndStaffEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5F, 0.5F));
    public static final RegistryObject<EntityType<VordusEntity>> VORDUS = register("vordus", EntityType.Builder.<VordusEntity>of(VordusEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(VordusEntity::new).sized(0.6F, 1.8F));
    public static final RegistryObject<EntityType<DarkZombieEntity>> DARK_ZOMBIE = register("dark_zombie", EntityType.Builder.<DarkZombieEntity>of(DarkZombieEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(32).setUpdateInterval(3).setCustomClientFactory(DarkZombieEntity::new).sized(0.6F, 1.8F));
    public static final RegistryObject<EntityType<DarkShieldEntity>> DARK_SHIELD = register("dark_shield", EntityType.Builder.<DarkShieldEntity>of(DarkShieldEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(32).setUpdateInterval(3).setCustomClientFactory(DarkShieldEntity::new).sized(0.6F, 1.8F));
    public static final RegistryObject<EntityType<WitherShieldEntity>> WITHER_SHIELD = register("wither_shield", EntityType.Builder.<WitherShieldEntity>of(WitherShieldEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(32).setUpdateInterval(3).setCustomClientFactory(WitherShieldEntity::new).fireImmune().sized(0.6F, 1.8F));
    public static final RegistryObject<EntityType<SkeletonSnowEntity>> SKELETON_SNOW = register("skeleton_snow", EntityType.Builder.<SkeletonSnowEntity>of(SkeletonSnowEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(32).setUpdateInterval(3).setCustomClientFactory(SkeletonSnowEntity::new).sized(0.6F, 1.8F));
    public static final RegistryObject<EntityType<ArabellaEntity>> ARABELLA = register("arabella", EntityType.Builder.<ArabellaEntity>of(ArabellaEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(ArabellaEntity::new).sized(0.6F, 1.8F));
    public static final RegistryObject<EntityType<AzaleaEntity>> AZALEA = register("azalea", EntityType.Builder.<AzaleaEntity>of(AzaleaEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(AzaleaEntity::new).sized(0.6F, 1.8F));
    public static final RegistryObject<EntityType<NullLikeEntity>> NULL_LIKE = register("null_like", EntityType.Builder.<NullLikeEntity>of(NullLikeEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(NullLikeEntity::new).fireImmune().sized(0.6F, 1.8F));
    public static final RegistryObject<EntityType<ZombiesPiglinKingEntity>> ZOMBIE_PIGLIN_KING = register("zombie_piglin_king", EntityType.Builder.<ZombiesPiglinKingEntity>of(ZombiesPiglinKingEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(ZombiesPiglinKingEntity::new).fireImmune().sized(0.6F, 1.8F));
    public static final RegistryObject<EntityType<PiglinKingZombiesEntity>> PIGLIN_KING_ZOMBIES = register("piglin_king_zombies", EntityType.Builder.<PiglinKingZombiesEntity>of(PiglinKingZombiesEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(PiglinKingZombiesEntity::new).fireImmune().sized(0.6F, 1.8F));
    public static final RegistryObject<EntityType<PiglinKingZombieEntity>> PIGLIN_KING_ZOMBIE = register("piglin_king_zombie", EntityType.Builder.<PiglinKingZombieEntity>of(PiglinKingZombieEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(PiglinKingZombieEntity::new).fireImmune().sized(0.6F, 1.8F));
    public static final RegistryObject<EntityType<PiglinCommanderEntity>> PIGLIN_COMMANDER = register("piglin_commander", EntityType.Builder.<PiglinCommanderEntity>of(PiglinCommanderEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(PiglinCommanderEntity::new).fireImmune().sized(0.6F, 1.8F));
    public static final RegistryObject<EntityType<DaryllEntity>> DARYLL = register("daryll", EntityType.Builder.<DaryllEntity>of(DaryllEntity::new, MobCategory.UNDERGROUND_WATER_CREATURE).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(DaryllEntity::new).sized(0.6F, 1.8F));
    public static final RegistryObject<EntityType<DaryllEntityProjectile>> DARYLL_PROJECTILE = register("projectile_daryll", EntityType.Builder.<DaryllEntityProjectile>of(DaryllEntityProjectile::new, MobCategory.MISC).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).setCustomClientFactory(DaryllEntityProjectile::new).sized(0.5F, 0.5F));
    public static final RegistryObject<EntityType<NaeusKingEntity>> NAEUS_KING = register("naeus_king", EntityType.Builder.<NaeusKingEntity>of(NaeusKingEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(NaeusKingEntity::new).fireImmune().sized(0.6F, 1.8F));
    public static final RegistryObject<EntityType<TuskEntity>> TUSK = register("tusk", EntityType.Builder.<TuskEntity>of(TuskEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TuskEntity::new).fireImmune().sized(0.6F, 1.8F));
    public static final RegistryObject<EntityType<BrotsEntity>> BROTS = register("brots", EntityType.Builder.<BrotsEntity>of(BrotsEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(BrotsEntity::new).fireImmune().sized(0.6F, 1.8F));
    public static final RegistryObject<EntityType<ZombiePiglinArtEntity>> ZOMBIE_PIGLIN_ART = register("zombie_piglin_art", EntityType.Builder.<ZombiePiglinArtEntity>of(ZombiePiglinArtEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(ZombiePiglinArtEntity::new).fireImmune().sized(0.6F, 1.8F));
    public static final RegistryObject<EntityType<MutatedEntity>> MUTATED = register("mutated", EntityType.Builder.<MutatedEntity>of(MutatedEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(MutatedEntity::new).fireImmune().sized(0.6F, 1.8F));
    public static final RegistryObject<EntityType<NamtarEntity>> NAMTAR = register("namtar", EntityType.Builder.<NamtarEntity>of(NamtarEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(NamtarEntity::new).fireImmune().sized(0.6F, 1.8F));
    public static final RegistryObject<EntityType<AgethaEntity>> AGETHA = register("agetha", EntityType.Builder.<AgethaEntity>of(AgethaEntity::new, MobCategory.UNDERGROUND_WATER_CREATURE).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(AgethaEntity::new).sized(0.6F, 1.8F));
    public static final RegistryObject<EntityType<TricerEntity>> TRICER = register("tricer", EntityType.Builder.<TricerEntity>of(TricerEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(TricerEntity::new).fireImmune().sized(0.6F, 1.8F));
    public static final RegistryObject<EntityType<BiGunDeadSkeletonEntity>> BIG_UNDEAD_SKELETON = register("big_undead_skeleton", EntityType.Builder.<BiGunDeadSkeletonEntity>of(BiGunDeadSkeletonEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(BiGunDeadSkeletonEntity::new).fireImmune().sized(0.6F, 1.8F));
    public static final RegistryObject<EntityType<ArcherEntity>> ARCHER = register("archer", EntityType.Builder.<ArcherEntity>of(ArcherEntity::new, MobCategory.UNDERGROUND_WATER_CREATURE).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(ArcherEntity::new).sized(0.6F, 1.8F));
    public static final RegistryObject<EntityType<GigaBoneEntity>> GIGABONE = register("gigabone", EntityType.Builder.<GigaBoneEntity>of(GigaBoneEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(GigaBoneEntity::new).fireImmune().sized(0.6F, 1.8F));
    public static final RegistryObject<EntityType<KlausEntity>> KLAUS = register("klaus", EntityType.Builder.<KlausEntity>of(KlausEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(KlausEntity::new).fireImmune().sized(0.6F, 1.8F));
    public static final RegistryObject<EntityType<Klaus2Entity>> KLAUS_2 = register("klaus_2", EntityType.Builder.<Klaus2Entity>of(Klaus2Entity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(Klaus2Entity::new).fireImmune().sized(0.6F, 1.8F));
    public static final RegistryObject<EntityType<KralosEntity>> KRALOS = register("kralos", EntityType.Builder.<KralosEntity>of(KralosEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(KralosEntity::new).fireImmune().sized(0.6F, 1.8F));


}
