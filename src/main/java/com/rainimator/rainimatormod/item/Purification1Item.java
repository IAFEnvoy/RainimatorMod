package com.rainimator.rainimatormod.item;

import com.rainimator.rainimatormod.registry.ModEffects;
import com.rainimator.rainimatormod.registry.util.ItemBase;
import com.rainimator.rainimatormod.registry.util.ModCreativeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Purification1Item extends ItemBase {
    public Purification1Item() {
        super(p -> p.tab(ModCreativeTab.items).stacksTo(1).rarity(Rarity.UNCOMMON).food((new FoodProperties.Builder()).nutrition(0).saturationMod(0.0F).alwaysEat().build()));
    }

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack itemstack) {
        return UseAnim.DRINK;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public boolean isFoil(@NotNull ItemStack itemstack) {
        return true;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemstack, Level world, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(new TextComponent("§b由蓝钻石的纯净之力净化而来的水，喝下可给予使用者2分30秒的净化buff，净化生效期间不会受到任何负面buff影响！"));
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack itemstack, @NotNull Level world, @NotNull LivingEntity entity) {
        ItemStack retval = new ItemStack(Items.GLASS_BOTTLE);
        super.finishUsingItem(itemstack, world, entity);

        if (!entity.level.isClientSide())
            entity.addEffect(new MobEffectInstance(ModEffects.PURIFICATION.get(), 3000, 0));

        if (itemstack.isEmpty())
            return retval;
        if (entity instanceof Player player)
            if (!(player.getAbilities()).instabuild && !player.getInventory().add(retval))
                player.drop(retval, false);

        return itemstack;
    }
}
