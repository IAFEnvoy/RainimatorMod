package dev.rainimator.mod.registry;

import dev.rainimator.mod.RainimatorMod;
import me.shedaniel.architectury.registry.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public class RainimatorItemGroups {
    public static final ItemGroup MAIN = CreativeTabs.create(
            new Identifier("itemGroup." + RainimatorMod.MOD_ID + ".rainimator"),
            () -> new ItemStack(RainimatorItems.RAIN_SWORD.get())
    );
    public static final List<Supplier<? extends Item>> ITEMS = new ArrayList<>();

    public static void init() {
        MAIN.appendStacks(DefaultedList.copyOf(ItemStack.EMPTY, ITEMS.stream().map(Supplier::get).map(ItemStack::new).toArray(x -> new ItemStack[]{})));
    }
}