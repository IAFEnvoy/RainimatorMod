package com.iafenvoy.rainimator.effect;

import com.iafenvoy.neptune.object.DamageUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class SoulDeathMobEffect extends StatusEffect {
    public SoulDeathMobEffect() {
        super(StatusEffectCategory.HARMFUL, -16764058);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        entity.damage(DamageUtil.build(entity, DamageTypes.GENERIC), 1.0F);
        if (!entity.getWorld().isClient) {
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 60, 4));
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 60, 1));
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}