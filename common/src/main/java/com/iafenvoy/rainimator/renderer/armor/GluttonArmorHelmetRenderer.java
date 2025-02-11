package com.iafenvoy.rainimator.renderer.armor;

import com.iafenvoy.neptune.render.armor.IArmorRendererBase;
import com.iafenvoy.rainimator.renderer.model.ModelCustomModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.Collections;
import java.util.Map;

@Environment(EnvType.CLIENT)
public class GluttonArmorHelmetRenderer implements IArmorRendererBase<LivingEntity> {
    @Override
    public BipedEntityModel<LivingEntity> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, BipedEntityModel<LivingEntity> bipedEntityModel) {
        BipedEntityModel<LivingEntity> armorModel = new BipedEntityModel<>(new ModelPart(Collections.emptyList(), Map.of(
                "head", new ModelCustomModel<>(MinecraftClient.getInstance().getEntityModelLoader().getModelPart(ModelCustomModel.LAYER_LOCATION)).Head,
                "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                "body", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
                "left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
        armorModel.sneaking = livingEntity.isSneaking();
        armorModel.riding = livingEntity.hasVehicle();
        armorModel.child = livingEntity.isBaby();
        return armorModel;
    }

    @Override
    public Identifier getArmorTexture(ItemStack itemStack, Entity entity, EquipmentSlot equipmentSlot) {
        return Identifier.tryParse("textures/models/armor/glutton_armors_layer_1.png");
    }
}
