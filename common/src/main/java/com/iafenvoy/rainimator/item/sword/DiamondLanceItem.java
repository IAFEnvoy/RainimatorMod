package com.iafenvoy.rainimator.item.sword;

import com.iafenvoy.neptune.object.item.SwordItemBase;
import com.iafenvoy.neptune.object.item.ToolMaterialUtil;
import com.iafenvoy.rainimator.registry.RainimatorItemGroups;
import com.iafenvoy.rainimator.registry.RainimatorItems;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class DiamondLanceItem extends SwordItemBase {
    public DiamondLanceItem() {
        super(ToolMaterialUtil.of(2000, 4.0F, 7.0F, 0, 3, RainimatorItems.BLUE_DIAMOND::get), 3, -2.0F, new Settings().arch$tab(RainimatorItemGroups.MAIN));
    }

    @Override
    public boolean postHit(ItemStack itemtack, LivingEntity entity, LivingEntity sourceentity) {
        boolean ret_val = super.postHit(itemtack, entity, sourceentity);
        if (Math.random() < 0.1D) {
            if (entity.getWorld() instanceof ServerWorld _level) {
                LightningEntity entityToSpawn = EntityType.LIGHTNING_BOLT.create(_level);
                if (entityToSpawn != null) {
                    entityToSpawn.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(new BlockPos((int) entity.getX(), (int) entity.getY(), (int) entity.getZ())));
                    entityToSpawn.setCosmetic(true);
                    _level.spawnEntity(entityToSpawn);
                }
            }
            entity.getWorld().setBlockState(new BlockPos((int) entity.getX(), (int) entity.getY(), (int) entity.getZ()), Blocks.FIRE.getDefaultState(), 3);
        }
        return ret_val;
    }
}
