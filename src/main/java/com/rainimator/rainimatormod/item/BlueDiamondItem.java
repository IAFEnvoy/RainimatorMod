package com.rainimator.rainimatormod.item;

import com.rainimator.rainimatormod.registry.util.FoilItemBase;
import com.rainimator.rainimatormod.registry.util.ModCreativeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BlueDiamondItem extends FoilItemBase {
    public BlueDiamondItem() {
        super(p -> p.tab(ModCreativeTab.items).fireResistant().rarity(Rarity.EPIC));
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemstack, Level world, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(new TextComponent("§b拥有神力的宝物"));
        list.add(new TextComponent("§a它散发的力量可以轻松压制不死族的力量！"));
    }
}
