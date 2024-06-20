package dev.rainimator.mod.registry;

import dev.architectury.registry.menu.MenuRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.rainimator.mod.RainimatorMod;
import dev.rainimator.mod.screen.gui.EnderBookSkillScreen;
import dev.rainimator.mod.screen.handler.EnderBookSkillScreenHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;

import java.util.function.Supplier;

public class RainimatorScreenHandlers {
    public static final DeferredRegister<ScreenHandlerType<?>> REGISTRY = DeferredRegister.create(RainimatorMod.MOD_ID, RegistryKeys.SCREEN_HANDLER);
    public static final RegistrySupplier<ScreenHandlerType<EnderBookSkillScreenHandler>> ENDER_BOOK = register("ender_book", () -> new ScreenHandlerType<>(EnderBookSkillScreenHandler::new, FeatureFlags.VANILLA_FEATURES));

    private static <T extends ScreenHandler> RegistrySupplier<ScreenHandlerType<T>> register(String name, Supplier<ScreenHandlerType<T>> type) {
        return REGISTRY.register(name, type);
    }

    @Environment(EnvType.CLIENT)
    public static void registerGui() {
        MenuRegistry.registerScreenFactory(ENDER_BOOK.get(), EnderBookSkillScreen::new);
    }
}
