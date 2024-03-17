package com.rainimator.rainimatormod.item;

import com.rainimator.rainimatormod.gui.inventory.EnderBookSkillMenu;
import com.rainimator.rainimatormod.registry.util.ItemBase;
import com.rainimator.rainimatormod.registry.util.ModCreativeTab;
import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class EnderBookItem extends ItemBase {
    public EnderBookItem() {
        super(p -> p.tab(ModCreativeTab.items).stacksTo(1).rarity(Rarity.EPIC));
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemstack, Level world, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(new TranslatableComponent("item.rainimator.enderbook.tooltip"));
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level world, @NotNull Player entity, @NotNull InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();

        if (entity instanceof ServerPlayer _ent) {
            final BlockPos _bpos = new BlockPos(x, y, z);
            NetworkHooks.openGui(_ent, new MenuProvider() {
                @Override
                public @NotNull Component getDisplayName() {
                    return new TextComponent("Enderbookskill");
                }

                @Override
                public AbstractContainerMenu createMenu(int id, @NotNull Inventory inventory, @NotNull Player player) {
                    return new EnderBookSkillMenu(id, inventory, (new FriendlyByteBuf(Unpooled.buffer())).writeBlockPos(_bpos));
                }
            }, _bpos);
        }

        return ar;
    }
}
