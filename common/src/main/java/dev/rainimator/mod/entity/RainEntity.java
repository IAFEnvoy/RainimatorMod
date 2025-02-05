package dev.rainimator.mod.entity;

import com.iafenvoy.neptune.object.SoundUtil;
import com.iafenvoy.neptune.object.entity.MonsterEntityBase;
import com.iafenvoy.neptune.render.Stage;
import dev.rainimator.mod.RainimatorMod;
import dev.rainimator.mod.registry.RainimatorEffects;
import dev.rainimator.mod.registry.RainimatorEntities;
import dev.rainimator.mod.registry.RainimatorItems;
import dev.rainimator.mod.registry.RainimatorParticles;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

public class RainEntity extends MonsterEntityBase implements RangedAttackMob {
    public static final Stage.StagedEntityTextureProvider TEXTURE = Stage.ofProvider(RainimatorMod.MOD_ID, "rain");

    public RainEntity(EntityType<RainEntity> type, World world) {
        super(type, world, EntityGroup.DEFAULT);
        this.experiencePoints = 0;
        this.setAiDisabled(false);
        this.setPersistent();
        this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(RainimatorItems.RAIN_SWORD.get()));
        this.equipStack(EquipmentSlot.CHEST, new ItemStack(RainimatorItems.RAIN_CHESTPLATE.get()));
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        DefaultAttributeContainer.Builder builder = MobEntity.createMobAttributes();
        builder = builder.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3D);
        builder = builder.add(EntityAttributes.GENERIC_MAX_HEALTH, 150.0D);
        builder = builder.add(EntityAttributes.GENERIC_ARMOR, 30.0D);
        builder = builder.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0D);
        builder = builder.add(EntityAttributes.GENERIC_FOLLOW_RANGE, 64.0D);
        builder = builder.add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 5.0D);
        builder = builder.add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 1.0D);
        return builder;
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.2D, false) {
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
            public boolean shouldContinue() {
                return this.canStart();
            }
        });
    }

    @Override
    public boolean canImmediatelyDespawn(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public SoundEvent getHurtSound(DamageSource ds) {
        return Registries.SOUND_EVENT.get(Identifier.tryParse("entity.generic.hurt"));
    }

    @Override
    public SoundEvent getDeathSound() {
        return Registries.SOUND_EVENT.get(Identifier.tryParse("entity.generic.death"));
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        if (Math.random() < 0.2D) {//TODO: Optimize
            this.getWorld().addParticle(RainimatorParticles.WHITE_CIRCLE.get(), x, y + 0.5D, z + 0.5D, 0.0D, 0.0D, 0.0D);
            this.getWorld().addParticle(RainimatorParticles.WHITE_CIRCLE.get(), x + 0.5D, y + 1.5D, z, 0.0D, 0.0D, 0.0D);
            this.getWorld().addParticle(RainimatorParticles.WHITE_CIRCLE.get(), x, y + 1.0D, z - 1.0D, 0.0D, 0.0D, 0.0D);
            this.getWorld().addParticle(RainimatorParticles.WHITE_CIRCLE.get(), x - 1.0D, y + 2.0D, z, 0.0D, 0.0D, 0.0D);
        } else if (Math.random() < 0.2D) {
            this.getWorld().addParticle(RainimatorParticles.WHITE_CIRCLE.get(), x + 1.0D, y + 0.5D, z + 1.0D, 0.0D, 0.0D, 0.0D);
            this.getWorld().addParticle(RainimatorParticles.WHITE_CIRCLE.get(), x - 1.0D, y + 1.5D, z - 1.0D, 0.0D, 0.0D, 0.0D);
            this.getWorld().addParticle(RainimatorParticles.WHITE_CIRCLE.get(), x - 0.5D, y + 1.0D, z + 1.0D, 0.0D, 0.0D, 0.0D);
            this.getWorld().addParticle(RainimatorParticles.WHITE_CIRCLE.get(), x + 1.0D, y + 2.0D, z - 0.5D, 0.0D, 0.0D, 0.0D);
        } else if (Math.random() < 0.2D) {
            this.getWorld().addParticle(RainimatorParticles.WHITE_CIRCLE.get(), x + 1.0D, y + 1.0D, z - 1.0D, 0.0D, 0.0D, 0.0D);
            this.getWorld().addParticle(RainimatorParticles.WHITE_CIRCLE.get(), x - 1.0D, y + 2.0D, z + 1.0D, 0.0D, 0.0D, 0.0D);
            this.getWorld().addParticle(RainimatorParticles.WHITE_CIRCLE.get(), x + 1.0D, y + 1.5D, z + 1.0D, 0.0D, 0.0D, 0.0D);
            this.getWorld().addParticle(RainimatorParticles.WHITE_CIRCLE.get(), x - 1.0D, y + 0.5D, z - 1.0D, 0.0D, 0.0D, 0.0D);
        } else if (Math.random() < 0.2D) {
            this.getWorld().addParticle(RainimatorParticles.WHITE_CIRCLE.get(), x, y + 1.0D, z - 0.5D, 0.0D, 0.0D, 0.0D);
            this.getWorld().addParticle(RainimatorParticles.WHITE_CIRCLE.get(), x - 0.5D, y + 2.0D, z, 0.0D, 0.0D, 0.0D);
            this.getWorld().addParticle(RainimatorParticles.WHITE_CIRCLE.get(), x, y + 0.5D, z + 1.0D, 0.0D, 0.0D, 0.0D);
            this.getWorld().addParticle(RainimatorParticles.WHITE_CIRCLE.get(), x + 1.0D, y + 1.5D, z, 0.0D, 0.0D, 0.0D);
        }
        if (Math.random() < 0.7D)
            if (!this.getWorld().isClient()) {
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 200, 1));
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 200, 1));
            }
        if (this.hasStatusEffect(RainimatorEffects.STUNNED.get()))
            if (!this.getWorld().isClient()) {
                this.addStatusEffect(new StatusEffectInstance(RainimatorEffects.PURIFICATION.get(), 200, 0));
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 200, 3));
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 200, 3));
            }
        return super.damage(source, amount);
    }

    @Override
    public boolean isInvulnerableTo(DamageSource damageSource) {
        if (damageSource.getSource() instanceof PersistentProjectileEntity)
            return true;
        if (damageSource.isOf(DamageTypes.FALL))
            return true;
        if (damageSource.isOf(DamageTypes.EXPLOSION))
            return true;
        if (damageSource.isOf(DamageTypes.TRIDENT))
            return true;
        return super.isInvulnerableTo(damageSource);
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason reason, EntityData livingdata, NbtCompound tag) {
        EntityData retval = super.initialize(world, difficulty, reason, livingdata, tag);
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        if (world instanceof World _level)
            SoundUtil.playSound(_level, x, y, z, Identifier.of(RainimatorMod.MOD_ID, "blued_diamond_skill_1"), 1.0F, 1.0F);
        if (world instanceof ServerWorld _level)
            _level.spawnParticles(RainimatorParticles.SNOW.get(), x, y, z, 50, 0.5D, 1.0D, 0.5D, 0.1D);
        return retval;
    }

    @Override
    public void attack(LivingEntity target, float flval) {
        RainEntityProjectile entityarrow = new RainEntityProjectile(RainimatorEntities.RAIN_PROJECTILE.get(), this, this.getWorld());
        double d0 = target.getY() + target.getStandingEyeHeight() - 1.1D;
        double d1 = target.getX() - this.getX();
        double d3 = target.getZ() - this.getZ();
        entityarrow.setVelocity(d1, d0 - entityarrow.getY() + Math.sqrt(d1 * d1 + d3 * d3) * 0.20000000298023224D, d3, 1.6F, 12.0F);
        this.getWorld().spawnEntity(entityarrow);
    }
}