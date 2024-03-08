package com.rainimator.rainimatormod.item;

import com.rainimator.rainimatormod.registry.ModItems;
import com.rainimator.rainimatormod.registry.util.ModCreativeTab;
import com.rainimator.rainimatormod.registry.util.TierBase;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import org.jetbrains.annotations.NotNull;

public class HerobrineTomahawkItem extends SwordItem {
    public HerobrineTomahawkItem() {
        super(TierBase.of(2500, 12.0F, 11.0F, 0, 20, ModItems.SUPER_RUBY, ModItems.SUPER_SPPARIES), 3, -2.2F, ModCreativeTab.createProperty().fireResistant());
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack itemstack, @NotNull LivingEntity entity, @NotNull LivingEntity sourceentity) {
        boolean ret_val = super.hurtEnemy(itemstack, entity, sourceentity);
        if (entity.level.isClientSide()) return ret_val;
        entity.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 0));
        if (Math.random() < 0.5D) {
            entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 60, 0));
            if (Math.random() < 0.1D) {
                entity.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 1));
                if (Math.random() < 0.05D) {
                    entity.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 1));
                    if (Math.random() < 0.025D) {
                        entity.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 2));
                        if (Math.random() < 0.001D) entity.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 3));
                    }
                }
            }
        }
        return ret_val;
    }
}
