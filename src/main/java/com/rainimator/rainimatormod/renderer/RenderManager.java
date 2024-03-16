package com.rainimator.rainimatormod.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class RenderManager {
    @SubscribeEvent
    public static void render(RenderPlayerEvent event) {
        LivingEntity entity = event.getEntityLiving();
        if (entity.isSpectator() || entity.isInvisible() || !entity.isAlive()) return;
        PoseStack poseStack = event.getPoseStack();
        MultiBufferSource bufferSource = event.getMultiBufferSource();
        int packedLight = event.getPackedLight();

        BackItemLayer.render(entity, poseStack, bufferSource, packedLight);
    }
}
