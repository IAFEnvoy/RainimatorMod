package com.rainimator.rainimatormod.registry;

import com.rainimator.rainimatormod.gui.EnderBookSkillScreen;
import com.rainimator.rainimatormod.gui.ModItemInfoScreen;
import com.rainimator.rainimatormod.gui.inventory.ModItemInfoMenu;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class ModScreens {
    @SubscribeEvent
    public static void clientLoad(FMLClientSetupEvent event) {
        event.enqueueWork(() -> MenuScreens.register(ModMenus.ENDERBOOKSKILL, EnderBookSkillScreen::new));
    }
}
