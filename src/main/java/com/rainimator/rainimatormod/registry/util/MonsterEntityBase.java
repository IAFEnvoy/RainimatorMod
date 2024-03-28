package com.rainimator.rainimatormod.registry.util;

import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

public class MonsterEntityBase extends Monster {
    private final MobType mobType;

    protected MonsterEntityBase(EntityType<? extends Monster> p_33002_, Level p_33003_, MobType mobType) {
        super(p_33002_, p_33003_);
        this.mobType = mobType;
        this.setNoAi(false);
    }

    @Override
    public @NotNull Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public @NotNull MobType getMobType() {
        return this.mobType;
    }
}
