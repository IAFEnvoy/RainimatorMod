package dev.rainimator.mod.impl;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.rainimator.mod.data.component.ManaData;
import net.minecraft.entity.LivingEntity;
import org.apache.commons.lang3.NotImplementedException;
import org.jetbrains.annotations.Nullable;

public class ComponentManager {
    @ExpectPlatform
    @Nullable
    public static ManaData getManaData(LivingEntity entity) {
        throw new NotImplementedException("This method should be replaced by Architectury.");
    }
}
