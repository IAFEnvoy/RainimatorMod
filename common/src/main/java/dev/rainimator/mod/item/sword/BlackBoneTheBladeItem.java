package dev.rainimator.mod.item.sword;

import dev.rainimator.mod.registry.RainimatorItemGroups;
import dev.rainimator.mod.registry.util.FoilSwordItemBase;
import dev.rainimator.mod.registry.util.IRainimatorInfo;
import dev.rainimator.mod.registry.util.ToolMaterialUtil;
import dev.rainimator.mod.util.Episode;
import dev.rainimator.mod.util.ParticleUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;

public class BlackBoneTheBladeItem extends FoilSwordItemBase implements IRainimatorInfo {
    public BlackBoneTheBladeItem() {
        super(ToolMaterialUtil.of(1500, 0.0F, 7.0F, 0, 10), 3, -2.4F, new Settings().fireproof().group(RainimatorItemGroups.MAIN));
    }

    @Override
    public boolean onSwingHand(ItemStack itemtack, World world, double x, double y, double z) {
        boolean retval = super.onSwingHand(itemtack, world, x, y, z);
        if (Math.random() < 0.2D)
            ParticleUtil.spawnCircleParticles(world, ParticleTypes.LAVA, x, y, z, 4, 0, 50);
        return retval;
    }

    @Override
    public Episode getEpisode() {
        return Episode.WeAreTheDanger;
    }
}
