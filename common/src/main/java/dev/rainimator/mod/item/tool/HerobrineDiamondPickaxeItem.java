package dev.rainimator.mod.item.tool;

import dev.rainimator.mod.registry.RainimatorItemGroups;
import dev.rainimator.mod.registry.util.ToolMaterialUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PickaxeItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HerobrineDiamondPickaxeItem extends PickaxeItem {
    public HerobrineDiamondPickaxeItem() {
        super(ToolMaterialUtil.of(2500, 20.0F, 5.0F, 4, 25, () -> Items.DIAMOND), 1, -2.2F, new Settings().fireproof().group(RainimatorItemGroups.MAIN));
    }

    @Override
    public boolean postMine(ItemStack itemtack, World world, BlockState blockstate, BlockPos pos, LivingEntity entity) {
        boolean retval = super.postMine(itemtack, world, blockstate, pos, entity);
        double x = pos.getX();
        double y = pos.getY();
        double z = pos.getZ();
        if (Math.random() < 0.5D)
            for (double i = -1; i <= 1; i++)
                for (double j = -1; j <= 1; j++)
                    if (i != 0 || j != 0) {
                        BlockPos _pos = new BlockPos((int) (x + i), (int) y, (int) (z + j));
                        world.breakBlock(_pos, true);
                    }
        return retval;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public boolean hasGlint(ItemStack itemtack) {
        return true;
    }
}
