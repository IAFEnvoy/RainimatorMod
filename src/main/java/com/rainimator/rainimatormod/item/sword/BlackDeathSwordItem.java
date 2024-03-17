package com.rainimator.rainimatormod.item.sword;

import com.rainimator.rainimatormod.RainimatorMod;
import com.rainimator.rainimatormod.registry.ModParticleTypes;
import com.rainimator.rainimatormod.registry.util.IRainimatorInfo;
import com.rainimator.rainimatormod.registry.util.ModCreativeTab;
import com.rainimator.rainimatormod.registry.util.SwordItemBase;
import com.rainimator.rainimatormod.registry.util.TierBase;
import com.rainimator.rainimatormod.util.Episode;
import com.rainimator.rainimatormod.util.MiscUtil;
import com.rainimator.rainimatormod.util.ParticleUtil;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BlackDeathSwordItem extends SwordItemBase implements IRainimatorInfo {
    public BlackDeathSwordItem() {
        super(TierBase.of(1000, 0.0F, 9.0F, 0, 30), 3, -1.7F, ModCreativeTab.createProperty().fireResistant());
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack itemstack, @NotNull LivingEntity entity, @NotNull LivingEntity sourceentity) {
        boolean retval = super.hurtEnemy(itemstack, entity, sourceentity);
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();
        if (entity instanceof Mob _entity)
            _entity.setTarget(sourceentity);
        if (Math.random() < 0.7D) {
            if (!entity.level.isClientSide()) {
                entity.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 2));
                entity.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 2));
            }
            if (Math.random() < 0.7D)
                MiscUtil.playSound(entity.level, x, y, z, new ResourceLocation(RainimatorMod.MOD_ID, "black_death_sword_skills"), 4.0F, 1.0F);
            else
                MiscUtil.playSound(entity.level, x, y, z, new ResourceLocation(RainimatorMod.MOD_ID, "black_death_sword_skill_3"), 4.0F, 1.0F);
            if (entity.level instanceof ServerLevel _level)
                _level.sendParticles((ParticleOptions) ModParticleTypes.FLOWERWRITE.get(), x, y, z, 50, 0.5D, 1.0D, 0.5D, 0.1D);
        } else {
            if (!sourceentity.level.isClientSide())
                sourceentity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 300, 1));
        }

        return retval;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemstack, Level world, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
    }

    @Override
    public boolean onEntitySwing(ItemStack itemstack, LivingEntity entity) {
        boolean retval = super.onEntitySwing(itemstack, entity);
        if (Math.random() < 0.2D)
            ParticleUtil.spawnCircleParticles(entity.level, ParticleTypes.SOUL, entity.getX(), entity.getY(), entity.getZ(), 3, 0, 50);
        return retval;
    }

    @Override
    public Episode getEpisode() {
        return Episode.Unknown;
    }
}
