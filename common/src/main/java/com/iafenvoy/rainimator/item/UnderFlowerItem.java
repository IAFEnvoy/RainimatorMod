package com.iafenvoy.rainimator.item;

import com.iafenvoy.neptune.object.SoundUtil;
import com.iafenvoy.rainimator.config.ServerConfig;
import com.iafenvoy.rainimator.data.ManaData;
import com.iafenvoy.rainimator.impl.ComponentManager;
import com.iafenvoy.rainimator.registry.RainimatorItemGroups;
import com.iafenvoy.rainimator.registry.RainimatorSounds;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class UnderFlowerItem extends Item {
    public UnderFlowerItem() {
        super(new Settings().maxCount(1).rarity(Rarity.UNCOMMON).arch$tab(RainimatorItemGroups.ITEM));
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        super.useOnBlock(context);
        if (context.getPlayer() == null) return ActionResult.PASS;
        ManaData data = ComponentManager.getManaData(context.getPlayer());
        if (!data.tryUseMana(context.getPlayer(), ServerConfig.getInstance().under_flower))
            return ActionResult.PASS;
        World world = context.getWorld();
        double x = context.getBlockPos().getX();
        double y = context.getBlockPos().getY();
        double z = context.getBlockPos().getZ();
        PlayerEntity entity = context.getPlayer();
        if (entity != null) {
            BlockState _bs = (entity.isSneaking() ? Blocks.SOUL_FIRE : Blocks.FIRE).getDefaultState();
            SoundUtil.playSound(world, x, y, z, RainimatorSounds.UNDER_FLOWER.get(), 1.0F, 1.0F);
            BlockPos _bp = new BlockPos((int) x, (int) (y + 1.0D), (int) z);
            world.setBlockState(_bp, _bs, 3);
            entity.getItemCooldownManager().set(context.getStack().getItem(), 400);
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public boolean hasGlint(ItemStack itemtack) {
        return true;
    }
}
