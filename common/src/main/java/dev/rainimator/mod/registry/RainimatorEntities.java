package dev.rainimator.mod.registry;

import dev.architectury.hooks.level.biome.SpawnProperties;
import dev.architectury.registry.level.biome.BiomeModifications;
import dev.architectury.registry.level.entity.EntityAttributeRegistry;
import dev.architectury.registry.level.entity.SpawnPlacementsRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.rainimator.mod.ModConstants;
import dev.rainimator.mod.RainimatorMod;
import dev.rainimator.mod.entity.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.world.Difficulty;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.SpawnSettings;

import java.util.function.Supplier;

public class RainimatorEntities {
    public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(RainimatorMod.MOD_ID, RegistryKeys.ENTITY_TYPE);
    public static final RegistrySupplier<EntityType<HerobrineEntity>> HEROBRINE = build("herobrine", HerobrineEntity::new, SpawnGroup.UNDERGROUND_WATER_CREATURE, 64, 3, true, 0.6F, 1.8F);
    public static final RegistrySupplier<EntityType<CerisEntity>> CERIS = build("ceris", CerisEntity::new, SpawnGroup.UNDERGROUND_WATER_CREATURE, 64, 3, true, 0.6F, 1.8F);
    public static final RegistrySupplier<EntityType<EliteZombieEntity>> ELITE_ZOMBIE = build("elite_zombie", EliteZombieEntity::new, SpawnGroup.MONSTER, 32, 3, false, 0.6F, 1.8F);
    public static final RegistrySupplier<EntityType<NaeusEntity>> NAEUS = build("naeus", NaeusEntity::new, SpawnGroup.UNDERGROUND_WATER_CREATURE, 64, 3, true, 0.6F, 1.8F);
    public static final RegistrySupplier<EntityType<RainEntity>> RAIN = build("rain", RainEntity::new, SpawnGroup.UNDERGROUND_WATER_CREATURE, 64, 3, false, 0.6F, 1.8F);
    public static final RegistrySupplier<EntityType<RainEntityProjectile>> RAIN_PROJECTILE = build("rain_projectile", RainEntityProjectile::new, SpawnGroup.MISC, 64, 1, false, 0.5F, 0.5F);
    public static final RegistrySupplier<EntityType<AbigailEntity>> ABIGAIL = build("abigail", AbigailEntity::new, SpawnGroup.UNDERGROUND_WATER_CREATURE, 64, 3, true, 0.6F, 1.8F);
    public static final RegistrySupplier<EntityType<AbigailEntityProjectile>> ABIGAIL_PROJECTILE = build("abigail_projectile", AbigailEntityProjectile::new, SpawnGroup.MISC, 64, 1, false, 0.5F, 0.5F);
    public static final RegistrySupplier<EntityType<PatrickEntity>> PATRICK = build("patrick", PatrickEntity::new, SpawnGroup.UNDERGROUND_WATER_CREATURE, 64, 3, false, 0.6F, 1.8F);
    public static final RegistrySupplier<EntityType<PatrickEntityProjectile>> PATRICK_PROJECTILE = build("patrick_projectile", PatrickEntityProjectile::new, SpawnGroup.MISC, 64, 1, false, 0.5F, 0.5F);
    public static final RegistrySupplier<EntityType<BlackBoneEntity>> BLACKBONE = build("blackbone", BlackBoneEntity::new, SpawnGroup.MONSTER, 64, 3, true, 0.6F, 1.8F);
    public static final RegistrySupplier<EntityType<HogsworthEntity>> HOGSWORTH = build("hogsworth", HogsworthEntity::new, SpawnGroup.MONSTER, 64, 3, true, 0.6F, 1.8F);
    public static final RegistrySupplier<EntityType<SoldiersEntity>> SOLDIERS = build("soldiers", SoldiersEntity::new, SpawnGroup.UNDERGROUND_WATER_CREATURE, 64, 3, false, 0.6F, 1.8F);
    public static final RegistrySupplier<EntityType<SoldiersEntityProjectile>> SOLDIERS_PROJECTILE = build("soldiers_projectile", SoldiersEntityProjectile::new, SpawnGroup.MISC, 64, 1, false, 0.5F, 0.5F);
    public static final RegistrySupplier<EntityType<CiaraEntity>> CIARA = build("ciara", CiaraEntity::new, SpawnGroup.UNDERGROUND_WATER_CREATURE, 64, 3, false, 0.6F, 1.8F);
    public static final RegistrySupplier<EntityType<CiaraEntityProjectile>> CIARA_PROJECTILE = build("ciara_projectile", CiaraEntityProjectile::new, SpawnGroup.MISC, 64, 1, false, 0.5F, 0.5F);
    public static final RegistrySupplier<EntityType<HildaEntity>> HILDA = build("hilda", HildaEntity::new, SpawnGroup.UNDERGROUND_WATER_CREATURE, 64, 3, false, 0.6F, 1.8F);
    public static final RegistrySupplier<EntityType<HildaEntityProjectile>> HILDA_PROJECTILE = build("hilda_projectile", HildaEntityProjectile::new, SpawnGroup.MISC, 64, 1, false, 0.5F, 0.5F);
    public static final RegistrySupplier<EntityType<WitheredSkeletonsEntity>> WITHERED_SKELETONS = build("withered_skeletons", WitheredSkeletonsEntity::new, SpawnGroup.MONSTER, 32, 3, true, 0.6F, 1.8F);
    public static final RegistrySupplier<EntityType<EndStaffEntity>> END_STAFF = build("end_staff", EndStaffEntity::new, SpawnGroup.MISC, 64, 1, false, 0.5F, 0.5F);
    public static final RegistrySupplier<EntityType<VordusEntity>> VORDUS = build("vordus", VordusEntity::new, SpawnGroup.MONSTER, 64, 3, false, 0.6F, 1.8F);
    public static final RegistrySupplier<EntityType<DarkZombieEntity>> DARK_ZOMBIE = build("dark_zombie", DarkZombieEntity::new, SpawnGroup.MONSTER, 32, 3, false, 0.6F, 1.8F);
    public static final RegistrySupplier<EntityType<DarkShieldEntity>> DARK_SHIELD = build("dark_shield", DarkShieldEntity::new, SpawnGroup.MONSTER, 32, 3, false, 0.6F, 1.8F);
    public static final RegistrySupplier<EntityType<WitherShieldEntity>> WITHER_SHIELD = build("wither_shield", WitherShieldEntity::new, SpawnGroup.MONSTER, 32, 3, true, 0.6F, 1.8F);
    public static final RegistrySupplier<EntityType<SkeletonSnowEntity>> SKELETON_SNOW = build("skeleton_snow", SkeletonSnowEntity::new, SpawnGroup.MONSTER, 32, 3, false, 0.6F, 1.8F);
    public static final RegistrySupplier<EntityType<ArabellaEntity>> ARABELLA = build("arabella", ArabellaEntity::new, SpawnGroup.MONSTER, 64, 3, false, 0.6F, 1.8F);
    public static final RegistrySupplier<EntityType<AzaleaEntity>> AZALEA = build("azalea", AzaleaEntity::new, SpawnGroup.MONSTER, 64, 3, false, 0.6F, 1.8F);
    public static final RegistrySupplier<EntityType<NullLikeEntity>> NULL_LIKE = build("null_like", NullLikeEntity::new, SpawnGroup.MONSTER, 64, 3, true, 0.6F, 1.8F);
    public static final RegistrySupplier<EntityType<ZombiesPiglinKingEntity>> ZOMBIE_PIGLIN_KING = build("zombie_piglin_king", ZombiesPiglinKingEntity::new, SpawnGroup.MONSTER, 64, 3, true, 0.6F, 1.8F);
    public static final RegistrySupplier<EntityType<GluttonEntity>> GLUTTON = build("glutton", GluttonEntity::new, SpawnGroup.MONSTER, 64, 3, true, 0.6F, 1.8F);
    public static final RegistrySupplier<EntityType<PiglinCommanderEntity>> PIGLIN_COMMANDER = build("piglin_commander", PiglinCommanderEntity::new, SpawnGroup.MONSTER, 64, 3, true, 0.6F, 1.8F);
    public static final RegistrySupplier<EntityType<DaryllEntity>> DARYLL = build("daryll", DaryllEntity::new, SpawnGroup.UNDERGROUND_WATER_CREATURE, 64, 3, false, 0.6F, 1.8F);
    public static final RegistrySupplier<EntityType<DaryllEntityProjectile>> DARYLL_PROJECTILE = build("daryll_projectile", DaryllEntityProjectile::new, SpawnGroup.MISC, 64, 1, false, 0.5F, 0.5F);
    public static final RegistrySupplier<EntityType<NaeusKingEntity>> NAEUS_KING = build("naeus_king", NaeusKingEntity::new, SpawnGroup.MONSTER, 64, 3, true, 0.6F, 1.8F);
    public static final RegistrySupplier<EntityType<TuskEntity>> TUSK = build("tusk", TuskEntity::new, SpawnGroup.MONSTER, 64, 3, true, 0.6F, 1.8F);
    public static final RegistrySupplier<EntityType<BrotsEntity>> BROTS = build("brots", BrotsEntity::new, SpawnGroup.MONSTER, 64, 3, true, 0.6F, 1.8F);
    public static final RegistrySupplier<EntityType<ZombiePiglinArtEntity>> ZOMBIE_PIGLIN_ART = build("zombie_piglin_art", ZombiePiglinArtEntity::new, SpawnGroup.MONSTER, 64, 3, true, 0.6F, 1.8F);
    public static final RegistrySupplier<EntityType<MutatedEntity>> MUTATED = build("mutated", MutatedEntity::new, SpawnGroup.MONSTER, 64, 3, true, 0.6F, 1.8F);
    public static final RegistrySupplier<EntityType<NamtarEntity>> NAMTAR = build("namtar", NamtarEntity::new, SpawnGroup.MONSTER, 64, 3, true, 0.6F, 1.8F);
    public static final RegistrySupplier<EntityType<AgethaEntity>> AGETHA = build("agetha", AgethaEntity::new, SpawnGroup.UNDERGROUND_WATER_CREATURE, 64, 3, false, 0.6F, 1.8F);
    public static final RegistrySupplier<EntityType<BigUndeadSkeletonEntity>> BIG_UNDEAD_SKELETON = build("big_undead_skeleton", BigUndeadSkeletonEntity::new, SpawnGroup.MONSTER, 64, 3, true, 0.6F, 1.8F);
    public static final RegistrySupplier<EntityType<ArcherEntity>> ARCHER = build("archer", ArcherEntity::new, SpawnGroup.UNDERGROUND_WATER_CREATURE, 64, 3, false, 0.6F, 1.8F);
    public static final RegistrySupplier<EntityType<GigaBoneEntity>> GIGABONE = build("gigabone", GigaBoneEntity::new, SpawnGroup.MONSTER, 64, 3, true, 0.6F, 1.8F);
    public static final RegistrySupplier<EntityType<KlausEntity>> KLAUS = build("klaus", KlausEntity::new, SpawnGroup.MONSTER, 64, 3, true, 0.6F, 1.8F);
    public static final RegistrySupplier<EntityType<Klaus2Entity>> KLAUS_2 = build("klaus_2", Klaus2Entity::new, SpawnGroup.MONSTER, 64, 3, true, 0.6F, 1.8F);
    public static final RegistrySupplier<EntityType<KralosEntity>> KRALOS = build("kralos", KralosEntity::new, SpawnGroup.MONSTER, 64, 3, true, 0.6F, 1.8F);
    public static final RegistrySupplier<EntityType<KyleEntity>> KYLE = build("kyle", KyleEntity::new, SpawnGroup.MONSTER, 64, 3, false, 0.6F, 1.8F);
    public static final RegistrySupplier<EntityType<ScorchEntity>> SCORCH = build("scorch", ScorchEntity::new, SpawnGroup.MONSTER, 64, 3, true, 0.6F, 1.8F);
    public static final RegistrySupplier<EntityType<StellaEntity>> STELLA = build("stella", StellaEntity::new, SpawnGroup.MONSTER, 64, 3, false, 0.6F, 1.8F);
    public static final RegistrySupplier<EntityType<StellaDemonEntity>> STELLA_DEMON = build("stella_demon", StellaDemonEntity::new, SpawnGroup.MONSTER, 64, 3, false, 0.6F, 1.8F);

    private static <T extends Entity> RegistrySupplier<EntityType<T>> build(String name, EntityType.EntityFactory<T> constructor, SpawnGroup category, int trackingRange, int updateInterval, boolean fireImmune, float sizeX, float sizeY) {
        return register(name, () -> {
            EntityType.Builder<T> builder = EntityType.Builder.create(constructor, category).maxTrackingRange(trackingRange).trackingTickInterval(updateInterval).setDimensions(sizeX, sizeY);
            if (fireImmune) builder.makeFireImmune();
            return builder.build(name);
        });
    }

    private static <T extends Entity> RegistrySupplier<EntityType<T>> register(String name, Supplier<EntityType<T>> type) {
        return REGISTRY.register(name, type);
    }

    public static void registerAttributes() {
        EntityAttributeRegistry.register(HEROBRINE, HerobrineEntity::createAttributes);
        EntityAttributeRegistry.register(CERIS, CerisEntity::createAttributes);
        EntityAttributeRegistry.register(ELITE_ZOMBIE, EliteZombieEntity::createAttributes);
        EntityAttributeRegistry.register(NAEUS, NaeusEntity::createAttributes);
        EntityAttributeRegistry.register(RAIN, RainEntity::createAttributes);
        EntityAttributeRegistry.register(ABIGAIL, AbigailEntity::createAttributes);
        EntityAttributeRegistry.register(PATRICK, PatrickEntity::createAttributes);
        EntityAttributeRegistry.register(BLACKBONE, BlackBoneEntity::createAttributes);
        EntityAttributeRegistry.register(HOGSWORTH, HogsworthEntity::createAttributes);
        EntityAttributeRegistry.register(CIARA, CiaraEntity::createAttributes);
        EntityAttributeRegistry.register(HILDA, HildaEntity::createAttributes);
        EntityAttributeRegistry.register(SOLDIERS, SoldiersEntity::createAttributes);
        EntityAttributeRegistry.register(WITHERED_SKELETONS, WitheredSkeletonsEntity::createAttributes);
        EntityAttributeRegistry.register(DARK_SHIELD, DarkShieldEntity::createAttributes);
        EntityAttributeRegistry.register(DARK_ZOMBIE, DarkZombieEntity::createAttributes);
        EntityAttributeRegistry.register(VORDUS, VordusEntity::createEndermanAttributes);
        EntityAttributeRegistry.register(WITHER_SHIELD, WitherShieldEntity::createAttributes);
        EntityAttributeRegistry.register(SKELETON_SNOW, SkeletonSnowEntity::createAttributes);
        EntityAttributeRegistry.register(ZOMBIE_PIGLIN_KING, ZombiesPiglinKingEntity::createAttributes);
        EntityAttributeRegistry.register(GLUTTON, GluttonEntity::createAttributes);
        EntityAttributeRegistry.register(PIGLIN_COMMANDER, PiglinCommanderEntity::createAttributes);
        EntityAttributeRegistry.register(NAEUS_KING, NaeusKingEntity::createAttributes);
        EntityAttributeRegistry.register(TUSK, TuskEntity::createAttributes);
        EntityAttributeRegistry.register(BROTS, BrotsEntity::createAttributes);
        EntityAttributeRegistry.register(ZOMBIE_PIGLIN_ART, ZombiePiglinArtEntity::createAttributes);
        EntityAttributeRegistry.register(MUTATED, MutatedEntity::createAttributes);
        EntityAttributeRegistry.register(NAMTAR, NamtarEntity::createAttributes);
        EntityAttributeRegistry.register(AGETHA, AgethaEntity::createAttributes);
        EntityAttributeRegistry.register(BIG_UNDEAD_SKELETON, BigUndeadSkeletonEntity::createAttributes);
        EntityAttributeRegistry.register(ARCHER, ArcherEntity::createAttributes);
        EntityAttributeRegistry.register(DARYLL, DaryllEntity::createAttributes);
        EntityAttributeRegistry.register(NULL_LIKE, NullLikeEntity::createAttributes);
        EntityAttributeRegistry.register(GIGABONE, GigaBoneEntity::createAttributes);
        EntityAttributeRegistry.register(KLAUS, KlausEntity::createAttributes);
        EntityAttributeRegistry.register(KLAUS_2, Klaus2Entity::createAttributes);
        EntityAttributeRegistry.register(KRALOS, KralosEntity::createAttributes);
        EntityAttributeRegistry.register(ARABELLA, ArabellaEntity::createAttributes);
        EntityAttributeRegistry.register(AZALEA, AzaleaEntity::createAttributes);
        EntityAttributeRegistry.register(KYLE, KyleEntity::createAttributes);
        EntityAttributeRegistry.register(SCORCH, ScorchEntity::createAttributes);
        EntityAttributeRegistry.register(STELLA, StellaEntity::createAttributes);
        EntityAttributeRegistry.register(STELLA_DEMON, StellaDemonEntity::createAttributes);
    }

    public static void addSpawner() {
        SpawnPlacementsRegistry.register(ELITE_ZOMBIE, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, (entityType, world, reason, pos, random) ->
                world.getDifficulty() != Difficulty.PEACEFUL && HostileEntity.isSpawnDark(world, pos, random) && MobEntity.canMobSpawn(entityType, world, reason, pos, random));
//        DungeonHooks.addDungeonMob(ZOMBIES, 180);
        SpawnPlacementsRegistry.register(SOLDIERS, SpawnRestriction.Location.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, (entityType, world, reason, pos, random) ->
                world.getFluidState(pos.down()).isIn(FluidTags.WATER) && pos.getY() >= ModConstants.SEA_LEVEL - 13 && pos.getY() <= ModConstants.SEA_LEVEL);
        SpawnPlacementsRegistry.register(HILDA, SpawnRestriction.Location.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, (entityType, world, reason, pos, random) ->
                world.getFluidState(pos.down()).isIn(FluidTags.WATER) && pos.getY() >= ModConstants.SEA_LEVEL - 13 && pos.getY() <= ModConstants.SEA_LEVEL);
        SpawnPlacementsRegistry.register(WITHERED_SKELETONS, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, (entityType, world, reason, pos, random) ->
                world.getDifficulty() != Difficulty.PEACEFUL && HostileEntity.isSpawnDark(world, pos, random) && MobEntity.canMobSpawn(entityType, world, reason, pos, random));
        SpawnPlacementsRegistry.register(DARK_ZOMBIE, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, (entityType, world, reason, pos, random) ->
                world.getDifficulty() != Difficulty.PEACEFUL && HostileEntity.isSpawnDark(world, pos, random) && MobEntity.canMobSpawn(entityType, world, reason, pos, random));
        SpawnPlacementsRegistry.register(DARK_SHIELD, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, (entityType, world, reason, pos, random) ->
                world.getDifficulty() != Difficulty.PEACEFUL && HostileEntity.isSpawnDark(world, pos, random) && MobEntity.canMobSpawn(entityType, world, reason, pos, random));
//        DungeonHooks.addDungeonMob(DARK_SHIELD, 180);
        SpawnPlacementsRegistry.register(WITHER_SHIELD, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, (entityType, world, reason, pos, random) ->
                world.getDifficulty() != Difficulty.PEACEFUL && HostileEntity.isSpawnDark(world, pos, random) && MobEntity.canMobSpawn(entityType, world, reason, pos, random));
//        DungeonHooks.addDungeonMob(WITHER_SHIELD, 180);
        SpawnPlacementsRegistry.register(SKELETON_SNOW, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, (entityType, world, reason, pos, random) ->
                world.getDifficulty() != Difficulty.PEACEFUL && HostileEntity.isSpawnDark(world, pos, random) && MobEntity.canMobSpawn(entityType, world, reason, pos, random));
        SpawnPlacementsRegistry.register(TUSK, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, (entityType, world, reason, pos, random) ->
                world.getDifficulty() != Difficulty.PEACEFUL && HostileEntity.isSpawnDark(world, pos, random) && MobEntity.canMobSpawn(entityType, world, reason, pos, random));
//        DungeonHooks.addDungeonMob(TUSK, 180);
        SpawnPlacementsRegistry.register(BROTS, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, (entityType, world, reason, pos, random) ->
                world.getDifficulty() != Difficulty.PEACEFUL && HostileEntity.isSpawnDark(world, pos, random) && MobEntity.canMobSpawn(entityType, world, reason, pos, random));
        SpawnPlacementsRegistry.register(AGETHA, SpawnRestriction.Location.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, (entityType, world, reason, pos, random) ->
                world.getFluidState(pos.down()).isIn(FluidTags.WATER) && pos.getY() >= ModConstants.SEA_LEVEL - 13 && pos.getY() <= ModConstants.SEA_LEVEL);
        SpawnPlacementsRegistry.register(ARCHER, SpawnRestriction.Location.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, (entityType, world, reason, pos, random) ->
                world.getFluidState(pos.down()).isIn(FluidTags.WATER) && pos.getY() >= ModConstants.SEA_LEVEL - 13 && pos.getY() <= ModConstants.SEA_LEVEL);
    }

    public static void addLivingEntityToBiomes() {
        BiomeModifications.addProperties((context, mutable) -> {
            SpawnProperties.Mutable spawnProperties = mutable.getSpawnProperties();
            if (context.hasTag(RainimatorTags.COMMON_SPAWN_BIOMES)) {
                spawnProperties.addSpawn(SpawnGroup.UNDERGROUND_WATER_CREATURE, new SpawnSettings.SpawnEntry(RainimatorEntities.AGETHA.get(), 10, 1, 1));
                spawnProperties.addSpawn(SpawnGroup.UNDERGROUND_WATER_CREATURE, new SpawnSettings.SpawnEntry(RainimatorEntities.ARCHER.get(), 10, 1, 1));
                spawnProperties.addSpawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(RainimatorEntities.BROTS.get(), 5, 1, 1));
                spawnProperties.addSpawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(RainimatorEntities.DARK_ZOMBIE.get(), 10, 1, 1));
                spawnProperties.addSpawn(SpawnGroup.UNDERGROUND_WATER_CREATURE, new SpawnSettings.SpawnEntry(RainimatorEntities.HILDA.get(), 10, 1, 1));
                spawnProperties.addSpawn(SpawnGroup.UNDERGROUND_WATER_CREATURE, new SpawnSettings.SpawnEntry(RainimatorEntities.SOLDIERS.get(), 10, 1, 1));
                spawnProperties.addSpawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(RainimatorEntities.TUSK.get(), 5, 1, 1));
                spawnProperties.addSpawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(RainimatorEntities.WITHER_SHIELD.get(), 3, 1, 1));
                spawnProperties.addSpawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(RainimatorEntities.ELITE_ZOMBIE.get(), 10, 1, 1));
            }
            if (context.hasTag(RainimatorTags.END_SPAWN_BIOMES))
                spawnProperties.addSpawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(RainimatorEntities.DARK_SHIELD.get(), 1, 1, 1));
            if (context.hasTag(RainimatorTags.SNOW_SPAWN_BIOMES))
                spawnProperties.addSpawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(RainimatorEntities.SKELETON_SNOW.get(), 10, 1, 1));
            if (context.hasTag(RainimatorTags.NETHER_SPAWN_BIOMES))
                spawnProperties.addSpawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(RainimatorEntities.WITHERED_SKELETONS.get(), 19, 2, 2));
        });
    }
}
