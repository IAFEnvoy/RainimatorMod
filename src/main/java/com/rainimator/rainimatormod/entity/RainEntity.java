package com.rainimator.rainimatormod.entity;

import com.rainimator.rainimatormod.RainimatorMod;
import com.rainimator.rainimatormod.registry.ModEffects;
import com.rainimator.rainimatormod.registry.ModEntities;
import com.rainimator.rainimatormod.registry.ModItems;
import com.rainimator.rainimatormod.registry.ModParticleTypes;
import com.rainimator.rainimatormod.registry.util.MonsterEntityBase;
import com.rainimator.rainimatormod.util.SoundUtil;
import com.rainimator.rainimatormod.util.Stage;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class RainEntity extends MonsterEntityBase implements RangedAttackMob {
    public static Stage.StagedEntityTextureProvider texture = Stage.ofProvider("rain");

    public RainEntity(PlayMessages.SpawnEntity packet, Level world) {
        this(ModEntities.RAIN.get(), world);
    }

    public RainEntity(EntityType<RainEntity> type, Level world) {
        super(type, world, MobType.UNDEFINED);
        this.xpReward = 0;
        this.setNoAi(false);
        this.setPersistenceRequired();
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ModItems.RAIN_SWORD.get()));
        this.setItemSlot(EquipmentSlot.CHEST, new ItemStack(ModItems.RAIN_CHESTPLATE.get()));
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3D);
        builder = builder.add(Attributes.MAX_HEALTH, 150.0D);
        builder = builder.add(Attributes.ARMOR, 30.0D);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 1.0D);
        builder = builder.add(Attributes.FOLLOW_RANGE, 64.0D);
        builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 5.0D);
        builder = builder.add(Attributes.ATTACK_KNOCKBACK, 1.0D);
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
        this.goalSelector.addGoal(5, new OpenDoorGoal(this, true));
        this.goalSelector.addGoal(6, new OpenDoorGoal(this, false));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(8, new FloatGoal(this));
        this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.25D, 20, 10.0F) {
            public boolean canContinueToUse() {
                return this.canUse();
            }
        });
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
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        if (Math.random() < 0.2D) {
            this.level.addParticle((ParticleOptions) ModParticleTypes.WRITECRICLE.get(), x, y + 0.5D, z + 0.5D, 0.0D, 0.0D, 0.0D);
            this.level.addParticle((ParticleOptions) ModParticleTypes.WRITECRICLE.get(), x + 0.5D, y + 1.5D, z, 0.0D, 0.0D, 0.0D);
            this.level.addParticle((ParticleOptions) ModParticleTypes.WRITECRICLE.get(), x, y + 1.0D, z - 1.0D, 0.0D, 0.0D, 0.0D);
            this.level.addParticle((ParticleOptions) ModParticleTypes.WRITECRICLE.get(), x - 1.0D, y + 2.0D, z, 0.0D, 0.0D, 0.0D);
        } else if (Math.random() < 0.2D) {
            this.level.addParticle((ParticleOptions) ModParticleTypes.WRITECRICLE.get(), x + 1.0D, y + 0.5D, z + 1.0D, 0.0D, 0.0D, 0.0D);
            this.level.addParticle((ParticleOptions) ModParticleTypes.WRITECRICLE.get(), x - 1.0D, y + 1.5D, z - 1.0D, 0.0D, 0.0D, 0.0D);
            this.level.addParticle((ParticleOptions) ModParticleTypes.WRITECRICLE.get(), x - 0.5D, y + 1.0D, z + 1.0D, 0.0D, 0.0D, 0.0D);
            this.level.addParticle((ParticleOptions) ModParticleTypes.WRITECRICLE.get(), x + 1.0D, y + 2.0D, z - 0.5D, 0.0D, 0.0D, 0.0D);
        } else if (Math.random() < 0.2D) {
            this.level.addParticle((ParticleOptions) ModParticleTypes.WRITECRICLE.get(), x + 1.0D, y + 1.0D, z - 1.0D, 0.0D, 0.0D, 0.0D);
            this.level.addParticle((ParticleOptions) ModParticleTypes.WRITECRICLE.get(), x - 1.0D, y + 2.0D, z + 1.0D, 0.0D, 0.0D, 0.0D);
            this.level.addParticle((ParticleOptions) ModParticleTypes.WRITECRICLE.get(), x + 1.0D, y + 1.5D, z + 1.0D, 0.0D, 0.0D, 0.0D);
            this.level.addParticle((ParticleOptions) ModParticleTypes.WRITECRICLE.get(), x - 1.0D, y + 0.5D, z - 1.0D, 0.0D, 0.0D, 0.0D);
        } else if (Math.random() < 0.2D) {
            this.level.addParticle((ParticleOptions) ModParticleTypes.WRITECRICLE.get(), x, y + 1.0D, z - 0.5D, 0.0D, 0.0D, 0.0D);
            this.level.addParticle((ParticleOptions) ModParticleTypes.WRITECRICLE.get(), x - 0.5D, y + 2.0D, z, 0.0D, 0.0D, 0.0D);
            this.level.addParticle((ParticleOptions) ModParticleTypes.WRITECRICLE.get(), x, y + 0.5D, z + 1.0D, 0.0D, 0.0D, 0.0D);
            this.level.addParticle((ParticleOptions) ModParticleTypes.WRITECRICLE.get(), x + 1.0D, y + 1.5D, z, 0.0D, 0.0D, 0.0D);
        }
        if (Math.random() < 0.7D)
            if (!this.level.isClientSide()) {
                this.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 1));
                this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 1));
            }
        if (this.hasEffect(ModEffects.STUNNED.get()))
            if (!this.level.isClientSide()) {
                this.addEffect(new MobEffectInstance(ModEffects.PURIFICATION.get(), 200, 0));
                this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 3));
                this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 3));
            }
        if (source.getDirectEntity() instanceof AbstractArrow)
            return false;
        if (source == DamageSource.FALL)
            return false;
        if (source.isExplosion())
            return false;
        if (source.getMsgId().equals("trident"))
            return false;
        return super.hurt(source, amount);
    }

    @Override
    public SpawnGroupData finalizeSpawn(@NotNull ServerLevelAccessor world, @NotNull DifficultyInstance difficulty, @NotNull MobSpawnType reason, @Nullable SpawnGroupData livingdata, @Nullable CompoundTag tag) {
        SpawnGroupData retval = super.finalizeSpawn(world, difficulty, reason, livingdata, tag);
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        if (world instanceof Level _level)
            SoundUtil.playSound(_level, x, y, z, new ResourceLocation(RainimatorMod.MOD_ID, "blued_diamond_skill_1"), 1.0F, 1.0F);
        if (world instanceof ServerLevel _level)
            _level.sendParticles((ParticleOptions) ModParticleTypes.SNOW.get(), x, y, z, 50, 0.5D, 1.0D, 0.5D, 0.1D);
        return retval;
    }

    @Override
    public void performRangedAttack(LivingEntity target, float flval) {
        RainEntityProjectile entityarrow = new RainEntityProjectile(ModEntities.RAIN_PROJECTILE.get(), this, this.level);
        double d0 = target.getY() + target.getEyeHeight() - 1.1D;
        double d1 = target.getX() - this.getX();
        double d3 = target.getZ() - this.getZ();
        entityarrow.shoot(d1, d0 - entityarrow.getY() + Math.sqrt(d1 * d1 + d3 * d3) * 0.20000000298023224D, d3, 1.6F, 12.0F);
        this.level.addFreshEntity(entityarrow);
    }
}