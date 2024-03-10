package com.rainimator.rainimatormod.item;

import com.rainimator.rainimatormod.registry.util.ItemBase;
import com.rainimator.rainimatormod.registry.util.ModCreativeTab;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.ItemHandlerHelper;
import org.jetbrains.annotations.NotNull;

public class CottonCandyItem extends ItemBase {
    public CottonCandyItem() {
        super(p -> p.tab(ModCreativeTab.items).food((new FoodProperties.Builder()).nutrition(3).saturationMod(0.1F).alwaysEat().build()));
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack itemstack, @NotNull Level world, @NotNull LivingEntity entity) {
        ItemStack retval = super.finishUsingItem(itemstack, world, entity);
        if (!entity.level.isClientSide())
            entity.addEffect(new MobEffectInstance(MobEffects.SATURATION, 200, 0));
        if (entity instanceof Player _player) {
            ItemStack _setstack = new ItemStack(Items.STICK);
            _setstack.setCount(1);
            ItemHandlerHelper.giveItemToPlayer(_player, _setstack);
        }
        return retval;
    }
}
