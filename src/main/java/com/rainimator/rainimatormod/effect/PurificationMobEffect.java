package com.rainimator.rainimatormod.effect;

import com.rainimator.rainimatormod.RainimatorMod;
import com.rainimator.rainimatormod.registry.ModEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PurificationMobEffect extends MobEffect {
    private static final List<MobEffect> effects = new ArrayList<>();

    public PurificationMobEffect() {
        super(MobEffectCategory.BENEFICIAL, -10027009);
    }

    public static synchronized void initEffectList() {
        effects.add(ModEffects.FEAR_DARK.get());
        effects.add(ModEffects.SOUL_DEATH.get());
        effects.add(ModEffects.STUNNED.get());
        effects.add(ModEffects.ICE_PEOPLE.get());
        effects.add(MobEffects.BAD_OMEN);
        effects.add(MobEffects.BLINDNESS);
        effects.add(MobEffects.HUNGER);
        effects.add(MobEffects.HARM);
        effects.add(MobEffects.DIG_SLOWDOWN);
        effects.add(MobEffects.POISON);
        effects.add(MobEffects.MOVEMENT_SLOWDOWN);
        effects.add(MobEffects.UNLUCK);
        effects.add(MobEffects.WEAKNESS);
        effects.add(MobEffects.WITHER);
        effects.add(MobEffects.CONFUSION);
        effects.add(MobEffects.LEVITATION);
    }

    @Override
    public @NotNull String getDescriptionId() {
        return "effect." + RainimatorMod.MOD_ID + ".purification";
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
        if (effects.size() == 0) initEffectList();
        for (MobEffect effect : effects)
            if (entity.hasEffect(effect)) {
                entity.removeEffect(effect);
                break;
            }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
