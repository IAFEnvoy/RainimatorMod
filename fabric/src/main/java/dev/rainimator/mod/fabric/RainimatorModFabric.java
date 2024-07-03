package dev.rainimator.mod.fabric;

import dev.rainimator.mod.RainimatorMod;
import net.fabricmc.api.ModInitializer;

public class RainimatorModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        RainimatorMod.init();
        RainimatorMod.process();
    }
}