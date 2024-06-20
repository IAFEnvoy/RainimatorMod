package dev.rainimator.mod.registry;

import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.rainimator.mod.RainimatorMod;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class RainimatorItemGroups {
    public static final DeferredRegister<ItemGroup> REGISTRY = DeferredRegister.create(RainimatorMod.MOD_ID, RegistryKeys.ITEM_GROUP);
    public static final RegistrySupplier<ItemGroup> MAIN = register("main", () -> CreativeTabRegistry.create(
            Text.translatable("itemGroup." + RainimatorMod.MOD_ID + ".rainimator"),
            () -> new ItemStack(RainimatorItems.WARRIOR_HEART.get())
    ));
    public static final RegistrySupplier<ItemGroup> MOBS = register("mobs", () -> CreativeTabRegistry.create(
            Text.translatable("itemGroup." + RainimatorMod.MOD_ID + ".rainimator_mobs"),
            () -> new ItemStack(RainimatorItems.ICE_HEART.get())
    ));
    public static final RegistrySupplier<ItemGroup> ITEM = register("item", () -> CreativeTabRegistry.create(
            Text.translatable("itemGroup." + RainimatorMod.MOD_ID + ".rainimator_item"),
            () -> new ItemStack(RainimatorItems.ENDER_HEART.get())
    ));

    private static <T extends ItemGroup> RegistrySupplier<T> register(String name, Supplier<T> group) {
        return REGISTRY.register(name, group);
    }

    public static void init() {
        CreativeTabRegistry.appendStack(MAIN,
                RainimatorBanners.FROST,
                RainimatorBanners.UNDEAD,
                RainimatorBanners.NETHER,
                RainimatorBanners.DRAGONSPIRE,
                RainimatorBanners.ENDER_PIRATE,
                RainimatorBanners.THE_GATEKEEPERS,
                RainimatorBanners.ORCHID_CITY,
                RainimatorBanners.LIGHTBORNE_ISLES,
                RainimatorBanners.THE_UMBRAL_KINGDOM,
                RainimatorBanners.CHORUS_BAY,
                RainimatorBanners.VOID_ISLANDS
        );
    }
}