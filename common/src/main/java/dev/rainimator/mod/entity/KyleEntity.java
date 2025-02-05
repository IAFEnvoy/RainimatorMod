package dev.rainimator.mod.entity;

import com.iafenvoy.neptune.object.entity.MonsterEntityBase;
import com.iafenvoy.neptune.render.Stage;
import dev.rainimator.mod.RainimatorMod;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class KyleEntity extends MonsterEntityBase {
    public static final Stage.StagedEntityTextureProvider TEXTURE = Stage.ofProvider(RainimatorMod.MOD_ID, "kyle");

    public KyleEntity(EntityType<KyleEntity> entityType, World world) {
        super(entityType, world, EntityGroup.DEFAULT);
        this.setStepHeight(0.6f);
        this.experiencePoints = 0;
        this.setPersistent();
        this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_AXE));
        this.equipStack(EquipmentSlot.OFFHAND, new ItemStack(Items.CROSSBOW));
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
        this.goalSelector.add(4, new LongDoorInteractGoal(this, false));
        this.goalSelector.add(5, new LongDoorInteractGoal(this, true));
        this.goalSelector.add(6, new BreakDoorGoal(this, e -> true));
        this.goalSelector.add(7, new LookAroundGoal(this));
        this.goalSelector.add(8, new SwimGoal(this));
    }

    @Override
    public boolean canImmediatelyDespawn(double distanceToClosestPlayer) {
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

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.4)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0)
                .add(EntityAttributes.GENERIC_ARMOR, 10.0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 64.0)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1.0)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 1.0);
    }
}
