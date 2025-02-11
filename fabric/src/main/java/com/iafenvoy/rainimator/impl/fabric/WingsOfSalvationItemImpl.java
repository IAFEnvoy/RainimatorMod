package com.iafenvoy.rainimator.impl.fabric;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.Trinket;
import com.iafenvoy.rainimator.data.ManaData;
import com.iafenvoy.rainimator.config.ServerConfig;
import com.iafenvoy.rainimator.impl.ComponentManager;
import com.iafenvoy.rainimator.impl.WingsOfSalvationItem;
import net.fabricmc.fabric.api.entity.event.v1.FabricElytraItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

@SuppressWarnings("unused")
public class WingsOfSalvationItemImpl extends WingsOfSalvationItem implements Trinket, FabricElytraItem {
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
    public void onEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (entity instanceof PlayerEntity player && ServerConfig.getInstance().enableWingsCreativeFly && !player.isCreative()) {
            player.getAbilities().allowFlying = true;
            player.sendAbilitiesUpdate();
        }
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (entity instanceof PlayerEntity player && ServerConfig.getInstance().enableWingsCreativeFly && !player.isCreative()) {
            player.getAbilities().allowFlying = false;
            player.getAbilities().flying = false;
            player.sendAbilitiesUpdate();
        }
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
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

    @Override
    public boolean useCustomElytra(LivingEntity entity, ItemStack chestStack, boolean tickElytra) {
        return true;
    }
}
