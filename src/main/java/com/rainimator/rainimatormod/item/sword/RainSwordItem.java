package com.rainimator.rainimatormod.item.sword;

import com.google.common.collect.Lists;
import com.rainimator.rainimatormod.RainimatorMod;
import com.rainimator.rainimatormod.registry.ModEffects;
import com.rainimator.rainimatormod.registry.ModItems;
import com.rainimator.rainimatormod.registry.util.IRainimatorInfo;
import com.rainimator.rainimatormod.registry.util.ModCreativeTab;
import com.rainimator.rainimatormod.registry.util.SwordItemBase;
import com.rainimator.rainimatormod.registry.util.TierBase;
import com.rainimator.rainimatormod.util.Episode;
import com.rainimator.rainimatormod.util.ParticleUtil;
import com.rainimator.rainimatormod.util.SoundUtil;
import com.rainimator.rainimatormod.util.Timeout;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.apache.commons.lang3.tuple.Triple;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class RainSwordItem extends SwordItemBase implements IRainimatorInfo, ICurioItem {
    private static final List<Triple<Integer, Integer, Integer>> places = Lists.newArrayList(
            Triple.of(0, 0, 0),
            Triple.of(1, 0, 0),
            Triple.of(-1, 0, 0),
            Triple.of(0, 0, 1),
            Triple.of(0, 0, -1),
            Triple.of(0, 1, 0),
            Triple.of(1, 1, 0),
            Triple.of(-1, 1, 0),
            Triple.of(0, 1, 1),
            Triple.of(0, 1, -1),
            Triple.of(0, 2, 0)
    );

    public RainSwordItem() {
        super(TierBase.of(2000, 4.0F, 11.0F, 0, 20, ModItems.SUPER_SAPPHIRE), 3, -2.0F, ModCreativeTab.createProperty());
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack itemstack, @NotNull LivingEntity entity, @NotNull LivingEntity sourceentity) {
        boolean ret_val = super.hurtEnemy(itemstack, entity, sourceentity);
        if (Math.random() < 0.1D)
            if (entity instanceof LivingEntity)
                if (!entity.level.isClientSide()) {
                    entity.addEffect(new MobEffectInstance(ModEffects.ICE_PEOPLE.get(), 100, 0));
                    entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 0));
                }
        return ret_val;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level world, @NotNull Player entity, @NotNull InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);

        Vec3 _center = entity.position();
        List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, (new AABB(_center, _center)).inflate(7.0D), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
        for (Entity entityiterator : _entfound) {
            if (!(entityiterator instanceof LivingEntity _livEnt)) continue;
            if (_livEnt.getMainHandItem().getItem() == ModItems.RAIN_SWORD.get()) {
                entityiterator.hurt(DamageSource.GENERIC, 0.0F);
                continue;
            }
            if (entity.isShiftKeyDown()) {
                if (entityiterator instanceof Mob _entity)
                    _entity.getNavigation().stop();
                SoundUtil.playSound(world, _center.x, _center.y, _center.z, new ResourceLocation(RainimatorMod.MOD_ID, "rain_sword_skill"), 1.0F, 1.0F);

                for (Triple<Integer, Integer, Integer> place : places)
                    world.setBlock(new BlockPos(entityiterator.getX() + place.getLeft(), entityiterator.getY() + place.getMiddle(), entityiterator.getZ() + place.getRight()), Blocks.ICE.defaultBlockState(), 3);
                if (!_livEnt.level.isClientSide()) {
                    _livEnt.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 540, 4));
                    _livEnt.addEffect(new MobEffectInstance(ModEffects.ICE_PEOPLE.get(), 500, 0));
                }
                if (entity instanceof Player)
                    entity.getCooldowns().addCooldown(ar.getObject().getItem(), 1200);

                Timeout.create(500, () -> {
                    SoundUtil.playSound(world, _center.x, _center.y, _center.z, new ResourceLocation(RainimatorMod.MOD_ID, "rain_sword_skill_2"), 5.0F, 1.0F);
                    for (Triple<Integer, Integer, Integer> place : places)
                        world.setBlock(new BlockPos(entityiterator.getX() + place.getLeft(), entityiterator.getY() + place.getMiddle(), entityiterator.getZ() + place.getRight()), Blocks.AIR.defaultBlockState(), 3);
                    entityiterator.hurt(DamageSource.MAGIC, 5.0F);
                });
            }
        }
        return ar;
    }

    @Override
    public boolean onEntitySwing(ItemStack itemstack, LivingEntity entity) {
        boolean ret_val = super.onEntitySwing(itemstack, entity);
        double x = entity.getX(), y = entity.getY(), z = entity.getZ();
        if (Math.random() < 0.2D)
            ParticleUtil.spawnCircleParticles(entity.level, ParticleTypes.ENCHANT, x, y, z, 3, 0, 50);
        return ret_val;
    }


    @Override
    public void inventoryTick(@NotNull ItemStack itemstack, @NotNull Level world, @NotNull Entity entity, int slot, boolean selected) {
        super.inventoryTick(itemstack, world, entity, slot, selected);
        if (selected) {
            entity.clearFire();

            Vec3 _center = entity.position();
            List<Entity> _ent_found = world.getEntitiesOfClass(Entity.class, (new AABB(_center, _center)).inflate(7.0D), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
            for (Entity entityIterator : _ent_found) {
                if (!(entityIterator instanceof LivingEntity _livEnt)) continue;
                if (_livEnt.getMainHandItem().getItem() == ModItems.RAIN_SWORD.get()) {
                    if (itemstack.hurt(0, new Random(), null)) {
                        itemstack.shrink(1);
                        itemstack.setDamageValue(0);
                    }
                    continue;
                }
                if (!_livEnt.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)) {
                    if (!_livEnt.level.isClientSide()) {
                        _livEnt.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 0));
                        _livEnt.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 60, 0));
                    }
                    entityIterator.setTicksFrozen(2);
                    if (Math.random() < 0.04D) {
                        if (!_livEnt.level.isClientSide())
                            _livEnt.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 1));
                        entityIterator.setTicksFrozen(4);
                        Runnable callback = () -> {
                            if (!_livEnt.level.isClientSide())
                                _livEnt.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 2));
                            entityIterator.setTicksFrozen(6);
                        };
                        Timeout.create(60, callback);
                        Timeout.create(120, callback);
                        Timeout.create(180, () -> {
                            if (!_livEnt.level.isClientSide()) {
                                _livEnt.addEffect(new MobEffectInstance(ModEffects.ICE_PEOPLE.get(), 100, 0));
                                _livEnt.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 4));
                            }
                            SoundUtil.playSound(world, _center.x, _center.y, _center.z, new ResourceLocation("block.conduit.activate"), 1.0F, 1.0F);
                            entityIterator.setTicksFrozen(10);
                        });
                    }
                }
            }
        }
    }

    @Override
    public Episode getEpisode() {
        return Episode.ColdAsIce;
    }
}
