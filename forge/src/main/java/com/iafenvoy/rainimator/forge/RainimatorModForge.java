package com.iafenvoy.rainimator.forge;

import com.iafenvoy.neptune.forge.component.CapabilitySyncHelper;
import com.iafenvoy.rainimator.RainimatorMod;
import com.iafenvoy.rainimator.RainimatorModClient;
import com.iafenvoy.rainimator.forge.component.ManaDataProvider;
import com.iafenvoy.rainimator.registry.RainimatorItems;
import com.iafenvoy.rainimator.registry.RainimatorPotions;
import dev.architectury.platform.Platform;
import dev.architectury.platform.forge.EventBuses;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(RainimatorMod.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RainimatorModForge {
    public RainimatorModForge() {
        EventBuses.registerModEventBus(RainimatorMod.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        RainimatorMod.init();
        CapabilitySyncHelper.register(Identifier.of(RainimatorMod.MOD_ID, "mana"), ManaDataProvider.CAPABILITY, ManaDataProvider::new);
        if (Platform.getEnv() == Dist.CLIENT)
            RainimatorModClient.initClient();
    }

    @SubscribeEvent
    public static void onInit(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            RainimatorMod.process();
            BrewingRecipeRegistry.addRecipe(Ingredient.ofStacks(PotionUtil.setPotion(Items.POTION.getDefaultStack(), Potions.AWKWARD)),
                    Ingredient.ofItems(RainimatorItems.BLUE_DIAMOND.get()),
                    PotionUtil.setPotion(Items.POTION.getDefaultStack(), RainimatorPotions.PURIFICATION_POTION.get()));
            BrewingRecipeRegistry.addRecipe(Ingredient.ofStacks(PotionUtil.setPotion(Items.POTION.getDefaultStack(), RainimatorPotions.PURIFICATION_POTION.get())),
                    Ingredient.ofItems(Items.GUNPOWDER),
                    PotionUtil.setPotion(Items.SPLASH_POTION.getDefaultStack(), RainimatorPotions.PURIFICATION_POTION.get()));
            BrewingRecipeRegistry.addRecipe(Ingredient.ofStacks(PotionUtil.setPotion(Items.SPLASH_POTION.getDefaultStack(), RainimatorPotions.PURIFICATION_POTION.get())),
                    Ingredient.ofItems(Items.GUNPOWDER),
                    PotionUtil.setPotion(Items.LINGERING_POTION.getDefaultStack(), RainimatorPotions.PURIFICATION_POTION.get()));
        });
    }
}