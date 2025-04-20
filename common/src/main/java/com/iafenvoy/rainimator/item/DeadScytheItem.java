package com.iafenvoy.rainimator.item;

import com.iafenvoy.neptune.object.item.ToolMaterialUtil;
import com.iafenvoy.neptune.util.RandomHelper;
import com.iafenvoy.rainimator.registry.RainimatorItemGroups;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DeadScytheItem extends SwordItem {
    public DeadScytheItem() {
        super(ToolMaterialUtil.of(3000, 4, 10, 4, 20, () -> Blocks.NETHERITE_BLOCK), 3, -2.3f, new Settings().fireproof().arch$tab(RainimatorItemGroups.MAIN));
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        boolean retval = super.postHit(stack, target, attacker);
        if (target instanceof MobEntity mob)
            mob.setTarget(attacker);
        if (Math.random() < 0.4)
            target.setOnFireFor(5);
        if (Math.random() < 0.2 && attacker instanceof LivingEntity)
            attacker.setHealth(attacker.getHealth() + RandomHelper.nextInt(1, 2));
        return retval;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        tooltip.add(Text.translatable("item.rainimator.dead_scythe.tooltip"));
    }
}
