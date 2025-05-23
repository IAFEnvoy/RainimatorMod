package com.iafenvoy.rainimator.item.armor;

import com.iafenvoy.neptune.object.item.ArmorMaterialUtil;
import com.iafenvoy.neptune.object.item.ArmorWithTickItem;
import com.iafenvoy.rainimator.config.ServerConfig;
import com.iafenvoy.rainimator.registry.RainimatorItemGroups;
import com.iafenvoy.rainimator.registry.RainimatorItems;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class BlueDiamondArmorItem extends ArmorWithTickItem {
    public BlueDiamondArmorItem(Type slot) {
        super(ArmorMaterialUtil.of("blue_diamond_armors", new int[]{13, 15, 16, 11}, 70, new int[]{9, 13, 18, 10}, 40, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 5.0F, 0.3F, RainimatorItems.BLUE_DIAMOND::get), slot, new Settings().fireproof().arch$tab(RainimatorItemGroups.ITEM));
    }

    @Override
    public void onArmorTick(World world, PlayerEntity entity) {
        if (!entity.getWorld().isClient && ServerConfig.getInstance().enableArmorEffect &&
                entity.getEquippedStack(EquipmentSlot.HEAD).isOf(RainimatorItems.BLUE_DIAMOND_HELMET.get()) &&
                entity.getEquippedStack(EquipmentSlot.CHEST).isOf(RainimatorItems.BLUE_DIAMOND_CHESTPLATE.get()) &&
                entity.getEquippedStack(EquipmentSlot.LEGS).isOf(RainimatorItems.BLUE_DIAMOND_LEGGINGS.get()) &&
                entity.getEquippedStack(EquipmentSlot.FEET).isOf(RainimatorItems.BLUE_DIAMOND_BOOTS.get())) {
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 80, 1));
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 80, 2));
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.LUCK, 80, 0));
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 80, 0));
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 80, 4));
        }
    }
}

