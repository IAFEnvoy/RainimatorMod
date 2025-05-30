package com.iafenvoy.rainimator.item.armor;

import com.iafenvoy.neptune.object.item.ArmorMaterialUtil;
import com.iafenvoy.rainimator.registry.RainimatorItemGroups;
import com.iafenvoy.rainimator.registry.RainimatorItems;
import net.minecraft.item.ArmorItem;
import net.minecraft.sound.SoundEvents;

public class HerobrineArmorItem extends ArmorItem {
    public HerobrineArmorItem(Type slot) {
        super(ArmorMaterialUtil.of("herobrine_armors", new int[]{13, 15, 16, 11}, 20, new int[]{0, 0, 20, 0}, 17, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 5.0F, 0.0F, RainimatorItems.SUPER_RUBY::get, RainimatorItems.HEROBRINE_CHESTPLATE::get), slot, new Settings().arch$tab(RainimatorItemGroups.MAIN));
    }
}
