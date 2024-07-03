package dev.rainimator.mod.impl;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.rainimator.mod.registry.RainimatorItemGroups;
import net.minecraft.item.ElytraItem;
import net.minecraft.util.Rarity;
import org.apache.commons.lang3.NotImplementedException;

public class WingsOfSalvationItem extends ElytraItem {
    public WingsOfSalvationItem() {
        super(new Settings().rarity(Rarity.EPIC).maxCount(1).arch$tab(RainimatorItemGroups.MAIN));
    }

    @ExpectPlatform
    public static WingsOfSalvationItem create() {
        throw new NotImplementedException("This method should be replaced by Architectury.");
    }
}
