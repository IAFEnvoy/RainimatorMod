package dev.rainimator.mod.item.sword;

import dev.rainimator.mod.registry.RainimatorItemGroups;
import dev.rainimator.mod.registry.util.IRainimatorInfo;
import dev.rainimator.mod.registry.util.SwordItemBase;
import dev.rainimator.mod.registry.util.ToolMaterialUtil;
import dev.rainimator.mod.util.Episode;

public class AbigailSpearItem extends SwordItemBase implements IRainimatorInfo {
    public AbigailSpearItem() {
        super(ToolMaterialUtil.of(2000, 4.0F, 8.0F, 1, 20), 3, -2.0F, new Settings().fireproof().group(RainimatorItemGroups.MAIN));
    }

    @Override
    public Episode getEpisode() {
        return Episode.BeginAgain;
    }
}
