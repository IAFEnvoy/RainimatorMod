package com.iafenvoy.rainimator;

import com.afoxxvi.asteorbar.overlay.Overlays;
import com.iafenvoy.neptune.accessory.AccessoryManager;
import com.iafenvoy.rainimator.compat.asteorbar.ManaHud;
import com.iafenvoy.rainimator.registry.RainimatorItems;
import com.iafenvoy.rainimator.registry.RainimatorRenderers;
import dev.architectury.platform.Platform;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class RainimatorModClient {
    public static void initClient() {
        RainimatorRenderers.registerEntityRenderers();
        RainimatorRenderers.registerLayerDefinitions();
        RainimatorRenderers.registerParticles();
    }

    public static void processClient() {
        RainimatorRenderers.registerArmorRenderers();
        RainimatorRenderers.registerSkulls();
        RainimatorRenderers.registerModelPredicates();
        RainimatorRenderers.registerRenderLayers();
        AccessoryManager.registerBack(RainimatorItems.RAIN_SWORD.get(), (matrices, left) -> matrices.translate(0, 0, 0.2));
        AccessoryManager.registerBack(RainimatorItems.ENDER_BIG_SWORD.get(), (matrices, left) -> matrices.translate(-0.1, -0.2, 0.2));

        if (Platform.isModLoaded("asteorbar"))
            Overlays.registerOverlayAtLast(new ManaHud(), null);
    }
}
