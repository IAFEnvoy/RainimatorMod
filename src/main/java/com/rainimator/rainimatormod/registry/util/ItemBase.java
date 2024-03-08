package com.rainimator.rainimatormod.registry.util;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

import java.util.function.Function;

public class ItemBase extends Item {
    public ItemBase(Function<Properties, Properties> properties) {
        super(properties.apply(ModCreativeTab.createProperty().rarity(Rarity.COMMON).stacksTo(64)));
    }
}
