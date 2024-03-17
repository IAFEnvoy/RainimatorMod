package com.rainimator.rainimatormod.item.sword;

import com.rainimator.rainimatormod.registry.util.ModCreativeTab;
import com.rainimator.rainimatormod.registry.util.SwordItemBase;
import com.rainimator.rainimatormod.registry.util.TierBase;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

public class LowerBoundAlloyFlameSaberItem extends SwordItemBase {
    public LowerBoundAlloyFlameSaberItem() {
        super(TierBase.of(2000, 4.0F, 7.0F, 1, 2, Items.NETHERITE_INGOT), 3, -2.0F, ModCreativeTab.createProperty().fireResistant());
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack itemstack, @NotNull LivingEntity entity, @NotNull LivingEntity sourceentity) {
        boolean retval = super.hurtEnemy(itemstack, entity, sourceentity);
        entity.setSecondsOnFire(10);
        return retval;
    }
}

