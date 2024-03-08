package com.rainimator.rainimatormod.registry.util;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

public class TierBase {
    public static Tier of(int uses, float speed, float attackDamageBonus, int level, int enchantmentLevel) {
        return of(uses, speed, attackDamageBonus, level, enchantmentLevel, new Item[0]);
    }

    public static Tier of(int uses, float speed, float attackDamageBonus, int level, int enchantmentLevel, Block... repairIngredient) {
        Item[] items = new Item[repairIngredient.length];
        for (int i = 0; i < repairIngredient.length; i++)
            items[i] = repairIngredient[i].asItem();
        return of(uses, speed, attackDamageBonus, level, enchantmentLevel, items);
    }

    @SafeVarargs
    public static Tier of(int uses, float speed, float attackDamageBonus, int level, int enchantmentLevel, RegistryObject<Item>... repairIngredient) {
        Item[] items = new Item[repairIngredient.length];
        for (int i = 0; i < repairIngredient.length; i++)
            items[i] = repairIngredient[i].get();
        return of(uses, speed, attackDamageBonus, level, enchantmentLevel, items);
    }

    public static Tier of(int uses, float speed, float attackDamageBonus, int level, int enchantmentLevel, Item... repairIngredients) {
        return new Tier() {
            @Override
            public int getUses() {
                return uses;
            }

            @Override
            public float getSpeed() {
                return speed;
            }

            @Override
            public float getAttackDamageBonus() {
                return attackDamageBonus;
            }

            @Override
            public int getLevel() {
                return level;
            }

            @Override
            public int getEnchantmentValue() {
                return enchantmentLevel;
            }

            @Override
            public @NotNull Ingredient getRepairIngredient() {
                return Ingredient.of(repairIngredients);
            }
        };
    }
}
