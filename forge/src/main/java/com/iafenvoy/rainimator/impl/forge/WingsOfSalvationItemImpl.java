package com.iafenvoy.rainimator.impl.forge;

import com.iafenvoy.rainimator.impl.WingsOfSalvationItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

@SuppressWarnings("unused")
public class WingsOfSalvationItemImpl extends WingsOfSalvationItem {
    protected int lastBoostTick = 0;

    public WingsOfSalvationItemImpl() {
        super();
    }

    public static WingsOfSalvationItem create() {
        return new WingsOfSalvationItemImpl();
    }

    @Override
    public boolean canElytraFly(ItemStack stack, LivingEntity entity) {
        return true;
    }
}
