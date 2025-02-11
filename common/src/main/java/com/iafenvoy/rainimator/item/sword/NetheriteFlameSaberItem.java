package com.iafenvoy.rainimator.item.sword;

import com.iafenvoy.neptune.object.item.SwordItemBase;
import com.iafenvoy.neptune.object.item.ToolMaterialUtil;
import com.iafenvoy.rainimator.registry.RainimatorItemGroups;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class NetheriteFlameSaberItem extends SwordItemBase {
    public NetheriteFlameSaberItem() {
        super(ToolMaterialUtil.of(2000, 4.0F, 7.0F, 1, 2, () -> Items.NETHERITE_INGOT), 3, -2.0F, new Settings().fireproof().arch$tab(RainimatorItemGroups.MAIN));
    }

    @Override
    public boolean postHit(ItemStack itemtack, LivingEntity entity, LivingEntity sourceentity) {
        boolean retval = super.postHit(itemtack, entity, sourceentity);
        entity.setOnFireFor(10);
        return retval;
    }
}

