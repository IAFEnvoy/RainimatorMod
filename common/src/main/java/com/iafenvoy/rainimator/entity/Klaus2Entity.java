package com.iafenvoy.rainimator.entity;

import com.iafenvoy.neptune.object.SoundUtil;
import com.iafenvoy.neptune.object.entity.MonsterEntityBase;
import com.iafenvoy.neptune.render.Stage;
import com.iafenvoy.rainimator.RainimatorMod;
import com.iafenvoy.rainimator.registry.RainimatorEffects;
import com.iafenvoy.rainimator.registry.RainimatorItems;
import com.iafenvoy.rainimator.registry.RainimatorParticles;
import com.iafenvoy.rainimator.registry.RainimatorSounds;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

public class Klaus2Entity extends MonsterEntityBase {
    public static final Stage.StagedEntityTextureProvider TEXTURE = Stage.ofProvider(RainimatorMod.MOD_ID, "klaus_2");
    private final ServerBossBar bossInfo = new ServerBossBar(this.getDisplayName(), BossBar.Color.BLUE, BossBar.Style.PROGRESS);

    public Klaus2Entity(EntityType<Klaus2Entity> type, World world) {
        super(type, world, EntityGroup.DEFAULT);
        this.experiencePoints = 0;
        this.setPersistent();
        this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(RainimatorItems.SOUL_RAIDING_HAMMER.get()));
        this.equipStack(EquipmentSlot.HEAD, new ItemStack(RainimatorItems.KING_NORMAL_CROWN.get()));
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3D)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 200.0D)
                .add(EntityAttributes.GENERIC_ARMOR, 20.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.0D)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 64.0D)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 6.0D)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 2.0D);
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
        this.goalSelector.add(5, new LongDoorInteractGoal(this, false));
        this.goalSelector.add(6, new LongDoorInteractGoal(this, true));
        this.goalSelector.add(7, new LookAroundGoal(this));
        this.goalSelector.add(8, new SwimGoal(this));
    }

    @Override
    public boolean canImmediatelyDespawn(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public SoundEvent getHurtSound(DamageSource ds) {
        return SoundEvents.ENTITY_VINDICATOR_HURT;
    }

    @Override
    public SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_VINDICATOR_DEATH;
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        Entity sourceentity = source.getAttacker();
        if (sourceentity != null) {
            if (sourceentity instanceof LivingEntity _ent)
                this.setTarget(_ent);
            if (this.getMainHandStack().getItem() == RainimatorItems.DEEP_WAR_HAMMER.get() || this.getMainHandStack().getItem() == RainimatorItems.LASER_SWORD.get()) {
                if (Math.random() < 0.3) {
                    ItemStack _setstack = new ItemStack(RainimatorItems.LASER_SWORD.get());
                    _setstack.setCount(1);
                    this.setStackInHand(Hand.MAIN_HAND, _setstack);
                } else if (Math.random() < 0.4) {
                    ItemStack _setstack = new ItemStack(RainimatorItems.DEEP_WAR_HAMMER.get());
                    _setstack.setCount(1);
                    this.setStackInHand(Hand.MAIN_HAND, _setstack);
                }
            }
            if (EnchantmentHelper.getLevel(Enchantments.PROTECTION, this.getEquippedStack(EquipmentSlot.HEAD)) != 0) {
                if (!this.getWorld().isClient()) {
                    this.addStatusEffect(new StatusEffectInstance(RainimatorEffects.PURIFICATION.get(), 200, 0));
                    this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100, 2));
                }
                if (Math.random() < 0.1) {
                    if (!this.getWorld().isClient())
                        this.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 100, 0));
                    SoundUtil.playSound(this.getWorld(), x, y, z, SoundEvents.BLOCK_ANVIL_LAND, 1, 1);
                    if (this.getWorld() instanceof ServerWorld _level)
                        _level.spawnParticles(ParticleTypes.TOTEM_OF_UNDYING, x, y, z, 200, 0, 10, 0, 0.002);
                }
            }
            if (EnchantmentHelper.getLevel(Enchantments.SHARPNESS, this.getMainHandStack()) != 0) {
                if (!this.getWorld().isClient())
                    this.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 200, 0));
                if (Math.random() < 0.1) {
                    if (sourceentity instanceof LivingEntity _entity && !_entity.getWorld().isClient())
                        _entity.addStatusEffect(new StatusEffectInstance(RainimatorEffects.STUNNED.get(), 100, 0));
                    SoundUtil.playSound(this.getWorld(), x, y, z, RainimatorSounds.STUNNED.get(), 5, 1);
                    if (this.getWorld() instanceof ServerWorld _level)
                        _level.spawnParticles(RainimatorParticles.YELLOW_STARS.get(), x, y, z, 50, 1, 2, 1, 1);
                }
            }
            if (this.getMainHandStack().getItem() == RainimatorItems.SOUL_RAIDING_HAMMER.get()) {
                if (!this.getWorld().isClient())
                    this.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 200, 1));
                if (Math.random() < 0.1) {
                    SoundUtil.playSound(this.getWorld(), x, y, z, SoundEvents.BLOCK_ANVIL_LAND, 1, 1);
                    if (this.getWorld() instanceof ServerWorld _level)
                        _level.spawnParticles(ParticleTypes.END_ROD, x, y, z, 100, 2, 3, 2, 0.002);
                    if (sourceentity instanceof LivingEntity _entity && !_entity.getWorld().isClient())
                        _entity.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 100, 1));
                }
            }
            if (this.getMainHandStack().getItem() == RainimatorItems.SEIZING_SHADOW_HALBERD.get()) {
                if (!this.getWorld().isClient())
                    this.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 200, 2));
                if (Math.random() < 0.1) {
                    SoundUtil.playSound(this.getWorld(), x, y, z, SoundEvents.BLOCK_ANVIL_LAND, 1, 1);
                    if (this.getWorld() instanceof ServerWorld _level)
                        _level.spawnParticles(ParticleTypes.END_ROD, x, y, z, 100, 2, 3, 2, 0.002);
                    if (sourceentity instanceof LivingEntity _entity && !_entity.getWorld().isClient()) {
                        _entity.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 100, 1));
                        _entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 100, 1));
                        _entity.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 100, 1));
                    }
                }
            }
        }
        return super.damage(source, amount);
    }

    @Override
    public boolean isInvulnerableTo(DamageSource damageSource) {
        if (damageSource.getSource() instanceof PersistentProjectileEntity ||
                damageSource.isOf(DamageTypes.FALL) ||
                damageSource.isOf(DamageTypes.CACTUS) ||
                damageSource.isOf(DamageTypes.LIGHTNING_BOLT) ||
                damageSource.isOf(DamageTypes.EXPLOSION) ||
                damageSource.isOf(DamageTypes.TRIDENT) ||
                damageSource.isOf(DamageTypes.FALLING_ANVIL) ||
                damageSource.isOf(DamageTypes.DRAGON_BREATH) ||
                damageSource.isOf(DamageTypes.WITHER) ||
                damageSource.isOf(DamageTypes.WITHER_SKULL))
            return true;
        return super.isInvulnerableTo(damageSource);
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason reason, EntityData livingdata, NbtCompound tag) {
        EntityData retval = super.initialize(world, difficulty, reason, livingdata, tag);
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        if (EnchantmentHelper.getLevel(Enchantments.SHARPNESS, this.getMainHandStack()) == 0) {
            if (EnchantmentHelper.getLevel(Enchantments.PROTECTION, this.getEquippedStack(EquipmentSlot.HEAD)) == 0) {
                if (world instanceof World _level)
                    SoundUtil.playSound(_level, this.getX(), this.getY(), this.getZ(), SoundEvents.BLOCK_BEACON_ACTIVATE, 5.0F, 1.0F);
                if (world instanceof ServerWorld _level)
                    _level.spawnParticles(RainimatorParticles.LIGHTENING_ARC.get(), x, y, z, 100, 2.0D, 3.0D, 2.0D, 0.001D);
                this.getMainHandStack().addEnchantment(Enchantments.SHARPNESS, 4);
                this.getEquippedStack(EquipmentSlot.HEAD).addEnchantment(Enchantments.PROTECTION, 4);
            }
        }
        return retval;
    }

    @Override
    public void baseTick() {
        super.baseTick();
        if (this.getOffHandStack().getItem() == Blocks.AIR.asItem())
            if (this.getMainHandStack().getItem() == Blocks.AIR.asItem()) {
                if (!this.getWorld().isClient()) {
                    this.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 2));
                    this.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 100, 2));
                    this.addStatusEffect(new StatusEffectInstance(StatusEffects.UNLUCK, 100, 2));
                }
                return;
            }
        if (this.getOffHandStack().getItem() == Items.EMERALD)
            if (this.getMainHandStack().getItem() == RainimatorItems.SEIZING_SHADOW_HALBERD.get()) {
                if (!this.getWorld().isClient()) {
                    this.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 100, 1));
                    this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100, 1));
                    this.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 100, 1));
                    this.addStatusEffect(new StatusEffectInstance(RainimatorEffects.PURIFICATION.get(), 100, 0));
                }
                return;
            }
        if (this.getOffHandStack().getItem() == Blocks.AIR.asItem())
            if (this.getMainHandStack().getItem() == RainimatorItems.SEIZING_SHADOW_HALBERD.get()) {
                if (!this.getWorld().isClient()) {
                    this.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 100, 0));
                    this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100, 0));
                    this.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 100, 0));
                    this.addStatusEffect(new StatusEffectInstance(RainimatorEffects.PURIFICATION.get(), 100, 0));
                }
            }
    }

    @Override
    public boolean canUsePortals() {
        return false;
    }

    @Override
    public void onStartedTrackingBy(ServerPlayerEntity player) {
        super.onStartedTrackingBy(player);
        this.bossInfo.addPlayer(player);
    }

    @Override
    public void onStoppedTrackingBy(ServerPlayerEntity player) {
        super.onStoppedTrackingBy(player);
        this.bossInfo.removePlayer(player);
    }

    @Override
    public void mobTick() {
        super.mobTick();
        this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
    }
}