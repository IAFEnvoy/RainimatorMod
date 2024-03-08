package com.rainimator.rainimatormod.item;

import com.rainimator.rainimatormod.registry.ModItems;
import com.rainimator.rainimatormod.registry.util.ModCreativeTab;
import com.rainimator.rainimatormod.registry.util.TierBase;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

public class LaserSwordItem extends SwordItem {
    public LaserSwordItem() {
        super(TierBase.of(3000, 4.0F, 13.0F, 1, 18, ModItems.SUPER_SPPARIES.get(), Items.ENDER_EYE), 3, -2.4F, ModCreativeTab.createProperty());
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public boolean isFoil(@NotNull ItemStack itemstack) {
        return true;
    }
}
