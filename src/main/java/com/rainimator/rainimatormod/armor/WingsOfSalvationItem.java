package com.rainimator.rainimatormod.armor;

import com.rainimator.rainimatormod.RainimatorMod;
import com.rainimator.rainimatormod.registry.util.ModCreativeTab;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class WingsOfSalvationItem extends ArmorItem {
    public WingsOfSalvationItem(EquipmentSlot slot, Properties properties) {
        super(new ArmorMaterial() {
            @Override
            public int getDurabilityForSlot(@NotNull EquipmentSlot slot) {
                return new int[]{13, 15, 16, 11}[slot.getIndex()] * 15;
            }

            @Override
            public int getDefenseForSlot(@NotNull EquipmentSlot slot) {
                return new int[]{0, 0, 0, 4}[slot.getIndex()];
            }

            @Override
            public int getEnchantmentValue() {
                return 9;
            }

            @Override
            public @NotNull SoundEvent getEquipSound() {
                return Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("")));
            }

            @Override
            public @NotNull Ingredient getRepairIngredient() {
                return Ingredient.of();
            }

            @Override
            public @NotNull String getName() {
                return "wings_of_salvation";
            }

            @Override
            public float getToughness() {
                return 0.0F;
            }

            @Override
            public float getKnockbackResistance() {
                return 0.0F;
            }
        }, slot, properties);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return RainimatorMod.MOD_ID + ":textures/entities/wings_of_salvation.png";
    }

    public static class Chestplate extends WingsOfSalvationItem {
        public Chestplate() {
            super(EquipmentSlot.CHEST, ModCreativeTab.createProperty());
        }
    }
}
