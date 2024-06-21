package dev.rainimator.mod.util;

import net.minecraft.util.Identifier;

import java.util.Set;

public class SpawnBiome {
    //wtf is this?
    public static final Set<Identifier> COMMON_SPAWN_BIOMES = Set.of(
            Identifier.tryParse("warm_ocean"),
            Identifier.tryParse("mushroom_fields"),
            Identifier.tryParse("sunflower_plains"),
            Identifier.tryParse("flower_forest"),
            Identifier.tryParse("lush_caves"),
            Identifier.tryParse("cold_ocean"),
            Identifier.tryParse("ice_spikes"),
            Identifier.tryParse("lukewarm_ocean"),
            Identifier.tryParse("dark_forest"),
            Identifier.tryParse("savanna"),
            Identifier.tryParse("stony_peaks"),
            Identifier.tryParse("snowy_beach"),
            Identifier.tryParse("frozen_ocean"),
            Identifier.tryParse("savanna_plateau"),
            Identifier.tryParse("dripstone_caves"),
            Identifier.tryParse("snowy_plains"),
            Identifier.tryParse("jagged_peaks"),
            Identifier.tryParse("eroded_badlands"),
            Identifier.tryParse("badlands"),
            Identifier.tryParse("windswept_hills"),
            Identifier.tryParse("ocean"),
            Identifier.tryParse("wooded_badlands"),
            Identifier.tryParse("windswept_savanna"),
            Identifier.tryParse("jungle"),
            Identifier.tryParse("warped_forest"),
            Identifier.tryParse("frozen_river"),
            Identifier.tryParse("forest"),
            Identifier.tryParse("stony_shore"),
            Identifier.tryParse("sparse_jungle"),
            Identifier.tryParse("birch_forest"),
            Identifier.tryParse("deep_lukewarm_ocean"),
            Identifier.tryParse("snowy_slopes"),
            Identifier.tryParse("deep_ocean"),
            Identifier.tryParse("deep_frozen_ocean"),
            Identifier.tryParse("bamboo_jungle"),
            Identifier.tryParse("plains"),
            Identifier.tryParse("frozen_peaks"),
            Identifier.tryParse("meadow"),
            Identifier.tryParse("old_growth_spruce_taiga"),
            Identifier.tryParse("basalt_deltas"),
            Identifier.tryParse("taiga"),
            Identifier.tryParse("crimson_forest"),
            Identifier.tryParse("snowy_taiga"),
            Identifier.tryParse("swamp"),
            Identifier.tryParse("deep_cold_ocean"),
            Identifier.tryParse("old_growth_birch_forest"),
            Identifier.tryParse("grove"),
            Identifier.tryParse("old_growth_pine_taiga"),
            Identifier.tryParse("beach"),
            Identifier.tryParse("the_void"),
            Identifier.tryParse("windswept_forest"),
            Identifier.tryParse("windswept_gravelly_hills"),
            Identifier.tryParse("river"),
            Identifier.tryParse("desert"));

    public static final Set<Identifier> SNOW_SPAWN_BIOMES = Set.of(
            Identifier.tryParse("windswept_hills"),
            Identifier.tryParse("snowy_plains"),
            Identifier.tryParse("snowy_slopes"),
            Identifier.tryParse("snowy_taiga"),
            Identifier.tryParse("snowy_beach"));
    public static final Set<Identifier> END_SPAWN_BIOMES = Set.of(
            Identifier.tryParse("small_end_islands"),
            Identifier.tryParse("end_midlands"),
            Identifier.tryParse("the_end"),
            Identifier.tryParse("end_highlands"),
            Identifier.tryParse("end_barrens"));
}
