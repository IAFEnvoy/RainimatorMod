package com.rainimator.rainimatormod.armor;

import com.rainimator.rainimatormod.RainimatorMod;
import com.rainimator.rainimatormod.registry.ModItems;
import com.rainimator.rainimatormod.registry.util.ModCreativeTab;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class RainArmorItem extends ArmorItem {
    public RainArmorItem(EquipmentSlot slot, Item.Properties properties) {
        super(new ArmorMaterial() {
            @Override
            public int getDurabilityForSlot(@NotNull EquipmentSlot slot) {
                return new int[]{13, 15, 16, 11}[slot.getIndex()] * 20;
            }

            @Override
            public int getDefenseForSlot(@NotNull EquipmentSlot slot) {
                return new int[]{0, 0, 20, 0}[slot.getIndex()];
            }

            @Override
            public int getEnchantmentValue() {
                return 30;
            }

            @Override
            public @NotNull SoundEvent getEquipSound() {
                return Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("")));
            }

            @Override
            public @NotNull Ingredient getRepairIngredient() {
                return Ingredient.of(new ItemStack(ModItems.SUPER_SPPARIES.get()), new ItemStack(ModItems.RAINARMOR_CHESTPLATE.get()));
            }

            @Override
            public @NotNull String getName() {
                return "rain_armors";
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
        return RainimatorMod.MOD_ID + ":textures/models/armor/skinseedskin_1697289804855_layer_1.png";
    }

    public static class Chestplate extends RainArmorItem {
        public Chestplate() {
            super(EquipmentSlot.CHEST, ModCreativeTab.createProperty());
        }
    }
}