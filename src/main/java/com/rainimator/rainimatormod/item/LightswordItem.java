package com.rainimator.rainimatormod.item;

import com.rainimator.rainimatormod.registry.util.ModCreativeTab;
import com.rainimator.rainimatormod.registry.util.TierBase;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;


public class LightswordItem extends SwordItem {
    public LightswordItem() {
        super(TierBase.of(200,0.0F,5.0F,0,25), 3, -2.2F, ModCreativeTab.createProperty().fireResistant());
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public boolean isFoil(@NotNull ItemStack itemstack) {
        return true;
    }
}

