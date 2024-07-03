package dev.rainimator.mod.item.sword;

import dev.rainimator.mod.registry.RainimatorItemGroups;
import dev.rainimator.mod.registry.util.SwordItemBase;
import dev.rainimator.mod.registry.util.ToolMaterialUtil;
import net.minecraft.item.Items;

public class EnderBigSwordItem extends SwordItemBase {
    public EnderBigSwordItem() {
        super(ToolMaterialUtil.of(2000, 4.0F, 9.0F, 1, 15, () -> Items.ENDER_EYE), 3, -2.2F, new Settings().group(RainimatorItemGroups.MAIN));
    }
}

