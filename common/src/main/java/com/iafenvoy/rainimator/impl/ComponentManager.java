package com.iafenvoy.rainimator.impl;

import com.iafenvoy.rainimator.data.ManaData;
import dev.architectury.injectables.annotations.ExpectPlatform;
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
