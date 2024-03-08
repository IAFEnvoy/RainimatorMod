package com.rainimator.rainimatormod.world.inventory;

import com.rainimator.rainimatormod.registry.ModMenus;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;


public class EnderbookskillMenu extends AbstractContainerMenu implements Supplier<Map<Integer, Slot>> {
    public static final HashMap<String, Object> guistate = new HashMap<>();

    public final Level world;
    public final Player entity;
    public int x;
    private final Map<Integer, Slot> customSlots = new HashMap<>();
    public int y;
    public int z;

    public EnderbookskillMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
        super(ModMenus.ENDERBOOKSKILL, id);
        this.entity = inv.player;
        this.world = inv.player.level;
        IItemHandler internal = new ItemStackHandler(0);
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
