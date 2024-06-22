package dev.rainimator.mod;

import com.mojang.logging.LogUtils;
import dev.architectury.networking.NetworkManager;
import dev.rainimator.mod.network.EnderBookActionHandler;
import dev.rainimator.mod.registry.*;
import dev.rainimator.mod.util.Timeout;
import org.slf4j.Logger;

public class RainimatorMod {
    public static final String MOD_ID = "rainimator";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static void init() {
        Timeout.startTimeout();

        RainimatorSkulls.init();

        RainimatorEntities.REGISTRY.register();
        RainimatorBlocks.REGISTRY.register();
        RainimatorEffects.REGISTRY.register();
        RainimatorEnchantments.REGISTRY.register();
        RainimatorItems.REGISTRY.register();
        RainimatorPotions.REGISTRY.register();
        RainimatorItemGroups.REGISTRY.register();
        RainimatorParticles.REGISTRY.register();
        RainimatorScreenHandlers.REGISTRY.register();
        RainimatorSounds.REGISTRY.register();
        RainimatorEntities.registerAttributes();
        RainimatorEntities.addSpawner();
        RainimatorFeatures.init();
        RainimatorParticles.registerParticles();
    }

    public static void process() {
        RainimatorEntities.addLivingEntityToBiomes();
        RainimatorItemGroups.init();
        RainimatorItems.init();
        RainimatorTrades.registerTrades();

//        AbilityManager.init();
        NetworkManager.registerReceiver(NetworkManager.Side.C2S, ModConstants.ENDER_BOOK_SKILL_PACKET_ID, new EnderBookActionHandler());
    }

    public static void initClient() {
        RainimatorEntities.registerEntityRenderers();
        RainimatorModels.registerLayerDefinitions();
    }

    public static void processClient() {
        RainimatorScreenHandlers.registerGui();
        RainimatorSkulls.clientInit();
    }
}
