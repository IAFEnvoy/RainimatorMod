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

public class PatrickArmorItem extends ArmorWithTickItem {
    public PatrickArmorItem(Type slot) {
        super(ArmorMaterialUtil.of("patrick_armor", new int[]{13, 15, 16, 11}, 25, new int[]{3, 8, 12, 5}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 3.0F, 0.0F, RainimatorItems.SUPER_SAPPHIRE::get), slot, new Settings().arch$tab(RainimatorItemGroups.MAIN));
    }

    @Override
    public void onArmorTick(World world, PlayerEntity entity) {
        if (!entity.getWorld().isClient && ServerConfig.getInstance().enableArmorEffect &&
                entity.getEquippedStack(EquipmentSlot.HEAD).getItem() == RainimatorItems.PATRICK_HELMET.get() &&
                entity.getEquippedStack(EquipmentSlot.CHEST).getItem() == RainimatorItems.PATRICK_CHESTPLATE.get() &&
                entity.getEquippedStack(EquipmentSlot.LEGS).getItem() == RainimatorItems.PATRICK_LEGGINGS.get() &&
                entity.getEquippedStack(EquipmentSlot.FEET).getItem() == RainimatorItems.PATRICK_BOOTS.get()) {
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 80, 1));
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 80, 1));
        }
    }
}
