package com.iafenvoy.rainimator.item.block;

import com.iafenvoy.rainimator.registry.RainimatorBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class TopazBlock extends Block {
    public TopazBlock() {
        super(Settings.create().requiresTool().strength(6).sounds(BlockSoundGroup.METAL));
    }

    @Override
    public int getOpacity(BlockState state, BlockView worldIn, BlockPos pos) {
        return 15;
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBreak(world, pos, state, player);
        dropStack(world, pos, new ItemStack(RainimatorBlocks.TOPAZ_BLOCK.get()));
    }
}