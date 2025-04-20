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

public class SugiliteArmorItem extends ArmorWithTickItem {
    public SugiliteArmorItem(Type slot) {
        super(ArmorMaterialUtil.of("topaz_armor", new int[]{13, 15, 16, 11}, 40, new int[]{5, 10, 12, 6}, 25, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.5F, 0.0F, RainimatorItems.SUGILITE::get), slot, new Settings().arch$tab(RainimatorItemGroups.ITEM));
    }

    @Override
    public void onArmorTick(World world, PlayerEntity entity) {
        if (!entity.getWorld().isClient && ServerConfig.getInstance().enableArmorEffect &&
                entity.getEquippedStack(EquipmentSlot.HEAD).isOf(RainimatorItems.SUGILITE_HELMET.get()) &&
                entity.getEquippedStack(EquipmentSlot.CHEST).isOf(RainimatorItems.SUGILITE_CHESTPLATE.get()) &&
                entity.getEquippedStack(EquipmentSlot.LEGS).isOf(RainimatorItems.SUGILITE_LEGGINGS.get()) &&
                entity.getEquippedStack(EquipmentSlot.FEET).isOf(RainimatorItems.SUGILITE_BOOTS.get())) {
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 80, 0));
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 80, 1));
        }
    }
}
