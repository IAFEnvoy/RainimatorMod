package com.iafenvoy.rainimator.impl.fabric;

import com.iafenvoy.rainimator.impl.WingsOfSalvationItem;
import net.fabricmc.fabric.api.entity.event.v1.FabricElytraItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

@SuppressWarnings("unused")
public class WingsOfSalvationItemImpl extends WingsOfSalvationItem implements FabricElytraItem {
    protected int lastBoostTick = 0;

    public WingsOfSalvationItemImpl() {
        super();
    }

    public static WingsOfSalvationItem create() {
        return new WingsOfSalvationItemImpl();
    }

    @Override
    public boolean useCustomElytra(LivingEntity entity, ItemStack chestStack, boolean tickElytra) {
        return true;
    }
}
