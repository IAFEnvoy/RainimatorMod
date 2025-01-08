package dev.rainimator.mod.fabric;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import dev.rainimator.mod.registry.RainimatorItems;
import dev.rainimator.mod.registry.RainimatorPotions;
import net.fabricmc.fabric.api.entity.event.v1.EntityElytraEvents;
import net.fabricmc.fabric.api.entity.event.v1.FabricElytraItem;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Pair;

import java.util.List;
import java.util.Optional;

public class FabricApiCall {
    public static void run() {
        FabricBrewingRecipeRegistry.registerPotionRecipe(Potions.AWKWARD, Ingredient.ofItems(RainimatorItems.BLUE_DIAMOND.get()), RainimatorPotions.PURIFICATION_POTION.get());
        EntityElytraEvents.CUSTOM.register((entity, tickElytra) -> {
            Optional<TrinketComponent> optional = TrinketsApi.getTrinketComponent(entity);
            if (optional.isEmpty()) return false;
            List<Pair<SlotReference, ItemStack>> list = optional.get().getEquipped(RainimatorItems.WINGS_OF_SALVATION.get());
            if (list.isEmpty()) return false;
            ItemStack stack = list.get(0).getRight();
            if (stack.getItem() instanceof FabricElytraItem fabricElytraItem)
                return fabricElytraItem.useCustomElytra(entity, stack, tickElytra);
            return false;
        });
    }
}
