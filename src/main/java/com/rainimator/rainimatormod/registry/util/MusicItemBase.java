package com.rainimator.rainimatormod.registry.util;

import com.rainimator.rainimatormod.registry.ModSounds;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.RecordItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

public class MusicItemBase extends RecordItem {
    public MusicItemBase(String musicId) {
        super(2, () -> ModSounds.REGISTRY.get(musicId), ModCreativeTab.createProperty("items").stacksTo(1).rarity(Rarity.RARE));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public boolean isFoil(@NotNull ItemStack itemstack) {
        return true;
    }
}
