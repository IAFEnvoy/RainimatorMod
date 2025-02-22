package com.iafenvoy.rainimator.registry;

import com.iafenvoy.rainimator.RainimatorMod;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

public class RainimatorTags {
    public static final TagKey<Biome> COMMON_SPAWN_BIOMES = biome("common_spawn_biomes");
    public static final TagKey<Biome> SNOW_SPAWN_BIOMES = biome("snow_spawn_biomes");
    public static final TagKey<Biome> NETHER_SPAWN_BIOMES = biome("nether_spawn_biomes");
    public static final TagKey<Biome> END_SPAWN_BIOMES = biome("end_spawn_biomes");

    private static TagKey<Biome> biome(String id) {
        return TagKey.of(RegistryKeys.BIOME, Identifier.of(RainimatorMod.MOD_ID, id));
    }
}
