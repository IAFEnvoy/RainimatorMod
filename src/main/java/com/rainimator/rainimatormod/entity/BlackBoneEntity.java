package com.rainimator.rainimatormod.entity;

import com.rainimator.rainimatormod.RainimatorMod;
import com.rainimator.rainimatormod.registry.ModEffects;
import com.rainimator.rainimatormod.registry.ModEntities;
import com.rainimator.rainimatormod.registry.ModItems;
import com.rainimator.rainimatormod.registry.util.MonsterEntityBase;
import com.rainimator.rainimatormod.util.SoundUtil;
import com.rainimator.rainimatormod.util.Stage;
import com.rainimator.rainimatormod.util.Timeout;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.TranslatableComponent;
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
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class BlackBoneEntity extends MonsterEntityBase {
    public static Stage.StagedEntityTextureProvider texture = Stage.ofProvider("blackbone");
    private final ServerBossEvent bossInfo = new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.WHITE, BossEvent.BossBarOverlay.PROGRESS);

    public BlackBoneEntity(PlayMessages.SpawnEntity packet, Level world) {
        this(ModEntities.BLACKBONE.get(), world);
    }

    public BlackBoneEntity(EntityType<BlackBoneEntity> type, Level world) {
        super(type, world, MobType.UNDEAD);
        this.xpReward = 0;
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ModItems.BLACKBONE_THE_BLADE_SINGLE_HAND.get()));
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.35D);
        builder = builder.add(Attributes.MAX_HEALTH, 130.0D);
        builder = builder.add(Attributes.ARMOR, 30.0D);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 4.0D);
        builder = builder.add(Attributes.FOLLOW_RANGE, 64.0D);
        builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 5.0D);
        builder = builder.add(Attributes.ATTACK_KNOCKBACK, 1.0D);
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
        this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Player.class, false, false));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6, new FloatGoal(this));
    }

    @Override
    public SoundEvent getAmbientSound() {
        return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(RainimatorMod.MOD_ID, "blackbone"));
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
        double y = this.getY();
        Entity sourceentity = source.getEntity();
        if (sourceentity != null) {
            if (sourceentity instanceof LivingEntity _ent)
                this.setTarget(_ent);
            if (this.hasEffect(ModEffects.FEAR_DARK.get()))
                this.removeAllEffects();
            else if (this.hasEffect(ModEffects.ICE_PEOPLE.get()))
                this.removeAllEffects();
            else if (this.hasEffect(ModEffects.SOUL_DEATH.get()))
                this.removeAllEffects();
            else if (this.hasEffect(MobEffects.POISON))
                this.removeAllEffects();
            else if (this.hasEffect(MobEffects.WITHER))
                this.removeAllEffects();
            else {
                if (Math.random() < 0.2) {
                    if ((sourceentity instanceof LivingEntity _livEnt && _livEnt.hasEffect(ModEffects.FEAR_DARK.get()))) {
                        sourceentity.hurt(new DamageSource("death by dark").bypassArmor(), 12);
                        if (!_livEnt.level.isClientSide())
                            _livEnt.addEffect(new MobEffectInstance(MobEffects.WITHER, 500, 1));
                    }
                } else {
                    if (!(sourceentity instanceof LivingEntity _livEnt && _livEnt.hasEffect(ModEffects.FEAR_DARK.get()))) {
                        SoundUtil.playSound(this.level, this.getX(), this.getY(), this.getZ(), new ResourceLocation(RainimatorMod.MOD_ID, "blackbone_skill"), 1, 1);
                        if (this.level instanceof ServerLevel _level)
                            _level.sendParticles(ParticleTypes.ELECTRIC_SPARK, this.getX(), this.getY(), this.getZ(), 50, 1, 1, 1, 1);
                        if (sourceentity instanceof LivingEntity _entity && !_entity.level.isClientSide())
                            _entity.addEffect(new MobEffectInstance(ModEffects.FEAR_DARK.get(), 300, 0));
                        sourceentity.setSecondsOnFire(10);
                    }
                }
            }

            if (Math.random() < 0.1) {
                if (!this.level.isClientSide() && this.level.getServer() != null)
                    if (Math.random() < 0.3)
                        this.level.getServer().getPlayerList().broadcastMessage(new TranslatableComponent("entity.rainimator.blackbone.message1"), ChatType.SYSTEM, Util.NIL_UUID);
                    else if (Math.random() < 0.4)
                        this.level.getServer().getPlayerList().broadcastMessage(new TranslatableComponent("entity.rainimator.blackbone.message2"), ChatType.SYSTEM, Util.NIL_UUID);
                    else if (Math.random() < 0.5)
                        this.level.getServer().getPlayerList().broadcastMessage(new TranslatableComponent("entity.rainimator.blackbone.message3"), ChatType.SYSTEM, Util.NIL_UUID);
                    else
                        this.level.getServer().getPlayerList().broadcastMessage(new TranslatableComponent("entity.rainimator.blackbone.message4"), ChatType.SYSTEM, Util.NIL_UUID);

                if (!sourceentity.level.isClientSide() && sourceentity.getServer() != null)
                    sourceentity.getServer().getCommands().performCommand(sourceentity.createCommandSourceStack().withSuppressedOutput().withPermission(4), "title @p title {\"text\":\"！！！\",\"color\":\"red\"}");
                Timeout.create(50, () -> {
                    if (!this.level.isClientSide()) {
                        BlockPos pos = BlackBoneEntity.this.level.clip(new ClipContext(this.getEyePosition(1f), this.getEyePosition(1f).add(this.getViewVector(1f).scale(2)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, this)).getBlockPos();
                        this.level.explode(null, (pos.getX()), (y + 1), (pos.getZ()), 1, Explosion.BlockInteraction.NONE);
                    }

                    Runnable callback = () -> {
                        if (!this.level.isClientSide()) {
                            BlockPos pos = this.level.clip(new ClipContext(this.getEyePosition(1f), this.getEyePosition(1f).add(this.getViewVector(1f).scale(18)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, this)).getBlockPos();
                            this.level.explode(null, (pos.getX()), (y + 1), (pos.getZ()), 1, Explosion.BlockInteraction.NONE);
                        }
                    };
                    Timeout.create(5, callback);
                    Timeout.create(10, callback);
                    Timeout.create(15, callback);
                    Timeout.create(20, callback);
                    Timeout.create(25, callback);
                    Timeout.create(30, callback);
                    Timeout.create(35, callback);
                    Timeout.create(40, callback);
                });
            }
        }
        if (source == DamageSource.FALL)
            return false;
        if (source == DamageSource.DROWN)
            return false;
        if (source.isExplosion())
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
        if (world instanceof Level _level)
            SoundUtil.playSound(_level, this.getX(), this.getY(), this.getZ(), new ResourceLocation(RainimatorMod.MOD_ID, "blackbone_living"), 1.0F, 1.0F);

        if (world instanceof ServerLevel _level)
            _level.sendParticles((ParticleOptions) ParticleTypes.ELECTRIC_SPARK, this.getX(), this.getY(), this.getZ(), 50, 1.0D, 1.0D, 1.0D, 1.0D);
        if (world.getDifficulty() != Difficulty.PEACEFUL) {
            if (!this.level.isClientSide() && this.getServer() != null)
                this.getServer().getCommands().performCommand(this.createCommandSourceStack().withSuppressedOutput().withPermission(4), "playsound rainimator:blackbone_boss_music neutral @a ~ ~ ~");

            Runnable callback = () -> {
                if (this.isAlive())
                    if (!this.level.isClientSide() && this.getServer() != null)
                        this.getServer().getCommands().performCommand(this.createCommandSourceStack().withSuppressedOutput().withPermission(4), "playsound rainimator:blackbone_boss_music neutral @a ~ ~ ~");
            };
            Timeout.create(3960, callback);
            Timeout.create(7920, callback);
            Timeout.create(11880, callback);
            Timeout.create(15840, callback);
            Timeout.create(19800, callback);
            Timeout.create(23760, callback);
        }

        return retval;
    }

    @Override
    public void baseTick() {
        super.baseTick();
        if (this.hasEffect(ModEffects.STUNNED.get()))
            if (!this.level.isClientSide()) {
                this.addEffect(new MobEffectInstance(ModEffects.PURIFICATION.get(), 200, 0));
                this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 2));
            }
        if (!this.isAlive())
            SoundUtil.stopSound(this.level, new ResourceLocation(RainimatorMod.MOD_ID, "blackbone_boss_music"));
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