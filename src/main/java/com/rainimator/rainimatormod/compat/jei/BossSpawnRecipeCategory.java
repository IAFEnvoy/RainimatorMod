package com.rainimator.rainimatormod.compat.jei;

import com.mojang.blaze3d.systems.RenderSystem;
import com.rainimator.rainimatormod.RainimatorMod;
import com.rainimator.rainimatormod.registry.ModItems;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocus;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class BossSpawnRecipeCategory implements IRecipeCategory<BossSpawnRecipe> {
    private final IDrawable icon;
    private final IDrawable background;

    public BossSpawnRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(new ResourceLocation(RainimatorMod.MOD_ID, "textures/gui/gui_boss_spawn_recipe.png"), 0, 0, 140, 44);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModItems.DARKOBSIDIANBLOCK.get()));
    }

    @SuppressWarnings("removal")
    @Override
    public @NotNull ResourceLocation getUid() {
        return BossSpawnRecipePlugin.BOSS_SPAWN;
    }

    @Override
    public @NotNull Component getTitle() {
        return new TranslatableComponent("block.rainimator.darkobsidianblock");
    }

    @Override
    public @NotNull IDrawable getBackground() {
        return this.background;
    }

    @Override
    public @NotNull IDrawable getIcon() {
        return this.icon;
    }

    @SuppressWarnings("removal")
    @Override
    public @NotNull Class<? extends BossSpawnRecipe> getRecipeClass() {
        return BossSpawnRecipe.class;
    }

    @Override
    public @NotNull RecipeType<BossSpawnRecipe> getRecipeType() {
        return new RecipeType<>(BossSpawnRecipePlugin.BOSS_SPAWN, BossSpawnRecipe.class);
    }

    @Override
    public void setRecipe(@NotNull IRecipeLayoutBuilder builder, @NotNull BossSpawnRecipe recipe, @NotNull IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 14, 13).addItemStack(new ItemStack(recipe.activeItem()));
        builder.addSlot(RecipeIngredientRole.RENDER_ONLY, 62, 13).addItemStack(new ItemStack(ModItems.DARKOBSIDIANBLOCK.get()));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 110, 13).addItemStack(new ItemStack(recipe.entitySpawnEgg()));
    }
}
