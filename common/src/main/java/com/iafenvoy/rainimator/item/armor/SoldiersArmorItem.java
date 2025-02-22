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

public class SoldiersArmorItem extends ArmorWithTickItem {
    public SoldiersArmorItem(Type slot) {
        super(ArmorMaterialUtil.of("soldiers_armor", new int[]{13, 15, 16, 11}, 25, new int[]{4, 8, 9, 4}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F), slot, new Settings().arch$tab(RainimatorItemGroups.MAIN));
    }

    @Override
    public void onArmorTick(World world, PlayerEntity entity) {
        if (!entity.getWorld().isClient && ServerConfig.getInstance().enableArmorEffect &&
                entity.getEquippedStack(EquipmentSlot.HEAD).getItem() == RainimatorItems.SOLDIERS_ARMOR_HELMET.get() &&
                entity.getEquippedStack(EquipmentSlot.CHEST).getItem() == RainimatorItems.SOLDIERS_ARMOR_CHESTPLATE.get() &&
                entity.getEquippedStack(EquipmentSlot.LEGS).getItem() == RainimatorItems.SOLDIERS_ARMOR_LEGGINGS.get() &&
                entity.getEquippedStack(EquipmentSlot.FEET).getItem() == RainimatorItems.SOLDIERS_ARMOR_BOOTS.get())
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 80, 0));
    }
}
