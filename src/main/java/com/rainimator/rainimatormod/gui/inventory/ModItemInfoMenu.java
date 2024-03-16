package com.rainimator.rainimatormod.gui.inventory;

import com.rainimator.rainimatormod.registry.ModMenus;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;


public class ModItemInfoMenu extends AbstractContainerMenu implements Supplier<Map<Integer, Slot>> {
    public final Level world;
    public final Player entity;
    private final Map<Integer, Slot> customSlots = new HashMap<>();
    public int x;
    public int y;
    public int z;

    public ModItemInfoMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
        super(ModMenus.MOD_ITEM_INFO, id);
        this.entity = inv.player;
        this.world = inv.player.level;
        if (extraData != null) {
            BlockPos pos = extraData.readBlockPos();
            this.x = pos.getX();
            this.y = pos.getY();
            this.z = pos.getZ();
        }
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return true;
    }

    @Override
    public Map<Integer, Slot> get() {
        return this.customSlots;
    }
}
