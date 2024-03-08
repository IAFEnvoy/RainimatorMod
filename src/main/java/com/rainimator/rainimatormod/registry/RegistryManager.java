package com.rainimator.rainimatormod.registry;

import net.minecraftforge.eventbus.api.IEventBus;

public class RegistryManager {
    public static void register(IEventBus bus) {
        ModBlocks.REGISTRY.register(bus);
        ModEffects.REGISTRY.register(bus);
        ModEntities.REGISTRY.register(bus);
        ModFeatures.REGISTRY.register(bus);
        ModItems.REGISTRY.register(bus);
        ModParticleTypes.REGISTRY.register(bus);
    }
}
