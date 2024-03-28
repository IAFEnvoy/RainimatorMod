package com.rainimator.rainimatormod.registry;

import com.rainimator.rainimatormod.entity.HerobrineEntity;
import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModSkulls {
    @SubscribeEvent
    public static void createSkullRenderers(EntityRenderersEvent.CreateSkullModels event) {
        SkullBlockRenderer.SKIN_BY_TYPE.put(SkullType.HEROBRINE, HerobrineEntity.texture.getTexture());
        event.registerSkullModel(SkullType.HEROBRINE, new SkullModel(event.getEntityModelSet().bakeLayer(ModelLayers.PLAYER_HEAD)));
    }

    public enum SkullType implements SkullBlock.Type {
        HEROBRINE
    }
}
