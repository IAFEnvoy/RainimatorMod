package com.rainimator.rainimatormod.item.sword;

import com.rainimator.rainimatormod.RainimatorMod;
import com.rainimator.rainimatormod.registry.ModItems;
import com.rainimator.rainimatormod.registry.ModParticleTypes;
import com.rainimator.rainimatormod.registry.util.IRainimatorInfo;
import com.rainimator.rainimatormod.registry.util.ModCreativeTab;
import com.rainimator.rainimatormod.registry.util.SwordItemBase;
import com.rainimator.rainimatormod.registry.util.TierBase;
import com.rainimator.rainimatormod.util.Episode;
import com.rainimator.rainimatormod.util.ParticleUtil;
import com.rainimator.rainimatormod.util.SoundUtil;
import com.rainimator.rainimatormod.util.Timeout;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class BlueDiamondSwordItem extends SwordItemBase implements IRainimatorInfo {
    public BlueDiamondSwordItem() {
        super(TierBase.of(3000, 4.0F, 15.0F, 0, 30, ModItems.BLUE_DIAMOND), 3, -2.0F, ModCreativeTab.createProperty().fireResistant());
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack itemstack, @NotNull LivingEntity entity, @NotNull LivingEntity sourceentity) {
        boolean retval = super.hurtEnemy(itemstack, entity, sourceentity);
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();
        if (entity instanceof LivingEntity)
            if (entity.hasEffect(MobEffects.GLOWING) && Math.random() < 0.4D)
                entity.hurt(DamageSource.MAGIC, 5.0F);
        if (Math.random() < 0.1D) {
            if (entity.level instanceof ServerLevel _level) {
                LightningBolt entityToSpawn = EntityType.LIGHTNING_BOLT.create(_level);
                if (entityToSpawn != null) {
                    entityToSpawn.moveTo(Vec3.atBottomCenterOf(new BlockPos(x, y, z)));
                    entityToSpawn.setVisualOnly(true);
                    _level.addFreshEntity(entityToSpawn);
                }
            }
            entity.level.setBlock(new BlockPos(x, y, z), Blocks.FIRE.defaultBlockState(), 3);
        }

        return retval;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level world, @NotNull Player entity, @NotNull InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();
        ItemStack itemstack = ar.getObject();
        final Vec3 _center = new Vec3(x, y, z);
        List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(16 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
        for (Entity entityiterator : _entfound) {
            if (entity.isShiftKeyDown()) {
                if ((entityiterator instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == ModItems.BLUE_DIAMOND_SWORD.get()) {
                    if (itemstack.hurt(0, new Random(), null)) {
                        itemstack.shrink(1);
                        itemstack.setDamageValue(0);
                    }
                } else {
                    if (entityiterator instanceof LivingEntity _livEnt && _livEnt.getMobType() == MobType.ARTHROPOD) {
                        if (world instanceof ServerLevel _level)
                            _level.sendParticles(ParticleTypes.END_ROD, x, y, z, 50, 0.5, 0.5, 0.5, 0.2);
                        SoundUtil.playSound(world, x, y, z, new ResourceLocation(RainimatorMod.MOD_ID, "blued_diamond_skill_1"), 5, 1);
                        if (!_livEnt.level.isClientSide())
                            _livEnt.addEffect(new MobEffectInstance(MobEffects.GLOWING, 1200, 0));
                        entityiterator.setSecondsOnFire(10);
                        entity.getCooldowns().addCooldown(itemstack.getItem(), 1000);
                        if (!entity.level.isClientSide())
                            entity.displayClientMessage(new TranslatableComponent("item.rainimator.blue_diamond_sword.skill1"), true);
                        if (world.isClientSide())
                            Minecraft.getInstance().gameRenderer.displayItemActivation(itemstack);
                        Runnable callback = () -> {
                            if (entityiterator.isAlive()) {
                                if (world instanceof ServerLevel _level) {
                                    LightningBolt entityToSpawn = EntityType.LIGHTNING_BOLT.create(_level);
                                    if (entityToSpawn != null) {
                                        entityToSpawn.moveTo(Vec3.atBottomCenterOf(new BlockPos(entityiterator.getX(), entityiterator.getY(), entityiterator.getZ())));
                                        entityToSpawn.setVisualOnly(true);
                                        _level.addFreshEntity(entityToSpawn);
                                    }
                                }
                                world.setBlock(new BlockPos(entityiterator.getX(), entityiterator.getY(), entityiterator.getZ()), Blocks.FIRE.defaultBlockState(), 3);
                                entityiterator.hurt(DamageSource.MAGIC, 5);
                            }
                        };
                        Timeout.create(40, callback);
                        Timeout.create(80, callback);
                        Timeout.create(120, callback);
                        Timeout.create(160, callback);
                        Timeout.create(200, callback);
                    } else if ((entityiterator instanceof LivingEntity _livEnt && _livEnt.getMobType() == MobType.UNDEFINED)) {
                        if ((LevelAccessor) world instanceof ServerLevel _level)
                            _level.sendParticles(ParticleTypes.END_ROD, x, y, z, 50, 0.5, 0.5, 0.5, 0.2);
                        SoundUtil.playSound(world, x, y, z, new ResourceLocation(RainimatorMod.MOD_ID, "blue_diamond_skill_2"), 5, 1);
                        if (!_livEnt.level.isClientSide())
                            _livEnt.addEffect(new MobEffectInstance(MobEffects.GLOWING, 1200, 0));
                        entityiterator.setSecondsOnFire(10);
                        entity.getCooldowns().addCooldown(itemstack.getItem(), 1000);
                        if (!entity.level.isClientSide())
                            entity.displayClientMessage(new TranslatableComponent("item.rainimator.blue_diamond_sword.skill2"), true);
                        if (((LevelAccessor) world).isClientSide())
                            Minecraft.getInstance().gameRenderer.displayItemActivation(itemstack);

                        Runnable callback1 = () -> {
                            if (entityiterator.isAlive()) {
                                if (!world.isClientSide())
                                    world.explode(null, (entityiterator.getX()), (entityiterator.getY()), (entityiterator.getZ()), 2, Explosion.BlockInteraction.NONE);
                                entityiterator.hurt(DamageSource.MAGIC, 5);
                            }
                        };
                        Timeout.create(40, callback1);
                        Timeout.create(80, callback1);
                        Timeout.create(120, callback1);
                        Timeout.create(160, callback1);
                        Timeout.create(200, callback1);
                    } else if (entityiterator instanceof LivingEntity _livEnt && (_livEnt.getMobType() == MobType.UNDEAD || _livEnt.getMobType() == MobType.ILLAGER)) {
                        entityiterator.setSecondsOnFire(10);
                        if (!_livEnt.level.isClientSide()) {
                            _livEnt.addEffect(new MobEffectInstance(MobEffects.GLOWING, 1200, 0));
                            _livEnt.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 160, 2));
                            _livEnt.addEffect(new MobEffectInstance(MobEffects.WITHER, 160, 0));
                        }
                        SoundUtil.playSound(world, x, y, z, new ResourceLocation(RainimatorMod.MOD_ID, "blue_diamond_skill_3"), 5, 1);
                        if (world instanceof ServerLevel _level)
                            _level.sendParticles(ParticleTypes.END_ROD, x, y, z, 50, 0.5, 0.5, 0.5, 0.2);
                        entity.getCooldowns().addCooldown(itemstack.getItem(), 1000);
                        if (!entity.level.isClientSide())
                            entity.displayClientMessage(new TranslatableComponent("item.rainimator.blue_diamond_sword.skill3"), true);
                        if (world.isClientSide())
                            Minecraft.getInstance().gameRenderer.displayItemActivation(itemstack);
                        Runnable callback1 = () -> {
                            if (world instanceof ServerLevel _level)
                                _level.sendParticles((SimpleParticleType) ModParticleTypes.FLOWERWRITE.get(), entityiterator.getX(), entityiterator.getY(), entityiterator.getZ(), 50, 0.5, 2, 0.5, 0.2);
                        };
                        Runnable callback2 = () -> {
                            entityiterator.teleportTo(x + Mth.nextDouble(new Random(), -1, 1), y + 2, z + Mth.nextDouble(new Random(), -1, 1));
                            if (entityiterator instanceof ServerPlayer _serverPlayer)
                                _serverPlayer.connection.teleport(x + Mth.nextDouble(new Random(), -1, 1), y + 2, z + Mth.nextDouble(new Random(), -1, 1), entityiterator.getYRot(), entityiterator.getXRot());
                        };
                        Runnable callback3 = () -> {
                            SoundUtil.playSound(world, x, y, z, new ResourceLocation(RainimatorMod.MOD_ID, "blue_diamond_skill_4"), 5, 1);
                            callback1.run();
                            entityiterator.teleportTo(x + Mth.nextDouble(new Random(), -1, 1), y + 2, z + Mth.nextDouble(new Random(), -1, 1));
                            if (entityiterator instanceof ServerPlayer _serverPlayer)
                                _serverPlayer.connection.teleport(x + Mth.nextDouble(new Random(), -1, 1), y + 2, z + Mth.nextDouble(new Random(), -1, 1), entityiterator.getYRot(), entityiterator.getXRot());
                        };
                        Timeout.create(20, callback3);
                        Timeout.create(40, callback3);
                        Timeout.create(60, callback1);
                        Timeout.create(80, callback1);
                        Timeout.create(100, callback1);
                        Timeout.create(120, callback1);
                        Timeout.create(140, callback1);
                        Timeout.create(155, callback2);
                        Timeout.create(160, () -> {
                            if (entityiterator.isAlive()) {
                                if (world instanceof ServerLevel _level) {
                                    LightningBolt entityToSpawn = EntityType.LIGHTNING_BOLT.create(_level);
                                    if (entityToSpawn != null) {
                                        entityToSpawn.moveTo(Vec3.atBottomCenterOf(new BlockPos(entityiterator.getX(), entityiterator.getY(), entityiterator.getZ())));
                                        entityToSpawn.setVisualOnly(true);
                                        _level.addFreshEntity(entityToSpawn);
                                    }
                                }
                                if (!world.isClientSide())
                                    world.explode(null, (entityiterator.getX()), (entityiterator.getY()), (entityiterator.getZ()), 8, Explosion.BlockInteraction.NONE);
                                if (world instanceof ServerLevel _level)
                                    _level.sendParticles((SimpleParticleType) (ModParticleTypes.LIGHTINGARC.get()), (entityiterator.getX()), (entityiterator.getY()), (entityiterator.getZ()), 50, 0.5, 1, 0.5, 0.2);
                            }
                        });
                    }
                }
            }
        }
        return ar;
    }

    @Override
    public boolean onEntitySwing(ItemStack itemstack, LivingEntity entity) {
        boolean retval = super.onEntitySwing(itemstack, entity);
        if (Math.random() < 0.2D)
            ParticleUtil.spawnCircleParticles(entity.level, ParticleTypes.SOUL_FIRE_FLAME, entity.getX(), entity.getY(), entity.getZ(), 2, 0, 50);
        return retval;
    }

    @Override
    public Episode getEpisode() {
        return Episode.Unknown;
    }
}