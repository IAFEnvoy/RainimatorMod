package com.iafenvoy.rainimator.item.sword;

import com.iafenvoy.neptune.object.item.SwordItemBase;
import com.iafenvoy.neptune.object.item.ToolMaterialUtil;
import com.iafenvoy.rainimator.registry.RainimatorItemGroups;
import com.iafenvoy.rainimator.registry.RainimatorItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public class BlackBoneTheBladeSingleHandItem extends SwordItemBase {
    public BlackBoneTheBladeSingleHandItem() {
        super(ToolMaterialUtil.of(1500, 0.0F, 10.0F, 0, 0, RainimatorItems.RUBY::get), 3, -2.3F, new Settings().fireproof().arch$tab(RainimatorItemGroups.MAIN));
    }

    @Override
    public boolean postHit(ItemStack itemtack, LivingEntity entity, LivingEntity sourceentity) {
        boolean retval = super.postHit(itemtack, entity, sourceentity);
        entity.setOnFireFor(10);
        return retval;
    }
}

