package dev.rainimator.mod.impl;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.rainimator.mod.item.armor.DecoratingArmorItem;
import dev.rainimator.mod.registry.RainimatorItemGroups;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import org.apache.commons.lang3.NotImplementedException;

public class NetherTheCrownItem extends DecoratingArmorItem {
    public NetherTheCrownItem() {
        super(ArmorItem.Type.HELMET, new Item.Settings().arch$tab(RainimatorItemGroups.MAIN));
    }

    @ExpectPlatform
    public static NetherTheCrownItem create() {
        throw new NotImplementedException("This method should be replaced by Architectury.");
    }
}
