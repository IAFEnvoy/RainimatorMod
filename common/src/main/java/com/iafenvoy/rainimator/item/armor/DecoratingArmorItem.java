package com.iafenvoy.rainimator.item.armor;

import com.iafenvoy.neptune.object.item.ArmorMaterialUtil;
import net.minecraft.item.ArmorItem;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class DecoratingArmorItem extends ArmorItem {
    public DecoratingArmorItem(Type type, Settings settings) {
        super(ArmorMaterialUtil.of("decorating", new int[]{1, 1, 1, 1}, 1000, new int[]{0, 0, 0, 0}, 0, Registries.SOUND_EVENT.get(Identifier.tryParse("item.armor.equip_diamond")), 0, 0), type, settings);
    }
}
