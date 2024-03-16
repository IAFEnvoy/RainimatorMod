package com.rainimator.rainimatormod.registry;

import com.rainimator.rainimatormod.gui.inventory.EnderBookSkillMenu;
import com.rainimator.rainimatormod.gui.inventory.ModItemInfoMenu;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.network.IContainerFactory;


@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class ModMenus {
    public static final MenuType<EnderBookSkillMenu> ENDERBOOKSKILL;
    public static final MenuType<ModItemInfoMenu> MOD_ITEM_INFO;

    static {
        ENDERBOOKSKILL = register(EnderBookSkillMenu::new,"enderbookskill");
        MOD_ITEM_INFO = register(ModItemInfoMenu::new,"mod_item_info");
    }

    private static <T extends AbstractContainerMenu> MenuType<T> register(IContainerFactory<T> containerFactory, String name) {
        MenuType<T> menuType = new MenuType<>(containerFactory);
        menuType.setRegistryName(name);
        return menuType;
    }

    @SubscribeEvent
    public static void registerContainers(RegistryEvent.Register<MenuType<?>> event) {
        event.getRegistry().register(ENDERBOOKSKILL);
    }
}