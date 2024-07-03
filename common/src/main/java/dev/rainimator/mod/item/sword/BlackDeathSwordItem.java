package dev.rainimator.mod.item.sword;

import dev.rainimator.mod.registry.util.IRainimatorInfo;
import dev.rainimator.mod.registry.util.SwordItemBase;
import dev.rainimator.mod.registry.util.ToolMaterialUtil;
import dev.rainimator.mod.util.Episode;
import dev.rainimator.mod.util.ParticleUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;

public class BlackDeathSwordItem extends SwordItemBase implements IRainimatorInfo {
    public BlackDeathSwordItem() {
        super(ToolMaterialUtil.of(1000, 0.0F, 9.0F, 0, 30), 3, -1.7F, new Settings().fireproof());
    }

    @Override
    public boolean onSwingHand(ItemStack itemtack, World world, double x, double y, double z) {
        boolean retval = super.onSwingHand(itemtack, world, x, y, z);
        if (Math.random() < 0.2D)
            ParticleUtil.spawnCircleParticles(world, ParticleTypes.SOUL, x, y, z, 3, 0, 50);
        return retval;
    }

    @Override
    public Episode getEpisode() {
        return Episode.Unknown;
    }
}
