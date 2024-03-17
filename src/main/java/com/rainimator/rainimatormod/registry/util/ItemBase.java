package com.rainimator.rainimatormod.registry.util;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Function;

public class ItemBase extends Item {
    public ItemBase(Function<Properties, Properties> properties) {
        super(properties.apply(ModCreativeTab.createProperty().rarity(Rarity.COMMON).stacksTo(64)));
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemstack, Level world, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        if(this instanceof IRainimatorInfo)
            list.add(new TextComponent(RainimatorInfoManager.getHoverText()));
    }
}
