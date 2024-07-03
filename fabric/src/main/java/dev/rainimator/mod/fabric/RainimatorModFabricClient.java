package dev.rainimator.mod.fabric;

import dev.rainimator.mod.RainimatorMod;
import dev.rainimator.mod.fabric.compat.trinkets.TrinketsRegistry;
import net.fabricmc.api.ClientModInitializer;

public class RainimatorModFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        RainimatorMod.initClient();
        RainimatorMod.processClient();
        TrinketsRegistry.registerClient();
    }
}
