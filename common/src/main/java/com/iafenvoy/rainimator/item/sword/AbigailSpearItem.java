package com.iafenvoy.rainimator.item.sword;

import com.iafenvoy.neptune.object.item.ToolMaterialUtil;
import com.iafenvoy.neptune.util.RandomHelper;
import com.iafenvoy.rainimator.registry.RainimatorItemGroups;
import com.iafenvoy.rainimator.registry.RainimatorItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;

public class AbigailSpearItem extends SwordItem {
    public AbigailSpearItem() {
        super(ToolMaterialUtil.of(2000, 4.0F, 8.0F, 1, 20, RainimatorItems.SUPER_RUBY::get), 3, -2.0F, new Settings().fireproof().arch$tab(RainimatorItemGroups.MAIN));
    }

    @Override
    public boolean postHit(ItemStack itemtack, LivingEntity entity, LivingEntity sourceentity) {
        if (entity instanceof MobEntity mob) mob.setTarget(sourceentity);
        if (Math.random() < 0.5D) sourceentity.setHealth(sourceentity.getHealth() + RandomHelper.nextInt(1, 3));
        return super.postHit(itemtack, entity, sourceentity);
    }
}
