package dev.rainimator.mod.forge.compat.trinkets.renderer;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.client.TrinketRenderer;
import dev.rainimator.mod.RainimatorMod;
import dev.rainimator.mod.registry.RainimatorItems;
import dev.rainimator.mod.renderer.model.wing.WingsOfSalvationModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

@Environment(EnvType.CLIENT)
public class WingsOfSalvationRenderer implements TrinketRenderer {
    private static final Identifier WINGS_LOCATION = Identifier.of(RainimatorMod.MOD_ID, "textures/wings/tech_wings.png");
    private static final Identifier WINGS_LOCATION2 = Identifier.of(RainimatorMod.MOD_ID, "textures/wings/tech_wings_2.png");

    @Override
    public void render(ItemStack itemtack, SlotReference slotReference, EntityModel<? extends LivingEntity> contextModel, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, LivingEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (itemtack.getItem() == RainimatorItems.WINGS_OF_SALVATION.get() && entity instanceof AbstractClientPlayerEntity player) {
            WingsOfSalvationModel<AbstractClientPlayerEntity> wingModel = new WingsOfSalvationModel<>(WingsOfSalvationModel.createLayer().createModel());
            PlayerEntityModel<AbstractClientPlayerEntity> model = (PlayerEntityModel<AbstractClientPlayerEntity>) contextModel;
            matrices.push();
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180));
            matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(180));
            matrices.scale(2, 2, 2);
            TrinketRenderer.translateToChest(matrices, model, player);
            if (player.isInSneakingPose()) {
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180));
                matrices.multiply(RotationAxis.POSITIVE_X.rotation(model.body.pitch * 2));
                matrices.translate(0, -1.25, 0.45);
            } else
                matrices.translate(0, -1.2, 0.15);
            if (player.getEquippedStack(EquipmentSlot.CHEST) != ItemStack.EMPTY)
                matrices.translate(0, 0, -0.03);
            wingModel.setAngles(player, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
            VertexConsumer vertexconsumer = ItemRenderer.getArmorGlintConsumer(vertexConsumers, RenderLayer.getArmorCutoutNoCull(WINGS_LOCATION), false, itemtack.hasGlint());
            wingModel.render(matrices, vertexconsumer, light, OverlayTexture.DEFAULT_UV, 0, 1.0F, 1.0F, 0.5F);
            vertexconsumer = ItemRenderer.getArmorGlintConsumer(vertexConsumers, RenderLayer.getArmorCutoutNoCull(WINGS_LOCATION2), false, itemtack.hasGlint());
            wingModel.render(matrices, vertexconsumer, light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
            matrices.pop();
        }
    }
}
