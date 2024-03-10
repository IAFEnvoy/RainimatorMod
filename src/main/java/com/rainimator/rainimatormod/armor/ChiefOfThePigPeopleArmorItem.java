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
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ChiefOfThePigPeopleArmorItem extends ArmorItem {
    public ChiefOfThePigPeopleArmorItem(EquipmentSlot slot, Item.Properties properties) {
        super(new ArmorMaterial() {
            @Override
            public int getDurabilityForSlot(@NotNull EquipmentSlot slot) {
                return new int[]{13, 15, 16, 11}[slot.getIndex()] * 25;
            }

            @Override
            public int getDefenseForSlot(@NotNull EquipmentSlot slot) {
                return new int[]{5, 10, 12, 5}[slot.getIndex()];
            }

            @Override
            public int getEnchantmentValue() {
                return 10;
            }

            @Override
            public @NotNull SoundEvent getEquipSound() {
                return Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("")));
            }

            @Override
            public @NotNull Ingredient getRepairIngredient() {
                return Ingredient.of(new ItemStack(Items.NETHERITE_INGOT));
            }

            @Override
            public @NotNull String getName() {
                return "chief_of_the_pig_people_armors";
            }

            @Override
            public float getToughness() {
                return 3.0F;
            }

            @Override
            public float getKnockbackResistance() {
                return 0.0F;
            }
        }, slot, properties);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return RainimatorMod.MOD_ID + ":textures/models/armor/amrom1_layer_1.png";
    }

    @Override
    public void onArmorTick(ItemStack itemstack, Level world, Player entity) {
        if (entity == null)
            return;
        if (entity.getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.CHIEF_OF_THE_PIG_PEOPLE_ARAMOR_HELMET.get())
            if (entity.getItemBySlot(EquipmentSlot.CHEST).getItem() == ModItems.CHIEF_OF_THE_PIG_PEOPLE_ARAMOR_CHESTPLATE.get())
                if (entity.getItemBySlot(EquipmentSlot.LEGS).getItem() == ModItems.CHIEF_OF_THE_PIG_PEOPLE_ARAMOR_LEGGINGS.get())
                    if (entity.getItemBySlot(EquipmentSlot.FEET).getItem() == ModItems.CHIEF_OF_THE_PIG_PEOPLE_ARAMOR_BOOTS.get())
                        if (!entity.level.isClientSide()) {
                            entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 80, 1));
                            entity.addEffect(new MobEffectInstance(MobEffects.LUCK, 80, 1));
                        }
    }

    public static class Helmet extends ChiefOfThePigPeopleArmorItem {
        public Helmet() {
            super(EquipmentSlot.HEAD, ModCreativeTab.createProperty());
        }
    }

    public static class Chestplate extends ChiefOfThePigPeopleArmorItem {
        public Chestplate() {
            super(EquipmentSlot.CHEST, ModCreativeTab.createProperty());
        }
    }

    public static class Leggings extends ChiefOfThePigPeopleArmorItem {
        public Leggings() {
            super(EquipmentSlot.LEGS, ModCreativeTab.createProperty());
        }
    }

    public static class Boots extends ChiefOfThePigPeopleArmorItem {
        public Boots() {
            super(EquipmentSlot.FEET, ModCreativeTab.createProperty());
        }
    }
}
