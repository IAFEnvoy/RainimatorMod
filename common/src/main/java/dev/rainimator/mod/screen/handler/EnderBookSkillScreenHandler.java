package dev.rainimator.mod.screen.handler;

import dev.rainimator.mod.registry.RainimatorScreenHandlers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;

public class EnderBookSkillScreenHandler extends ScreenHandler {
    public EnderBookSkillScreenHandler(int syncId, PlayerInventory playerInventory) {
        super(RainimatorScreenHandlers.ENDER_BOOK.get(), syncId);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        return null;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }
}
