package dev.rainimator.mod.item.block;

import dev.rainimator.mod.RainimatorMod;
import dev.rainimator.mod.item.block.entity.DarkObsidianBlockEntity;
import dev.rainimator.mod.registry.RainimatorBlockEntities;
import dev.rainimator.mod.registry.RainimatorBlocks;
import dev.rainimator.mod.registry.RainimatorEntities;
import dev.rainimator.mod.registry.RainimatorItems;
import dev.rainimator.mod.util.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.world.ServerWorld;
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
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class DarkObsidianBlock extends BlockWithEntity {
    public static final HashMap<Item, Consumer5<PlayerEntity, ServerWorld, Integer, Integer, Integer>> consumers = new HashMap<>();
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final String NBT_KEY = "biome";

    public DarkObsidianBlock() {
        super(Settings.create().requiresTool().strength(50, 1200));
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
    }

    public static synchronized void initConsumers() {
        consumers.put(RainimatorItems.LIGHT_HEART.get(), (entity, world, x, y, z) -> {
            EntityUtil.summon(RainimatorEntities.HEROBRINE.get(), world, x, y, z);
            EntityUtil.summon(RainimatorEntities.ZOMBIES.get(), world, x, y, z - RandomHelper.nextInt(1, 3));
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
        consumers.put(RainimatorItems.WITHER_BONE.get(), (entity, world, x, y, z) -> EntityUtil.summon(RainimatorEntities.BIG_UNDEAD_SKELETON.get(), world, x, y, z));
        consumers.put(Items.GOLDEN_SWORD, (entity, world, x, y, z) -> EntityUtil.summon(RainimatorEntities.PIGLIN_COMMANDER.get(), world, x, y, z));
        consumers.put(Items.GOLD_INGOT, (entity, world, x, y, z) -> {
            EntityUtil.summon(RainimatorEntities.ZOMBIE_PIGLIN_KING.get(), world, x, y, z);
            EntityUtil.summon(EntityType.ZOMBIFIED_PIGLIN, world, x, y, z + RandomHelper.nextInt(1, 3));
        });
        consumers.put(Blocks.GOLD_BLOCK.asItem(), (entity, world, x, y, z) -> {
            EntityUtil.summon(RainimatorEntities.PIGLIN_KING_ZOMBIES.get(), world, x, y, z);
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
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        Optional<DarkObsidianBlockEntity> placed = world.getBlockEntity(pos, RainimatorBlockEntities.DARK_OBSIDIAN_BLOCK.get());
        placed.ifPresent(x -> x.setBiome(itemStack, world.getBiome(pos)));
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBreak(world, pos, state, player);
        ItemStack dropStack = new ItemStack(RainimatorBlocks.DARK_OBSIDIAN_BLOCK.get());
        Optional<DarkObsidianBlockEntity> placed = world.getBlockEntity(pos, RainimatorBlockEntities.DARK_OBSIDIAN_BLOCK.get());
        placed.ifPresent(x -> {
            RegistryKey<Biome> key = placed.get().getBiome();
            Optional<RegistryKey<Biome>> current = world.getBiome(pos).getKey();
            if (key == null && current.isPresent())
                placed.get().setBiome(current.get());
            if (key != null)
                dropStack.getOrCreateNbt().putString(NBT_KEY, key.getValue().toString());
        });
        dropStack(world, pos, dropStack);
    }

    @Override
    public List<ItemStack> getDroppedStacks(BlockState state, LootContextParameterSet.Builder builder) {
        List<ItemStack> dropsOriginal = super.getDroppedStacks(state, builder);
        if (!dropsOriginal.isEmpty())
            return dropsOriginal;
        return Collections.emptyList();
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        super.appendTooltip(stack, world, tooltip, options);
        NbtCompound compound = stack.getOrCreateNbt();
        String t = compound.getString(NBT_KEY);
        if (t.isBlank())
            t = RainimatorMod.MOD_ID + ":unknown";
        Identifier biome = new Identifier(t);
        tooltip.add(Text.literal(I18n.translate("block.rainimator.dark_obsidian_block.tooltip.biome") + I18n.translate(String.format("biome.%s.%s", biome.getNamespace(), biome.getPath()))));
    }

    @Override
    public ActionResult onUse(BlockState blockstate, World world, BlockPos pos, PlayerEntity entity, Hand hand, BlockHitResult hit) {
        super.onUse(blockstate, world, pos, entity, hand, hit);
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        if (consumers.size() == 0) initConsumers();
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
            SoundUtil.playSound(world, x, y, z, new Identifier("block.portal.travel"), 1, 1);
            if (!world.isClient() && world.getServer() != null)
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

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new DarkObsidianBlockEntity(pos, state);
    }
}