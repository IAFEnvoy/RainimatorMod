package com.rainimator.rainimatormod.registry.util;

import com.rainimator.rainimatormod.registry.ModSounds;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MusicItemBase extends RecordItem {
    public MusicItemBase(String musicId) {
        super(2, () -> ModSounds.REGISTRY.get(musicId), ModCreativeTab.createProperty("items").stacksTo(1).rarity(Rarity.RARE));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public boolean isFoil(@NotNull ItemStack itemstack) {
        return true;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemstack, Level world, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        if(this instanceof IRainimatorInfo)
            list.add(new TextComponent(RainimatorInfoManager.getHoverText()));
    }
}
