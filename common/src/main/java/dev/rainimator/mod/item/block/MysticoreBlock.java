package dev.rainimator.mod.item.block;

import dev.rainimator.mod.registry.RainimatorItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.text.Text;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MysticoreBlock extends Block {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    private static final List<Pair<ItemConvertible, Double>> lootTable = new ArrayList<>();

    public MysticoreBlock() {
        super(Settings.create().requiresTool().strength(4));
        this.setDefaultState((this.stateManager.getDefaultState()).with(FACING, Direction.NORTH));
    }

    private static synchronized void initLootTable() {
        lootTable.add(Pair.of(Items.REDSTONE, 0.6D));
        lootTable.add(Pair.of(Items.EMERALD, 0.5D));
        lootTable.add(Pair.of(Items.LAPIS_LAZULI, 0.4D));
        lootTable.add(Pair.of(Items.DIAMOND, 0.3D));
        lootTable.add(Pair.of(RainimatorItems.RAW_SAPPHIRE.get(), 0.2D));
        lootTable.add(Pair.of(RainimatorItems.RAW_RUBY.get(), 0.1D));
    }

    @Override
    public int getOpacity(BlockState state, BlockView worldIn, BlockPos pos) {
        return 15;
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
    public List<ItemStack> getDroppedStacks(BlockState state, LootContextParameterSet.Builder builder) {
        List<ItemStack> dropsOriginal = super.getDroppedStacks(state, builder);
        if (!dropsOriginal.isEmpty())
            return dropsOriginal;
        return Collections.singletonList(new ItemStack(Blocks.AIR));
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBreak(world, pos, state, player);
        double x = pos.getX();
        double y = pos.getY();
        double z = pos.getZ();
        if (player != null) {
            if (!world.isClient())
                world.spawnEntity(new ExperienceOrbEntity(world, x, y, z, 10));
            if (Math.random() < 0.8D) {
                if (lootTable.isEmpty()) initLootTable();
                for (int i = 0; i < 3; i++)
                    for (Pair<ItemConvertible, Double> itemLikeDoublePair : lootTable)
                        if (Math.random() < itemLikeDoublePair.getRight()) {
                            if (!world.isClient()) {
                                ItemEntity entityToSpawn = new ItemEntity(world, x, y, z, new ItemStack(itemLikeDoublePair.getLeft()));
                                entityToSpawn.setPickupDelay(10);
                                world.spawnEntity(entityToSpawn);
                            }
                            break;
                        }
            } else {
                if (!player.getWorld().isClient())
                    player.sendMessage(Text.translatable("block.rainimator.mystic_ore.nothing"), true);
                if (!world.isClient()) {
                    ItemEntity entityToSpawn = new ItemEntity(world, x, y, z, new ItemStack(Blocks.COBBLED_DEEPSLATE));
                    entityToSpawn.setPickupDelay(10);
                    world.spawnEntity(entityToSpawn);
                }

            }
        }
    }
}