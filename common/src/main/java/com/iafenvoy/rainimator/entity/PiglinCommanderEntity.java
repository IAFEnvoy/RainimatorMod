package com.iafenvoy.rainimator.entity;

import com.iafenvoy.neptune.object.EntityUtil;
import com.iafenvoy.neptune.object.entity.MonsterEntityBase;
import com.iafenvoy.neptune.render.Stage;
import com.iafenvoy.neptune.util.RandomHelper;
import com.iafenvoy.rainimator.RainimatorMod;
import com.iafenvoy.rainimator.registry.RainimatorEntities;
import com.iafenvoy.rainimator.registry.RainimatorItems;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

public class PiglinCommanderEntity extends MonsterEntityBase {
    public static final Stage.StagedEntityTextureProvider TEXTURE = Stage.ofProvider(RainimatorMod.MOD_ID, "piglin_commander");

    public PiglinCommanderEntity(EntityType<PiglinCommanderEntity> type, World world) {
        super(type, world, EntityGroup.UNDEAD);
        this.experiencePoints = 20;
        this.setPersistent();
        this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(RainimatorItems.THE_GOLDEN_SWORD.get()));
        this.equipStack(EquipmentSlot.HEAD, new ItemStack(RainimatorItems.PORKSHIRE_KING_CROWN.get()));
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3D)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 80.0D)
                .add(EntityAttributes.GENERIC_ARMOR, 20.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0D)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 64.0D)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 4.0D)
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
        this.goalSelector.add(5, new LookAroundGoal(this));
        this.goalSelector.add(6, new SwimGoal(this));
    }

    @Override
    public boolean canImmediatelyDespawn(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public SoundEvent getHurtSound(DamageSource ds) {
        return SoundEvents.ENTITY_PIGLIN_HURT;
    }

    @Override
    public SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_PIGLIN_DEATH;
    }

    @Override
    public boolean isInvulnerableTo(DamageSource damageSource) {
        if (damageSource.isOf(DamageTypes.FALL) ||
                damageSource.isOf(DamageTypes.FALLING_ANVIL))
            return true;
        return super.isInvulnerableTo(damageSource);
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason reason, EntityData livingdata, NbtCompound tag) {
        EntityData retval = super.initialize(world, difficulty, reason, livingdata, tag);
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        if (world instanceof ServerWorld _level) {
            EntityUtil.summon(RainimatorEntities.MUTATED.get(), _level, x + RandomHelper.nextInt(-2, 2), y, z + RandomHelper.nextInt(-2, 2));
            if (Math.random() < 0.4D)
                EntityUtil.summon(RainimatorEntities.ZOMBIE_PIGLIN_ART.get(), _level, x + RandomHelper.nextInt(-2, 2), y, z + RandomHelper.nextInt(-2, 2));
        }
        return retval;
    }
}