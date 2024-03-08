package com.rainimator.rainimatormod.util;

import com.rainimator.rainimatormod.RainimatorMod;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

public class MiscUtil {
    public static void playSound(Level world, int x, int y, int z, String soundId, float volume, float pitch) {
        SoundEvent soundEvent = ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(RainimatorMod.MOD_ID, soundId));
        if (soundEvent == null) return;
        if (world.isClientSide())
            world.playLocalSound(x, y, z, soundEvent, SoundSource.NEUTRAL, volume, pitch, false);
        else
            world.playSound(null, new BlockPos(x, y, z), soundEvent, SoundSource.NEUTRAL, volume, pitch);
    }
}
