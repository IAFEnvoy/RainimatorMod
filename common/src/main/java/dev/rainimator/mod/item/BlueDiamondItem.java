package dev.rainimator.mod.item;

import dev.rainimator.mod.registry.util.FoilItemBase;
import dev.rainimator.mod.registry.RainimatorItemGroups;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;

import java.util.List;

public class BlueDiamondItem extends FoilItemBase {
    public BlueDiamondItem() {
        super(p -> p.fireproof().rarity(Rarity.EPIC).arch$tab(RainimatorItemGroups.ITEM));
    }

    @Override
    public void appendTooltip(ItemStack itemtack, World world, List<Text> list, TooltipContext flag) {
        super.appendTooltip(itemtack, world, list, flag);
        list.add(Text.translatable("item.rainimator.blue_diamond.tooltip1"));
        list.add(Text.translatable("item.rainimator.blue_diamond.tooltip2"));
    }
}
