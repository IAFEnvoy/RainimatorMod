package com.rainimator.rainimatormod.registry;

import com.rainimator.rainimatormod.renderer.model.*;
import com.rainimator.rainimatormod.renderer.model.wing.WingsOfSalvationModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class ModModels {
    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModelPorkshireKingCrown.LAYER_LOCATION, ModelPorkshireKingCrown::createBodyLayer);
        event.registerLayerDefinition(ModelMagic.LAYER_LOCATION, ModelMagic::createBodyLayer);
        event.registerLayerDefinition(ModelCustomModel.LAYER_LOCATION, ModelCustomModel::createBodyLayer);
        event.registerLayerDefinition(ModelKingNormalCrown.LAYER_LOCATION, ModelKingNormalCrown::createBodyLayer);
        event.registerLayerDefinition(ModelNetherCrown.LAYER_LOCATION, ModelNetherCrown::createBodyLayer);
        event.registerLayerDefinition(ModelEnderman.LAYER_LOCATION, ModelEnderman::createBodyLayer);
        event.registerLayerDefinition(ModelNetherKing2.LAYER_LOCATION, ModelNetherKing2::createBodyLayer);
        event.registerLayerDefinition(WingsOfSalvationModel.LAYER_LOCATION, WingsOfSalvationModel::createLayer);
    }
}
