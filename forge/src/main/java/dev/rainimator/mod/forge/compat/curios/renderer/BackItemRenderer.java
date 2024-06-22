package dev.rainimator.mod.forge.compat.curios.renderer;

import dev.rainimator.mod.registry.RainimatorItems;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RotationAxis;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

import java.util.HashMap;
import java.util.function.Consumer;

@OnlyIn(Dist.CLIENT)
public class BackItemRenderer implements ICurioRenderer {
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
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext, MatrixStack matrices, FeatureRendererContext<T, M> renderLayerParent, VertexConsumerProvider vertexConsumers, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (specialItemPose.isEmpty()) initPoseConsumers();
        LivingEntity entity = slotContext.entity();
        matrices.push();
//        matrices.multiply(RotationAxis.NEGATIVE_Y.rotationDegrees(entity.bodyYaw));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(-90));
        if (specialItemPose.containsKey(stack.getItem())) specialItemPose.get(stack.getItem()).accept(matrices);
        matrices.translate(-0.25, -0.85, -0.25);
        MinecraftClient.getInstance().getItemRenderer().renderItem(entity, stack, ModelTransformationMode.HEAD, false, matrices, vertexConsumers, entity.getWorld(), light, 0, 0);
        matrices.pop();
    }
}
