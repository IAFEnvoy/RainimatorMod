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

public class SoldiersArmorItem extends ArmorWithTickItem {
    public SoldiersArmorItem(Type slot) {
        super(ArmorMaterialUtil.of("soldiers_armor", new int[]{13, 15, 16, 11}, 25, new int[]{4, 8, 9, 4}, 10, Registries.SOUND_EVENT.get(Identifier.tryParse("item.armor.equip_diamond")), 2.0F, 0.0F), slot, new Settings().arch$tab(RainimatorItemGroups.MAIN));
    }

    @Override
    public void onArmorTick(World world, PlayerEntity entity) {
        if (entity == null)
            return;
        if (!entity.getWorld().isClient() && ServerConfig.getInstance().enableArmorEffect &&
                entity.getEquippedStack(EquipmentSlot.HEAD).getItem() == RainimatorItems.SOLDIERS_ARMOR_HELMET.get() &&
                entity.getEquippedStack(EquipmentSlot.CHEST).getItem() == RainimatorItems.SOLDIERS_ARMOR_CHESTPLATE.get() &&
                entity.getEquippedStack(EquipmentSlot.LEGS).getItem() == RainimatorItems.SOLDIERS_ARMOR_LEGGINGS.get() &&
                entity.getEquippedStack(EquipmentSlot.FEET).getItem() == RainimatorItems.SOLDIERS_ARMOR_BOOTS.get())
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 80, 0));
    }
}
