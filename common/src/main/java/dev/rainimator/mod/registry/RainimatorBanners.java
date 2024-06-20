package dev.rainimator.mod.registry;

import dev.rainimator.mod.RainimatorMod;
import dev.rainimator.mod.item.util.BannerUtil;
import net.minecraft.block.entity.BannerPatterns;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Pair;

@SuppressWarnings("unused")
public class RainimatorBanners {
    public static final ItemStack FROST = BannerUtil.create("itemStack." + RainimatorMod.MOD_ID + ".frost", Items.CYAN_BANNER,
            new Pair<>(BannerPatterns.CROSS, DyeColor.WHITE),
            new Pair<>(BannerPatterns.STRAIGHT_CROSS, DyeColor.WHITE),
            new Pair<>(BannerPatterns.CURLY_BORDER, DyeColor.WHITE),
            new Pair<>(BannerPatterns.FLOWER, DyeColor.LIGHT_BLUE));
    public static final ItemStack UNDEAD = BannerUtil.create("itemStack." + RainimatorMod.MOD_ID + ".undead", Items.LIGHT_GRAY_BANNER,
            new Pair<>(BannerPatterns.CROSS, DyeColor.BLACK),
            new Pair<>(BannerPatterns.STRAIGHT_CROSS, DyeColor.BLACK),
            new Pair<>(BannerPatterns.TRIANGLES_TOP, DyeColor.BLACK),
            new Pair<>(BannerPatterns.GRADIENT_UP, DyeColor.BLACK),
            new Pair<>(BannerPatterns.SKULL, DyeColor.LIGHT_GRAY),
            new Pair<>(BannerPatterns.SKULL, DyeColor.WHITE));
    public static final ItemStack NETHER = BannerUtil.create("itemStack." + RainimatorMod.MOD_ID + ".nether", Items.BLACK_BANNER,
            new Pair<>(BannerPatterns.TRIANGLES_TOP, DyeColor.ORANGE),
            new Pair<>(BannerPatterns.TRIANGLES_BOTTOM, DyeColor.ORANGE),
            new Pair<>(BannerPatterns.CIRCLE, DyeColor.YELLOW),
            new Pair<>(BannerPatterns.FLOWER, DyeColor.RED),
            new Pair<>(BannerPatterns.SKULL, DyeColor.BLACK),
            new Pair<>(BannerPatterns.CURLY_BORDER, DyeColor.BLACK),
            new Pair<>(BannerPatterns.BORDER, DyeColor.BLACK));
    public static final ItemStack DRAGONSPIRE = BannerUtil.create("itemStack." + RainimatorMod.MOD_ID + ".dragonspire", Items.BLUE_BANNER,
            new Pair<>(BannerPatterns.SMALL_STRIPES, DyeColor.BLACK),
            new Pair<>(BannerPatterns.TRIANGLES_TOP, DyeColor.BLACK),
            new Pair<>(BannerPatterns.TRIANGLES_BOTTOM, DyeColor.BLACK),
            new Pair<>(BannerPatterns.RHOMBUS, DyeColor.BLUE),
            new Pair<>(BannerPatterns.FLOWER, DyeColor.BLACK),
            new Pair<>(BannerPatterns.CIRCLE, DyeColor.LIGHT_BLUE),
            new Pair<>(BannerPatterns.BORDER, DyeColor.BLACK));
    public static final ItemStack ENDER_PIRATE = BannerUtil.create("itemStack." + RainimatorMod.MOD_ID + ".ender_pirate", Items.PURPLE_BANNER,
            new Pair<>(BannerPatterns.GRADIENT, DyeColor.BLACK),
            new Pair<>(BannerPatterns.STRAIGHT_CROSS, DyeColor.MAGENTA),
            new Pair<>(BannerPatterns.TRIANGLES_TOP, DyeColor.WHITE),
            new Pair<>(BannerPatterns.TRIANGLES_BOTTOM, DyeColor.WHITE),
            new Pair<>(BannerPatterns.TRIANGLES_TOP, DyeColor.MAGENTA),
            new Pair<>(BannerPatterns.TRIANGLES_BOTTOM, DyeColor.MAGENTA),
            new Pair<>(BannerPatterns.SKULL, DyeColor.WHITE));
    public static final ItemStack THE_GATEKEEPERS = BannerUtil.create("itemStack." + RainimatorMod.MOD_ID + ".the_gatekeepers", Items.CYAN_BANNER,
            new Pair<>(BannerPatterns.STRIPE_BOTTOM, DyeColor.MAGENTA),
            new Pair<>(BannerPatterns.SMALL_STRIPES, DyeColor.PURPLE),
            new Pair<>(BannerPatterns.HALF_HORIZONTAL, DyeColor.PURPLE),
            new Pair<>(BannerPatterns.RHOMBUS, DyeColor.BLACK),
            new Pair<>(BannerPatterns.TRIANGLES_TOP, DyeColor.CYAN),
            new Pair<>(BannerPatterns.TRIANGLES_BOTTOM, DyeColor.CYAN),
            new Pair<>(BannerPatterns.CROSS, DyeColor.CYAN),
            new Pair<>(BannerPatterns.SMALL_STRIPES, DyeColor.BLACK),
            new Pair<>(BannerPatterns.CURLY_BORDER, DyeColor.BLACK));
    public static final ItemStack ORCHID_CITY = BannerUtil.create("itemStack." + RainimatorMod.MOD_ID + ".orchid_city", Items.GRAY_BANNER,
            new Pair<>(BannerPatterns.GRADIENT, DyeColor.MAGENTA),
            new Pair<>(BannerPatterns.CROSS, DyeColor.WHITE),
            new Pair<>(BannerPatterns.CROSS, DyeColor.BLACK),
            new Pair<>(BannerPatterns.CURLY_BORDER, DyeColor.BLACK),
            new Pair<>(BannerPatterns.TRIANGLES_TOP, DyeColor.BLACK),
            new Pair<>(BannerPatterns.TRIANGLES_BOTTOM, DyeColor.BLACK),
            new Pair<>(BannerPatterns.CIRCLE, DyeColor.MAGENTA),
            new Pair<>(BannerPatterns.FLOWER, DyeColor.WHITE));
    public static final ItemStack LIGHTBORNE_ISLES = BannerUtil.create("itemStack." + RainimatorMod.MOD_ID + ".lightborne_isles", Items.WHITE_BANNER,
            new Pair<>(BannerPatterns.STRIPE_CENTER, DyeColor.GRAY),
            new Pair<>(BannerPatterns.STRIPE_MIDDLE, DyeColor.GRAY),
            new Pair<>(BannerPatterns.CURLY_BORDER, DyeColor.BLACK),
            new Pair<>(BannerPatterns.CROSS, DyeColor.BLACK),
            new Pair<>(BannerPatterns.FLOWER, DyeColor.LIGHT_GRAY),
            new Pair<>(BannerPatterns.CIRCLE, DyeColor.WHITE));
    public static final ItemStack THE_UMBRAL_KINGDOM = BannerUtil.create("itemStack." + RainimatorMod.MOD_ID + ".the_umbral_kingdom", Items.BLACK_BANNER,
            new Pair<>(BannerPatterns.STRIPE_CENTER, DyeColor.WHITE),
            new Pair<>(BannerPatterns.BRICKS, DyeColor.LIGHT_BLUE),
            new Pair<>(BannerPatterns.BRICKS, DyeColor.BLACK),
            new Pair<>(BannerPatterns.STRIPE_DOWNLEFT, DyeColor.CYAN),
            new Pair<>(BannerPatterns.STRIPE_DOWNRIGHT, DyeColor.MAGENTA),
            new Pair<>(BannerPatterns.RHOMBUS, DyeColor.CYAN),
            new Pair<>(BannerPatterns.CREEPER, DyeColor.MAGENTA),
            new Pair<>(BannerPatterns.CROSS, DyeColor.BLACK),
            new Pair<>(BannerPatterns.CIRCLE, DyeColor.MAGENTA),
            new Pair<>(BannerPatterns.FLOWER, DyeColor.BLACK),
            new Pair<>(BannerPatterns.SKULL, DyeColor.BLACK));
    public static final ItemStack CHORUS_BAY = BannerUtil.create("itemStack." + RainimatorMod.MOD_ID + ".chorus_bay", Items.LIGHT_BLUE_BANNER,
            new Pair<>(BannerPatterns.GRADIENT, DyeColor.BLUE),
            new Pair<>(BannerPatterns.TRIANGLES_TOP, DyeColor.WHITE),
            new Pair<>(BannerPatterns.FLOWER, DyeColor.LIGHT_BLUE),
            new Pair<>(BannerPatterns.CURLY_BORDER, DyeColor.BLACK),
            new Pair<>(BannerPatterns.CIRCLE, DyeColor.WHITE));
    public static final ItemStack VOID_ISLANDS = BannerUtil.create("itemStack." + RainimatorMod.MOD_ID + ".void_islands", Items.WHITE_BANNER,
            new Pair<>(BannerPatterns.GRADIENT, DyeColor.BLACK),
            new Pair<>(BannerPatterns.SMALL_STRIPES, DyeColor.BLACK),
            new Pair<>(BannerPatterns.RHOMBUS, DyeColor.WHITE),
            new Pair<>(BannerPatterns.CURLY_BORDER, DyeColor.WHITE),
            new Pair<>(BannerPatterns.FLOWER, DyeColor.BLACK),
            new Pair<>(BannerPatterns.BORDER, DyeColor.BLACK));
}
