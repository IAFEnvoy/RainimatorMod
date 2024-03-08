package com.rainimator.rainimatormod.registry;

import com.rainimator.rainimatormod.model.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class ModModels {
    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModelPorkshire_king_crown.LAYER_LOCATION, ModelPorkshire_king_crown::createBodyLayer);
        event.registerLayerDefinition(Modelmagic.LAYER_LOCATION, Modelmagic::createBodyLayer);
        event.registerLayerDefinition(ModelCustomModel.LAYER_LOCATION, ModelCustomModel::createBodyLayer);
        event.registerLayerDefinition(Modelking_nomal_crown.LAYER_LOCATION, Modelking_nomal_crown::createBodyLayer);
        event.registerLayerDefinition(Modelnether_crown.LAYER_LOCATION, Modelnether_crown::createBodyLayer);
        event.registerLayerDefinition(Modelenderman.LAYER_LOCATION, Modelenderman::createBodyLayer);
        event.registerLayerDefinition(Modelnether_king_2.LAYER_LOCATION, Modelnether_king_2::createBodyLayer);
    }
}
