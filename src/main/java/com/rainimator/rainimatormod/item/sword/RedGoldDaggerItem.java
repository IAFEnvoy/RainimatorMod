package com.rainimator.rainimatormod.item.sword;

import com.rainimator.rainimatormod.registry.ModItems;
import com.rainimator.rainimatormod.registry.util.ModCreativeTab;
import com.rainimator.rainimatormod.registry.util.TierBase;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class RedGoldDaggerItem extends SwordItem {
    public RedGoldDaggerItem() {
        super(TierBase.of(1000, 4.0F, 6.0F, 0, 3, ModItems.RUBY), 3, -2.0F, ModCreativeTab.createProperty());
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack itemstack, @NotNull LivingEntity entity, @NotNull LivingEntity sourceentity) {
        boolean ret_val = super.hurtEnemy(itemstack, entity, sourceentity);
        if (entity instanceof Mob _entity)
            _entity.setTarget(sourceentity);
        if (Math.random() < 0.4D)
            entity.setSecondsOnFire(5);
        if (Math.random() < 0.2D)
            sourceentity.setHealth(sourceentity.getHealth() + Mth.nextInt(new Random(), 1, 2));
        return ret_val;
    }
}
