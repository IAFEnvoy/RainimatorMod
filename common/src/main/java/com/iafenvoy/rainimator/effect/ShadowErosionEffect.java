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

public class ShadowErosionEffect extends StatusEffect {
    public ShadowErosionEffect() {
        super(StatusEffectCategory.HARMFUL, -6723841);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (Math.random() < 0.2D) {
            entity.damage(DamageUtil.build(entity, DamageTypes.MAGIC), 3.0F);
            if (entity.getWorld() instanceof ServerWorld _level) {
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 100, 0));
                _level.spawnParticles(RainimatorParticles.PURPLE_LIGHT.get(), entity.getX(), entity.getY(), entity.getZ(), 100, 0.0D, 10.0D, 0.0D, 0.001D);
            }
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}