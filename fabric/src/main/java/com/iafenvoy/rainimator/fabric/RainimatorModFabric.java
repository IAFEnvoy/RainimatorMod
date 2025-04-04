package com.iafenvoy.rainimator.fabric;

import com.iafenvoy.rainimator.RainimatorMod;
import net.fabricmc.api.ModInitializer;

public class RainimatorModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        RainimatorMod.init();
        RainimatorMod.process();
        FabricApiCall.run();
    }
}