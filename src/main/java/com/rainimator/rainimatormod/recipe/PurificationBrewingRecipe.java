package com.rainimator.rainimatormod.recipe;

import com.rainimator.rainimatormod.registry.ModItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.brewing.IBrewingRecipe;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.jetbrains.annotations.NotNull;

@EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class PurificationBrewingRecipe implements IBrewingRecipe {
    @SubscribeEvent
    public static void init(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> BrewingRecipeRegistry.addRecipe(new PurificationBrewingRecipe()));
    }

    @Override
    public boolean isInput(@NotNull ItemStack input) {
        return Ingredient.of(new ItemStack(Items.POTION)).test(input);
    }

    @Override
    public boolean isIngredient(@NotNull ItemStack ingredient) {
        return Ingredient.of(new ItemStack(ModItems.BLUEDIAMOND.get())).test(ingredient);
    }

    @Override
    public @NotNull ItemStack getOutput(@NotNull ItemStack input, @NotNull ItemStack ingredient) {
        if (this.isInput(input) && this.isIngredient(ingredient))
            return new ItemStack(ModItems.PURIFICATION_1.get());
        return ItemStack.EMPTY;
    }
}
