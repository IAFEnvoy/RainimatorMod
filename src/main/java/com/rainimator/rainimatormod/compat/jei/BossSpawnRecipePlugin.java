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
                new BossSpawnRecipe(ModItems.LIGHT_HEART.get(), ModItems.HEROBRINE_SPAWN_EGG.get()),
                new BossSpawnRecipe(Blocks.WITHER_ROSE.asItem(), ModItems.KRALOS_SPAWN_EGG.get()),
                new BossSpawnRecipe(Items.TOTEM_OF_UNDYING, ModItems.KLAUS_SPAWN_EGG.get()),
                new BossSpawnRecipe(Blocks.WITHER_SKELETON_SKULL.asItem(), ModItems.GIGABONE_SPAWN_EGG.get()),
                new BossSpawnRecipe(ModItems.SOUL_PEOPLE.get(), ModItems.NAMTAR_SPAWN_EGG.get()),
                new BossSpawnRecipe(ModItems.WITHER_BONE.get(), ModItems.BIG_UNDEAD_SKELETON_SPAWN_EGG.get()),
                new BossSpawnRecipe(Items.GOLDEN_SWORD, ModItems.PIGLIN_COMMANDER_SPAWN_EGG.get()),
                new BossSpawnRecipe(Items.GOLD_INGOT, ModItems.ZOMBIES_PIGLIN_KING_SPAWN_EGG.get()),
                new BossSpawnRecipe(Blocks.GOLD_BLOCK.asItem(), ModItems.PIGLIN_KING_ZOMBIES_SPAWN_EGG.get()),
                new BossSpawnRecipe(ModItems.BAO_ZHU.get(), ModItems.NULL_LIKE_SPAWN_EGG.get()),
                new BossSpawnRecipe(ModItems.WARRIOR_HEART.get(), ModItems.NAEUS_SPAWN_EGG.get()),
                new BossSpawnRecipe(ModItems.ICE_HEART.get(), ModItems.CIARA_SPAWN_EGG.get()),
                new BossSpawnRecipe(ModItems.ENDER_HEART.get(), ModItems.DARK_SHIELD_SPAWN_EGG.get()),
                new BossSpawnRecipe(ModItems.MAGIC_STARD.get(), ModItems.PATRICK_SPAWN_EGG.get()),
                new BossSpawnRecipe(ModItems.LOWER_BOUND_ALLOY_BONE.get(), ModItems.BLACKBONE_SPAWN_EGG.get()),
                new BossSpawnRecipe(ModItems.UNDER_FLOWER.get(), ModItems.ABIGAIL_SPAWN_EGG.get())
        ));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModItems.DARK_OBSIDIAN_BLOCK.get()), BOSS_SPAWN_RECIPE);
    }
}
