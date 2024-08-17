package dev.rainimator.mod.entity;

import com.iafenvoy.neptune.render.Stage;
import dev.rainimator.mod.RainimatorMod;
import dev.rainimator.mod.registry.RainimatorFractions;
import dev.rainimator.mod.registry.RainimatorItems;
import com.iafenvoy.neptune.object.entity.MonsterFractionEntityBase;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.projectile.thrown.PotionEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class StellaDemonEntity extends MonsterFractionEntityBase {
    public static final Stage.StagedEntityTextureProvider texture = Stage.ofProvider(RainimatorMod.MOD_ID, "stella_demon");

    public StellaDemonEntity(EntityType<StellaDemonEntity> type, World world) {
        super(type, world, EntityGroup.DEFAULT, RainimatorFractions.NETHER);
        this.setStepHeight(0.7f);
        this.experiencePoints = 0;
        this.setAiDisabled(false);
        this.setPersistent();
        this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(RainimatorItems.ARCANE_BLADES.get()));
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.getNavigation().getNodeMaker().setCanOpenDoors(true);
        this.goalSelector.add(4, new MeleeAttackGoal(this, 1.2, false) {
            protected double getSquaredMaxAttackDistance(LivingEntity entity) {
                return this.mob.getWidth() * this.mob.getWidth() + entity.getWidth();
            }
        });
        this.goalSelector.add(5, new WanderAroundGoal(this, 1.0));
        this.targetSelector.add(6, new RevengeGoal(this));
        this.goalSelector.add(7, new LookAroundGoal(this));
        this.goalSelector.add(8, new SwimGoal(this));
        this.goalSelector.add(9, new LongDoorInteractGoal(this, false));
        this.goalSelector.add(10, new LongDoorInteractGoal(this, true));
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
    public boolean isInvulnerableTo(DamageSource source) {
        if (source.getAttacker() instanceof PotionEntity ||
                source.getAttacker() instanceof AreaEffectCloudEntity ||
                source.isOf(DamageTypes.FALL) ||
                source.isOf(DamageTypes.EXPLOSION) ||
                source.isOf(DamageTypes.WITHER) ||
                source.isOf(DamageTypes.WITHER_SKULL))
            return true;
        return super.isInvulnerableTo(source);
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        DefaultAttributeContainer.Builder builder = MobEntity.createMobAttributes();
        builder = builder.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3);
        builder = builder.add(EntityAttributes.GENERIC_MAX_HEALTH, 30.0);
        builder = builder.add(EntityAttributes.GENERIC_ARMOR, 35.0);
        builder = builder.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 7.0);
        builder = builder.add(EntityAttributes.GENERIC_FOLLOW_RANGE, 64.0);
        builder = builder.add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 30.0);
        builder = builder.add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 43.0);
        return builder;
    }
}
