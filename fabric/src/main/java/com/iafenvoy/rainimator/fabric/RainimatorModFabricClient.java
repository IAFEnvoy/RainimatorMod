package com.iafenvoy.rainimator.fabric;

import com.iafenvoy.rainimator.RainimatorModClient;
import com.iafenvoy.rainimator.fabric.compat.trinkets.TrinketsRegistry;
import net.fabricmc.api.ClientModInitializer;

public class RainimatorModFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        RainimatorModClient.initClient();
        RainimatorModClient.processClient();
        TrinketsRegistry.registerClient();
    }
}
