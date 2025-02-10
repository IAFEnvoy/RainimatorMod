package dev.rainimator.mod.item.block;

import dev.rainimator.mod.registry.RainimatorBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class BlueDiamondBlock extends Block {
    public BlueDiamondBlock() {
        super(Settings.create().requiresTool().strength(6).sounds(BlockSoundGroup.METAL));
    }

    @Override
    public int getOpacity(BlockState state, BlockView worldIn, BlockPos pos) {
        return 15;
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBreak(world, pos, state, player);
        dropStack(world, pos, new ItemStack(RainimatorBlocks.BLUE_DIAMOND_BLOCK.get()));
    }
}