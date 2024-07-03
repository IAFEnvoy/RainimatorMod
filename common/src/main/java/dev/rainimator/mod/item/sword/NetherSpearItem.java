package dev.rainimator.mod.item.sword;

import dev.rainimator.mod.registry.RainimatorItemGroups;
import dev.rainimator.mod.registry.util.IRainimatorInfo;
import dev.rainimator.mod.registry.util.SwordItemBase;
import dev.rainimator.mod.registry.util.ToolMaterialUtil;
import dev.rainimator.mod.util.Episode;
import dev.rainimator.mod.util.ParticleUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;

public class NetherSpearItem extends SwordItemBase implements IRainimatorInfo {
    public NetherSpearItem() {
        super(ToolMaterialUtil.of(3000, 0.0F, 11.0F, 0, 25), 3, -2.2F, new Settings().fireproof().group(RainimatorItemGroups.MAIN));
    }

    @Override
    public boolean onSwingHand(ItemStack itemtack, World world, double x, double y, double z) {
        boolean retval = super.onSwingHand(itemtack, world, x, y, z);
        if (Math.random() < 0.2D)
            ParticleUtil.spawnCircleParticles(world, ParticleTypes.FLAME, x, y, z, 4, 0, 50);
        return retval;
    }

    @Override
    public Episode getEpisode() {
        return Episode.HardPillToSwallow;
    }
}
