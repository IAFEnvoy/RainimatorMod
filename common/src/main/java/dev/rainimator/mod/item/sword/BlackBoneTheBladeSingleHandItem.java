package dev.rainimator.mod.item.sword;

import dev.rainimator.mod.registry.RainimatorItemGroups;
import dev.rainimator.mod.registry.util.SwordItemBase;
import dev.rainimator.mod.registry.util.ToolMaterialUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public class BlackBoneTheBladeSingleHandItem extends SwordItemBase {
    public BlackBoneTheBladeSingleHandItem() {
        super(ToolMaterialUtil.of(1500, 0.0F, 10.0F, 0, 0), 3, -2.3F, new Settings().fireproof().group(RainimatorItemGroups.MAIN));
    }

    @Override
    public boolean postHit(ItemStack itemtack, LivingEntity entity, LivingEntity sourceentity) {
        boolean retval = super.postHit(itemtack, entity, sourceentity);
        entity.setOnFireFor(10);
        return retval;
    }
}

