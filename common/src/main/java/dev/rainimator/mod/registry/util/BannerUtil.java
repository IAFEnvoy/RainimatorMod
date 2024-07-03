package dev.rainimator.mod.registry.util;

import net.minecraft.block.entity.BannerPattern;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Style;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Formatting;
import net.minecraft.util.Pair;

public class BannerUtil {
    @SafeVarargs
    public static ItemStack create(String translateId, Item baseItem, Pair<BannerPattern, DyeColor>... patterns) {
        BannerPattern.Patterns p = new BannerPattern.Patterns();
        for (Pair<BannerPattern, DyeColor> pattern : patterns)
            p.add(pattern.getLeft(), pattern.getRight());
        ItemStack itemStack = new ItemStack(baseItem);
        itemStack.getOrCreateSubTag("BlockEntityTag").put("Patterns", p.toTag());
        itemStack.setCustomName(new TranslatableText(translateId).setStyle(Style.EMPTY.withColor(Formatting.AQUA).withItalic(false)));
        itemStack.addHideFlag(ItemStack.TooltipSection.ADDITIONAL);
        return itemStack;
    }
}
