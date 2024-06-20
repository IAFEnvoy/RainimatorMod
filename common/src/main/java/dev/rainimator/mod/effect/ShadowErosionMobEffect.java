package dev.rainimator.mod.effect;

import dev.rainimator.mod.RainimatorMod;
import dev.rainimator.mod.registry.RainimatorParticles;
import dev.rainimator.mod.util.DamageUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.server.world.ServerWorld;

public class ShadowErosionMobEffect extends StatusEffect {
    public ShadowErosionMobEffect() {
        super(StatusEffectCategory.HARMFUL, -6723841);
    }

    @Override
    public String getTranslationKey() {
        return "effect." + RainimatorMod.MOD_ID + ".shadow_erosion";
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (Math.random() < 0.2D) {
            entity.damage(DamageUtil.build(entity, DamageTypes.MAGIC), 3.0F);
            if (!entity.getWorld().isClient())
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 100, 0));
            if (entity.getWorld() instanceof ServerWorld _level)
                _level.spawnParticles((ParticleEffect) RainimatorParticles.PURPLE_LIGHT, entity.getX(), entity.getY(), entity.getZ(), 100, 0.0D, 10.0D, 0.0D, 0.001D);
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}