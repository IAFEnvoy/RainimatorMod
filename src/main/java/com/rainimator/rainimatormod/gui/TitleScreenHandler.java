package com.rainimator.rainimatormod.gui;

import com.rainimator.rainimatormod.RainimatorMod;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.internal.BrandingControl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = RainimatorMod.MOD_ID, value = Dist.CLIENT)
public class TitleScreenHandler {
    private static boolean hasLoaded = false;

    @SubscribeEvent
    public static void openMainMenu(final ScreenEvent.InitScreenEvent.Post event) {
        if (event.getScreen() instanceof TitleScreen) {
            TitleScreenHandler.init();
        }
    }

    public static void init() {
        if (!hasLoaded) {
            try {
                BrandingControl brandingControl = new BrandingControl();

                Field f = BrandingControl.class.getDeclaredField("brandings");
                f.setAccessible(true);

                Method computeBranding = BrandingControl.class.getDeclaredMethod("computeBranding");
                computeBranding.setAccessible(true);
                computeBranding.invoke(null);

                List<String> brands = new ArrayList<>((List<String>) f.get(brandingControl));
                List<String> newBrands = new ArrayList<>();

                f.set(brandingControl, newBrands);
                newBrands.add("Rainimator Mod 1.0");
                newBrands.addAll(brands);
            } catch (Exception e) {
                e.printStackTrace();
            }
            hasLoaded = true;
        }
    }
}
