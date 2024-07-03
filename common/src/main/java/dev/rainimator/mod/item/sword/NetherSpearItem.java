package dev.rainimator.mod.item.sword;

import dev.rainimator.mod.registry.RainimatorItemGroups;
import dev.rainimator.mod.registry.util.IRainimatorInfo;
import dev.rainimator.mod.registry.util.SwordItemBase;
import dev.rainimator.mod.registry.util.ToolMaterialUtil;
import dev.rainimator.mod.util.Episode;
import dev.rainimator.mod.util.ParticleUtil;
import dev.rainimator.mod.util.RandomHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;

public class NetherSpearItem extends SwordItemBase implements IRainimatorInfo {
    public NetherSpearItem() {
        super(ToolMaterialUtil.of(3000, 0.0F, 11.0F, 0, 25), 3, -2.2F, new Settings().fireproof().arch$tab(RainimatorItemGroups.MAIN));
    }

    @Override
    public boolean postHit(ItemStack itemtack, LivingEntity entity, LivingEntity sourceentity) {
        boolean retval = super.postHit(itemtack, entity, sourceentity);
        if (entity instanceof MobEntity _entity)
            _entity.setTarget(sourceentity);
        if (Math.random() < 0.2D)
            entity.setOnFireFor(10);
        if (Math.random() < 0.2D)
            sourceentity.setHealth(((sourceentity instanceof LivingEntity) ? sourceentity.getHealth() : -1.0F) + RandomHelper.nextInt(1, 4));
        return retval;
    }

    @Override
    public boolean onSwingHand(ItemStack itemtack, World world, double x, double y, double z) {
        boolean retval = super.onSwingHand(itemtack, world, x, y, z);
        if (Math.random() < 0.2D)
            ParticleUtil.spawnCircleParticles(world, ParticleTypes.FLAME, x, y, z, 4, 0, 50);
        return retval;
    }

    @Override
    public void inventoryTick(ItemStack itemtack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(itemtack, world, entity, slot, selected);
        if (selected && entity instanceof LivingEntity _livEnt && !_livEnt.getWorld().isClient()) {
            if (_livEnt.getArmor() <= 10)
                _livEnt.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 100, 3));
            else if (_livEnt.getArmor() <= 20)
                _livEnt.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 100, 2));
            else if (_livEnt.getArmor() <= 30)
                _livEnt.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 100, 1));
            else if (_livEnt.getArmor() <= 40)
                _livEnt.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 100, 0));
        }
    }

    @Override
    public Episode getEpisode() {
        return Episode.HardPillToSwallow;
    }
}
