package com.iafenvoy.rainimator.forge.compat.asteorbar;

import com.afoxxvi.asteorbar.overlay.parts.SimpleBarOverlay;
import com.iafenvoy.rainimator.data.ManaData;
import com.iafenvoy.rainimator.impl.ComponentManager;
import com.iafenvoy.rainimator.screen.InGameHudRenderHelper;
import net.minecraft.entity.player.PlayerEntity;

public class ManaHud extends SimpleBarOverlay {
    @Override
    protected Parameters getParameters(PlayerEntity playerEntity) {
        ManaData manaData = ComponentManager.getManaData(playerEntity);
        Parameters parameters = new Parameters();
        parameters.capacity = manaData.getMaxMana();
        parameters.value = manaData.getMana();
        parameters.fillColor = 0xFF00FFFF;
        parameters.boundColor = 0xFF004444;
        parameters.emptyColor = 0xFF444444;
        parameters.centerColor = 0xFFFFFF;
        parameters.centerText = InGameHudRenderHelper.getValueText(manaData.getMana(), manaData.getMaxMana());
        return parameters;
    }

    @Override
    protected boolean shouldRender(PlayerEntity playerEntity) {
        return true;
    }

    @Override
    protected boolean isLeftSide() {
        return true;
    }
}
