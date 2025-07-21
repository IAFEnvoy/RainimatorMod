package com.iafenvoy.rainimator.item.sword;

import com.iafenvoy.neptune.object.item.ThrowableWeaponItem;
import com.iafenvoy.neptune.object.item.ToolMaterialUtil;
import com.iafenvoy.neptune.util.RandomHelper;
import com.iafenvoy.rainimator.registry.RainimatorItemGroups;
import com.iafenvoy.rainimator.registry.RainimatorItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;

public class AbigailSpearItem extends ThrowableWeaponItem {
    public AbigailSpearItem() {
        super(ToolMaterialUtil.of(2000, 4.0F, 8.0F, 1, 20, RainimatorItems.SUPER_RUBY::get), 3, -2.0F, new Settings().fireproof().arch$tab(RainimatorItemGroups.MAIN), new ThrowSettings());
    }

    @Override
    public boolean postHit(ItemStack itemtack, LivingEntity entity, LivingEntity sourceentity) {
        boolean ret_val = super.postHit(itemtack, entity, sourceentity);
        if (entity instanceof MobEntity _entity)
            _entity.setTarget(sourceentity);
        if (Math.random() < 0.5D)
            sourceentity.setHealth(sourceentity.getHealth() + RandomHelper.nextInt(1, 3));
        return ret_val;
    }
}
