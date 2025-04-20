package com.iafenvoy.rainimator.item.armor;

import com.iafenvoy.neptune.object.item.ArmorMaterialUtil;
import com.iafenvoy.neptune.object.item.ArmorWithTickItem;
import com.iafenvoy.rainimator.config.ServerConfig;
import com.iafenvoy.rainimator.registry.RainimatorEffects;
import com.iafenvoy.rainimator.registry.RainimatorItemGroups;
import com.iafenvoy.rainimator.registry.RainimatorItems;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class EnderArmorItem extends ArmorWithTickItem {
    public EnderArmorItem(Type slot) {
        super(ArmorMaterialUtil.of("ender_armor", new int[]{13, 15, 16, 11}, 25, new int[]{6, 9, 15, 0}, 30, SoundEvents.INTENTIONALLY_EMPTY, 5, 0.01f, () -> Blocks.OBSIDIAN, () -> Items.ENDER_EYE), slot, new Settings().arch$tab(RainimatorItemGroups.MAIN));
    }

    @Override
    public void onArmorTick(World world, PlayerEntity entity) {
        if (!entity.getWorld().isClient && ServerConfig.getInstance().enableArmorEffect &&
                entity.getEquippedStack(EquipmentSlot.CHEST).isOf(RainimatorItems.ENDER_CHESTPLATE.get()) &&
                entity.getEquippedStack(EquipmentSlot.LEGS).isOf(RainimatorItems.ENDER_LEGGINGS.get()) &&
                entity.getEquippedStack(EquipmentSlot.FEET).isOf(RainimatorItems.ENDER_BOOTS.get())) {
            entity.addStatusEffect(new StatusEffectInstance(RainimatorEffects.WATCHERS_PROVIDENCE.get(), 80, 1));
        }
    }
}
