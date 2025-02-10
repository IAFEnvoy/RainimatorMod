package dev.rainimator.mod;

import com.mojang.logging.LogUtils;
import dev.rainimator.mod.registry.*;
import org.slf4j.Logger;

public class RainimatorMod {
    public static final String MOD_ID = "rainimator";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static void init() {
        RainimatorSkulls.init();

        RainimatorEntities.REGISTRY.register();
        RainimatorBlocks.REGISTRY.register();
        RainimatorEffects.REGISTRY.register();
        RainimatorEnchantments.REGISTRY.register();
        RainimatorSounds.REGISTRY.register();
        RainimatorItems.REGISTRY.register();
        RainimatorPotions.REGISTRY.register();
        RainimatorItemGroups.REGISTRY.register();
        RainimatorParticles.REGISTRY.register();
        RainimatorEntities.registerAttributes();
        RainimatorEntities.addSpawner();
        RainimatorFeatures.init();
    }

    public static void process() {
        RainimatorEntities.addLivingEntityToBiomes();
        RainimatorItemGroups.init();
        RainimatorItems.init();
        RainimatorTrades.registerTrades();
    }
}
