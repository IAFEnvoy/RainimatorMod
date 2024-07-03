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

public class RainSwordItem extends SwordItemBase implements IRainimatorInfo {
    public RainSwordItem() {
        super(ToolMaterialUtil.of(2000, 4.0F, 11.0F, 0, 20), 3, -2.0F, new Settings().arch$tab(RainimatorItemGroups.MAIN));
    }

    @Override
    public boolean onSwingHand(ItemStack itemtack, World world, double x, double y, double z) {
        boolean ret_val = super.onSwingHand(itemtack, world, x, y, z);
        if (Math.random() < 0.2D)
            ParticleUtil.spawnCircleParticles(world, ParticleTypes.ENCHANT, x, y, z, 3, 0, 50);
        return ret_val;
    }

    @Override
    public Episode getEpisode() {
        return Episode.ColdAsIce;
    }
}
