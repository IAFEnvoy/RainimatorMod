package dev.rainimator.mod.registry.util;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.registry.Registry;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.function.Supplier;

public class ArmorMaterialUtil {
    @SafeVarargs
    public static ArmorMaterial of(String name, int[] baseDurability, int durabilityMul, int[] protection, int enchantAbility, SoundEvent equipSound, float toughness, float knockBackResistance, Supplier<? extends ItemConvertible>... repairIngredients) {
        return of(name, Util.make(new EnumMap<>(EquipmentSlot.class), (map) -> {
            map.put(EquipmentSlot.HEAD, baseDurability[3]);
            map.put(EquipmentSlot.CHEST, baseDurability[2]);
            map.put(EquipmentSlot.LEGS, baseDurability[1]);
            map.put(EquipmentSlot.FEET, baseDurability[0]);
        }), durabilityMul, Util.make(new EnumMap<>(EquipmentSlot.class), (map) -> {
            map.put(EquipmentSlot.HEAD, protection[3]);
            map.put(EquipmentSlot.CHEST, protection[2]);
            map.put(EquipmentSlot.LEGS, protection[1]);
            map.put(EquipmentSlot.FEET, protection[0]);
        }), enchantAbility, equipSound, toughness, knockBackResistance, repairIngredients);
    }

    @SafeVarargs
    public static ArmorMaterial of(String name, EnumMap<EquipmentSlot, Integer> baseDurability, int durabilityMul, EnumMap<EquipmentSlot, Integer> protection, int enchantAbility, SoundEvent equipSound, float toughness, float knockBackResistance, Supplier<? extends ItemConvertible>... repairIngredients) {
        if (equipSound == null) equipSound = Registry.SOUND_EVENT.get(Identifier.tryParse(""));
        SoundEvent finalEquipSound = equipSound;
        return new ArmorMaterial() {
            @Override
            public int getDurability(EquipmentSlot slot) {
                return baseDurability.get(slot) * durabilityMul;
            }

            @Override
            public int getProtectionAmount(EquipmentSlot slot) {
                return protection.get(slot);
            }

            @Override
            public int getEnchantability() {
                return enchantAbility;
            }

            @Override
            public SoundEvent getEquipSound() {
                return finalEquipSound;
            }

            @Override
            public Ingredient getRepairIngredient() {
                return Ingredient.ofItems(Arrays.stream(repairIngredients).map(Supplier::get).toArray(ItemConvertible[]::new));
            }

            @Override
            public String getName() {
                return name;
            }

            @Override
            public float getToughness() {
                return toughness;
            }

            @Override
            public float getKnockbackResistance() {
                return knockBackResistance;
            }
        };
    }
}
