package com.rainimator.rainimatormod.entity;

import com.rainimator.rainimatormod.RainimatorMod;
import com.rainimator.rainimatormod.registry.ModEntities;
import com.rainimator.rainimatormod.registry.ModItems;
import com.rainimator.rainimatormod.registry.util.MonsterEntityBase;
import com.rainimator.rainimatormod.util.SoundUtil;
import com.rainimator.rainimatormod.util.Stage;
import com.rainimator.rainimatormod.util.Timeout;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.BossEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class ZombiesPiglinKingEntity extends MonsterEntityBase {
    public static Stage.StagedEntityTextureProvider texture = Stage.ofProvider("zombies_piglin_king");
    private final ServerBossEvent bossInfo = new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.YELLOW, BossEvent.BossBarOverlay.PROGRESS);

    public ZombiesPiglinKingEntity(PlayMessages.SpawnEntity packet, Level world) {
        this(ModEntities.ZOMBIE_PIGLIN_KING.get(), world);
    }

    public ZombiesPiglinKingEntity(EntityType<ZombiesPiglinKingEntity> type, Level world) {
        super(type, world, MobType.UNDEAD);
        this.xpReward = 0;
        this.setNoAi(false);
        this.setPersistenceRequired();
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ModItems.KING_ZOMBIE_PIG_MAN_SWORD.get()));
        this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(ModItems.PIGLIN_KING_CROWN.get()));
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.35D);
        builder = builder.add(Attributes.MAX_HEALTH, 120.0D);
        builder = builder.add(Attributes.ARMOR, 30.0D);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 1.0D);
        builder = builder.add(Attributes.FOLLOW_RANGE, 64.0D);
        builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 5.0D);
        builder = builder.add(Attributes.ATTACK_KNOCKBACK, 2.0D);
        return builder;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2D, false) {
            protected double getAttackReachSqr(@NotNull LivingEntity entity) {
                return (this.mob.getBbWidth() * this.mob.getBbWidth() + entity.getBbWidth());
            }
        });
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 1.0D));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, false, false));
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
        return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.piglin.hurt"));
    }

    @Override
    public SoundEvent getDeathSound() {
        return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.piglin.death"));
    }

    @Override
    public boolean hurt(@NotNull DamageSource source, float amount) {
        if (source == DamageSource.FALL)
            return false;
        if (source == DamageSource.CACTUS)
            return false;
        if (source.isExplosion())
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
            SoundUtil.playSound(_level, x, y, z, new ResourceLocation("entity.wither.ambient"), 1.0F, 1.0F);
        if (world instanceof ServerLevel _level)
            _level.sendParticles((ParticleOptions) ParticleTypes.SOUL, x, y, z, 200, 1.0D, 2.0D, 1.0D, 0.02D);
        if (world.getDifficulty() != Difficulty.PEACEFUL) {
            if (!this.level.isClientSide() && this.getServer() != null)
                this.getServer().getCommands().performCommand(this.createCommandSourceStack().withSuppressedOutput().withPermission(4), "playsound rainimator:piglin_king_boss_music neutral @a ~ ~ ~");
            Runnable callback = () -> {
                if (this.isAlive())
                    if (!this.level.isClientSide() && this.getServer() != null)
                        this.getServer().getCommands().performCommand(this.createCommandSourceStack().withSuppressedOutput().withPermission(4), "playsound rainimator:piglin_king_boss_music neutral @a ~ ~ ~");
            };
            Timeout.create(3480, callback);
            Timeout.create(6960, callback);
            Timeout.create(10440, callback);
            Timeout.create(13920, callback);
            Timeout.create(17400, callback);
            Timeout.create(20880, callback);
        }
        return retval;
    }

    @Override
    public void baseTick() {
        super.baseTick();
        if (!this.level.isClientSide())
            this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 0));
        if (!((Entity) this).isAlive())
            SoundUtil.stopSound(this.level, new ResourceLocation(RainimatorMod.MOD_ID, "piglin_king_boss_music"));
    }

    @Override
    public boolean canChangeDimensions() {
        return false;
    }

    @Override
    public void startSeenByPlayer(@NotNull ServerPlayer player) {
        super.startSeenByPlayer(player);
        this.bossInfo.addPlayer(player);
    }

    @Override
    public void stopSeenByPlayer(@NotNull ServerPlayer player) {
        super.stopSeenByPlayer(player);
        this.bossInfo.removePlayer(player);
    }

    @Override
    public void customServerAiStep() {
        super.customServerAiStep();
        this.bossInfo.setProgress(this.getHealth() / this.getMaxHealth());
    }
}