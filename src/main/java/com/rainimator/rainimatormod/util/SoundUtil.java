package com.rainimator.rainimatormod.util;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundStopSoundPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

public class SoundUtil {
    public static void playSound(Level world, double x, double y, double z, ResourceLocation soundId, float volume, float pitch) {
        SoundEvent soundEvent = ForgeRegistries.SOUND_EVENTS.getValue(soundId);
        if (soundEvent == null) return;
        if (world.isClientSide())
            world.playLocalSound(x, y, z, soundEvent, SoundSource.NEUTRAL, volume, pitch, false);
        else
            world.playSound(null, new BlockPos(x, y, z), soundEvent, SoundSource.NEUTRAL, volume, pitch);
    }

    public static void stopSound(Level world, ResourceLocation soundId) {
        if (world instanceof ServerLevel serverLevel) {
            ClientboundStopSoundPacket stopSoundPacket = new ClientboundStopSoundPacket(soundId, SoundSource.NEUTRAL);
            for (ServerPlayer serverPlayer : serverLevel.players())
                serverPlayer.connection.send(stopSoundPacket);
        }
    }
}
