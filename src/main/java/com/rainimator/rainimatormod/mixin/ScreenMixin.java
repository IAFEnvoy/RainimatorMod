package com.rainimator.rainimatormod.mixin;

import com.rainimator.rainimatormod.registry.util.RainimatorInfoManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Screen.class)
public class ScreenMixin {
    @Shadow
    private ItemStack tooltipStack;

    @Inject(method = "renderTooltipInternal", at = @At("HEAD"))
    private void onRenderTooltipInternal(CallbackInfo ci) {
        if (this.tooltipStack != ItemStack.EMPTY)
            RainimatorInfoManager.onRenderToolTip(Minecraft.getInstance().player, this.tooltipStack.getItem());
    }
}
