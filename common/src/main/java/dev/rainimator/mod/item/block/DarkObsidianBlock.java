package dev.rainimator.mod.item.block;

import com.iafenvoy.neptune.object.EntityUtil;
import com.iafenvoy.neptune.object.SoundUtil;
import com.iafenvoy.neptune.util.RandomHelper;
import com.iafenvoy.neptune.util.Timeout;
import com.iafenvoy.neptune.util.function.consumer.Consumer5;
import dev.rainimator.mod.registry.RainimatorEntities;
import dev.rainimator.mod.registry.RainimatorItems;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class DarkObsidianBlock extends Block {
    public static final HashMap<Item, Consumer5<PlayerEntity, ServerWorld, Integer, Integer, Integer>> consumers = new HashMap<>();
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

    public DarkObsidianBlock() {
        super(Settings.create().requiresTool().strength(50, 1200));
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
    }

    public static synchronized void initConsumers() {
        consumers.put(RainimatorItems.LIGHT_HEART.get(), (entity, world, x, y, z) -> {
            EntityUtil.summon(RainimatorEntities.HEROBRINE.get(), world, x, y, z);
            EntityUtil.summon(RainimatorEntities.ELITE_ZOMBIE.get(), world, x, y, z - RandomHelper.nextInt(1, 3));
            EntityUtil.summon(RainimatorEntities.DARK_ZOMBIE.get(), world, x, y, z + RandomHelper.nextInt(1, 3));
            EntityUtil.summon(RainimatorEntities.AZALEA.get(), world, x + RandomHelper.nextInt(1, 3), y, z);
        });
        consumers.put(Blocks.WITHER_ROSE.asItem(), (entity, world, x, y, z) -> EntityUtil.summon(RainimatorEntities.KRALOS.get(), world, x, y, z));
        consumers.put(Items.TOTEM_OF_UNDYING, (entity, world, x, y, z) -> {
            EntityUtil.summon(RainimatorEntities.KLAUS.get(), world, x, y, z);
            EntityUtil.summon(EntityType.PILLAGER, world, x, y, z - RandomHelper.nextInt(1, 3));
            EntityUtil.summon(EntityType.PILLAGER, world, x, y, z + RandomHelper.nextInt(1, 3));
            EntityUtil.summon(EntityType.PILLAGER, world, x - RandomHelper.nextInt(1, 3), y, z);
            EntityUtil.summon(EntityType.PILLAGER, world, x + RandomHelper.nextInt(1, 3), y, z);
        });
        consumers.put(Blocks.WITHER_SKELETON_SKULL.asItem(), (entity, world, x, y, z) -> EntityUtil.summon(RainimatorEntities.GIGABONE.get(), world, x, y, z));
        consumers.put(RainimatorItems.SOUL_PEOPLE.get(), (entity, world, x, y, z) -> {
            EntityUtil.summon(RainimatorEntities.NAMTAR.get(), world, x, y, z);
            EntityUtil.summon(RainimatorEntities.ZOMBIE_PIGLIN_ART.get(), world, x, y, z - RandomHelper.nextInt(1, 3));
            EntityUtil.summon(RainimatorEntities.MUTATED.get(), world, x, y, z + RandomHelper.nextInt(1, 3));
            EntityUtil.summon(RainimatorEntities.SKELETON_SNOW.get(), world, x + RandomHelper.nextInt(1, 3), y, z);
            EntityUtil.summon(RainimatorEntities.WITHER_SHIELD.get(), world, x - RandomHelper.nextInt(1, 3), y, z);
        });
        consumers.put(Items.GOLDEN_SWORD, (entity, world, x, y, z) -> EntityUtil.summon(RainimatorEntities.PIGLIN_COMMANDER.get(), world, x, y, z));
        consumers.put(Items.GOLD_INGOT, (entity, world, x, y, z) -> {
            EntityUtil.summon(RainimatorEntities.ZOMBIE_PIGLIN_KING.get(), world, x, y, z);
            EntityUtil.summon(EntityType.ZOMBIFIED_PIGLIN, world, x, y, z + RandomHelper.nextInt(1, 3));
        });
        consumers.put(Blocks.GOLD_BLOCK.asItem(), (entity, world, x, y, z) -> {
            EntityUtil.summon(RainimatorEntities.GLUTTON.get(), world, x, y, z);
            EntityUtil.summon(RainimatorEntities.ZOMBIE_PIGLIN_ART.get(), world, x, y, z - RandomHelper.nextInt(1, 3));
            EntityUtil.summon(EntityType.ZOMBIFIED_PIGLIN, world, x, y, z + RandomHelper.nextInt(1, 3));
        });
        consumers.put(RainimatorItems.BAO_ZHU.get(), (entity, world, x, y, z) -> EntityUtil.summon(RainimatorEntities.NULL_LIKE.get(), world, x, y, z));
        consumers.put(RainimatorItems.WARRIOR_HEART.get(), (entity, world, x, y, z) -> {
            EntityUtil.summon(RainimatorEntities.NAEUS.get(), world, x, y, z);
            EntityUtil.summon(RainimatorEntities.WITHERED_SKELETONS.get(), world, x, y, z - RandomHelper.nextInt(1, 3));
            EntityUtil.summon(RainimatorEntities.HOGSWORTH.get(), world, x, y, z + RandomHelper.nextInt(1, 3));
        });
        consumers.put(RainimatorItems.ICE_HEART.get(), (entity, world, x, y, z) -> {
            EntityUtil.summon(RainimatorEntities.RAIN.get(), world, x, y, z);
            EntityUtil.summon(RainimatorEntities.CIARA.get(), world, x, y, z + RandomHelper.nextInt(1, 3));
            EntityUtil.summon(RainimatorEntities.DARYLL.get(), world, x, y, z - RandomHelper.nextInt(1, 3));
        });
        consumers.put(RainimatorItems.ENDER_HEART.get(), (entity, world, x, y, z) -> {
            EntityUtil.summon(RainimatorEntities.CERIS.get(), world, x, y, z);
            EntityUtil.summon(RainimatorEntities.DARK_SHIELD.get(), world, x, y, x + RandomHelper.nextInt(1, 3));
            EntityUtil.summon(RainimatorEntities.DARK_SHIELD.get(), world, x, y, x - RandomHelper.nextInt(1, 3));
        });
        consumers.put(RainimatorItems.MAGIC_STAR.get(), (entity, world, x, y, z) -> {
            EntityUtil.summon(RainimatorEntities.PATRICK.get(), world, x, y, z);
            EntityUtil.summon(RainimatorEntities.HILDA.get(), world, x, y, x - RandomHelper.nextInt(1, 3));
            EntityUtil.summon(RainimatorEntities.SOLDIERS.get(), world, x, y, x + RandomHelper.nextInt(1, 3));
        });
        consumers.put(RainimatorItems.NETHERITE_WITHER_BONE.get(), (entity, world, x, y, z) -> EntityUtil.summon(RainimatorEntities.BLACKBONE.get(), world, x, y, z));
        consumers.put(RainimatorItems.UNDER_FLOWER.get(), (entity, world, x, y, z) -> EntityUtil.summon(RainimatorEntities.ABIGAIL.get(), world, x, y, z));
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
    public VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
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
    public List<ItemStack> getDroppedStacks(BlockState state, LootContextParameterSet.Builder builder) {
        List<ItemStack> dropsOriginal = super.getDroppedStacks(state, builder);
        if (!dropsOriginal.isEmpty())
            return dropsOriginal;
        return Collections.emptyList();
    }

    @Override
    public ActionResult onUse(BlockState blockstate, World world, BlockPos pos, PlayerEntity entity, Hand hand, BlockHitResult hit) {
        super.onUse(blockstate, world, pos, entity, hand, hit);
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        if (consumers.isEmpty()) initConsumers();
        Item used = entity.getMainHandStack().getItem();
        if (consumers.containsKey(used)) {
            if (world instanceof ServerWorld _level) {
                _level.spawnParticles(ParticleTypes.SOUL_FIRE_FLAME, x, y, z, 200, 0, 8, 0, 0.0002);
                _level.spawnParticles(ParticleTypes.FLAME, ((double) x + 3), y, z, 150, 0, 6, 0, 0.0002);
                _level.spawnParticles(ParticleTypes.FLAME, ((double) x - 3), y, z, 150, 0, 6, 0, 0.0002);
                _level.spawnParticles(ParticleTypes.FLAME, x, y, ((double) z + 3), 150, 0, 6, 0, 0.0002);
                _level.spawnParticles(ParticleTypes.FLAME, x, y, ((double) z - 3), 150, 0, 6, 0, 0.0002);
                _level.spawnParticles(ParticleTypes.FLAME, ((double) x + 2), y, ((double) z + 2), 150, 0, 6, 0, 0.0002);
                _level.spawnParticles(ParticleTypes.FLAME, ((double) x - 2), y, ((double) z - 2), 150, 0, 6, 0, 0.0002);
                _level.spawnParticles(ParticleTypes.FLAME, ((double) x + 2), y, ((double) z - 2), 150, 0, 6, 0, 0.0002);
                _level.spawnParticles(ParticleTypes.FLAME, ((double) x - 2), y, ((double) z + 2), 150, 0, 6, 0, 0.0002);
            }
            SoundUtil.playSound(world, x, y, z, SoundEvents.BLOCK_PORTAL_TRAVEL, 1, 1);
            if (!world.isClient && world.getServer() != null)
                world.getServer().getPlayerManager().broadcast(Text.translatable("block.rainimator.dark_obsidian_block.running"), false);
            entity.getMainHandStack().decrement(1);
            entity.getInventory().markDirty();
            if (world instanceof ServerWorld _level)
                Timeout.create(60, () -> {
                    _level.spawnParticles(ParticleTypes.END_ROD, x, y, z, 200, 1, 2, 1, 0.05);
                    consumers.get(used).accept(entity, _level, x, y, z);
                });
        }
        return ActionResult.SUCCESS;
    }
}