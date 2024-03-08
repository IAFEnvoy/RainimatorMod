package com.rainimator.rainimatormod.util;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class Timeout {
    private final int waitTicks;
    private final Runnable callback;
    private int ticks = 0;
    private Timeout(int waitTicks, Runnable callback) {
        this.waitTicks = waitTicks;
        this.callback = callback;
    }

    public static void create(int waitTicks, Runnable callback) {
        MinecraftForge.EVENT_BUS.register(new Timeout(waitTicks, callback));
    }

    @SubscribeEvent
    public void tick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            this.ticks++;
            if (this.ticks >= this.waitTicks) {
                this.callback.run();
                MinecraftForge.EVENT_BUS.unregister(this);
            }
        }
    }
}
