package dev.rainimator.mod.registry;

import dev.architectury.registry.registries.RegistrySupplier;
import dev.rainimator.mod.entity.*;
import dev.rainimator.mod.renderer.util.SkullRenderRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SkullBlock;
import net.minecraft.block.WallSkullBlock;
import net.minecraft.item.Item;
import net.minecraft.item.SkullItem;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class RainimatorSkulls {
    public static final RegistrySupplier<Block> HEROBRINE_HEAD = register("herobrine_head", () -> new SkullBlock(SkullType.HEROBRINE, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> HEROBRINE_WALL_HEAD = register("herobrine_wall_head", () -> new WallSkullBlock(SkullType.HEROBRINE, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> CERIS_HEAD = register("ceris_head", () -> new SkullBlock(SkullType.CERIS, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> CERIS_WALL_HEAD = register("ceris_wall_head", () -> new WallSkullBlock(SkullType.CERIS, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> ZOMBIES_HEAD = register("zombies_head", () -> new SkullBlock(SkullType.ZOMBIES, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> ZOMBIES_WALL_HEAD = register("zombies_wall_head", () -> new WallSkullBlock(SkullType.ZOMBIES, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> NAEUS_HEAD = register("naeus_head", () -> new SkullBlock(SkullType.NAEUS, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> NAEUS_WALL_HEAD = register("naeus_wall_head", () -> new WallSkullBlock(SkullType.NAEUS, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> RAIN_HEAD = register("rain_head", () -> new SkullBlock(SkullType.RAIN, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> RAIN_WALL_HEAD = register("rain_wall_head", () -> new WallSkullBlock(SkullType.RAIN, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> ABIGAIL_HEAD = register("abigail_head", () -> new SkullBlock(SkullType.ABIGAIL, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> ABIGAIL_WALL_HEAD = register("abigail_wall_head", () -> new WallSkullBlock(SkullType.ABIGAIL, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> PATRICK_HEAD = register("patrick_head", () -> new SkullBlock(SkullType.PATRICK, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> PATRICK_WALL_HEAD = register("patrick_wall_head", () -> new WallSkullBlock(SkullType.PATRICK, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> BLACKBONE_HEAD = register("blackbone_head", () -> new SkullBlock(SkullType.BLACKBONE, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> BLACKBONE_WALL_HEAD = register("blackbone_wall_head", () -> new WallSkullBlock(SkullType.BLACKBONE, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> HOGSWORTH_HEAD = register("hogsworth_head", () -> new SkullBlock(SkullType.HOGSWORTH, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> HOGSWORTH_WALL_HEAD = register("hogsworth_wall_head", () -> new WallSkullBlock(SkullType.HOGSWORTH, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> CIARA_HEAD = register("ciara_head", () -> new SkullBlock(SkullType.CIARA, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> CIARA_WALL_HEAD = register("ciara_wall_head", () -> new WallSkullBlock(SkullType.CIARA, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> HILDA_HEAD = register("hilda_head", () -> new SkullBlock(SkullType.HILDA, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> HILDA_WALL_HEAD = register("hilda_wall_head", () -> new WallSkullBlock(SkullType.HILDA, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> SOLDIERS_HEAD = register("soldiers_head", () -> new SkullBlock(SkullType.SOLDIERS, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> SOLDIERS_WALL_HEAD = register("soldiers_wall_head", () -> new WallSkullBlock(SkullType.SOLDIERS, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> WITHERED_SKELETONS_HEAD = register("withered_skeletons_head", () -> new SkullBlock(SkullType.WITHERED_SKELETONS, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> WITHERED_SKELETONS_WALL_HEAD = register("withered_skeletons_wall_head", () -> new WallSkullBlock(SkullType.WITHERED_SKELETONS, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> DARK_SHIELD_HEAD = register("dark_shield_head", () -> new SkullBlock(SkullType.DARK_SHIELD, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> DARK_SHIELD_WALL_HEAD = register("dark_shield_wall_head", () -> new WallSkullBlock(SkullType.DARK_SHIELD, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> DARK_ZOMBIE_HEAD = register("dark_zombie_head", () -> new SkullBlock(SkullType.DARK_ZOMBIE, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> DARK_ZOMBIE_WALL_HEAD = register("dark_zombie_wall_head", () -> new WallSkullBlock(SkullType.DARK_ZOMBIE, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> VORDUS_HEAD = register("vordus_head", () -> new SkullBlock(SkullType.VORDUS, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> VORDUS_WALL_HEAD = register("vordus_wall_head", () -> new WallSkullBlock(SkullType.VORDUS, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> WITHER_SHIELD_HEAD = register("wither_shield_head", () -> new SkullBlock(SkullType.WITHER_SHIELD, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> WITHER_SHIELD_WALL_HEAD = register("wither_shield_wall_head", () -> new WallSkullBlock(SkullType.WITHER_SHIELD, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> SKELETON_SNOW_HEAD = register("skeleton_snow_head", () -> new SkullBlock(SkullType.SKELETON_SNOW, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> SKELETON_SNOW_WALL_HEAD = register("skeleton_snow_wall_head", () -> new WallSkullBlock(SkullType.SKELETON_SNOW, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> PIGLIN_KING_HEAD = register("piglin_king_head", () -> new SkullBlock(SkullType.PIGLIN_KING, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> PIGLIN_KING_WALL_HEAD = register("piglin_king_wall_head", () -> new WallSkullBlock(SkullType.PIGLIN_KING, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> GLUTTON_HEAD = register("glutton_head", () -> new SkullBlock(SkullType.GLUTTON, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> GLUTTON_WALL_HEAD = register("glutton_wall_head", () -> new WallSkullBlock(SkullType.GLUTTON, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> GLUTTON_OLD_HEAD = register("glutton_old_head", () -> new SkullBlock(SkullType.GLUTTON_OLD, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> GLUTTON_OLD_WALL_HEAD = register("glutton_old_wall_head", () -> new WallSkullBlock(SkullType.GLUTTON_OLD, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> PORKSHIRE_HEAD = register("porkshire_head", () -> new SkullBlock(SkullType.PORKSHIRE, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> PORKSHIRE_WALL_HEAD = register("porkshire_wall_head", () -> new WallSkullBlock(SkullType.PORKSHIRE, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> NAEUS_KING_HEAD = register("naeus_king_head", () -> new SkullBlock(SkullType.NAEUS_KING, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> NAEUS_KING_WALL_HEAD = register("naeus_king_wall_head", () -> new WallSkullBlock(SkullType.NAEUS_KING, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> TUSK_HEAD = register("tusk_head", () -> new SkullBlock(SkullType.TUSK, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> TUSK_WALL_HEAD = register("tusk_wall_head", () -> new WallSkullBlock(SkullType.TUSK, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> BROTS_HEAD = register("brots_head", () -> new SkullBlock(SkullType.BROTS, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> BROTS_WALL_HEAD = register("brots_wall_head", () -> new WallSkullBlock(SkullType.BROTS, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> ZOMBIE_PIG_HEAD = register("zombie_pig_head", () -> new SkullBlock(SkullType.ZOMBIE_PIG, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> ZOMBIE_PIG_WALL_HEAD = register("zombie_pig_wall_head", () -> new WallSkullBlock(SkullType.ZOMBIE_PIG, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> MUTATED_HEAD = register("mutated_head", () -> new SkullBlock(SkullType.MUTATED, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> MUTATED_WALL_HEAD = register("mutated_wall_head", () -> new WallSkullBlock(SkullType.MUTATED, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> NAMTAR_HEAD = register("namtar_head", () -> new SkullBlock(SkullType.NAMTAR, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> NAMTAR_WALL_HEAD = register("namtar_wall_head", () -> new WallSkullBlock(SkullType.NAMTAR, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> AGETHA_HEAD = register("agetha_head", () -> new SkullBlock(SkullType.AGETHA, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> AGETHA_WALL_HEAD = register("agetha_wall_head", () -> new WallSkullBlock(SkullType.AGETHA, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> TRICER_HEAD = register("tricer_head", () -> new SkullBlock(SkullType.TRICER, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> TRICER_WALL_HEAD = register("tricer_wall_head", () -> new WallSkullBlock(SkullType.TRICER, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> BIG_BLACK_SKELETON_HEAD = register("big_black_skeleton_head", () -> new SkullBlock(SkullType.BIG_BLACK_SKELETON, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> BIG_BLACK_SKELETON_WALL_HEAD = register("big_black_skeleton_wall_head", () -> new WallSkullBlock(SkullType.BIG_BLACK_SKELETON, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> ARCHER_HEAD = register("archer_head", () -> new SkullBlock(SkullType.ARCHER, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> ARCHER_WALL_HEAD = register("archer_wall_head", () -> new WallSkullBlock(SkullType.ARCHER, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> DARYLL_HEAD = register("daryll_head", () -> new SkullBlock(SkullType.DARYLL, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> DARYLL_WALL_HEAD = register("daryll_wall_head", () -> new WallSkullBlock(SkullType.DARYLL, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> NULL_LIKE_HEAD = register("null_like_head", () -> new SkullBlock(SkullType.NULL_LIKE, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> NULL_LIKE_WALL_HEAD = register("null_like_wall_head", () -> new WallSkullBlock(SkullType.NULL_LIKE, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> GIGABONE_HEAD = register("gigabone_head", () -> new SkullBlock(SkullType.GIGABONE, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> GIGABONE_WALL_HEAD = register("gigabone_wall_head", () -> new WallSkullBlock(SkullType.GIGABONE, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> KLAUS_HEAD = register("klaus_head", () -> new SkullBlock(SkullType.KLAUS, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> KLAUS_WALL_HEAD = register("klaus_wall_head", () -> new WallSkullBlock(SkullType.KLAUS, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> KLAUS_2_HEAD = register("klaus_2_head", () -> new SkullBlock(SkullType.KLAUS_2, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> KLAUS_2_WALL_HEAD = register("klaus_2_wall_head", () -> new WallSkullBlock(SkullType.KLAUS_2, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> KRALOS_HEAD = register("kralos_head", () -> new SkullBlock(SkullType.KRALOS, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> KRALOS_WALL_HEAD = register("kralos_wall_head", () -> new WallSkullBlock(SkullType.KRALOS, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> ARABELLA_HEAD = register("arabella_head", () -> new SkullBlock(SkullType.ARABELLA, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> ARABELLA_WALL_HEAD = register("arabella_wall_head", () -> new WallSkullBlock(SkullType.ARABELLA, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> AZALEA_HEAD = register("azalea_head", () -> new SkullBlock(SkullType.AZALEA, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> AZALEA_WALL_HEAD = register("azalea_wall_head", () -> new WallSkullBlock(SkullType.AZALEA, AbstractBlock.Settings.create()));

    public static final RegistrySupplier<Item> HEROBRINE_HEAD_ITEM = register("herobrine", HEROBRINE_HEAD, HEROBRINE_WALL_HEAD);
    public static final RegistrySupplier<Item> CERIS_HEAD_ITEM = register("ceris", CERIS_HEAD, CERIS_WALL_HEAD);
    public static final RegistrySupplier<Item> ZOMBIES_HEAD_ITEM = register("zombies", ZOMBIES_HEAD, ZOMBIES_WALL_HEAD);
    public static final RegistrySupplier<Item> NAEUS_HEAD_ITEM = register("naeus", NAEUS_HEAD, NAEUS_WALL_HEAD);
    public static final RegistrySupplier<Item> RAIN_HEAD_ITEM = register("rain", RAIN_HEAD, RAIN_WALL_HEAD);
    public static final RegistrySupplier<Item> ABIGAIL_HEAD_ITEM = register("abigail", ABIGAIL_HEAD, ABIGAIL_WALL_HEAD);
    public static final RegistrySupplier<Item> PATRICK_HEAD_ITEM = register("patrick", PATRICK_HEAD, PATRICK_WALL_HEAD);
    public static final RegistrySupplier<Item> BLACKBONE_HEAD_ITEM = register("blackbone", BLACKBONE_HEAD, BLACKBONE_WALL_HEAD);
    public static final RegistrySupplier<Item> HOGSWORTH_HEAD_ITEM = register("hogsworth", HOGSWORTH_HEAD, HOGSWORTH_WALL_HEAD);
    public static final RegistrySupplier<Item> CIARA_HEAD_ITEM = register("ciara", CIARA_HEAD, CIARA_WALL_HEAD);
    public static final RegistrySupplier<Item> HILDA_HEAD_ITEM = register("hilda", HILDA_HEAD, HILDA_WALL_HEAD);
    public static final RegistrySupplier<Item> SOLDIERS_HEAD_ITEM = register("soldiers", SOLDIERS_HEAD, SOLDIERS_WALL_HEAD);
    public static final RegistrySupplier<Item> WITHERED_SKELETONS_HEAD_ITEM = register("withered_skeletons", WITHERED_SKELETONS_HEAD, WITHERED_SKELETONS_WALL_HEAD);
    public static final RegistrySupplier<Item> DARK_SHIELD_HEAD_ITEM = register("dark_shield", DARK_SHIELD_HEAD, DARK_SHIELD_WALL_HEAD);
    public static final RegistrySupplier<Item> DARK_ZOMBIE_HEAD_ITEM = register("dark_zombie", DARK_ZOMBIE_HEAD, DARK_ZOMBIE_WALL_HEAD);
    public static final RegistrySupplier<Item> VORDUS_HEAD_ITEM = register("vordus", VORDUS_HEAD, VORDUS_WALL_HEAD);
    public static final RegistrySupplier<Item> WITHER_SHIELD_HEAD_ITEM = register("wither_shield", WITHER_SHIELD_HEAD, WITHER_SHIELD_WALL_HEAD);
    public static final RegistrySupplier<Item> SKELETON_SNOW_HEAD_ITEM = register("skeleton_snow", SKELETON_SNOW_HEAD, SKELETON_SNOW_WALL_HEAD);
    public static final RegistrySupplier<Item> PIGLIN_KING_HEAD_ITEM = register("piglin_king", PIGLIN_KING_HEAD, PIGLIN_KING_WALL_HEAD);
    public static final RegistrySupplier<Item> GLUTTON_HEAD_ITEM = register("glutton", GLUTTON_HEAD, GLUTTON_WALL_HEAD);
    public static final RegistrySupplier<Item> GLUTTON_OLD_HEAD_ITEM = register("glutton_old", GLUTTON_OLD_HEAD, GLUTTON_OLD_WALL_HEAD);
    public static final RegistrySupplier<Item> PORKSHIRE_HEAD_ITEM = register("porkshire", PORKSHIRE_HEAD, PORKSHIRE_WALL_HEAD);
    public static final RegistrySupplier<Item> NAEUS_KING_HEAD_ITEM = register("naeus_king", NAEUS_KING_HEAD, NAEUS_KING_WALL_HEAD);
    public static final RegistrySupplier<Item> TUSK_HEAD_ITEM = register("tusk", TUSK_HEAD, TUSK_WALL_HEAD);
    public static final RegistrySupplier<Item> BROTS_HEAD_ITEM = register("brots", BROTS_HEAD, BROTS_WALL_HEAD);
    public static final RegistrySupplier<Item> ZOMBIE_PIG_HEAD_ITEM = register("zombie_pig", ZOMBIE_PIG_HEAD, ZOMBIE_PIG_WALL_HEAD);
    public static final RegistrySupplier<Item> MUTATED_HEAD_ITEM = register("mutated", MUTATED_HEAD, MUTATED_WALL_HEAD);
    public static final RegistrySupplier<Item> NAMTAR_HEAD_ITEM = register("namtar", NAMTAR_HEAD, NAMTAR_WALL_HEAD);
    public static final RegistrySupplier<Item> AGETHA_HEAD_ITEM = register("agetha", AGETHA_HEAD, AGETHA_WALL_HEAD);
    public static final RegistrySupplier<Item> TRICER_HEAD_ITEM = register("tricer", TRICER_HEAD, TRICER_WALL_HEAD);
    public static final RegistrySupplier<Item> BIG_BLACK_SKELETON_HEAD_ITEM = register("big_black_skeleton", BIG_BLACK_SKELETON_HEAD, BIG_BLACK_SKELETON_WALL_HEAD);
    public static final RegistrySupplier<Item> ARCHER_HEAD_ITEM = register("archer", ARCHER_HEAD, ARCHER_WALL_HEAD);
    public static final RegistrySupplier<Item> DARYLL_HEAD_ITEM = register("daryll", DARYLL_HEAD, DARYLL_WALL_HEAD);
    public static final RegistrySupplier<Item> NULL_LIKE_HEAD_ITEM = register("null_like", NULL_LIKE_HEAD, NULL_LIKE_WALL_HEAD);
    public static final RegistrySupplier<Item> GIGABONE_HEAD_ITEM = register("gigabone", GIGABONE_HEAD, GIGABONE_WALL_HEAD);
    public static final RegistrySupplier<Item> KLAUS_HEAD_ITEM = register("klaus", KLAUS_HEAD, KLAUS_WALL_HEAD);
    public static final RegistrySupplier<Item> KLAUS_2_HEAD_ITEM = register("klaus_2", KLAUS_2_HEAD, KLAUS_2_WALL_HEAD);
    public static final RegistrySupplier<Item> KRALOS_HEAD_ITEM = register("kralos", KRALOS_HEAD, KRALOS_WALL_HEAD);
    public static final RegistrySupplier<Item> ARABELLA_HEAD_ITEM = register("arabella", ARABELLA_HEAD, ARABELLA_WALL_HEAD);
    public static final RegistrySupplier<Item> AZALEA_HEAD_ITEM = register("azalea", AZALEA_HEAD, AZALEA_WALL_HEAD);

    private static RegistrySupplier<Block> register(String name, Supplier<Block> block) {
        return RainimatorBlocks.REGISTRY.register(name, block);
    }

    private static RegistrySupplier<Item> register(String name, Supplier<Block> head, Supplier<Block> wallHead) {
        return RainimatorItems.REGISTRY.register(name + "_head", () -> new SkullItem(head.get(), wallHead.get(), new Item.Settings().arch$tab(RainimatorItemGroups.MOBS)));
    }

    public static void init() {
    }

    @Environment(EnvType.CLIENT)
    public static void clientInit() {
        SkullRenderRegistry.register(SkullType.HEROBRINE, HerobrineEntity.texture.getTexture(), HEROBRINE_HEAD, HEROBRINE_WALL_HEAD);
        SkullRenderRegistry.register(SkullType.CERIS, CerisEntity.texture.getTexture(), CERIS_HEAD, CERIS_WALL_HEAD);
        SkullRenderRegistry.register(SkullType.ZOMBIES, ZombiesEntity.texture.getTexture(), ZOMBIES_HEAD, ZOMBIES_WALL_HEAD);
        SkullRenderRegistry.register(SkullType.NAEUS, NaeusEntity.texture.getTexture(), NAEUS_HEAD, NAEUS_WALL_HEAD);
        SkullRenderRegistry.register(SkullType.RAIN, RainEntity.texture.getTexture(), RAIN_HEAD, RAIN_WALL_HEAD);
        SkullRenderRegistry.register(SkullType.ABIGAIL, AbigailEntity.texture.getTexture(), ABIGAIL_HEAD, ABIGAIL_WALL_HEAD);
        SkullRenderRegistry.register(SkullType.PATRICK, PatrickEntity.texture.getTexture(), PATRICK_HEAD, PATRICK_WALL_HEAD);
        SkullRenderRegistry.register(SkullType.BLACKBONE, BlackBoneEntity.texture.getTexture(), BLACKBONE_HEAD, BLACKBONE_WALL_HEAD);
        SkullRenderRegistry.register(SkullType.HOGSWORTH, HogsworthEntity.texture.getTexture(), HOGSWORTH_HEAD, HOGSWORTH_WALL_HEAD);
        SkullRenderRegistry.register(SkullType.CIARA, CiaraEntity.texture.getTexture(), CIARA_HEAD, CIARA_WALL_HEAD);
        SkullRenderRegistry.register(SkullType.HILDA, HildaEntity.texture.getTexture(), HILDA_HEAD, HILDA_WALL_HEAD);
        SkullRenderRegistry.register(SkullType.SOLDIERS, SoldiersEntity.texture.getTexture(), SOLDIERS_HEAD, SOLDIERS_WALL_HEAD);
        SkullRenderRegistry.register(SkullType.WITHERED_SKELETONS, WitheredSkeletonsEntity.texture.getTexture(), WITHERED_SKELETONS_HEAD, WITHERED_SKELETONS_WALL_HEAD);
        SkullRenderRegistry.register(SkullType.DARK_SHIELD, DarkShieldEntity.texture.getTexture(), DARK_SHIELD_HEAD, DARK_SHIELD_WALL_HEAD);
        SkullRenderRegistry.register(SkullType.DARK_ZOMBIE, DarkZombieEntity.texture.getTexture(), DARK_ZOMBIE_HEAD, DARK_ZOMBIE_WALL_HEAD);
        SkullRenderRegistry.register(SkullType.VORDUS, VordusEntity.texture.getTexture(), VORDUS_HEAD, VORDUS_WALL_HEAD);
        SkullRenderRegistry.register(SkullType.WITHER_SHIELD, WitherShieldEntity.texture.getTexture(), WITHER_SHIELD_HEAD, WITHER_SHIELD_WALL_HEAD);
        SkullRenderRegistry.register(SkullType.SKELETON_SNOW, SkeletonSnowEntity.texture.getTexture(), SKELETON_SNOW_HEAD, SKELETON_SNOW_WALL_HEAD);
        SkullRenderRegistry.register(SkullType.PIGLIN_KING, ZombiesPiglinKingEntity.texture.getTexture(), PIGLIN_KING_HEAD, PIGLIN_KING_WALL_HEAD);
        SkullRenderRegistry.register(SkullType.GLUTTON, PiglinKingZombiesEntity.texture.getTexture(), GLUTTON_HEAD, GLUTTON_WALL_HEAD);
        SkullRenderRegistry.register(SkullType.GLUTTON_OLD, PiglinKingZombieEntity.texture.getTexture(), GLUTTON_OLD_HEAD, GLUTTON_OLD_WALL_HEAD);
        SkullRenderRegistry.register(SkullType.PORKSHIRE, PiglinCommanderEntity.texture.getTexture(), PORKSHIRE_HEAD, PORKSHIRE_WALL_HEAD);
        SkullRenderRegistry.register(SkullType.NAEUS_KING, NaeusKingEntity.texture.getTexture(), NAEUS_KING_HEAD, NAEUS_KING_WALL_HEAD);
        SkullRenderRegistry.register(SkullType.TUSK, TuskEntity.texture.getTexture(), TUSK_HEAD, TUSK_WALL_HEAD);
        SkullRenderRegistry.register(SkullType.BROTS, BrotsEntity.texture.getTexture(), BROTS_HEAD, BROTS_WALL_HEAD);
        SkullRenderRegistry.register(SkullType.ZOMBIE_PIG, ZombiePiglinArtEntity.texture.getTexture(), ZOMBIE_PIG_HEAD, ZOMBIE_PIG_WALL_HEAD);
        SkullRenderRegistry.register(SkullType.MUTATED, MutatedEntity.texture.getTexture(), MUTATED_HEAD, MUTATED_WALL_HEAD);
        SkullRenderRegistry.register(SkullType.NAMTAR, NamtarEntity.texture.getTexture(), NAMTAR_HEAD, NAMTAR_WALL_HEAD);
        SkullRenderRegistry.register(SkullType.AGETHA, AgethaEntity.texture.getTexture(), AGETHA_HEAD, AGETHA_WALL_HEAD);
        SkullRenderRegistry.register(SkullType.TRICER, TricerEntity.texture.getTexture(), TRICER_HEAD, TRICER_WALL_HEAD);
        SkullRenderRegistry.register(SkullType.BIG_BLACK_SKELETON, BigUndeadSkeletonEntity.texture.getTexture(), BIG_BLACK_SKELETON_HEAD, BIG_BLACK_SKELETON_WALL_HEAD);
        SkullRenderRegistry.register(SkullType.ARCHER, ArcherEntity.texture.getTexture(), ARCHER_HEAD, ARCHER_WALL_HEAD);
        SkullRenderRegistry.register(SkullType.DARYLL, DaryllEntity.texture.getTexture(), DARYLL_HEAD, DARYLL_WALL_HEAD);
        SkullRenderRegistry.register(SkullType.NULL_LIKE, NullLikeEntity.texture.getTexture(), NULL_LIKE_HEAD, NULL_LIKE_WALL_HEAD);
        SkullRenderRegistry.register(SkullType.GIGABONE, GigaBoneEntity.texture.getTexture(), GIGABONE_HEAD, GIGABONE_WALL_HEAD);
        SkullRenderRegistry.register(SkullType.KLAUS, KlausEntity.texture.getTexture(), KLAUS_HEAD, KLAUS_WALL_HEAD);
        SkullRenderRegistry.register(SkullType.KLAUS_2, Klaus2Entity.texture.getTexture(), KLAUS_2_HEAD, KLAUS_2_WALL_HEAD);
        SkullRenderRegistry.register(SkullType.KRALOS, KralosEntity.texture.getTexture(), KRALOS_HEAD, KRALOS_WALL_HEAD);
        SkullRenderRegistry.register(SkullType.ARABELLA, ArabellaEntity.texture.getTexture(), ARABELLA_HEAD, ARABELLA_WALL_HEAD);
        SkullRenderRegistry.register(SkullType.AZALEA, AzaleaEntity.texture.getTexture(), AZALEA_HEAD, AZALEA_WALL_HEAD);
    }

    public enum SkullType implements SkullBlock.SkullType {
        HEROBRINE, CERIS, ZOMBIES, NAEUS, RAIN, ABIGAIL, PATRICK, BLACKBONE, HOGSWORTH,
        CIARA, HILDA, SOLDIERS, WITHERED_SKELETONS, DARK_SHIELD, DARK_ZOMBIE, VORDUS, WITHER_SHIELD, SKELETON_SNOW,
        PIGLIN_KING, GLUTTON, GLUTTON_OLD, PORKSHIRE, NAEUS_KING, TUSK, BROTS, ZOMBIE_PIG, MUTATED,
        NAMTAR, AGETHA, TRICER, BIG_BLACK_SKELETON, ARCHER, DARYLL, NULL_LIKE, GIGABONE, KLAUS,
        KLAUS_2, KRALOS, ARABELLA, AZALEA
    }
}
