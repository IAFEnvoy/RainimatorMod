package dev.rainimator.mod.fabric.compat.trinkets;

import dev.emi.trinkets.api.Trinket;
import dev.emi.trinkets.api.TrinketsApi;
import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import dev.rainimator.mod.fabric.compat.trinkets.renderer.WingsOfSalvationRenderer;
import dev.rainimator.mod.fabric.compat.trinkets.renderer.armor.*;
import dev.rainimator.mod.registry.RainimatorItems;

public class TrinketsRegistry {
    public static void registerClient() {
        TrinketRendererRegistry.registerRenderer(RainimatorItems.WINGS_OF_SALVATION.get(), new WingsOfSalvationRenderer());

        TrinketRendererRegistry.registerRenderer(RainimatorItems.KING_NORMAL_CROWN.get(), new KingNormalCrownRenderer());
        TrinketRendererRegistry.registerRenderer(RainimatorItems.GLUTTON_HELMET.get(), new GluttonArmorHelmetRenderer());
        TrinketRendererRegistry.registerRenderer(RainimatorItems.MAGIC_HAT.get(), new MagicHatRenderer());
        TrinketRendererRegistry.registerRenderer(RainimatorItems.NETHER_THE_CROWN.get(), new NetherTheCrownRenderer());
        TrinketRendererRegistry.registerRenderer(RainimatorItems.PIGLIN_KING_CROWN.get(), new PiglinKingCrownRenderer());
        TrinketRendererRegistry.registerRenderer(RainimatorItems.PORKSHIRE_KING_CROWN.get(), new PorkshireKingCrownRenderer());
    }

    public static void registerCommon() {
        TrinketsApi.registerTrinket(RainimatorItems.WINGS_OF_SALVATION.get(), (Trinket) RainimatorItems.WINGS_OF_SALVATION.get());
    }
}
