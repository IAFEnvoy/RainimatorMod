package com.iafenvoy.rainimator.forge;

import com.iafenvoy.rainimator.RainimatorModClient;
import com.iafenvoy.rainimator.forge.compat.curios.CuriosRegistry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RainimatorModForgeClient {
    @SubscribeEvent
    public static void onInit(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            RainimatorModClient.processClient();
            CuriosRegistry.registerClient();
        });
    }
}