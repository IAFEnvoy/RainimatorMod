package dev.rainimator.mod.item.sword;

import dev.rainimator.mod.registry.util.FoilSwordItemBase;
import dev.rainimator.mod.registry.util.ToolMaterialUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;

public class FallenSoulAxeItem extends FoilSwordItemBase {
    public FallenSoulAxeItem() {
        super(ToolMaterialUtil.of(1000, 0.0F, 6.0F, 0, 10), 3, -2.3F, new Settings().fireproof());
    }

    @Override
    public boolean postHit(ItemStack itemtack, LivingEntity entity, LivingEntity sourceentity) {
        boolean retval = super.postHit(itemtack, entity, sourceentity);
        if (!entity.world.isClient())
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 80, 0));
        return retval;
    }
}
