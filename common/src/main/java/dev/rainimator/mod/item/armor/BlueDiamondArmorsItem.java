package dev.rainimator.mod.item.armor;

import dev.rainimator.mod.data.config.ServerConfig;
import dev.rainimator.mod.registry.util.ArmorMaterialUtil;
import dev.rainimator.mod.registry.util.ArmorWithTickItem;
import dev.rainimator.mod.registry.RainimatorItemGroups;
import dev.rainimator.mod.registry.RainimatorItems;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class BlueDiamondArmorsItem extends ArmorWithTickItem {
    public BlueDiamondArmorsItem(Type slot) {
        super(ArmorMaterialUtil.of("blue_diamond_armors", new int[]{13, 15, 16, 11}, 70, new int[]{9, 13, 18, 10}, 40, Registries.SOUND_EVENT.get(Identifier.tryParse("item.armor.equip_diamond")), 5.0F, 0.3F, RainimatorItems.BLUE_DIAMOND), slot, new Settings().fireproof().arch$tab(RainimatorItemGroups.ITEM));
    }

    @Override
    public void onArmorTick(World world, PlayerEntity entity) {
        if (entity == null)
            return;
        if (!entity.getWorld().isClient() && ServerConfig.getInstance().enableArmorEffect &&
                entity.getEquippedStack(EquipmentSlot.HEAD).getItem() == RainimatorItems.BLUE_DIAMOND_HELMET.get() &&
                entity.getEquippedStack(EquipmentSlot.CHEST).getItem() == RainimatorItems.BLUE_DIAMOND_CHESTPLATE.get() &&
                entity.getEquippedStack(EquipmentSlot.LEGS).getItem() == RainimatorItems.BLUE_DIAMOND_LEGGINGS.get() &&
                entity.getEquippedStack(EquipmentSlot.FEET).getItem() == RainimatorItems.BLUE_DIAMOND_BOOTS.get()) {
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 80, 1));
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 80, 2));
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.LUCK, 80, 0));
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 80, 0));
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 80, 4));
        }
    }
}

