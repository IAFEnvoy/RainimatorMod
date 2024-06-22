package dev.rainimator.mod.impl;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.rainimator.mod.registry.util.ItemBase;
import dev.rainimator.mod.registry.RainimatorItemGroups;
import org.apache.commons.lang3.NotImplementedException;

public class NetherTheCrownItem extends ItemBase {
    public NetherTheCrownItem() {
        super(p -> p.arch$tab(RainimatorItemGroups.MAIN));
    }

    @ExpectPlatform
    public static NetherTheCrownItem create() {
        throw new NotImplementedException("This method should be replaced by Architectury.");
    }
}
