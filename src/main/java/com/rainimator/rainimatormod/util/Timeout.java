package com.rainimator.rainimatormod.util;

import io.netty.util.internal.UnstableApi;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class Timeout {
    private final int waitTicks;
    private final int maxTimes;
    private final Runnable callback;
    private int ticks = 0;
    private int currentTimes = 0;

    private Timeout(int waitTicks, int maxTimes, Runnable callback) {
        this.waitTicks = waitTicks;
        this.maxTimes = maxTimes;
        this.callback = callback;
    }

    public static void create(int waitTicks, Runnable callback) {
        create(waitTicks, 1, callback);
    }

    @UnstableApi
    public static void create(int waitTicks, int maxTimes, Runnable callback) {
        if (maxTimes <= 0) return;
        if (waitTicks <= 0) callback.run();
        else MinecraftForge.EVENT_BUS.register(new Timeout(waitTicks, maxTimes, callback));
    }

    @SubscribeEvent
    public void tick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            this.ticks++;
            if (this.ticks >= this.waitTicks) {
                this.callback.run();
                this.currentTimes++;
                if (this.currentTimes >= this.maxTimes)
                    MinecraftForge.EVENT_BUS.unregister(this);
            }
        }
    }
}
