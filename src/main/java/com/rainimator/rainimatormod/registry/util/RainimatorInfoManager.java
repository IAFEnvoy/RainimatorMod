package com.rainimator.rainimatormod.registry.util;

import com.rainimator.rainimatormod.RainimatorMod;
import com.rainimator.rainimatormod.gui.ModItemInfoScreen;
import com.rainimator.rainimatormod.gui.inventory.ModItemInfoMenu;
import com.rainimator.rainimatormod.util.Episode;
import io.netty.buffer.Unpooled;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;
import org.lwjgl.glfw.GLFW;

public class RainimatorInfoManager {
    public static final KeyMapping ITEM_INFO = new KeyMapping(RainimatorMod.MOD_ID + ".key_info.item_info", GLFW.GLFW_KEY_LEFT_ALT, RainimatorMod.MOD_NAME);

    public static void onRenderToolTip(LocalPlayer player, Item item) {
        if (!ITEM_INFO.isDown()) return;
        if (item instanceof IRainimatorInfo info && info.getEpisode() != Episode.None) {
            BlockPos _bpos = new BlockPos(player.position());
            String id = ForgeRegistries.ITEMS.getKey(item).getPath();
            ModItemInfoScreen.InfoType infoType = ModItemInfoScreen.InfoType.Item;
            if (item instanceof SpawnEggBase) {
                id = id.replace("_spawn_egg", "");
                infoType = ModItemInfoScreen.InfoType.Entity;
            }

            Minecraft.getInstance().setScreen(new ModItemInfoScreen(
                    new ModItemInfoMenu(111, player.getInventory(), new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos)),
                    player.getInventory(), new TextComponent("ModItemInfo"),
                    new ModItemInfoScreen.ItemInfo(id, ForgeRegistries.ITEMS.getKey(item).getPath(), infoType, info.getEpisode()),
                    Minecraft.getInstance().screen
            ));
        }
    }

    public static String getHoverText() {
        return I18n.get("gui.rainimator.item_info.alt_info");
    }
}
