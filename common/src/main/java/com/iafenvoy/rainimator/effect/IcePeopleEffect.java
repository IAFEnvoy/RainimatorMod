package com.iafenvoy.rainimator.effect;

import com.iafenvoy.neptune.object.DamageUtil;
import com.iafenvoy.rainimator.registry.RainimatorParticles;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.world.ServerWorld;

public class IcePeopleEffect extends StatusEffect {
    public IcePeopleEffect() {
        super(StatusEffectCategory.HARMFUL, -10027009);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        entity.setFrozenTicks(180);
        if (entity.getWorld() instanceof ServerWorld _level) {
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 500, 7));
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 500, 0));
            _level.spawnParticles(RainimatorParticles.SNOW.get(), entity.getX(), entity.getY(), entity.getZ(), 100, 0.0D, 20.0D, 0.0D, 0.001D);
        }
        entity.damage(DamageUtil.build(entity, DamageTypes.FREEZE), 1.0F);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}

