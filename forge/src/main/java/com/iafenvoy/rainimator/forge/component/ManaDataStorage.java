package com.iafenvoy.rainimator.forge.component;

import com.iafenvoy.rainimator.data.ManaData;
import net.minecraft.nbt.NbtCompound;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;
import net.minecraftforge.common.util.INBTSerializable;

@AutoRegisterCapability
public class ManaDataStorage implements INBTSerializable<NbtCompound> {
    private final ManaData playerData = new ManaData();

    public ManaDataStorage() {
    }

    @Override
    public NbtCompound serializeNBT() {
        NbtCompound compound = new NbtCompound();
        this.playerData.encode(compound);
        return compound;
    }

    @Override
    public void deserializeNBT(NbtCompound compound) {
        this.playerData.decode(compound);
    }

    public ManaData getPlayerData() {
        return this.playerData;
    }
}
