package dev.rainimator.mod;

import com.iafenvoy.neptune.render.tool.BackBeltToolManager;
import dev.rainimator.mod.registry.*;
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
        RainimatorRenderers.registerGui();
        RainimatorRenderers.registerSkull();
        BackBeltToolManager.registerBack(RainimatorItems.RAIN_SWORD.get(), (matrices, left) -> matrices.translate(0, 0, 0.2));
        BackBeltToolManager.registerBack(RainimatorItems.ENDER_BIG_SWORD.get(), (matrices, left) -> {
            matrices.translate(-0.1, -0.2, 0.2);
        });
    }
}
