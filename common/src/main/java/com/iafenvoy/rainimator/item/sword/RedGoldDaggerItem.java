package com.iafenvoy.rainimator.item.sword;

import com.iafenvoy.neptune.object.item.ToolMaterialUtil;
import com.iafenvoy.neptune.util.RandomHelper;
import com.iafenvoy.rainimator.registry.RainimatorItemGroups;
import com.iafenvoy.rainimator.registry.RainimatorItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;

public class RedGoldDaggerItem extends SwordItem {
    public RedGoldDaggerItem() {
        super(ToolMaterialUtil.of(1000, 4.0F, 6.0F, 0, 3, RainimatorItems.RUBY::get), 3, -2.0F, new Settings().arch$tab(RainimatorItemGroups.MAIN));
    }

    @Override
    public boolean postHit(ItemStack itemtack, LivingEntity entity, LivingEntity sourceentity) {
        boolean ret_val = super.postHit(itemtack, entity, sourceentity);
        if (entity instanceof MobEntity _entity)
            _entity.setTarget(sourceentity);
        if (Math.random() < 0.4D)
            entity.setOnFireFor(5);
        if (Math.random() < 0.2D)
            sourceentity.setHealth(sourceentity.getHealth() + RandomHelper.nextInt(1, 2));
        return ret_val;
    }
}
