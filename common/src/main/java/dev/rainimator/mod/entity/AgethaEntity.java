package dev.rainimator.mod.entity;

import com.iafenvoy.neptune.object.EntityUtil;
import com.iafenvoy.neptune.object.entity.MonsterEntityBase;
import com.iafenvoy.neptune.render.Stage;
import com.iafenvoy.neptune.util.RandomHelper;
import dev.rainimator.mod.RainimatorMod;
import dev.rainimator.mod.registry.RainimatorEntities;
import dev.rainimator.mod.registry.RainimatorItems;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class AgethaEntity extends MonsterEntityBase {
    public static final Stage.StagedEntityTextureProvider TEXTURE = Stage.ofProvider(RainimatorMod.MOD_ID, "agetha");

    public AgethaEntity(EntityType<AgethaEntity> type, World world) {
        super(type, world, EntityGroup.DEFAULT);
        this.experiencePoints = 10;
        this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(RainimatorItems.DIAMOND_BIG_SWORD.get()));
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        DefaultAttributeContainer.Builder builder = MobEntity.createMobAttributes();
        builder = builder.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3D);
        builder = builder.add(EntityAttributes.GENERIC_MAX_HEALTH, 40.0D);
        builder = builder.add(EntityAttributes.GENERIC_ARMOR, 21.0D);
        builder = builder.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3.0D);
        builder = builder.add(EntityAttributes.GENERIC_FOLLOW_RANGE, 64.0D);
        builder = builder.add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 4.0D);
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
        this.goalSelector.add(5, new LongDoorInteractGoal(this, true));
        this.goalSelector.add(6, new LongDoorInteractGoal(this, false));
        this.goalSelector.add(7, new LookAroundGoal(this));
        this.goalSelector.add(8, new SwimGoal(this));
    }

    @Override
    public boolean isInvulnerableTo(DamageSource damageSource) {
        if (damageSource.isOf(DamageTypes.DROWN))
            return true;
        return super.isInvulnerableTo(damageSource);
    }

    @Override
    public SoundEvent getHurtSound(@NotNull DamageSource ds) {
        return Registries.SOUND_EVENT.get(Identifier.tryParse("entity.generic.hurt"));
    }

    @Override
    public SoundEvent getDeathSound() {
        return Registries.SOUND_EVENT.get(Identifier.tryParse("entity.generic.death"));
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason reason, EntityData livingdata, NbtCompound tag) {
        EntityData ret_val = super.initialize(world, difficulty, reason, livingdata, tag);
        if (!(world instanceof ServerWorld _level)) return ret_val;
        if (Math.random() < 0.1D)
            EntityUtil.summon(RainimatorEntities.SOLDIERS.get(), _level, this.getX() + RandomHelper.nextInt(-2, 2), this.getY() + 2.0D, this.getZ() + RandomHelper.nextInt(-2, 2));
        else if (Math.random() < 0.1D)
            EntityUtil.summon(RainimatorEntities.AGETHA.get(), _level, this.getX() + RandomHelper.nextInt(-2, 2), this.getY() + 2.0D, this.getZ() + RandomHelper.nextInt(-2, 2));
        else if (Math.random() < 0.1D)
            EntityUtil.summon(RainimatorEntities.ARCHER.get(), _level, this.getX() + RandomHelper.nextInt(-2, 2), this.getY() + 2.0D, this.getZ() + RandomHelper.nextInt(-2, 2));
        return ret_val;
    }
}