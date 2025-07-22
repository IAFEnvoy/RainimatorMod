package com.iafenvoy.rainimator.item;

import com.iafenvoy.neptune.object.SoundUtil;
import com.iafenvoy.rainimator.registry.RainimatorBlocks;
import com.iafenvoy.rainimator.registry.RainimatorItemGroups;
import com.iafenvoy.rainimator.registry.RainimatorItems;
import com.iafenvoy.rainimator.registry.RainimatorSounds;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.text.Text;
import net.minecraft.util.Rarity;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import org.apache.commons.lang3.tuple.Triple;

import java.util.ArrayList;
import java.util.List;

public class MysteriousGiftBoxItem extends Item {
    private static final List<Triple<ItemConvertible, Integer, String>> LOOT_TABLES = new ArrayList<>();

    public MysteriousGiftBoxItem() {
        super(new Settings().maxCount(16).rarity(Rarity.EPIC).food((new FoodComponent.Builder()).hunger(0).saturationModifier(0.0F).alwaysEdible().build()).arch$tab(RainimatorItemGroups.ITEM));
    }

    private static synchronized void initLootTable() {
        LOOT_TABLES.add(Triple.of(Items.ROTTEN_FLESH, 5, ""));
        LOOT_TABLES.add(Triple.of(Items.SPIDER_EYE, 3, ""));
        LOOT_TABLES.add(Triple.of(Items.BONE, 3, ""));
        LOOT_TABLES.add(Triple.of(Blocks.SWEET_BERRY_BUSH, 3, ""));
        LOOT_TABLES.add(Triple.of(Items.GOLD_NUGGET, 5, ""));
        LOOT_TABLES.add(Triple.of(Items.IRON_NUGGET, 5, ""));
        LOOT_TABLES.add(Triple.of(RainimatorItems.COTTON_CANDY.get(), 3, ""));
        LOOT_TABLES.add(Triple.of(Items.GLOW_BERRIES, 3, ""));
        LOOT_TABLES.add(Triple.of(Items.STICK, 6, ""));
        LOOT_TABLES.add(Triple.of(Items.FEATHER, 3, ""));
        LOOT_TABLES.add(Triple.of(Items.PHANTOM_MEMBRANE, 2, ""));
        LOOT_TABLES.add(Triple.of(Items.APPLE, 3, ""));
        LOOT_TABLES.add(Triple.of(Items.SUGAR_CANE, 3, ""));
        LOOT_TABLES.add(Triple.of(Items.GLOWSTONE_DUST, 3, ""));
        LOOT_TABLES.add(Triple.of(Items.GUNPOWDER, 3, ""));
        LOOT_TABLES.add(Triple.of(Items.PAPER, 5, ""));
        LOOT_TABLES.add(Triple.of(Items.CLAY_BALL, 5, ""));
        LOOT_TABLES.add(Triple.of(Blocks.MAGMA_BLOCK, 5, ""));
        LOOT_TABLES.add(Triple.of(Items.COAL, 5, ""));
        LOOT_TABLES.add(Triple.of(Items.CHARCOAL, 5, "item.rainimator.mysterious_gift_box.goodluck1"));
        LOOT_TABLES.add(Triple.of(Items.BAMBOO, 5, ""));
        LOOT_TABLES.add(Triple.of(Items.LEATHER, 5, ""));
        LOOT_TABLES.add(Triple.of(Items.STRING, 5, ""));
        LOOT_TABLES.add(Triple.of(Items.WHEAT_SEEDS, 5, ""));
        LOOT_TABLES.add(Triple.of(Items.WHEAT, 4, "item.rainimator.mysterious_gift_box.goodluck1"));
        LOOT_TABLES.add(Triple.of(Items.ARROW, 5, ""));
        LOOT_TABLES.add(Triple.of(Items.NETHER_WART, 5, ""));
        LOOT_TABLES.add(Triple.of(Items.COPPER_INGOT, 5, ""));
        LOOT_TABLES.add(Triple.of(Blocks.AMETHYST_BLOCK, 3, ""));
        LOOT_TABLES.add(Triple.of(Items.ENDER_PEARL, 5, ""));
        LOOT_TABLES.add(Triple.of(Items.ENDER_EYE, 3, ""));
        LOOT_TABLES.add(Triple.of(Items.AMETHYST_SHARD, 3, ""));
        LOOT_TABLES.add(Triple.of(Items.QUARTZ, 5, ""));
        LOOT_TABLES.add(Triple.of(RainimatorItems.ZOMBIE_HEART.get(), 3, ""));
        LOOT_TABLES.add(Triple.of(RainimatorItems.WITHER_BONE.get(), 3, ""));
        LOOT_TABLES.add(Triple.of(RainimatorItems.NETHERITE_WITHER_BONE.get(), 1, "item.rainimator.mysterious_gift_box.goodluck1"));
        LOOT_TABLES.add(Triple.of(Items.IRON_INGOT, 3, ""));
        LOOT_TABLES.add(Triple.of(Blocks.IRON_BLOCK, 1, "item.rainimator.mysterious_gift_box.goodluck1"));
        LOOT_TABLES.add(Triple.of(RainimatorItems.HOT_IRON.get(), 3, "item.rainimator.mysterious_gift_box.goodluck2"));
        LOOT_TABLES.add(Triple.of(Items.EXPERIENCE_BOTTLE, 3, ""));
        LOOT_TABLES.add(Triple.of(Items.BLAZE_ROD, 3, ""));
        LOOT_TABLES.add(Triple.of(Items.BLAZE_POWDER, 5, "item.rainimator.mysterious_gift_box.goodluck1"));
        LOOT_TABLES.add(Triple.of(Items.SLIME_BALL, 5, ""));
        LOOT_TABLES.add(Triple.of(Blocks.SLIME_BLOCK, 1, "item.rainimator.mysterious_gift_box.goodluck1"));
        LOOT_TABLES.add(Triple.of(Items.ENCHANTED_GOLDEN_APPLE, 3, ""));
        LOOT_TABLES.add(Triple.of(Items.GOLDEN_APPLE, 5, ""));
        LOOT_TABLES.add(Triple.of(Items.BREAD, 5, ""));
        LOOT_TABLES.add(Triple.of(Items.GOLDEN_CARROT, 5, "item.rainimator.mysterious_gift_box.goodluck1"));
        LOOT_TABLES.add(Triple.of(Items.DIAMOND, 5, ""));
        LOOT_TABLES.add(Triple.of(Blocks.DIAMOND_BLOCK, 1, ""));
        LOOT_TABLES.add(Triple.of(RainimatorItems.BLUE_DIAMOND.get(), 5, "item.rainimator.mysterious_gift_box.goodluck2"));
        LOOT_TABLES.add(Triple.of(Items.GOLD_INGOT, 5, ""));
        LOOT_TABLES.add(Triple.of(Blocks.GOLD_BLOCK, 1, "item.rainimator.mysterious_gift_box.goodluck1"));
        LOOT_TABLES.add(Triple.of(Items.NETHERITE_INGOT, 5, ""));
        LOOT_TABLES.add(Triple.of(Blocks.NETHERITE_BLOCK, 1, "item.rainimator.mysterious_gift_box.goodluck1"));
        LOOT_TABLES.add(Triple.of(RainimatorItems.SAPPHIRE.get(), 3, ""));
        LOOT_TABLES.add(Triple.of(RainimatorBlocks.SAPPHIRE_BLOCK.get(), 1, "item.rainimator.mysterious_gift_box.goodluck1"));
        LOOT_TABLES.add(Triple.of(RainimatorItems.RUBY.get(), 3, ""));
        LOOT_TABLES.add(Triple.of(RainimatorBlocks.RUBY_BLOCK.get(), 1, "item.rainimator.mysterious_gift_box.goodluck1"));
        LOOT_TABLES.add(Triple.of(Items.EMERALD, 5, ""));
        LOOT_TABLES.add(Triple.of(Blocks.EMERALD_BLOCK, 1, "item.rainimator.mysterious_gift_box.goodluck1"));
        LOOT_TABLES.add(Triple.of(RainimatorItems.SOUL_PEOPLE.get(), 3, ""));
        LOOT_TABLES.add(Triple.of(RainimatorItems.SUPER_DIAMOND_APPLE.get(), 1, "item.rainimator.mysterious_gift_box.goodluck1"));
        LOOT_TABLES.add(Triple.of(Blocks.LAPIS_BLOCK, 1, ""));
        LOOT_TABLES.add(Triple.of(Blocks.REDSTONE_BLOCK, 1, ""));
        LOOT_TABLES.add(Triple.of(RainimatorItems.MYSTERIOUS_GIFT_BOX.get(), 1, "item.rainimator.mysterious_gift_box.goodluck3"));
    }

    @Override
    public UseAction getUseAction(ItemStack itemtack) {
        return UseAction.BLOCK;
    }

    @Override
    public int getMaxUseTime(ItemStack itemtack) {
        return 1;
    }

    @Override
    public void appendTooltip(ItemStack itemtack, World world, List<Text> list, TooltipContext flag) {
        super.appendTooltip(itemtack, world, list, flag);
        list.add(Text.translatable("item.rainimator.mysterious_gift_box.tooltip"));
    }

    @Override
    public ItemStack finishUsing(ItemStack itemtack, World world, LivingEntity entity) {
        ItemStack retval = super.finishUsing(itemtack, world, entity);
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();

        if (entity instanceof PlayerEntity _player) {
            SoundUtil.playSound(world, x, y, z, RainimatorSounds.GIFT_BOX.get(), 15.0F, 1.0F);
            ItemStack _stktoremove = new ItemStack(RainimatorItems.MYSTERIOUS_GIFT_BOX.get());
            _player.getInventory().remove(p -> (_stktoremove.getItem() == p.getItem()), 0, _player.playerScreenHandler.getCraftingInput());
            ItemStack stack = new ItemStack(Items.AIR);
            if (LOOT_TABLES.isEmpty()) initLootTable();
            for (Triple<ItemConvertible, Integer, String> triple : LOOT_TABLES) {
                if (Math.random() < 0.1D) {
                    stack = new ItemStack(triple.getLeft(), triple.getMiddle());
                    if (!triple.getRight().isBlank() && !_player.getWorld().isClient)
                        _player.sendMessage(Text.translatable(triple.getRight()), true);
                    break;
                }
            }
            if (stack.getItem() == Items.AIR)
                if (!_player.getWorld().isClient)
                    _player.sendMessage(Text.translatable("item.rainimator.mysterious_gift_box.goodluck4"), true);
            _player.getInventory().insertStack(stack);
            _player.getInventory().markDirty();
        }
        return retval;
    }
}
