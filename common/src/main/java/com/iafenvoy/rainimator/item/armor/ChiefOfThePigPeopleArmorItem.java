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
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class ChiefOfThePigPeopleArmorItem extends ArmorWithTickItem {
    public ChiefOfThePigPeopleArmorItem(Type slot) {
        super(ArmorMaterialUtil.of("chief_of_the_pig_people_armors", new int[]{13, 15, 16, 11}, 25, new int[]{5, 10, 12, 5}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 3.0F, 0F, () -> Items.NETHERITE_INGOT), slot, new Settings().arch$tab(RainimatorItemGroups.MAIN));
    }

    @Override
    public void onArmorTick(World world, PlayerEntity entity) {
        if (!entity.getWorld().isClient && ServerConfig.getInstance().enableArmorEffect &&
                entity.getEquippedStack(EquipmentSlot.HEAD).getItem() == RainimatorItems.CHIEF_OF_THE_PIG_PEOPLE_HELMET.get() &&
                entity.getEquippedStack(EquipmentSlot.CHEST).getItem() == RainimatorItems.CHIEF_OF_THE_PIG_PEOPLE_CHESTPLATE.get() &&
                entity.getEquippedStack(EquipmentSlot.LEGS).getItem() == RainimatorItems.CHIEF_OF_THE_PIG_PEOPLE_LEGGINGS.get() &&
                entity.getEquippedStack(EquipmentSlot.FEET).getItem() == RainimatorItems.CHIEF_OF_THE_PIG_PEOPLE_BOOTS.get()) {
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 80, 1));
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.LUCK, 80, 1));
        }
    }
}
