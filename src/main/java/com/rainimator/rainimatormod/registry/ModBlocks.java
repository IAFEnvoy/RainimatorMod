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
    public static final RegistryObject<Block> SAPPRIES_ORE = REGISTRY.register("sappries_ore", SappriesOreBlock::new);
    public static final RegistryObject<Block> DEEPSLATE_SAPPRIES_ORE = REGISTRY.register("deepslate_sappries_ore", DeepslateSappriesOreBlock::new);
    public static final RegistryObject<Block> DEEPSLATE_RUBY_ORE = REGISTRY.register("deepslate_ruby_ore", DeepslateRubyOreBlock::new);
    public static final RegistryObject<Block> DARKOBSIDIANBLOCK = REGISTRY.register("darkobsidianblock", DarkobsidianblockBlock::new);
    public static final RegistryObject<Block> RUBYBLOCK = REGISTRY.register("rubyblock", RubyblockBlock::new);
    public static final RegistryObject<Block> SAPPRIESBLOCK = REGISTRY.register("sappriesblock", SappriesblockBlock::new);
    public static final RegistryObject<Block> BLUEDIAMONDBLOCK = REGISTRY.register("bluediamondblock", BluediamondblockBlock::new);
    public static final RegistryObject<Block> MYSTICORE = REGISTRY.register("mysticore", MysticoreBlock::new);

    @EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
    public static class ClientSideHandler {
        @SubscribeEvent
        public static void clientSetup(FMLClientSetupEvent event) {
            DarkobsidianblockBlock.registerRenderLayer();
        }
    }
}
