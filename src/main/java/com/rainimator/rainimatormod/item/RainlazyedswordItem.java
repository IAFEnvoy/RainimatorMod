package com.rainimator.rainimatormod.item;

import com.rainimator.rainimatormod.registry.util.ModCreativeTab;
import com.rainimator.rainimatormod.registry.util.TierBase;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

public class RainlazyedswordItem extends SwordItem {
    public RainlazyedswordItem() {
        super(TierBase.of(1500, 0.0F, 8.0F, 0, 15), 3, -2.0F, ModCreativeTab.createProperty());
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public boolean isFoil(@NotNull ItemStack itemstack) {
        return true;
    }
}
