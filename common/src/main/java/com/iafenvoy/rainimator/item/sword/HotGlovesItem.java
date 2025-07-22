package com.iafenvoy.rainimator.item.sword;

import com.iafenvoy.neptune.object.item.ToolMaterialUtil;
import com.iafenvoy.rainimator.registry.RainimatorItemGroups;
import com.iafenvoy.rainimator.registry.RainimatorItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;

public class HotGlovesItem extends SwordItem {
    public HotGlovesItem() {
        super(ToolMaterialUtil.of(1000, 4.0F, 5.0F, 1, 2, RainimatorItems.HOT_IRON::get), 3, 0.0F, new Settings().fireproof().arch$tab(RainimatorItemGroups.MAIN));
    }

    @Override
    public boolean postHit(ItemStack itemtack, LivingEntity entity, LivingEntity sourceentity) {
        if (Math.random() < 0.7D) entity.setOnFireFor(5);
        return super.postHit(itemtack, entity, sourceentity);
    }
}
