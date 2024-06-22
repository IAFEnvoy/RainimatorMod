package dev.rainimator.mod.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.rainimator.mod.RainimatorMod;
import dev.rainimator.mod.util.MemorizeSupplier;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class RainimatorSounds {
    public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(RainimatorMod.MOD_ID, RegistryKeys.SOUND_EVENT);

    public static final String[] idMap = new String[]{
            "blackbone", "ceris_live", "ceris_skill", "ceris_f", "him_skill", "naeus_living", "fire_soul", "rain_sword_skill", "rain_sword_skill_2",
            "blackbone_living", "blackbone_skill", "naeus_roll", "ceris_death", "naeus_sword_1", "power_skill", "under_flower",
            "sword_teleport1", "sword_teleport2", "sword_teleport3", "sword_teleport4",
            "black_death_sword_skill", "black_death_sword_skills", "black_death_sword_skill_3",
            "gift_box", "him", "stunned",
            "blued_diamond_skill_1", "blue_diamond_skill_2", "blue_diamond_skill_3", "blue_diamond_skill_4",
            "ceris_boss_music", "null_boss_music", "blackbone_boss_music", "piglin_king_boss_music", "naeus_boss_music",
            "glutton_boss_music", "him_music_boss", "him_one_lives", "kralos_boss_music", "klaus_boss_music"
    };
    public static final Map<String, Supplier<SoundEvent>> REGISTRY_MAP = new HashMap<>();

    static {
        for (String id : idMap) {
            Identifier location = Identifier.of(RainimatorMod.MOD_ID, id);
            MemorizeSupplier<SoundEvent> soundEvent = new MemorizeSupplier<>(() -> SoundEvent.of(location));
            REGISTRY.register(id, soundEvent);
            REGISTRY_MAP.put(id, soundEvent);
        }
    }
}