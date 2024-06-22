package dev.rainimator.mod.impl;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.rainimator.mod.data.component.ManaData;
import dev.rainimator.mod.data.config.ServerConfig;
import dev.rainimator.mod.item.util.ItemBase;
import dev.rainimator.mod.registry.RainimatorItemGroups;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.apache.commons.lang3.NotImplementedException;

public class WingsOfSalvationItem extends ItemBase {
    protected int lastBoostTick = 0;

    public WingsOfSalvationItem() {
        super(p -> p.arch$tab(RainimatorItemGroups.MAIN));
    }

    protected static void noEnoughEnergy(PlayerEntity player) {
        player.getAbilities().flying = false;
    }

    @ExpectPlatform
    public static WingsOfSalvationItem create() {
        throw new NotImplementedException("This method should be replaced by Architectury.");
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
