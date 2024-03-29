package com.rainimator.rainimatormod.entity;

import com.rainimator.rainimatormod.registry.ModEntities;
import com.rainimator.rainimatormod.registry.util.MonsterEntityBase;
import com.rainimator.rainimatormod.util.Stage;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class BigUndeadSkeletonEntity extends MonsterEntityBase {
    public static Stage.StagedEntityTextureProvider texture = Stage.ofProvider("big_blackbone");

    public BigUndeadSkeletonEntity(PlayMessages.SpawnEntity packet, Level world) {
        this(ModEntities.BIG_UNDEAD_SKELETON.get(), world);
    }

    public BigUndeadSkeletonEntity(EntityType<BigUndeadSkeletonEntity> type, Level world) {
        super(type, world, MobType.UNDEAD);
        this.xpReward = 200;
        this.setPersistenceRequired();
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3D);
        builder = builder.add(Attributes.MAX_HEALTH, 1000.0D);
        builder = builder.add(Attributes.ARMOR, 30.0D);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 8.0D);
        builder = builder.add(Attributes.FOLLOW_RANGE, 64.0D);
        builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 100.0D);
        builder = builder.add(Attributes.ATTACK_KNOCKBACK, 6.0D);
        return builder;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, false, false));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2D, false) {
            protected double getAttackReachSqr(@NotNull LivingEntity entity) {
                return (this.mob.getBbWidth() * this.mob.getBbWidth() + entity.getBbWidth());
            }
        });
        this.goalSelector.addGoal(3, new RandomStrollGoal(this, 1.0D));
        this.targetSelector.addGoal(4, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6, new FloatGoal(this));
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public SoundEvent getHurtSound(@NotNull DamageSource ds) {
        return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.hurt"));
    }

    @Override
    public SoundEvent getDeathSound() {
        return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.death"));
    }

    @Override
    public boolean hurt(@NotNull DamageSource source, float amount) {
        LivingEntity _livEnt = this;
        if (_livEnt.getHealth() < 200.0F)
            if (this.level instanceof ServerLevel _level)
                if (Math.random() < 0.2D) {
                    WitherSkeleton witherSkeleton = new WitherSkeleton(EntityType.WITHER_SKELETON, _level);
                    witherSkeleton.moveTo(this.getX() + Mth.nextInt(new Random(), 1, 3), this.getY() + 2.0D, this.getZ(), this.level.getRandom().nextFloat() * 360.0F, 0.0F);
                    ((Mob) witherSkeleton).finalizeSpawn(_level, this.level.getCurrentDifficultyAt(witherSkeleton.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
                    this.level.addFreshEntity(witherSkeleton);
                } else if (Math.random() < 0.2D) {
                    WitheredSkeletonsEntity witheredSkeletonsEntity = new WitheredSkeletonsEntity(ModEntities.WITHERED_SKELETONS.get(), _level);
                    witheredSkeletonsEntity.moveTo(this.getX() + Mth.nextInt(new Random(), 1, 3), this.getY() + 2.0D, this.getZ(), this.level.getRandom().nextFloat() * 360.0F, 0.0F);
                    witheredSkeletonsEntity.finalizeSpawn(_level, this.level.getCurrentDifficultyAt(witheredSkeletonsEntity.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
                    this.level.addFreshEntity(witheredSkeletonsEntity);
                }
        if (source == DamageSource.FALL)
            return false;
        if (source.isExplosion())
            return false;
        if (source == DamageSource.WITHER)
            return false;
        if (source.getMsgId().equals("witherSkull"))
            return false;
        return super.hurt(source, amount);
    }
}