package dev.rainimator.mod.entity;

import com.iafenvoy.neptune.object.DamageUtil;
import com.iafenvoy.neptune.object.SoundUtil;
import com.iafenvoy.neptune.object.entity.MonsterFractionEntityBase;
import com.iafenvoy.neptune.render.Stage;
import com.iafenvoy.neptune.util.CommandHelper;
import com.iafenvoy.neptune.util.Timeout;
import dev.rainimator.mod.RainimatorMod;
import dev.rainimator.mod.registry.RainimatorEffects;
import dev.rainimator.mod.registry.RainimatorFractions;
import dev.rainimator.mod.registry.RainimatorItems;
import dev.rainimator.mod.registry.RainimatorSounds;
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
import org.jetbrains.annotations.NotNull;

public class BlackBoneEntity extends MonsterFractionEntityBase {
    public static final Stage.StagedEntityTextureProvider texture = Stage.ofProvider(RainimatorMod.MOD_ID, "blackbone");
    private final ServerBossBar bossInfo = new ServerBossBar(this.getDisplayName(), BossBar.Color.WHITE, BossBar.Style.PROGRESS);

    public BlackBoneEntity(EntityType<BlackBoneEntity> type, World world) {
        super(type, world, EntityGroup.UNDEAD, RainimatorFractions.NETHER);
        this.experiencePoints = 0;
        this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(RainimatorItems.BLACKBONE_THE_BLADE_SINGLE_HAND.get()));
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        DefaultAttributeContainer.Builder builder = MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.35D)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 130.0D)
                .add(EntityAttributes.GENERIC_ARMOR, 30.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0D)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 64.0D)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 5.0D)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 1.0D);
        return builder;
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
    public boolean damage(DamageSource source, float amount) {
        double y = this.getY();
        Entity sourceentity = source.getAttacker();
        if (sourceentity != null) {
            if (sourceentity instanceof LivingEntity _ent) this.setTarget(_ent);
            if (this.hasStatusEffect(RainimatorEffects.FEAR_DARK.get())) this.clearStatusEffects();
            else if (this.hasStatusEffect(RainimatorEffects.ICE_PEOPLE.get())) this.clearStatusEffects();
            else if (this.hasStatusEffect(RainimatorEffects.SOUL_DEATH.get())) this.clearStatusEffects();
            else if (this.hasStatusEffect(StatusEffects.POISON)) this.clearStatusEffects();
            else if (this.hasStatusEffect(StatusEffects.WITHER)) this.clearStatusEffects();
            else {
                if (Math.random() < 0.2) {
                    if (sourceentity instanceof LivingEntity _livEnt && _livEnt.hasStatusEffect(RainimatorEffects.FEAR_DARK.get())) {
                        sourceentity.damage(DamageUtil.build(this.getWorld(), source, DamageTypes.MAGIC), 12);
                        if (!_livEnt.getWorld().isClient)
                            _livEnt.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 500, 1));
                    }
                } else {
                    if (!(sourceentity instanceof LivingEntity _livEnt && _livEnt.hasStatusEffect(RainimatorEffects.FEAR_DARK.get()))) {
                        SoundUtil.playSound(this.getWorld(), this.getX(), this.getY(), this.getZ(), RainimatorSounds.BLACKBONE_SKILL.get(), 1, 1);
                        if (this.getWorld() instanceof ServerWorld serverWorld)
                            serverWorld.spawnParticles(ParticleTypes.ELECTRIC_SPARK, this.getX(), this.getY(), this.getZ(), 50, 1, 1, 1, 1);
                        if (sourceentity instanceof LivingEntity living && !living.getWorld().isClient)
                            living.addStatusEffect(new StatusEffectInstance(RainimatorEffects.FEAR_DARK.get(), 300, 0));
                        sourceentity.setOnFireFor(10);
                    }
                }
            }

            if (Math.random() < 0.1) {
                if (!this.getWorld().isClient && this.getWorld().getServer() != null)
                    if (Math.random() < 0.3)
                        this.getWorld().getServer().getPlayerManager().broadcast(Text.translatable("entity.rainimator.blackbone.message1"), false);
                    else if (Math.random() < 0.4)
                        this.getWorld().getServer().getPlayerManager().broadcast(Text.translatable("entity.rainimator.blackbone.message2"), false);
                    else if (Math.random() < 0.5)
                        this.getWorld().getServer().getPlayerManager().broadcast(Text.translatable("entity.rainimator.blackbone.message3"), false);
                    else
                        this.getWorld().getServer().getPlayerManager().broadcast(Text.translatable("entity.rainimator.blackbone.message4"), false);
                CommandHelper.execute(sourceentity, "title @p title {\"text\":\"！！！\",\"color\":\"red\"}");
                Runnable callback = () -> {
                    if (!this.getWorld().isClient) {
                        BlockPos pos = this.getWorld().raycast(new RaycastContext(this.getCameraPosVec(1f), this.getCameraPosVec(1f).add(this.getRotationVec(1f).multiply(18)), RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, this)).getBlockPos();
                        this.getWorld().createExplosion(null, (pos.getX()), (y + 1), (pos.getZ()), 1, World.ExplosionSourceType.NONE);
                    }
                };
                Timeout.create(50, callback);
                Timeout.create(55, callback);
                Timeout.create(60, callback);
                Timeout.create(65, callback);
                Timeout.create(70, callback);
                Timeout.create(75, callback);
                Timeout.create(80, callback);
                Timeout.create(85, callback);
                Timeout.create(90, callback);
            }
        }
        return super.damage(source, amount);
    }

    @Override
    public boolean isInvulnerableTo(DamageSource damageSource) {
        if (damageSource.isOf(DamageTypes.FALL)) return true;
        if (damageSource.isOf(DamageTypes.DROWN)) return true;
        if (damageSource.isOf(DamageTypes.EXPLOSION)) return true;
        if (damageSource.isOf(DamageTypes.WITHER)) return true;
        if (damageSource.isOf(DamageTypes.WITHER_SKULL)) return true;
        return super.isInvulnerableTo(damageSource);
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason reason, EntityData livingdata, NbtCompound tag) {
        EntityData retval = super.initialize(world, difficulty, reason, livingdata, tag);
        SoundUtil.playSound(world.toServerWorld(), this.getX(), this.getY(), this.getZ(), RainimatorSounds.BLACKBONE_LIVING.get(), 1.0F, 1.0F);
        world.toServerWorld().spawnParticles(ParticleTypes.ELECTRIC_SPARK, this.getX(), this.getY(), this.getZ(), 50, 1.0D, 1.0D, 1.0D, 1.0D);
        if (world.getDifficulty() != Difficulty.PEACEFUL) {
            Runnable callback = () -> {
                if (this.isAlive())
                    SoundUtil.playSound(this.getWorld(), this.getX(), this.getY(), this.getZ(), RainimatorSounds.BLACKBONE_BOSS_MUSIC.get(), 1, 1);
            };
            Timeout.create(0, callback);
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
        if (this.hasStatusEffect(RainimatorEffects.STUNNED.get()))
            if (!this.getWorld().isClient) {
                this.addStatusEffect(new StatusEffectInstance(RainimatorEffects.PURIFICATION.get(), 200, 0));
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 200, 2));
            }
        if (!this.isAlive())
            SoundUtil.stopSound(this.getWorld(), Identifier.of(RainimatorMod.MOD_ID, "blackbone_boss_music"));
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

    @Override
    public SoundEvent getAmbientSound() {
        return Registries.SOUND_EVENT.get(Identifier.of(RainimatorMod.MOD_ID, "blackbone"));
    }

    @Override
    public SoundEvent getHurtSound(@NotNull DamageSource ds) {
        return Registries.SOUND_EVENT.get(Identifier.tryParse("entity.generic.hurt"));
    }

    @Override
    public SoundEvent getDeathSound() {
        return Registries.SOUND_EVENT.get(Identifier.tryParse("entity.generic.death"));
    }
}