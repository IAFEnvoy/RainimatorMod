package com.iafenvoy.rainimator.item;

import com.iafenvoy.neptune.object.item.ItemBase;
import com.iafenvoy.rainimator.config.ServerConfig;
import com.iafenvoy.rainimator.data.ManaData;
import com.iafenvoy.rainimator.impl.ComponentManager;
import com.iafenvoy.rainimator.registry.RainimatorItemGroups;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class NetherNuclearReactorItem extends ItemBase {
    public NetherNuclearReactorItem() {
        super(p -> p.maxDamage(16).fireproof().arch$tab(RainimatorItemGroups.ITEM));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity entity, Hand hand) {
        TypedActionResult<ItemStack> ar = super.use(world, entity, hand);
        ManaData data = ComponentManager.getManaData(entity);
        if (!data.tryUseMana(entity, ServerConfig.getInstance().nether_nuclear_reactor))
            return ar;
        ItemStack itemtack = ar.getValue();
        if (!entity.getWorld().isClient)
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 400, 0));
        entity.getItemCooldownManager().set(itemtack.getItem(), 800);
        return ar;
    }
}
