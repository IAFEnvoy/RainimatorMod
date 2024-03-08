package com.rainimator.rainimatormod.effect;

import com.rainimator.rainimatormod.RainimatorMod;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import org.jetbrains.annotations.NotNull;

public class StunnedMobEffect extends MobEffect {
    public StunnedMobEffect() {
        super(MobEffectCategory.HARMFUL, -13210);
    }

    @Override
    public @NotNull String getDescriptionId() {
        return "effect." + RainimatorMod.MOD_ID + ".stunned";
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
        if (entity instanceof Mob _entity)
            _entity.getNavigation().stop();
        if (entity instanceof LivingEntity)
            if (!entity.level.isClientSide()) {
                entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 4));
                entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 40, 4));
            }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
