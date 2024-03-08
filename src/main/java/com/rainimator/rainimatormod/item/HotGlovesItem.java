package com.rainimator.rainimatormod.item;

import com.rainimator.rainimatormod.registry.ModItems;
import com.rainimator.rainimatormod.registry.util.ModCreativeTab;
import com.rainimator.rainimatormod.registry.util.TierBase;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import org.jetbrains.annotations.NotNull;

public class HotGlovesItem extends SwordItem {
    public HotGlovesItem() {
        super(TierBase.of(1000, 4.0F, 5.0F, 1, 2, ModItems.HOT_IRON), 3, 0.0F, ModCreativeTab.createProperty().fireResistant());
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack itemstack, @NotNull LivingEntity entity, @NotNull LivingEntity sourceentity) {
        boolean ret_val = super.hurtEnemy(itemstack, entity, sourceentity);
        if (Math.random() < 0.7D)
            entity.setSecondsOnFire(5);
        return ret_val;
    }
}
