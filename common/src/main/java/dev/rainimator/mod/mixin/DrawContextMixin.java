package dev.rainimator.mod.mixin;

import dev.rainimator.mod.registry.util.RainimatorInfoManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(DrawContext.class)
public class DrawContextMixin {
    @Inject(method = "drawItemTooltip", at = @At("HEAD"))
    private void onRenderTooltipInternal(TextRenderer textRenderer, ItemStack stack, int x, int y, CallbackInfo ci) {
        if (stack != ItemStack.EMPTY)
            RainimatorInfoManager.onRenderToolTip(stack.getItem());
    }
}
