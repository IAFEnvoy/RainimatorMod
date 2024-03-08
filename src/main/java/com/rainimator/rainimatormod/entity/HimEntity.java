package com.rainimator.rainimatormod.entity;

import com.rainimator.rainimatormod.RainimatorMod;
import com.rainimator.rainimatormod.registry.ModEffects;
import com.rainimator.rainimatormod.registry.ModEntities;
import com.rainimator.rainimatormod.registry.ModItems;
import com.rainimator.rainimatormod.util.MiscUtil;
import com.rainimator.rainimatormod.util.Timeout;
import net.minecraft.Util;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.ChatType;
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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class HimEntity extends Monster {
    private final ServerBossEvent bossInfo = new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.RED, BossEvent.BossBarOverlay.PROGRESS);

    public HimEntity(PlayMessages.SpawnEntity packet, Level world) {
        this(ModEntities.HIM.get(), world);
    }

    public HimEntity(EntityType<HimEntity> type, Level world) {
        super(type, world);
        this.xpReward = 0;
        this.setNoAi(false);
        this.setPersistenceRequired();
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ModItems.BLUE_DIAMOND_SWORD.get()));
        this.setItemSlot(EquipmentSlot.OFFHAND, new ItemStack(Items.IRON_SWORD));
        this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(Items.DIAMOND_HELMET));
        this.setItemSlot(EquipmentSlot.CHEST, new ItemStack(ModItems.HEROBRINE_ARMOR_CHESTPLATE.get()));
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3D);
        builder = builder.add(Attributes.MAX_HEALTH, 200.0D);
        builder = builder.add(Attributes.ARMOR, 30.0D);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 3.0D);
        builder = builder.add(Attributes.FOLLOW_RANGE, 64.0D);
        builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 1.0D);
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
    public SoundEvent getAmbientSound() {
        return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(RainimatorMod.MOD_ID, "him"));
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
        Vec3 _center = new Vec3(x, y, z);
        List<Entity> _entfound = this.level.getEntitiesOfClass(Entity.class, (new AABB(_center, _center)).inflate(4.5D), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
        for (Entity entityiterator : _entfound) {
            if (this.hasEffect(ModEffects.ICEPEOPLE.get())) {
                MiscUtil.playSound(this.level, this.getX(), this.getY(), this.getZ(), new ResourceLocation(RainimatorMod.MOD_ID, "him_skill"), 1.0F, 1.0F);
                this.teleportTo(x, y + 4.0D, z);
                continue;
            }
            if (this.hasEffect(MobEffects.POISON)) {
                this.removeAllEffects();
                continue;
            }
            if (this.hasEffect(MobEffects.WITHER)) {
                this.removeAllEffects();
                continue;
            }
            if (this.hasEffect(ModEffects.SOULDEATH.get())) {
                this.removeAllEffects();
                continue;
            }
            if (this.hasEffect(ModEffects.STUNNED.get())) {
                if (!this.level.isClientSide()) {
                    this.addEffect(new MobEffectInstance(ModEffects.PURIFICATION.get(), 200, 0));
                    this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 3));
                }
                continue;
            }
            if (Math.random() < 0.1D) {
                MiscUtil.playSound(this.level, this.getX(), this.getY(), this.getZ(), new ResourceLocation(RainimatorMod.MOD_ID, "him_skill"), 1.0F, 1.0F);
                if (this.level instanceof ServerLevel _level) {
                    LightningBolt entityToSpawn = EntityType.LIGHTNING_BOLT.create(_level);
                    if (entityToSpawn != null) {
                        entityToSpawn.moveTo(Vec3.atBottomCenterOf(new BlockPos(entityiterator.getX(), entityiterator.getY(), entityiterator.getZ())));
                        entityToSpawn.setVisualOnly(true);
                        _level.addFreshEntity(entityToSpawn);
                    }
                }
                this.level.setBlock(new BlockPos(entityiterator.getX(), entityiterator.getY(), entityiterator.getZ()), Blocks.FIRE.defaultBlockState(), 3);
                if (entityiterator instanceof LivingEntity _entity) {
                    if (!_entity.level.isClientSide())
                        _entity.addEffect(new MobEffectInstance(MobEffects.WITHER, 1200, 2));
                }
            } else if (Math.random() < 0.05D) {
                MiscUtil.playSound(this.level, this.getX(), this.getY(), this.getZ(), new ResourceLocation(RainimatorMod.MOD_ID, "him_skill"), 1.0F, 1.0F);
                if (this.level instanceof ServerLevel _level)
                    _level.sendParticles((ParticleOptions) ParticleTypes.END_ROD, x, y, z, 15, 0.5D, 0.5D, 0.5D, 0.5D);
                this.getNavigation().moveTo(x + Mth.nextInt(new Random(), -2, 2), y, z + Mth.nextInt(new Random(), -2, 2), 20.0D);
                if (!this.level.isClientSide())
                    this.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 300, 0));

            }
            if (this.hasEffect(MobEffects.MOVEMENT_SPEED))
                if (!this.level.isClientSide()) {
                    this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 100, 2));
                    this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 0));
                }
            if (this.getHealth() <= 100.0F)
                if (!this.level.isClientSide())
                    this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 2));
        }
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
        if (world instanceof ServerLevel _level) {
            LightningBolt entityToSpawn = EntityType.LIGHTNING_BOLT.create(_level);
            if (entityToSpawn != null) {
                entityToSpawn.moveTo(Vec3.atBottomCenterOf(new BlockPos(this.getX(), this.getY(), this.getZ())));
                entityToSpawn.setVisualOnly(true);
                _level.addFreshEntity(entityToSpawn);
            }
        }
        if (!world.isClientSide() && world.getServer() != null)
            world.getServer().getPlayerList().broadcastMessage(new TextComponent("挡我路者，必诛！"), ChatType.SYSTEM, Util.NIL_UUID);
        if (world.getDifficulty() != Difficulty.PEACEFUL) {
            if (!this.level.isClientSide() && this.getServer() != null)
                this.getServer().getCommands().performCommand(this.createCommandSourceStack().withSuppressedOutput().withPermission(4), "playsound rainimator:him_music_boss neutral @a ~ ~ ~");
            Runnable callback = () -> {
                if (this.isAlive())
                    if (!this.level.isClientSide() && this.getServer() != null)
                        this.getServer().getCommands().performCommand(this.createCommandSourceStack().withSuppressedOutput().withPermission(4), "playsound rainimator:him_music_boss neutral @a ~ ~ ~");
            };
            Timeout.create(5720, callback);
            Timeout.create(11440, callback);
            Timeout.create(17160, callback);
            Timeout.create(22880, callback);
            Timeout.create(28600, callback);
            Timeout.create(34320, callback);
        }

        return retval;
    }

    @Override
    public void baseTick() {
        super.baseTick();
        if (!this.level.isClientSide()) {
            this.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 80, 0));
            this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 80, 1));
            if (!this.isAlive() && this.level instanceof ServerLevel _level)
                _level.getServer().getCommands().performCommand((new CommandSourceStack(NULL, new Vec3(this.getX(), this.getY(), this.getZ()), Vec2.ZERO, _level, 4, "", new TextComponent(""), _level.getServer(), null)).withSuppressedOutput(), "stopsound @a neutral rainimator:him_music_boss");
        }
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