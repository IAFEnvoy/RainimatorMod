package dev.rainimator.mod.data.component;

import dev.rainimator.mod.data.config.ServerConfig;
import dev.rainimator.mod.registry.RainimatorEnchantments;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

import java.util.Map;

public class ManaData {
    public LivingEntity entity;
    private double mana = 0;
    private double restoreSpeed;
    private double maxMana;
    private boolean enabled = true;

    public ManaData(LivingEntity entity) {
        this.entity = entity;
        this.restoreSpeed = ServerConfig.getInstance().baseRestoreSpeed;
        this.maxMana = ServerConfig.getInstance().baseMaxMana;
    }

    public void encode(NbtCompound tag) {
        tag.putDouble("mana", this.mana);
        tag.putDouble("restoreSpeed", this.restoreSpeed);
        tag.putDouble("maxMana", this.maxMana);
        tag.putBoolean("enabled", this.enabled);
    }

    public void decode(NbtCompound tag) {
        this.mana = tag.getDouble("mana");
        this.restoreSpeed = tag.getDouble("restoreSpeed");
        this.maxMana = tag.getDouble("maxMana");
        this.enabled = tag.getBoolean("enabled");
    }

    public double getMana() {
        return this.mana;
    }

    public double getMaxMana() {
        return this.maxMana;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public boolean tryUseMana(double amount) {
        if (this.entity instanceof PlayerEntity player && player.isCreative()) return true;
        if (amount > this.mana) return false;
        this.mana -= amount;
        return true;
    }

    private void measureMaxMana() {
        double base = ServerConfig.getInstance().baseMaxMana, multiple = 1.0, modifier = 0.0;
        Map<EquipmentSlot, ItemStack> upgrade = RainimatorEnchantments.MANA_UPGRADE.get().getEquipment(this.entity);
        for (Map.Entry<EquipmentSlot, ItemStack> entry : upgrade.entrySet())
            multiple += (double) EnchantmentHelper.getLevel(RainimatorEnchantments.MANA_UPGRADE.get(), entry.getValue()) / 10;
        this.maxMana = base * multiple + modifier;
    }

    private void measureRestoreSpeed() {
        double base = ServerConfig.getInstance().baseRestoreSpeed, multiple = 1.0, modifier = 0.0;
        Map<EquipmentSlot, ItemStack> regeneration = RainimatorEnchantments.MANA_REGENERATION.get().getEquipment(this.entity);
        for (Map.Entry<EquipmentSlot, ItemStack> entry : regeneration.entrySet())
            multiple += (double) EnchantmentHelper.getLevel(RainimatorEnchantments.MANA_REGENERATION.get(), entry.getValue()) / 10;
        this.restoreSpeed = base * multiple + modifier;
    }

    public void tick() {
        this.measureMaxMana();
        this.measureRestoreSpeed();
        this.mana += this.restoreSpeed / 20;
        if (this.mana > this.maxMana)
            this.mana = this.maxMana;
    }
}
