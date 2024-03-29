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
import net.minecraft.world.entity.ai.goal.*;
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

public class AgethaEntity extends MonsterEntityBase {
    public static Stage.StagedEntityTextureProvider texture = Stage.ofProvider("agetha");

    public AgethaEntity(PlayMessages.SpawnEntity packet, Level world) {
        this(ModEntities.AGETHA.get(), world);
    }

    public AgethaEntity(EntityType<AgethaEntity> type, Level world) {
        super(type, world, MobType.UNDEFINED);
        this.xpReward = 10;
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ModItems.DIAMOND_BIG_SWORD.get()));
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3D);
        builder = builder.add(Attributes.MAX_HEALTH, 40.0D);
        builder = builder.add(Attributes.ARMOR, 21.0D);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 3.0D);
        builder = builder.add(Attributes.FOLLOW_RANGE, 64.0D);
        builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 4.0D);
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
        if (source == DamageSource.DROWN)
            return false;
        return super.hurt(source, amount);
    }

    @Override
    public SpawnGroupData finalizeSpawn(@NotNull ServerLevelAccessor world, @NotNull DifficultyInstance difficulty, @NotNull MobSpawnType reason, @Nullable SpawnGroupData livingdata, @Nullable CompoundTag tag) {
        SpawnGroupData ret_val = super.finalizeSpawn(world, difficulty, reason, livingdata, tag);
        if (!(world instanceof ServerLevel _level)) return ret_val;
        if (Math.random() < 0.1D) {
            SoldiersEntity soldiersEntity = new SoldiersEntity(ModEntities.SOLDIERS.get(), _level);
            soldiersEntity.moveTo(this.getX() + Mth.nextInt(new Random(), -2, 2), this.getY() + 2.0D, this.getZ() + Mth.nextInt(new Random(), -2, 2), world.getRandom().nextFloat() * 360.0F, 0.0F);
            soldiersEntity.finalizeSpawn(_level, world.getCurrentDifficultyAt(soldiersEntity.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
            world.addFreshEntity(soldiersEntity);
        } else if (Math.random() < 0.1D) {
            AgethaEntity agethaEntity = new AgethaEntity(ModEntities.AGETHA.get(), _level);
            agethaEntity.moveTo(this.getX() + Mth.nextInt(new Random(), -2, 2), this.getY() + 2.0D, this.getZ() + Mth.nextInt(new Random(), -2, 2), world.getRandom().nextFloat() * 360.0F, 0.0F);
            agethaEntity.finalizeSpawn(_level, world.getCurrentDifficultyAt(agethaEntity.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
            world.addFreshEntity(agethaEntity);
        } else if (Math.random() < 0.1D) {
            ArcherEntity archerEntity = new ArcherEntity(ModEntities.ARCHER.get(), _level);
            archerEntity.moveTo(this.getX() + Mth.nextInt(new Random(), -2, 2), this.getY() + 2.0D, this.getZ() + Mth.nextInt(new Random(), -2, 2), world.getRandom().nextFloat() * 360.0F, 0.0F);
            archerEntity.finalizeSpawn(_level, world.getCurrentDifficultyAt(archerEntity.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
            world.addFreshEntity(archerEntity);
        }
        return ret_val;
    }
}