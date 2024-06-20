package dev.rainimator.mod.item.shield;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import dev.rainimator.mod.registry.RainimatorItemGroups;
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

public class BlueDiamondShieldItem extends ShieldItem {
    protected static final UUID OFF_HAND_MODIFIER = UUID.fromString("9271eeea-5f74-4e12-97b6-7cf3c60ef7a0");
    protected static final UUID MAIN_HAND_MODIFIER = UUID.fromString("7d766720-0695-46c6-b320-44529f3da63f");

    public BlueDiamondShieldItem() {
        super(new Settings().maxCount(1).maxDamage(1000).rarity(Rarity.RARE).arch$tab(RainimatorItemGroups.ITEM));
    }

    @Override
    public UseAction getUseAction(ItemStack p_40678_) {
        return UseAction.BLOCK;
    }


    @Override
    public TypedActionResult<ItemStack> use(World p_77659_1_, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemtack = playerIn.getStackInHand(handIn);
        playerIn.setCurrentHand(handIn);
        return TypedActionResult.consume(itemtack);
    }

    @Override
    public boolean canRepair(ItemStack p_43091_, ItemStack p_43092_) {
        return (p_43092_.isIn(ItemTags.PLANKS) || super.canRepair(p_43091_, p_43092_));
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot equipmentSlot) {
        Multimap<EntityAttribute, EntityAttributeModifier> hashMultimap = HashMultimap.create();
        if (equipmentSlot == EquipmentSlot.OFFHAND) {
            hashMultimap.put(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, new EntityAttributeModifier(OFF_HAND_MODIFIER, "Weapon modifier", 0.5D, EntityAttributeModifier.Operation.fromId(0)));
            hashMultimap.put(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(MAIN_HAND_MODIFIER, "Weapon modifier", 10.0D, EntityAttributeModifier.Operation.fromId(0)));
        }
        if (equipmentSlot == EquipmentSlot.MAINHAND)
            hashMultimap.put(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(MAIN_HAND_MODIFIER, "Weapon modifier", 10.0D, EntityAttributeModifier.Operation.fromId(0)));
        return hashMultimap;
    }
}
