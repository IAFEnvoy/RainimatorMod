package dev.rainimator.mod.item.sword;

import dev.rainimator.mod.registry.RainimatorItemGroups;
import dev.rainimator.mod.registry.util.SwordItemBase;
import dev.rainimator.mod.registry.util.ToolMaterialUtil;

public class RedGoldDaggerItem extends SwordItemBase {
    public RedGoldDaggerItem() {
        super(ToolMaterialUtil.of(1000, 4.0F, 6.0F, 0, 3), 3, -2.0F, new Settings().group(RainimatorItemGroups.MAIN));
    }
}
