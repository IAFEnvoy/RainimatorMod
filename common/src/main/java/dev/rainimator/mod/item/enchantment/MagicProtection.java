package dev.rainimator.mod.item.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageSource;

public class MagicProtection extends Enchantment {
    public MagicProtection() {
        super(Rarity.RARE, EnchantmentTarget.ARMOR, EquipmentSlot.values());
    }

    public int getMaxLevel() {
        return 4;
    }

    public int getProtectionAmount(int level, DamageSource source) {
        return level * 2;
    }
}
