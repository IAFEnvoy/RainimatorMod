package com.iafenvoy.rainimator.registry;

import dev.architectury.registry.level.entity.trade.TradeRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.random.Random;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;
import org.jetbrains.annotations.Nullable;

public class RainimatorTrades {
    public static void registerTrades() {
        //TOOL SMITH
        TradeRegistry.registerVillagerTrade(VillagerProfession.TOOLSMITH, 1,
                new BuyWithPrice(new ItemStack(RainimatorItems.DIAMOND_HATCHET.get()), new ItemStack(RainimatorItems.UNDER_FLOWER.get()), new ItemStack(RainimatorItems.INTELLIGENCE_TOMAHAWK.get()), 1, 5, 0.05F),
                new BuyWithPrice(new ItemStack(RainimatorItems.SOUL_PEOPLE.get()), new ItemStack(RainimatorItems.RUBY.get(), 2), 5, 5, 0.05F),
                new BuyWithPrice(new ItemStack(RainimatorItems.SOUL_PEOPLE.get()), new ItemStack(RainimatorItems.SAPPHIRE.get(), 2), 5, 5, 0.05F),
                new BuyWithPrice(new ItemStack(Items.IRON_INGOT, 2), new ItemStack(Items.COPPER_INGOT, 3), new ItemStack(Items.DIAMOND), 10, 5, 0.05F),
                new BuyWithPrice(new ItemStack(Blocks.AMETHYST_BLOCK, 2), new ItemStack(Items.COPPER_INGOT, 3), new ItemStack(Items.DIAMOND), 10, 5, 0.05F)
        );
        TradeRegistry.registerVillagerTrade(VillagerProfession.TOOLSMITH, 2,
                new BuyWithPrice(new ItemStack(RainimatorItems.RAW_RUBY.get()), new ItemStack(RainimatorItems.SAPPHIRE.get()), 25, 5, 0.05F),
                new BuyWithPrice(new ItemStack(RainimatorItems.RAW_SAPPHIRE.get()), new ItemStack(RainimatorItems.RUBY.get()), 25, 5, 0.05F),
                new BuyWithPrice(new ItemStack(RainimatorItems.RUBY.get(), 2), new ItemStack(RainimatorItems.RUBY_PICKAXE.get()), 1, 25, 0.05F),
                new BuyWithPrice(new ItemStack(RainimatorItems.RUBY.get(), 2), new ItemStack(RainimatorItems.RUBY_AXE.get()), 1, 25, 0.05F),
                new BuyWithPrice(new ItemStack(RainimatorItems.SAPPHIRE.get(), 2), new ItemStack(RainimatorItems.SAPPHIRE_PICKAXE.get()), 1, 25, 0.05F),
                new BuyWithPrice(new ItemStack(RainimatorItems.SAPPHIRE.get(), 2), new ItemStack(RainimatorItems.SAPPHIRE_AXE.get()), 1, 25, 0.05F)
        );
        TradeRegistry.registerVillagerTrade(VillagerProfession.TOOLSMITH, 4,
                new BuyWithPrice(new ItemStack(RainimatorItems.SUPER_RUBY.get()), new ItemStack(RainimatorItems.DIAMOND_APPLE_SUPPER.get(), 3), 5, 5, 0.05F),
                new BuyWithPrice(new ItemStack(RainimatorItems.SUPER_SAPPHIRE.get()), new ItemStack(RainimatorItems.DIAMOND_APPLE_SUPPER.get(), 3), 5, 5, 0.05F)
        );
        TradeRegistry.registerVillagerTrade(VillagerProfession.TOOLSMITH, 4,
                new BuyWithPrice(new ItemStack(RainimatorItems.BLUE_DIAMOND.get()), new ItemStack(RainimatorItems.SUPER_RUBY.get()), new ItemStack(RainimatorItems.DIAMOND_APPLE_SUPPER.get(), 3), 1, 5, 0.05F),
                new BuyWithPrice(new ItemStack(RainimatorItems.BLUE_DIAMOND.get()), new ItemStack(RainimatorItems.SUPER_SAPPHIRE.get()), new ItemStack(RainimatorItems.DIAMOND_APPLE_SUPPER.get(), 3), 1, 5, 0.05F)
        );
        //WEAPON SMITH
        TradeRegistry.registerVillagerTrade(VillagerProfession.WEAPONSMITH, 1,
                new BuyWithPrice(new ItemStack(RainimatorItems.SOUL_PEOPLE.get()), new ItemStack(RainimatorItems.RUBY.get(), 2), 5, 5, 0.05F),
                new BuyWithPrice(new ItemStack(RainimatorItems.SOUL_PEOPLE.get()), new ItemStack(RainimatorItems.SAPPHIRE.get(), 2), 5, 5, 0.05F),
                new BuyWithPrice(new ItemStack(Items.REDSTONE, 3), new ItemStack(Items.STICK, 16), new ItemStack(Items.GOLDEN_APPLE), 10, 5, 0.05F),
                new BuyWithPrice(new ItemStack(Items.QUARTZ, 3), new ItemStack(Items.STICK, 16), new ItemStack(Items.ENCHANTED_GOLDEN_APPLE), 10, 5, 0.05F)
        );
        TradeRegistry.registerVillagerTrade(VillagerProfession.WEAPONSMITH, 2,
                new BuyWithPrice(new ItemStack(RainimatorItems.ZOMBIE_HEART.get(), 10), new ItemStack(RainimatorItems.WITHER_BONE.get(), 2), new ItemStack(RainimatorItems.SUPER_RUBY.get()), 5, 5, 0.05F),
                new BuyWithPrice(new ItemStack(RainimatorItems.ZOMBIE_HEART.get(), 10), new ItemStack(RainimatorItems.WITHER_BONE.get(), 2), new ItemStack(RainimatorItems.SUPER_SAPPHIRE.get()), 5, 5, 0.05F),
                new BuyWithPrice(new ItemStack(RainimatorItems.BAO_ZHU.get()), new ItemStack(RainimatorItems.WITHER_BONE.get()), new ItemStack(RainimatorItems.MAGIC_STAR.get()), 5, 5, 0.05F)
        );
        TradeRegistry.registerVillagerTrade(VillagerProfession.WEAPONSMITH, 3,
                new BuyWithPrice(new ItemStack(RainimatorItems.ZOMBIE_HEART.get(), 20), new ItemStack(RainimatorItems.WITHER_BONE.get(), 5), new ItemStack(RainimatorItems.BLUE_DIAMOND.get()), 3, 5, 0.05F),
                new BuyWithPrice(new ItemStack(RainimatorItems.RUBY.get()), new ItemStack(RainimatorItems.RUBY_SWORD.get()), 2, 25, 0.05F),
                new BuyWithPrice(new ItemStack(RainimatorItems.SAPPHIRE.get()), new ItemStack(RainimatorItems.SAPPHIRE_SWORD.get()), 2, 25, 0.05F)
        );
        TradeRegistry.registerVillagerTrade(VillagerProfession.WEAPONSMITH, 4,
                new BuyWithPrice(new ItemStack(Items.NETHERITE_SCRAP), new ItemStack(RainimatorItems.WITHER_BONE.get()), new ItemStack(RainimatorItems.NETHERITE_WITHER_BONE.get()), 5, 5, 0.05F)
        );
        TradeRegistry.registerVillagerTrade(VillagerProfession.WEAPONSMITH, 5,
                new BuyWithPrice(new ItemStack(RainimatorItems.RUBY.get(), 2), new ItemStack(RainimatorItems.SAPPHIRE.get(), 2), new ItemStack(RainimatorItems.BLUE_DIAMOND.get()), 3, 5, 0.05F),
                new BuyWithPrice(new ItemStack(RainimatorItems.HOT_IRON.get(), 2), new ItemStack(Items.IRON_SWORD), new ItemStack(RainimatorItems.NETHERITE_FLAME_SABER.get()), 1, 5, 0.05F),
                new BuyWithPrice(new ItemStack(RainimatorItems.SUPER_RUBY.get()), new ItemStack(Items.IRON_SWORD), new ItemStack(RainimatorItems.RED_GOLD_DAGGER.get()), 1, 5, 0.05F),
                new BuyWithPrice(new ItemStack(RainimatorItems.BLUE_DIAMOND.get()), new ItemStack(RainimatorItems.MAGIC_STAR.get(), 5), new ItemStack(RainimatorItems.BLACK_GUN.get()), 1, 5, 0.05F),
                new BuyWithPrice(new ItemStack(RainimatorItems.BLUE_DIAMOND.get()), new ItemStack(RainimatorItems.SUPER_RUBY.get()), new ItemStack(RainimatorItems.NEAUS_SOULS.get()), 1, 5, 0.05F),
                new BuyWithPrice(new ItemStack(RainimatorItems.BLUE_DIAMOND.get()), new ItemStack(RainimatorItems.SUPER_SAPPHIRE.get()), new ItemStack(RainimatorItems.NEAUS_SOULS.get()), 1, 5, 0.05F)
        );
        //ARMORER
        TradeRegistry.registerVillagerTrade(VillagerProfession.ARMORER, 1,
                new BuyWithPrice(new ItemStack(Items.REDSTONE, 5), new ItemStack(RainimatorItems.SOUL_PEOPLE.get()), 10, 5, 0.05F),
                new BuyWithPrice(new ItemStack(Items.ROTTEN_FLESH, 3), new ItemStack(Items.GOLDEN_CARROT), 10, 5, 0.05F),
                new BuyWithPrice(new ItemStack(Items.STRING, 3), new ItemStack(Items.GOLDEN_CARROT), 10, 5, 0.05F),
                new BuyWithPrice(new ItemStack(Items.BONE, 3), new ItemStack(Items.GOLDEN_CARROT), 10, 5, 0.05F),
                new BuyWithPrice(new ItemStack(RainimatorItems.MAGIC_STAR.get()), new ItemStack(Blocks.COPPER_BLOCK), new ItemStack(Blocks.DIAMOND_BLOCK), 1, 5, 0.05F),
                new BuyWithPrice(new ItemStack(RainimatorItems.MAGIC_STAR.get()), new ItemStack(Blocks.IRON_BLOCK), new ItemStack(Blocks.DIAMOND_BLOCK), 1, 5, 0.05F),
                new BuyWithPrice(new ItemStack(RainimatorItems.MAGIC_STAR.get()), new ItemStack(Blocks.GOLD_BLOCK), new ItemStack(Blocks.DIAMOND_BLOCK), 1, 5, 0.05F),
                new BuyWithPrice(new ItemStack(RainimatorItems.MAGIC_STAR.get()), new ItemStack(Blocks.REDSTONE_BLOCK), new ItemStack(Blocks.DIAMOND_BLOCK), 1, 5, 0.05F),
                new BuyWithPrice(new ItemStack(RainimatorItems.MAGIC_STAR.get()), new ItemStack(Blocks.EMERALD_BLOCK), new ItemStack(Blocks.DIAMOND_BLOCK), 1, 5, 0.05F),
                new BuyWithPrice(new ItemStack(RainimatorItems.MAGIC_STAR.get()), new ItemStack(Blocks.LAPIS_BLOCK), new ItemStack(Blocks.DIAMOND_BLOCK), 1, 5, 0.05F),
                new BuyWithPrice(new ItemStack(RainimatorItems.MAGIC_STAR.get()), new ItemStack(Blocks.NETHERITE_BLOCK), new ItemStack(Blocks.DIAMOND_BLOCK), 1, 5, 0.05F),
                new BuyWithPrice(new ItemStack(RainimatorItems.MAGIC_STAR.get()), new ItemStack(RainimatorBlocks.SAPPHIRE_BLOCK.get()), new ItemStack(Blocks.DIAMOND_BLOCK), 1, 5, 0.05F),
                new BuyWithPrice(new ItemStack(RainimatorItems.MAGIC_STAR.get()), new ItemStack(RainimatorBlocks.RUBY_BLOCK.get()), new ItemStack(Blocks.DIAMOND_BLOCK), 1, 5, 0.05F)
        );
        TradeRegistry.registerVillagerTrade(VillagerProfession.ARMORER, 2,
                new BuyWithPrice(new ItemStack(Items.REDSTONE, 5), new ItemStack(RainimatorItems.SOUL_PEOPLE.get(), 2), 5, 5, 0.05F)
        );
        TradeRegistry.registerVillagerTrade(VillagerProfession.ARMORER, 3,
                new BuyWithPrice(new ItemStack(RainimatorBlocks.SAPPHIRE_BLOCK.get()), new ItemStack(RainimatorItems.DIAMOND_PIECE.get()), new ItemStack(RainimatorItems.BLUE_DIAMOND.get()), 1, 5, 0.05F),
                new BuyWithPrice(new ItemStack(RainimatorBlocks.RUBY_BLOCK.get()), new ItemStack(RainimatorItems.DIAMOND_PIECE.get()), new ItemStack(RainimatorItems.BLUE_DIAMOND.get()), 1, 5, 0.05F)
        );
        TradeRegistry.registerVillagerTrade(VillagerProfession.ARMORER, 4,
                new BuyWithPrice(new ItemStack(RainimatorItems.SUPER_DIAMOND_APPLE.get()), new ItemStack(RainimatorItems.ICE_HEART.get()), new ItemStack(RainimatorItems.DIAMOND_APPLE_SUPPER.get(), 2), 2, 5, 0.05F),
                new BuyWithPrice(new ItemStack(RainimatorItems.SUPER_DIAMOND_APPLE.get()), new ItemStack(RainimatorItems.WARRIOR_HEART.get()), new ItemStack(RainimatorItems.DIAMOND_APPLE_SUPPER.get(), 2), 2, 5, 0.05F),
                new BuyWithPrice(new ItemStack(RainimatorItems.SUPER_DIAMOND_APPLE.get()), new ItemStack(RainimatorItems.ENDER_HEART.get()), new ItemStack(RainimatorItems.DIAMOND_APPLE_SUPPER.get(), 2), 2, 5, 0.05F),
                new BuyWithPrice(new ItemStack(RainimatorItems.SUPER_DIAMOND_APPLE.get()), new ItemStack(RainimatorItems.LIGHT_HEART.get()), new ItemStack(RainimatorItems.DIAMOND_APPLE_SUPPER.get(), 2), 2, 5, 0.05F)
        );
        TradeRegistry.registerVillagerTrade(VillagerProfession.ARMORER, 5,
                new BuyWithPrice(new ItemStack(RainimatorItems.GEMSTONE_MIXTURE.get()), new ItemStack(RainimatorItems.BLUE_DIAMOND.get()), new ItemStack(RainimatorItems.DIAMOND_HATCHET.get()), 1, 5, 0.05F)
        );
        //FLETCHER
        TradeRegistry.registerVillagerTrade(VillagerProfession.FLETCHER, 1,
                new BuyWithPrice(new ItemStack(Items.REDSTONE, 10), new ItemStack(RainimatorItems.SOUL_PEOPLE.get()), 5, 5, 0.05F),
                new BuyWithPrice(new ItemStack(Items.COPPER_INGOT, 5), new ItemStack(RainimatorItems.MYSTERIOUS_GIFT_BOX.get()), 5, 5, 0.05F),
                new BuyWithPrice(new ItemStack(Items.REDSTONE, 5), new ItemStack(RainimatorItems.MYSTERIOUS_GIFT_BOX.get()), 5, 5, 0.05F),
                new BuyWithPrice(new ItemStack(Items.QUARTZ, 5), new ItemStack(RainimatorItems.MYSTERIOUS_GIFT_BOX.get()), 5, 5, 0.05F),
                new BuyWithPrice(new ItemStack(Blocks.COPPER_BLOCK), new ItemStack(RainimatorItems.MYSTERIOUS_GIFT_BOX.get(), 3), 3, 5, 0.05F),
                new BuyWithPrice(new ItemStack(Blocks.REDSTONE_BLOCK), new ItemStack(RainimatorItems.MYSTERIOUS_GIFT_BOX.get(), 3), 3, 5, 0.05F),
                new BuyWithPrice(new ItemStack(Items.ROTTEN_FLESH, 5), new ItemStack(Items.EMERALD), 10, 5, 0.05F),
                new BuyWithPrice(new ItemStack(Items.BONE, 5), new ItemStack(Items.EMERALD), 10, 5, 0.05F),
                new BuyWithPrice(new ItemStack(Items.STRING, 5), new ItemStack(Items.EMERALD), 10, 5, 0.05F),
                new BuyWithPrice(new ItemStack(Items.GUNPOWDER, 5), new ItemStack(Items.EMERALD), 10, 5, 0.05F),
                new BuyWithPrice(new ItemStack(Items.SPIDER_EYE, 5), new ItemStack(Items.EMERALD), 10, 5, 0.05F),
                new BuyWithPrice(new ItemStack(RainimatorItems.ZOMBIE_HEART.get(), 5), new ItemStack(Items.EMERALD), 10, 5, 0.05F),
                new BuyWithPrice(new ItemStack(Items.ARROW, 5), new ItemStack(Items.EMERALD), 10, 5, 0.05F),
                new BuyWithPrice(new ItemStack(RainimatorItems.WITHER_BONE.get(), 5), new ItemStack(Items.EMERALD), 10, 5, 0.05F),
                new BuyWithPrice(new ItemStack(RainimatorItems.RUBY.get()), new ItemStack(RainimatorItems.HOT_IRON.get()), new ItemStack(RainimatorItems.COTTON_CANDY.get(), 10), 10, 5, 0.05F),
                new BuyWithPrice(new ItemStack(RainimatorItems.SAPPHIRE.get()), new ItemStack(RainimatorItems.HOT_IRON.get()), new ItemStack(RainimatorItems.COTTON_CANDY.get(), 10), 10, 5, 0.05F)
        );
        TradeRegistry.registerVillagerTrade(VillagerProfession.FLETCHER, 2,
                new BuyWithPrice(new ItemStack(Items.REDSTONE, 8), new ItemStack(RainimatorItems.SOUL_PEOPLE.get()), 5, 5, 0.05F)
        );
        TradeRegistry.registerVillagerTrade(VillagerProfession.FLETCHER, 3,
                new BuyWithPrice(new ItemStack(Items.REDSTONE, 6), new ItemStack(RainimatorItems.SOUL_PEOPLE.get()), 5, 5, 0.05F)
        );
        TradeRegistry.registerVillagerTrade(VillagerProfession.FLETCHER, 4,
                new BuyWithPrice(new ItemStack(Items.REDSTONE, 4), new ItemStack(RainimatorItems.SOUL_PEOPLE.get()), 5, 5, 0.05F)
        );
        TradeRegistry.registerVillagerTrade(VillagerProfession.FLETCHER, 5,
                new BuyWithPrice(new ItemStack(Items.REDSTONE, 2), new ItemStack(RainimatorItems.SOUL_PEOPLE.get()), 5, 5, 0.05F)
        );
    }

    private static class BuyWithPrice implements TradeOffers.Factory {
        private final ItemStack input1;
        private final ItemStack input2;
        private final ItemStack output;
        private final int maxUses;
        private final int experience;
        private final float multiplier;

        public BuyWithPrice(ItemStack input, ItemStack output, int maxUses, int experience, float priceMultiplier) {
            this(input, null, output, maxUses, experience, priceMultiplier);
        }

        public BuyWithPrice(ItemStack input1, ItemStack input2, ItemStack output, int maxUses, int experience, float priceMultiplier) {
            this.input1 = input1;
            this.input2 = input2;
            this.output = output;
            this.maxUses = maxUses;
            this.experience = experience;
            this.multiplier = priceMultiplier;
        }

        @Nullable
        @Override
        public TradeOffer create(Entity entity, Random random) {
            if (this.input2 == null)
                return new TradeOffer(this.input1, this.output, this.maxUses, this.experience, this.multiplier);
            return new TradeOffer(this.input1, this.input2, this.output, this.maxUses, this.experience, this.multiplier);
        }
    }
}
