package dev.rainimator.mod.item.sword;

import com.iafenvoy.neptune.object.item.SwordItemBase;
import com.iafenvoy.neptune.object.item.ToolMaterialUtil;
import dev.rainimator.mod.registry.RainimatorItemGroups;
import dev.rainimator.mod.registry.RainimatorItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;

public class HerobrineTomahawkItem extends SwordItemBase {
    public HerobrineTomahawkItem() {
        super(ToolMaterialUtil.of(2500, 12.0F, 11.0F, 0, 20, RainimatorItems.SUPER_RUBY::get, RainimatorItems.SUPER_SAPPHIRE::get), 3, -2.2F, new Settings().fireproof().arch$tab(RainimatorItemGroups.MAIN));
    }

    @Override
    public boolean postHit(ItemStack itemtack, LivingEntity entity, LivingEntity sourceentity) {
        boolean ret_val = super.postHit(itemtack, entity, sourceentity);
        if (entity.getWorld().isClient()) return ret_val;
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 100, 0));
        if (Math.random() < 0.5D) {
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 60, 0));
            if (Math.random() < 0.1D) {
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 100, 1));
                if (Math.random() < 0.05D) {
                    entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 100, 1));
                    if (Math.random() < 0.025D) {
                        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 100, 2));
                        if (Math.random() < 0.001D)
                            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 100, 3));
                    }
                }
            }
        }
        return ret_val;
    }
}
