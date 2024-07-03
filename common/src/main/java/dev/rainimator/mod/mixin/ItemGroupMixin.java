package dev.rainimator.mod.mixin;

import dev.rainimator.mod.registry.RainimatorBanners;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemGroup.class)
public class ItemGroupMixin {
    @Inject(method = "appendStacks", at = @At("RETURN"))
    private void addStacks(DefaultedList<ItemStack> stacks, CallbackInfo ci) {
        stacks.add(RainimatorBanners.FROST);
        stacks.add(RainimatorBanners.UNDEAD);
        stacks.add(RainimatorBanners.NETHER);
        stacks.add(RainimatorBanners.DRAGONSPIRE);
        stacks.add(RainimatorBanners.ENDER_PIRATE);
        stacks.add(RainimatorBanners.THE_GATEKEEPERS);
        stacks.add(RainimatorBanners.ORCHID_CITY);
        stacks.add(RainimatorBanners.LIGHTBORNE_ISLES);
        stacks.add(RainimatorBanners.THE_UMBRAL_KINGDOM);
        stacks.add(RainimatorBanners.CHORUS_BAY);
        stacks.add(RainimatorBanners.VOID_ISLANDS);
    }
}
