package com.rainimator.rainimatormod.entity;

import com.rainimator.rainimatormod.RainimatorMod;
import com.rainimator.rainimatormod.registry.ModEffects;
import com.rainimator.rainimatormod.registry.ModEntities;
import com.rainimator.rainimatormod.registry.ModItems;
import com.rainimator.rainimatormod.registry.ModParticleTypes;
import com.rainimator.rainimatormod.util.SoundUtil;
import com.rainimator.rainimatormod.util.Timeout;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.BossEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class KlausEntity extends Monster {
    private final ServerBossEvent bossInfo = new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.BLUE, BossEvent.BossBarOverlay.PROGRESS);

    public KlausEntity(PlayMessages.SpawnEntity packet, Level world) {
        this(ModEntities.KLAUS.get(), world);
    }

    public KlausEntity(EntityType<KlausEntity> type, Level world) {
        super(type, world);
        this.xpReward = 0;
        this.setNoAi(false);
        this.setPersistenceRequired();
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ModItems.DEEP_WAR_HAMMER.get()));
        this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(ModItems.KING_NORMAL_CROWN.get()));
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3D);
        builder = builder.add(Attributes.MAX_HEALTH, 180.0D);
        builder = builder.add(Attributes.ARMOR, 20.0D);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 5.0D);
        builder = builder.add(Attributes.FOLLOW_RANGE, 64.0D);
        builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 5.0D);
        builder = builder.add(Attributes.ATTACK_KNOCKBACK, 1.0D);
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
        this.goalSelector.addGoal(5, new OpenDoorGoal(this, true));
        this.goalSelector.addGoal(6, new OpenDoorGoal(this, false));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(8, new FloatGoal(this));
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
        return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.vindicator.hurt"));
    }

    @Override
    public SoundEvent getDeathSound() {
        return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.vindicator.death"));
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        Entity sourceentity = source.getEntity();
        if (sourceentity != null) {
            if (sourceentity instanceof LivingEntity _ent)
                this.setTarget(_ent);
            if (this.getMainHandItem().getItem() == ModItems.DEEP_WAR_HAMMER.get() || this.getMainHandItem().getItem() == ModItems.LASER_SWORD.get()) {
                if (Math.random() < 0.3) {
                    ItemStack _setstack = new ItemStack(ModItems.LASER_SWORD.get());
                    _setstack.setCount(1);
                    this.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
                } else if (Math.random() < 0.4) {
                    ItemStack _setstack = new ItemStack(ModItems.DEEP_WAR_HAMMER.get());
                    _setstack.setCount(1);
                    this.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
                }
            }
            if ((EnchantmentHelper.getItemEnchantmentLevel(Enchantments.ALL_DAMAGE_PROTECTION, ((LivingEntity) this).getItemBySlot(EquipmentSlot.HEAD)) != 0)) {
                if (!this.level.isClientSide())
                    this.addEffect(new MobEffectInstance(ModEffects.PURIFICATION.get(), 200, 0));
                if (!this.level.isClientSide())
                    this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 2));
                if (Math.random() < 0.1) {
                    if (!this.level.isClientSide())
                        this.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 0));
                    SoundUtil.playSound(this.level, x, y, z, new ResourceLocation("block.anvil.land"), 1, 1);
                    if (this.level instanceof ServerLevel _level)
                        _level.sendParticles(ParticleTypes.TOTEM_OF_UNDYING, x, y, z, 200, 0, 10, 0, 0.002);
                }
            }
            if ((EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SHARPNESS, this.getMainHandItem()) != 0)) {
                if (!this.level.isClientSide())
                    this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 0));
                if (Math.random() < 0.1) {
                    if (sourceentity instanceof LivingEntity _entity && !_entity.level.isClientSide())
                        _entity.addEffect(new MobEffectInstance(ModEffects.STUNNED.get(), 100, 0));
                    SoundUtil.playSound(this.level, x, y, z, new ResourceLocation(RainimatorMod.MOD_ID, "stunned"), 1, 1);
                    if ((LevelAccessor) this.level instanceof ServerLevel _level)
                        _level.sendParticles((SimpleParticleType) (ModParticleTypes.YELLOWSTEARS.get()), x, y, z, 50, 1, 2, 1, 1);
                }
            }
            if (this.getMainHandItem().getItem() == ModItems.SOUL_RAIDING_HAMMER.get()) {
                if (!this.level.isClientSide())
                    this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 1));
                if (Math.random() < 0.1) {
                    SoundUtil.playSound(this.level, x, y, z, new ResourceLocation("block.anvil.land"), 1, 1);
                    if (this.level instanceof ServerLevel _level)
                        _level.sendParticles(ParticleTypes.END_ROD, x, y, z, 100, 2, 3, 2, 0.002);
                    if (sourceentity instanceof LivingEntity _entity && !_entity.level.isClientSide())
                        _entity.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 100, 1));
                }
            }
            if (this.getMainHandItem().getItem() == ModItems.SEIZING_SHADOW_HALBERD.get()) {
                if (!this.level.isClientSide())
                    this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 2));
                if (Math.random() < 0.1) {
                    SoundUtil.playSound(this.level, x, y, z, new ResourceLocation("block.anvil.land"), 1, 1);
                    if ( this.level instanceof ServerLevel _level)
                        _level.sendParticles(ParticleTypes.END_ROD, x, y, z, 100, 2, 3, 2, 0.002);
                    if (sourceentity instanceof LivingEntity _entity && !_entity.level.isClientSide()) {
                        _entity.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 100, 1));
                        _entity.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 1));
                        _entity.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 1));
                    }
                }
            }
        }
        if (source.getDirectEntity() instanceof AbstractArrow)
            return false;
        if (source == DamageSource.FALL)
            return false;
        if (source == DamageSource.CACTUS)
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
        if (world.getDifficulty() != Difficulty.PEACEFUL) {
            if (!this.level.isClientSide() && this.getServer() != null)
                this.getServer().getCommands().performCommand(this.createCommandSourceStack().withSuppressedOutput().withPermission(4), "playsound rainimator:klaus_boss_music neutral @a ~ ~ ~");
            Runnable callback = () -> {
                if (this.isAlive())
                    if (!this.level.isClientSide() && this.getServer() != null)
                        this.getServer().getCommands().performCommand(this.createCommandSourceStack().withSuppressedOutput().withPermission(4), "playsound rainimator:klaus_boss_music neutral @a ~ ~ ~");
            };
            Timeout.create(6060, callback);
            Timeout.create(12120, callback);
            Timeout.create(18180, callback);
            Timeout.create(24240, callback);
            Timeout.create(30300, callback);
            Timeout.create(36360, callback);
        }
        return retval;
    }

    @Override
    public void baseTick() {
        super.baseTick();
        if (!this.isAlive())
            SoundUtil.stopSound(this.level, new ResourceLocation(RainimatorMod.MOD_ID, "klaus_boss_music"));
        if (!this.level.isClientSide())
            this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 80, 0));
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