package com.rainimator.rainimatormod.item;

import com.rainimator.rainimatormod.RainimatorMod;
import com.rainimator.rainimatormod.registry.ModItems;
import com.rainimator.rainimatormod.registry.util.ItemBase;
import com.rainimator.rainimatormod.registry.util.ModCreativeTab;
import com.rainimator.rainimatormod.util.MiscUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.items.ItemHandlerHelper;
import org.apache.commons.lang3.tuple.Triple;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MysteriousgiftboxItem extends ItemBase {
    private static final List<Triple<ItemLike, Integer, String>> lootTable = new ArrayList<>();

    public MysteriousgiftboxItem() {
        super(p -> p.tab(ModCreativeTab.items).stacksTo(16).rarity(Rarity.EPIC).food((new FoodProperties.Builder()).nutrition(0).saturationMod(0.0F).alwaysEat().build()));
    }

    private static synchronized void initLootTable() {
        lootTable.add(Triple.of(Items.ROTTEN_FLESH, 5, ""));
        lootTable.add(Triple.of(Items.SPIDER_EYE, 3, ""));
        lootTable.add(Triple.of(Items.BONE, 3, ""));
        lootTable.add(Triple.of(Blocks.SWEET_BERRY_BUSH, 3, ""));
        lootTable.add(Triple.of(Items.GOLD_NUGGET, 5, ""));
        lootTable.add(Triple.of(Items.IRON_NUGGET, 5, ""));
        lootTable.add(Triple.of(ModItems.COTTONCANDY.get(), 3, ""));
        lootTable.add(Triple.of(Items.GLOW_BERRIES, 3, ""));
        lootTable.add(Triple.of(Items.STICK, 6, ""));
        lootTable.add(Triple.of(Items.FEATHER, 3, ""));
        lootTable.add(Triple.of(Items.PHANTOM_MEMBRANE, 2, ""));
        lootTable.add(Triple.of(Items.APPLE, 3, ""));
        lootTable.add(Triple.of(Items.SUGAR_CANE, 3, ""));
        lootTable.add(Triple.of(Items.GLOWSTONE_DUST, 3, ""));
        lootTable.add(Triple.of(Items.GUNPOWDER, 3, ""));
        lootTable.add(Triple.of(Items.PAPER, 5, ""));
        lootTable.add(Triple.of(Items.CLAY_BALL, 5, ""));
        lootTable.add(Triple.of(Blocks.MAGMA_BLOCK, 5, ""));
        lootTable.add(Triple.of(Items.COAL, 5, ""));
        lootTable.add(Triple.of(Items.CHARCOAL, 5, "§a你有不错的手气！"));
        lootTable.add(Triple.of(Items.BAMBOO, 5, ""));
        lootTable.add(Triple.of(Items.LEATHER, 5, ""));
        lootTable.add(Triple.of(Items.STRING, 5, ""));
        lootTable.add(Triple.of(Items.WHEAT_SEEDS, 5, ""));
        lootTable.add(Triple.of(Items.WHEAT, 4, "§a你有不错的手气！"));
        lootTable.add(Triple.of(Items.ARROW, 5, ""));
        lootTable.add(Triple.of(Items.NETHER_WART, 5, ""));
        lootTable.add(Triple.of(Items.COPPER_INGOT, 5, ""));
        lootTable.add(Triple.of(Blocks.AMETHYST_BLOCK, 3, ""));
        lootTable.add(Triple.of(Items.ENDER_PEARL, 5, ""));
        lootTable.add(Triple.of(Items.ENDER_EYE, 3, ""));
        lootTable.add(Triple.of(Items.AMETHYST_SHARD, 3, ""));
        lootTable.add(Triple.of(Items.QUARTZ, 5, ""));
        lootTable.add(Triple.of(ModItems.ZOMBIE_HEART.get(), 3, ""));
        lootTable.add(Triple.of(ModItems.WITHER_BONE.get(), 3, ""));
        lootTable.add(Triple.of(ModItems.LOWER_BOUND_ALLOY_BONE.get(), 1, "§a你有不错的手气！"));
        lootTable.add(Triple.of(Items.IRON_INGOT, 3, ""));
        lootTable.add(Triple.of(Blocks.IRON_BLOCK, 1, "§a你有不错的手气！"));
        lootTable.add(Triple.of(ModItems.HOT_IRON.get(), 3, "§a欧皇附体！"));
        lootTable.add(Triple.of(Items.EXPERIENCE_BOTTLE, 3, ""));
        lootTable.add(Triple.of(Items.BLAZE_ROD, 3, ""));
        lootTable.add(Triple.of(Items.BLAZE_POWDER, 5, "§a你有不错的手气！"));
        lootTable.add(Triple.of(Items.SLIME_BALL, 5, ""));
        lootTable.add(Triple.of(Blocks.SLIME_BLOCK, 1, "§a你有不错的手气！"));
        lootTable.add(Triple.of(Items.ENCHANTED_GOLDEN_APPLE, 3, ""));
        lootTable.add(Triple.of(Items.GOLDEN_APPLE, 5, ""));
        lootTable.add(Triple.of(Items.BREAD, 5, ""));
        lootTable.add(Triple.of(Items.GOLDEN_CARROT, 5, "§a你有不错的手气！"));
        lootTable.add(Triple.of(Items.DIAMOND, 5, ""));
        lootTable.add(Triple.of(Blocks.DIAMOND_BLOCK, 1, ""));
        lootTable.add(Triple.of(ModItems.BLUEDIAMOND.get(), 5, "§a欧皇附体！"));
        lootTable.add(Triple.of(Items.GOLD_INGOT, 5, ""));
        lootTable.add(Triple.of(Blocks.GOLD_BLOCK, 1, "§a你有不错的手气！"));
        lootTable.add(Triple.of(Items.NETHERITE_INGOT, 5, ""));
        lootTable.add(Triple.of(Blocks.NETHERITE_BLOCK, 1, "§a你有不错的手气！"));
        lootTable.add(Triple.of(ModItems.SAPPRIES.get(), 3, ""));
        lootTable.add(Triple.of(ModItems.SAPPRIESBLOCK.get(), 1, "§a你有不错的手气！"));
        lootTable.add(Triple.of(ModItems.RUBY.get(), 3, ""));
        lootTable.add(Triple.of(ModItems.RUBYBLOCK.get(), 1, "§a你有不错的手气！"));
        lootTable.add(Triple.of(Items.EMERALD, 5, ""));
        lootTable.add(Triple.of(Blocks.EMERALD_BLOCK, 1, "§a你有不错的手气！"));
        lootTable.add(Triple.of(ModItems.SOULPEOPLE.get(), 3, ""));
        lootTable.add(Triple.of(ModItems.SUPER_DIAMOND_APPLE.get(), 1, "§a你有不错的手气！"));
        lootTable.add(Triple.of(Blocks.LAPIS_BLOCK, 1, ""));
        lootTable.add(Triple.of(Blocks.REDSTONE_BLOCK, 1, ""));
        lootTable.add(Triple.of(ModItems.MYSTERIOUSGIFTBOX.get(), 1, "§a套娃？"));
    }

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack itemstack) {
        return UseAnim.BLOCK;
    }

    @Override
    public int getUseDuration(@NotNull ItemStack itemstack) {
        return 1;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemstack, Level world, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(new TextComponent("§5神秘的礼盒，考研你手气的时候到了，有概率获得极品宝物哦！"));
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack itemstack, @NotNull Level world, @NotNull LivingEntity entity) {
        ItemStack retval = super.finishUsingItem(itemstack, world, entity);
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();

        if (entity instanceof Player _player) {
            MiscUtil.playSound(world, x, y, z, new ResourceLocation(RainimatorMod.MOD_ID, "gift_box"), 15.0F, 1.0F);
            ItemStack _stktoremove = new ItemStack(ModItems.MYSTERIOUSGIFTBOX.get());
            _player.getInventory().clearOrCountMatchingItems(p -> (_stktoremove.getItem() == p.getItem()), 0, _player.inventoryMenu.getCraftSlots());
            ItemStack stack = new ItemStack(Items.AIR);
            if (lootTable.size() == 0) initLootTable();
            for (Triple<ItemLike, Integer, String> itemLikeIntegerStringTriple : lootTable) {
                if (Math.random() < 0.1D) {
                    stack = new ItemStack(itemLikeIntegerStringTriple.getLeft(), itemLikeIntegerStringTriple.getMiddle());
                    if (!itemLikeIntegerStringTriple.getRight().isBlank() && !_player.level.isClientSide())
                        _player.displayClientMessage(new TextComponent(itemLikeIntegerStringTriple.getRight()), true);
                    break;
                }
            }
            if (stack.getItem() == Items.AIR)
                if (!_player.level.isClientSide())
                    _player.displayClientMessage(new TextComponent("§a你用力过大撕碎了礼盒！"), true);
            ItemHandlerHelper.giveItemToPlayer(_player, stack);
        }
        return retval;
    }
}
