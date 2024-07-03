package dev.rainimator.mod.registry.util;

import dev.rainimator.mod.registry.RainimatorItemGroups;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.world.World;

public class SwordItemBase extends SwordItem implements ISwingable {
    public SwordItemBase(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings.group(RainimatorItemGroups.MAIN));
    }

    public boolean onEntitySwing(ItemStack itemtack, Entity entity) {
        return this.onSwingHand(itemtack, entity.world, entity.getX(), entity.getY(), entity.getZ());
    }

    public boolean onSwingHand(ItemStack itemtack, World world, double x, double y, double z) {
        return false;
    }
}
