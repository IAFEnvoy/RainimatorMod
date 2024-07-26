package dev.rainimator.mod.entity;

import com.iafenvoy.neptune.object.SoundUtil;
import com.iafenvoy.neptune.object.entity.MonsterEntityBase;
import com.iafenvoy.neptune.render.Stage;
import com.iafenvoy.neptune.util.Timeout;
import dev.rainimator.mod.RainimatorMod;
import dev.rainimator.mod.data.fraction.Fraction;
import dev.rainimator.mod.registry.RainimatorEffects;
import dev.rainimator.mod.registry.RainimatorItems;
import dev.rainimator.mod.registry.RainimatorParticles;
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
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.world.Difficulty;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

public class NullLikeEntity extends MonsterEntityBase {
    public static final Stage.StagedEntityTextureProvider texture = Stage.ofProvider(RainimatorMod.MOD_ID, "null_like").setEyeTextureId("textures/entities/null_like_eye.png");
    private final ServerBossBar bossInfo = new ServerBossBar(this.getDisplayName(), BossBar.Color.WHITE, BossBar.Style.PROGRESS);

    public NullLikeEntity(EntityType<NullLikeEntity> type, World world) {
        super(type, world, EntityGroup.UNDEAD);
        this.experiencePoints = 0;
        this.setPersistent();
        this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(RainimatorItems.BLACK_DEATH_SWORD.get()));
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        DefaultAttributeContainer.Builder builder = MobEntity.createMobAttributes();
        builder = builder.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3D);
        builder = builder.add(EntityAttributes.GENERIC_MAX_HEALTH, 120.0D);
        builder = builder.add(EntityAttributes.GENERIC_ARMOR, 30.0D);
        builder = builder.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0D);
        builder = builder.add(EntityAttributes.GENERIC_FOLLOW_RANGE, 64.0D);
        builder = builder.add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 5.0D);
        builder = builder.add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 2.0D);
        return builder;
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        Fraction.findAndAddTarget(this);
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
        if (this.hasStatusEffect(RainimatorEffects.FEAR_DARK.get()))
            this.clearStatusEffects();
        else if (this.hasStatusEffect(RainimatorEffects.SOUL_DEATH.get()))
            this.clearStatusEffects();
        else if (this.hasStatusEffect(StatusEffects.POISON))
            this.clearStatusEffects();
        else if (this.hasStatusEffect(StatusEffects.WEAKNESS))
            this.clearStatusEffects();
        else if (Math.random() < 0.3D) {//TODO: Optimize
            this.getWorld().addParticle(RainimatorParticles.SWEATER_SNOW.get(), x, y + 0.5D, z + 0.5D, 0.0D, 0.0D, 0.0D);
            this.getWorld().addParticle(RainimatorParticles.SWEATER_SNOW.get(), x + 0.5D, y + 1.5D, z, 0.0D, 0.0D, 0.0D);
            this.getWorld().addParticle(RainimatorParticles.SWEATER_SNOW.get(), x, y + 1.0D, z - 1.0D, 0.0D, 0.0D, 0.0D);
            this.getWorld().addParticle(RainimatorParticles.SWEATER_SNOW.get(), x - 1.0D, y + 2.0D, z, 0.0D, 0.0D, 0.0D);
        } else if (Math.random() < 0.3D) {
            this.getWorld().addParticle(RainimatorParticles.SWEATER_SNOW.get(), x + 1.0D, y + 0.5D, z + 1.0D, 0.0D, 0.0D, 0.0D);
            this.getWorld().addParticle(RainimatorParticles.SWEATER_SNOW.get(), x - 1.0D, y + 1.5D, z - 1.0D, 0.0D, 0.0D, 0.0D);
            this.getWorld().addParticle(RainimatorParticles.SWEATER_SNOW.get(), x - 0.5D, y + 1.0D, z + 1.0D, 0.0D, 0.0D, 0.0D);
            this.getWorld().addParticle(RainimatorParticles.SWEATER_SNOW.get(), x + 1.0D, y + 2.0D, z - 0.5D, 0.0D, 0.0D, 0.0D);
        } else if (Math.random() < 0.3D) {
            this.getWorld().addParticle(RainimatorParticles.SWEATER_SNOW.get(), x + 1.0D, y + 1.0D, z - 1.0D, 0.0D, 0.0D, 0.0D);
            this.getWorld().addParticle(RainimatorParticles.SWEATER_SNOW.get(), x - 1.0D, y + 2.0D, z + 1.0D, 0.0D, 0.0D, 0.0D);
            this.getWorld().addParticle(RainimatorParticles.SWEATER_SNOW.get(), x + 1.0D, y + 1.5D, z + 1.0D, 0.0D, 0.0D, 0.0D);
            this.getWorld().addParticle(RainimatorParticles.SWEATER_SNOW.get(), x - 1.0D, y + 0.5D, z - 1.0D, 0.0D, 0.0D, 0.0D);
        } else if (Math.random() < 0.3D) {
            this.getWorld().addParticle(RainimatorParticles.SWEATER_SNOW.get(), x, y + 1.0D, z - 0.5D, 0.0D, 0.0D, 0.0D);
            this.getWorld().addParticle(RainimatorParticles.SWEATER_SNOW.get(), x - 0.5D, y + 2.0D, z, 0.0D, 0.0D, 0.0D);
            this.getWorld().addParticle(RainimatorParticles.SWEATER_SNOW.get(), x, y + 0.5D, z + 1.0D, 0.0D, 0.0D, 0.0D);
            this.getWorld().addParticle(RainimatorParticles.SWEATER_SNOW.get(), x + 1.0D, y + 1.5D, z, 0.0D, 0.0D, 0.0D);
        }
        if (Math.random() < 0.7D)
            if (!this.getWorld().isClient()) {
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 200, 1));
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 200, 1));
            }
        return super.damage(source, amount);
    }

    @Override
    public boolean isInvulnerableTo(DamageSource damageSource) {
        if (damageSource.isOf(DamageTypes.FALL))
            return true;
        if (damageSource.isOf(DamageTypes.CACTUS))
            return true;
        if (damageSource.isOf(DamageTypes.DROWN))
            return true;
        if (damageSource.isOf(DamageTypes.LIGHTNING_BOLT))
            return true;
        if (damageSource.isOf(DamageTypes.EXPLOSION))
            return true;
        if (damageSource.isOf(DamageTypes.FALLING_ANVIL))
            return true;
        if (damageSource.isOf(DamageTypes.WITHER))
            return true;
        if (damageSource.isOf(DamageTypes.WITHER_SKULL))
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
            SoundUtil.playSound(_level, x, y, z, Identifier.of(RainimatorMod.MOD_ID, "blued_diamond_skill_1"), 5.0F, 1.0F);
        if (world instanceof ServerWorld _level)
            _level.spawnParticles(RainimatorParticles.FLOWER_WHITE.get(), x, y, z, 300, 2.0D, 3.0D, 2.0D, 0.3D);
        if (world.getDifficulty() != Difficulty.PEACEFUL) {
            Runnable callback = () -> {
                if (this.isAlive())
                    SoundUtil.playSound(this.getWorld(), x, y, z, Identifier.of(RainimatorMod.MOD_ID, "null_boss_music"), 1, 1);
            };
            Timeout.create(0, callback);
            Timeout.create(6420, callback);
            Timeout.create(12840, callback);
            Timeout.create(19260, callback);
            Timeout.create(25680, callback);
            Timeout.create(32100, callback);
            Timeout.create(38520, callback);
        }

        return retval;
    }

    @Override
    public void baseTick() {
        super.baseTick();
        if (!this.getWorld().isClient())
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100, 1));
        if (!this.isAlive())
            SoundUtil.stopSound(this.getWorld(), Identifier.of(RainimatorMod.MOD_ID, "null_boss_music"));
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