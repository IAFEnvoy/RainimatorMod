package com.iafenvoy.rainimator.recipe;

import com.google.common.collect.Iterables;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.iafenvoy.neptune.util.RandomHelper;
import com.iafenvoy.rainimator.RainimatorMod;
import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.SynchronousResourceReloader;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public enum BossSpawnRecipeManager implements SynchronousResourceReloader {
    INSTANCE;

    private static final Map<Identifier, Single> DATA = new LinkedHashMap<>();
    private static final Map<Item, List<EntityHolder>> DATA_MAP = new HashMap<>();

    @Override
    public void reload(ResourceManager manager) {
        DATA.clear();
        DATA_MAP.clear();
        for (Map.Entry<Identifier, Resource> entry : manager.findResources("boss_spawn", p -> p.getPath().endsWith(".json")).entrySet()) {
            Resource resource = entry.getValue();
            try {
                JsonElement element = JsonParser.parseReader(new InputStreamReader(resource.getInputStream()));
                Single single = Single.CODEC.parse(JsonOps.INSTANCE, element).resultOrPartial(RainimatorMod.LOGGER::error).orElseThrow();
                DATA.put(entry.getKey(), single);
                for (Item item : single.getItems())
                    DATA_MAP.put(item, single.entities);
            } catch (Exception e) {
                RainimatorMod.LOGGER.error("Failed to load {} from datapack {}.", entry.getKey(), resource.getResourcePackName(), e);
            }
        }
        RainimatorMod.LOGGER.info("Loaded {} boss spawn recipe(s).", DATA.size());
    }

    public static boolean canSummon(Item item) {
        return !DATA_MAP.getOrDefault(item, List.of()).isEmpty();
    }

    public static void summon(Item item, ServerWorld world, double x, double y, double z) {
        DATA_MAP.getOrDefault(item, List.of()).forEach(h -> h.summon(world, x, y, z));
    }

    public static Iterable<Map.Entry<Identifier, Single>> getAll() {
        return Iterables.unmodifiableIterable(DATA.entrySet());
    }

    public record Single(Either<Item, List<Item>> item, List<EntityHolder> entities) {
        private static final Codec<Either<Item, List<Item>>> EITHER_CODEC = Codec.either(Registries.ITEM.getCodec(), Registries.ITEM.getCodec().listOf());
        public static final Codec<Single> CODEC = RecordCodecBuilder.create(i -> i.group(
                EITHER_CODEC.fieldOf("item").forGetter(Single::item),
                EntityHolder.CODEC.listOf().fieldOf("entities").forGetter(Single::entities)
        ).apply(i, Single::new));

        public List<Item> getItems() {
            return this.item.map(List::of, x -> x);
        }

        public Item getFirstSpawnEgg() {
            if (this.entities.isEmpty()) return Items.AIR;
            return SpawnEggItem.forEntity(this.entities.get(0).type);
        }
    }

    public record EntityHolder(EntityType<?> type,
                               Either<Integer, Range> x, Either<Integer, Range> y, Either<Integer, Range> z) {
        private static final Codec<Either<Integer, Range>> EITHER_CODEC = Codec.either(Codec.INT, Range.CODEC);
        public static final Codec<EntityHolder> CODEC = RecordCodecBuilder.create(i -> i.group(
                Registries.ENTITY_TYPE.getCodec().fieldOf("type").forGetter(EntityHolder::type),
                EITHER_CODEC.optionalFieldOf("x", Either.left(0)).forGetter(EntityHolder::x),
                EITHER_CODEC.optionalFieldOf("y", Either.left(0)).forGetter(EntityHolder::y),
                EITHER_CODEC.optionalFieldOf("z", Either.left(0)).forGetter(EntityHolder::z)
        ).apply(i, EntityHolder::new));

        public static int parseEither(Either<Integer, Range> e) {
            return e.map(x -> x, x -> RandomHelper.nextInt(x.min, x.max));
        }

        public int getX() {
            return parseEither(this.x);
        }

        public int getY() {
            return parseEither(this.y);
        }

        public int getZ() {
            return parseEither(this.z);
        }

        public void summon(ServerWorld world, double x, double y, double z) {
            Entity entityToSpawn = this.type.create(world);
            if (entityToSpawn instanceof MobEntity mob) {
                mob.refreshPositionAndAngles(x + this.getX(), y + this.getY(), z + this.getZ(), world.getRandom().nextFloat() * 360.0F, 0.0F);
                mob.initialize(world, world.getLocalDifficulty(mob.getBlockPos()), SpawnReason.MOB_SUMMONED, null, null);
                world.spawnEntity(mob);
            }
        }
    }

    public record Range(int min, int max) {
        public static final Codec<Range> CODEC = RecordCodecBuilder.create(i -> i.group(
                Codec.INT.fieldOf("min").forGetter(Range::min),
                Codec.INT.fieldOf("max").forGetter(Range::max)
        ).apply(i, Range::new));
    }
}
