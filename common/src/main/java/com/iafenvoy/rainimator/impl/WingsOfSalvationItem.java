package com.iafenvoy.rainimator.impl;

import com.iafenvoy.neptune.accessory.Accessory;
import com.iafenvoy.rainimator.config.ServerConfig;
import com.iafenvoy.rainimator.data.ManaData;
import com.iafenvoy.rainimator.registry.RainimatorItemGroups;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Rarity;
import org.apache.commons.lang3.NotImplementedException;

public class WingsOfSalvationItem extends ElytraItem implements Accessory {
    protected int lastBoostTick = 0;

    public WingsOfSalvationItem() {
        super(new Settings().rarity(Rarity.EPIC).maxCount(1).arch$tab(RainimatorItemGroups.MAIN));
    }

    @ExpectPlatform
    public static WingsOfSalvationItem create() {
        throw new NotImplementedException("This method should be replaced by Architectury.");
    }

    @Override
    public void onEquip(ItemStack stack, LivingEntity entity) {
        if (entity instanceof PlayerEntity player && ServerConfig.getInstance().enableWingsCreativeFly && !player.isCreative()) {
            player.getAbilities().allowFlying = true;
            player.sendAbilitiesUpdate();
        }
    }

    @Override
    public void onUnequip(ItemStack stack, LivingEntity entity) {
        if (entity instanceof PlayerEntity player && ServerConfig.getInstance().enableWingsCreativeFly && !player.isCreative()) {
            player.getAbilities().allowFlying = false;
            player.getAbilities().flying = false;
            player.sendAbilitiesUpdate();
        }
    }

    @Override
    public void tick(ItemStack stack, LivingEntity entity) {
        if (entity instanceof PlayerEntity player) {
            if (ServerConfig.getInstance().enableWingsCreativeFly && !player.isCreative()) {
                if (!player.getAbilities().allowFlying) {
                    player.getAbilities().allowFlying = true;
                    player.sendAbilitiesUpdate();
                }
                ManaData data = ComponentManager.getManaData(player);
                if (player.getAbilities().flying && !data.tryUseMana(player, ServerConfig.getInstance().wings_of_salvation_second / 20))
                    player.getAbilities().flying = false;
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
