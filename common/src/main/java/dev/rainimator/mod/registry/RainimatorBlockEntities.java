package dev.rainimator.mod.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.rainimator.mod.RainimatorMod;
import dev.rainimator.mod.item.block.entity.DarkObsidianBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.datafixer.TypeReferences;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Util;

import java.util.function.Supplier;

public class RainimatorBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(RainimatorMod.MOD_ID, RegistryKeys.BLOCK_ENTITY_TYPE);
    public static final RegistrySupplier<BlockEntityType<DarkObsidianBlockEntity>> DARK_OBSIDIAN_BLOCK = register("dark_obsidian_block", () -> BlockEntityType.Builder.create(DarkObsidianBlockEntity::new, RainimatorBlocks.DARK_OBSIDIAN_BLOCK.get()));

    private static <T extends BlockEntity> RegistrySupplier<BlockEntityType<T>> register(String name, Supplier<BlockEntityType.Builder<T>> type) {
        return REGISTRY.register(name, () -> type.get().build(Util.getChoiceType(TypeReferences.BLOCK_ENTITY, name)));
    }
}
