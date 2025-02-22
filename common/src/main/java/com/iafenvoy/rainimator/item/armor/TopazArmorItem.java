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

public class TopazArmorItem extends ArmorWithTickItem {
    public TopazArmorItem(Type slot) {
        super(ArmorMaterialUtil.of("topaz_armor", new int[]{13, 15, 16, 11}, 40, new int[]{5, 10, 12, 6}, 25, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.5F, 0.0F, RainimatorItems.TOPAZ::get), slot, new Settings().arch$tab(RainimatorItemGroups.ITEM));
    }

    @Override
    public void onArmorTick(World world, PlayerEntity entity) {
        if (!entity.getWorld().isClient && ServerConfig.getInstance().enableArmorEffect &&
                entity.getEquippedStack(EquipmentSlot.HEAD).getItem() == RainimatorItems.TOPAZ_HELMET.get() &&
                entity.getEquippedStack(EquipmentSlot.CHEST).getItem() == RainimatorItems.TOPAZ_CHESTPLATE.get() &&
                entity.getEquippedStack(EquipmentSlot.LEGS).getItem() == RainimatorItems.TOPAZ_LEGGINGS.get() &&
                entity.getEquippedStack(EquipmentSlot.FEET).getItem() == RainimatorItems.TOPAZ_BOOTS.get()) {
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 80, 0));
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 80, 1));
        }
    }
}
