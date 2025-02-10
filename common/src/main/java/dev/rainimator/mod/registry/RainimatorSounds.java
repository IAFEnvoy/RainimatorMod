package dev.rainimator.mod.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.rainimator.mod.RainimatorMod;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class RainimatorSounds {
    public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(RainimatorMod.MOD_ID, RegistryKeys.SOUND_EVENT);

    public static final RegistrySupplier<SoundEvent> CERIS_LIVE = register("ceris_live");
    public static final RegistrySupplier<SoundEvent> CERIS_SKILL = register("ceris_skill");
    public static final RegistrySupplier<SoundEvent> CERIS_F = register("ceris_f");
    public static final RegistrySupplier<SoundEvent> HIM_SKILL = register("him_skill");
    public static final RegistrySupplier<SoundEvent> NAEUS_LIVING = register("naeus_living");
    public static final RegistrySupplier<SoundEvent> FIRE_SOUL = register("fire_soul");
    public static final RegistrySupplier<SoundEvent> RAIN_SWORD_SKILL = register("rain_sword_skill");
    public static final RegistrySupplier<SoundEvent> RAIN_SWORD_SKILL_2 = register("rain_sword_skill_2");
    public static final RegistrySupplier<SoundEvent> BLACKBONE_LIVING = register("blackbone_living");
    public static final RegistrySupplier<SoundEvent> BLACKBONE_SKILL = register("blackbone_skill");
    public static final RegistrySupplier<SoundEvent> NAEUS_ROLL = register("naeus_roll");
    public static final RegistrySupplier<SoundEvent> CERIS_DEATH = register("ceris_death");
    public static final RegistrySupplier<SoundEvent> NAEUS_SWORD_1 = register("naeus_sword_1");
    public static final RegistrySupplier<SoundEvent> UNDER_FLOWER = register("under_flower");
    public static final RegistrySupplier<SoundEvent> SWORD_TELEPORT = register("sword_teleport");
    public static final RegistrySupplier<SoundEvent> BLACK_DEATH_SWORD_SKILL = register("black_death_sword_skill");
    public static final RegistrySupplier<SoundEvent> BLACK_DEATH_SWORD_SKILLS = register("black_death_sword_skills");
    public static final RegistrySupplier<SoundEvent> BLACK_DEATH_SWORD_SKILL_3 = register("black_death_sword_skill_3");
    public static final RegistrySupplier<SoundEvent> GIFT_BOX = register("gift_box");
    public static final RegistrySupplier<SoundEvent> STUNNED = register("stunned");
    public static final RegistrySupplier<SoundEvent> BLUED_DIAMOND_SKILL_1 = register("blued_diamond_skill_1");
    public static final RegistrySupplier<SoundEvent> BLUE_DIAMOND_SKILL_2 = register("blue_diamond_skill_2");
    public static final RegistrySupplier<SoundEvent> BLUE_DIAMOND_SKILL_3 = register("blue_diamond_skill_3");
    public static final RegistrySupplier<SoundEvent> BLUE_DIAMOND_SKILL_4 = register("blue_diamond_skill_4");

    public static RegistrySupplier<SoundEvent> register(String id) {
        return REGISTRY.register(id, () -> SoundEvent.of(Identifier.of(RainimatorMod.MOD_ID, id)));
    }
}