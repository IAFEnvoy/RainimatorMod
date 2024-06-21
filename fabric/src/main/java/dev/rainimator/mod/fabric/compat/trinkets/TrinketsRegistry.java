package dev.rainimator.mod.fabric.compat.trinkets;

import dev.emi.trinkets.api.Trinket;
import dev.emi.trinkets.api.TrinketsApi;
import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import dev.rainimator.mod.fabric.compat.trinkets.renderer.BackItemRenderer;
import dev.rainimator.mod.fabric.compat.trinkets.renderer.WingsOfSalvationRenderer;
import dev.rainimator.mod.fabric.compat.trinkets.renderer.armor.*;
import dev.rainimator.mod.registry.RainimatorItems;
import net.minecraft.item.Items;

public class TrinketsRegistry {
    public static void registerClient() {
        TrinketRendererRegistry.registerRenderer(Items.WOODEN_SWORD, new BackItemRenderer());
        TrinketRendererRegistry.registerRenderer(Items.STONE_SWORD, new BackItemRenderer());
        TrinketRendererRegistry.registerRenderer(Items.IRON_SWORD, new BackItemRenderer());
        TrinketRendererRegistry.registerRenderer(Items.GOLDEN_SWORD, new BackItemRenderer());
        TrinketRendererRegistry.registerRenderer(Items.DIAMOND_SWORD, new BackItemRenderer());
        TrinketRendererRegistry.registerRenderer(Items.NETHERITE_SWORD, new BackItemRenderer());
        TrinketRendererRegistry.registerRenderer(RainimatorItems.ENDER_BIG_SWORD.get(), new BackItemRenderer());
        TrinketRendererRegistry.registerRenderer(RainimatorItems.NETHER_SPEAR.get(), new BackItemRenderer());
        TrinketRendererRegistry.registerRenderer(RainimatorItems.RAIN_SWORD.get(), new BackItemRenderer());
        TrinketRendererRegistry.registerRenderer(RainimatorItems.ABIGAIL_SPEAR.get(), new BackItemRenderer());
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
