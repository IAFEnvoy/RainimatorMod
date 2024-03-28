package com.rainimator.rainimatormod.network;

import com.rainimator.rainimatormod.RainimatorMod;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class NetworkManager {
    public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(new ResourceLocation(RainimatorMod.MOD_ID, RainimatorMod.MOD_ID), () -> "1", "1"::equals, "1"::equals);
    private static final String PROTOCOL_VERSION = "1";
    private static int messageID = 0;

    public static <T> void addNetworkMessage(Class<T> messageType, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder, BiConsumer<T, Supplier<NetworkEvent.Context>> messageConsumer) {
        PACKET_HANDLER.registerMessage(messageID, messageType, encoder, decoder, messageConsumer);
        messageID++;
    }

    @SubscribeEvent
    public static void registerMessage(FMLCommonSetupEvent event) {
        NetworkManager.addNetworkMessage(EnderBookSkillButtonMessage.class, EnderBookSkillButtonMessage::buffer, EnderBookSkillButtonMessage::new, EnderBookSkillButtonMessage::handler);
    }
}
