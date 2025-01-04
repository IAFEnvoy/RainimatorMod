package dev.rainimator.mod.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class FeardarkMobEffect extends StatusEffect {
    public FeardarkMobEffect() {
        super(StatusEffectCategory.HARMFUL, -16777216);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity.getWorld().isClient) return;
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 1));
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 200, 0));

    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
