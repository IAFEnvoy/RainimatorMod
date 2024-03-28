package com.rainimator.rainimatormod.entity;

import com.rainimator.rainimatormod.registry.ModEntities;
import com.rainimator.rainimatormod.registry.ModItems;
import com.rainimator.rainimatormod.registry.util.MonsterEntityBase;
import com.rainimator.rainimatormod.util.Stage;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Random;

public class PiglinCommanderEntity extends MonsterEntityBase {
    public static Stage.StagedEntityTextureProvider texture = Stage.ofProvider("piglin_commander");

    public PiglinCommanderEntity(PlayMessages.SpawnEntity packet, Level world) {
        this(ModEntities.PIGLIN_COMMANDER.get(), world);
    }

    public PiglinCommanderEntity(EntityType<PiglinCommanderEntity> type, Level world) {
        super(type, world, MobType.UNDEAD);
        this.xpReward = 20;
        this.setPersistenceRequired();
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ModItems.THE_GOLDEN_SWORD.get()));
        this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(ModItems.PORKSHIRE_KING_CROWN.get()));
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3D);
        builder = builder.add(Attributes.MAX_HEALTH, 80.0D);
        builder = builder.add(Attributes.ARMOR, 20.0D);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 1.0D);
        builder = builder.add(Attributes.FOLLOW_RANGE, 64.0D);
        builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 4.0D);
        builder = builder.add(Attributes.ATTACK_KNOCKBACK, 1.0D);
        return builder;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2D, false) {
            protected double getAttackReachSqr(@NotNull LivingEntity entity) {
                return (this.mob.getBbWidth() * this.mob.getBbWidth() + entity.getBbWidth());
            }
        });
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 1.0D));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Player.class, false, false));
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
        return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.piglin.hurt"));
    }

    @Override
    public SoundEvent getDeathSound() {
        return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.piglin.death"));
    }

    @Override
    public boolean hurt(@NotNull DamageSource source, float amount) {
        if (source == DamageSource.FALL)
            return false;
        if (source == DamageSource.ANVIL)
            return false;
        return super.hurt(source, amount);
    }

    @Override
    public SpawnGroupData finalizeSpawn(@NotNull ServerLevelAccessor world, @NotNull DifficultyInstance difficulty, @NotNull MobSpawnType reason, @Nullable SpawnGroupData livingdata, @Nullable CompoundTag tag) {
        SpawnGroupData retval = super.finalizeSpawn(world, difficulty, reason, livingdata, tag);
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        if (world instanceof ServerLevel _level) {
            MutatedEntity mutatedEntity = new MutatedEntity(ModEntities.MUTATED.get(), _level);
            mutatedEntity.moveTo(x + Mth.nextInt(new Random(), -2, 2), y, z + Mth.nextInt(new Random(), -2, 2), world.getRandom().nextFloat() * 360.0F, 0.0F);
            mutatedEntity.finalizeSpawn(_level, world.getCurrentDifficultyAt(mutatedEntity.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
            world.addFreshEntity(mutatedEntity);
            if (Math.random() < 0.4D) {
                ZombiePiglinArtEntity zombiepiglinartEntity = new ZombiePiglinArtEntity(ModEntities.ZOMBIE_PIGLIN_ART.get(), _level);
                zombiepiglinartEntity.moveTo(x + Mth.nextInt(new Random(), -2, 2), y, z + Mth.nextInt(new Random(), -2, 2), world.getRandom().nextFloat() * 360.0F, 0.0F);
                zombiepiglinartEntity.finalizeSpawn(_level, world.getCurrentDifficultyAt(zombiepiglinartEntity.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
                world.addFreshEntity(zombiepiglinartEntity);
            }
        }
        return retval;
    }
}