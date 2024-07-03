package dev.rainimator.mod.item.tool;

import dev.rainimator.mod.registry.RainimatorItemGroups;
import dev.rainimator.mod.registry.util.ToolMaterialUtil;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;

public class DiamondHatchetAxeItem extends AxeItem {
    public DiamondHatchetAxeItem() {
        super(ToolMaterialUtil.of(1500, 4.0F, 5.0F, 2, 2), 1.0F, -2.0F, new Item.Settings().group(RainimatorItemGroups.MAIN));
    }
}
