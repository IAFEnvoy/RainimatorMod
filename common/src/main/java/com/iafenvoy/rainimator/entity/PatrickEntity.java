package com.iafenvoy.rainimator.entity;

import com.iafenvoy.neptune.object.EntityUtil;
import com.iafenvoy.neptune.object.entity.MonsterEntityBase;
import com.iafenvoy.neptune.render.Stage;
import com.iafenvoy.neptune.util.RandomHelper;
import com.iafenvoy.rainimator.RainimatorMod;
import com.iafenvoy.rainimator.registry.RainimatorEntities;
import com.iafenvoy.rainimator.registry.RainimatorItems;
import com.iafenvoy.rainimator.registry.RainimatorParticles;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

public class PatrickEntity extends MonsterEntityBase implements RangedAttackMob {
    public static final Stage.StagedEntityTextureProvider TEXTURE = Stage.ofProvider(RainimatorMod.MOD_ID, "patrick");

    public PatrickEntity(EntityType<PatrickEntity> type, World world) {
        super(type, world, EntityGroup.DEFAULT);
        this.experiencePoints = 0;
        this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(RainimatorItems.GENERAL_PATRICK_LONG_KNIVES.get()));
        this.equipStack(EquipmentSlot.OFFHAND, new ItemStack(RainimatorItems.GENERAL_PATRICK_LONG_KNIVES.get()));
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3D)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 120.0D)
                .add(EntityAttributes.GENERIC_ARMOR, 30.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0D)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 64.0D)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 5.0D)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 1.0D);
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
        this.goalSelector.add(5, new LongDoorInteractGoal(this, true));
        this.goalSelector.add(6, new LongDoorInteractGoal(this, false));
        this.goalSelector.add(7, new LookAroundGoal(this));
        this.goalSelector.add(8, new SwimGoal(this));
        this.goalSelector.add(1, new ProjectileAttackGoal(this, 1.25D, 20, 10.0F) {
            @Override
            public boolean shouldContinue() {
                return this.canStart();
            }
        });
    }

    @Override
    public SoundEvent getHurtSound(DamageSource ds) {
        return SoundEvents.ENTITY_GENERIC_HURT;
    }

    @Override
    public SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_GENERIC_DEATH;
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        if (Math.random() < 0.2D) {//TODO: Optimize
            this.getWorld().addParticle(RainimatorParticles.YELLOW_LIGHTENING.get(), x, y + 0.5D, z + 0.5D, 0.0D, 0.0D, 0.0D);
            this.getWorld().addParticle(RainimatorParticles.YELLOW_LIGHTENING.get(), x + 0.5D, y + 1.5D, z, 0.0D, 0.0D, 0.0D);
            this.getWorld().addParticle(RainimatorParticles.YELLOW_LIGHTENING.get(), x, y + 1.0D, z - 1.0D, 0.0D, 0.0D, 0.0D);
            this.getWorld().addParticle(RainimatorParticles.YELLOW_LIGHTENING.get(), x - 1.0D, y + 2.0D, z, 0.0D, 0.0D, 0.0D);
        } else if (Math.random() < 0.2D) {
            this.getWorld().addParticle(RainimatorParticles.YELLOW_LIGHTENING.get(), x + 1.0D, y + 0.5D, z + 1.0D, 0.0D, 0.0D, 0.0D);
            this.getWorld().addParticle(RainimatorParticles.YELLOW_LIGHTENING.get(), x - 1.0D, y + 1.5D, z - 1.0D, 0.0D, 0.0D, 0.0D);
            this.getWorld().addParticle(RainimatorParticles.YELLOW_LIGHTENING.get(), x - 0.5D, y + 1.0D, z + 1.0D, 0.0D, 0.0D, 0.0D);
            this.getWorld().addParticle(RainimatorParticles.YELLOW_LIGHTENING.get(), x + 1.0D, y + 2.0D, z - 0.5D, 0.0D, 0.0D, 0.0D);
        } else if (Math.random() < 0.2D) {
            this.getWorld().addParticle(RainimatorParticles.YELLOW_LIGHTENING.get(), x + 1.0D, y + 1.0D, z - 1.0D, 0.0D, 0.0D, 0.0D);
            this.getWorld().addParticle(RainimatorParticles.YELLOW_LIGHTENING.get(), x - 1.0D, y + 2.0D, z + 1.0D, 0.0D, 0.0D, 0.0D);
            this.getWorld().addParticle(RainimatorParticles.YELLOW_LIGHTENING.get(), x + 1.0D, y + 1.5D, z + 1.0D, 0.0D, 0.0D, 0.0D);
            this.getWorld().addParticle(RainimatorParticles.YELLOW_LIGHTENING.get(), x - 1.0D, y + 0.5D, z - 1.0D, 0.0D, 0.0D, 0.0D);
        } else if (Math.random() < 0.2D) {
            this.getWorld().addParticle(RainimatorParticles.YELLOW_LIGHTENING.get(), x, y + 1.0D, z - 0.5D, 0.0D, 0.0D, 0.0D);
            this.getWorld().addParticle(RainimatorParticles.YELLOW_LIGHTENING.get(), x - 0.5D, y + 2.0D, z, 0.0D, 0.0D, 0.0D);
            this.getWorld().addParticle(RainimatorParticles.YELLOW_LIGHTENING.get(), x, y + 0.5D, z + 1.0D, 0.0D, 0.0D, 0.0D);
            this.getWorld().addParticle(RainimatorParticles.YELLOW_LIGHTENING.get(), x + 1.0D, y + 1.5D, z, 0.0D, 0.0D, 0.0D);
        }
        if (Math.random() < 0.7D)
            if (!this.getWorld().isClient()) {
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 200, 1));
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 200, 1));
            }
        return super.damage(source, amount);
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason reason, EntityData livingdata, NbtCompound tag) {
        EntityData retval = super.initialize(world, difficulty, reason, livingdata, tag);
        if (world instanceof ServerWorld _level)
            EntityUtil.summon(RainimatorEntities.HILDA.get(), _level, this.getX() + RandomHelper.nextInt(1, 4), this.getY(), this.getZ() + RandomHelper.nextInt(1, 4));
        return retval;
    }

    @Override
    public void baseTick() {
        super.baseTick();
        if (!this.getWorld().isClient())
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 80, 0));
    }

    @Override
    public void attack(LivingEntity target, float flval) {
        PatrickProjectileEntity entityarrow = new PatrickProjectileEntity(RainimatorEntities.PATRICK_PROJECTILE.get(), this, this.getWorld());
        double d0 = target.getY() + target.getStandingEyeHeight() - 1.1D;
        double d1 = target.getX() - this.getX();
        double d3 = target.getZ() - this.getZ();
        entityarrow.setVelocity(d1, d0 - entityarrow.getY() + Math.sqrt(d1 * d1 + d3 * d3) * 0.20000000298023224D, d3, 1.6F, 12.0F);
        this.getWorld().spawnEntity(entityarrow);
    }
}