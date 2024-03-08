package com.rainimator.rainimatormod.item;

import com.rainimator.rainimatormod.registry.util.ItemBase;
import com.rainimator.rainimatormod.registry.util.ModCreativeTab;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class LowerBoundNuclearReactorItem extends ItemBase {
    public LowerBoundNuclearReactorItem() {
        super(p -> p.tab(ModCreativeTab.items).durability(16).fireResistant());
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level world, @NotNull Player entity, @NotNull InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
        ItemStack itemstack = ar.getObject();
        if (!entity.level.isClientSide())
            entity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 400, 0));
        entity.getCooldowns().addCooldown(itemstack.getItem(), 800);
        return ar;
    }
}
