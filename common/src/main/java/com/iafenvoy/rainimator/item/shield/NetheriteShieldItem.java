package com.iafenvoy.rainimator.item.shield;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.iafenvoy.rainimator.registry.RainimatorItemGroups;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

import java.util.UUID;

public class NetheriteShieldItem extends ShieldItem {
    protected static final UUID OFF_HAND_MODIFIER = UUID.fromString("9271eeea-5f74-4e12-97b6-7cf3c60ef7a0");
    protected static final UUID MAIN_HAND_MODIFIER = UUID.fromString("7d766720-0695-46c6-b320-44529f3da63f");

    public NetheriteShieldItem() {
        super(new Settings().maxCount(1).maxDamage(2500).rarity(Rarity.UNCOMMON).arch$tab(RainimatorItemGroups.MAIN));
    }

    @Override
    public UseAction getUseAction(ItemStack itemStack) {
        return UseAction.BLOCK;
    }

    @Override
    public TypedActionResult<ItemStack> use(World level, PlayerEntity playerIn, Hand hand) {
        ItemStack itemtack = playerIn.getStackInHand(hand);
        playerIn.setCurrentHand(hand);
        return TypedActionResult.consume(itemtack);
    }

    @Override
    public boolean canRepair(ItemStack itemStack, ItemStack itemStack2) {
        return (itemStack2.isIn(ItemTags.PLANKS) || super.canRepair(itemStack, itemStack2));
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot equipmentSlot) {
        Multimap<EntityAttribute, EntityAttributeModifier> hashMultimap = HashMultimap.create();
        if (equipmentSlot == EquipmentSlot.OFFHAND) {
            hashMultimap.put(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, new EntityAttributeModifier(OFF_HAND_MODIFIER, "Weapon modifier", 0.3D, EntityAttributeModifier.Operation.fromId(0)));
            hashMultimap.put(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(MAIN_HAND_MODIFIER, "Weapon modifier", 6.0D, EntityAttributeModifier.Operation.fromId(0)));
        }
        if (equipmentSlot == EquipmentSlot.MAINHAND)
            hashMultimap.put(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(MAIN_HAND_MODIFIER, "Weapon modifier", 6.0D, EntityAttributeModifier.Operation.fromId(0)));
        return hashMultimap;
    }
}