package com.iafenvoy.rainimator.item.sword;

import com.iafenvoy.neptune.object.item.ToolMaterialUtil;
import com.iafenvoy.rainimator.registry.RainimatorItemGroups;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SwordItem;

public class NetheriteFlameSaberItem extends SwordItem {
    public NetheriteFlameSaberItem() {
        super(ToolMaterialUtil.of(2000, 4.0F, 7.0F, 1, 2, () -> Items.NETHERITE_INGOT), 3, -2.0F, new Settings().fireproof().arch$tab(RainimatorItemGroups.MAIN));
    }

    @Override
    public boolean postHit(ItemStack itemtack, LivingEntity entity, LivingEntity sourceentity) {
        entity.setOnFireFor(10);
        return super.postHit(itemtack, entity, sourceentity);
    }
}

