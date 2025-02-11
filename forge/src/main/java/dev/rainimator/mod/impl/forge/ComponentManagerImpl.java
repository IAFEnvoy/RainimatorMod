package dev.rainimator.mod.impl.forge;

import dev.rainimator.mod.data.ManaData;
import dev.rainimator.mod.forge.component.ManaDataProvider;
import dev.rainimator.mod.forge.component.ManaDataStorage;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

@SuppressWarnings("unused")
public class ComponentManagerImpl {
    @Nullable
    public static ManaData getManaData(LivingEntity entity) {
        LazyOptional<ManaDataStorage> storageLazyOptional = entity.getCapability(ManaDataProvider.CAPABILITY);
        if (!storageLazyOptional.isPresent()) return null;
        Optional<ManaDataStorage> storage = storageLazyOptional.resolve();
        return storage.map(ManaDataStorage::getPlayerData).orElse(null);
    }
}
