package dev.rainimator.mod.effect;

import dev.rainimator.mod.RainimatorMod;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;

public class StunnedMobEffect extends StatusEffect {
    public StunnedMobEffect() {
        super(StatusEffectCategory.HARMFUL, -13210);
    }

    @Override
    public String getTranslationKey() {
        return "effect." + RainimatorMod.MOD_ID + ".stunned";
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity instanceof MobEntity _entity)
            _entity.getNavigation().stop();
        if (entity instanceof LivingEntity)
            if (!entity.getWorld().isClient()) {
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 40, 4));
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 40, 4));
            }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
