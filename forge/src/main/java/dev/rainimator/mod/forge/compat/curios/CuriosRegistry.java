package dev.rainimator.mod.forge.compat.curios;

import dev.rainimator.mod.forge.compat.curios.renderer.WingsOfSalvationRenderer;
import dev.rainimator.mod.forge.compat.curios.renderer.armor.*;
import dev.rainimator.mod.registry.RainimatorItems;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class CuriosRegistry {
    public static void registerClient() {
        CuriosRendererRegistry.register(RainimatorItems.WINGS_OF_SALVATION.get(), WingsOfSalvationRenderer::new);

        CuriosRendererRegistry.register(RainimatorItems.KING_NORMAL_CROWN.get(), KingNormalCrownRenderer::new);
        CuriosRendererRegistry.register(RainimatorItems.GLUTTON_HELMET.get(), GluttonArmorHelmetRenderer::new);
        CuriosRendererRegistry.register(RainimatorItems.MAGIC_HAT.get(), MagicHatRenderer::new);
        CuriosRendererRegistry.register(RainimatorItems.NETHER_THE_CROWN.get(), NetherTheCrownRenderer::new);
        CuriosRendererRegistry.register(RainimatorItems.PIGLIN_KING_CROWN.get(), PiglinKingCrownRenderer::new);
        CuriosRendererRegistry.register(RainimatorItems.PORKSHIRE_KING_CROWN.get(), PorkshireKingCrownRenderer::new);
    }

    public static void registerCommon() {
        CuriosApi.registerCurio(RainimatorItems.WINGS_OF_SALVATION.get(), (ICurioItem) RainimatorItems.WINGS_OF_SALVATION.get());
    }
}
