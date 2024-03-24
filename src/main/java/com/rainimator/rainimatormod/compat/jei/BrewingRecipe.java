package com.rainimator.rainimatormod.compat.jei;

import com.rainimator.rainimatormod.RainimatorMod;
import com.rainimator.rainimatormod.registry.ModItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.recipe.vanilla.IVanillaRecipeFactory;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@JeiPlugin
public class BrewingRecipe implements IModPlugin {
    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return new ResourceLocation(RainimatorMod.MOD_ID, "brewing_recipe");
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        IVanillaRecipeFactory factory = registration.getVanillaRecipeFactory();
        registration.addRecipes(RecipeTypes.BREWING, List.of(
                factory.createBrewingRecipe(List.of(new ItemStack(ModItems.BLUE_DIAMOND.get())),
                PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.AWKWARD),
                new ItemStack(ModItems.PURIFICATION.get()))));
    }
}
