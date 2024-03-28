package com.rainimator.rainimatormod.entity;

import com.rainimator.rainimatormod.RainimatorMod;
import com.rainimator.rainimatormod.registry.ModEffects;
import com.rainimator.rainimatormod.registry.ModEntities;
import com.rainimator.rainimatormod.registry.ModItems;
import com.rainimator.rainimatormod.registry.ModParticleTypes;
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
import net.minecraft.sounds.SoundSource;
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
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Objects;

public class NaeusKingEntity extends MonsterEntityBase {
    public static Stage.StagedEntityTextureProvider texture = Stage.ofProvider("naeus_king");
    private final ServerBossEvent bossInfo = new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.RED, BossEvent.BossBarOverlay.PROGRESS);

    public NaeusKingEntity(PlayMessages.SpawnEntity packet, Level world) {
        this(ModEntities.NAEUS_KING.get(), world);
    }

    public NaeusKingEntity(EntityType<NaeusKingEntity> type, Level world) {
        super(type, world, MobType.UNDEAD);
        this.xpReward = 0;
        this.setPersistenceRequired();
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ModItems.NETHER_SPEAR.get()));
        this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(ModItems.NETHER_THE_CROWN_HELMET.get()));
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.35D);
        builder = builder.add(Attributes.MAX_HEALTH, 250.0D);
        builder = builder.add(Attributes.ARMOR, 50.0D);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 3.0D);
        builder = builder.add(Attributes.FOLLOW_RANGE, 64.0D);
        builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 10.0D);
        builder = builder.add(Attributes.ATTACK_KNOCKBACK, 5.0D);
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
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6, new FloatGoal(this));
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
        double x = this.getX();
        final double y = this.getY();
        double z = this.getZ();
        Entity sourceentity = source.getEntity();
        if (sourceentity != null) {
            if (sourceentity instanceof LivingEntity _ent)
                this.setTarget(_ent);
            if (this.hasEffect(ModEffects.ICE_PEOPLE.get()))
                this.removeAllEffects();
            else {
                if (Math.random() < 0.5D) {
                    SoundUtil.playSound(this.level, x, y, z, new ResourceLocation(RainimatorMod.MOD_ID, "fire_soul"), 1.0F, 1.0F);
                    if (this.level instanceof ServerLevel _level)
                        _level.sendParticles((ParticleOptions) ParticleTypes.SOUL_FIRE_FLAME, x, y, z, 100, 1.0D, 2.0D, 1.0D, 2.0E-4D);
                    if (!this.level.isClientSide()) {
                        this.addEffect(new MobEffectInstance(ModEffects.PURIFICATION.get(), 100, 0));
                        this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 2));
                        this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 1));
                    }
                    if (sourceentity instanceof LivingEntity _entity)
                        if (!_entity.level.isClientSide())
                            _entity.addEffect(new MobEffectInstance(ModEffects.SOUL_DEATH.get(), 100, 0));
                    sourceentity.hurt(DamageSource.MAGIC, 5.0F);
                }
                if (Math.random() < 0.1D) {
                    if (!this.level.isClientSide() && this.level.getServer() != null)
                        if (Math.random() < 0.3D)
                            (this.level).getServer().getPlayerList().broadcastMessage(new TranslatableComponent("entity.rainimator.naeusking.message1"), ChatType.SYSTEM, Util.NIL_UUID);
                        else if (Math.random() < 0.4D)
                            this.level.getServer().getPlayerList().broadcastMessage(new TranslatableComponent("entity.rainimator.naeusking.message2"), ChatType.SYSTEM, Util.NIL_UUID);
                        else if (Math.random() < 0.45D)
                            this.level.getServer().getPlayerList().broadcastMessage(new TranslatableComponent("entity.rainimator.naeusking.message3"), ChatType.SYSTEM, Util.NIL_UUID);
                        else if (Math.random() < 0.5D)
                            this.level.getServer().getPlayerList().broadcastMessage(new TranslatableComponent("entity.rainimator.naeusking.message4"), ChatType.SYSTEM, Util.NIL_UUID);
                        else
                            this.level.getServer().getPlayerList().broadcastMessage(new TranslatableComponent("entity.rainimator.naeusking.message5"), ChatType.SYSTEM, Util.NIL_UUID);

                    if (!sourceentity.level.isClientSide() && sourceentity.getServer() != null)
                        sourceentity.getServer().getCommands().performCommand(sourceentity.createCommandSourceStack().withSuppressedOutput().withPermission(4), "title @p title {\"text\":\"！！！\",\"color\":\"red\"}");

                    BlockPos pos = this.level.clip(new ClipContext(this.getEyePosition(1.0F), this.getEyePosition(1.0F).add(this.getViewVector(1.0F).scale(2.0D)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, NaeusKingEntity.this)).getBlockPos();
                    Runnable callback = () -> {
                        LevelAccessor levelAccessor = this.level;
                        BlockPos pos1 = this.level.clip(new ClipContext(this.getEyePosition(1.0F), this.getEyePosition(1.0F).add(this.getViewVector(1.0F).scale(0.0D)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, NaeusKingEntity.this)).getBlockPos();
                        if (levelAccessor instanceof ServerLevel _level) {
                            LightningBolt entityToSpawn = EntityType.LIGHTNING_BOLT.create(_level);
                            if (entityToSpawn != null) {
                                entityToSpawn.moveTo(Vec3.atBottomCenterOf(new BlockPos(pos.getX(), y, pos1.getZ())));
                                entityToSpawn.setVisualOnly(true);
                                _level.addFreshEntity(entityToSpawn);
                            }
                        }
                        this.level.setBlock(new BlockPos(pos.getX(), y, pos1.getZ()), Blocks.FIRE.defaultBlockState(), 3);
                        if (levelAccessor instanceof ServerLevel _level) {
                            LightningBolt entityToSpawn = EntityType.LIGHTNING_BOLT.create(_level);
                            if (entityToSpawn != null) {
                                entityToSpawn.moveTo(Vec3.atBottomCenterOf(new BlockPos(pos1.getX(), y, pos.getZ())));
                                entityToSpawn.setVisualOnly(true);
                                _level.addFreshEntity(entityToSpawn);
                            }
                        }
                        this.level.setBlock(new BlockPos(pos1.getX(), y, pos.getZ()), Blocks.FIRE.defaultBlockState(), 3);
                    };
                    Timeout.create(50, () -> {
                        LevelAccessor levelAccessor = this.level;
                        if (levelAccessor instanceof ServerLevel _level) {
                            LightningBolt entityToSpawn = EntityType.LIGHTNING_BOLT.create(_level);
                            if (entityToSpawn != null) {
                                entityToSpawn.moveTo(Vec3.atBottomCenterOf(new BlockPos(pos.getX(), y, pos.getZ())));
                                entityToSpawn.setVisualOnly(true);
                                _level.addFreshEntity(entityToSpawn);
                            }
                        }

                        this.level.setBlock(new BlockPos(pos.getX(), y, pos.getZ()), Blocks.FIRE.defaultBlockState(), 3);
                        Timeout.create(2, callback);
                        Timeout.create(4, callback);
                        Timeout.create(6, callback);
                        Timeout.create(8, callback);
                        Timeout.create(10, callback);
                        Timeout.create(12, callback);
                        Timeout.create(14, callback);
                        Timeout.create(16, callback);
                        Timeout.create(18, callback);
                        Timeout.create(20, callback);
                    });
                }
            }
        }

        if (source.getDirectEntity() instanceof AbstractArrow)
            return false;
        if (source == DamageSource.FALL)
            return false;
        if (source == DamageSource.CACTUS)
            return false;
        if (source == DamageSource.DROWN)
            return false;
        if (source == DamageSource.LIGHTNING_BOLT)
            return false;
        if (source.isExplosion())
            return false;
        if (source.getMsgId().equals("trident"))
            return false;
        if (source == DamageSource.ANVIL)
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
    public SpawnGroupData finalizeSpawn(@NotNull ServerLevelAccessor world, @NotNull DifficultyInstance difficulty, @NotNull MobSpawnType reason, @Nullable SpawnGroupData livingdata, @Nullable CompoundTag tag) {
        SpawnGroupData retval = super.finalizeSpawn(world, difficulty, reason, livingdata, tag);
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        if (world instanceof Level _level) {
            if (!_level.isClientSide())
                _level.playSound(null, new BlockPos(x, y, z), Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(RainimatorMod.MOD_ID, "naeus_living"))), SoundSource.NEUTRAL, 1.0F, 1.0F);
            else
                _level.playLocalSound(x, y, z, Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(RainimatorMod.MOD_ID, "naeus_living"))), SoundSource.NEUTRAL, 1.0F, 1.0F, false);
        }
        if (world instanceof ServerLevel _level)
            _level.sendParticles((ParticleOptions) ModParticleTypes.REDFLOWER.get(), x, y, z, 50, 0.5D, 1.0D, 0.5D, 0.01D);
        if (world.getDifficulty() != Difficulty.PEACEFUL) {
            if (!this.level.isClientSide() && this.getServer() != null)
                this.getServer().getCommands().performCommand(this.createCommandSourceStack().withSuppressedOutput().withPermission(4), "playsound rainimator:naeus_boss_music neutral @a ~ ~ ~");
            Runnable callback = () -> {
                if (((Entity) NaeusKingEntity.this).isAlive())
                    if (!this.level.isClientSide() && this.getServer() != null)
                        this.getServer().getCommands().performCommand(this.createCommandSourceStack().withSuppressedOutput().withPermission(4), "playsound rainimator:naeus_boss_music neutral @a ~ ~ ~");
            };
            Timeout.create(4300, callback);
            Timeout.create(8600, callback);
            Timeout.create(12900, callback);
            Timeout.create(17200, callback);
            Timeout.create(21500, callback);
            Timeout.create(25800, callback);
        }

        return retval;
    }

    @Override
    public void baseTick() {
        super.baseTick();
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        if (this.hasEffect(ModEffects.ICE_PEOPLE.get()) || this.hasEffect(ModEffects.FEAR_DARK.get()) || this.hasEffect(ModEffects.SOUL_DEATH.get())) {
            this.removeAllEffects();
            this.teleportTo(x, (y + 3), z);
        }
        if (!this.isAlive())
            SoundUtil.stopSound(this.level, new ResourceLocation(RainimatorMod.MOD_ID, "naeus_boss_music"));
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