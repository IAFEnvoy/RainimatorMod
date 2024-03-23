package com.rainimator.rainimatormod.feature;

import com.rainimator.rainimatormod.RainimatorMod;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

import java.util.List;
import java.util.Set;

public class PiglinTeamFeature extends Feature<NoneFeatureConfiguration> {
    public static final Set<ResourceLocation> GENERATE_BIOMES = null;
    public static PiglinTeamFeature FEATURE = null;
    public static Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> CONFIGURED_FEATURE = null;
    public static Holder<PlacedFeature> PLACED_FEATURE = null;
    private final Set<ResourceKey<Level>> generate_dimensions = Set.of(Level.OVERWORLD);
    private final List<Block> base_blocks;
    private StructureTemplate template = null;
    public PiglinTeamFeature() {
        super(NoneFeatureConfiguration.CODEC);
        this.base_blocks = List.of(Blocks.GRASS_BLOCK);
    }

    public static Feature<?> feature() {
        FEATURE = new PiglinTeamFeature();
        CONFIGURED_FEATURE = FeatureUtils.register(RainimatorMod.MOD_ID + ":piglin_team", FEATURE, FeatureConfiguration.NONE);
        PLACED_FEATURE = PlacementUtils.register(RainimatorMod.MOD_ID + ":piglin_team", CONFIGURED_FEATURE, List.of());
        return FEATURE;
    }

    public static Holder<PlacedFeature> placedFeature() {
        return PLACED_FEATURE;
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        if (!this.generate_dimensions.contains(context.level().getLevel().dimension()))
            return false;
        if (this.template == null)
            this.template = context.level().getLevel().getStructureManager().getOrCreate(new ResourceLocation(RainimatorMod.MOD_ID, "piglin_team"));
        boolean anyPlaced = false;
        if (context.random().nextInt(1000000) + 1 <= 500) {
            int count = context.random().nextInt(1) + 1;
            for (int a = 0; a < count; a++) {
                int i = context.origin().getX() + context.random().nextInt(16);
                int k = context.origin().getZ() + context.random().nextInt(16);
                int j = context.level().getHeight(Heightmap.Types.OCEAN_FLOOR_WG, i, k) - 1;
                if (this.base_blocks.contains(context.level().getBlockState(new BlockPos(i, j, k)).getBlock())) {

                    BlockPos spawnTo = new BlockPos(i, j, k);
                    if (this.template.placeInWorld(context.level(), spawnTo, spawnTo, (new StructurePlaceSettings()).setMirror(Mirror.NONE).setRotation(Rotation.NONE).setRandom(context.random()).addProcessor(BlockIgnoreProcessor.STRUCTURE_BLOCK).setIgnoreEntities(false), context.random(), 2))
                        anyPlaced = true;
                }
            }
        }
        return anyPlaced;
    }
}

