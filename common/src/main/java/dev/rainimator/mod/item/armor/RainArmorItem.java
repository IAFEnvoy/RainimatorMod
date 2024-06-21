package dev.rainimator.mod.item.armor;

import dev.rainimator.mod.item.util.ArmorMaterialUtil;
import dev.rainimator.mod.registry.RainimatorItemGroups;
import dev.rainimator.mod.registry.RainimatorItems;
import net.minecraft.item.ArmorItem;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class RainArmorItem extends ArmorItem {
    public RainArmorItem(Type slot) {
        super(ArmorMaterialUtil.of("rain_armors", new int[]{13, 15, 16, 11}, 20, new int[]{0, 0, 20, 0}, 30, Registries.SOUND_EVENT.get(Identifier.tryParse("item.armor.equip_diamond")), 0.0F, 0.0F, RainimatorItems.SUPER_SAPPHIRE, RainimatorItems.RAIN_CHESTPLATE), slot, new Settings().arch$tab(RainimatorItemGroups.MAIN));
    }
}