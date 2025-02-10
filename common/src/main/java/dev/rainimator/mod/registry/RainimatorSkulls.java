package dev.rainimator.mod.registry;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SkullBlock;
import net.minecraft.block.WallSkullBlock;
import net.minecraft.item.Item;
import net.minecraft.item.SkullItem;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class RainimatorSkulls {
    public static final RegistrySupplier<Block> HEROBRINE_1_HEAD = register("herobrine_1_head", () -> new SkullBlock(SkullType.HEROBRINE_1, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> HEROBRINE_1_WALL_HEAD = register("herobrine_1_wall_head", () -> new WallSkullBlock(SkullType.HEROBRINE_1, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> HEROBRINE_2_HEAD = register("herobrine_2_head", () -> new SkullBlock(SkullType.HEROBRINE_2, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> HEROBRINE_2_WALL_HEAD = register("herobrine_2_wall_head", () -> new WallSkullBlock(SkullType.HEROBRINE_2, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> CERIS_HEAD = register("ceris_head", () -> new SkullBlock(SkullType.CERIS, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> CERIS_WALL_HEAD = register("ceris_wall_head", () -> new WallSkullBlock(SkullType.CERIS, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> ELITE_ZOMBIE_HEAD = register("elite_zombie_head", () -> new SkullBlock(SkullType.ZOMBIES, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> ELITE_ZOMBIE_WALL_HEAD = register("elite_zombie_wall_head", () -> new WallSkullBlock(SkullType.ZOMBIES, AbstractBlock.Settings.create()));
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
    public static final RegistrySupplier<Block> WITHER_SHIELD_HEAD = register("wither_shield_head", () -> new SkullBlock(SkullType.WITHER_SHIELD, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> WITHER_SHIELD_WALL_HEAD = register("wither_shield_wall_head", () -> new WallSkullBlock(SkullType.WITHER_SHIELD, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> SKELETON_SNOW_HEAD = register("skeleton_snow_head", () -> new SkullBlock(SkullType.SKELETON_SNOW, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> SKELETON_SNOW_WALL_HEAD = register("skeleton_snow_wall_head", () -> new WallSkullBlock(SkullType.SKELETON_SNOW, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> PIGLIN_KING_HEAD = register("piglin_king_head", () -> new SkullBlock(SkullType.PIGLIN_KING, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> PIGLIN_KING_WALL_HEAD = register("piglin_king_wall_head", () -> new WallSkullBlock(SkullType.PIGLIN_KING, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> GLUTTON_1_HEAD = register("glutton_1_head", () -> new SkullBlock(SkullType.GLUTTON_1, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> GLUTTON_1_WALL_HEAD = register("glutton_1_wall_head", () -> new WallSkullBlock(SkullType.GLUTTON_1, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> GLUTTON_2_HEAD = register("glutton_2_head", () -> new SkullBlock(SkullType.GLUTTON_2, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> GLUTTON_2_WALL_HEAD = register("glutton_2_wall_head", () -> new WallSkullBlock(SkullType.GLUTTON_2, AbstractBlock.Settings.create()));
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
    public static final RegistrySupplier<Block> KYLE_HEAD = register("kyle_head", () -> new SkullBlock(SkullType.KYLE, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> KYLE_WALL_HEAD = register("kyle_wall_head", () -> new WallSkullBlock(SkullType.KYLE, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> SCORCH_HEAD = register("scorch_head", () -> new SkullBlock(SkullType.SCORCH, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> SCORCH_WALL_HEAD = register("scorch_wall_head", () -> new WallSkullBlock(SkullType.SCORCH, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> STELLA_HEAD = register("stella_head", () -> new SkullBlock(SkullType.STELLA, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> STELLA_WALL_HEAD = register("stella_wall_head", () -> new WallSkullBlock(SkullType.STELLA, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> STELLA_DEMON_HEAD = register("stella_demon_head", () -> new SkullBlock(SkullType.STELLA_DEMON, AbstractBlock.Settings.create()));
    public static final RegistrySupplier<Block> STELLA_DEMON_WALL_HEAD = register("stella_demon_wall_head", () -> new WallSkullBlock(SkullType.STELLA_DEMON, AbstractBlock.Settings.create()));

    public static final RegistrySupplier<Item> HEROBRINE_1_HEAD_ITEM = register("herobrine_1", HEROBRINE_1_HEAD, HEROBRINE_1_WALL_HEAD);
    public static final RegistrySupplier<Item> HEROBRINE_2_HEAD_ITEM = register("herobrine_2", HEROBRINE_2_HEAD, HEROBRINE_2_WALL_HEAD);
    public static final RegistrySupplier<Item> CERIS_HEAD_ITEM = register("ceris", CERIS_HEAD, CERIS_WALL_HEAD);
    public static final RegistrySupplier<Item> ELITE_ZOMBIE_HEAD_ITEM = register("elite_zombie", ELITE_ZOMBIE_HEAD, ELITE_ZOMBIE_WALL_HEAD);
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
    public static final RegistrySupplier<Item> WITHER_SHIELD_HEAD_ITEM = register("wither_shield", WITHER_SHIELD_HEAD, WITHER_SHIELD_WALL_HEAD);
    public static final RegistrySupplier<Item> SKELETON_SNOW_HEAD_ITEM = register("skeleton_snow", SKELETON_SNOW_HEAD, SKELETON_SNOW_WALL_HEAD);
    public static final RegistrySupplier<Item> PIGLIN_KING_HEAD_ITEM = register("piglin_king", PIGLIN_KING_HEAD, PIGLIN_KING_WALL_HEAD);
    public static final RegistrySupplier<Item> GLUTTON_1_HEAD_ITEM = register("glutton_1", GLUTTON_1_HEAD, GLUTTON_1_WALL_HEAD);
    public static final RegistrySupplier<Item> GLUTTON_2_HEAD_ITEM = register("glutton_2", GLUTTON_2_HEAD, GLUTTON_2_WALL_HEAD);
    public static final RegistrySupplier<Item> PORKSHIRE_HEAD_ITEM = register("porkshire", PORKSHIRE_HEAD, PORKSHIRE_WALL_HEAD);
    public static final RegistrySupplier<Item> NAEUS_KING_HEAD_ITEM = register("naeus_king", NAEUS_KING_HEAD, NAEUS_KING_WALL_HEAD);
    public static final RegistrySupplier<Item> TUSK_HEAD_ITEM = register("tusk", TUSK_HEAD, TUSK_WALL_HEAD);
    public static final RegistrySupplier<Item> BROTS_HEAD_ITEM = register("brots", BROTS_HEAD, BROTS_WALL_HEAD);
    public static final RegistrySupplier<Item> ZOMBIE_PIG_HEAD_ITEM = register("zombie_pig", ZOMBIE_PIG_HEAD, ZOMBIE_PIG_WALL_HEAD);
    public static final RegistrySupplier<Item> MUTATED_HEAD_ITEM = register("mutated", MUTATED_HEAD, MUTATED_WALL_HEAD);
    public static final RegistrySupplier<Item> NAMTAR_HEAD_ITEM = register("namtar", NAMTAR_HEAD, NAMTAR_WALL_HEAD);
    public static final RegistrySupplier<Item> AGETHA_HEAD_ITEM = register("agetha", AGETHA_HEAD, AGETHA_WALL_HEAD);
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
    public static final RegistrySupplier<Item> KYLE_HEAD_ITEM = register("kyle", KYLE_HEAD, KYLE_WALL_HEAD);
    public static final RegistrySupplier<Item> SCORCH_HEAD_ITEM = register("scorch", SCORCH_HEAD, SCORCH_WALL_HEAD);
    public static final RegistrySupplier<Item> STELLA_HEAD_ITEM = register("stella", STELLA_HEAD, STELLA_WALL_HEAD);
    public static final RegistrySupplier<Item> STELLA_DEMON_HEAD_ITEM = register("stella_demon", STELLA_DEMON_HEAD, STELLA_DEMON_WALL_HEAD);

    private static RegistrySupplier<Block> register(String name, Supplier<Block> block) {
        return RainimatorBlocks.REGISTRY.register(name, block);
    }

    private static RegistrySupplier<Item> register(String name, Supplier<Block> head, Supplier<Block> wallHead) {
        return RainimatorItems.REGISTRY.register(name + "_head", () -> new SkullItem(head.get(), wallHead.get(), new Item.Settings().arch$tab(RainimatorItemGroups.MOBS)));
    }

    public static void init() {
    }

    public enum SkullType implements SkullBlock.SkullType {
        HEROBRINE_1, HEROBRINE_2, CERIS, ZOMBIES, NAEUS, RAIN, ABIGAIL, PATRICK, BLACKBONE, HOGSWORTH,
        CIARA, HILDA, SOLDIERS, WITHERED_SKELETONS, DARK_SHIELD, DARK_ZOMBIE, WITHER_SHIELD, SKELETON_SNOW,
        PIGLIN_KING, GLUTTON_1, GLUTTON_2, PORKSHIRE, NAEUS_KING, TUSK, BROTS, ZOMBIE_PIG, MUTATED,
        NAMTAR, AGETHA, BIG_BLACK_SKELETON, ARCHER, DARYLL, NULL_LIKE, GIGABONE, KLAUS,
        KLAUS_2, KRALOS, ARABELLA, AZALEA, KYLE, SCORCH, STELLA, STELLA_DEMON
    }
}
