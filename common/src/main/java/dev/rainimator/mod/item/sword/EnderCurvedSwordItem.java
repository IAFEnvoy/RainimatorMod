package dev.rainimator.mod.item.sword;

import dev.rainimator.mod.registry.util.SwordItemBase;
import dev.rainimator.mod.registry.util.ToolMaterialUtil;

public class EnderCurvedSwordItem extends SwordItemBase {
    public EnderCurvedSwordItem() {
        super(ToolMaterialUtil.of(2000, 0.0F, 6.0F, 0, 25), 3, -2.0F, new Settings().fireproof());
    }
}
