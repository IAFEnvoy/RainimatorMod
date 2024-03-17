package com.rainimator.rainimatormod.block;

import com.rainimator.rainimatormod.entity.*;
import com.rainimator.rainimatormod.registry.ModBlocks;
import com.rainimator.rainimatormod.registry.ModEntities;
import com.rainimator.rainimatormod.registry.ModItems;
import com.rainimator.rainimatormod.util.Consumer5;
import com.rainimator.rainimatormod.util.MiscUtil;
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
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.Pillager;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
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
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.DARKOBSIDIANBLOCK.get(), renderType -> (renderType == RenderType.cutout()));
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
            MiscUtil.playSound(world, x, y, z, new ResourceLocation("block.portal.travel"), 1, 1);
            if (!world.isClientSide() && world.getServer() != null)
                world.getServer().getPlayerList().broadcastMessage(new TranslatableComponent("block.rainimator.darkobsidianblock.running"), ChatType.SYSTEM, Util.NIL_UUID);
            ItemStack _setstack = new ItemStack(Blocks.AIR);
            _setstack.setCount(1);
            entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
            entity.getInventory().setChanged();
            if (world instanceof ServerLevel _level)
                Timeout.create(60, () -> consumers.get(used).accept(entity, _level, x, y, z));
        }
        return InteractionResult.SUCCESS;
    }

    public static synchronized void initConsumers() {
        consumers.put(ModItems.LIGHTHEART.get(), (entity, world, x, y, z) -> {
            world.sendParticles(ParticleTypes.END_ROD, x, y, z, 200, 1, 2, 1, 0.05);
            Mob entityToSpawn1 = new HerobrineEntity(ModEntities.HEROBRINE.get(), world);
            entityToSpawn1.moveTo(x, y, z, world.getRandom().nextFloat() * 360F, 0);
            entityToSpawn1.finalizeSpawn(world, world.getCurrentDifficultyAt(entityToSpawn1.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
            world.addFreshEntity(entityToSpawn1);
            Mob entityToSpawn2 = new ZombiesEntity(ModEntities.ZOMBIES.get(), world);
            entityToSpawn2.moveTo(x, y, ((double) z - Mth.nextInt(new Random(), 1, 3)), world.getRandom().nextFloat() * 360F, 0);
            entityToSpawn2.finalizeSpawn(world, world.getCurrentDifficultyAt(entityToSpawn2.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
            world.addFreshEntity(entityToSpawn2);
            Mob entityToSpawn3 = new DarkZombieEntity(ModEntities.DARKZOMBIE.get(), world);
            entityToSpawn3.moveTo(x, y, ((double) z + Mth.nextInt(new Random(), 1, 3)), world.getRandom().nextFloat() * 360F, 0);
            entityToSpawn3.finalizeSpawn(world, world.getCurrentDifficultyAt(entityToSpawn3.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
            world.addFreshEntity(entityToSpawn3);
            Mob entityToSpawn4 = new AzaleaEntity(ModEntities.AZALEA.get(), world);
            entityToSpawn4.moveTo(((double) x + Mth.nextInt(new Random(), 1, 3)), y, z, world.getRandom().nextFloat() * 360F, 0);
            entityToSpawn4.finalizeSpawn(world, world.getCurrentDifficultyAt(entityToSpawn4.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
            world.addFreshEntity(entityToSpawn4);
        });
        consumers.put(Blocks.WITHER_ROSE.asItem(), (entity, world, x, y, z) -> {
            ItemStack _stktoremove = new ItemStack(Blocks.WITHER_ROSE);
            entity.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, entity.inventoryMenu.getCraftSlots());
            world.sendParticles(ParticleTypes.END_ROD, x, y, z, 200, 1, 2, 1, 0.05);
            Mob entityToSpawn1 = new KralosEntity(ModEntities.KRALOS.get(), world);
            entityToSpawn1.moveTo(x, y, z, world.getRandom().nextFloat() * 360F, 0);
            entityToSpawn1.finalizeSpawn(world, world.getCurrentDifficultyAt(entityToSpawn1.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
            world.addFreshEntity(entityToSpawn1);
        });
        consumers.put(Items.TOTEM_OF_UNDYING, (entity, world, x, y, z) -> {
            world.sendParticles(ParticleTypes.END_ROD, x, y, z, 200, 1, 2, 1, 0.05);
            Mob entityToSpawn1 = new KlausEntity(ModEntities.KLAUS.get(), world);
            entityToSpawn1.moveTo(x, y, z, world.getRandom().nextFloat() * 360F, 0);
            entityToSpawn1.finalizeSpawn(world, world.getCurrentDifficultyAt(entityToSpawn1.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
            world.addFreshEntity(entityToSpawn1);
            Mob entityToSpawn2 = new Pillager(EntityType.PILLAGER, world);
            entityToSpawn2.moveTo(x, y, ((double) z - Mth.nextInt(new Random(), 1, 3)), world.getRandom().nextFloat() * 360F, 0);
            entityToSpawn2.finalizeSpawn(world, world.getCurrentDifficultyAt(entityToSpawn2.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
            world.addFreshEntity(entityToSpawn2);
            Mob entityToSpawn3 = new Pillager(EntityType.PILLAGER, world);
            entityToSpawn3.moveTo(x, y, ((double) z + Mth.nextInt(new Random(), 1, 3)), world.getRandom().nextFloat() * 360F, 0);
            entityToSpawn3.finalizeSpawn(world, world.getCurrentDifficultyAt(entityToSpawn3.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
            world.addFreshEntity(entityToSpawn3);
            Mob entityToSpawn4 = new Pillager(EntityType.PILLAGER, world);
            entityToSpawn4.moveTo(((double) x + Mth.nextInt(new Random(), 1, 3)), y, z, world.getRandom().nextFloat() * 360F, 0);
            entityToSpawn4.finalizeSpawn(world, world.getCurrentDifficultyAt(entityToSpawn4.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
            world.addFreshEntity(entityToSpawn4);
            Mob entityToSpawn5 = new Pillager(EntityType.PILLAGER, world);
            entityToSpawn5.moveTo(((double) x - Mth.nextInt(new Random(), 1, 3)), y, z, world.getRandom().nextFloat() * 360F, 0);
            entityToSpawn5.finalizeSpawn(world, world.getCurrentDifficultyAt(entityToSpawn5.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
            world.addFreshEntity(entityToSpawn5);
        });
        consumers.put(Blocks.WITHER_SKELETON_SKULL.asItem(), (entity, world, x, y, z) -> {
            ItemStack _stktoremove = new ItemStack(Blocks.WITHER_SKELETON_SKULL);
            entity.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, entity.inventoryMenu.getCraftSlots());
            world.sendParticles(ParticleTypes.END_ROD, x, y, z, 200, 1, 2, 1, 0.05);
            Mob entityToSpawn1 = new GigaBoneEntity(ModEntities.GIGABONE.get(), world);
            entityToSpawn1.moveTo(x, y, z, world.getRandom().nextFloat() * 360F, 0);
            entityToSpawn1.finalizeSpawn(world, world.getCurrentDifficultyAt(entityToSpawn1.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
            world.addFreshEntity(entityToSpawn1);
        });
        consumers.put(ModItems.SOULPEOPLE.get(), (entity, world, x, y, z) -> {
            ItemStack _stktoremove = new ItemStack(ModItems.SOULPEOPLE.get());
            entity.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, entity.inventoryMenu.getCraftSlots());
            world.sendParticles(ParticleTypes.END_ROD, x, y, z, 200, 1, 2, 1, 0.05);
            Mob entityToSpawn1 = new NamtarEntity(ModEntities.NAMTAR.get(), world);
            entityToSpawn1.moveTo(x, y, z, world.getRandom().nextFloat() * 360F, 0);
            entityToSpawn1.finalizeSpawn(world, world.getCurrentDifficultyAt(entityToSpawn1.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
            world.addFreshEntity(entityToSpawn1);
            Mob entityToSpawn2 = new ZombiePiglinArtEntity(ModEntities.ZOMBIEPIGLINART.get(), world);
            entityToSpawn2.moveTo(x, y, ((double) z - Mth.nextInt(new Random(), 1, 3)), world.getRandom().nextFloat() * 360F, 0);
            entityToSpawn2.finalizeSpawn(world, world.getCurrentDifficultyAt(entityToSpawn2.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
            world.addFreshEntity(entityToSpawn2);
            Mob entityToSpawn3 = new MutatedEntity(ModEntities.MUTATED.get(), world);
            entityToSpawn3.moveTo(x, y, ((double) z + Mth.nextInt(new Random(), 1, 3)), world.getRandom().nextFloat() * 360F, 0);
            entityToSpawn3.finalizeSpawn(world, world.getCurrentDifficultyAt(entityToSpawn3.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
            world.addFreshEntity(entityToSpawn3);
            Mob entityToSpawn4 = new SkeletonSnowEntity(ModEntities.SKELETONSNOW.get(), world);
            entityToSpawn4.moveTo(((double) x + Mth.nextInt(new Random(), 1, 3)), y, z, world.getRandom().nextFloat() * 360F, 0);
            entityToSpawn4.finalizeSpawn(world, world.getCurrentDifficultyAt(entityToSpawn4.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
            world.addFreshEntity(entityToSpawn4);
            Mob entityToSpawn5 = new WitherShieldEntity(ModEntities.WITHERSHIELD.get(), world);
            entityToSpawn5.moveTo(((double) x - Mth.nextInt(new Random(), 1, 3)), y, z, world.getRandom().nextFloat() * 360F, 0);
            entityToSpawn5.finalizeSpawn(world, world.getCurrentDifficultyAt(entityToSpawn5.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
            world.addFreshEntity(entityToSpawn5);
        });
        consumers.put(ModItems.WITHER_BONE.get(), (entity, world, x, y, z) -> {
            ItemStack _stktoremove = new ItemStack(ModItems.WITHER_BONE.get());
            entity.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, entity.inventoryMenu.getCraftSlots());
            world.sendParticles(ParticleTypes.END_ROD, x, y, z, 200, 1, 2, 1, 0.05);
            Mob entityToSpawn1 = new BiGunDeadSkeletonEntity(ModEntities.BIGUNDEADSKELETON.get(), world);
            entityToSpawn1.moveTo(x, y, z, world.getRandom().nextFloat() * 360F, 0);
            entityToSpawn1.finalizeSpawn(world, world.getCurrentDifficultyAt(entityToSpawn1.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
            world.addFreshEntity(entityToSpawn1);
        });
        consumers.put(Items.GOLDEN_SWORD, (entity, world, x, y, z) -> {
            world.sendParticles(ParticleTypes.END_ROD, x, y, z, 200, 1, 2, 1, 0.05);
            Mob entityToSpawn1 = new PiglinCommanderEntity(ModEntities.PIGLINCOMMANDER.get(), world);
            entityToSpawn1.moveTo(x, y, z, world.getRandom().nextFloat() * 360F, 0);
            entityToSpawn1.finalizeSpawn(world, world.getCurrentDifficultyAt(entityToSpawn1.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
            world.addFreshEntity(entityToSpawn1);
        });
        consumers.put(Items.GOLD_INGOT, (entity, world, x, y, z) -> {
            world.sendParticles(ParticleTypes.END_ROD, x, y, z, 200, 1, 2, 1, 0.05);
            ItemStack _stktoremove = new ItemStack(Items.GOLD_INGOT);
            entity.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, entity.inventoryMenu.getCraftSlots());
            Mob entityToSpawn1 = new ZombiesPiglinKingEntity(ModEntities.ZOMBIESPLIGEKING.get(), world);
            entityToSpawn1.moveTo(x, y, z, world.getRandom().nextFloat() * 360F, 0);
            entityToSpawn1.finalizeSpawn(world, world.getCurrentDifficultyAt(entityToSpawn1.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
            world.addFreshEntity(entityToSpawn1);
            Mob entityToSpawn2 = new ZombifiedPiglin(EntityType.ZOMBIFIED_PIGLIN, world);
            entityToSpawn2.moveTo(x, y, ((double) z + Mth.nextInt(new Random(), 1, 3)), world.getRandom().nextFloat() * 360F, 0);
            entityToSpawn2.finalizeSpawn(world, world.getCurrentDifficultyAt(entityToSpawn2.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
            world.addFreshEntity(entityToSpawn2);

        });
        consumers.put(Blocks.GOLD_BLOCK.asItem(), (entity, world, x, y, z) -> {
            world.sendParticles(ParticleTypes.END_ROD, x, y, z, 200, 1, 2, 1, 0.05);
            Mob entityToSpawn1 = new PiglinKingZombiesEntity(ModEntities.PILGEKINGZOMBIES.get(), world);
            entityToSpawn1.moveTo(x, y, z, world.getRandom().nextFloat() * 360F, 0);
            entityToSpawn1.finalizeSpawn(world, world.getCurrentDifficultyAt(entityToSpawn1.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
            world.addFreshEntity(entityToSpawn1);
            Mob entityToSpawn2 = new ZombiePiglinArtEntity(ModEntities.ZOMBIEPIGLINART.get(), world);
            entityToSpawn2.moveTo(x, y, ((double) z - Mth.nextInt(new Random(), 1, 3)), world.getRandom().nextFloat() * 360F, 0);
            entityToSpawn2.finalizeSpawn(world, world.getCurrentDifficultyAt(entityToSpawn2.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
            world.addFreshEntity(entityToSpawn2);
            Mob entityToSpawn3 = new ZombifiedPiglin(EntityType.ZOMBIFIED_PIGLIN, world);
            entityToSpawn3.moveTo(x, y, ((double) z + Mth.nextInt(new Random(), 1, 3)), world.getRandom().nextFloat() * 360F, 0);
            entityToSpawn3.finalizeSpawn(world, world.getCurrentDifficultyAt(entityToSpawn3.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
            world.addFreshEntity(entityToSpawn3);
            ItemStack _stktoremove = new ItemStack(Blocks.GOLD_BLOCK);
            entity.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, entity.inventoryMenu.getCraftSlots());
        });
        consumers.put(ModItems.BAOZHU.get(), (entity, world, x, y, z) -> {
            world.sendParticles(ParticleTypes.END_ROD, x, y, z, 200, 1, 2, 1, 0.05);
            Mob entityToSpawn1 = new NullLikeEntity(ModEntities.NULLLIKE.get(), world);
            entityToSpawn1.moveTo(x, y, z, world.getRandom().nextFloat() * 360F, 0);
            entityToSpawn1.finalizeSpawn(world, world.getCurrentDifficultyAt(entityToSpawn1.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
            world.addFreshEntity(entityToSpawn1);
        });
        consumers.put(ModItems.WARRIORHEART.get(), (entity, world, x, y, z) -> {
            world.sendParticles(ParticleTypes.END_ROD, x, y, z, 200, 1, 2, 1, 0.05);
            Mob entityToSpawn1 = new NaeusEntity(ModEntities.NAEUS.get(), world);
            entityToSpawn1.moveTo(x, y, z, world.getRandom().nextFloat() * 360F, 0);
            entityToSpawn1.finalizeSpawn(world, world.getCurrentDifficultyAt(entityToSpawn1.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
            world.addFreshEntity(entityToSpawn1);
            Mob entityToSpawn2 = new WitheredSkeletonsEntity(ModEntities.WITHERED_SKELETONS.get(), world);
            entityToSpawn2.moveTo(x, y, ((double) z - Mth.nextInt(new Random(), 1, 3)), world.getRandom().nextFloat() * 360F, 0);
            entityToSpawn2.finalizeSpawn(world, world.getCurrentDifficultyAt(entityToSpawn2.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
            world.addFreshEntity(entityToSpawn2);
            Mob entityToSpawn3 = new HogsworthEntity(ModEntities.HOGSWORTH.get(), world);
            entityToSpawn3.moveTo(x, y, ((double) z + Mth.nextInt(new Random(), 1, 3)), world.getRandom().nextFloat() * 360F, 0);
            entityToSpawn3.finalizeSpawn(world, world.getCurrentDifficultyAt(entityToSpawn3.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
            world.addFreshEntity(entityToSpawn3);
        });
        consumers.put(ModItems.ICEHEART.get(), (entity, world, x, y, z) -> {
            world.sendParticles(ParticleTypes.END_ROD, x, y, z, 200, 1, 2, 1, 0.05);
            Mob entityToSpawn1 = new RainEntity(ModEntities.RAIN.get(), world);
            entityToSpawn1.moveTo(x, y, z, world.getRandom().nextFloat() * 360F, 0);
            entityToSpawn1.finalizeSpawn(world, world.getCurrentDifficultyAt(entityToSpawn1.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
            world.addFreshEntity(entityToSpawn1);
            Mob entityToSpawn2 = new CiaraEntity(ModEntities.CIARA.get(), world);
            entityToSpawn2.moveTo(x, y, ((double) z + Mth.nextInt(new Random(), 1, 3)), world.getRandom().nextFloat() * 360F, 0);
            entityToSpawn2.finalizeSpawn(world, world.getCurrentDifficultyAt(entityToSpawn2.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
            world.addFreshEntity(entityToSpawn2);
            Mob entityToSpawn3 = new DaryllEntity(ModEntities.DARYLL.get(), world);
            entityToSpawn3.moveTo(x, y, ((double) z - Mth.nextInt(new Random(), 1, 3)), world.getRandom().nextFloat() * 360F, 0);
            entityToSpawn3.finalizeSpawn(world, world.getCurrentDifficultyAt(entityToSpawn3.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
            world.addFreshEntity(entityToSpawn3);
        });
        consumers.put(ModItems.ENDERHEART.get(), (entity, world, x, y, z) -> {
            world.sendParticles(ParticleTypes.END_ROD, x, y, z, 200, 1, 2, 1, 0.05);
            Mob entityToSpawn1 = new CerisEntity(ModEntities.CERIS.get(), world);
            entityToSpawn1.moveTo(x, y, z, world.getRandom().nextFloat() * 360F, 0);
            entityToSpawn1.finalizeSpawn(world, world.getCurrentDifficultyAt(entityToSpawn1.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
            world.addFreshEntity(entityToSpawn1);
            Mob entityToSpawn2 = new DarkShieldEntity(ModEntities.DARKSHIELD.get(), world);
            entityToSpawn2.moveTo(((double) x + Mth.nextInt(new Random(), 1, 3)), y, z, world.getRandom().nextFloat() * 360F, 0);
            entityToSpawn2.finalizeSpawn(world, world.getCurrentDifficultyAt(entityToSpawn2.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
            world.addFreshEntity(entityToSpawn2);
            Mob entityToSpawn3 = new DarkShieldEntity(ModEntities.DARKSHIELD.get(), world);
            entityToSpawn3.moveTo(((double) x - Mth.nextInt(new Random(), 1, 3)), y, z, world.getRandom().nextFloat() * 360F, 0);
            entityToSpawn3.finalizeSpawn(world, world.getCurrentDifficultyAt(entityToSpawn3.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
            world.addFreshEntity(entityToSpawn3);
        });
        consumers.put(ModItems.MAGIC_STARD.get(), (entity, world, x, y, z) -> {
            world.sendParticles(ParticleTypes.END_ROD, x, y, z, 200, 1, 2, 1, 0.05);
            Mob entityToSpawn1 = new PatrickEntity(ModEntities.PATRICK.get(), world);
            entityToSpawn1.moveTo(x, y, z, world.getRandom().nextFloat() * 360F, 0);
            entityToSpawn1.finalizeSpawn(world, world.getCurrentDifficultyAt(entityToSpawn1.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
            world.addFreshEntity(entityToSpawn1);
            Mob entityToSpawn2 = new HildaEntity(ModEntities.HILDA.get(), world);
            entityToSpawn2.moveTo(((double) x - Mth.nextInt(new Random(), 1, 3)), y, z, world.getRandom().nextFloat() * 360F, 0);
            entityToSpawn2.finalizeSpawn(world, world.getCurrentDifficultyAt(entityToSpawn2.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
            world.addFreshEntity(entityToSpawn2);
            Mob entityToSpawn3 = new SoldiersEntity(ModEntities.SOLDIERS.get(), world);
            entityToSpawn3.moveTo(((double) x + Mth.nextInt(new Random(), 1, 3)), y, z, world.getRandom().nextFloat() * 360F, 0);
            entityToSpawn3.finalizeSpawn(world, world.getCurrentDifficultyAt(entityToSpawn3.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
            world.addFreshEntity(entityToSpawn3);
        });
        consumers.put(ModItems.LOWER_BOUND_ALLOY_BONE.get(), (entity, world, x, y, z) -> {
            world.sendParticles(ParticleTypes.END_ROD, x, y, z, 200, 1, 2, 1, 0.05);
            Mob entityToSpawn1 = new BlackBoneEntity(ModEntities.BLACKBONE.get(), world);
            entityToSpawn1.moveTo(x, y, z, world.getRandom().nextFloat() * 360F, 0);
            entityToSpawn1.finalizeSpawn(world, world.getCurrentDifficultyAt(entityToSpawn1.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
            world.addFreshEntity(entityToSpawn1);
        });
        consumers.put(ModItems.UNDERFLOWER.get(), (entity, world, x, y, z) -> {
            world.sendParticles(ParticleTypes.END_ROD, x, y, z, 200, 1, 2, 1, 0.05);
            Mob entityToSpawn1 = new AbigailEntity(ModEntities.ABIGAIL.get(), world);
            entityToSpawn1.moveTo(x, y, z, world.getRandom().nextFloat() * 360F, 0);
            entityToSpawn1.finalizeSpawn(world, world.getCurrentDifficultyAt(entityToSpawn1.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
            world.addFreshEntity(entityToSpawn1);
        });
    }
}