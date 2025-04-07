package com.iafenvoy.rainimator.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class WitherHeartCurseEffect extends StatusEffect {
    public WitherHeartCurseEffect() {
        super(StatusEffectCategory.HARMFUL, -12830150);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (!entity.getWorld().isClient)
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 2400, 1));
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
