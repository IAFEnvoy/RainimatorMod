package dev.rainimator.mod.entity;

import com.iafenvoy.neptune.object.EntityUtil;
import com.iafenvoy.neptune.object.SoundUtil;
import com.iafenvoy.neptune.object.VecUtil;
import com.iafenvoy.neptune.object.entity.MonsterEntityBase;
import com.iafenvoy.neptune.render.Stage;
import com.iafenvoy.neptune.util.Timeout;
import dev.rainimator.mod.RainimatorMod;
import dev.rainimator.mod.registry.RainimatorEffects;
import dev.rainimator.mod.registry.RainimatorItems;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
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
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.Difficulty;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

public class KralosEntity extends MonsterEntityBase {
    public static final Stage.StagedEntityTextureProvider TEXTURE = Stage.ofProvider(RainimatorMod.MOD_ID, "kralos");
    private final ServerBossBar bossInfo = new ServerBossBar(this.getDisplayName(), BossBar.Color.RED, BossBar.Style.PROGRESS);

    public KralosEntity(EntityType<KralosEntity> type, World world) {
        super(type, world, EntityGroup.UNDEAD);
        this.experiencePoints = 0;
        this.setPersistent();
        this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(RainimatorItems.FALLEN_SOUL_AXE.get()));
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        DefaultAttributeContainer.Builder builder = MobEntity.createMobAttributes();
        builder = builder.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3D);
        builder = builder.add(EntityAttributes.GENERIC_MAX_HEALTH, 100.0D);
        builder = builder.add(EntityAttributes.GENERIC_ARMOR, 25.0D);
        builder = builder.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.0D);
        builder = builder.add(EntityAttributes.GENERIC_FOLLOW_RANGE, 64.0D);
        builder = builder.add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 5.0D);
        builder = builder.add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 1.0D);
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
    public boolean canImmediatelyDespawn(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public SoundEvent getHurtSound(DamageSource ds) {
        return Registries.SOUND_EVENT.get(Identifier.tryParse("entity.wither_skeleton.hurt"));
    }

    @Override
    public SoundEvent getDeathSound() {
        return Registries.SOUND_EVENT.get(Identifier.tryParse("entity.wither_skeleton.death"));
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        Entity sourceentity = source.getAttacker();
        if (sourceentity != null) {
            if (sourceentity instanceof LivingEntity _entity) {
                this.setTarget(_entity);
                if (Math.random() < 0.2D)
                    if (!_entity.getWorld().isClient())
                        _entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 100, 1));
            }
            if (this.getHealth() < 60.0F) {
                if (EnchantmentHelper.getLevel(Enchantments.SHARPNESS, this.getMainHandStack()) == 0) {
                    if (!this.getWorld().isClient())
                        this.addStatusEffect(new StatusEffectInstance(RainimatorEffects.PURIFICATION.get(), 9999, 0));
                    this.getMainHandStack().addEnchantment(Enchantments.SHARPNESS, 4);
                    if (this.getWorld() instanceof ServerWorld _level)
                        EntityUtil.lightening(_level, x, y, z);
                    this.getWorld().setBlockState(VecUtil.createBlockPos(x, y, z), Blocks.FIRE.getDefaultState(), 3);
                    SoundUtil.playSound(this.getWorld(), this.getX(), this.getY(), this.getZ(), Identifier.tryParse("entity.enderman.scream"), 1.0F, 1.0F);
                    if (this.getWorld() instanceof ServerWorld _level)
                        _level.spawnParticles(ParticleTypes.SOUL, x, y, z, 200, 2.0D, 3.0D, 2.0D, 0.001D);
                    if (!this.getWorld().isClient() && this.getWorld().getServer() != null)
                        this.getWorld().getServer().getPlayerManager().broadcast(Text.translatable("entity.rainimator.kralos.message"), false);
                }
            }
        }
        return super.damage(source, amount);
    }

    @Override
    public boolean isInvulnerableTo(DamageSource damageSource) {
        if (damageSource.getSource() instanceof PersistentProjectileEntity)
            return true;
        if (damageSource.isOf(DamageTypes.FALL))
            return true;
        if (damageSource.isOf(DamageTypes.CACTUS))
            return true;
        if (damageSource.isOf(DamageTypes.LIGHTNING_BOLT))
            return true;
        if (damageSource.isOf(DamageTypes.FALLING_ANVIL))
            return true;
        if (damageSource.isOf(DamageTypes.WITHER))
            return true;
        if (damageSource.isOf(DamageTypes.WITHER_SKULL))
            return true;
        return super.isInvulnerableTo(damageSource);
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason reason, EntityData livingdata, NbtCompound tag) {
        EntityData retval = super.initialize(world, difficulty, reason, livingdata, tag);
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        if (world instanceof World _level)
            SoundUtil.playSound(_level, this.getX(), this.getY(), this.getZ(), Identifier.tryParse("entity.wither.ambient"), 1.0F, 1.0F);
        if (world instanceof ServerWorld _level)
            _level.spawnParticles(ParticleTypes.SOUL, x, y, z, 100, 3.0D, 4.0D, 3.0D, 0.001D);
        if (world.getDifficulty() != Difficulty.PEACEFUL) {
            Runnable callback = () -> {
                if (this.isAlive())
                    SoundUtil.playSound(this.getWorld(), this.getX(), this.getY(), this.getZ(), Identifier.of(RainimatorMod.MOD_ID, "kralos_boss_music"), 1, 1);
            };
            Timeout.create(0, callback);
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
        if (!this.getWorld().isClient()) {
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 80, 0));
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 80, 0));
        }
        if (!this.isAlive())
            SoundUtil.stopSound(this.getWorld(), Identifier.of(RainimatorMod.MOD_ID, "kralos_boss_music"));
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