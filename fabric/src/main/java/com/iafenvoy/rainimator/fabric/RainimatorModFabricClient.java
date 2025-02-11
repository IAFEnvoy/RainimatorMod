package com.iafenvoy.rainimator.fabric;

import com.afoxxvi.asteorbar.overlay.FabricGuiRegistry;
import com.iafenvoy.rainimator.RainimatorModClient;
import com.iafenvoy.rainimator.compat.asteorbar.ManaHud;
import com.iafenvoy.rainimator.fabric.compat.trinkets.TrinketsRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class RainimatorModFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        RainimatorModClient.initClient();
        RainimatorModClient.processClient();
        TrinketsRegistry.registerClient();

        if (FabricLoader.getInstance().isModLoaded("asteorbar"))
            FabricGuiRegistry.REGISTRY.add(FabricGuiRegistry.REGISTRY.size() - 1, new ManaHud());
    }
}
