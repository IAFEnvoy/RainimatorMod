package com.rainimator.rainimatormod.entity;

import com.rainimator.rainimatormod.RainimatorMod;
import com.rainimator.rainimatormod.registry.ModEffects;
import com.rainimator.rainimatormod.registry.ModEntities;
import com.rainimator.rainimatormod.registry.ModItems;
import com.rainimator.rainimatormod.registry.ModParticleTypes;
import com.rainimator.rainimatormod.util.MiscUtil;
import com.rainimator.rainimatormod.util.Timeout;
import net.minecraft.commands.CommandSource;
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
import net.minecraft.util.Mth;
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
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Random;

public class CerisEntity extends Monster {
    private final ServerBossEvent bossInfo = new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.PURPLE, BossEvent.BossBarOverlay.PROGRESS);

    public CerisEntity(PlayMessages.SpawnEntity packet, Level world) {
        this(ModEntities.CERIS.get(), world);
    }

    public CerisEntity(EntityType<CerisEntity> type, Level world) {
        super(type, world);
        this.xpReward = 0;
        this.setNoAi(false);
        this.setPersistenceRequired();
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ModItems.END_BLADE.get()));
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.35D);
        builder = builder.add(Attributes.MAX_HEALTH, 200.0D);
        builder = builder.add(Attributes.ARMOR, 35.0D);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 5.0D);
        builder = builder.add(Attributes.FOLLOW_RANGE, 64.0D);
        builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 5.0D);
        builder = builder.add(Attributes.ATTACK_KNOCKBACK, 5.0D);
        return builder;
    }

    @Override
    public @NotNull Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
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
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6, new FloatGoal(this));
    }

    @Override
    public @NotNull MobType getMobType() {
        return MobType.UNDEFINED;
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
    public boolean hurt(DamageSource source, float amount) {
        Entity sourceentity = source.getEntity();
        if (sourceentity instanceof LivingEntity _ent)
            this.setTarget(_ent);
        if (this.hasEffect(ModEffects.STUNNED.get())) {
            if (!this.level.isClientSide()) {
                this.addEffect(new MobEffectInstance(ModEffects.PURIFICATION.get(), 100, 0));
                this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 3));
            }
        } else {

            LivingEntity _entity = this;
            if (!_entity.level.isClientSide())
                _entity.addEffect(new MobEffectInstance(ModEffects.PURIFICATION.get(), 100, 0));

            if (Math.random() < 0.3D) {
                MiscUtil.playSound(this.level, this.getX(), this.getY(), this.getZ(), new ResourceLocation("entity.enderman.teleport"), 4.0F, 1.0F);

                if (this.level instanceof ServerLevel) {
                    ServerLevel _level = (ServerLevel) this.level;
                    _level.sendParticles((ParticleOptions) ModParticleTypes.PURPLELIGHT.get(), this.getX(), this.getY(), this.getZ(), 50, 0.5D, 0.1D, 0.5D, 0.3D);
                }
                this.getNavigation().moveTo(this.getX() + Mth.nextInt(new Random(), 3, 9), this.getY(), this.getZ() + Mth.nextInt(new Random(), 3, 9), 20.0D);
                if (!this.level.isClientSide())
                    this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 2));
                if (sourceentity instanceof LivingEntity livingEntity) {
                    if (!livingEntity.level.isClientSide())
                        livingEntity.addEffect(new MobEffectInstance(ModEffects.FEARDARK.get(), 200, 0));
                    if (!livingEntity.hasEffect(MobEffects.GLOWING)) {
                        MiscUtil.playSound(this.level, this.getX(), this.getY(), this.getZ(), new ResourceLocation(RainimatorMod.MOD_ID, "ceris_f"), 1.0F, 1.0F);
                        if (livingEntity instanceof LivingEntity)
                            if (!livingEntity.level.isClientSide())
                                livingEntity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 100, 0));
                    }
                    if (sourceentity instanceof LivingEntity && livingEntity.hasEffect(MobEffects.GLOWING)) {
                        if (!sourceentity.level.isClientSide()) {
                            ((LivingEntity) sourceentity).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 3));
                            ((LivingEntity) sourceentity).addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 0));
                        }
                        Timeout.create(60, () -> {
                            sourceentity.teleportTo(this.level
                                    .clip(new ClipContext(this.getEyePosition(1.0F), this.getEyePosition(1.0F).add(this.getViewVector(1.0F).scale(1.0D)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, this)).getBlockPos().getX(), this
                                    .getY(), this.level.clip(new ClipContext(this.getEyePosition(1.0F), this.getEyePosition(1.0F).add(this.getViewVector(1.0F).scale(1.0D)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, this))
                                    .getBlockPos().getZ());
                            if (sourceentity instanceof ServerPlayer _serverPlayer) {
                                _serverPlayer.connection.teleport(this.level
                                        .clip(new ClipContext(this.getEyePosition(1.0F), this.getEyePosition(1.0F).add(this.getViewVector(1.0F).scale(1.0D)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, this)).getBlockPos()
                                        .getX(), this
                                        .getY(), this.level.clip(new ClipContext(this.getEyePosition(1.0F), this.getEyePosition(1.0F).add(this.getViewVector(1.0F).scale(1.0D)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, this))
                                        .getBlockPos().getZ(), sourceentity
                                        .getYRot(), sourceentity.getXRot());
                            }
                            Level levelAccessor = this.level;
                            MiscUtil.playSound(this.level, this.getX(), this.getY(), this.getZ(), new ResourceLocation(RainimatorMod.MOD_ID, "ceris_skill"), 1.0F, 1.0F);
                            if (levelAccessor instanceof ServerLevel _level)
                                _level.sendParticles((ParticleOptions) ModParticleTypes.PURPLELIGHT.get(), this.getX(), this.getY(), this.getZ(), 50, 0.5D, 0.1D, 0.5D, 0.3D);
                        });
                    }
                }
            }
        }

        if (source.getDirectEntity() instanceof AbstractArrow)
            return false;
        if (source == DamageSource.FALL)
            return false;
        if (source == DamageSource.DROWN)
            return false;
        if (source == DamageSource.LIGHTNING_BOLT)
            return false;
        if (source.isExplosion())
            return false;
        if (source.getMsgId().equals("trident"))
            return false;
        if (source == DamageSource.DRAGON_BREATH)
            return false;
        if (source == DamageSource.WITHER)
            return false;
        if (source.getMsgId().equals("witherSkull"))
            return false;
        return super.hurt(source, amount);
    }

    @Override
    public void die(@NotNull DamageSource source) {
        super.die(source);
        MiscUtil.playSound(this.level, this.getX(), this.getY(), this.getZ(), new ResourceLocation(RainimatorMod.MOD_ID, "ceris_death"), 1.0F, 1.0F);
        if (this.level instanceof ServerLevel _level)
            _level.sendParticles((ParticleOptions) ModParticleTypes.PURPLELIGHT.get(), this.getX(), this.getY(), this.getZ(), 60, 0.5D, 1.0D, 0.5D, 0.5D);
    }

    @Override
    public SpawnGroupData finalizeSpawn(@NotNull ServerLevelAccessor world, @NotNull DifficultyInstance difficulty, @NotNull MobSpawnType reason, @Nullable SpawnGroupData livingdata, @Nullable CompoundTag tag) {
        SpawnGroupData ret_val = super.finalizeSpawn(world, difficulty, reason, livingdata, tag);
        Entity entity = this;
        MiscUtil.playSound(this.level, this.getX(), this.getY(), this.getZ(), new ResourceLocation(RainimatorMod.MOD_ID, "curis_live"), 1.0F, 1.0F);

        if (world instanceof ServerLevel _level)
            _level.sendParticles((ParticleOptions) ModParticleTypes.PURPLELIGHT.get(), this.getX(), this.getY(), this.getZ(), 50, 0.5D, 0.5D, 0.5D, 0.5D);
        if (world.getDifficulty() != Difficulty.PEACEFUL) {
            if (!entity.level.isClientSide() && entity.getServer() != null)
                entity.getServer().getCommands().performCommand(entity.createCommandSourceStack().withSuppressedOutput().withPermission(4), "/playsound rainimator:ceris_boss_music neutral @a ~ ~ ~");

            Runnable callback = () -> {
                if (entity.isAlive()) {
                    if (!entity.level.isClientSide() && entity.getServer() != null) {
                        entity.getServer().getCommands().performCommand(entity.createCommandSourceStack().withSuppressedOutput().withPermission(4), "/playsound rainimator:ceris_boss_music neutral @a ~ ~ ~");
                    }
                }
            };
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
        if (!this.level.isClientSide()) {
            this.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 80, 1));
            this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 80, 1));
        }
        if (!this.isAlive() && this.level instanceof ServerLevel _level)
            _level.getServer().getCommands().performCommand((new CommandSourceStack(CommandSource.NULL, new Vec3(this.getX(), this.getY(), this.getZ()), Vec2.ZERO, _level, 4, "", new TextComponent(""), _level.getServer(), null)).withSuppressedOutput(), "stopsound @a neutral rainimator:ceris_boss_music");
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