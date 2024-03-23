package com.rainimator.rainimatormod.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.rainimator.rainimatormod.registry.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;
import top.theillusivec4.curios.api.SlotTypePreset;

import java.util.HashMap;
import java.util.Optional;
import java.util.function.Consumer;

public class BackItemLayer {
    private static final HashMap<Item, Consumer<PoseStack>> specialItemPose = new HashMap<>();

    public static void render(LivingEntity entity, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        if (specialItemPose.size() == 0) initPoseConsumers();
        if (entity.isSpectator() || entity.isInvisible() || !entity.isAlive()) return;
        Optional<SlotResult> result = CuriosApi.getCuriosHelper().findCurio(entity, SlotTypePreset.BACK.getIdentifier(), 0);
        if (result.isEmpty()) return;
        poseStack.pushPose();
        poseStack.mulPose(Vector3f.YN.rotationDegrees(entity.yBodyRot));
        poseStack.mulPose(Vector3f.ZP.rotationDegrees(180));
        ItemStack stack = result.get().stack();
        if (specialItemPose.containsKey(stack.getItem())) specialItemPose.get(stack.getItem()).accept(poseStack);
        poseStack.translate(0, -2, -0.6);
        Minecraft.getInstance().getItemInHandRenderer().renderItem(entity, stack, ItemTransforms.TransformType.HEAD, false, poseStack, bufferSource, packedLight);
        poseStack.popPose();
    }

    public static void initPoseConsumers() {
        specialItemPose.put(ModItems.ENDER_BIG_SWORD.get(), poseStack -> {
            poseStack.translate(-0.7, -0.3, 0.15);
            poseStack.scale(0.6F, 0.6F, 0.6F);
            poseStack.mulPose(Vector3f.ZP.rotationDegrees(45));
        });
        specialItemPose.put(ModItems.RAIN_SWORD.get(), poseStack -> {
            poseStack.translate(-0.7, -0.3, 0.3);
            poseStack.scale(0.6F, 0.6F, 0.6F);
            poseStack.mulPose(Vector3f.ZP.rotationDegrees(45));
        });
    }
}
