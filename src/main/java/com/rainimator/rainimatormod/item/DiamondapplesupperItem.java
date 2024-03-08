package com.rainimator.rainimatormod.item;

import com.rainimator.rainimatormod.registry.util.FoilItemBase;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class DiamondapplesupperItem extends FoilItemBase {
    public DiamondapplesupperItem() {
        super(p -> p.stacksTo(16).rarity(Rarity.EPIC).food((new FoodProperties.Builder()).nutrition(5).saturationMod(5.0F).alwaysEat().build()));
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack itemstack, @NotNull Level world, @NotNull LivingEntity entity) {
        ItemStack retval = super.finishUsingItem(itemstack, world, entity);
        if (entity instanceof LivingEntity)
            if (!entity.level.isClientSide()) {
                entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 600, 2));
                entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 600, 2));
                entity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 600, 4));
            }
        return retval;
    }
}
