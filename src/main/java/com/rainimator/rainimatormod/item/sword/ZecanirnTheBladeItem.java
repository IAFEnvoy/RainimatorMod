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
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
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
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class ZecanirnTheBladeItem extends SwordItemBase implements IRainimatorInfo {
    public ZecanirnTheBladeItem() {
        super(TierBase.of(1500, 4.0F, 11.0F, 0, 20, ModItems.SUPER_SAPPHIRE, ModItems.SUPER_RUBY), 3, -2.2F, ModCreativeTab.createProperty());
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack itemstack, @NotNull LivingEntity entity, @NotNull LivingEntity sourceentity) {
        boolean ret_val = super.hurtEnemy(itemstack, entity, sourceentity);
        if (entity instanceof Mob _entity)
            _entity.setTarget(sourceentity);
        if (sourceentity.getMainHandItem().getItem() == ModItems.ZECANIRN_THE_BLADE.get())
            if (Math.random() < 0.9D) {
                if (entity.level instanceof ServerLevel _level)
                    _level.sendParticles((ParticleOptions) ModParticleTypes.PURPLELIGHT.get(), entity.getX(), entity.getY(), entity.getZ(), 50, 0.5D, 1.0D, 0.5D, 0.2D);
                if (Math.random() < 0.5D)
                    SoundUtil.playSound(entity.level, entity.getX(), entity.getY(), entity.getZ(), new ResourceLocation(RainimatorMod.MOD_ID, "sword_teleport1"), 4.0F, 1.0F);
                else if (Math.random() < 0.5D)
                    SoundUtil.playSound(entity.level, entity.getX(), entity.getY(), entity.getZ(), new ResourceLocation(RainimatorMod.MOD_ID, "sword_teleport2"), 4.0F, 1.0F);
                else if (Math.random() < 0.5D)
                    SoundUtil.playSound(entity.level, entity.getX(), entity.getY(), entity.getZ(), new ResourceLocation(RainimatorMod.MOD_ID, "sword_teleport3"), 4.0F, 1.0F);
                else
                    SoundUtil.playSound(entity.level, entity.getX(), entity.getY(), entity.getZ(), new ResourceLocation(RainimatorMod.MOD_ID, "sword_teleport4"), 4.0F, 1.0F);

                sourceentity.teleportTo(entity.level.clip(new ClipContext(entity.getEyePosition(1.0F), entity.getEyePosition(1.0F).add(entity.getViewVector(1.0F).scale(-2.0D)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getX(), entity.getY() + 0.5D, entity.level
                        .clip(new ClipContext(entity.getEyePosition(1.0F), entity.getEyePosition(1.0F).add(entity.getViewVector(1.0F).scale(-2.0D)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getZ());
                if (sourceentity instanceof ServerPlayer _serverPlayer) {
                    _serverPlayer.connection.teleport(entity.level
                            .clip(new ClipContext(entity.getEyePosition(1.0F), entity.getEyePosition(1.0F).add(entity.getViewVector(1.0F).scale(-2.0D)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getX(), entity.getY() + 0.5D, entity.level
                            .clip(new ClipContext(entity.getEyePosition(1.0F), entity.getEyePosition(1.0F).add(entity.getViewVector(1.0F).scale(-2.0D)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getZ(), sourceentity
                            .getYRot(), sourceentity.getXRot());
                }

                entity.level.destroyBlock(new BlockPos(sourceentity.getX(), entity.getY(), sourceentity.getZ()), false);
                entity.level.destroyBlock(new BlockPos(sourceentity.getX(), entity.getY() + 1.0D, sourceentity.getZ()), false);
                entity.level.destroyBlock(new BlockPos(sourceentity.getX(), entity.getY() + 2.0D, sourceentity.getZ()), false);

                if (itemstack.hurt(Mth.nextInt(new Random(), 1, 5), new Random(), null)) {
                    itemstack.shrink(1);
                    itemstack.setDamageValue(0);
                }
            } else {
                entity.hurt(DamageSource.MAGIC, 3.0F);
                if (entity instanceof LivingEntity) {
                    if (!entity.level.isClientSide())
                        entity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 100, 0));
                }

                if (itemstack.hurt(Mth.nextInt(new Random(), 1, 5), new Random(), null)) {
                    itemstack.shrink(1);
                    itemstack.setDamageValue(0);
                }
                Timeout.create(100, () -> {
                    if (!entity.level.isClientSide())
                        entity.level.explode(null, entity.getX(), entity.getY() + 1.0D, entity.getZ(), 1.0F, Explosion.BlockInteraction.NONE);
                });
            }
        return ret_val;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level world, @NotNull Player entity, @NotNull InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);

        Vec3 _center = entity.position();
        List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, (new AABB(_center, _center)).inflate(8.0D), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
        for (Entity entityiterator : _entfound) {
            if (!(entityiterator instanceof LivingEntity _livEnt)) continue;
            if (_livEnt.getMainHandItem().getItem() == ModItems.ZECANIRN_THE_BLADE.get()) {
                if (entity instanceof Player)
                    entity.getCooldowns().addCooldown(ar.getObject().getItem(), 0);
                continue;
            }
            if (entity.isShiftKeyDown()) {
                if (entity.getOffhandItem().getItem() == ModItems.ENDER_STONE.get()) {
                    if (entity.getHealth() > 5.0F) {
                        entity.hurt(DamageSource.MAGIC, 5.0F);
                        ItemStack _setstack = new ItemStack(Blocks.AIR);
                        _setstack.setCount(1);
                        entity.setItemInHand(InteractionHand.OFF_HAND, _setstack);
                        entity.getInventory().setChanged();
                        entityiterator.hurt(DamageSource.MAGIC, 10.0F);
                        if (world instanceof ServerLevel _level)
                            _level.sendParticles((ParticleOptions) ModParticleTypes.PURPLELIGHT.get(), entity.getX(), entity.getY(), entity.getZ(), 40, 0.5D, 0.5D, 0.5D, 0.5D);
                        SoundUtil.playSound(world, entity.getX(), entity.getY(), entity.getZ(), new ResourceLocation(RainimatorMod.MOD_ID, "black_death_sword_skills"), 4.0F, 1.0F);

                        Runnable callback = () -> {
                            entity.teleportTo(entity.getX(), entity.getY(), entity.getZ());
                            if ((Entity) entity instanceof ServerPlayer _serverPlayer)
                                _serverPlayer.connection.teleport(entity.getX(), entity.getY(), entity.getZ(), entity.getYRot(), entity.getXRot());

                            entityiterator.hurt(DamageSource.MAGIC, 15.0F);
                            if (world instanceof ServerLevel _level) {
                                for (double z = -7; z <= 7; z++)
                                    _level.sendParticles((ParticleOptions) ModParticleTypes.PURPLELIGHT.get(), entity.getX(), entity.getY(), entity.getZ() + z, 20, 0.2D, 0.5D, 0.2D, 0.2D);
                                for (double x = -7; x <= 7; x++)
                                    _level.sendParticles((ParticleOptions) ModParticleTypes.PURPLELIGHT.get(), entity.getX() + x, entity.getY(), entity.getZ(), 20, 0.2D, 0.5D, 0.2D, 0.2D);
                            }
                            SoundUtil.playSound(world, entity.getX(), entity.getY(), entity.getZ(), new ResourceLocation(RainimatorMod.MOD_ID, "black_death_sword_skills"), 4.0F, 1.0F);
                        };
                        Timeout.create(5, callback);
                        Timeout.create(10, callback);
                        Timeout.create(15, callback);
                        Timeout.create(20, callback);
                        Timeout.create(25, callback);
                        entity.getCooldowns().addCooldown(ar.getObject().getItem(), 1200);
                        continue;
                    }
                    if (!entity.level.isClientSide())
                        entity.displayClientMessage(new TranslatableComponent("item.rainimator.zecanirn_the_blade.error.health"), true);
                    continue;
                }
                if (!entity.level.isClientSide())
                    entity.displayClientMessage(new TranslatableComponent("item.rainimator.zecanirn_the_blade.error.magic"), true);
            }
        }
        return ar;
    }

    @Override
    public boolean onEntitySwing(ItemStack itemstack, LivingEntity entity) {
        boolean ret_val = super.onEntitySwing(itemstack, entity);
        if (Math.random() < 0.2D)
            ParticleUtil.spawnCircleParticles(entity.level, (ParticleOptions) ModParticleTypes.PURPLELIGHT.get(), entity.getX(), entity.getY(), entity.getZ(), 4, 0, 50);
        return ret_val;
    }

    @Override
    public Episode getEpisode() {
        return Episode.Goodbye;
    }
}
