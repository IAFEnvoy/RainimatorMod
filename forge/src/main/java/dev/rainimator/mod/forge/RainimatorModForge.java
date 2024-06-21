package dev.rainimator.mod.forge;

import dev.architectury.platform.Platform;
import dev.architectury.platform.forge.EventBuses;
import dev.rainimator.mod.RainimatorMod;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(RainimatorMod.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RainimatorModForge {
    public RainimatorModForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(RainimatorMod.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        RainimatorMod.init();
        if (Platform.getEnv() == Dist.CLIENT)
            RainimatorMod.initClient();
    }

    @SubscribeEvent
    public static void afterRegister(FMLCommonSetupEvent event) {
        event.enqueueWork(RainimatorMod::process);
    }

    @SubscribeEvent
    public static void onClientInit(FMLClientSetupEvent event) {
        event.enqueueWork(RainimatorMod::processClient);
    }
}