package com.rainimator.rainimatormod.item.tool;

import com.rainimator.rainimatormod.registry.util.ModCreativeTab;
import com.rainimator.rainimatormod.registry.util.TierBase;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

public class HerobrineDiamondPickaxeItem extends PickaxeItem {
    public HerobrineDiamondPickaxeItem() {
        super(TierBase.of(2500, 20.0F, 5.0F, 4, 25, Items.DIAMOND), 1, -2.2F, ModCreativeTab.createProperty().fireResistant());
    }

    @Override
    public boolean mineBlock(@NotNull ItemStack itemstack, @NotNull Level world, @NotNull BlockState blockstate, @NotNull BlockPos pos, @NotNull LivingEntity entity) {
        boolean retval = super.mineBlock(itemstack, world, blockstate, pos, entity);
        double x = pos.getX();
        double y = pos.getY();
        double z = pos.getZ();
        if (Math.random() < 0.5D)
            for (double i = -1; i <= 1; i++)
                for (double j = -1; j <= 1; j++)
                    if (i != 0 || j != 0) {
                        BlockPos _pos = new BlockPos(x + i, y, z + j);
                        Block.dropResources(((LevelAccessor) world).getBlockState(_pos), world, new BlockPos(x, y, z), null);
                        world.destroyBlock(_pos, false);
                    }
        return retval;
    }

    @Override
    public @NotNull InteractionResult useOn(@NotNull UseOnContext context) {
        super.useOn(context);
        Level world = context.getLevel();
        double x = context.getClickedPos().getX();
        double y = context.getClickedPos().getY();
        double z = context.getClickedPos().getZ();
        if (context.getPlayer() != null) {
            world.destroyBlock(new BlockPos(x, y, z), false);
            if (!world.isClientSide())
                world.addFreshEntity(new ExperienceOrb(world, x, y, z, 10));
            if (context.getPlayer() instanceof Player)
                if (!context.getPlayer().level.isClientSide())
                    context.getPlayer().displayClientMessage(new TextComponent("你使用神力消除了一个方块"), true);
            if (context.getPlayer() instanceof Player)
                context.getPlayer().getCooldowns().addCooldown(context.getItemInHand().getItem(), 4800);
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public boolean isFoil(@NotNull ItemStack itemstack) {
        return true;
    }
}
