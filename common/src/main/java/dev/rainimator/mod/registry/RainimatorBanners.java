package dev.rainimator.mod.registry;

import dev.rainimator.mod.RainimatorMod;
import dev.rainimator.mod.registry.util.BannerUtil;
import net.minecraft.block.entity.BannerPattern;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Pair;

@SuppressWarnings("unused")
public class RainimatorBanners {
    public static final ItemStack FROST = BannerUtil.create("itemStack." + RainimatorMod.MOD_ID + ".frost", Items.CYAN_BANNER,
            new Pair<>(BannerPattern.CROSS, DyeColor.WHITE),
            new Pair<>(BannerPattern.STRAIGHT_CROSS, DyeColor.WHITE),
            new Pair<>(BannerPattern.CURLY_BORDER, DyeColor.WHITE),
            new Pair<>(BannerPattern.FLOWER, DyeColor.LIGHT_BLUE));
    public static final ItemStack UNDEAD = BannerUtil.create("itemStack." + RainimatorMod.MOD_ID + ".undead", Items.LIGHT_GRAY_BANNER,
            new Pair<>(BannerPattern.CROSS, DyeColor.BLACK),
            new Pair<>(BannerPattern.STRAIGHT_CROSS, DyeColor.BLACK),
            new Pair<>(BannerPattern.TRIANGLES_TOP, DyeColor.BLACK),
            new Pair<>(BannerPattern.GRADIENT_UP, DyeColor.BLACK),
            new Pair<>(BannerPattern.SKULL, DyeColor.LIGHT_GRAY),
            new Pair<>(BannerPattern.SKULL, DyeColor.WHITE));
    public static final ItemStack NETHER = BannerUtil.create("itemStack." + RainimatorMod.MOD_ID + ".nether", Items.BLACK_BANNER,
            new Pair<>(BannerPattern.TRIANGLES_TOP, DyeColor.ORANGE),
            new Pair<>(BannerPattern.TRIANGLES_BOTTOM, DyeColor.ORANGE),
            new Pair<>(BannerPattern.CIRCLE_MIDDLE, DyeColor.YELLOW),
            new Pair<>(BannerPattern.FLOWER, DyeColor.RED),
            new Pair<>(BannerPattern.SKULL, DyeColor.BLACK),
            new Pair<>(BannerPattern.CURLY_BORDER, DyeColor.BLACK),
            new Pair<>(BannerPattern.BORDER, DyeColor.BLACK));
    public static final ItemStack DRAGONSPIRE = BannerUtil.create("itemStack." + RainimatorMod.MOD_ID + ".dragonspire", Items.BLUE_BANNER,
            new Pair<>(BannerPattern.STRIPE_SMALL, DyeColor.BLACK),
            new Pair<>(BannerPattern.TRIANGLES_TOP, DyeColor.BLACK),
            new Pair<>(BannerPattern.TRIANGLES_BOTTOM, DyeColor.BLACK),
            new Pair<>(BannerPattern.RHOMBUS_MIDDLE, DyeColor.BLUE),
            new Pair<>(BannerPattern.FLOWER, DyeColor.BLACK),
            new Pair<>(BannerPattern.CIRCLE_MIDDLE, DyeColor.LIGHT_BLUE),
            new Pair<>(BannerPattern.BORDER, DyeColor.BLACK));
    public static final ItemStack ENDER_PIRATE = BannerUtil.create("itemStack." + RainimatorMod.MOD_ID + ".ender_pirate", Items.PURPLE_BANNER,
            new Pair<>(BannerPattern.GRADIENT, DyeColor.BLACK),
            new Pair<>(BannerPattern.STRAIGHT_CROSS, DyeColor.MAGENTA),
            new Pair<>(BannerPattern.TRIANGLES_TOP, DyeColor.WHITE),
            new Pair<>(BannerPattern.TRIANGLES_BOTTOM, DyeColor.WHITE),
            new Pair<>(BannerPattern.TRIANGLES_TOP, DyeColor.MAGENTA),
            new Pair<>(BannerPattern.TRIANGLES_BOTTOM, DyeColor.MAGENTA),
            new Pair<>(BannerPattern.SKULL, DyeColor.WHITE));
    public static final ItemStack THE_GATEKEEPERS = BannerUtil.create("itemStack." + RainimatorMod.MOD_ID + ".the_gatekeepers", Items.CYAN_BANNER,
            new Pair<>(BannerPattern.STRIPE_BOTTOM, DyeColor.MAGENTA),
            new Pair<>(BannerPattern.STRIPE_SMALL, DyeColor.PURPLE),
            new Pair<>(BannerPattern.HALF_HORIZONTAL, DyeColor.PURPLE),
            new Pair<>(BannerPattern.RHOMBUS_MIDDLE, DyeColor.BLACK),
            new Pair<>(BannerPattern.TRIANGLES_TOP, DyeColor.CYAN),
            new Pair<>(BannerPattern.TRIANGLES_BOTTOM, DyeColor.CYAN),
            new Pair<>(BannerPattern.CROSS, DyeColor.CYAN),
            new Pair<>(BannerPattern.STRIPE_SMALL, DyeColor.BLACK),
            new Pair<>(BannerPattern.CURLY_BORDER, DyeColor.BLACK));
    public static final ItemStack ORCHID_CITY = BannerUtil.create("itemStack." + RainimatorMod.MOD_ID + ".orchid_city", Items.GRAY_BANNER,
            new Pair<>(BannerPattern.GRADIENT, DyeColor.MAGENTA),
            new Pair<>(BannerPattern.CROSS, DyeColor.WHITE),
            new Pair<>(BannerPattern.CROSS, DyeColor.BLACK),
            new Pair<>(BannerPattern.CURLY_BORDER, DyeColor.BLACK),
            new Pair<>(BannerPattern.TRIANGLES_TOP, DyeColor.BLACK),
            new Pair<>(BannerPattern.TRIANGLES_BOTTOM, DyeColor.BLACK),
            new Pair<>(BannerPattern.CIRCLE_MIDDLE, DyeColor.MAGENTA),
            new Pair<>(BannerPattern.FLOWER, DyeColor.WHITE));
    public static final ItemStack LIGHTBORNE_ISLES = BannerUtil.create("itemStack." + RainimatorMod.MOD_ID + ".lightborne_isles", Items.WHITE_BANNER,
            new Pair<>(BannerPattern.STRIPE_CENTER, DyeColor.GRAY),
            new Pair<>(BannerPattern.STRIPE_MIDDLE, DyeColor.GRAY),
            new Pair<>(BannerPattern.CURLY_BORDER, DyeColor.BLACK),
            new Pair<>(BannerPattern.CROSS, DyeColor.BLACK),
            new Pair<>(BannerPattern.FLOWER, DyeColor.LIGHT_GRAY),
            new Pair<>(BannerPattern.CIRCLE_MIDDLE, DyeColor.WHITE));
    public static final ItemStack THE_UMBRAL_KINGDOM = BannerUtil.create("itemStack." + RainimatorMod.MOD_ID + ".the_umbral_kingdom", Items.BLACK_BANNER,
            new Pair<>(BannerPattern.STRIPE_CENTER, DyeColor.WHITE),
            new Pair<>(BannerPattern.BRICKS, DyeColor.LIGHT_BLUE),
            new Pair<>(BannerPattern.BRICKS, DyeColor.BLACK),
            new Pair<>(BannerPattern.STRIPE_DOWNLEFT, DyeColor.CYAN),
            new Pair<>(BannerPattern.STRIPE_DOWNRIGHT, DyeColor.MAGENTA),
            new Pair<>(BannerPattern.RHOMBUS_MIDDLE, DyeColor.CYAN),
            new Pair<>(BannerPattern.CREEPER, DyeColor.MAGENTA),
            new Pair<>(BannerPattern.CROSS, DyeColor.BLACK),
            new Pair<>(BannerPattern.CIRCLE_MIDDLE, DyeColor.MAGENTA),
            new Pair<>(BannerPattern.FLOWER, DyeColor.BLACK),
            new Pair<>(BannerPattern.SKULL, DyeColor.BLACK));
    public static final ItemStack CHORUS_BAY = BannerUtil.create("itemStack." + RainimatorMod.MOD_ID + ".chorus_bay", Items.LIGHT_BLUE_BANNER,
            new Pair<>(BannerPattern.GRADIENT, DyeColor.BLUE),
            new Pair<>(BannerPattern.TRIANGLES_TOP, DyeColor.WHITE),
            new Pair<>(BannerPattern.FLOWER, DyeColor.LIGHT_BLUE),
            new Pair<>(BannerPattern.CURLY_BORDER, DyeColor.BLACK),
            new Pair<>(BannerPattern.CIRCLE_MIDDLE, DyeColor.WHITE));
    public static final ItemStack VOID_ISLANDS = BannerUtil.create("itemStack." + RainimatorMod.MOD_ID + ".void_islands", Items.WHITE_BANNER,
            new Pair<>(BannerPattern.GRADIENT, DyeColor.BLACK),
            new Pair<>(BannerPattern.STRIPE_SMALL, DyeColor.BLACK),
            new Pair<>(BannerPattern.RHOMBUS_MIDDLE, DyeColor.WHITE),
            new Pair<>(BannerPattern.CURLY_BORDER, DyeColor.WHITE),
            new Pair<>(BannerPattern.FLOWER, DyeColor.BLACK),
            new Pair<>(BannerPattern.BORDER, DyeColor.BLACK));
}
