package com.iafenvoy.rainimator.registry;

import com.iafenvoy.rainimator.RainimatorMod;
import com.iafenvoy.rainimator.item.block.DarkObsidianBlock;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class RainimatorBlocks {
    public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(RainimatorMod.MOD_ID, RegistryKeys.BLOCK);
    public static final RegistrySupplier<Block> RUBY_ORE = register("ruby_ore", () -> new Block(AbstractBlock.Settings.create().requiresTool().strength(4)));
    public static final RegistrySupplier<Block> SAPPHIRE_ORE = register("sapphire_ore", () -> new Block(AbstractBlock.Settings.create().requiresTool().strength(4)));
    public static final RegistrySupplier<Block> SUGILITE_ORE = register("sugilite_ore", () -> new Block(AbstractBlock.Settings.create().requiresTool().strength(4)));
    public static final RegistrySupplier<Block> TOPAZ_ORE = register("topaz_ore", () -> new Block(AbstractBlock.Settings.create().requiresTool().strength(4)));
    public static final RegistrySupplier<Block> DEEPSLATE_RUBY_ORE = register("deepslate_ruby_ore", () -> new Block(AbstractBlock.Settings.create().requiresTool().strength(4)));
    public static final RegistrySupplier<Block> DEEPSLATE_SAPPHIRE_ORE = register("deepslate_sapphire_ore", () -> new Block(AbstractBlock.Settings.create().requiresTool().strength(4)));
    public static final RegistrySupplier<Block> DARK_OBSIDIAN_BLOCK = register("dark_obsidian_block", DarkObsidianBlock::new);
    public static final RegistrySupplier<Block> RUBY_BLOCK = register("ruby_block", () -> new Block(AbstractBlock.Settings.create().requiresTool().strength(6).sounds(BlockSoundGroup.METAL)));
    public static final RegistrySupplier<Block> SAPPHIRE_BLOCK = register("sapphire_block", () -> new Block(AbstractBlock.Settings.create().requiresTool().strength(6).sounds(BlockSoundGroup.METAL)));
    public static final RegistrySupplier<Block> SUGILITE_BLOCK = register("sugilite_block", () -> new Block(AbstractBlock.Settings.create().requiresTool().strength(6).sounds(BlockSoundGroup.METAL)));
    public static final RegistrySupplier<Block> TOPAZ_BLOCK = register("topaz_block", () -> new Block(AbstractBlock.Settings.create().requiresTool().strength(6).sounds(BlockSoundGroup.METAL)));
    public static final RegistrySupplier<Block> BLUE_DIAMOND_BLOCK = register("blue_diamond_block", () -> new Block(AbstractBlock.Settings.create().requiresTool().strength(6).sounds(BlockSoundGroup.METAL)));
    public static final RegistrySupplier<Block> MYSTIC_ORE = register("mystic_ore", () -> new Block(AbstractBlock.Settings.create().requiresTool().strength(4)));

    private static <T extends Block> RegistrySupplier<T> register(String name, Supplier<T> block) {
        RegistrySupplier<T> r = REGISTRY.register(name, block);
        RainimatorItems.REGISTRY.register(name, () -> new BlockItem(r.get(), new Item.Settings().arch$tab(RainimatorItemGroups.ITEM)));
        return r;
    }
}
