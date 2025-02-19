package com.iafenvoy.rainimator.compat.emi;

import com.iafenvoy.rainimator.RainimatorMod;
import com.iafenvoy.rainimator.recipe.BossSpawnRecipeManager;
import com.iafenvoy.rainimator.registry.RainimatorBlocks;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Map;

@Environment(EnvType.CLIENT)
public class BossSpawnRecipePlugin implements EmiPlugin {
    private static final Identifier BOSS_SPAWN = Identifier.of(RainimatorMod.MOD_ID, "boss_spawn_recipe");
    private static final EmiTexture TEXTURE = new EmiTexture(Identifier.of(RainimatorMod.MOD_ID, "textures/gui/gui_boss_spawn_recipe.png"), 0, 0, 140, 44);
    private static final EmiStack WORKSTATION = EmiStack.of(RainimatorBlocks.DARK_OBSIDIAN_BLOCK.get());
    private static final EmiRecipeCategory BOSS_SPAWN_CATEGORY = new EmiRecipeCategory(BOSS_SPAWN, WORKSTATION, TEXTURE);

    @Override
    public void register(EmiRegistry registry) {
        registry.addCategory(BOSS_SPAWN_CATEGORY);
        registry.addWorkstation(BOSS_SPAWN_CATEGORY, WORKSTATION);
        for (Map.Entry<Identifier, BossSpawnRecipeManager.Single> entry : BossSpawnRecipeManager.getAll())
            registry.addRecipe(new BossSpawnRecipe(entry.getKey(), entry.getValue().getItems(), entry.getValue().getFirstSpawnEgg()));
    }

    private record BossSpawnRecipe(Identifier id, List<Item> input, ItemConvertible output) implements EmiRecipe {
        @Override
        public EmiRecipeCategory getCategory() {
            return BOSS_SPAWN_CATEGORY;
        }

        @Override
        public Identifier getId() {
            return this.id;
        }

        @Override
        public List<EmiIngredient> getInputs() {
            return List.of(EmiIngredient.of(Ingredient.ofItems(this.input.toArray(ItemConvertible[]::new))));
        }

        @Override
        public List<EmiStack> getOutputs() {
            return List.of(EmiStack.of(this.output));
        }

        @Override
        public int getDisplayWidth() {
            return 140;
        }

        @Override
        public int getDisplayHeight() {
            return 44;
        }

        @Override
        public void addWidgets(WidgetHolder widgets) {
            widgets.addTexture(TEXTURE, 0, 0);
            widgets.addSlot(this.getInputs().get(0), 13, 12);
            widgets.addSlot(EmiIngredient.of(List.of(WORKSTATION)), 61, 12);
            widgets.addSlot(this.getOutputs().get(0), 109, 12).recipeContext(this);
        }
    }
}
