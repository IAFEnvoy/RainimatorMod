package com.rainimator.rainimatormod.item.tool;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.rainimator.rainimatormod.registry.util.ItemBase;
import com.rainimator.rainimatormod.registry.util.ModCreativeTab;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;


public class RubyPickaxeItem extends ItemBase {
    public RubyPickaxeItem() {
        super(p -> p.tab(ModCreativeTab.items).durability(200).fireResistant());
    }

    @Override
    public float getDestroySpeed(@NotNull ItemStack itemstack, @NotNull BlockState blockstate) {
        return 1.0F;
    }

    @Override
    public boolean mineBlock(ItemStack itemstack, @NotNull Level world, @NotNull BlockState blockstate, @NotNull BlockPos pos, @NotNull LivingEntity entity) {
        itemstack.hurtAndBreak(1, entity, i -> i.broadcastBreakEvent(EquipmentSlot.MAINHAND));
        return true;
    }

    @Override
    public boolean hurtEnemy(ItemStack itemstack, @NotNull LivingEntity entity, @NotNull LivingEntity sourceentity) {
        itemstack.hurtAndBreak(2, entity, i -> i.broadcastBreakEvent(EquipmentSlot.MAINHAND));
        return true;
    }

    @Override
    public int getEnchantmentValue() {
        return 10;
    }

    @Deprecated
    @Override
    public @NotNull Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(@NotNull EquipmentSlot equipmentSlot) {
        if (equipmentSlot == EquipmentSlot.MAINHAND) {
            ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
            builder.putAll(super.getDefaultAttributeModifiers(equipmentSlot));
            builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", -1.0D, AttributeModifier.Operation.ADDITION));
            builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Tool modifier", -2.5D, AttributeModifier.Operation.ADDITION));
            return builder.build();
        }
        return super.getDefaultAttributeModifiers(equipmentSlot);
    }
}
