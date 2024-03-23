package com.rainimator.rainimatormod.block;

import com.rainimator.rainimatormod.registry.ModBlocks;
import com.rainimator.rainimatormod.registry.ModEntities;
import com.rainimator.rainimatormod.registry.ModItems;
import com.rainimator.rainimatormod.util.Consumer5;
import com.rainimator.rainimatormod.util.EntityUtil;
import com.rainimator.rainimatormod.util.SoundUtil;
import com.rainimator.rainimatormod.util.Timeout;
import net.minecraft.Util;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class DarkObsidianBlock extends Block {
    public static final HashMap<Item, Consumer5<Player, ServerLevel, Integer, Integer, Integer>> consumers = new HashMap<>();
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public DarkObsidianBlock() {
        super(Properties.of(Material.STONE).sound(SoundType.STONE).strength(-1.0F, 3600000.0F).lightLevel(s -> 5).jumpFactor(0.8F).noOcclusion().hasPostProcess((bs, br, bp) -> true).emissiveRendering((bs, br, bp) -> true).isRedstoneConductor((bs, br, bp) -> false));
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @OnlyIn(Dist.CLIENT)
    public static void registerRenderLayer() {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.DARK_OBSIDIAN_BLOCK.get(), renderType -> (renderType == RenderType.cutout()));
    }

    @Override
    public boolean propagatesSkylightDown(@NotNull BlockState state, @NotNull BlockGetter reader, @NotNull BlockPos pos) {
        return true;
    }

    @Deprecated
    @Override
    public int getLightBlock(@NotNull BlockState state, @NotNull BlockGetter worldIn, @NotNull BlockPos pos) {
        return 0;
    }

    @Deprecated
    @Override
    public @NotNull VoxelShape getVisualShape(@NotNull BlockState state, @NotNull BlockGetter world, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return Shapes.empty();
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
    public @NotNull List<ItemStack> getDrops(@NotNull BlockState state, LootContext.@NotNull Builder builder) {
        List<ItemStack> dropsOriginal = super.getDrops(state, builder);
        if (!dropsOriginal.isEmpty())
            return dropsOriginal;
        return Collections.singletonList(new ItemStack(this, 1));
    }

    @Deprecated
    @Override
    public @NotNull InteractionResult use(@NotNull BlockState blockstate, @NotNull Level world, @NotNull BlockPos pos, @NotNull Player entity, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
        super.use(blockstate, world, pos, entity, hand, hit);
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        if (consumers.size() == 0) initConsumers();
        Item used = entity.getMainHandItem().getItem();
        if (consumers.containsKey(used)) {
            if (world instanceof ServerLevel _level) {
                _level.sendParticles(ParticleTypes.SOUL_FIRE_FLAME, x, y, z, 200, 0, 8, 0, 0.0002);
                _level.sendParticles(ParticleTypes.FLAME, ((double) x + 3), y, z, 150, 0, 6, 0, 0.0002);
                _level.sendParticles(ParticleTypes.FLAME, ((double) x - 3), y, z, 150, 0, 6, 0, 0.0002);
                _level.sendParticles(ParticleTypes.FLAME, x, y, ((double) z + 3), 150, 0, 6, 0, 0.0002);
                _level.sendParticles(ParticleTypes.FLAME, x, y, ((double) z - 3), 150, 0, 6, 0, 0.0002);
                _level.sendParticles(ParticleTypes.FLAME, ((double) x + 2), y, ((double) z + 2), 150, 0, 6, 0, 0.0002);
                _level.sendParticles(ParticleTypes.FLAME, ((double) x - 2), y, ((double) z - 2), 150, 0, 6, 0, 0.0002);
                _level.sendParticles(ParticleTypes.FLAME, ((double) x + 2), y, ((double) z - 2), 150, 0, 6, 0, 0.0002);
                _level.sendParticles(ParticleTypes.FLAME, ((double) x - 2), y, ((double) z + 2), 150, 0, 6, 0, 0.0002);
            }
            SoundUtil.playSound(world, x, y, z, new ResourceLocation("block.portal.travel"), 1, 1);
            if (!world.isClientSide() && world.getServer() != null)
                world.getServer().getPlayerList().broadcastMessage(new TranslatableComponent("block.rainimator.dark_obsidian_block.running"), ChatType.SYSTEM, Util.NIL_UUID);
            ItemStack _setstack = new ItemStack(Blocks.AIR);
            _setstack.setCount(1);
            entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
            entity.getInventory().setChanged();
            if (world instanceof ServerLevel _level)
                Timeout.create(60, () -> {
                    _level.sendParticles(ParticleTypes.END_ROD, x, y, z, 200, 1, 2, 1, 0.05);
                    consumers.get(used).accept(entity, _level, x, y, z);
                });
        }
        return InteractionResult.SUCCESS;
    }

    public static synchronized void initConsumers() {
        consumers.put(ModItems.LIGHT_HEART.get(), (entity, world, x, y, z) -> {
            EntityUtil.summon(ModEntities.HEROBRINE.get(), world, x, y, z);
            EntityUtil.summon(ModEntities.ZOMBIES.get(), world, x, y, z - Mth.nextInt(new Random(), 1, 3));
            EntityUtil.summon(ModEntities.DARK_ZOMBIE.get(), world, x, y, z + Mth.nextInt(new Random(), 1, 3));
            EntityUtil.summon(ModEntities.AZALEA.get(), world, x + Mth.nextInt(new Random(), 1, 3), y, z);
        });
        consumers.put(Blocks.WITHER_ROSE.asItem(), (entity, world, x, y, z) -> {
            ItemStack _stktoremove = new ItemStack(Blocks.WITHER_ROSE);
            entity.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, entity.inventoryMenu.getCraftSlots());
            EntityUtil.summon(ModEntities.KRALOS.get(), world, x, y, z);
        });
        consumers.put(Items.TOTEM_OF_UNDYING, (entity, world, x, y, z) -> {
            EntityUtil.summon(ModEntities.KLAUS.get(), world, x, y, z);
            EntityUtil.summon(EntityType.PILLAGER, world, x, y, z - Mth.nextInt(new Random(), 1, 3));
            EntityUtil.summon(EntityType.PILLAGER, world, x, y, z + Mth.nextInt(new Random(), 1, 3));
            EntityUtil.summon(EntityType.PILLAGER, world, x - Mth.nextInt(new Random(), 1, 3), y, z);
            EntityUtil.summon(EntityType.PILLAGER, world, x + Mth.nextInt(new Random(), 1, 3), y, z);
        });
        consumers.put(Blocks.WITHER_SKELETON_SKULL.asItem(), (entity, world, x, y, z) -> {
            ItemStack _stktoremove = new ItemStack(Blocks.WITHER_SKELETON_SKULL);
            entity.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, entity.inventoryMenu.getCraftSlots());
            EntityUtil.summon(ModEntities.GIGABONE.get(), world, x, y, z);
        });
        consumers.put(ModItems.SOUL_PEOPLE.get(), (entity, world, x, y, z) -> {
            ItemStack _stktoremove = new ItemStack(ModItems.SOUL_PEOPLE.get());
            entity.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, entity.inventoryMenu.getCraftSlots());
            EntityUtil.summon(ModEntities.NAMTAR.get(), world, x, y, z);
            EntityUtil.summon(ModEntities.ZOMBIE_PIGLIN_ART.get(), world, x, y, z - Mth.nextInt(new Random(), 1, 3));
            EntityUtil.summon(ModEntities.MUTATED.get(), world, x, y, z + Mth.nextInt(new Random(), 1, 3));
            EntityUtil.summon(ModEntities.SKELETON_SNOW.get(), world, x + Mth.nextInt(new Random(), 1, 3), y, z);
            EntityUtil.summon(ModEntities.WITHER_SHIELD.get(), world, x - Mth.nextInt(new Random(), 1, 3), y, z);
        });
        consumers.put(ModItems.WITHER_BONE.get(), (entity, world, x, y, z) -> {
            ItemStack _stktoremove = new ItemStack(ModItems.WITHER_BONE.get());
            entity.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, entity.inventoryMenu.getCraftSlots());
            EntityUtil.summon(ModEntities.BIG_UNDEAD_SKELETON.get(), world, x, y, z);
        });
        consumers.put(Items.GOLDEN_SWORD, (entity, world, x, y, z) -> EntityUtil.summon(ModEntities.PIGLIN_COMMANDER.get(), world, x, y, z));
        consumers.put(Items.GOLD_INGOT, (entity, world, x, y, z) -> {
            ItemStack _stktoremove = new ItemStack(Items.GOLD_INGOT);
            entity.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, entity.inventoryMenu.getCraftSlots());
            EntityUtil.summon(ModEntities.ZOMBIE_PIGLIN_KING.get(), world, x, y, z);
            EntityUtil.summon(EntityType.ZOMBIFIED_PIGLIN, world, x, y, z + Mth.nextInt(new Random(), 1, 3));
        });
        consumers.put(Blocks.GOLD_BLOCK.asItem(), (entity, world, x, y, z) -> {
            ItemStack _stktoremove = new ItemStack(Blocks.GOLD_BLOCK);
            entity.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, entity.inventoryMenu.getCraftSlots());
            EntityUtil.summon(ModEntities.PIGLIN_KING_ZOMBIES.get(), world, x, y, z);
            EntityUtil.summon(ModEntities.ZOMBIE_PIGLIN_ART.get(), world, x, y, z - Mth.nextInt(new Random(), 1, 3));
            EntityUtil.summon(EntityType.ZOMBIFIED_PIGLIN, world, x, y, z + Mth.nextInt(new Random(), 1, 3));
        });
        consumers.put(ModItems.BAO_ZHU.get(), (entity, world, x, y, z) -> EntityUtil.summon(ModEntities.NULL_LIKE.get(), world, x, y, z));
        consumers.put(ModItems.WARRIOR_HEART.get(), (entity, world, x, y, z) -> {
            EntityUtil.summon(ModEntities.NAEUS.get(), world, x, y, z);
            EntityUtil.summon(ModEntities.WITHERED_SKELETONS.get(), world, x, y, z - Mth.nextInt(new Random(), 1, 3));
            EntityUtil.summon(ModEntities.HOGSWORTH.get(), world, x, y, z + Mth.nextInt(new Random(), 1, 3));
        });
        consumers.put(ModItems.ICE_HEART.get(), (entity, world, x, y, z) -> {
            EntityUtil.summon(ModEntities.RAIN.get(), world, x, y, z);
            EntityUtil.summon(ModEntities.CIARA.get(), world, x, y, z + Mth.nextInt(new Random(), 1, 3));
            EntityUtil.summon(ModEntities.DARYLL.get(), world, x, y, z - Mth.nextInt(new Random(), 1, 3));
        });
        consumers.put(ModItems.ENDER_HEART.get(), (entity, world, x, y, z) -> {
            EntityUtil.summon(ModEntities.CERIS.get(), world, x, y, z);
            EntityUtil.summon(ModEntities.DARK_SHIELD.get(), world, x, y, x + Mth.nextInt(new Random(), 1, 3));
            EntityUtil.summon(ModEntities.DARK_SHIELD.get(), world, x, y, x - Mth.nextInt(new Random(), 1, 3));
        });
        consumers.put(ModItems.MAGIC_STARD.get(), (entity, world, x, y, z) -> {
            EntityUtil.summon(ModEntities.PATRICK.get(), world, x, y, z);
            EntityUtil.summon(ModEntities.HILDA.get(), world, x, y, x - Mth.nextInt(new Random(), 1, 3));
            EntityUtil.summon(ModEntities.SOLDIERS.get(), world, x, y, x + Mth.nextInt(new Random(), 1, 3));
        });
        consumers.put(ModItems.LOWER_BOUND_ALLOY_BONE.get(), (entity, world, x, y, z) -> EntityUtil.summon(ModEntities.BLACKBONE.get(), world, x, y, z));
        consumers.put(ModItems.UNDER_FLOWER.get(), (entity, world, x, y, z) -> EntityUtil.summon(ModEntities.ABIGAIL.get(), world, x, y, z));
    }
}