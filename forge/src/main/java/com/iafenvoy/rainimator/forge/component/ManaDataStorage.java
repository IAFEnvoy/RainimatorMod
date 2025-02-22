package com.iafenvoy.rainimator.forge.component;

import com.iafenvoy.neptune.forge.component.ITickableCapability;
import com.iafenvoy.rainimator.data.ManaData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public class ManaDataStorage implements ITickableCapability {
    private final ManaData playerData = new ManaData();
    private final LivingEntity living;

    public ManaDataStorage(LivingEntity living) {
        this.living = living;
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

    @Override
    public void tick() {
        this.playerData.tick(this.living);
    }

    @Override
    public boolean isDirty() {
        return false;
    }
}
