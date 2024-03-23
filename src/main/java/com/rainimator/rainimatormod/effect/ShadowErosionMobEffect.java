package com.rainimator.rainimatormod.effect;

import com.rainimator.rainimatormod.RainimatorMod;
import com.rainimator.rainimatormod.registry.ModParticleTypes;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public class ShadowErosionMobEffect extends MobEffect {
    public ShadowErosionMobEffect() {
        super(MobEffectCategory.HARMFUL, -6723841);
    }

    @Override
    public @NotNull String getDescriptionId() {
        return "effect." + RainimatorMod.MOD_ID + ".shadow_erosion";
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
        if (Math.random() < 0.2D) {
            entity.hurt(DamageSource.MAGIC, 3.0F);
            if (!entity.level.isClientSide())
                entity.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 100, 0));
            if (entity.level instanceof ServerLevel _level)
                _level.sendParticles((ParticleOptions) ModParticleTypes.PURPLELIGHT.get(), entity.getX(), entity.getY(), entity.getZ(), 100, 0.0D, 10.0D, 0.0D, 0.001D);
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}