package com.rainimator.rainimatormod.item;

import com.rainimator.rainimatormod.RainimatorMod;
import com.rainimator.rainimatormod.registry.util.FoilItemBase;
import com.rainimator.rainimatormod.registry.util.ModCreativeTab;
import com.rainimator.rainimatormod.util.MiscUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class UnderFlowerItem extends FoilItemBase {
    public UnderFlowerItem() {
        super(p -> p.tab(ModCreativeTab.items).stacksTo(1).rarity(Rarity.UNCOMMON));
    }

    @Override
    public @NotNull InteractionResult useOn(@NotNull UseOnContext context) {
        super.useOn(context);
        Level world = context.getLevel();
        double x = context.getClickedPos().getX();
        double y = context.getClickedPos().getY();
        double z = context.getClickedPos().getZ();
        Player entity = context.getPlayer();
        ItemStack itemstack = context.getItemInHand();
        if (entity != null) {
            BlockState _bs;
            if (entity.isShiftKeyDown())
                _bs = Blocks.SOUL_FIRE.defaultBlockState();
            else
                _bs = Blocks.FIRE.defaultBlockState();
            MiscUtil.playSound(world, x, y, z, new ResourceLocation(RainimatorMod.MOD_ID, "underflower_1"), 1.0F, 1.0F);
            BlockPos _bp = new BlockPos(x, y + 1.0D, z);
            world.setBlock(_bp, _bs, 3);
            entity.getCooldowns().addCooldown(itemstack.getItem(), 400);
        }
        return InteractionResult.SUCCESS;
    }
}
