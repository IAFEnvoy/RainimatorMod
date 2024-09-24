package dev.rainimator.mod.entity;

import com.iafenvoy.neptune.object.EntityUtil;
import com.iafenvoy.neptune.object.SoundUtil;
import com.iafenvoy.neptune.object.VecUtil;
import com.iafenvoy.neptune.object.entity.MonsterFractionEntityBase;
import com.iafenvoy.neptune.render.Stage;
import com.iafenvoy.neptune.util.CommandHelper;
import com.iafenvoy.neptune.util.RandomHelper;
import com.iafenvoy.neptune.util.Timeout;
import dev.rainimator.mod.RainimatorMod;
import dev.rainimator.mod.registry.*;
import net.minecraft.block.Blocks;
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
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.*;

public class NaeusEntity extends MonsterFractionEntityBase {
    public static final Stage.StagedEntityTextureProvider texture = Stage.ofProvider(RainimatorMod.MOD_ID, "naeus");
    private final ServerBossBar bossInfo = new ServerBossBar(this.getDisplayName(), BossBar.Color.GREEN, BossBar.Style.PROGRESS);

    public NaeusEntity(EntityType<NaeusEntity> type, World world) {
        super(type, world, EntityGroup.UNDEAD, RainimatorFractions.NETHER);
        this.experiencePoints = 0;
        this.setPersistent();
        this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(RainimatorItems.NAEUS_SWORD.get()));
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.35D)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 200.0D)
                .add(EntityAttributes.GENERIC_ARMOR, 35.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0D)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 64.0D)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 10.0D)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 1.0D);
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
    public SoundEvent getAmbientSound() {
        return Registries.SOUND_EVENT.get(Identifier.of(RainimatorMod.MOD_ID, "nause"));
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
        final double y = this.getY();
        double z = this.getZ();
        Entity sourceentity = source.getAttacker();
        if (sourceentity != null) {
            if (sourceentity instanceof LivingEntity _ent) this.setTarget(_ent);
            if (this.hasStatusEffect(RainimatorEffects.ICE_PEOPLE.get())) this.clearStatusEffects();
            else if (this.hasStatusEffect(RainimatorEffects.SOUL_DEATH.get())) this.clearStatusEffects();
            else if (this.hasStatusEffect(StatusEffects.WITHER)) this.clearStatusEffects();
            else if (this.hasStatusEffect(StatusEffects.POISON)) this.clearStatusEffects();
            else if (Math.random() < 0.5D) {
                SoundUtil.playSound(this.getWorld(), x, y, z, RainimatorSounds.FIRE_SOUL.get(), 1.0F, 1.0F);
                if (this.getWorld() instanceof ServerWorld serverWorld) {
                    serverWorld.spawnParticles(RainimatorParticles.RED_FLOWER.get(), x, y, z, 20, 0.5D, 0.0D, 0.5D, 0.5D);
                    if (sourceentity instanceof LivingEntity living)
                        living.addStatusEffect(new StatusEffectInstance(RainimatorEffects.SOUL_DEATH.get(), 100, 1));
                    this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 300, 2));
                    this.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 300, 1));
                }
                sourceentity.setOnFireFor(10);
            }
            if (Math.random() < 0.5D) {
                this.getNavigation().startMovingTo(this.getX() + RandomHelper.nextDouble(1.0D, 6.0D), y, this.getZ() + RandomHelper.nextDouble(1.0D, 6.0D), 10.0D);
                SoundUtil.playSound(this.getWorld(), this.getX(), this.getY(), this.getZ(), RainimatorSounds.NAEUS_ROLL.get(), 1, 1);
                if (this.getWorld() instanceof ServerWorld serverWorld)
                    serverWorld.spawnParticles(ParticleTypes.SOUL, this.getX(), this.getY(), this.getZ(), 30, 0.5D, 0.5D, 0.5D, 0.5D);
            }
            if (Math.random() < 0.1D) {
                if (!this.getWorld().isClient)
                    this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100, 2));
                if (!this.getWorld().isClient && this.getWorld().getServer() != null)
                    if (Math.random() < 0.3D)
                        this.getWorld().getServer().getPlayerManager().broadcast(Text.translatable("entity.rainimator.naeus.message1"), false);
                    else if (Math.random() < 0.4D)
                        this.getWorld().getServer().getPlayerManager().broadcast(Text.translatable("entity.rainimator.naeus.message2"), false);
                    else if (Math.random() < 0.5D)
                        this.getWorld().getServer().getPlayerManager().broadcast(Text.translatable("entity.rainimator.naeus.message3"), false);
                    else
                        this.getWorld().getServer().getPlayerManager().broadcast(Text.translatable("entity.rainimator.naeus.message4"), false);
                CommandHelper.execute(this, "title @p title {\"text\":\"！！！\",\"color\":\"red\"}");

                Runnable callback = () -> {
                    BlockPos pos = this.getWorld().raycast(new RaycastContext(this.getCameraPosVec(1.0F), this.getCameraPosVec(1.0F).add(this.getRotationVec(1.0F).multiply(2.0D)), RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, NaeusEntity.this)).getBlockPos();
                    if (this.getWorld() instanceof ServerWorld serverWorld)
                        EntityUtil.lightening(serverWorld, pos.getX(), y, pos.getZ());
                    this.getWorld().setBlockState(VecUtil.createBlockPos(pos.getX(), y, pos.getZ()), Blocks.FIRE.getDefaultState(), 3);
                };
                Timeout.create(50, () -> {
                    BlockPos pos = this.getWorld().raycast(new RaycastContext(this.getCameraPosVec(1.0F), this.getCameraPosVec(1.0F).add(this.getRotationVec(1.0F).multiply(1.0D)), RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, NaeusEntity.this)).getBlockPos();
                    if (this.getWorld() instanceof ServerWorld serverWorld)
                        EntityUtil.lightening(serverWorld, pos.getX(), y, pos.getZ());
                    this.getWorld().setBlockState(VecUtil.createBlockPos(pos.getX(), y, pos.getZ()), Blocks.FIRE.getDefaultState(), 3);
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
        return super.damage(source, amount);
    }

    @Override
    public boolean isInvulnerableTo(DamageSource damageSource) {
        if (damageSource.isOf(DamageTypes.FALL)) return true;
        if (damageSource.isOf(DamageTypes.DROWN)) return true;
        if (damageSource.isOf(DamageTypes.LIGHTNING_BOLT)) return true;
        if (damageSource.isOf(DamageTypes.EXPLOSION)) return true;
        if (damageSource.isOf(DamageTypes.WITHER)) return true;
        if (damageSource.isOf(DamageTypes.WITHER_SKULL)) return true;
        return super.isInvulnerableTo(damageSource);
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason reason, EntityData livingdata, NbtCompound tag) {
        EntityData data = super.initialize(world, difficulty, reason, livingdata, tag);
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        ServerWorld serverWorld = world.toServerWorld();
        SoundUtil.playSound(serverWorld, x, y, z,RainimatorSounds.NAEUS_LIVING.get(), 1.0F, 1.0F);
        serverWorld.spawnParticles(RainimatorParticles.RED_FLOWER.get(), x, y, z, 50, 0.5D, 1.0D, 0.5D, 0.01D);
        if (world.getDifficulty() != Difficulty.PEACEFUL) {
            Runnable callback = () -> {
                if (this.isAlive())
                    SoundUtil.playSound(this.getWorld(), x, y, z, RainimatorSounds.NAEUS_BOSS_MUSIC.get(), 1, 1);
            };
            Timeout.create(0, callback);
            Timeout.create(4300, callback);
            Timeout.create(8600, callback);
            Timeout.create(12900, callback);
            Timeout.create(17200, callback);
            Timeout.create(21500, callback);
            Timeout.create(25800, callback);
        }
        return data;
    }

    @Override
    public void baseTick() {
        super.baseTick();
        double y = this.getY();
        if (this.getWorld() instanceof ServerWorld serverWorld) {
            BlockPos blockPos = this.getWorld().raycast(new RaycastContext(this.getCameraPosVec(1.0F), this.getCameraPosVec(1.0F).add(this.getRotationVec(1.0F).multiply(-1.0D)), RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, this)).getBlockPos();
            serverWorld.spawnParticles(RainimatorParticles.RED_FLOWER.get(), blockPos.getX(), y + 1.4D, blockPos.getZ(), 5, 0.5D, 0.0D, 0.5D, 0.1D);
        }
        if (this.getHealth() <= 75.0F) {
            if (!this.getWorld().isClient) {
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100, 2));
                if (this.hasStatusEffect(RainimatorEffects.STUNNED.get())) {
                    this.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 100, 4));
                    this.removeStatusEffect(RainimatorEffects.STUNNED.get());
                }
            }
        }
        if (!this.isAlive())
            SoundUtil.stopSound(this.getWorld(), Identifier.of(RainimatorMod.MOD_ID, "naeus_boss_music"));
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