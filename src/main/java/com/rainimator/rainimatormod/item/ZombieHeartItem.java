package com.rainimator.rainimatormod.item;

import com.rainimator.rainimatormod.registry.util.ItemBase;
import com.rainimator.rainimatormod.registry.util.ModCreativeTab;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class ZombieHeartItem extends ItemBase {
    public ZombieHeartItem() {
        super(p -> p.tab(ModCreativeTab.items).food((new FoodProperties.Builder()).nutrition(10).saturationMod(1.0F).alwaysEat().meat().build()));
    }

    @Override
    public int getUseDuration(@NotNull ItemStack itemstack) {
        return 36;
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack itemstack, @NotNull Level world, @NotNull LivingEntity entity) {
        ItemStack retval = super.finishUsingItem(itemstack, world, entity);
        if (entity instanceof LivingEntity)
            if (!entity.level.isClientSide()) {
                entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 200, 1));
                entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 200, 1));
                entity.addEffect(new MobEffectInstance(MobEffects.POISON, 200, 1));
                entity.addEffect(new MobEffectInstance(MobEffects.WITHER, 200, 1));
            }
        return retval;
    }
}