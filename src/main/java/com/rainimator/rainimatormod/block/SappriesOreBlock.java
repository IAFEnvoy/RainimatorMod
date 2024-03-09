package com.rainimator.rainimatormod.block;

import com.rainimator.rainimatormod.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.storage.loot.LootContext;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class SappriesOreBlock extends Block {
    public SappriesOreBlock() {
        super(Properties.of(Material.STONE).sound(SoundType.STONE).strength(3.0F, 10.0F).lightLevel(s -> 1).requiresCorrectToolForDrops());
    }

    @Deprecated
    @Override
    public int getLightBlock(@NotNull BlockState state, @NotNull BlockGetter worldIn, @NotNull BlockPos pos) {
        return 15;
    }

    @Deprecated
    @Override
    public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
        Item item = player.getInventory().getSelected().getItem();
        if (item instanceof PickaxeItem tieredItem)
            return (tieredItem.getTier().getLevel() >= 3);
        return false;
    }

    @Deprecated
    @Override
    public @NotNull List<ItemStack> getDrops(@NotNull BlockState state, LootContext.@NotNull Builder builder) {
        List<ItemStack> dropsOriginal = super.getDrops(state, builder);
        if (!dropsOriginal.isEmpty())
            return dropsOriginal;
        return Collections.singletonList(new ItemStack(ModItems.RUBYBLOCKSHIT.get()));
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState blockstate, Level world, BlockPos pos, Player entity, boolean willHarvest, FluidState fluid) {
        boolean retval = super.onDestroyedByPlayer(blockstate, world, pos, entity, willHarvest, fluid);
        double x = pos.getX();
        double y = pos.getY();
        double z = pos.getZ();
        if (entity != null) {
            double[] p = {0.25, 0.2, 0.15, 0.1, 0.05};
            for (int i = 1; i <= EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BLOCK_FORTUNE, entity.getMainHandItem()); i++)
                if (Math.random() < p[i - 1]) {
                    for (int j = 0; j < i; j++) {
                        ItemEntity entityToSpawn = new ItemEntity(world, x, y, z, new ItemStack(ModItems.RUBYBLOCKSHIT.get()));
                        entityToSpawn.setPickUpDelay(50);
                        world.addFreshEntity(entityToSpawn);
                    }
                    break;
                }
            for (int i = 1; i <= EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BLOCK_FORTUNE, entity.getOffhandItem()); i++)
                if (Math.random() < p[i - 1]) {
                    for (int j = 0; j < i; j++) {
                        ItemEntity entityToSpawn = new ItemEntity(world, x, y, z, new ItemStack(ModItems.RUBYBLOCKSHIT.get()));
                        entityToSpawn.setPickUpDelay(50);
                        world.addFreshEntity(entityToSpawn);
                    }
                    break;
                }
        }
        return retval;
    }
}