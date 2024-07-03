package dev.rainimator.mod.item.tool;

import dev.rainimator.mod.registry.RainimatorItemGroups;
import dev.rainimator.mod.registry.util.ToolMaterialUtil;
import net.minecraft.item.AxeItem;

public class IntelligenceTomahawkItem extends AxeItem {
    public IntelligenceTomahawkItem() {
        super(ToolMaterialUtil.of(8000, 4.0F, 8.0F, 1, 5), 1.0F, -2.2F, new Settings().group(RainimatorItemGroups.MAIN));
    }
}
