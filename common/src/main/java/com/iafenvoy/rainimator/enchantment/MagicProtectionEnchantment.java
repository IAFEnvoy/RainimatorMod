package com.iafenvoy.rainimator.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageSource;

public class MagicProtectionEnchantment extends Enchantment {
    public MagicProtectionEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.ARMOR, EquipmentSlot.values());
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }

    @Override
    public int getProtectionAmount(int level, DamageSource source) {
        return level * 2;
    }
}
