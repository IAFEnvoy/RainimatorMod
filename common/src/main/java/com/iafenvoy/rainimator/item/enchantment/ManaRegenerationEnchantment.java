package com.iafenvoy.rainimator.item.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class ManaRegenerationEnchantment extends Enchantment {
    public ManaRegenerationEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.BREAKABLE, EquipmentSlot.values());
    }

    @Override
    public int getMaxLevel() {
        return 10;
    }
}
