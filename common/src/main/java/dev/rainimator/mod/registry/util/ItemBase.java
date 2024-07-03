package dev.rainimator.mod.registry.util;

import dev.rainimator.mod.registry.RainimatorItemGroups;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;

import java.util.function.Function;

public class ItemBase extends Item implements ISwingable {
    public ItemBase(Function<Settings, Settings> properties) {
        super(properties.apply(new Settings().rarity(Rarity.COMMON).maxCount(64).group(RainimatorItemGroups.MAIN)));
    }

    public void onDroppedByPlayer(ItemStack itemstack, PlayerEntity entity) {
    }

    public boolean onEntitySwing(ItemStack itemtack, Entity entity) {
        return this.onSwingHand(itemtack, entity.world, entity.getX(), entity.getY(), entity.getZ());
    }

    public boolean onSwingHand(ItemStack itemtack, World world, double x, double y, double z) {
        return false;
    }
}
