package com.iafenvoy.rainimator.fabric;

import com.iafenvoy.rainimator.RainimatorMod;
import com.iafenvoy.rainimator.fabric.compat.trinkets.TrinketsRegistry;
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