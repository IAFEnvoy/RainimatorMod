package dev.rainimator.mod.item.sword;

import dev.rainimator.mod.registry.RainimatorItemGroups;
import dev.rainimator.mod.registry.util.SwordItemBase;
import dev.rainimator.mod.registry.util.ToolMaterialUtil;

public class DiamondLanceItem extends SwordItemBase {
    public DiamondLanceItem() {
        super(ToolMaterialUtil.of(2000, 4.0F, 7.0F, 0, 3), 3, -2.0F, new Settings().group(RainimatorItemGroups.MAIN));
    }
}
