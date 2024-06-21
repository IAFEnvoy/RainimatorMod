package dev.rainimator.mod.fabric.compat.trinkets.renderer.armor;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.client.TrinketRenderer;
import dev.rainimator.mod.renderer.model.ModelPorkshireKingCrown;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.Collections;
import java.util.Map;

@Environment(EnvType.CLIENT)
public class PorkshireKingCrownRenderer implements TrinketRenderer {
    private BipedEntityModel<LivingEntity> getArmorModel(LivingEntity living) {
        BipedEntityModel<LivingEntity> armorModel = new BipedEntityModel<>(
                new ModelPart(Collections.emptyList(), Map.of("head", (new ModelPorkshireKingCrown<>(MinecraftClient.getInstance().getEntityModelLoader().getModelPart(ModelPorkshireKingCrown.LAYER_LOCATION))).Head, "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "body", new ModelPart(
                        Collections.emptyList(), Collections.emptyMap()), "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_arm", new ModelPart(
                        Collections.emptyList(), Collections.emptyMap()), "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_leg", new ModelPart(
                        Collections.emptyList(), Collections.emptyMap()))));
        armorModel.sneaking = living.isSneaking();
        armorModel.riding = living.hasVehicle();
        armorModel.child = living.isBaby();
        return armorModel;
    }

    private Identifier getTexture() {
        return Identifier.tryParse("textures/models/armor/porkshire_king_crown.png");
    }

    @Override
    public void render(ItemStack stack, SlotReference slotReference, EntityModel<? extends LivingEntity> contextModel, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, LivingEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        BipedEntityModel<LivingEntity> model = this.getArmorModel(entity);
        model.head.pitch = headPitch * 0.01745329f;
        model.head.yaw = headYaw * 0.01745329f;
        ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, this.getArmorModel(entity), this.getTexture());
    }
}
