package com.rainimator.rainimatormod.item;

import com.rainimator.rainimatormod.registry.util.FoilItemBase;
import com.rainimator.rainimatormod.registry.util.ModCreativeTab;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class DivineCoreItem extends FoilItemBase {
    public DivineCoreItem() {
        super(p -> p.tab(ModCreativeTab.items).durability(100).rarity(Rarity.UNCOMMON));
    }

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack itemstack) {
        return UseAnim.BLOCK;
    }

    @Override
    public int getEnchantmentValue() {
        return 10;
    }

    @Override
    public int getUseDuration(@NotNull ItemStack itemstack) {
        return 1;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level world, @NotNull Player entity, @NotNull InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
        ItemStack itemstack = ar.getObject();

        if (entity.getHealth() == entity.getMaxHealth()) {
            if (itemstack.hurt(0, new Random(), null)) {
                itemstack.shrink(1);
                itemstack.setDamageValue(0);
            }
        } else {
            entity.setHealth(entity.getHealth() + Mth.nextInt(new Random(), 1, 4));
            if (itemstack.hurt(1, new Random(), null)) {
                itemstack.shrink(1);
                itemstack.setDamageValue(0);
            }
        }
        return ar;
    }
}
