package dev.rainimator.mod.registry;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import dev.rainimator.mod.RainimatorMod;
import dev.rainimator.mod.item.block.*;
import dev.rainimator.mod.util.MemorizeSupplier;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class RainimatorBlocks {
    public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(RainimatorMod.MOD_ID, RegistryKeys.BLOCK);
    public static final RegistrySupplier<Block> RUBY_ORE = register("ruby_ore", RubyOreBlock::new);
    public static final RegistrySupplier<Block> SAPPHIRE_ORE = register("sapphire_ore", SapphireOreBlock::new);
    public static final RegistrySupplier<Block> SUGILITE_ORE = register("sugilite_ore", SugiliteOreBlock::new);
    public static final RegistrySupplier<Block> TOPAZ_ORE = register("topaz_ore", TopazOreBlock::new);
    public static final RegistrySupplier<Block> DEEPSLATE_RUBY_ORE = register("deepslate_ruby_ore", DeepslateRubyOreBlock::new);
    public static final RegistrySupplier<Block> DEEPSLATE_SAPPHIRE_ORE = register("deepslate_sapphire_ore", DeepslateSapphireOreBlock::new);
    public static final RegistrySupplier<Block> DARK_OBSIDIAN_BLOCK = register("dark_obsidian_block", DarkObsidianBlock::new);
    public static final RegistrySupplier<Block> RUBY_BLOCK = register("ruby_block", RubyBlock::new);
    public static final RegistrySupplier<Block> SAPPHIRE_BLOCK = register("sapphire_block", SapphireBlock::new);
    public static final RegistrySupplier<Block> SUGILITE_BLOCK = register("sugilite_block", SugiliteBlock::new);
    public static final RegistrySupplier<Block> TOPAZ_BLOCK = register("topaz_block", TopazBlock::new);
    public static final RegistrySupplier<Block> BLUE_DIAMOND_BLOCK = register("blue_diamond_block", BlueDiamondBlock::new);
    public static final RegistrySupplier<Block> MYSTIC_ORE = register("mystic_ore", MysticoreBlock::new);

    private static <T extends Block> RegistrySupplier<T> register(String name, Supplier<T> block) {
        final Supplier<T> newBlock = new MemorizeSupplier<>(block);
        RainimatorItems.REGISTRY.register(name, () -> new BlockItem(newBlock.get(), new Item.Settings()));
        return REGISTRY.register(name, newBlock);
    }
}
