package dev.rainimator.mod;

import com.mojang.logging.LogUtils;
import dev.rainimator.mod.network.ServerNetworkHelper;
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
        RainimatorItems.REGISTRY.register();
        RainimatorPotions.REGISTRY.register();
        RainimatorItemGroups.REGISTRY.register();
        RainimatorParticles.REGISTRY.register();
        RainimatorScreenHandlers.REGISTRY.register();
        RainimatorSounds.REGISTRY.register();
        RainimatorEntities.registerAttributes();
        RainimatorEntities.addSpawner();
        RainimatorFeatures.init();
        RainimatorFractions.init();
    }

    public static void process() {
        RainimatorEntities.addLivingEntityToBiomes();
        RainimatorItemGroups.init();
        RainimatorItems.init();
        RainimatorTrades.registerTrades();

//        AbilityManager.init();
        ServerNetworkHelper.register();
    }

    public static void initClient() {
        RainimatorEntities.registerEntityRenderers();
        RainimatorModels.registerLayerDefinitions();
        RainimatorParticles.registerParticles();
    }

    public static void processClient() {
        RainimatorModels.registerArmorRenderers();
        RainimatorScreenHandlers.registerGui();
        RainimatorSkulls.clientInit();
    }

//    public static void checkMods() {
//        List<String> mods = Platform.getMods().stream().map(Mod::getModId).toList();
//        if (mods.contains("epicfight") || mods.contains("epic_fight"))
//            throw new RuntimeException("Incapable mod detected! Please remove Epic Fight to continue.");
//        if (mods.stream().anyMatch(x -> x.contains("annoying")))
//            throw new RuntimeException("Incapable mod detected! Please remove Annoying Villagers to continue.");
//        if (mods.stream().anyMatch(x -> x.contains("opti") && x.contains("village")))
//            throw new RuntimeException("Incapable mod detected! Please remove Opti-Villagers to continue.");
//    }
}
