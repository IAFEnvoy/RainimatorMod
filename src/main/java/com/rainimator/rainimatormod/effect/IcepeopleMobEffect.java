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

public class IcepeopleMobEffect extends MobEffect {
    public IcepeopleMobEffect() {
        super(MobEffectCategory.HARMFUL, -10027009);
    }

    @Override
    public @NotNull String getDescriptionId() {
        return "effect." + RainimatorMod.MOD_ID + ".icepeople";
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        entity.setTicksFrozen(180);
        if (!entity.level.isClientSide()) {
            entity.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 500, 7));
            entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 500, 0));
        }
        if (entity.level instanceof ServerLevel _level)
            _level.sendParticles((ParticleOptions) ModParticleTypes.SNOW.get(), entity.getX(), entity.getY(), entity.getZ(), 100, 0.0D, 20.0D, 0.0D, 0.001D);
        entity.hurt(DamageSource.FREEZE, 1.0F);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}

