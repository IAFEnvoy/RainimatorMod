package dev.rainimator.mod.fabric.compat.emi;

import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import dev.rainimator.mod.RainimatorMod;
import dev.rainimator.mod.registry.RainimatorBlocks;
import dev.rainimator.mod.registry.RainimatorItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Environment(EnvType.CLIENT)
public class BossSpawnRecipePlugin implements EmiPlugin {
    private static final Identifier BOSS_SPAWN = new Identifier(RainimatorMod.MOD_ID, "boss_spawn_recipe");
    private static final EmiTexture TEXTURE = new EmiTexture(new Identifier(RainimatorMod.MOD_ID, "textures/gui/gui_boss_spawn_recipe.png"), 0, 0, 140, 44);
    private static final EmiStack WORKSTATION = EmiStack.of(RainimatorBlocks.DARK_OBSIDIAN_BLOCK.get());
    private static final EmiRecipeCategory BOSS_SPAWN_CATEGORY = new EmiRecipeCategory(BOSS_SPAWN, WORKSTATION, TEXTURE);
    private static final List<BossSpawnRecipe> RECIPES = List.of(
            new BossSpawnRecipe("herobrine", RainimatorItems.LIGHT_HEART.get(), RainimatorItems.HEROBRINE_SPAWN_EGG.get()),
            new BossSpawnRecipe("kralos", Blocks.WITHER_ROSE, RainimatorItems.KRALOS_SPAWN_EGG.get()),
            new BossSpawnRecipe("klaus", Items.TOTEM_OF_UNDYING, RainimatorItems.KLAUS_SPAWN_EGG.get()),
            new BossSpawnRecipe("gigabone", Blocks.WITHER_SKELETON_SKULL, RainimatorItems.GIGABONE_SPAWN_EGG.get()),
            new BossSpawnRecipe("namtar", RainimatorItems.SOUL_PEOPLE.get(), RainimatorItems.NAMTAR_SPAWN_EGG.get()),
            new BossSpawnRecipe("big_undead_skeleton", RainimatorItems.WITHER_BONE.get(), RainimatorItems.BIG_UNDEAD_SKELETON_SPAWN_EGG.get()),
            new BossSpawnRecipe("piglin_commander", Items.GOLDEN_SWORD, RainimatorItems.PIGLIN_COMMANDER_SPAWN_EGG.get()),
            new BossSpawnRecipe("zombie_piglin_king", Items.GOLD_INGOT, RainimatorItems.ZOMBIES_PIGLIN_KING_SPAWN_EGG.get()),
            new BossSpawnRecipe("piglin_king_zombies", Blocks.GOLD_BLOCK, RainimatorItems.PIGLIN_KING_ZOMBIES_SPAWN_EGG.get()),
            new BossSpawnRecipe("null_like", RainimatorItems.BAO_ZHU.get(), RainimatorItems.NULL_LIKE_SPAWN_EGG.get()),
            new BossSpawnRecipe("naeus", RainimatorItems.WARRIOR_HEART.get(), RainimatorItems.NAEUS_SPAWN_EGG.get()),
            new BossSpawnRecipe("ciara", RainimatorItems.ICE_HEART.get(), RainimatorItems.CIARA_SPAWN_EGG.get()),
            new BossSpawnRecipe("ceris", RainimatorItems.ENDER_HEART.get(), RainimatorItems.CERIS_SPAWN_EGG.get()),
            new BossSpawnRecipe("patrick", RainimatorItems.MAGIC_STAR.get(), RainimatorItems.PATRICK_SPAWN_EGG.get()),
            new BossSpawnRecipe("blackbone", RainimatorItems.NETHERITE_WITHER_BONE.get(), RainimatorItems.BLACKBONE_SPAWN_EGG.get()),
            new BossSpawnRecipe("abigail", RainimatorItems.UNDER_FLOWER.get(), RainimatorItems.ABIGAIL_SPAWN_EGG.get())
    );

    @Override
    public void register(EmiRegistry registry) {
        registry.addCategory(BOSS_SPAWN_CATEGORY);
        registry.addWorkstation(BOSS_SPAWN_CATEGORY, WORKSTATION);
        for (BossSpawnRecipe recipe : RECIPES)
            registry.addRecipe(recipe);
    }

    private record BossSpawnRecipe(String id, ItemConvertible input, ItemConvertible output) implements EmiRecipe {
        @Override
        public EmiRecipeCategory getCategory() {
            return BOSS_SPAWN_CATEGORY;
        }

        @Override
        public @NotNull Identifier getId() {
            return new Identifier(RainimatorMod.MOD_ID, this.id);
        }

        @Override
        public List<EmiIngredient> getInputs() {
            return List.of(EmiIngredient.of(Ingredient.ofItems(this.input)));
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
