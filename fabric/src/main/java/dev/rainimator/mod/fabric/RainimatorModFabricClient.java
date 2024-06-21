package dev.rainimator.mod.fabric;

import com.afoxxvi.asteorbar.overlay.FabricGuiRegistry;
import dev.rainimator.mod.RainimatorMod;
import dev.rainimator.mod.fabric.compat.asteorbar.ManaHud;
import dev.rainimator.mod.fabric.compat.trinkets.TrinketsRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class RainimatorModFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        RainimatorMod.initClient();
        RainimatorMod.processClient();
        TrinketsRegistry.registerClient();

        if (FabricLoader.getInstance().isModLoaded("asteorbar"))
            FabricGuiRegistry.REGISTRY.add(FabricGuiRegistry.REGISTRY.size() - 1, new ManaHud());
    }
}
