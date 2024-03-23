package com.rainimator.rainimatormod.block;

import com.rainimator.rainimatormod.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.storage.loot.LootContext;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MysticoreBlock extends Block {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    private static final List<Pair<ItemLike, Double>> lootTable = new ArrayList<>();

    public MysticoreBlock() {
        super(Properties.of(Material.STONE).sound(SoundType.STONE).strength(25.0F, 50.0F).requiresCorrectToolForDrops().hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true));
        this.registerDefaultState((this.stateDefinition.any()).setValue(FACING, Direction.NORTH));
    }

    private static synchronized void initLootTable() {
        lootTable.add(Pair.of(Items.REDSTONE, 0.6D));
        lootTable.add(Pair.of(Items.EMERALD, 0.5D));
        lootTable.add(Pair.of(Items.LAPIS_LAZULI, 0.4D));
        lootTable.add(Pair.of(Items.DIAMOND, 0.3D));
        lootTable.add(Pair.of(ModItems.RAW_SAPPHIRE.get(), 0.2D));
        lootTable.add(Pair.of(ModItems.RAW_RUBY.get(), 0.1D));
    }

    @Deprecated
    @Override
    public int getLightBlock(@NotNull BlockState state, @NotNull BlockGetter worldIn, @NotNull BlockPos pos) {
        return 15;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Deprecated
    @Override
    public @NotNull BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Deprecated
    @Override
    public @NotNull BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
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
        return Collections.singletonList(new ItemStack(Blocks.AIR));
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState blockstate, Level world, BlockPos pos, Player entity, boolean willHarvest, FluidState fluid) {
        boolean retval = super.onDestroyedByPlayer(blockstate, world, pos, entity, willHarvest, fluid);
        double x = pos.getX();
        double y = pos.getY();
        double z = pos.getZ();
        if (entity != null) {
            if (!world.isClientSide())
                world.addFreshEntity(new ExperienceOrb(world, x, y, z, 10));
            if (Math.random() < 0.8D) {
                if (lootTable.size() == 0) initLootTable();
                for (int i = 0; i < 3; i++)
                    for (Pair<ItemLike, Double> itemLikeDoublePair : lootTable)
                        if (Math.random() < itemLikeDoublePair.getRight()) {
                            if (!world.isClientSide()) {
                                ItemEntity entityToSpawn = new ItemEntity(world, x, y, z, new ItemStack(itemLikeDoublePair.getLeft()));
                                entityToSpawn.setPickUpDelay(10);
                                world.addFreshEntity(entityToSpawn);
                            }
                            break;
                        }
            } else {
                if (!entity.level.isClientSide())
                    entity.displayClientMessage(new TranslatableComponent("block.rainimator.mystic_ore.nothing"), true);
                if (!world.isClientSide()) {
                    ItemEntity entityToSpawn = new ItemEntity(world, x, y, z, new ItemStack(Blocks.COBBLED_DEEPSLATE));
                    entityToSpawn.setPickUpDelay(10);
                    world.addFreshEntity(entityToSpawn);
                }

            }
        }
        return retval;
    }
}