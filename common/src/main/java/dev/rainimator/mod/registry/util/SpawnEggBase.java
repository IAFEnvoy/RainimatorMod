package dev.rainimator.mod.registry.util;

import dev.rainimator.mod.registry.RainimatorItemGroups;
import dev.rainimator.mod.util.Episode;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.List;

public class SpawnEggBase extends SpawnEggItem implements IRainimatorInfo {
    private final Episode episode;

    public SpawnEggBase(Episode episode, EntityType<? extends MobEntity> type, int backgroundColor, int highlightColor) {
        super(type, backgroundColor, highlightColor, new Settings().arch$tab(RainimatorItemGroups.MOBS));
        this.episode = episode;
    }

    @Override
    public void appendTooltip(ItemStack itemtack, World world, List<Text> list, TooltipContext flag) {
        super.appendTooltip(itemtack, world, list, flag);
        if (this.episode != Episode.None)
            list.add(Text.literal(RainimatorInfoManager.getHoverText()));
    }

    @Override
    public Episode getEpisode() {
        return this.episode;
    }
}
