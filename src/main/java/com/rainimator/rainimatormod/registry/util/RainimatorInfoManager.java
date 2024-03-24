package com.rainimator.rainimatormod.registry.util;

import com.rainimator.rainimatormod.RainimatorMod;
import com.rainimator.rainimatormod.gui.ModItemInfoScreen;
import com.rainimator.rainimatormod.util.Episode;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;
import org.lwjgl.glfw.GLFW;

public class RainimatorInfoManager {
    public static final KeyMapping ITEM_INFO = new KeyMapping(RainimatorMod.MOD_ID + ".key_info.item_info", GLFW.GLFW_KEY_LEFT_ALT, RainimatorMod.MOD_NAME);

    public static void onRenderToolTip(Item item) {
        if (!ITEM_INFO.isDown()) return;
        if (item instanceof IRainimatorInfo info && info.getEpisode() != Episode.None) {
            ResourceLocation location = ForgeRegistries.ITEMS.getKey(item);
            if (location == null) return;
            String id = location.getPath();
            ModItemInfoScreen.InfoType infoType = ModItemInfoScreen.InfoType.Item;
            if (item instanceof SpawnEggBase) {
                id = id.replace("_spawn_egg", "");
                infoType = ModItemInfoScreen.InfoType.Entity;
            }
            Minecraft.getInstance().setScreen(new ModItemInfoScreen(new TextComponent("ModItemInfo"),
                    new ModItemInfoScreen.ItemInfo(id, location.getPath(), infoType, info.getEpisode()),
                    Minecraft.getInstance().screen
            ));
        }
    }

    public static String getHoverText() {
        return I18n.get("gui.rainimator.item_info.alt_info");
    }
}
