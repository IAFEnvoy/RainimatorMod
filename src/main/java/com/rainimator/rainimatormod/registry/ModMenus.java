package com.rainimator.rainimatormod.registry;

import com.rainimator.rainimatormod.gui.inventory.EnderBookSkillMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.network.IContainerFactory;


@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class ModMenus {
    public static final MenuType<EnderBookSkillMenu> ENDERBOOKSKILL;

    static {
        ENDERBOOKSKILL = register(EnderBookSkillMenu::new);
    }

    private static <T extends net.minecraft.world.inventory.AbstractContainerMenu> MenuType<T> register(IContainerFactory<T> containerFactory) {
        MenuType<T> menuType = new MenuType<>(containerFactory);
        menuType.setRegistryName("enderbookskill");
        return menuType;
    }

    @SubscribeEvent
    public static void registerContainers(RegistryEvent.Register<MenuType<?>> event) {
        event.getRegistry().register(ENDERBOOKSKILL);
    }
}