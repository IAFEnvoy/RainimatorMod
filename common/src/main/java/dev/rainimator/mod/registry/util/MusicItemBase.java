package dev.rainimator.mod.registry.util;

import dev.rainimator.mod.registry.RainimatorItemGroups;
import dev.rainimator.mod.registry.RainimatorItems;
import dev.rainimator.mod.registry.RainimatorSounds;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.util.Rarity;

public class MusicItemBase extends MusicDiscItem {
    public MusicItemBase(String musicId) {
        super(2, RainimatorSounds.REGISTRY_MAP.get(musicId).get(), new Settings().maxCount(1).rarity(Rarity.RARE).group(RainimatorItemGroups.MAIN));
    }

    @Override
    @Environment(EnvType.CLIENT)
    public boolean hasGlint(ItemStack itemtack) {
        return true;
    }
}
