package com.rainimator.rainimatormod.compat.jei;

import com.rainimator.rainimatormod.RainimatorMod;
import com.rainimator.rainimatormod.registry.ModItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@JeiPlugin
public class BossSpawnRecipePlugin implements IModPlugin {
    public static final ResourceLocation BOSS_SPAWN = new ResourceLocation(RainimatorMod.MOD_ID, "boss_spawn_recipe");
    private static final RecipeType<BossSpawnRecipe> BOSS_SPAWN_RECIPE = new RecipeType<>(BOSS_SPAWN, BossSpawnRecipe.class);

    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return new ResourceLocation(RainimatorMod.MOD_ID, "boss_spawn_recipe");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IJeiHelpers jeiHelpers = registration.getJeiHelpers();
        registration.addRecipeCategories(new BossSpawnRecipeCategory(jeiHelpers.getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(BOSS_SPAWN_RECIPE, List.of(
                new BossSpawnRecipe(ModItems.LIGHTHEART.get(), ModItems.HEROBRINE_SPAWN_EGG.get()),
                new BossSpawnRecipe(Blocks.WITHER_ROSE.asItem(), ModItems.KRALOS_SPAWN_EGG.get()),
                new BossSpawnRecipe(Items.TOTEM_OF_UNDYING, ModItems.KLAUS_SPAWN_EGG.get()),
                new BossSpawnRecipe(Blocks.WITHER_SKELETON_SKULL.asItem(), ModItems.GIGABONE_SPAWN_EGG.get()),
                new BossSpawnRecipe(ModItems.SOULPEOPLE.get(), ModItems.NAMTAR_SPAWN_EGG.get()),
                new BossSpawnRecipe(ModItems.WITHER_BONE.get(), ModItems.BIGUNDEADSKELETON_SPAWN_EGG.get()),
                new BossSpawnRecipe(Items.GOLDEN_SWORD, ModItems.PIGLINCOMMANDER_SPAWN_EGG.get()),
                new BossSpawnRecipe(Items.GOLD_INGOT, ModItems.ZOMBIESPLIGEKING_SPAWN_EGG.get()),
                new BossSpawnRecipe(Blocks.GOLD_BLOCK.asItem(), ModItems.PILGEKINGZOMBIES_SPAWN_EGG.get()),
                new BossSpawnRecipe(ModItems.BAOZHU.get(), ModItems.NULLLIKE_SPAWN_EGG.get()),
                new BossSpawnRecipe(ModItems.WARRIORHEART.get(), ModItems.NAEUS_SPAWN_EGG.get()),
                new BossSpawnRecipe(ModItems.ICEHEART.get(), ModItems.CIARA_SPAWN_EGG.get()),
                new BossSpawnRecipe(ModItems.ENDERHEART.get(), ModItems.DARKSHIELD_SPAWN_EGG.get()),
                new BossSpawnRecipe(ModItems.MAGIC_STARD.get(), ModItems.PATRICK_SPAWN_EGG.get()),
                new BossSpawnRecipe(ModItems.LOWER_BOUND_ALLOY_BONE.get(), ModItems.BLACKBONE_SPAWN_EGG.get()),
                new BossSpawnRecipe(ModItems.UNDERFLOWER.get(), ModItems.ABIGAIL_SPAWN_EGG.get())
        ));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModItems.DARKOBSIDIANBLOCK.get()), BOSS_SPAWN_RECIPE);
    }
}
