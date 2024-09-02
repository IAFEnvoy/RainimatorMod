package dev.rainimator.mod;

import com.iafenvoy.neptune.render.tool.BackBeltToolManager;
import dev.rainimator.mod.registry.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class RainimatorModClient {
    public static void initClient() {
        RainimatorEntities.registerEntityRenderers();
        RainimatorModels.registerLayerDefinitions();
        RainimatorParticles.registerParticles();
    }

    public static void processClient() {
        RainimatorModels.registerArmorRenderers();
        RainimatorScreenHandlers.registerGui();
        RainimatorSkulls.clientInit();
        BackBeltToolManager.registerBack(RainimatorItems.RAIN_SWORD.get(), (matrices, left) -> matrices.translate(0, 0, 0.2));
        BackBeltToolManager.registerBack(RainimatorItems.ENDER_BIG_SWORD.get(), (matrices, left) -> {
            matrices.translate(-0.1, -0.2, 0.2);
        });
    }
}
