package com.rainimator.rainimatormod.registry;

import com.rainimator.rainimatormod.RainimatorMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import java.util.HashMap;
import java.util.Map;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class ModSounds {
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
    public static final Map<String, SoundEvent> REGISTRY = new HashMap<>();

    @SubscribeEvent
    public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
        for (String id : idMap) {
            ResourceLocation location = new ResourceLocation(RainimatorMod.MOD_ID, id);
            SoundEvent soundEvent = new SoundEvent(location);
            event.getRegistry().register(soundEvent.setRegistryName(location));
            REGISTRY.put(id, soundEvent);
        }
    }
}