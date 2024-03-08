package com.rainimator.rainimatormod.registry.util;

import com.rainimator.rainimatormod.RainimatorMod;
import com.rainimator.rainimatormod.registry.ModItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class ModCreativeTab extends CreativeModeTab {
    public static final ModCreativeTab main = new ModCreativeTab(RainimatorMod.MOD_ID, ModItems.WARRIORHEART);
    public static final ModCreativeTab mobs = new ModCreativeTab(RainimatorMod.MOD_ID + "_mobs", ModItems.ICEHEART);
    public static final ModCreativeTab items = new ModCreativeTab(RainimatorMod.MOD_ID + "_items", ModItems.ENDERHEART);

    private final Supplier<Item> icon;

    private ModCreativeTab(String label, Supplier<Item> icon) {
        super(label);
        this.icon = icon;
    }

    @Override
    public @NotNull ItemStack makeIcon() {
        return new ItemStack(this.icon.get());
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public boolean hasSearchBar() {
        return false;
    }

    public static Item.Properties createProperty() {
        return createProperty(ModCreativeTab.main);
    }

    public static Item.Properties createProperty(String name) {
        return switch (name) {
            case "mobs" -> createProperty(ModCreativeTab.mobs);
            case "items" -> createProperty(ModCreativeTab.items);
            default -> createProperty(ModCreativeTab.main);
        };
    }

    public static Item.Properties createProperty(ModCreativeTab tab) {
        return new Item.Properties().tab(tab);
    }

    public static void load() {
    }
}
