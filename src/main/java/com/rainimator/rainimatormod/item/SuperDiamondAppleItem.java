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

public class SuperDiamondAppleItem extends ItemBase {
    public SuperDiamondAppleItem() {
        super(p -> p.tab(ModCreativeTab.items).food(new FoodProperties.Builder().nutrition(4).saturationMod(0.3F).alwaysEat().build()));
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack itemstack, @NotNull Level world, @NotNull LivingEntity entity) {
        ItemStack retval = super.finishUsingItem(itemstack, world, entity);
        if (!entity.level.isClientSide()) {
            entity.addEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 2400, 4));
            entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 2400, 1));
            entity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 2400, 1));
        }
        return retval;
    }
}
