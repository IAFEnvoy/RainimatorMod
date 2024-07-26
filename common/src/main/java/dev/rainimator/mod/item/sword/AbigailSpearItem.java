package dev.rainimator.mod.item.sword;

import com.iafenvoy.neptune.object.item.SwordItemBase;
import com.iafenvoy.neptune.object.item.ToolMaterialUtil;
import com.iafenvoy.neptune.util.RandomHelper;
import dev.rainimator.mod.registry.RainimatorItemGroups;
import dev.rainimator.mod.registry.RainimatorItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;

public class AbigailSpearItem extends SwordItemBase {
    public AbigailSpearItem() {
        super(ToolMaterialUtil.of(2000, 4.0F, 8.0F, 1, 20, RainimatorItems.SUPER_RUBY::get), 3, -2.0F, new Settings().fireproof().arch$tab(RainimatorItemGroups.MAIN));
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
