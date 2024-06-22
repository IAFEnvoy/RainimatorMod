package dev.rainimator.mod.item.sword;

import dev.rainimator.mod.registry.util.SwordItemBase;
import dev.rainimator.mod.registry.util.ToolMaterialUtil;
import dev.rainimator.mod.registry.RainimatorItemGroups;
import dev.rainimator.mod.registry.RainimatorItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public class HotGlovesItem extends SwordItemBase {
    public HotGlovesItem() {
        super(ToolMaterialUtil.of(1000, 4.0F, 5.0F, 1, 2, RainimatorItems.HOT_IRON), 3, 0.0F, new Settings().fireproof().arch$tab(RainimatorItemGroups.MAIN));
    }

    @Override
    public boolean postHit(ItemStack itemtack, LivingEntity entity, LivingEntity sourceentity) {
        boolean ret_val = super.postHit(itemtack, entity, sourceentity);
        if (Math.random() < 0.7D)
            entity.setOnFireFor(5);
        return ret_val;
    }
}
