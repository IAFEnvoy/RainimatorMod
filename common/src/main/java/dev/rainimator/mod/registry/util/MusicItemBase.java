package dev.rainimator.mod.registry.util;

import dev.rainimator.mod.registry.RainimatorItemGroups;
import dev.rainimator.mod.registry.RainimatorSounds;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Rarity;

public class MusicItemBase extends MusicDiscItem {
    public MusicItemBase(SoundEvent soundEvent) {
        super(2, soundEvent, new Settings().maxCount(1).rarity(Rarity.RARE).arch$tab(RainimatorItemGroups.ITEM), 0);
    }//TODO:LENGTH

    @Override
    @Environment(EnvType.CLIENT)
    public boolean hasGlint(ItemStack itemtack) {
        return true;
    }
}
