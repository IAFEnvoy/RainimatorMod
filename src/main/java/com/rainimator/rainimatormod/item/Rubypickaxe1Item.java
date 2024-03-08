package com.rainimator.rainimatormod.item;

import com.rainimator.rainimatormod.registry.util.ModCreativeTab;
import com.rainimator.rainimatormod.registry.util.TierBase;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import org.jetbrains.annotations.NotNull;

public class Rubypickaxe1Item extends PickaxeItem {
    public Rubypickaxe1Item() {
        super(TierBase.of(1500, 10.0F, 4.0F, 3, 20), 1, -2.2F, ModCreativeTab.createProperty());
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
