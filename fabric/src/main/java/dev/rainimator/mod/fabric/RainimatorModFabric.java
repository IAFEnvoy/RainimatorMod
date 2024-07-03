package dev.rainimator.mod.fabric;

import dev.rainimator.mod.RainimatorMod;
import dev.rainimator.mod.fabric.compat.trinkets.TrinketsRegistry;
import net.fabricmc.api.ModInitializer;

public class RainimatorModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        RainimatorMod.init();
        RainimatorMod.process();
        FabricApiCall.run();
        TrinketsRegistry.registerCommon();
    }
}