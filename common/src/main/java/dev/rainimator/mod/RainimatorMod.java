package dev.rainimator.mod;

import dev.rainimator.mod.registry.*;
import dev.rainimator.mod.util.Timeout;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RainimatorMod {
    public static final String MOD_ID = "rainimator";
    public static final Logger LOGGER = LogManager.getLogger();

    public static void init() {
        Timeout.startTimeout();
        RainimatorBlocks.REGISTRY.register();
        RainimatorItems.REGISTRY.register();
        RainimatorSounds.REGISTRY.register();
    }

    public static void process() {
        RainimatorItemGroups.init();
        RainimatorItems.init();
    }

    public static void initClient() {
        RainimatorModels.registerLayerDefinitions();
    }

    public static void processClient() {
    }
}
