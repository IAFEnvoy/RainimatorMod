package dev.rainimator.mod.item;

import dev.rainimator.mod.item.util.ItemBase;
import dev.rainimator.mod.registry.RainimatorItemGroups;
import dev.rainimator.mod.screen.handler.EnderBookSkillScreenHandler;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EnderBookItem extends ItemBase implements NamedScreenHandlerFactory {
    public EnderBookItem() {
        super(p -> p.maxCount(1).rarity(Rarity.EPIC).arch$tab(RainimatorItemGroups.ITEM));
    }

    @Override
    public void appendTooltip(ItemStack itemtack, World world, List<Text> list, TooltipContext flag) {
        super.appendTooltip(itemtack, world, list, flag);
        list.add(Text.translatable("item.rainimator.ender_book.tooltip"));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity entity, Hand hand) {
        entity.openHandledScreen(this);
        return super.use(world, entity, hand);
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new EnderBookSkillScreenHandler(syncId, playerInventory);
    }
}
