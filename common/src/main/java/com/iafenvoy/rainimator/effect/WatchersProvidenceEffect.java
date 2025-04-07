package com.iafenvoy.rainimator.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;

public class WatchersProvidenceEffect extends StatusEffect {
    public WatchersProvidenceEffect() {
        super(StatusEffectCategory.BENEFICIAL, -57884);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity instanceof MobEntity mob)
            mob.getNavigation().stop();
        if (!entity.getWorld().isClient) {
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 1, 2));
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.CONDUIT_POWER, 1, 1));
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

}
