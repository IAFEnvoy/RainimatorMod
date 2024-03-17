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
    public static final String MOD_NAME = "Rainimator";
    public static final String CURIOS_MOD_ID = "curios";
    private static final Logger LOGGER = LogUtils.getLogger();

    public RainimatorMod() {
        if (ModList.get().isLoaded("annoying_villagersbychentu")) {
            LOGGER.error("[annoying_villagersbychentu] failed to load");
            System.exit(1);
        }
        ModCreativeTab.load();
        RegistryManager.register(FMLJavaModLoadingContext.get().getModEventBus());
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
    }

    //curios api compact
    private void enqueueIMC(InterModEnqueueEvent event) {
        if (!ModList.get().isLoaded(CURIOS_MOD_ID)) return;
        InterModComms.sendTo(CURIOS_MOD_ID, SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.HEAD.getMessageBuilder().size(1).build());
        InterModComms.sendTo(CURIOS_MOD_ID, SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.BACK.getMessageBuilder().size(1).build());
        InterModComms.sendTo(CURIOS_MOD_ID, SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.BELT.getMessageBuilder().size(2).build());
    }
}
