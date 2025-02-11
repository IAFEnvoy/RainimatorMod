package dev.rainimator.mod.impl.fabric;

import dev.rainimator.mod.data.ManaData;
import dev.rainimator.mod.fabric.component.ManaComponent;
import net.minecraft.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

@SuppressWarnings("unused")
public class ComponentManagerImpl {
    @Nullable
    public static ManaData getManaData(LivingEntity entity) {
        Optional<ManaComponent> data = ManaComponent.MANA_COMPONENT.maybeGet(entity);
        return data.map(ManaComponent::getData).orElse(null);
    }
}
