package com.rainimator.rainimatormod.armor;

import com.rainimator.rainimatormod.RainimatorMod;
import com.rainimator.rainimatormod.registry.ModItems;
import com.rainimator.rainimatormod.registry.util.ModCreativeTab;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class BlueDiamondArmorsItem extends ArmorItem {
    public BlueDiamondArmorsItem(EquipmentSlot slot, Item.Properties properties) {
        super(new ArmorMaterial() {
            @Override
            public int getDurabilityForSlot(@NotNull EquipmentSlot slot) {
                return new int[]{13, 15, 16, 11}[slot.getIndex()] * 70;
            }

            @Override
            public int getDefenseForSlot(@NotNull EquipmentSlot slot) {
                return new int[]{9, 13, 18, 10}[slot.getIndex()];
            }

            @Override
            public int getEnchantmentValue() {
                return 40;
            }

            @Override
            public @NotNull SoundEvent getEquipSound() {
                return Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("")));
            }

            @Override
            public @NotNull Ingredient getRepairIngredient() {
                return Ingredient.of(new ItemStack(ModItems.BLUEDIAMOND.get()));
            }

            @Override
            public @NotNull String getName() {
                return "blue_diamond_armors";
            }

            @Override
            public float getToughness() {
                return 5.0F;
            }

            @Override
            public float getKnockbackResistance() {
                return 0.3F;
            }
        }, slot, properties);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return RainimatorMod.MOD_ID + ":textures/models/armor/blue_diamond_armor__layer_1.png";
    }

    @Override
    public void onArmorTick(ItemStack itemstack, Level world, Player entity) {
        if (entity == null)
            return;
        if (entity.getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.BLUEDIAMONDARMORS_HELMET.get())
            if (entity.getItemBySlot(EquipmentSlot.CHEST).getItem() == ModItems.BLUEDIAMONDARMORS_CHESTPLATE.get())
                if (entity.getItemBySlot(EquipmentSlot.LEGS).getItem() == ModItems.BLUEDIAMONDARMORS_LEGGINGS.get())
                    if (entity.getItemBySlot(EquipmentSlot.FEET).getItem() == ModItems.BLUEDIAMONDARMORS_BOOTS.get())
                        if (!entity.level.isClientSide()) {
                            entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 80, 1));
                            entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 80, 2));
                            entity.addEffect(new MobEffectInstance(MobEffects.LUCK, 80, 0));
                            entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 80, 0));
                            entity.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 80, 4));
                        }
    }

    public static class Helmet extends BlueDiamondArmorsItem {
        public Helmet() {
            super(EquipmentSlot.HEAD, ModCreativeTab.createProperty().fireResistant());
        }
    }

    public static class Chestplate extends BlueDiamondArmorsItem {
        public Chestplate() {
            super(EquipmentSlot.CHEST, ModCreativeTab.createProperty().fireResistant());
        }
    }

    public static class Leggings extends BlueDiamondArmorsItem {
        public Leggings() {
            super(EquipmentSlot.LEGS, ModCreativeTab.createProperty().fireResistant());
        }
    }

    public static class Boots extends BlueDiamondArmorsItem {
        public Boots() {
            super(EquipmentSlot.FEET, ModCreativeTab.createProperty().fireResistant());
        }
    }
}

