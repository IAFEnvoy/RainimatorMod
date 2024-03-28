package com.rainimator.rainimatormod.entity;

import com.rainimator.rainimatormod.registry.ModEntities;
import com.rainimator.rainimatormod.registry.ModItems;
import com.rainimator.rainimatormod.registry.util.MonsterEntityBase;
import com.rainimator.rainimatormod.util.Stage;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class AbigailEntity extends MonsterEntityBase implements RangedAttackMob {
    public static Stage.StagedEntityTextureProvider texture = Stage.ofProvider("abigail");

    public AbigailEntity(PlayMessages.SpawnEntity packet, Level world) {
        this(ModEntities.ABIGAIL.get(), world);
    }

    public AbigailEntity(EntityType<AbigailEntity> type, Level world) {
        super(type, world, MobType.UNDEFINED);
        this.xpReward = 0;
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ModItems.ABIGAIL_SPEAR.get()));
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3D);
        builder = builder.add(Attributes.MAX_HEALTH, 100.0D);
        builder = builder.add(Attributes.ARMOR, 30.0D);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 5.0D);
        builder = builder.add(Attributes.FOLLOW_RANGE, 64.0D);
        builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 1.0D);
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
        this.goalSelector.addGoal(5, new OpenDoorGoal(this, true));
        this.goalSelector.addGoal(6, new OpenDoorGoal(this, false));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(8, new FloatGoal(this));
        this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.25D, 20, 10.0F) {
            public boolean canContinueToUse() {
                return this.canUse();
            }
        });
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
        if (source.getEntity() instanceof LivingEntity _ent)
            this.setTarget(_ent);
        if (this.getHealth() <= 40.0F) {
            this.setHealth(this.getHealth() + Mth.nextInt(new Random(), 1, 3));
            if (Math.random() < 0.2D)
                this.hurt(DamageSource.MAGIC, 2.0F);
        }
        if (source.getDirectEntity() instanceof AbstractArrow)
            return false;
        if (source.isExplosion())
            return false;
        if (source.getMsgId().equals("trident"))
            return false;
        return super.hurt(source, amount);
    }

    @Override
    public void performRangedAttack(LivingEntity target, float flval) {
        AbigailEntityProjectile entityarrow = new AbigailEntityProjectile(ModEntities.ABIGAIL_PROJECTILE.get(), this, this.level);
        double d0 = target.getY() + target.getEyeHeight() - 1.1D;
        double d1 = target.getX() - this.getX();
        double d3 = target.getZ() - this.getZ();
        entityarrow.shoot(d1, d0 - entityarrow.getY() + Math.sqrt(d1 * d1 + d3 * d3) * 0.20000000298023224D, d3, 1.6F, 12.0F);
        this.level.addFreshEntity(entityarrow);
    }
}