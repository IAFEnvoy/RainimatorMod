package com.rainimator.rainimatormod.renderer;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.apache.commons.lang3.StringUtils;

public class ItemRenderHelper {
    public static void move(PoseStack matrixStack, String[][] moveTypes, int slot, float translation) {
        if (moveTypes[slot][2].equals("-")) {
            translation = -translation;
        }
        switch (moveTypes[slot][1]) {
            case "x" -> matrixStack.translate(translation, 0.0d, 0.0d);
            case "y" -> matrixStack.translate(0.0d, translation, 0.0d);
            case "z" -> matrixStack.translate(0.0d, 0.0d, translation);
        }
    }


    public static boolean hasEquipments(Player player, String[][] moveTypes, int slot) {
        boolean move = moveTypes[slot][0].contains("helm") && !player.getInventory().getArmor(3).isEmpty();
        if (moveTypes[slot][0].contains("chest") && !player.getInventory().getArmor(2).isEmpty()) {
            move = true;
        }
        if (moveTypes[slot][0].contains("legs") && !player.getInventory().getArmor(1).isEmpty()) {
            move = true;
        }
        if (moveTypes[slot][0].contains("boots") && !player.getInventory().getArmor(0).isEmpty()) {
            move = true;
        }
        return move;
    }

    public static void translateToHand(ModelPart part, HumanoidArm arm, PoseStack p_102855_) {
        part.translateAndRotate(p_102855_);
    }

    public static void translateAndRotate(PoseStack p_104300_, float x, float y, float z, float xRot, float yRot, float zRot, float xScale, float yScale, float zScale) {
        p_104300_.translate(x / 16.0f, y / 16.0f, z / 16.0f);
        if (zRot != 0.0f) {
            p_104300_.mulPose(Vector3f.ZP.rotation(zRot));
        }
        if (yRot != 0.0f) {
            p_104300_.mulPose(Vector3f.YP.rotation(yRot));
        }
        if (xRot != 0.0f) {
            p_104300_.mulPose(Vector3f.XP.rotation(xRot));
        }
        if (xScale != 1.0f || yScale != 1.0f || zScale != 1.0f) {
            p_104300_.scale(xScale, yScale, zScale);
        }
    }

    public static void copyModelPartRotation(HumanoidModel<LivingEntity> model, PoseStack ps, int slot, String[][] bodyattachment, float multiplier, float maxRotateChanger) {
        ModelPart part = switch (bodyattachment[slot][1]) {
            case "head" -> model.getHead();
            case "rarm" -> model.rightArm;
            case "larm" -> model.leftArm;
            case "rleg" -> model.rightLeg;
            case "lleg" -> model.leftLeg;
            default -> model.body;
        };
        part.translateAndRotate(ps);
    }

    public static void renderTridentItem(int slot, LivingEntity player, ItemStack itemStack, PoseStack poseStack, MultiBufferSource p_174522_, int p_174523_, ItemTransforms.TransformType ctx) {
        poseStack.pushPose();
        poseStack.mulPose(Vector3f.ZP.rotationDegrees(140.0f));
        poseStack.translate(0.699999988079071d, 0.9d, 0.61d);
        poseStack.scale(0.9f, 0.9f, 0.9f);
        poseStack.translate(-0.44999998807907104d, -1.0700000524520874d, -0.5350000262260437d);
        poseStack.mulPose(Vector3f.YP.rotationDegrees(-60.0f));
        Minecraft.getInstance().getItemInHandRenderer().renderItem(player, itemStack, ctx, false, poseStack, p_174522_, p_174523_);
        poseStack.popPose();
    }

    public static void renderCrossbowItem(int slot, LivingEntity player, ItemStack itemStack, PoseStack poseStack, MultiBufferSource p_174522_, int p_174523_, ItemTransforms.TransformType ctx) {
        poseStack.pushPose();
        poseStack.translate(-0.05d, 0.35d, 0.16d);
        poseStack.mulPose(Vector3f.YP.rotationDegrees(180.0f));
        poseStack.mulPose(Vector3f.ZP.rotationDegrees(270.0f));
        poseStack.scale(0.8f, -0.8f, -0.8f);
        poseStack.translate(0.10000000149011612d, -0.20000000298023224d, 0.0d);
        poseStack.mulPose(Vector3f.YP.rotationDegrees(0.0f));
        poseStack.mulPose(Vector3f.ZP.rotationDegrees(60.0f));
        poseStack.mulPose(Vector3f.XP.rotationDegrees(90.0f));
        Minecraft.getInstance().getItemInHandRenderer().renderItem(player, itemStack, ctx, false, poseStack, p_174522_, p_174523_);
        poseStack.popPose();
    }

    public static void renderBowItem(int slot, LivingEntity player, ItemStack itemStack, PoseStack poseStack, MultiBufferSource p_174522_, int p_174523_, ItemTransforms.TransformType ctx) {
        poseStack.pushPose();
        poseStack.translate(0.0d, 0.35d, 0.16d);
        poseStack.mulPose(Vector3f.YP.rotationDegrees(180.0f));
        poseStack.mulPose(Vector3f.ZP.rotationDegrees(180.0f));
        poseStack.scale(0.8f, -0.8f, -0.8f);
        poseStack.translate(-0.05d, 0.2d, -0.07d);
        poseStack.mulPose(Vector3f.YP.rotationDegrees(97.0f));
        poseStack.mulPose(Vector3f.ZP.rotationDegrees(-6.0f));
        poseStack.mulPose(Vector3f.XP.rotationDegrees(40.0f));
        Minecraft.getInstance().getItemInHandRenderer().renderItem(player, itemStack, ctx, false, poseStack, p_174522_, p_174523_);
        poseStack.popPose();
    }

    public static void renderNormalItem(int slot, LivingEntity player, ItemStack itemStack, PoseStack poseStack, MultiBufferSource p_174522_, int p_174523_, ItemTransforms.TransformType ctx) {
        poseStack.pushPose();
        poseStack.translate(0.0d, 0.35d, 0.16d);
        poseStack.mulPose(Vector3f.YP.rotationDegrees(180.0f));
        poseStack.mulPose(Vector3f.ZP.rotationDegrees(180.0f));
        poseStack.scale(0.8f, -0.8f, -0.8f);
        poseStack.translate(0.10000000149011612d, -0.10000000149011612d, -0.07999999821186066d);
        Minecraft.getInstance().getItemInHandRenderer().renderItem(player, itemStack, ctx, false, poseStack, p_174522_, p_174523_);
        poseStack.popPose();
    }

    public static void renderTieredItem(int slot, LivingEntity player, ItemStack itemStack, PoseStack poseStack, MultiBufferSource p_174522_, int p_174523_, ItemTransforms.TransformType ctx) {
        poseStack.pushPose();
        poseStack.translate(-0.05d, 0.3d, 0.16d);
        poseStack.mulPose(Vector3f.YP.rotationDegrees(180.0f));
        poseStack.mulPose(Vector3f.ZP.rotationDegrees(180.0f));
        poseStack.scale(0.8f, -0.8f, -0.8f);
        poseStack.translate(-0.2d, -0.1d, -0.0d);
        poseStack.mulPose(Vector3f.YP.rotationDegrees(90.0f));
        poseStack.mulPose(Vector3f.XP.rotationDegrees(55.0f));
        Minecraft.getInstance().getItemInHandRenderer().renderItem(player, itemStack, ctx, false, poseStack, p_174522_, p_174523_);
        poseStack.popPose();
    }
}
