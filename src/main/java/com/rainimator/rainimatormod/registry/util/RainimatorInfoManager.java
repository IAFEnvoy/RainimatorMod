package com.rainimator.rainimatormod.registry.util;

import com.ibm.icu.impl.Pair;
import com.rainimator.rainimatormod.RainimatorMod;
import com.rainimator.rainimatormod.gui.ModItemInfoScreen;
import com.rainimator.rainimatormod.gui.inventory.ModItemInfoMenu;
import com.rainimator.rainimatormod.registry.ModItems;
import io.netty.buffer.Unpooled;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.Item;
import org.lwjgl.glfw.GLFW;

import java.util.HashMap;

public class RainimatorInfoManager {
    public static final KeyMapping ITEM_INFO = new KeyMapping(RainimatorMod.MOD_ID + ".key_info.item_info", GLFW.GLFW_KEY_LEFT_ALT, RainimatorMod.MOD_NAME);
    public static final HashMap<Item, Pair<String, ModItemInfoScreen.InfoType>> idMap = new HashMap<>();

    public static void onInventoryTick(LocalPlayer player, Item item) {
        if (!ITEM_INFO.isDown()) return;
        if (idMap.containsKey(item) && item instanceof IRainimatorInfo info) {
            BlockPos _bpos = new BlockPos(player.position());
            Pair<String, ModItemInfoScreen.InfoType> p = idMap.get(item);
            Minecraft.getInstance().setScreen(new ModItemInfoScreen(
                    new ModItemInfoMenu(111, player.getInventory(), new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos)),
                    player.getInventory(), new TextComponent("ModItemInfo"),
                    new ModItemInfoScreen.ItemInfo(p.first, Registry.ITEM.getKey(item).getPath(), p.second, info.getEpisode())
            ));
        }
    }

    public static void initIdMap() {
        idMap.put(ModItems.HEROBRINE_SPAWN_EGG.get(), Pair.of("herobrine", ModItemInfoScreen.InfoType.Entity));
        idMap.put(ModItems.RAIN_SWORD.get(), Pair.of("rain_sword", ModItemInfoScreen.InfoType.Item));
        idMap.put(ModItems.BLACKBONE_THE_BLADE.get(), Pair.of("blackbone_the_blade", ModItemInfoScreen.InfoType.Item));
    }
}
