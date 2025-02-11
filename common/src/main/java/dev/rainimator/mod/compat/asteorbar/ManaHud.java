package dev.rainimator.mod.compat.asteorbar;

import com.afoxxvi.asteorbar.overlay.parts.SimpleBarOverlay;
import dev.rainimator.mod.data.component.ManaData;
import dev.rainimator.mod.impl.ComponentManager;
import dev.rainimator.mod.screen.InGameHudRenderHelper;
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
