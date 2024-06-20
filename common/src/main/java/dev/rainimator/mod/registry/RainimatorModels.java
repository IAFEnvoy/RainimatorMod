package dev.rainimator.mod.registry;

import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import dev.rainimator.mod.renderer.model.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class RainimatorModels {
    public static void registerLayerDefinitions() {
        EntityModelLayerRegistry.register(ModelPorkshireKingCrown.LAYER_LOCATION, ModelPorkshireKingCrown::createBodyLayer);
        EntityModelLayerRegistry.register(ModelMagic.LAYER_LOCATION, ModelMagic::createBodyLayer);
        EntityModelLayerRegistry.register(ModelCustomModel.LAYER_LOCATION, ModelCustomModel::createBodyLayer);
        EntityModelLayerRegistry.register(ModelKingNormalCrown.LAYER_LOCATION, ModelKingNormalCrown::createBodyLayer);
        EntityModelLayerRegistry.register(ModelNetherCrown.LAYER_LOCATION, ModelNetherCrown::createBodyLayer);
        EntityModelLayerRegistry.register(ModelEnderman.LAYER_LOCATION, ModelEnderman::createBodyLayer);
        EntityModelLayerRegistry.register(ModelNetherKing2.LAYER_LOCATION, ModelNetherKing2::createBodyLayer);
    }
}
