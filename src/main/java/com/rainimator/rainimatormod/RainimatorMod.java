package com.rainimator.rainimatormod;

import com.mojang.logging.LogUtils;
import com.rainimator.rainimatormod.registry.RegistryManager;
import com.rainimator.rainimatormod.registry.util.ModCreativeTab;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;

@Mod(RainimatorMod.MOD_ID)
public class RainimatorMod {
    public static final String MOD_ID = "rainimator";
    private static final Logger LOGGER = LogUtils.getLogger();

    public RainimatorMod() {
        ModCreativeTab.load();
        RegistryManager.register(FMLJavaModLoadingContext.get().getModEventBus());
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
    }

    //curios api compact
    private void enqueueIMC(final InterModEnqueueEvent event) {
        if (!ModList.get().isLoaded("curios")) return;
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.HEAD.getMessageBuilder().build());
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.BACK.getMessageBuilder().build());
    }
}
