package com.iafenvoy.rainimator.forge.compat.curios.renderer;

import com.iafenvoy.neptune.render.armor.IArmorRenderHelper;
import com.iafenvoy.rainimator.RainimatorMod;
import com.iafenvoy.rainimator.registry.RainimatorItems;
import com.iafenvoy.rainimator.renderer.model.wing.WingsOfSalvationModel;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

@OnlyIn(Dist.CLIENT)
public class WingsOfSalvationRenderer implements ICurioRenderer {
    private static final Identifier WINGS_LOCATION = Identifier.of(RainimatorMod.MOD_ID, "textures/wings/tech_wings.png");
    private static final Identifier WINGS_LOCATION2 = Identifier.of(RainimatorMod.MOD_ID, "textures/wings/tech_wings_2.png");

    @SuppressWarnings("unchecked")
    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext, MatrixStack matrices, FeatureRendererContext<T, M> renderLayerParent, VertexConsumerProvider vertexConsumers, int light, float limbAngle, float limbDistance, float animationProgress, float ageInTicks, float headYaw, float headPitch) {
        LivingEntity entity = slotContext.entity();
        if (stack.getItem() == RainimatorItems.WINGS_OF_SALVATION.get() && entity instanceof AbstractClientPlayerEntity player) {
            WingsOfSalvationModel<AbstractClientPlayerEntity> wingModel = new WingsOfSalvationModel<>(WingsOfSalvationModel.createLayer().createModel());
            PlayerEntityModel<AbstractClientPlayerEntity> model = (PlayerEntityModel<AbstractClientPlayerEntity>) renderLayerParent.getModel();
            matrices.push();
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180));
            matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(180));
            matrices.scale(2, 2, 2);
            IArmorRenderHelper.translateToChest(matrices, model, player);
            if (player.isInSneakingPose()) {
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180));
                matrices.multiply(RotationAxis.POSITIVE_X.rotation(model.body.pitch * 2));
                matrices.translate(0, -1.25, 0.45);
            } else
                matrices.translate(0, -1.2, 0.15);
            if (player.getEquippedStack(EquipmentSlot.CHEST) != ItemStack.EMPTY)
                matrices.translate(0, 0, -0.03);
            wingModel.setAngles(player, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
            VertexConsumer vertexconsumer = ItemRenderer.getArmorGlintConsumer(vertexConsumers, RenderLayer.getArmorCutoutNoCull(WINGS_LOCATION), false, stack.hasGlint());
            wingModel.render(matrices, vertexconsumer, light, OverlayTexture.DEFAULT_UV, 0, 1.0F, 1.0F, 0.5F);
            vertexconsumer = ItemRenderer.getArmorGlintConsumer(vertexConsumers, RenderLayer.getArmorCutoutNoCull(WINGS_LOCATION2), false, stack.hasGlint());
            wingModel.render(matrices, vertexconsumer, light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
            matrices.pop();
        }
    }
}
