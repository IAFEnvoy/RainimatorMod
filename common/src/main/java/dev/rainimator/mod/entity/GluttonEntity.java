package dev.rainimator.mod.entity;

import com.iafenvoy.neptune.object.SoundUtil;
import com.iafenvoy.neptune.object.entity.StagedMonsterEntityBase;
import com.iafenvoy.neptune.render.Stage;
import com.iafenvoy.neptune.util.Timeout;
import dev.rainimator.mod.RainimatorMod;
import dev.rainimator.mod.data.fraction.Fraction;
import dev.rainimator.mod.registry.RainimatorItems;
import dev.rainimator.mod.registry.RainimatorParticles;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.world.Difficulty;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

public class GluttonEntity extends StagedMonsterEntityBase {
    public static final Stage.StagedEntityTextureProvider texture = Stage.ofProvider(RainimatorMod.MOD_ID, "glutton_1", "glutton_2");
    private final ServerBossBar bossInfo = new ServerBossBar(this.getDisplayName(), BossBar.Color.YELLOW, BossBar.Style.PROGRESS);

    public GluttonEntity(EntityType<GluttonEntity> type, World world) {
        this(type, world, Stage.First);
    }

    public GluttonEntity(EntityType<GluttonEntity> type, World world, Stage stage) {
        super(type, world, EntityGroup.UNDEAD, stage);
        this.experiencePoints = 0;
        this.setPersistent();
        switch (stage) {
            case First -> {
                this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(RainimatorItems.GLUTTON_SLEDGE_HAMMER.get()));
                this.equipStack(EquipmentSlot.HEAD, new ItemStack(RainimatorItems.GLUTTON_HELMET.get()));
                this.equipStack(EquipmentSlot.CHEST, new ItemStack(RainimatorItems.HEROBRINE_CHESTPLATE.get()));
            }
            case Second -> {
                this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(RainimatorItems.GLUTTON_SLEDGE_HAMMER.get()));
                this.equipStack(EquipmentSlot.HEAD, new ItemStack(RainimatorItems.GLUTTON_HELMET.get()));
                this.equipStack(EquipmentSlot.LEGS, new ItemStack(Items.GOLDEN_LEGGINGS));
                this.equipStack(EquipmentSlot.FEET, new ItemStack(Items.GOLDEN_BOOTS));
            }
        }
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        DefaultAttributeContainer.Builder builder = MobEntity.createMobAttributes();
        builder = builder.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.35D);
        builder = builder.add(EntityAttributes.GENERIC_MAX_HEALTH, 150.0D);
        builder = builder.add(EntityAttributes.GENERIC_ARMOR, 20.0D);
        builder = builder.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0D);
        builder = builder.add(EntityAttributes.GENERIC_FOLLOW_RANGE, 64.0D);
        builder = builder.add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 6.0D);
        builder = builder.add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 2.0D);
        return builder;
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        Fraction.findAndAddTarget(this);
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
        return Registries.SOUND_EVENT.get(Identifier.tryParse("entity.zombified_piglin.hurt"));
    }

    @Override
    public SoundEvent getDeathSound() {
        return Registries.SOUND_EVENT.get(Identifier.tryParse("entity.zombified_piglin.death"));
    }

    @Override
    public boolean isInvulnerableTo(DamageSource damageSource) {
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
            return false;
        return super.isInvulnerableTo(damageSource);
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason reason, EntityData livingdata, NbtCompound tag) {
        EntityData retval = super.initialize(world, difficulty, reason, livingdata, tag);
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        if (world instanceof World _level)
            SoundUtil.playSound(_level, x, y, z, Identifier.of(RainimatorMod.MOD_ID, "blackbone_skill"), 5, 1);
        if (world instanceof ServerWorld _level)
            _level.spawnParticles(RainimatorParticles.YELLOW_STARS.get(), x, y, z, 100, 1, 2, 1, 1);
        if (world.getDifficulty() != Difficulty.PEACEFUL) {
            Timeout.create(30, () -> {
                if (world instanceof World _level)
                    SoundUtil.playSound(_level, x, y, z, Identifier.of(RainimatorMod.MOD_ID, "blackbone_living"), 5, 1);
            });
            Runnable callback = () -> {
                if (this.isAlive())
                    SoundUtil.playSound(this.getWorld(), x, y, z, Identifier.of(RainimatorMod.MOD_ID, "glutton_boss_music"), 5, 1);
            };
            Timeout.create(0, callback);
            Timeout.create(5520, callback);
            Timeout.create(11040, callback);
            Timeout.create(16560, callback);
            Timeout.create(22080, callback);
            Timeout.create(27600, callback);
            Timeout.create(33120, callback);
        }
        return retval;
    }

    @Override
    public void baseTick() {
        super.baseTick();
        if (!this.isAlive())
            SoundUtil.stopSound(this.getWorld(), Identifier.of(RainimatorMod.MOD_ID, "glutton_boss_music"));
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