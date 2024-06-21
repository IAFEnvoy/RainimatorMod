package dev.rainimator.mod.fabric;

import dev.architectury.networking.NetworkManager;
import dev.architectury.utils.Env;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import dev.rainimator.mod.ModConstants;
import dev.rainimator.mod.RainimatorMod;
import dev.rainimator.mod.fabric.compat.trinkets.TrinketsRegistry;
import dev.rainimator.mod.impl.WingsOfSalvationItem;
import dev.rainimator.mod.registry.RainimatorItems;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Pair;

import java.util.List;
import java.util.Optional;

public class RainimatorModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        RainimatorMod.init();
        RainimatorMod.process();
        FabricApiCall.run();
        TrinketsRegistry.registerCommon();

        //TODO
        NetworkManager.registerReceiver(NetworkManager.Side.C2S, ModConstants.ABILITY_PACKET_ID, (buf, context) -> {
            if (context.getEnvironment() == Env.CLIENT) return;
            ServerPlayerEntity player = (ServerPlayerEntity) context.getPlayer();
            Optional<TrinketComponent> component = TrinketsApi.getTrinketComponent(player);
            if (component.isPresent()) {
                List<Pair<SlotReference, ItemStack>> stacks = component.get().getEquipped(RainimatorItems.WINGS_OF_SALVATION.get());
                if (stacks.size() > 0) {
                    if (RainimatorItems.WINGS_OF_SALVATION instanceof WingsOfSalvationItem w)
                        w.keyPress(player, stacks.get(0).getRight());
                }
            }
        });
    }
}