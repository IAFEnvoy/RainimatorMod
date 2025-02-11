package com.iafenvoy.rainimator.entity;

import com.iafenvoy.neptune.object.entity.MonsterEntityBase;
import com.iafenvoy.neptune.render.Stage;
import com.iafenvoy.neptune.util.RandomHelper;
import com.iafenvoy.neptune.util.Timeout;
import com.iafenvoy.rainimator.RainimatorMod;
import com.iafenvoy.rainimator.registry.RainimatorItems;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.thrown.PotionEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class SoraEntity extends MonsterEntityBase {
    public static final Stage.StagedEntityTextureProvider TEXTURE = Stage.ofProvider(RainimatorMod.MOD_ID, "sora");

    public SoraEntity(EntityType<? extends SoraEntity> type, World world) {
        super(type, world, EntityGroup.DEFAULT);
        this.setStepHeight(0.6f);
        this.experiencePoints = 0;
        this.setPersistent();
        this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(RainimatorItems.KATANA.get()));
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.getNavigation().getNodeMaker().setCanOpenDoors(true);
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, AzaleaEntity.class, true, true));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, BlackBoneEntity.class, true, true));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, EliteZombieEntity.class, true, true));
        this.targetSelector.add(5, new ActiveTargetGoal<>(this, GigaBoneEntity.class, true, true));
        this.targetSelector.add(6, new ActiveTargetGoal<>(this, GluttonEntity.class, true, true));
        this.targetSelector.add(8, new ActiveTargetGoal<>(this, HerobrineEntity.class, true, true));
        this.targetSelector.add(10, new ActiveTargetGoal<>(this, HogsworthEntity.class, true, true));
        this.targetSelector.add(11, new ActiveTargetGoal<>(this, NaeusEntity.class, true, true));
        this.targetSelector.add(12, new ActiveTargetGoal<>(this, NaeusKingEntity.class, true, true));
        this.targetSelector.add(13, new ActiveTargetGoal<>(this, NamtarEntity.class, true, true));
        this.targetSelector.add(14, new ActiveTargetGoal<>(this, ScorchEntity.class, true, true));
        this.targetSelector.add(16, new ActiveTargetGoal<>(this, TuskEntity.class, true, true));
        this.targetSelector.add(18, new ActiveTargetGoal<>(this, BrotsEntity.class, true, true));
        this.goalSelector.add(19, new MeleeAttackGoal(this, 1.2, true) {
            @Override
            protected double getSquaredMaxAttackDistance(LivingEntity entity) {
                return this.mob.getWidth() * this.mob.getWidth() + entity.getWidth();
            }
        });
        this.goalSelector.add(20, new WanderAroundGoal(this, 1.0));
        this.targetSelector.add(21, new RevengeGoal(this, new Class[0]));
        this.goalSelector.add(22, new LongDoorInteractGoal(this, true));
        this.goalSelector.add(23, new LongDoorInteractGoal(this, false));
        this.goalSelector.add(24, new LookAroundGoal(this));
        this.goalSelector.add(25, new SwimGoal(this));
    }

    @Override
    public boolean canImmediatelyDespawn(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public double getHeightOffset() {
        return -0.35;
    }

    @Override
    public SoundEvent getHurtSound(DamageSource ds) {
        return Registries.SOUND_EVENT.get(new Identifier("entity.generic.hurt"));
    }

    @Override
    public SoundEvent getDeathSound() {
        return Registries.SOUND_EVENT.get(new Identifier("entity.generic.death"));
    }

    public boolean damage(DamageSource source, float amount) {
        if (this.random.nextInt(3) == 0)
            this.requestTeleport(this.getX() + RandomHelper.nextDouble(-10, 10), this.getY(), this.getZ() + RandomHelper.nextDouble(-10, 10));
        if (source.getAttacker() instanceof ArrowEntity)
            return false;
        if (source.getAttacker() instanceof PotionEntity || source.getAttacker() instanceof AreaEffectCloudEntity)
            return false;
        if (source.isOf(DamageTypes.CACTUS))
            return false;
        if (source.isOf(DamageTypes.DROWN))
            return false;
        if (source.isOf(DamageTypes.TRIDENT))
            return false;
        if (source.isOf(DamageTypes.DRAGON_BREATH))
            return false;
        if (source.isOf(DamageTypes.WITHER))
            return false;
        if (source.isOf(DamageTypes.WITHER_SKULL))
            return false;
        return super.damage(source, amount);
    }

    @Override
    public void baseTick() {
        super.baseTick();
        if (this.getWorld().getBlockState(this.getBlockPos()).isOpaque()) {
            this.requestTeleport(this.getX() + RandomHelper.nextDouble(-1, 1), this.getY() + 1.0, this.getZ() + RandomHelper.nextDouble(-1, 1));
            this.fallDistance = 0.0f;
            Runnable runnable = () -> {
                if (this.getWorld() instanceof ServerWorld _level)
                    _level.spawnParticles(ParticleTypes.END_ROD, this.getX(), this.getY() + 1.0, this.getZ(), 5, 3.0, 3.0, 3.0, 1.0);
            };
            Timeout.create(0, runnable);
            Timeout.create(10, runnable);
        }
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        DefaultAttributeContainer.Builder builder = MobEntity.createMobAttributes();
        builder = builder.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.35);
        builder = builder.add(EntityAttributes.GENERIC_MAX_HEALTH, 25.0);
        builder = builder.add(EntityAttributes.GENERIC_ARMOR, 20.0);
        builder = builder.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.0);
        builder = builder.add(EntityAttributes.GENERIC_FOLLOW_RANGE, 64.0);
        builder = builder.add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 2.0);
        return builder;
    }
}
