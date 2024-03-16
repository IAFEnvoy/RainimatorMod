package com.rainimator.rainimatormod.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.rainimator.rainimatormod.RainimatorMod;
import com.rainimator.rainimatormod.renderer.model.wing.WingsOfSalvationModel;
import com.rainimator.rainimatormod.registry.ModItems;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class WingsOfSalvationLayer<T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> {
    private static final ResourceLocation WINGS_LOCATION = new ResourceLocation(RainimatorMod.MOD_ID, "textures/entities/wings_of_salvation.png");
    private final WingsOfSalvationModel<T> wingModel;

    public WingsOfSalvationLayer(RenderLayerParent<T, M> renderLayerParent, EntityModelSet modelSet) {
        super(renderLayerParent);
        this.wingModel = new WingsOfSalvationModel<>(modelSet.bakeLayer(WingsOfSalvationModel.LAYER_LOCATION));
    }

    @Override
    public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferSource, int packedLight, T entity, float limbAngle, float limbDistance, float p_116957_, float animationProgress, float headYaw, float headPitch) {
        ItemStack itemstack = entity.getItemBySlot(EquipmentSlot.CHEST);
        if (itemstack.getItem() == ModItems.WINGS_OF_SALVATION.get()) {
            poseStack.pushPose();
            poseStack.translate(0.0D, 0.0D, 0.125D);
            this.getParentModel().copyPropertiesTo(this.wingModel);
            this.wingModel.setupAnim(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
            VertexConsumer vertexconsumer = ItemRenderer.getArmorFoilBuffer(bufferSource, RenderType.armorCutoutNoCull(WINGS_LOCATION), false, itemstack.hasFoil());
            this.wingModel.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
            poseStack.popPose();
        }
    }
}
