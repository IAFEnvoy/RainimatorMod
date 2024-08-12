package dev.rainimator.mod.entity;

import com.iafenvoy.neptune.object.EntityUtil;
import com.iafenvoy.neptune.object.SoundUtil;
import com.iafenvoy.neptune.object.VecUtil;
import com.iafenvoy.neptune.object.entity.MonsterEntityBase;
import com.iafenvoy.neptune.render.Stage;
import com.iafenvoy.neptune.util.RandomHelper;
import com.iafenvoy.neptune.util.Timeout;
import dev.rainimator.mod.RainimatorMod;
import dev.rainimator.mod.registry.RainimatorEffects;
import dev.rainimator.mod.registry.RainimatorEntities;
import dev.rainimator.mod.registry.RainimatorItems;
import dev.rainimator.mod.registry.RainimatorParticles;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.projectile.thrown.PotionEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Comparator;
import java.util.List;

public class StellaEntity extends MonsterEntityBase {
    public static final Stage.StagedEntityTextureProvider texture = Stage.ofProvider(RainimatorMod.MOD_ID, "stella");

    public StellaEntity(EntityType<StellaEntity> type, World world) {
        super(type, world, EntityGroup.DEFAULT);
        this.setStepHeight(0.7f);
        this.experiencePoints = 0;
        this.setAiDisabled(false);
        this.setPersistent();
        this.equipStack(EquipmentSlot.HEAD, new ItemStack(RainimatorItems.MAGIC_HAT.get()));
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.getNavigation().getNodeMaker().setCanOpenDoors(true);
        this.goalSelector.add(1, new MeleeAttackGoal(this, 1.2, false) {
            protected double getSquaredMaxAttackDistance(LivingEntity entity) {
                return this.mob.getWidth() * this.mob.getWidth() + entity.getWidth();
            }
        });
        this.goalSelector.add(2, new WanderAroundGoal(this, 1.0));
        this.targetSelector.add(3, new RevengeGoal(this));
        this.goalSelector.add(4, new LookAroundGoal(this));
        this.goalSelector.add(5, new SwimGoal(this));
        this.goalSelector.add(6, new LongDoorInteractGoal(this, false));
        this.goalSelector.add(7, new LongDoorInteractGoal(this, true));
    }

    @Override
    public boolean canImmediatelyDespawn(double distanceSquared) {
        return false;
    }

    @Override
    public double getHeightOffset() {
        return -0.35;
    }

    @Override
    public SoundEvent getHurtSound(DamageSource ds) {
        return Registries.SOUND_EVENT.get(new Identifier("entity.generic.hurt"));
    }

    @Override
    public SoundEvent getDeathSound() {
        return Registries.SOUND_EVENT.get(new Identifier("entity.generic.death"));
    }

    @Override
    protected void applyDamage(DamageSource source, float amount) {
        Vec3d _center = new Vec3d(this.getX(), this.getY(), this.getZ());
        List<Entity> _entfound = this.getWorld().getEntitiesByClass(Entity.class, new Box(_center, _center).expand(4.5), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.squaredDistanceTo(_center))).toList();
        for (Entity entityiterator : _entfound) {
            if (this.hasStatusEffect(RainimatorEffects.ICE_PEOPLE.get())) {
                this.teleport(this.getX(), this.getY() + 4.0, this.getZ());
                continue;
            }
            if (this.hasStatusEffect(StatusEffects.POISON)) {
                this.clearStatusEffects();
                continue;
            }
            if (this.hasStatusEffect(StatusEffects.WITHER)) {
                this.clearStatusEffects();
                continue;
            }
            if (this.hasStatusEffect(RainimatorEffects.SOUL_DEATH.get())) {
                this.clearStatusEffects();
                continue;
            }
            if (this.hasStatusEffect(RainimatorEffects.STUNNED.get())) {
                if (!this.getWorld().isClient) {
                    this.addStatusEffect(new StatusEffectInstance(RainimatorEffects.PURIFICATION.get(), 200, 0));
                    this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 200, 3));
                }
                continue;
            }
            if (Math.random() < 0.1) {
                this.getWorld().setBlockState(VecUtil.createBlockPos(entityiterator.getX(), entityiterator.getY(), entityiterator.getZ()), Blocks.FIRE.getDefaultState(), 3);
                if (entityiterator instanceof LivingEntity living)
                    if (!living.getWorld().isClient)
                        living.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 1200, 2));
            } else if (Math.random() < 0.05) {
                if (this.getWorld() instanceof ServerWorld world) {
                    world.spawnParticles(RainimatorParticles.PURPLE_LIGHT.get(), this.getX(), this.getY(), this.getZ(), 15, 0.5, 0.5, 0.5, 0.5);
                    EntityUtil.lightening(world, entityiterator.getX(), entityiterator.getY(), entityiterator.getZ(), false);
                    this.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 300, 0));
                }
                this.getNavigation().startMovingTo(this.getX() + RandomHelper.nextInt(-2, 2), this.getY(), this.getZ() + RandomHelper.nextInt(-2, 2), 20.0);
            }
            if (this.hasStatusEffect(StatusEffects.SPEED) && !this.getWorld().isClient) {
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100, 2));
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 200, 0));
            }
            if (this.getHealth() > 100.0f) continue;
            if (this.getWorld().isClient) continue;
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 200, 2));
        }
        super.applyDamage(source, amount);
    }

    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        if (source.getAttacker() instanceof PotionEntity ||
                source.getAttacker() instanceof AreaEffectCloudEntity ||
                source.isOf(DamageTypes.DROWN) ||
                source.isOf(DamageTypes.LIGHTNING_BOLT) ||
                source.isOf(DamageTypes.EXPLOSION) ||
                source.isOf(DamageTypes.WITHER) ||
                source.isOf(DamageTypes.WITHER_SKULL))
            return true;
        return super.isInvulnerableTo(source);
    }

    @Override
    public void onDeath(DamageSource damageSource) {
        super.onDeath(damageSource);
        World world = this.getWorld();
        double x = this.getX(), y = this.getY(), z = this.getZ();
        ItemStack itemStack = this.getOffHandStack();
        if (itemStack.getItem() == Blocks.AIR.asItem()) {
            this.getNavigation().stop();
            this.teleport(x, y, z);
            ItemStack _setstack = new ItemStack(RainimatorItems.SOUL_PEOPLE.get());
            _setstack.setCount(1);
            this.setStackInHand(Hand.OFF_HAND, _setstack);
            if (!this.getWorld().isClient) {
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 4));
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100, 4));
            }
            if (world instanceof ServerWorld _level) {
                EntityUtil.lightening(_level, x, y, z);
            }
            world.setBlockState(VecUtil.createBlockPos(x, y, z), Blocks.FIRE.getDefaultState(), 3);
            SoundUtil.playSound(world, x, y, z, new Identifier(RainimatorMod.MOD_ID, "naeus_roll"), 5, 1);
            if (world instanceof ServerWorld _level) {
                _level.spawnParticles(ParticleTypes.SOUL, x, y, z, 400, 3.0, 4.0, 3.0, 0.002);
            }
            Timeout.create(30, () -> {
                if (world instanceof ServerWorld world1)
                    EntityUtil.summon(RainimatorEntities.STELLA_DEMON.get(), world1, x, y, z);
            });
        }
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        DefaultAttributeContainer.Builder builder = MobEntity.createMobAttributes();
        builder = builder.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3);
        builder = builder.add(EntityAttributes.GENERIC_MAX_HEALTH, 25.0);
        builder = builder.add(EntityAttributes.GENERIC_ARMOR, 40.0);
        builder = builder.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.0);
        builder = builder.add(EntityAttributes.GENERIC_FOLLOW_RANGE, 64.0);
        builder = builder.add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 40.0);
        builder = builder.add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 3.0);
        return builder;
    }
}
