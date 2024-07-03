package dev.rainimator.mod.item.sword;

import dev.rainimator.mod.registry.RainimatorItemGroups;
import dev.rainimator.mod.registry.util.SwordItemBase;
import dev.rainimator.mod.registry.util.ToolMaterialUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;

public class NaeusSwordItem extends SwordItemBase {
    public NaeusSwordItem() {
        super(ToolMaterialUtil.of(4000, 4.0F, 9.0F, 0, 10), 3, -2.0F, new Settings().fireproof().group(RainimatorItemGroups.MAIN));
    }

    @Override
    public boolean postHit(ItemStack itemtack, LivingEntity entity, LivingEntity sourceEntity) {
        boolean ret_val = super.postHit(itemtack, entity, sourceEntity);
        if (!entity.world.isClient())
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 100, 0));
        entity.setOnFireFor(5);
        return ret_val;
    }
}