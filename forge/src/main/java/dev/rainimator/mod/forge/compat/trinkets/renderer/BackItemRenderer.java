package dev.rainimator.mod.forge.compat.trinkets.renderer;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.client.TrinketRenderer;
import dev.rainimator.mod.registry.RainimatorItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RotationAxis;

import java.util.HashMap;
import java.util.function.Consumer;

@Environment(EnvType.CLIENT)
public class BackItemRenderer implements TrinketRenderer {
    private static final HashMap<Item, Consumer<MatrixStack>> specialItemPose = new HashMap<>();

    public static void initPoseConsumers() {
        specialItemPose.put(RainimatorItems.ENDER_BIG_SWORD.get(), poseStack -> {
            poseStack.translate(-0.4, 0.4, 0.25);
            poseStack.scale(0.6F, 0.6F, 0.6F);
            poseStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(45));
        });
        specialItemPose.put(RainimatorItems.RAIN_SWORD.get(), poseStack -> {
            poseStack.translate(-0.8, 0.8, 0.6);
            poseStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(45));
        });
    }

    @Override
    public void render(ItemStack stack, SlotReference slotReference, EntityModel<? extends LivingEntity> contextModel, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, LivingEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (specialItemPose.size() == 0) initPoseConsumers();
        matrices.push();
//        matrices.multiply(RotationAxis.NEGATIVE_Y.rotationDegrees(entity.bodyYaw));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(-90));
        if (specialItemPose.containsKey(stack.getItem())) specialItemPose.get(stack.getItem()).accept(matrices);
        matrices.translate(-0.25, -0.85, -0.25);
        MinecraftClient.getInstance().getItemRenderer().renderItem(entity, stack, ModelTransformationMode.HEAD, false, matrices, vertexConsumers, entity.getWorld(), light, 0, 0);
        matrices.pop();
    }
}
