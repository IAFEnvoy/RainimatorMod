package com.iafenvoy.rainimator.effect;

import com.google.common.base.Suppliers;
import com.iafenvoy.rainimator.registry.RainimatorEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffects;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class PurificationEffect extends StatusEffect {
    private static final Supplier<List<StatusEffect>> effects = Suppliers.memoize(() -> {
        List<StatusEffect> list = new ArrayList<>();
        list.add(RainimatorEffects.FEAR_DARK.get());
        list.add(RainimatorEffects.SOUL_DEATH.get());
        list.add(RainimatorEffects.STUNNED.get());
        list.add(RainimatorEffects.ICE_PEOPLE.get());
        list.add(StatusEffects.BAD_OMEN);
        list.add(StatusEffects.BLINDNESS);
        list.add(StatusEffects.HUNGER);
        list.add(StatusEffects.INSTANT_DAMAGE);
        list.add(StatusEffects.MINING_FATIGUE);
        list.add(StatusEffects.POISON);
        list.add(StatusEffects.SLOWNESS);
        list.add(StatusEffects.UNLUCK);
        list.add(StatusEffects.WEAKNESS);
        list.add(StatusEffects.WITHER);
        list.add(StatusEffects.NAUSEA);
        list.add(StatusEffects.LEVITATION);
        return list;
    });

    public PurificationEffect() {
        super(StatusEffectCategory.BENEFICIAL, -10027009);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        for (StatusEffect effect : effects.get())
            if (entity.hasStatusEffect(effect)) {
                entity.removeStatusEffect(effect);
                break;
            }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
