package com.rainimator.rainimatormod.feature.ore;

import com.mojang.serialization.Codec;
import com.rainimator.rainimatormod.RainimatorMod;
import com.rainimator.rainimatormod.registry.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTestType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class RubyOreFeature extends OreFeature {
    public static final Set<ResourceLocation> GENERATE_BIOMES = null;
    public static RubyOreFeature FEATURE = null;
    public static Holder<ConfiguredFeature<OreConfiguration, ?>> CONFIGURED_FEATURE = null;
    public static Holder<PlacedFeature> PLACED_FEATURE = null;
    private final Set<ResourceKey<Level>> generate_dimensions = Set.of(Level.OVERWORLD);

    public RubyOreFeature() {
        super(OreConfiguration.CODEC);
    }

    public static Feature<?> feature() {
        FEATURE = new RubyOreFeature();
        CONFIGURED_FEATURE = FeatureUtils.register(RainimatorMod.MOD_ID + ":ruby_ore", FEATURE, new OreConfiguration(RubyOreFeatureRuleTest.INSTANCE, (ModBlocks.RUBY_ORE.get()).defaultBlockState(), 7));
        PLACED_FEATURE = PlacementUtils.register(RainimatorMod.MOD_ID + ":ruby_ore", CONFIGURED_FEATURE,
                List.of(CountPlacement.of(2), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.absolute(-1), VerticalAnchor.absolute(20)), BiomeFilter.biome()));
        return FEATURE;
    }

    public static Holder<PlacedFeature> placedFeature() {
        return PLACED_FEATURE;
    }

    @Override
    public boolean place(FeaturePlaceContext<OreConfiguration> context) {
        WorldGenLevel world = context.level();
        if (!this.generate_dimensions.contains(world.getLevel().dimension()))
            return false;
        return super.place(context);
    }

    @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    private static class RubyOreFeatureRuleTest extends RuleTest {
        static final RubyOreFeatureRuleTest INSTANCE = new RubyOreFeatureRuleTest();
        private static final Codec<RubyOreFeatureRuleTest> CODEC = Codec.unit(() -> INSTANCE);
        private static final RuleTestType<RubyOreFeatureRuleTest> CUSTOM_MATCH = () -> CODEC;
        private List<Block> base_blocks = null;

        @SubscribeEvent
        public static void init(FMLCommonSetupEvent event) {
            Registry.register(Registry.RULE_TEST, new ResourceLocation(RainimatorMod.MOD_ID, "ruby_ore_match"), CUSTOM_MATCH);
        }

        @Override
        public boolean test(@NotNull BlockState blockAt, @NotNull Random random) {
            if (this.base_blocks == null)
                this.base_blocks = List.of(Blocks.STONE, Blocks.GRANITE, Blocks.DIORITE, Blocks.ANDESITE, Blocks.DEEPSLATE);
            return this.base_blocks.contains(blockAt.getBlock());
        }

        @Override
        protected @NotNull RuleTestType<?> getType() {
            return CUSTOM_MATCH;
        }
    }
}