package dev.rainimator.mod.renderer;

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
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3f;

@Environment(EnvType.CLIENT)
public class WingsOfSalvationRenderer<T extends LivingEntity, M extends EntityModel<T>> extends FeatureRenderer<T, M> {
    private static final Identifier WINGS_LOCATION = new Identifier(RainimatorMod.MOD_ID, "textures/wings/tech_wings.png");
    private static final Identifier WINGS_LOCATION2 = new Identifier(RainimatorMod.MOD_ID, "textures/wings/tech_wings_2.png");
    private final EntityModel<T> contextModel;

    public WingsOfSalvationRenderer(FeatureRendererContext<T, M> context, EntityModel<T> contextModel) {
        super(context);
        this.contextModel = contextModel;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        ItemStack stack = entity.getEquippedStack(EquipmentSlot.CHEST);
        if (entity instanceof AbstractClientPlayerEntity && stack.getItem() == RainimatorItems.WINGS_OF_SALVATION.get()) {
            AbstractClientPlayerEntity player = (AbstractClientPlayerEntity) entity;
            WingsOfSalvationModel<AbstractClientPlayerEntity> wingModel = new WingsOfSalvationModel<>();
            PlayerEntityModel<AbstractClientPlayerEntity> model = (PlayerEntityModel<AbstractClientPlayerEntity>) this.contextModel;
            matrices.push();
            matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(180));
            matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(180));
            matrices.scale(2, 2, 2);
            IArmorRenderHelper.translateToChest(matrices, model, player);
            if (player.isInSneakingPose()) {
                matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(180));
                matrices.multiply(Vec3f.POSITIVE_X.getRadialQuaternion(model.body.pitch * 2));
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
