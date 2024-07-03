package dev.rainimator.mod;

import dev.rainimator.mod.registry.RainimatorItemGroups;
import dev.rainimator.mod.registry.RainimatorItems;
import dev.rainimator.mod.registry.RainimatorSounds;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RainimatorMod {
    public static final String MOD_ID = "rainimator";
    public static final Logger LOGGER = LogManager.getLogger();

    public static void init() {
        RainimatorItems.REGISTRY.register();
        RainimatorSounds.REGISTRY.register();
    }

    public static void process() {
        RainimatorItemGroups.init();
    }
}
