package com.iafenvoy.rainimator.forge.compat.curios.renderer.armor;

import com.iafenvoy.neptune.render.armor.IArmorRenderHelper;
import com.iafenvoy.rainimator.renderer.model.PiglinKingCrownModel;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

import java.util.Collections;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class PiglinKingCrownRenderer implements ICurioRenderer {
    private BipedEntityModel<LivingEntity> getArmorModel(LivingEntity living) {
        BipedEntityModel<LivingEntity> armorModel = new BipedEntityModel<>(new ModelPart(Collections.emptyList(), Map.of("head", (new PiglinKingCrownModel<>(MinecraftClient.getInstance().getEntityModelLoader().getModelPart(PiglinKingCrownModel.LAYER_LOCATION))).head, "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "body", new ModelPart(
                Collections.emptyList(), Collections.emptyMap()), "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_arm", new ModelPart(
                Collections.emptyList(), Collections.emptyMap()), "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_leg", new ModelPart(
                Collections.emptyList(), Collections.emptyMap()))));
        armorModel.sneaking = living.isSneaking();
        armorModel.riding = living.hasVehicle();
        armorModel.child = living.isBaby();
        return armorModel;
    }

    private Identifier getTexture() {
        return Identifier.tryParse("textures/models/armor/piglin_king_crown.png");
    }

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext, MatrixStack matrices, FeatureRendererContext<T, M> renderLayerParent, VertexConsumerProvider vertexConsumers, int light, float limbAngle, float limbDistance, float animationProgress, float ageInTicks, float headYaw, float headPitch) {
        LivingEntity entity = slotContext.entity();
        BipedEntityModel<LivingEntity> model = this.getArmorModel(entity);
        model.head.pitch = headPitch * 0.01745329f;
        model.head.yaw = headYaw * 0.01745329f;
        IArmorRenderHelper.renderPart(matrices, vertexConsumers, light, stack, this.getArmorModel(entity), this.getTexture());
    }
}
