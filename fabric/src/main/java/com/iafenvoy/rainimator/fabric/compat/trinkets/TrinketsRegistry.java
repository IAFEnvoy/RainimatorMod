package com.iafenvoy.rainimator.fabric.compat.trinkets;

import com.iafenvoy.rainimator.fabric.compat.trinkets.renderer.WingsOfSalvationRenderer;
import com.iafenvoy.rainimator.fabric.compat.trinkets.renderer.armor.*;
import com.iafenvoy.rainimator.registry.RainimatorItems;
import dev.emi.trinkets.api.client.TrinketRendererRegistry;

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
}
