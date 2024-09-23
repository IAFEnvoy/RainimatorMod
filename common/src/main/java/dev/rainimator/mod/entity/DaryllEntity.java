package dev.rainimator.mod.entity;

import com.iafenvoy.neptune.object.entity.MonsterFractionEntityBase;
import com.iafenvoy.neptune.render.Stage;
import dev.rainimator.mod.RainimatorMod;
import dev.rainimator.mod.registry.RainimatorEntities;
import dev.rainimator.mod.registry.RainimatorFractions;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class DaryllEntity extends MonsterFractionEntityBase implements RangedAttackMob {
    public static final Stage.StagedEntityTextureProvider texture = Stage.ofProvider(RainimatorMod.MOD_ID, "daryll");

    public DaryllEntity(EntityType<DaryllEntity> type, World world) {
        super(type, world, EntityGroup.DEFAULT, RainimatorFractions.FROST);
        this.experiencePoints = 0;
        this.setPersistent();
        this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_AXE));
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3D)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 80.0D)
                .add(EntityAttributes.GENERIC_ARMOR, 20.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0D)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 64.0D)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 5.0D)
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
        this.goalSelector.add(5, new LongDoorInteractGoal(this, true));
        this.goalSelector.add(6, new LongDoorInteractGoal(this, false));
        this.goalSelector.add(7, new LookAroundGoal(this));
        this.goalSelector.add(8, new SwimGoal(this));
        this.goalSelector.add(1, new ProjectileAttackGoal(this, 1.25D, 20, 10.0F) {
            public boolean shouldContinue() {
                return this.canStart();
            }
        });
    }

    @Override
    public boolean canImmediatelyDespawn(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public boolean isInvulnerableTo(DamageSource damageSource) {
        if (damageSource.isOf(DamageTypes.FALL)) return true;
        if (damageSource.isOf(DamageTypes.FALLING_ANVIL)) return true;
        return super.isInvulnerableTo(damageSource);
    }

    @Override
    public void attack(LivingEntity target, float pullProgress) {
        DaryllEntityProjectile projectile = new DaryllEntityProjectile(RainimatorEntities.DARYLL_PROJECTILE.get(), this, this.getWorld());
        double d0 = target.getY() + target.getStandingEyeHeight() - 1.1D;
        double d1 = target.getX() - this.getX();
        double d3 = target.getZ() - this.getZ();
        projectile.setVelocity(d1, d0 - projectile.getY() + Math.sqrt(d1 * d1 + d3 * d3) * 0.20000000298023224D, d3, 1.6F, 12.0F);
        this.getWorld().spawnEntity(projectile);
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