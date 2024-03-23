package com.rainimator.rainimatormod.registry;

import com.rainimator.rainimatormod.RainimatorMod;
import com.rainimator.rainimatormod.block.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, RainimatorMod.MOD_ID);
    public static final RegistryObject<Block> RUBY_ORE = REGISTRY.register("ruby_ore", RubyOreBlock::new);
    public static final RegistryObject<Block> SAPPHIRE_ORE = REGISTRY.register("sapphire_ore", SapphireOreBlock::new);
    public static final RegistryObject<Block> DEEPSLATE_SAPPHIRE_ORE = REGISTRY.register("deepslate_sapphire_ore", DeepslateSapphireOreBlock::new);
    public static final RegistryObject<Block> DEEPSLATE_RUBY_ORE = REGISTRY.register("deepslate_ruby_ore", DeepslateRubyOreBlock::new);
    public static final RegistryObject<Block> DARK_OBSIDIAN_BLOCK = REGISTRY.register("dark_obsidian_block", DarkObsidianBlock::new);
    public static final RegistryObject<Block> RUBY_BLOCK = REGISTRY.register("ruby_block", RubyBlock::new);
    public static final RegistryObject<Block> SAPPHIRE_BLOCK = REGISTRY.register("sapphire_block", SapphireBlock::new);
    public static final RegistryObject<Block> BLUE_DIAMOND_BLOCK = REGISTRY.register("blue_diamond_block", BlueDiamondBlock::new);
    public static final RegistryObject<Block> MYSTIC_ORE = REGISTRY.register("mystic_ore", MysticoreBlock::new);

    @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
    public static class ClientSideHandler {
        @SubscribeEvent
        public static void clientSetup(FMLClientSetupEvent event) {
            DarkObsidianBlock.registerRenderLayer();
        }
    }
}
