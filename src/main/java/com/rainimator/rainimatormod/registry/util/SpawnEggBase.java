package com.rainimator.rainimatormod.registry.util;

import com.rainimator.rainimatormod.util.Episode;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeSpawnEggItem;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Supplier;

public class SpawnEggBase extends ForgeSpawnEggItem implements IRainimatorInfo {
    private final Episode episode;

    public SpawnEggBase(Episode episode, Supplier<? extends EntityType<? extends Mob>> type, int backgroundColor, int highlightColor, Properties props) {
        super(type, backgroundColor, highlightColor, props);
        this.episode = episode;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemstack, Level world, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        if (this.episode != Episode.None)
            list.add(new TextComponent(RainimatorInfoManager.getHoverText()));
    }

    @Override
    public Episode getEpisode() {
        return this.episode;
    }
}
