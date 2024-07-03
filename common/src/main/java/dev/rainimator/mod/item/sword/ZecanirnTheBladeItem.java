package dev.rainimator.mod.item.sword;

import dev.rainimator.mod.registry.RainimatorItemGroups;
import dev.rainimator.mod.registry.RainimatorItems;
import dev.rainimator.mod.registry.util.IRainimatorInfo;
import dev.rainimator.mod.registry.util.SwordItemBase;
import dev.rainimator.mod.registry.util.ToolMaterialUtil;
import dev.rainimator.mod.util.Episode;

public class ZecanirnTheBladeItem extends SwordItemBase implements IRainimatorInfo {
    public ZecanirnTheBladeItem() {
        super(ToolMaterialUtil.of(1500, 4.0F, 11.0F, 0, 20), 3, -2.2F, new Settings().arch$tab(RainimatorItemGroups.MAIN));
    }

    @Override
    public Episode getEpisode() {
        return Episode.Goodbye;
    }
}
