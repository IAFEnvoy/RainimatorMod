package dev.rainimator.mod.item;

import com.iafenvoy.neptune.object.item.ItemBase;
import dev.rainimator.mod.registry.RainimatorItemGroups;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ZombieHeartItem extends ItemBase {
    public ZombieHeartItem() {
        super(p -> p.food((new FoodComponent.Builder()).hunger(10).saturationModifier(1.0F).alwaysEdible().meat().build()).arch$tab(RainimatorItemGroups.ITEM));
    }

    @Override
    public int getMaxUseTime(ItemStack itemtack) {
        return 36;
    }

    @Override
    public ItemStack finishUsing(ItemStack itemtack, World world, LivingEntity entity) {
        ItemStack retval = super.finishUsing(itemtack, world, entity);
        if (entity instanceof LivingEntity)
            if (!entity.getWorld().isClient) {
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200, 1));
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 200, 1));
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 200, 1));
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 200, 1));
            }
        return retval;
    }
}