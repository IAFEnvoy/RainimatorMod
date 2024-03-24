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

public class PatrickArmorItem extends ArmorItem {
    public PatrickArmorItem(EquipmentSlot slot, Item.Properties properties) {
        super(new ArmorMaterial() {
            @Override
            public int getDurabilityForSlot(@NotNull EquipmentSlot slot) {
                return new int[]{13, 15, 16, 11}[slot.getIndex()] * 25;
            }

            @Override
            public int getDefenseForSlot(@NotNull EquipmentSlot slot) {
                return new int[]{3, 8, 12, 5}[slot.getIndex()];
            }

            @Override
            public int getEnchantmentValue() {
                return 20;
            }

            @Override
            public @NotNull SoundEvent getEquipSound() {
                return Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("")));
            }

            @Override
            public @NotNull Ingredient getRepairIngredient() {
                return Ingredient.of(new ItemStack(ModItems.SUPER_SAPPHIRE.get()));
            }

            @Override
            public @NotNull String getName() {
                return "patrick_armor";
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
        return RainimatorMod.MOD_ID + ":textures/models/armor/patrick_armor_layer_1.png";
    }

    @Override
    public void onArmorTick(ItemStack itemstack, Level world, Player entity) {
        if (entity == null) return;
        if (((LivingEntity) entity).getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.PATRICK_HELMET.get())
            if (((LivingEntity) entity).getItemBySlot(EquipmentSlot.CHEST).getItem() == ModItems.PATRICK_CHESTPLATE.get())
                if (((LivingEntity) entity).getItemBySlot(EquipmentSlot.LEGS).getItem() == ModItems.PATRICK_LEGGINGS.get())
                    if (((LivingEntity) entity).getItemBySlot(EquipmentSlot.FEET).getItem() == ModItems.PATRICK_BOOTS.get())
                        if (!entity.level.isClientSide()) {
                            entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 80, 1));
                            entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 80, 1));
                        }
    }

    public static class Helmet extends PatrickArmorItem {
        public Helmet() {
            super(EquipmentSlot.HEAD, (new Item.Properties()).tab(ModCreativeTab.main));
        }
    }

    public static class Chestplate extends PatrickArmorItem {
        public Chestplate() {
            super(EquipmentSlot.CHEST, (new Item.Properties()).tab(ModCreativeTab.main));
        }
    }

    public static class Leggings extends PatrickArmorItem {
        public Leggings() {
            super(EquipmentSlot.LEGS, (new Item.Properties()).tab(ModCreativeTab.main));
        }
    }

    public static class Boots extends PatrickArmorItem {
        public Boots() {
            super(EquipmentSlot.FEET, (new Item.Properties()).tab(ModCreativeTab.main));
        }
    }
}
