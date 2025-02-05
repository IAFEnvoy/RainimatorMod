package dev.rainimator.mod.entity;

import com.iafenvoy.neptune.object.SoundUtil;
import com.iafenvoy.neptune.object.entity.EntityWithBossBar;
import com.iafenvoy.neptune.render.BossBarRenderHelper;
import com.iafenvoy.neptune.render.Stage;
import com.iafenvoy.neptune.util.RandomHelper;
import com.iafenvoy.neptune.util.Timeout;
import dev.rainimator.mod.RainimatorMod;
import dev.rainimator.mod.registry.RainimatorEffects;
import dev.rainimator.mod.registry.RainimatorItems;
import dev.rainimator.mod.registry.RainimatorParticles;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.*;
import org.jetbrains.annotations.NotNull;

public class CerisEntity extends EntityWithBossBar {
    public static final Stage.StagedEntityTextureProvider TEXTURE = Stage.ofProvider(RainimatorMod.MOD_ID, "ceris").setEyeTextureId("textures/entities/ceris_eye.png");

    static {
        BossBarRenderHelper.addBossBarType(CerisEntity.class, new Identifier(RainimatorMod.MOD_ID, "textures/bossbar/ceris.png"), true);
    }

    public CerisEntity(EntityType<CerisEntity> type, World world) {
        super(type, world, EntityGroup.DEFAULT, BossBar.Color.PURPLE);
        this.experiencePoints = 0;
        this.setPersistent();
        this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(RainimatorItems.ENDER_BIG_SWORD.get()));
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        DefaultAttributeContainer.Builder builder = MobEntity.createMobAttributes();
        builder = builder.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.35D);
        builder = builder.add(EntityAttributes.GENERIC_MAX_HEALTH, 200.0D);
        builder = builder.add(EntityAttributes.GENERIC_ARMOR, 35.0D);
        builder = builder.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.0D);
        builder = builder.add(EntityAttributes.GENERIC_FOLLOW_RANGE, 64.0D);
        builder = builder.add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 5.0D);
        builder = builder.add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 5.0D);
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
        this.goalSelector.add(5, new LookAroundGoal(this));
        this.goalSelector.add(6, new SwimGoal(this));
    }

    @Override
    public boolean canImmediatelyDespawn(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        Entity sourceentity = source.getAttacker();
        if (sourceentity instanceof LivingEntity _ent)
            this.setTarget(_ent);
        if (this.hasStatusEffect(RainimatorEffects.STUNNED.get())) {
            if (!this.getWorld().isClient()) {
                this.addStatusEffect(new StatusEffectInstance(RainimatorEffects.PURIFICATION.get(), 100, 0));
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100, 3));
            }
        } else {
            if (!this.getWorld().isClient())
                this.addStatusEffect(new StatusEffectInstance(RainimatorEffects.PURIFICATION.get(), 100, 0));

            if (Math.random() < 0.3D) {
                SoundUtil.playSound(this.getWorld(), this.getX(), this.getY(), this.getZ(), Identifier.tryParse("entity.enderman.teleport"), 4.0F, 1.0F);
                if (this.getWorld() instanceof ServerWorld _level)
                    _level.spawnParticles(RainimatorParticles.PURPLE_LIGHT.get(), this.getX(), this.getY(), this.getZ(), 50, 0.5D, 0.1D, 0.5D, 0.3D);
                this.getNavigation().startMovingTo(this.getX() + RandomHelper.nextInt(3, 9), this.getY(), this.getZ() + RandomHelper.nextInt(3, 9), 20.0D);
                if (!this.getWorld().isClient())
                    this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100, 2));
                if (sourceentity instanceof LivingEntity livingEntity) {
                    if (!livingEntity.getWorld().isClient())
                        livingEntity.addStatusEffect(new StatusEffectInstance(RainimatorEffects.FEAR_DARK.get(), 200, 0));
                    if (!livingEntity.hasStatusEffect(StatusEffects.GLOWING)) {
                        SoundUtil.playSound(this.getWorld(), this.getX(), this.getY(), this.getZ(), Identifier.of(RainimatorMod.MOD_ID, "ceris_f"), 1.0F, 1.0F);
                        if (livingEntity instanceof LivingEntity)
                            if (!livingEntity.getWorld().isClient())
                                livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 100, 0));
                    }
                    if (livingEntity.hasStatusEffect(StatusEffects.GLOWING)) {
                        if (!livingEntity.getWorld().isClient()) {
                            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 3));
                            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 100, 0));
                        }
                        Timeout.create(60, () -> {
                            BlockPos blockPos = this.getWorld().raycast(new RaycastContext(this.getCameraPosVec(1.0F), this.getCameraPosVec(1.0F).add(this.getRotationVec(1.0F).multiply(1.0D)), RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, this)).getBlockPos();
                            sourceentity.requestTeleport(blockPos.getX(), this.getY(), blockPos.getZ());
                            if (sourceentity instanceof ServerPlayerEntity _serverPlayer)
                                _serverPlayer.networkHandler.requestTeleport(blockPos.getX(), this.getY(), blockPos.getZ(), sourceentity.getYaw(), sourceentity.getPitch());
                            SoundUtil.playSound(this.getWorld(), this.getX(), this.getY(), this.getZ(), Identifier.of(RainimatorMod.MOD_ID, "ceris_skill"), 1.0F, 1.0F);
                            if (this.getWorld() instanceof ServerWorld _level)
                                _level.spawnParticles(RainimatorParticles.PURPLE_LIGHT.get(), this.getX(), this.getY(), this.getZ(), 50, 0.5D, 0.1D, 0.5D, 0.3D);
                        });
                    }
                }
            }
        }

        return super.damage(source, amount);
    }

    @Override
    public boolean isInvulnerableTo(DamageSource damageSource) {
        if (damageSource.getSource() instanceof PersistentProjectileEntity)
            return true;
        if (damageSource.isOf(DamageTypes.FALL))
            return true;
        if (damageSource.isOf(DamageTypes.DROWN))
            return true;
        if (damageSource.isOf(DamageTypes.LIGHTNING_BOLT))
            return true;
        if (damageSource.isOf(DamageTypes.EXPLOSION))
            return true;
        if (damageSource.isOf(DamageTypes.TRIDENT))
            return true;
        if (damageSource.isOf(DamageTypes.DRAGON_BREATH))
            return true;
        if (damageSource.isOf(DamageTypes.DRAGON_BREATH))
            return true;
        if (damageSource.isOf(DamageTypes.WITHER_SKULL))
            return true;
        return super.isInvulnerableTo(damageSource);
    }

    @Override
    public SoundEvent getHurtSound(@NotNull DamageSource ds) {
        return Registries.SOUND_EVENT.get(Identifier.tryParse("entity.generic.hurt"));
    }

    @Override
    public SoundEvent getDeathSound() {
        return Registries.SOUND_EVENT.get(Identifier.tryParse("entity.generic.death"));
    }

    @Override
    public void onDeath(DamageSource source) {
        super.onDeath(source);
        SoundUtil.playSound(this.getWorld(), this.getX(), this.getY(), this.getZ(), Identifier.of(RainimatorMod.MOD_ID, "ceris_death"), 1.0F, 1.0F);
        if (this.getWorld() instanceof ServerWorld _level)
            _level.spawnParticles(RainimatorParticles.PURPLE_LIGHT.get(), this.getX(), this.getY(), this.getZ(), 60, 0.5D, 1.0D, 0.5D, 0.5D);
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason reason, EntityData livingdata, NbtCompound tag) {
        EntityData ret_val = super.initialize(world, difficulty, reason, livingdata, tag);
        SoundUtil.playSound(this.getWorld(), this.getX(), this.getY(), this.getZ(), Identifier.of(RainimatorMod.MOD_ID, "ceris_live"), 1.0F, 1.0F);

        if (world instanceof ServerWorld _level)
            _level.spawnParticles(RainimatorParticles.PURPLE_LIGHT.get(), this.getX(), this.getY(), this.getZ(), 50, 0.5D, 0.5D, 0.5D, 0.5D);
        if (world.getDifficulty() != Difficulty.PEACEFUL) {
            Runnable callback = () -> {
                if (this.isAlive())
                    SoundUtil.playSound(this.getWorld(), this.getX(), this.getY(), this.getZ(), Identifier.of(RainimatorMod.MOD_ID, "ceris_boss_music"), 1, 1);
            };
            Timeout.create(0, callback);
            Timeout.create(4700, callback);
            Timeout.create(9400, callback);
            Timeout.create(14100, callback);
            Timeout.create(18800, callback);
            Timeout.create(23500, callback);
            Timeout.create(28200, callback);
            Timeout.create(32900, callback);
        }

        return ret_val;
    }

    @Override
    public void baseTick() {
        super.baseTick();
        if (!this.getWorld().isClient()) {
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 80, 1));
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 80, 1));
        }
        if (!this.isAlive())
            SoundUtil.stopSound(this.getWorld(), Identifier.of(RainimatorMod.MOD_ID, "ceris_boss_music"));
    }

    @Override
    public boolean canUsePortals() {
        return false;
    }
}