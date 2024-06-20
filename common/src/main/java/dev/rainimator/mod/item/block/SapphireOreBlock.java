package dev.rainimator.mod.item.block;

import dev.rainimator.mod.registry.RainimatorItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class SapphireOreBlock extends Block {
    public SapphireOreBlock() {
        super(Settings.create().requiresTool().strength(4));
    }

    @Override
    public int getOpacity(BlockState state, BlockView worldIn, BlockPos pos) {
        return 15;
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBreak(world, pos, state, player);
        dropStack(world, pos, new ItemStack(RainimatorItems.RAW_SAPPHIRE.get()));
    }
}