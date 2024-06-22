package dev.rainimator.mod.forge;

import com.afoxxvi.asteorbar.overlay.ForgeRenderGui;
import dev.architectury.platform.Platform;
import dev.architectury.platform.forge.EventBuses;
import dev.rainimator.mod.RainimatorMod;
import dev.rainimator.mod.data.component.ManaData;
import dev.rainimator.mod.forge.compat.asteorbar.ManaHud;
import dev.rainimator.mod.forge.compat.curios.CuriosRegistry;
import dev.rainimator.mod.forge.component.ManaDataProvider;
import dev.rainimator.mod.impl.ComponentManager;
import dev.rainimator.mod.registry.RainimatorItems;
import dev.rainimator.mod.registry.RainimatorPotions;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.List;

@Mod(RainimatorMod.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RainimatorModForge {
    public RainimatorModForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(RainimatorMod.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        RainimatorMod.init();
        if (Platform.getEnv() == Dist.CLIENT) {
            RainimatorMod.initClient();
            if (ModList.get().isLoaded("asteorbar"))
                FMLJavaModLoadingContext.get().getModEventBus().addListener(RainimatorModForge::registerOverlay);
        }
    }

    @SubscribeEvent
    public static void onInit(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            RainimatorMod.process();
            CuriosRegistry.registerCommon();
            BrewingRecipeRegistry.addRecipe(Ingredient.ofStacks(PotionUtil.setPotion(Items.POTION.getDefaultStack(), Potions.AWKWARD)),
                    Ingredient.ofItems(RainimatorItems.BLUE_DIAMOND.get()),
                    PotionUtil.setPotion(Items.POTION.getDefaultStack(), RainimatorPotions.PURIFICATION_POTION.get()));
            BrewingRecipeRegistry.addRecipe(Ingredient.ofStacks(PotionUtil.setPotion(Items.POTION.getDefaultStack(), RainimatorPotions.PURIFICATION_POTION.get())),
                    Ingredient.ofItems(Items.GUNPOWDER),
                    PotionUtil.setPotion(Items.SPLASH_POTION.getDefaultStack(), RainimatorPotions.PURIFICATION_POTION.get()));
            BrewingRecipeRegistry.addRecipe(Ingredient.ofStacks(PotionUtil.setPotion(Items.SPLASH_POTION.getDefaultStack(), RainimatorPotions.PURIFICATION_POTION.get())),
                    Ingredient.ofItems(Items.GUNPOWDER),
                    PotionUtil.setPotion(Items.LINGERING_POTION.getDefaultStack(), RainimatorPotions.PURIFICATION_POTION.get()));
        });
    }

    @SubscribeEvent
    public static void onClientInit(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            RainimatorMod.processClient();
            CuriosRegistry.registerClient();
        });
    }

    public static void registerOverlay(RegisterGuiOverlaysEvent event) {
        event.registerBelow(VanillaGuiOverlay.PLAYER_HEALTH.id(), "mana_hud", new ForgeRenderGui(new ManaHud()));
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class ForgeBusEvents {
        @SubscribeEvent
        public static void attachEntityCapabilities(AttachCapabilitiesEvent<Entity> event) {
            if (event.getObject() instanceof PlayerEntity player) {
                boolean isServerNotFake = player instanceof ServerPlayerEntity && !(player instanceof FakePlayer);
                if ((isServerNotFake || player instanceof AbstractClientPlayerEntity) && !player.getCapability(ManaDataProvider.CAPABILITY).isPresent())
                    event.addCapability(new Identifier(RainimatorMod.MOD_ID, "player_data"), new ManaDataProvider());
            }
        }

        @SubscribeEvent
        public static void onServerTick(TickEvent.ServerTickEvent event) {
            MinecraftServer server = event.getServer();
            List<ServerPlayerEntity> players = server.getPlayerManager().getPlayerList();
            for (PlayerEntity player : players) {
                ManaData data = ComponentManager.getManaData(player);
                if (data != null) data.tick(player);
            }
        }
    }
}