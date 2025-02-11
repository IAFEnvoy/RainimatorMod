package com.iafenvoy.rainimator.impl.forge;

import com.iafenvoy.rainimator.data.ManaData;
import com.iafenvoy.rainimator.config.ServerConfig;
import com.iafenvoy.rainimator.impl.ComponentManager;
import com.iafenvoy.rainimator.impl.WingsOfSalvationItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

@SuppressWarnings("unused")
public class WingsOfSalvationItemImpl extends WingsOfSalvationItem implements ICurioItem {
    protected int lastBoostTick = 0;

    public WingsOfSalvationItemImpl() {
        super();
    }

    protected static void noEnoughEnergy(PlayerEntity player) {
        player.getAbilities().flying = false;
    }

    public static WingsOfSalvationItem create() {
        return new WingsOfSalvationItemImpl();
    }

    @Override
    public boolean canElytraFly(ItemStack stack, LivingEntity entity) {
        return true;
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        ICurioItem.super.onEquip(slotContext, prevStack, stack);
        LivingEntity entity = slotContext.entity();
        if (entity instanceof PlayerEntity player && ServerConfig.getInstance().enableWingsCreativeFly && !player.isCreative()) {
            player.getAbilities().allowFlying = true;
            player.sendAbilitiesUpdate();
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        ICurioItem.super.onUnequip(slotContext, newStack, stack);
        LivingEntity entity = slotContext.entity();
        if (entity instanceof PlayerEntity player && ServerConfig.getInstance().enableWingsCreativeFly && !player.isCreative()) {
            player.getAbilities().allowFlying = false;
            player.getAbilities().flying = false;
            player.sendAbilitiesUpdate();
        }
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        ICurioItem.super.curioTick(slotContext, stack);
        LivingEntity entity = slotContext.entity();
        if (entity instanceof PlayerEntity player) {
            if (ServerConfig.getInstance().enableWingsCreativeFly && !player.isCreative()) {
                if (!player.getAbilities().allowFlying) {
                    player.getAbilities().allowFlying = true;
                    player.sendAbilitiesUpdate();
                }
                ManaData data = ComponentManager.getManaData(player);
                if (player.getAbilities().flying && !data.tryUseMana(player, ServerConfig.getInstance().wings_of_salvation_second / 20))
                    noEnoughEnergy(player);
            }
        }
        this.lastBoostTick--;
        if (this.lastBoostTick < 0) this.lastBoostTick = 0;
    }

    public void keyPress(PlayerEntity player, ItemStack stack) {
        if (player.isFallFlying()) {
            ManaData data = ComponentManager.getManaData(player);
            if (this.lastBoostTick <= 0 && data.tryUseMana(player, ServerConfig.getInstance().wings_of_salvation_boost))
                this.speedUp(player);
        }
    }

    protected void speedUp(PlayerEntity player) {
        this.lastBoostTick = 20;
        FireworkRocketEntity entity = new FireworkRocketEntity(player.getWorld(), new ItemStack(Items.AIR), player);
        player.getWorld().spawnEntity(entity);
    }
}
