package com.rainimator.rainimatormod.mixin;

import com.rainimator.rainimatormod.registry.util.RainimatorInfoManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@OnlyIn(Dist.CLIENT)
@Mixin(CreativeModeInventoryScreen.class)
public abstract class CreativeModeInventoryScreenMixin<T extends AbstractContainerMenu> extends AbstractContainerScreen<T> {
    public CreativeModeInventoryScreenMixin(T p_97741_, Inventory p_97742_, Component p_97743_) {
        super(p_97741_, p_97742_, p_97743_);
    }

    @Inject(method = "containerTick", at = @At("TAIL"))
    public void onContainerTick(CallbackInfo ci) {
        if (this.hoveredSlot != null)
            RainimatorInfoManager.onInventoryTick(Minecraft.getInstance().player, this.hoveredSlot.getItem().getItem());
    }
}
