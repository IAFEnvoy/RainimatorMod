package com.rainimator.rainimatormod.registry;

import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.BasicItemListing;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModTrades {
    @SubscribeEvent
    public static void registerTrades(VillagerTradesEvent event) {
        if (event.getType() == VillagerProfession.TOOLSMITH) {
            event.getTrades().get(2).add(new BasicItemListing(new ItemStack(ModItems.RAW_RUBY.get()), new ItemStack(ModItems.SAPPHIRE.get()), 25, 5, 0.05F));
            event.getTrades().get(2).add(new BasicItemListing(new ItemStack(ModItems.RAW_SAPPHIRE.get()), new ItemStack(ModItems.RUBY.get()), 25, 5, 0.05F));
            event.getTrades().get(4).add(new BasicItemListing(new ItemStack(ModItems.SUPER_RUBY.get()), new ItemStack(ModItems.DIAMOND_APPLE_SUPPER.get(), 3), 5, 5, 0.05F));
            event.getTrades().get(4).add(new BasicItemListing(new ItemStack(ModItems.SUPER_SAPPHIRE.get()), new ItemStack(ModItems.DIAMOND_APPLE_SUPPER.get(), 3), 5, 5, 0.05F));
            event.getTrades().get(5).add(new BasicItemListing(new ItemStack(ModItems.BLUE_DIAMOND.get()), new ItemStack(ModItems.SUPER_RUBY.get()), new ItemStack(ModItems.DIAMOND_APPLE_SUPPER.get(), 3), 1, 5, 0.05F));
            event.getTrades().get(5).add(new BasicItemListing(new ItemStack(ModItems.BLUE_DIAMOND.get()), new ItemStack(ModItems.SUPER_SAPPHIRE.get()), new ItemStack(ModItems.DIAMOND_APPLE_SUPPER.get(), 3), 1, 5, 0.05F));
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(ModItems.DIAMOND_HATCHET.get()), new ItemStack(ModItems.UNDER_FLOWER.get()), new ItemStack(ModItems.INTELLIGENCE_TOMAHAWK.get()), 1, 5, 0.05F));
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(ModItems.SOUL_PEOPLE.get()), new ItemStack(ModItems.RUBY.get(), 2), 5, 5, 0.05F));
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(ModItems.SOUL_PEOPLE.get()), new ItemStack(ModItems.SAPPHIRE.get(), 2), 5, 5, 0.05F));
            event.getTrades().get(2).add(new BasicItemListing(new ItemStack(ModItems.RUBY.get(), 2), new ItemStack(ModItems.RUBY_PICKAXE_1.get()), 1, 25, 0.05F));
            event.getTrades().get(2).add(new BasicItemListing(new ItemStack(ModItems.RUBY.get(), 2), new ItemStack(ModItems.RUBY_AXE.get()), 1, 25, 0.05F));
            event.getTrades().get(2).add(new BasicItemListing(new ItemStack(ModItems.SAPPHIRE.get(), 2), new ItemStack(ModItems.SAPPHIRE_PICKAXE.get()), 1, 25, 0.05F));
            event.getTrades().get(2).add(new BasicItemListing(new ItemStack(ModItems.SAPPHIRE.get(), 2), new ItemStack(ModItems.SAPPHIRE_AXE.get()), 1, 25, 0.05F));
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(Items.IRON_INGOT, 2), new ItemStack(Items.COPPER_INGOT, 3), new ItemStack(Items.DIAMOND), 10, 5, 0.05F));
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(Blocks.AMETHYST_BLOCK, 2), new ItemStack(Items.COPPER_INGOT, 3), new ItemStack(Items.DIAMOND), 10, 5, 0.05F));
        }
        if (event.getType() == VillagerProfession.WEAPONSMITH) {
            event.getTrades().get(2).add(new BasicItemListing(new ItemStack(ModItems.ZOMBIE_HEART.get(), 10), new ItemStack(ModItems.WITHER_BONE.get(), 2), new ItemStack(ModItems.SUPER_RUBY.get()), 5, 5, 0.05F));
            event.getTrades().get(2).add(new BasicItemListing(new ItemStack(ModItems.ZOMBIE_HEART.get(), 10), new ItemStack(ModItems.WITHER_BONE.get(), 2), new ItemStack(ModItems.SUPER_SAPPHIRE.get()), 5, 5, 0.05F));
            event.getTrades().get(3).add(new BasicItemListing(new ItemStack(ModItems.ZOMBIE_HEART.get(), 20), new ItemStack(ModItems.WITHER_BONE.get(), 5), new ItemStack(ModItems.BLUE_DIAMOND.get()), 3, 5, 0.05F));
            event.getTrades().get(5).add(new BasicItemListing(new ItemStack(ModItems.RUBY.get(), 2), new ItemStack(ModItems.SAPPHIRE.get(), 2), new ItemStack(ModItems.BLUE_DIAMOND.get()), 3, 5, 0.05F));
            event.getTrades().get(5).add(new BasicItemListing(new ItemStack(ModItems.HOT_IRON.get(), 2), new ItemStack(Items.IRON_SWORD), new ItemStack(ModItems.LOWER_BOUND_ALLOY_FLAME_SABER.get()), 1, 5, 0.05F));
            event.getTrades().get(4).add(new BasicItemListing(new ItemStack(Items.NETHERITE_SCRAP), new ItemStack(ModItems.WITHER_BONE.get()), new ItemStack(ModItems.LOWER_BOUND_ALLOY_BONE.get()), 5, 5, 0.05F));
            event.getTrades().get(5).add(new BasicItemListing(new ItemStack(ModItems.SUPER_RUBY.get()), new ItemStack(Items.IRON_SWORD), new ItemStack(ModItems.RED_GOLD_DAGGER.get()), 1, 5, 0.05F));
            event.getTrades().get(2).add(new BasicItemListing(new ItemStack(ModItems.BAO_ZHU.get()), new ItemStack(ModItems.WITHER_BONE.get()), new ItemStack(ModItems.MAGIC_STARD.get()), 5, 5, 0.05F));
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(ModItems.SOUL_PEOPLE.get()), new ItemStack(ModItems.RUBY.get(), 2), 5, 5, 0.05F));
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(ModItems.SOUL_PEOPLE.get()), new ItemStack(ModItems.SAPPHIRE.get(), 2), 5, 5, 0.05F));
            event.getTrades().get(3).add(new BasicItemListing(new ItemStack(ModItems.RUBY.get()), new ItemStack(ModItems.RUBY_SWORD.get()), 2, 25, 0.05F));
            event.getTrades().get(3).add(new BasicItemListing(new ItemStack(ModItems.SAPPHIRE.get()), new ItemStack(ModItems.SAPPHIRE_SWORD.get()), 2, 25, 0.05F));
            event.getTrades().get(5).add(new BasicItemListing(new ItemStack(ModItems.BLUE_DIAMOND.get()), new ItemStack(ModItems.MAGIC_STARD.get(), 5), new ItemStack(ModItems.BLACK_GUN.get()), 1, 5, 0.05F));
            event.getTrades().get(5).add(new BasicItemListing(new ItemStack(ModItems.BLUE_DIAMOND.get()), new ItemStack(ModItems.SUPER_RUBY.get()), new ItemStack(ModItems.NEAUS_SOULS.get()), 1, 5, 0.05F));
            event.getTrades().get(5).add(new BasicItemListing(new ItemStack(ModItems.BLUE_DIAMOND.get()), new ItemStack(ModItems.SUPER_SAPPHIRE.get()), new ItemStack(ModItems.NEAUS_SOULS.get()), 1, 5, 0.05F));
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(Items.REDSTONE, 3), new ItemStack(Items.STICK, 16), new ItemStack(Items.GOLDEN_APPLE), 10, 5, 0.05F));
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(Items.QUARTZ, 3), new ItemStack(Items.STICK, 16), new ItemStack(Items.ENCHANTED_GOLDEN_APPLE), 10, 5, 0.05F));
        }
        if (event.getType() == VillagerProfession.ARMORER) {
            event.getTrades().get(3).add(new BasicItemListing(new ItemStack(ModBlocks.SAPPHIRE_BLOCK.get()), new ItemStack(ModItems.DIAMOND_PIECE.get()), new ItemStack(ModItems.BLUE_DIAMOND.get()), 1, 5, 0.05F));
            event.getTrades().get(3).add(new BasicItemListing(new ItemStack(ModBlocks.RUBY_BLOCK.get()), new ItemStack(ModItems.DIAMOND_PIECE.get()), new ItemStack(ModItems.BLUE_DIAMOND.get()), 1, 5, 0.05F));
            event.getTrades().get(4).add(new BasicItemListing(new ItemStack(ModItems.SUPER_DIAMOND_APPLE.get()), new ItemStack(ModItems.ICE_HEART.get()), new ItemStack(ModItems.DIAMOND_APPLE_SUPPER.get(), 2), 2, 5, 0.05F));
            event.getTrades().get(4).add(new BasicItemListing(new ItemStack(ModItems.SUPER_DIAMOND_APPLE.get()), new ItemStack(ModItems.WARRIOR_HEART.get()), new ItemStack(ModItems.DIAMOND_APPLE_SUPPER.get(), 2), 2, 5, 0.05F));
            event.getTrades().get(4).add(new BasicItemListing(new ItemStack(ModItems.SUPER_DIAMOND_APPLE.get()), new ItemStack(ModItems.ENDER_HEART.get()), new ItemStack(ModItems.DIAMOND_APPLE_SUPPER.get(), 2), 2, 5, 0.05F));
            event.getTrades().get(4).add(new BasicItemListing(new ItemStack(ModItems.SUPER_DIAMOND_APPLE.get()), new ItemStack(ModItems.LIGHT_HEART.get()), new ItemStack(ModItems.DIAMOND_APPLE_SUPPER.get(), 2), 2, 5, 0.05F));
            event.getTrades().get(5).add(new BasicItemListing(new ItemStack(ModItems.RUBY_PICKAXE.get()), new ItemStack(ModItems.BLUE_DIAMOND.get()), new ItemStack(ModItems.DIAMOND_HATCHET.get()), 1, 5, 0.05F));
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(Items.REDSTONE, 5), new ItemStack(ModItems.SOUL_PEOPLE.get()), 10, 5, 0.05F));
            event.getTrades().get(2).add(new BasicItemListing(new ItemStack(Items.REDSTONE, 5), new ItemStack(ModItems.SOUL_PEOPLE.get(), 2), 5, 5, 0.05F));
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(Items.ROTTEN_FLESH, 3), new ItemStack(Items.GOLDEN_CARROT), 10, 5, 0.05F));
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(Items.STRING, 3), new ItemStack(Items.GOLDEN_CARROT), 10, 5, 0.05F));
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(Items.BONE, 3), new ItemStack(Items.GOLDEN_CARROT), 10, 5, 0.05F));
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(ModItems.MAGIC_STARD.get()), new ItemStack(Blocks.COPPER_BLOCK), new ItemStack(Blocks.DIAMOND_BLOCK), 1, 5, 0.05F));
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(ModItems.MAGIC_STARD.get()), new ItemStack(Blocks.IRON_BLOCK), new ItemStack(Blocks.DIAMOND_BLOCK), 1, 5, 0.05F));
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(ModItems.MAGIC_STARD.get()), new ItemStack(Blocks.GOLD_BLOCK), new ItemStack(Blocks.DIAMOND_BLOCK), 1, 5, 0.05F));
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(ModItems.MAGIC_STARD.get()), new ItemStack(Blocks.REDSTONE_BLOCK), new ItemStack(Blocks.DIAMOND_BLOCK), 1, 5, 0.05F));
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(ModItems.MAGIC_STARD.get()), new ItemStack(Blocks.EMERALD_BLOCK), new ItemStack(Blocks.DIAMOND_BLOCK), 1, 5, 0.05F));
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(ModItems.MAGIC_STARD.get()), new ItemStack(Blocks.LAPIS_BLOCK), new ItemStack(Blocks.DIAMOND_BLOCK), 1, 5, 0.05F));
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(ModItems.MAGIC_STARD.get()), new ItemStack(Blocks.NETHERITE_BLOCK), new ItemStack(Blocks.DIAMOND_BLOCK), 1, 5, 0.05F));
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(ModItems.MAGIC_STARD.get()), new ItemStack(ModBlocks.SAPPHIRE_BLOCK.get()), new ItemStack(Blocks.DIAMOND_BLOCK), 1, 5, 0.05F));
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(ModItems.MAGIC_STARD.get()), new ItemStack(ModBlocks.RUBY_BLOCK.get()), new ItemStack(Blocks.DIAMOND_BLOCK), 1, 5, 0.05F));
        }
        if (event.getType() == VillagerProfession.FLETCHER) {
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(Items.REDSTONE, 10), new ItemStack(ModItems.SOUL_PEOPLE.get()), 5, 5, 0.05F));
            event.getTrades().get(2).add(new BasicItemListing(new ItemStack(Items.REDSTONE, 8), new ItemStack(ModItems.SOUL_PEOPLE.get()), 5, 5, 0.05F));
            event.getTrades().get(3).add(new BasicItemListing(new ItemStack(Items.REDSTONE, 6), new ItemStack(ModItems.SOUL_PEOPLE.get()), 5, 5, 0.05F));
            event.getTrades().get(4).add(new BasicItemListing(new ItemStack(Items.REDSTONE, 4), new ItemStack(ModItems.SOUL_PEOPLE.get()), 5, 5, 0.05F));
            event.getTrades().get(5).add(new BasicItemListing(new ItemStack(Items.REDSTONE, 2), new ItemStack(ModItems.SOUL_PEOPLE.get()), 5, 5, 0.05F));
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(Items.COPPER_INGOT, 5), new ItemStack(ModItems.MYSTERIOUS_GIFT_BOX.get()), 5, 5, 0.05F));
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(Items.REDSTONE, 5), new ItemStack(ModItems.MYSTERIOUS_GIFT_BOX.get()), 5, 5, 0.05F));
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(Items.QUARTZ, 5), new ItemStack(ModItems.MYSTERIOUS_GIFT_BOX.get()), 5, 5, 0.05F));
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(Blocks.COPPER_BLOCK), new ItemStack(ModItems.MYSTERIOUS_GIFT_BOX.get(), 3), 3, 5, 0.05F));
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(Blocks.REDSTONE_BLOCK), new ItemStack(ModItems.MYSTERIOUS_GIFT_BOX.get(), 3), 3, 5, 0.05F));
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(Items.ROTTEN_FLESH, 5), new ItemStack(Items.EMERALD), 10, 5, 0.05F));
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(Items.BONE, 5), new ItemStack(Items.EMERALD), 10, 5, 0.05F));
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(Items.STRING, 5), new ItemStack(Items.EMERALD), 10, 5, 0.05F));
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(Items.GUNPOWDER, 5), new ItemStack(Items.EMERALD), 10, 5, 0.05F));
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(Items.SPIDER_EYE, 5), new ItemStack(Items.EMERALD), 10, 5, 0.05F));
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(ModItems.ZOMBIE_HEART.get(), 5), new ItemStack(Items.EMERALD), 10, 5, 0.05F));
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(Items.ARROW, 5), new ItemStack(Items.EMERALD), 10, 5, 0.05F));
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(ModItems.WITHER_BONE.get(), 5), new ItemStack(Items.EMERALD), 10, 5, 0.05F));
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(ModItems.RUBY.get()), new ItemStack(ModItems.HOT_IRON.get()), new ItemStack(ModItems.COTTON_CANDY.get(), 10), 10, 5, 0.05F));
            event.getTrades().get(1).add(new BasicItemListing(new ItemStack(ModItems.SAPPHIRE.get()), new ItemStack(ModItems.HOT_IRON.get()), new ItemStack(ModItems.COTTON_CANDY.get(), 10), 10, 5, 0.05F));
        }
    }
}
