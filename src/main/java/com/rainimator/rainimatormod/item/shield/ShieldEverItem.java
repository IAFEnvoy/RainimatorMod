package com.rainimator.rainimatormod.item.shield;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.rainimator.rainimatormod.registry.util.ModCreativeTab;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class ShieldEverItem extends ShieldItem {
    protected static final UUID OFF_HAND_MODIFIER = UUID.fromString("9271eeea-5f74-4e12-97b6-7cf3c60ef7a0");
    protected static final UUID MAIN_HAND_MODIFIER = UUID.fromString("7d766720-0695-46c6-b320-44529f3da63f");

    public ShieldEverItem() {
        super(ModCreativeTab.createProperty().stacksTo(1).durability(2500).rarity(Rarity.UNCOMMON));
    }

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack itemStack) {
        return UseAnim.BLOCK;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player playerIn, @NotNull InteractionHand hand) {
        ItemStack itemstack = playerIn.getItemInHand(hand);
        playerIn.startUsingItem(hand);
        return InteractionResultHolder.consume(itemstack);
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return 2500;
    }

    @Override
    public boolean isValidRepairItem(@NotNull ItemStack itemStack, @NotNull ItemStack itemStack2) {
        return (itemStack2.is(ItemTags.PLANKS) || super.isValidRepairItem(itemStack, itemStack2));
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot equipmentSlot, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> hashMultimap = HashMultimap.create();
        if (equipmentSlot == EquipmentSlot.OFFHAND) {
            hashMultimap.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(OFF_HAND_MODIFIER, "Weapon modifier", 0.3D, AttributeModifier.Operation.fromValue(0)));
            hashMultimap.put(Attributes.ARMOR, new AttributeModifier(MAIN_HAND_MODIFIER, "Weapon modifier", 6.0D, AttributeModifier.Operation.fromValue(0)));
        }
        if (equipmentSlot == EquipmentSlot.MAINHAND)
            hashMultimap.put(Attributes.ARMOR, new AttributeModifier(MAIN_HAND_MODIFIER, "Weapon modifier", 6.0D, AttributeModifier.Operation.fromValue(0)));
        return hashMultimap;
    }
}