package com.rainimator.rainimatormod.item.tool;

import com.rainimator.rainimatormod.registry.util.ModCreativeTab;
import com.rainimator.rainimatormod.registry.util.TierBase;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import org.jetbrains.annotations.NotNull;

public class SappriesshovelItem extends ShovelItem {
    public SappriesshovelItem() {
        super(TierBase.of(1000, 10.0F, 3.0F, 3, 20), 1.0F, -2.2F, ModCreativeTab.createProperty());
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack itemstack, @NotNull LivingEntity entity, @NotNull LivingEntity sourceentity) {
        boolean retval = super.hurtEnemy(itemstack, entity, sourceentity);
        if (Math.random() < 0.5D) {
            if (!entity.level.isClientSide()) {
                entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 0));
                entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 0));
            }
        }
        return retval;
    }
}
