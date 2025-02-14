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
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class RubyArmorItem extends ArmorWithTickItem {
    public RubyArmorItem(Type slot) {
        super(ArmorMaterialUtil.of("ruby_armor", new int[]{13, 15, 16, 11}, 40, new int[]{5, 10, 12, 6}, 25, Registries.SOUND_EVENT.get(Identifier.tryParse("item.armor.equip_diamond")), 2.5F, 0.0F, RainimatorItems.RUBY::get), slot, new Settings().arch$tab(RainimatorItemGroups.ITEM));
    }

    @Override
    public void onArmorTick(World world, PlayerEntity entity) {
        if (!entity.getWorld().isClient && ServerConfig.getInstance().enableArmorEffect &&
                entity.getEquippedStack(EquipmentSlot.HEAD).getItem() == RainimatorItems.RUBY_HELMET.get() &&
                entity.getEquippedStack(EquipmentSlot.CHEST).getItem() == RainimatorItems.RUBY_CHESTPLATE.get() &&
                entity.getEquippedStack(EquipmentSlot.LEGS).getItem() == RainimatorItems.RUBY_LEGGINGS.get() &&
                entity.getEquippedStack(EquipmentSlot.FEET).getItem() == RainimatorItems.RUBY_BOOTS.get()) {
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 80, 1));
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 80, 1));
        }
    }
}
