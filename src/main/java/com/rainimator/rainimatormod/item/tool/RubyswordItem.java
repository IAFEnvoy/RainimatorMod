package com.rainimator.rainimatormod.item.tool;

import com.rainimator.rainimatormod.registry.util.ModCreativeTab;
import com.rainimator.rainimatormod.registry.util.TierBase;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import org.jetbrains.annotations.NotNull;

public class RubyswordItem
        extends SwordItem {
    public RubyswordItem() {
        super(TierBase.of(1000, 0.0F, 6.0F, 0, 20), 3, -2.0F, ModCreativeTab.createProperty());
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack itemstack, @NotNull LivingEntity entity, @NotNull LivingEntity sourceentity) {
        boolean retval = super.hurtEnemy(itemstack, entity, sourceentity);
        if (Math.random() < 0.5D) {
            entity.setSecondsOnFire(5);
            entity.hurt(DamageSource.IN_FIRE, 2.0F);
        }
        return retval;
    }
}