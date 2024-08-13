package dev.rainimator.mod.impl.fabric;

import dev.rainimator.mod.data.component.FractionData;
import dev.rainimator.mod.data.component.ManaData;
import dev.rainimator.mod.fabric.component.FractionComponent;
import dev.rainimator.mod.fabric.component.ManaComponent;
import net.minecraft.entity.LivingEntity;
import org.apache.commons.lang3.NotImplementedException;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

@SuppressWarnings("unused")
public class ComponentManagerImpl {
    @Nullable
    public static ManaData getManaData(LivingEntity entity) {
        Optional<ManaComponent> data = ManaComponent.MANA_COMPONENT.maybeGet(entity);
        return data.map(ManaComponent::getData).orElse(null);
    }

    @Nullable
    public static FractionData getFractionData(LivingEntity entity) {
        Optional<FractionComponent> data = FractionComponent.FRACTION_COMPONENT.maybeGet(entity);
        return data.map(FractionComponent::getData).orElse(null);
    }
}
