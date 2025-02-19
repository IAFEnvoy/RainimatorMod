package com.iafenvoy.rainimator.item.block;

import com.iafenvoy.neptune.object.SoundUtil;
import com.iafenvoy.neptune.util.Timeout;
import com.iafenvoy.rainimator.recipe.BossSpawnRecipeManager;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class DarkObsidianBlock extends Block {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    public DarkObsidianBlock() {
        super(Settings.create().requiresTool().strength(50, 1200));
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    public boolean isTransparent(BlockState state, BlockView reader, BlockPos pos) {
        return true;
    }

    @Override
    public int getOpacity(BlockState state, BlockView worldIn, BlockPos pos) {
        return 0;
    }

    @Override
    public VoxelShape getCullingShape(BlockState state, BlockView world, BlockPos pos) {
        return VoxelShapes.empty();
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        return this.getDefaultState().with(FACING, context.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.get(FACING)));
    }

    @Override
    public ActionResult onUse(BlockState blockstate, World world, BlockPos pos, PlayerEntity entity, Hand hand, BlockHitResult hit) {
        double x = pos.getX() + 0.5;
        double y = pos.getY() + 0.5;
        double z = pos.getZ() + 0.5;
        Item used = entity.getMainHandStack().getItem();
        if (BossSpawnRecipeManager.canSummon(used)) {
            if (world instanceof ServerWorld serverWorld) {
                serverWorld.spawnParticles(ParticleTypes.SOUL_FIRE_FLAME, x, y, z, 200, 0, 8, 0, 0.0002);
                serverWorld.spawnParticles(ParticleTypes.FLAME, x + 3, y, z, 150, 0, 6, 0, 0.0002);
                serverWorld.spawnParticles(ParticleTypes.FLAME, x - 3, y, z, 150, 0, 6, 0, 0.0002);
                serverWorld.spawnParticles(ParticleTypes.FLAME, x, y, z + 3, 150, 0, 6, 0, 0.0002);
                serverWorld.spawnParticles(ParticleTypes.FLAME, x, y, z - 3, 150, 0, 6, 0, 0.0002);
                serverWorld.spawnParticles(ParticleTypes.FLAME, x + 2, y, z + 2, 150, 0, 6, 0, 0.0002);
                serverWorld.spawnParticles(ParticleTypes.FLAME, x - 2, y, z - 2, 150, 0, 6, 0, 0.0002);
                serverWorld.spawnParticles(ParticleTypes.FLAME, x + 2, y, z - 2, 150, 0, 6, 0, 0.0002);
                serverWorld.spawnParticles(ParticleTypes.FLAME, x - 2, y, z + 2, 150, 0, 6, 0, 0.0002);
            }
            SoundUtil.playSound(world, x, y, z, SoundEvents.BLOCK_PORTAL_TRAVEL, 1, 1);
            if (!world.isClient && world.getServer() != null)
                world.getServer().getPlayerManager().broadcast(Text.translatable("block.rainimator.dark_obsidian_block.running"), false);
            entity.getMainHandStack().decrement(1);
            entity.getInventory().markDirty();
            if (world instanceof ServerWorld serverWorld)
                Timeout.create(60, () -> {
                    serverWorld.spawnParticles(ParticleTypes.END_ROD, x, y, z, 200, 1, 2, 1, 0.05);
                    BossSpawnRecipeManager.summon(used, serverWorld, x, y, z);
                });
        }
        return ActionResult.SUCCESS;
    }
}