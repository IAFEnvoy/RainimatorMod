package com.rainimator.rainimatormod.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.rainimator.rainimatormod.entity.EndSatffEntity;
import com.rainimator.rainimatormod.registry.ModItems;
import com.rainimator.rainimatormod.registry.util.ItemBase;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class EndStaffItem extends ItemBase {
    public EndStaffItem() {
        super(p -> p.durability(10000));
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level world, Player entity, @NotNull InteractionHand hand) {
        entity.startUsingItem(hand);
        return new InteractionResultHolder<>(InteractionResult.SUCCESS, entity.getItemInHand(hand));
    }

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack itemstack) {
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(@NotNull ItemStack itemstack) {
        return 72000;
    }

    @Deprecated
    @Override
    public @NotNull Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(@NotNull EquipmentSlot slot) {
        if (slot == EquipmentSlot.MAINHAND) {
            ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
            builder.putAll(super.getDefaultAttributeModifiers(slot));
            builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Ranged item modifier", 8.0D, AttributeModifier.Operation.ADDITION));
            builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Ranged item modifier", -2.4D, AttributeModifier.Operation.ADDITION));
            return builder.build();
        }
        return super.getDefaultAttributeModifiers(slot);
    }

    @Override
    public void releaseUsing(@NotNull ItemStack itemstack, Level world, @NotNull LivingEntity entityLiving, int timeLeft) {
        if (!world.isClientSide() && entityLiving instanceof ServerPlayer entity) {
            ItemStack stack = ProjectileWeaponItem.getHeldProjectile(entity, e -> (e.getItem() == ModItems.MAGIC_STARD.get()));
            if (stack == ItemStack.EMPTY)
                for (int i = 0; i < (entity.getInventory()).items.size(); i++) {
                    ItemStack teststack = (entity.getInventory()).items.get(i);
                    if (teststack.getItem() == ModItems.MAGIC_STARD.get()) {
                        stack = teststack;
                        break;
                    }
                }
            if ((entity.getAbilities()).instabuild || stack != ItemStack.EMPTY) {
                EndSatffEntity entityarrow = EndSatffEntity.shoot(world, entity, world.getRandom(), 1.2F, 7.0D, 0);
                itemstack.hurtAndBreak(1, (LivingEntity) entity, e -> e.broadcastBreakEvent(entity.getUsedItemHand()));
                if ((entity.getAbilities()).instabuild) {
                    entityarrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                } else if ((new ItemStack(ModItems.MAGIC_STARD.get())).isDamageableItem()) {
                    if (stack.hurt(1, world.getRandom(), entity)) {
                        stack.shrink(1);
                        stack.setDamageValue(0);
                        if (stack.isEmpty())
                            entity.getInventory().removeItem(stack);
                    }
                } else {
                    stack.shrink(1);
                    if (stack.isEmpty())
                        entity.getInventory().removeItem(stack);
                }
                entity.getCooldowns().addCooldown(itemstack.getItem(), 100);
            }
        }
    }
}