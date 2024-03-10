package com.rainimator.rainimatormod.entity;

import com.rainimator.rainimatormod.RainimatorMod;
import com.rainimator.rainimatormod.registry.ModEffects;
import com.rainimator.rainimatormod.registry.ModEntities;
import com.rainimator.rainimatormod.registry.ModItems;
import com.rainimator.rainimatormod.registry.ModParticleTypes;
import com.rainimator.rainimatormod.util.MiscUtil;
import com.rainimator.rainimatormod.util.Timeout;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.protocol.Packet;
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
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class NullLikeEntity extends Monster {
    private final ServerBossEvent bossInfo = new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.WHITE, BossEvent.BossBarOverlay.PROGRESS);

    public NullLikeEntity(PlayMessages.SpawnEntity packet, Level world) {
        this(ModEntities.NULLLIKE.get(), world);
    }

    public NullLikeEntity(EntityType<NullLikeEntity> type, Level world) {
        super(type, world);
        this.xpReward = 0;
        this.setNoAi(false);
        this.setPersistenceRequired();
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ModItems.BLACKDEATHSWORD.get()));
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3D);
        builder = builder.add(Attributes.MAX_HEALTH, 120.0D);
        builder = builder.add(Attributes.ARMOR, 30.0D);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 1.0D);
        builder = builder.add(Attributes.FOLLOW_RANGE, 64.0D);
        builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 5.0D);
        builder = builder.add(Attributes.ATTACK_KNOCKBACK, 2.0D);
        return builder;
    }

    @Override
    public @NotNull Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
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
        this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Player.class, false, false));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6, new FloatGoal(this));
    }

    @Override
    public @NotNull MobType getMobType() {
        return MobType.UNDEAD;
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
        if (this.hasEffect(ModEffects.FEARDARK.get()))
            this.removeAllEffects();
        else if (this.hasEffect(ModEffects.SOULDEATH.get()))
            this.removeAllEffects();
        else if (this.hasEffect(MobEffects.POISON))
            this.removeAllEffects();
        else if (this.hasEffect(MobEffects.WEAKNESS))
            this.removeAllEffects();
        else if (Math.random() < 0.3D) {
            this.level.addParticle((ParticleOptions) ModParticleTypes.SWEATERSNOW.get(), x, y + 0.5D, z + 0.5D, 0.0D, 0.0D, 0.0D);
            this.level.addParticle((ParticleOptions) ModParticleTypes.SWEATERSNOW.get(), x + 0.5D, y + 1.5D, z, 0.0D, 0.0D, 0.0D);
            this.level.addParticle((ParticleOptions) ModParticleTypes.SWEATERSNOW.get(), x, y + 1.0D, z - 1.0D, 0.0D, 0.0D, 0.0D);
            this.level.addParticle((ParticleOptions) ModParticleTypes.SWEATERSNOW.get(), x - 1.0D, y + 2.0D, z, 0.0D, 0.0D, 0.0D);
        } else if (Math.random() < 0.3D) {
            this.level.addParticle((ParticleOptions) ModParticleTypes.SWEATERSNOW.get(), x + 1.0D, y + 0.5D, z + 1.0D, 0.0D, 0.0D, 0.0D);
            this.level.addParticle((ParticleOptions) ModParticleTypes.SWEATERSNOW.get(), x - 1.0D, y + 1.5D, z - 1.0D, 0.0D, 0.0D, 0.0D);
            this.level.addParticle((ParticleOptions) ModParticleTypes.SWEATERSNOW.get(), x - 0.5D, y + 1.0D, z + 1.0D, 0.0D, 0.0D, 0.0D);
            this.level.addParticle((ParticleOptions) ModParticleTypes.SWEATERSNOW.get(), x + 1.0D, y + 2.0D, z - 0.5D, 0.0D, 0.0D, 0.0D);
        } else if (Math.random() < 0.3D) {
            this.level.addParticle((ParticleOptions) ModParticleTypes.SWEATERSNOW.get(), x + 1.0D, y + 1.0D, z - 1.0D, 0.0D, 0.0D, 0.0D);
            this.level.addParticle((ParticleOptions) ModParticleTypes.SWEATERSNOW.get(), x - 1.0D, y + 2.0D, z + 1.0D, 0.0D, 0.0D, 0.0D);
            this.level.addParticle((ParticleOptions) ModParticleTypes.SWEATERSNOW.get(), x + 1.0D, y + 1.5D, z + 1.0D, 0.0D, 0.0D, 0.0D);
            this.level.addParticle((ParticleOptions) ModParticleTypes.SWEATERSNOW.get(), x - 1.0D, y + 0.5D, z - 1.0D, 0.0D, 0.0D, 0.0D);
        } else if (Math.random() < 0.3D) {
            this.level.addParticle((ParticleOptions) ModParticleTypes.SWEATERSNOW.get(), x, y + 1.0D, z - 0.5D, 0.0D, 0.0D, 0.0D);
            this.level.addParticle((ParticleOptions) ModParticleTypes.SWEATERSNOW.get(), x - 0.5D, y + 2.0D, z, 0.0D, 0.0D, 0.0D);
            this.level.addParticle((ParticleOptions) ModParticleTypes.SWEATERSNOW.get(), x, y + 0.5D, z + 1.0D, 0.0D, 0.0D, 0.0D);
            this.level.addParticle((ParticleOptions) ModParticleTypes.SWEATERSNOW.get(), x + 1.0D, y + 1.5D, z, 0.0D, 0.0D, 0.0D);
        }
        if (Math.random() < 0.7D)
            if (!this.level.isClientSide()) {
                this.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 1));
                this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 1));
            }
        if (source == DamageSource.FALL)
            return false;
        if (source == DamageSource.DROWN)
            return false;
        if (source == DamageSource.LIGHTNING_BOLT)
            return false;
        if (source.isExplosion())
            return false;
        if (source == DamageSource.ANVIL)
            return false;
        if (source == DamageSource.WITHER)
            return false;
        if (source.getMsgId().equals("witherSkull"))
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
            MiscUtil.playSound(_level, x, y, z, new ResourceLocation(RainimatorMod.MOD_ID, "blued_diamond_skill_1"), 5.0F, 1.0F);
        if (world instanceof ServerLevel _level)
            _level.sendParticles((ParticleOptions) ModParticleTypes.FLOWERWRITE.get(), x, y, z, 300, 2.0D, 3.0D, 2.0D, 0.3D);
        if (world.getDifficulty() != Difficulty.PEACEFUL) {
            if (!this.level.isClientSide() && this.getServer() != null)
                this.getServer().getCommands().performCommand(this.createCommandSourceStack().withSuppressedOutput().withPermission(4), "playsound rainimator:null_boss_music neutral @a ~ ~ ~");
            Runnable callback = () -> {
                if (this.isAlive())
                    if (!this.level.isClientSide() && this.getServer() != null)
                        this.getServer().getCommands().performCommand(this.createCommandSourceStack().withSuppressedOutput().withPermission(4), "playsound rainimator:null_boss_music neutral @a ~ ~ ~");
            };
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
        if (!this.level.isClientSide())
            this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 1));
        if (!this.isAlive() && this.level instanceof ServerLevel _level)
            _level.getServer().getCommands().performCommand((new CommandSourceStack(NULL, new Vec3(this.getX(), this.getY(), this.getZ()), Vec2.ZERO, _level, 4, "", new TextComponent(""), _level.getServer(), null)).withSuppressedOutput(), "stopsound @a neutral rainimator:null_boss_music");
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