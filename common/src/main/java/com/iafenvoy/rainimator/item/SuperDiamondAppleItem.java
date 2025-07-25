package com.iafenvoy.rainimator.item;

import com.iafenvoy.rainimator.registry.RainimatorItemGroups;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SuperDiamondAppleItem extends Item {
    public SuperDiamondAppleItem() {
        super(new Settings().food(new FoodComponent.Builder().hunger(4).saturationModifier(0.3F).alwaysEdible().build()).arch$tab(RainimatorItemGroups.ITEM));
    }

    @Override
    public ItemStack finishUsing(ItemStack itemtack, World world, LivingEntity entity) {
        ItemStack retval = super.finishUsing(itemtack, world, entity);
        if (!entity.getWorld().isClient) {
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 2400, 4));
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 2400, 1));
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 2400, 1));
        }
        return retval;
    }
}
