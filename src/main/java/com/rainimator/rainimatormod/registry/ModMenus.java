package com.rainimator.rainimatormod.registry;

import com.rainimator.rainimatormod.world.inventory.EnderbookskillMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.network.IContainerFactory;

import java.util.ArrayList;
import java.util.List;


@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class ModMenus {
    public static final MenuType<EnderbookskillMenu> ENDERBOOKSKILL;

    static {
        ENDERBOOKSKILL = register(EnderbookskillMenu::new);
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