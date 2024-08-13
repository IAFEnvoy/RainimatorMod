package dev.rainimator.mod.data.fraction;

import dev.rainimator.mod.impl.ComponentManager;
import dev.rainimator.mod.mixin.MobEntityAccessor;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;

public interface FractionEntity {
    Fraction getFraction();

    static void addTarget(MobEntity entity) {
        GoalSelector goalSelector=((MobEntityAccessor) entity).getTargetSelector();
        goalSelector.add(1, new ActiveTargetGoal<>(entity, MobEntity.class, true, mob -> {
            if (mob instanceof FractionEntity mobF && entity instanceof FractionEntity entityF)
                return mobF.getFraction() != entityF.getFraction();
            return false;
        }));
        goalSelector.add(1, new ActiveTargetGoal<>(entity, PlayerEntity.class, true, mob -> {
            if (mob instanceof PlayerEntity player && entity instanceof FractionEntity entityF)
                return ComponentManager.getFractionData(player).getFraction() != entityF.getFraction();
            return false;
        }));
    }
}
