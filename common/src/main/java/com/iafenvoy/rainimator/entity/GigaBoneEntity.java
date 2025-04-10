package com.iafenvoy.rainimator.entity;

import com.iafenvoy.neptune.object.EntityUtil;
import com.iafenvoy.neptune.object.entity.MonsterEntityBase;
import com.iafenvoy.neptune.render.Stage;
import com.iafenvoy.neptune.util.RandomHelper;
import com.iafenvoy.rainimator.RainimatorMod;
import com.iafenvoy.rainimator.registry.RainimatorEntities;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class GigaBoneEntity extends MonsterEntityBase {
    public static final Stage.StagedEntityTextureProvider TEXTURE = Stage.ofProvider(RainimatorMod.MOD_ID, "gigabone");

    public GigaBoneEntity(EntityType<GigaBoneEntity> type, World world) {
        super(type, world, EntityGroup.UNDEAD);
        this.experiencePoints = 100;
        this.setPersistent();
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3D)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 600.0D)
                .add(EntityAttributes.GENERIC_ARMOR, 30.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 8.0D)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 64.0D)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 100.0D)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 6.0D);
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.2D, false) {
            @Override
            protected double getSquaredMaxAttackDistance(LivingEntity entity) {
                return (this.mob.getWidth() * this.mob.getWidth() + entity.getWidth());
            }
        });
        this.goalSelector.add(3, new WanderAroundGoal(this, 1.0D));
        this.targetSelector.add(4, new RevengeGoal(this));
        this.goalSelector.add(5, new LookAroundGoal(this));
        this.goalSelector.add(6, new SwimGoal(this));
    }

    @Override
    public boolean canImmediatelyDespawn(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        LivingEntity _livEnt = this;
        if (_livEnt.getHealth() <= 100.0F && this.getWorld() instanceof ServerWorld _level)
            if (Math.random() < 0.2D)
                EntityUtil.summon(EntityType.WITHER_SKELETON, _level, this.getX() + RandomHelper.nextInt(-2, 2), this.getY() + 2.0D, this.getZ() + RandomHelper.nextInt(-2, 2));
            else if (Math.random() < 0.2D)
                EntityUtil.summon(RainimatorEntities.WITHERED_SKELETONS.get(), _level, this.getX() + RandomHelper.nextInt(-2, 2), this.getY() + 2.0D, this.getZ() + RandomHelper.nextInt(-2, 2));
        return super.damage(source, amount);
    }

    @Override
    public boolean isInvulnerableTo(DamageSource damageSource) {
        if (damageSource.isOf(DamageTypes.FALL) ||
                damageSource.isOf(DamageTypes.EXPLOSION) ||
                damageSource.isOf(DamageTypes.WITHER) ||
                damageSource.isOf(DamageTypes.WITHER_SKULL))
            return true;
        return super.isInvulnerableTo(damageSource);
    }

    @Override
    public SoundEvent getHurtSound(@NotNull DamageSource ds) {
        return SoundEvents.ENTITY_GENERIC_HURT;
    }

    @Override
    public SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_GENERIC_DEATH;
    }
}