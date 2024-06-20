package dev.rainimator.mod.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.rainimator.mod.ModConstants;
import dev.rainimator.mod.RainimatorMod;
import dev.rainimator.mod.entity.*;
import dev.rainimator.mod.impl.RainimatorRegistryManager;
import dev.rainimator.mod.util.SpawnBiome;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.Identifier;
import net.minecraft.world.Difficulty;
import net.minecraft.world.Heightmap;

import java.util.function.Supplier;

public class RainimatorEntities {
    public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(RainimatorMod.MOD_ID, RegistryKeys.ENTITY_TYPE);
    public static final RegistrySupplier<EntityType<HerobrineEntity>> HEROBRINE = build("herobrine", HerobrineEntity::new, SpawnGroup.UNDERGROUND_WATER_CREATURE, 64, 3, true, 0.6F, 1.8F);
    public static final RegistrySupplier<EntityType<CerisEntity>> CERIS = build("ceris", CerisEntity::new, SpawnGroup.UNDERGROUND_WATER_CREATURE, 64, 3, true, 0.6F, 1.8F);
    public static final RegistrySupplier<EntityType<ZombiesEntity>> ZOMBIES = build("zombies", ZombiesEntity::new, SpawnGroup.MONSTER, 32, 3, false, 0.6F, 1.8F);
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
    public static final RegistrySupplier<EntityType<PiglinKingZombiesEntity>> PIGLIN_KING_ZOMBIES = build("piglin_king_zombies", PiglinKingZombiesEntity::new, SpawnGroup.MONSTER, 64, 3, true, 0.6F, 1.8F);
    public static final RegistrySupplier<EntityType<PiglinKingZombieEntity>> PIGLIN_KING_ZOMBIE = build("piglin_king_zombie", PiglinKingZombieEntity::new, SpawnGroup.MONSTER, 64, 3, true, 0.6F, 1.8F);
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
    public static final RegistrySupplier<EntityType<TricerEntity>> TRICER = build("tricer", TricerEntity::new, SpawnGroup.MONSTER, 64, 3, true, 0.6F, 1.8F);
    public static final RegistrySupplier<EntityType<BigUndeadSkeletonEntity>> BIG_UNDEAD_SKELETON = build("bug_undead_skeleton", BigUndeadSkeletonEntity::new, SpawnGroup.MONSTER, 64, 3, true, 0.6F, 1.8F);
    public static final RegistrySupplier<EntityType<ArcherEntity>> ARCHER = build("archer", ArcherEntity::new, SpawnGroup.UNDERGROUND_WATER_CREATURE, 64, 3, false, 0.6F, 1.8F);
    public static final RegistrySupplier<EntityType<GigaBoneEntity>> GIGABONE = build("gigabone", GigaBoneEntity::new, SpawnGroup.MONSTER, 64, 3, true, 0.6F, 1.8F);
    public static final RegistrySupplier<EntityType<KlausEntity>> KLAUS = build("klaus", KlausEntity::new, SpawnGroup.MONSTER, 64, 3, true, 0.6F, 1.8F);
    public static final RegistrySupplier<EntityType<Klaus2Entity>> KLAUS_2 = build("klaus_2", Klaus2Entity::new, SpawnGroup.MONSTER, 64, 3, true, 0.6F, 1.8F);
    public static final RegistrySupplier<EntityType<KralosEntity>> KRALOS = build("kralos", KralosEntity::new, SpawnGroup.MONSTER, 64, 3, true, 0.6F, 1.8F);

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
        RainimatorRegistryManager.registerDefaultAttribute(HEROBRINE.get(), HerobrineEntity.createAttributes());
        RainimatorRegistryManager.registerDefaultAttribute(CERIS.get(), CerisEntity.createAttributes());
        RainimatorRegistryManager.registerDefaultAttribute(ZOMBIES.get(), ZombiesEntity.createAttributes());
        RainimatorRegistryManager.registerDefaultAttribute(NAEUS.get(), NaeusEntity.createAttributes());
        RainimatorRegistryManager.registerDefaultAttribute(RAIN.get(), RainEntity.createAttributes());
        RainimatorRegistryManager.registerDefaultAttribute(ABIGAIL.get(), AbigailEntity.createAttributes());
        RainimatorRegistryManager.registerDefaultAttribute(PATRICK.get(), PatrickEntity.createAttributes());
        RainimatorRegistryManager.registerDefaultAttribute(BLACKBONE.get(), BlackBoneEntity.createAttributes());
        RainimatorRegistryManager.registerDefaultAttribute(HOGSWORTH.get(), HogsworthEntity.createAttributes());
        RainimatorRegistryManager.registerDefaultAttribute(CIARA.get(), CiaraEntity.createAttributes());
        RainimatorRegistryManager.registerDefaultAttribute(HILDA.get(), HildaEntity.createAttributes());
        RainimatorRegistryManager.registerDefaultAttribute(SOLDIERS.get(), SoldiersEntity.createAttributes());
        RainimatorRegistryManager.registerDefaultAttribute(WITHERED_SKELETONS.get(), WitheredSkeletonsEntity.createAttributes());
        RainimatorRegistryManager.registerDefaultAttribute(DARK_SHIELD.get(), DarkShieldEntity.createAttributes());
        RainimatorRegistryManager.registerDefaultAttribute(DARK_ZOMBIE.get(), DarkZombieEntity.createAttributes());
        RainimatorRegistryManager.registerDefaultAttribute(VORDUS.get(), VordusEntity.createEndermanAttributes());
        RainimatorRegistryManager.registerDefaultAttribute(WITHER_SHIELD.get(), WitherShieldEntity.createAttributes());
        RainimatorRegistryManager.registerDefaultAttribute(SKELETON_SNOW.get(), SkeletonSnowEntity.createAttributes());
        RainimatorRegistryManager.registerDefaultAttribute(ZOMBIE_PIGLIN_KING.get(), ZombiesPiglinKingEntity.createAttributes());
        RainimatorRegistryManager.registerDefaultAttribute(PIGLIN_KING_ZOMBIES.get(), PiglinKingZombiesEntity.createAttributes());
        RainimatorRegistryManager.registerDefaultAttribute(PIGLIN_KING_ZOMBIE.get(), PiglinKingZombieEntity.createAttributes());
        RainimatorRegistryManager.registerDefaultAttribute(PIGLIN_COMMANDER.get(), PiglinCommanderEntity.createAttributes());
        RainimatorRegistryManager.registerDefaultAttribute(NAEUS_KING.get(), NaeusKingEntity.createAttributes());
        RainimatorRegistryManager.registerDefaultAttribute(TUSK.get(), TuskEntity.createAttributes());
        RainimatorRegistryManager.registerDefaultAttribute(BROTS.get(), BrotsEntity.createAttributes());
        RainimatorRegistryManager.registerDefaultAttribute(ZOMBIE_PIGLIN_ART.get(), ZombiePiglinArtEntity.createAttributes());
        RainimatorRegistryManager.registerDefaultAttribute(MUTATED.get(), MutatedEntity.createAttributes());
        RainimatorRegistryManager.registerDefaultAttribute(NAMTAR.get(), NamtarEntity.createAttributes());
        RainimatorRegistryManager.registerDefaultAttribute(AGETHA.get(), AgethaEntity.createAttributes());
        RainimatorRegistryManager.registerDefaultAttribute(TRICER.get(), TricerEntity.createAttributes());
        RainimatorRegistryManager.registerDefaultAttribute(BIG_UNDEAD_SKELETON.get(), BigUndeadSkeletonEntity.createAttributes());
        RainimatorRegistryManager.registerDefaultAttribute(ARCHER.get(), ArcherEntity.createAttributes());
        RainimatorRegistryManager.registerDefaultAttribute(DARYLL.get(), DaryllEntity.createAttributes());
        RainimatorRegistryManager.registerDefaultAttribute(NULL_LIKE.get(), NullLikeEntity.createAttributes());
        RainimatorRegistryManager.registerDefaultAttribute(GIGABONE.get(), GigaBoneEntity.createAttributes());
        RainimatorRegistryManager.registerDefaultAttribute(KLAUS.get(), KlausEntity.createAttributes());
        RainimatorRegistryManager.registerDefaultAttribute(KLAUS_2.get(), Klaus2Entity.createAttributes());
        RainimatorRegistryManager.registerDefaultAttribute(KRALOS.get(), KralosEntity.createAttributes());
        RainimatorRegistryManager.registerDefaultAttribute(ARABELLA.get(), ArabellaEntity.createAttributes());
        RainimatorRegistryManager.registerDefaultAttribute(AZALEA.get(), AzaleaEntity.createAttributes());
    }

    public static void addSpawner() {
        RainimatorRegistryManager.registerSpawner(ZOMBIES.get(), SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, (entityType, world, reason, pos, random) ->
                world.getDifficulty() != Difficulty.PEACEFUL && HostileEntity.isSpawnDark(world, pos, random) && MobEntity.canMobSpawn(entityType, world, reason, pos, random));
//        DungeonHooks.addDungeonMob(ZOMBIES, 180);
        RainimatorRegistryManager.registerSpawner(SOLDIERS.get(), SpawnRestriction.Location.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, (entityType, world, reason, pos, random) ->
                world.getFluidState(pos.down()).isIn(FluidTags.WATER) && pos.getY() >= ModConstants.SEA_LEVEL - 13 && pos.getY() <= ModConstants.SEA_LEVEL);
        RainimatorRegistryManager.registerSpawner(HILDA.get(), SpawnRestriction.Location.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, (entityType, world, reason, pos, random) ->
                world.getFluidState(pos.down()).isIn(FluidTags.WATER) && pos.getY() >= ModConstants.SEA_LEVEL - 13 && pos.getY() <= ModConstants.SEA_LEVEL);
        RainimatorRegistryManager.registerSpawner(WITHERED_SKELETONS.get(), SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, (entityType, world, reason, pos, random) ->
                world.getDifficulty() != Difficulty.PEACEFUL && HostileEntity.isSpawnDark(world, pos, random) && MobEntity.canMobSpawn(entityType, world, reason, pos, random));
        RainimatorRegistryManager.registerSpawner(DARK_ZOMBIE.get(), SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, (entityType, world, reason, pos, random) ->
                world.getDifficulty() != Difficulty.PEACEFUL && HostileEntity.isSpawnDark(world, pos, random) && MobEntity.canMobSpawn(entityType, world, reason, pos, random));
        RainimatorRegistryManager.registerSpawner(DARK_SHIELD.get(), SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, (entityType, world, reason, pos, random) ->
                world.getDifficulty() != Difficulty.PEACEFUL && HostileEntity.isSpawnDark(world, pos, random) && MobEntity.canMobSpawn(entityType, world, reason, pos, random));
//        DungeonHooks.addDungeonMob(DARK_SHIELD, 180);
        RainimatorRegistryManager.registerSpawner(WITHER_SHIELD.get(), SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, (entityType, world, reason, pos, random) ->
                world.getDifficulty() != Difficulty.PEACEFUL && HostileEntity.isSpawnDark(world, pos, random) && MobEntity.canMobSpawn(entityType, world, reason, pos, random));
//        DungeonHooks.addDungeonMob(WITHER_SHIELD, 180);
        RainimatorRegistryManager.registerSpawner(SKELETON_SNOW.get(), SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, (entityType, world, reason, pos, random) ->
                world.getDifficulty() != Difficulty.PEACEFUL && HostileEntity.isSpawnDark(world, pos, random) && MobEntity.canMobSpawn(entityType, world, reason, pos, random));
        RainimatorRegistryManager.registerSpawner(TUSK.get(), SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, (entityType, world, reason, pos, random) ->
                world.getDifficulty() != Difficulty.PEACEFUL && HostileEntity.isSpawnDark(world, pos, random) && MobEntity.canMobSpawn(entityType, world, reason, pos, random));
//        DungeonHooks.addDungeonMob(TUSK, 180);
        RainimatorRegistryManager.registerSpawner(BROTS.get(), SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, (entityType, world, reason, pos, random) ->
                world.getDifficulty() != Difficulty.PEACEFUL && HostileEntity.isSpawnDark(world, pos, random) && MobEntity.canMobSpawn(entityType, world, reason, pos, random));
        RainimatorRegistryManager.registerSpawner(AGETHA.get(), SpawnRestriction.Location.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, (entityType, world, reason, pos, random) ->
                world.getFluidState(pos.down()).isIn(FluidTags.WATER) && pos.getY() >= ModConstants.SEA_LEVEL - 13 && pos.getY() <= ModConstants.SEA_LEVEL);
        RainimatorRegistryManager.registerSpawner(ARCHER.get(), SpawnRestriction.Location.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, (entityType, world, reason, pos, random) ->
                world.getFluidState(pos.down()).isIn(FluidTags.WATER) && pos.getY() >= ModConstants.SEA_LEVEL - 13 && pos.getY() <= ModConstants.SEA_LEVEL);
    }

    public static void addLivingEntityToBiomes() {
        RainimatorRegistryManager.addSpawnToBiome(SpawnBiome.COMMON_SPAWN_BIOMES::contains, SpawnGroup.UNDERGROUND_WATER_CREATURE, RainimatorEntities.AGETHA.get(), 10, 1, 1);
        RainimatorRegistryManager.addSpawnToBiome(SpawnBiome.COMMON_SPAWN_BIOMES::contains, SpawnGroup.UNDERGROUND_WATER_CREATURE, RainimatorEntities.ARCHER.get(), 10, 1, 1);
        RainimatorRegistryManager.addSpawnToBiome(SpawnBiome.COMMON_SPAWN_BIOMES::contains, SpawnGroup.MONSTER, RainimatorEntities.BROTS.get(), 5, 1, 1);
        RainimatorRegistryManager.addSpawnToBiome(SpawnBiome.END_SPAWN_BIOMES::contains, SpawnGroup.MONSTER, RainimatorEntities.DARK_SHIELD.get(), 1, 1, 1);
        RainimatorRegistryManager.addSpawnToBiome(SpawnBiome.COMMON_SPAWN_BIOMES::contains, SpawnGroup.MONSTER, RainimatorEntities.DARK_ZOMBIE.get(), 10, 1, 1);
        RainimatorRegistryManager.addSpawnToBiome(SpawnBiome.COMMON_SPAWN_BIOMES::contains, SpawnGroup.UNDERGROUND_WATER_CREATURE, RainimatorEntities.HILDA.get(), 10, 1, 1);
        RainimatorRegistryManager.addSpawnToBiome(SpawnBiome.SNOW_SPAWN_BIOMES::contains, SpawnGroup.MONSTER, RainimatorEntities.SKELETON_SNOW.get(), 10, 1, 1);
        RainimatorRegistryManager.addSpawnToBiome(SpawnBiome.COMMON_SPAWN_BIOMES::contains, SpawnGroup.UNDERGROUND_WATER_CREATURE, RainimatorEntities.SOLDIERS.get(), 10, 1, 1);
        RainimatorRegistryManager.addSpawnToBiome(SpawnBiome.COMMON_SPAWN_BIOMES::contains, SpawnGroup.MONSTER, RainimatorEntities.TUSK.get(), 5, 1, 1);
        RainimatorRegistryManager.addSpawnToBiome(identifier -> new Identifier("nether_wastes").equals(identifier), SpawnGroup.MONSTER, RainimatorEntities.WITHERED_SKELETONS.get(), 19, 2, 2);
        RainimatorRegistryManager.addSpawnToBiome(SpawnBiome.COMMON_SPAWN_BIOMES::contains, SpawnGroup.MONSTER, RainimatorEntities.WITHER_SHIELD.get(), 3, 1, 1);
        RainimatorRegistryManager.addSpawnToBiome(SpawnBiome.COMMON_SPAWN_BIOMES::contains, SpawnGroup.MONSTER, RainimatorEntities.ZOMBIES.get(), 10, 1, 1);
    }

    @Environment(EnvType.CLIENT)
    public static void registerEntityRenderers() {
        RainimatorRegistryManager.registerRenderer(HEROBRINE.get(), HerobrineEntity.texture::createRenderer);
        RainimatorRegistryManager.registerRenderer(CERIS.get(), CerisEntity.texture::createRenderer);
        RainimatorRegistryManager.registerRenderer(ZOMBIES.get(), ZombiesEntity.texture::createRenderer);
        RainimatorRegistryManager.registerRenderer(NAEUS.get(), NaeusEntity.texture::createRenderer);
        RainimatorRegistryManager.registerRenderer(RAIN.get(), RainEntity.texture::createRenderer);
        RainimatorRegistryManager.registerRenderer(RAIN_PROJECTILE.get(), FlyingItemEntityRenderer::new);
        RainimatorRegistryManager.registerRenderer(ABIGAIL.get(), AbigailEntity.texture::createRenderer);
        RainimatorRegistryManager.registerRenderer(ABIGAIL_PROJECTILE.get(), FlyingItemEntityRenderer::new);
        RainimatorRegistryManager.registerRenderer(PATRICK.get(), PatrickEntity.texture::createRenderer);
        RainimatorRegistryManager.registerRenderer(PATRICK_PROJECTILE.get(), FlyingItemEntityRenderer::new);
        RainimatorRegistryManager.registerRenderer(BLACKBONE.get(), BlackBoneEntity.texture::createRenderer);
        RainimatorRegistryManager.registerRenderer(HOGSWORTH.get(), HogsworthEntity.texture::createRenderer);
        RainimatorRegistryManager.registerRenderer(SOLDIERS.get(), SoldiersEntity.texture::createRenderer);
        RainimatorRegistryManager.registerRenderer(SOLDIERS_PROJECTILE.get(), FlyingItemEntityRenderer::new);
        RainimatorRegistryManager.registerRenderer(CIARA.get(), CiaraEntity.texture::createRenderer);
        RainimatorRegistryManager.registerRenderer(CIARA_PROJECTILE.get(), FlyingItemEntityRenderer::new);
        RainimatorRegistryManager.registerRenderer(HILDA.get(), HildaEntity.texture::createRenderer);
        RainimatorRegistryManager.registerRenderer(HILDA_PROJECTILE.get(), FlyingItemEntityRenderer::new);
        RainimatorRegistryManager.registerRenderer(WITHERED_SKELETONS.get(), WitheredSkeletonsEntity.texture::createRenderer);
        RainimatorRegistryManager.registerRenderer(END_STAFF.get(), FlyingItemEntityRenderer::new);
        RainimatorRegistryManager.registerRenderer(VORDUS.get(), VordusEntity.texture::createRenderer);
        RainimatorRegistryManager.registerRenderer(DARK_ZOMBIE.get(), DarkZombieEntity.texture::createRenderer);
        RainimatorRegistryManager.registerRenderer(DARK_SHIELD.get(), DarkShieldEntity.texture::createRenderer);
        RainimatorRegistryManager.registerRenderer(WITHER_SHIELD.get(), WitherShieldEntity.texture::createRenderer);
        RainimatorRegistryManager.registerRenderer(SKELETON_SNOW.get(), SkeletonSnowEntity.texture::createRenderer);
        RainimatorRegistryManager.registerRenderer(ARABELLA.get(), ArabellaEntity.texture::createRenderer);
        RainimatorRegistryManager.registerRenderer(AZALEA.get(), AzaleaEntity.texture::createRenderer);
        RainimatorRegistryManager.registerRenderer(NULL_LIKE.get(), NullLikeEntity.texture::createRenderer);
        RainimatorRegistryManager.registerRenderer(ZOMBIE_PIGLIN_KING.get(), ZombiesPiglinKingEntity.texture::createRenderer);
        RainimatorRegistryManager.registerRenderer(PIGLIN_KING_ZOMBIES.get(), PiglinKingZombiesEntity.texture::createRenderer);
        RainimatorRegistryManager.registerRenderer(PIGLIN_KING_ZOMBIE.get(), PiglinKingZombieEntity.texture::createRenderer);
        RainimatorRegistryManager.registerRenderer(PIGLIN_COMMANDER.get(), PiglinCommanderEntity.texture::createRenderer);
        RainimatorRegistryManager.registerRenderer(DARYLL.get(), DaryllEntity.texture::createRenderer);
        RainimatorRegistryManager.registerRenderer(DARYLL_PROJECTILE.get(), FlyingItemEntityRenderer::new);
        RainimatorRegistryManager.registerRenderer(NAEUS_KING.get(), NaeusKingEntity.texture::createRenderer);
        RainimatorRegistryManager.registerRenderer(TUSK.get(), TuskEntity.texture::createRenderer);
        RainimatorRegistryManager.registerRenderer(BROTS.get(), BrotsEntity.texture::createRenderer);
        RainimatorRegistryManager.registerRenderer(ZOMBIE_PIGLIN_ART.get(), ZombiePiglinArtEntity.texture::createRenderer);
        RainimatorRegistryManager.registerRenderer(MUTATED.get(), MutatedEntity.texture::createRenderer);
        RainimatorRegistryManager.registerRenderer(NAMTAR.get(), NamtarEntity.texture::createRenderer);
        RainimatorRegistryManager.registerRenderer(AGETHA.get(), AgethaEntity.texture::createRenderer);
        RainimatorRegistryManager.registerRenderer(TRICER.get(), TricerEntity.texture::createRenderer);
        RainimatorRegistryManager.registerRenderer(BIG_UNDEAD_SKELETON.get(), BigUndeadSkeletonEntity.texture::createRenderer);
        RainimatorRegistryManager.registerRenderer(ARCHER.get(), ArcherEntity.texture::createRenderer);
        RainimatorRegistryManager.registerRenderer(GIGABONE.get(), GigaBoneEntity.texture::createRenderer);
        RainimatorRegistryManager.registerRenderer(KLAUS.get(), KlausEntity.texture::createRenderer);
        RainimatorRegistryManager.registerRenderer(KLAUS_2.get(), Klaus2Entity.texture::createRenderer);
        RainimatorRegistryManager.registerRenderer(KRALOS.get(), KralosEntity.texture::createRenderer);
    }
}
