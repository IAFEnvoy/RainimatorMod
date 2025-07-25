package com.iafenvoy.rainimator.item;

import com.iafenvoy.rainimator.registry.RainimatorItemGroups;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class NightmaresItem extends Item {
    public NightmaresItem() {
        super(new Settings().maxDamage(32).fireproof().arch$tab(RainimatorItemGroups.ITEM));
    }

    @Override
    public void inventoryTick(ItemStack itemtack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(itemtack, world, entity, slot, selected);
        if (entity instanceof LivingEntity _entity)
            if (!_entity.getWorld().isClient)
                _entity.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 80, 0));
    }
}