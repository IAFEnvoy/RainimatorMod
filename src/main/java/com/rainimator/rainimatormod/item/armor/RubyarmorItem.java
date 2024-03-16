package com.rainimator.rainimatormod.item.armor;

import com.rainimator.rainimatormod.RainimatorMod;
import com.rainimator.rainimatormod.registry.ModItems;
import com.rainimator.rainimatormod.registry.util.ModCreativeTab;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
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


public class RubyarmorItem extends ArmorItem {
    public RubyarmorItem(EquipmentSlot slot, Item.Properties properties) {
        super(new ArmorMaterial() {
            @Override
            public int getDurabilityForSlot(@NotNull EquipmentSlot slot) {
                return new int[]{13, 15, 16, 11}[slot.getIndex()] * 40;
            }

            @Override
            public int getDefenseForSlot(@NotNull EquipmentSlot slot) {
                return new int[]{5, 10, 12, 6}[slot.getIndex()];
            }

            @Override
            public int getEnchantmentValue() {
                return 25;
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
                return "rubyarmor";
            }

            @Override
            public float getToughness() {
                return 2.5F;
            }

            @Override
            public float getKnockbackResistance() {
                return 0.0F;
            }
        }, slot, properties);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return RainimatorMod.MOD_ID + ":textures/models/armor/ruby__layer_1.png";
    }


    public void onArmorTick(ItemStack itemstack, Level world, Player entity) {
        if (entity == null)
            return;
        if (((LivingEntity) entity).getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.RUBYARMOR_HELMET.get())
            if (((LivingEntity) entity).getItemBySlot(EquipmentSlot.CHEST).getItem() == ModItems.RUBYARMOR_CHESTPLATE.get())
                if (((LivingEntity) entity).getItemBySlot(EquipmentSlot.LEGS).getItem() == ModItems.RUBYARMOR_LEGGINGS.get())
                    if (((LivingEntity) entity).getItemBySlot(EquipmentSlot.FEET).getItem() == ModItems.RUBYARMOR_BOOTS.get())
                        if (!entity.level.isClientSide()) {
                            entity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 80, 0));
                            entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 80, 1));
                        }
    }

    public static class Helmet extends RubyarmorItem {
        public Helmet() {
            super(EquipmentSlot.HEAD, ModCreativeTab.createProperty());
        }
    }

    public static class Chestplate extends RubyarmorItem {
        public Chestplate() {
            super(EquipmentSlot.CHEST, ModCreativeTab.createProperty());
        }
    }

    public static class Leggings extends RubyarmorItem {
        public Leggings() {
            super(EquipmentSlot.LEGS, ModCreativeTab.createProperty());
        }
    }

    public static class Boots extends RubyarmorItem {
        public Boots() {
            super(EquipmentSlot.FEET, ModCreativeTab.createProperty());
        }
    }
}
