package com.rainimator.rainimatormod.armor;

import com.rainimator.rainimatormod.RainimatorMod;
import com.rainimator.rainimatormod.model.ModelKingNormalCrown;
import com.rainimator.rainimatormod.registry.util.ItemBase;
import com.rainimator.rainimatormod.registry.util.ModCreativeTab;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.IItemRenderProperties;

import java.util.Collections;
import java.util.Map;
import java.util.function.Consumer;

public class KingNormalCrownItem extends ItemBase {
    public static final ResourceLocation TEXTURE = new ResourceLocation(RainimatorMod.MOD_ID, "textures/entities/king_nomal_crown.png");
    public KingNormalCrownItem(Item.Properties properties) {
        super(p -> properties);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return RainimatorMod.MOD_ID + ":textures/entities/king_nomal_crown.png";
    }

    public static class Helmet extends KingNormalCrownItem {
        public Helmet() {
            super(ModCreativeTab.createProperty());
        }

        public void initializeClient(Consumer<IItemRenderProperties> consumer) {
            consumer.accept(new IItemRenderProperties() {
                public HumanoidModel getArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                    HumanoidModel armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(), Map.of("head", (new ModelKingNormalCrown(Minecraft.getInstance().getEntityModels().bakeLayer(ModelKingNormalCrown.LAYER_LOCATION))).Head, "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "body", new ModelPart(
                            Collections.emptyList(), Collections.emptyMap()), "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_arm", new ModelPart(
                            Collections.emptyList(), Collections.emptyMap()), "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_leg", new ModelPart(
                            Collections.emptyList(), Collections.emptyMap()))));
                    armorModel.crouching = living.isShiftKeyDown();
                    armorModel.riding = defaultModel.riding;
                    armorModel.young = living.isBaby();
                    return armorModel;
                }
            });
        }
    }

//    class null implements
//
//    IItemRenderProperties {
//        public HumanoidModel getArmorModel (LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel
//        defaultModel){
//            HumanoidModel armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(), Map.of("head", (new Modelking_nomal_crown(Minecraft.getInstance().getEntityModels().bakeLayer(Modelking_nomal_crown.LAYER_LOCATION))).Head, "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "body", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
//            armorModel.crouching = living.isShiftKeyDown();
//            armorModel.riding = defaultModel.riding;
//            armorModel.young = living.isBaby();
//            return armorModel;
//        }
//    }
}

