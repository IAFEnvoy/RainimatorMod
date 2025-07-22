package com.iafenvoy.rainimator.item.sword;

import com.iafenvoy.neptune.object.ParticleUtil;
import com.iafenvoy.neptune.object.SoundUtil;
import com.iafenvoy.neptune.object.item.ISwingable;
import com.iafenvoy.neptune.object.item.ToolMaterialUtil;
import com.iafenvoy.rainimator.registry.RainimatorItemGroups;
import com.iafenvoy.rainimator.registry.RainimatorParticles;
import com.iafenvoy.rainimator.registry.RainimatorSounds;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

public class BlackDeathSwordItem extends SwordItem implements ISwingable {
    public BlackDeathSwordItem() {
        super(ToolMaterialUtil.of(1000, 0.0F, 9.0F, 0, 30), 3, -1.7F, new Settings().fireproof().arch$tab(RainimatorItemGroups.MAIN));
    }

    @Override
    public boolean postHit(ItemStack itemtack, LivingEntity entity, LivingEntity sourceentity) {
        boolean retval = super.postHit(itemtack, entity, sourceentity);
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();
        if (entity instanceof MobEntity _entity)
            _entity.setTarget(sourceentity);
        if (Math.random() < 0.7D) {
            if (!entity.getWorld().isClient) {
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 100, 2));
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 100, 2));
            }
            if (Math.random() < 0.7D)
                SoundUtil.playSound(entity.getWorld(), x, y, z, RainimatorSounds.BLACK_DEATH_SWORD_SKILLS.get(), 4.0F, 1.0F);
            else
                SoundUtil.playSound(entity.getWorld(), x, y, z, RainimatorSounds.BLACK_DEATH_SWORD_SKILL_3.get(), 4.0F, 1.0F);
            if (entity.getWorld() instanceof ServerWorld _level)
                _level.spawnParticles(RainimatorParticles.FLOWER_WHITE.get(), x, y, z, 50, 0.5D, 1.0D, 0.5D, 0.1D);
        } else {
            if (!sourceentity.getWorld().isClient)
                sourceentity.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 300, 1));
        }

        return retval;
    }

    @Override
    public boolean onSwingHand(ItemStack itemtack, World world, double x, double y, double z) {
        if (Math.random() < 0.2D) ParticleUtil.spawnCircleParticles(world, ParticleTypes.SOUL, x, y, z, 3, 0, 50);
        return false;
    }
}
