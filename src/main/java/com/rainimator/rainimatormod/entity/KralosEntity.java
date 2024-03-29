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
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class KralosEntity extends MonsterEntityBase {
    public static Stage.StagedEntityTextureProvider texture = Stage.ofProvider("kralos");
    private final ServerBossEvent bossInfo = new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.RED, BossEvent.BossBarOverlay.PROGRESS);

    public KralosEntity(PlayMessages.SpawnEntity packet, Level world) {
        this(ModEntities.KRALOS.get(), world);
    }

    public KralosEntity(EntityType<KralosEntity> type, Level world) {
        super(type, world, MobType.UNDEAD);
        this.xpReward = 0;
        this.setPersistenceRequired();
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ModItems.FALLEN_SOUL_AXE.get()));
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3D);
        builder = builder.add(Attributes.MAX_HEALTH, 100.0D);
        builder = builder.add(Attributes.ARMOR, 25.0D);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 5.0D);
        builder = builder.add(Attributes.FOLLOW_RANGE, 64.0D);
        builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 5.0D);
        builder = builder.add(Attributes.ATTACK_KNOCKBACK, 1.0D);
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
        return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.wither_skeleton.hurt"));
    }

    @Override
    public SoundEvent getDeathSound() {
        return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.wither_skeleton.death"));
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        Entity sourceentity = source.getEntity();
        if (sourceentity != null) {
            if (sourceentity instanceof LivingEntity _entity) {
                this.setTarget(_entity);
                if (Math.random() < 0.2D)
                    if (!_entity.level.isClientSide())
                        _entity.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 1));
            }
            if (this.getHealth() < 60.0F) {
                if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SHARPNESS, this.getMainHandItem()) == 0) {
                    if (!this.level.isClientSide())
                        this.addEffect(new MobEffectInstance(ModEffects.PURIFICATION.get(), 9999, 0));
                    this.getMainHandItem().enchant(Enchantments.SHARPNESS, 4);
                    if (this.level instanceof ServerLevel) {
                        ServerLevel _level = (ServerLevel) this.level;
                        LightningBolt entityToSpawn = EntityType.LIGHTNING_BOLT.create(_level);
                        if (entityToSpawn != null) {
                            entityToSpawn.moveTo(Vec3.atBottomCenterOf(new BlockPos(x, y, z)));
                            entityToSpawn.setVisualOnly(true);
                            _level.addFreshEntity(entityToSpawn);
                        }
                    }

                    this.level.setBlock(new BlockPos(x, y, z), Blocks.FIRE.defaultBlockState(), 3);
                    SoundUtil.playSound(this.level, this.getX(), this.getY(), this.getZ(), new ResourceLocation("entity.enderman.scream"), 1.0F, 1.0F);
                    if (this.level instanceof ServerLevel _level)
                        _level.sendParticles((ParticleOptions) ParticleTypes.SOUL, x, y, z, 200, 2.0D, 3.0D, 2.0D, 0.001D);
                    if (!this.level.isClientSide() && this.level.getServer() != null)
                        this.level.getServer().getPlayerList().broadcastMessage(new TranslatableComponent("entity.rainimator.kralos.message"), ChatType.SYSTEM, Util.NIL_UUID);
                }
            }
        }

        if (source == DamageSource.FALL)
            return false;
        if (source == DamageSource.CACTUS)
            return false;
        if (source == DamageSource.LIGHTNING_BOLT)
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
            SoundUtil.playSound(_level, this.getX(), this.getY(), this.getZ(), new ResourceLocation("entity.wither.ambient"), 1.0F, 1.0F);
        if (world instanceof ServerLevel _level)
            _level.sendParticles((ParticleOptions) ParticleTypes.SOUL, x, y, z, 100, 3.0D, 4.0D, 3.0D, 0.001D);
        if (world.getDifficulty() != Difficulty.PEACEFUL) {
            if (!this.level.isClientSide() && this.getServer() != null)
                this.getServer().getCommands().performCommand(this.createCommandSourceStack().withSuppressedOutput().withPermission(4), "playsound rainimator:kralos_boss_music neutral @a ~ ~ ~");
            Runnable callback = () -> {
                if (this.isAlive()) {
                    if (!this.level.isClientSide() && this.getServer() != null) {
                        this.getServer().getCommands().performCommand(this.createCommandSourceStack().withSuppressedOutput().withPermission(4), "playsound rainimator:kralos_boss_music neutral @a ~ ~ ~");
                    }
                }
            };
            Timeout.create(2480, callback);
            Timeout.create(4960, callback);
            Timeout.create(7440, callback);
            Timeout.create(9920, callback);
            Timeout.create(12400, callback);
            Timeout.create(14880, callback);
            Timeout.create(17360, callback);
            Timeout.create(19840, callback);
        }
        return retval;
    }

    @Override
    public void baseTick() {
        super.baseTick();
        if (!this.level.isClientSide()) {
            this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 80, 0));
            this.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 80, 0));
        }
        if (!this.isAlive())
            SoundUtil.stopSound(this.level, new ResourceLocation(RainimatorMod.MOD_ID, "kralos_boss_music"));
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