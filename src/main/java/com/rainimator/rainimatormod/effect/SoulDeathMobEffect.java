package com.rainimator.rainimatormod.effect;

import com.rainimator.rainimatormod.RainimatorMod;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public class SoulDeathMobEffect extends MobEffect {
    public SoulDeathMobEffect() {
        super(MobEffectCategory.HARMFUL, -16764058);
    }

    @Override
    public @NotNull String getDescriptionId() {
        return "effect." + RainimatorMod.MOD_ID + ".soul_death";
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        entity.hurt(DamageSource.GENERIC, 1.0F);
        if (!entity.level.isClientSide()) {
            entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 4));
            entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 60, 1));
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}