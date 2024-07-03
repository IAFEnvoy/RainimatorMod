package dev.rainimator.mod.item.armor;

import dev.rainimator.mod.registry.RainimatorItemGroups;
import dev.rainimator.mod.registry.RainimatorItems;
import dev.rainimator.mod.registry.util.ArmorMaterialUtil;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class HerobrineArmorItem extends ArmorItem {
    public HerobrineArmorItem(EquipmentSlot slot) {
        super(ArmorMaterialUtil.of("herobrine_armors", new int[]{13, 15, 16, 11}, 20, new int[]{0, 0, 20, 0}, 17, Registry.SOUND_EVENT.get(Identifier.tryParse("item.armor.equip_diamond")), 5.0F, 0.0F, RainimatorItems.HEROBRINE_CHESTPLATE), slot, new Settings().group(RainimatorItemGroups.MAIN));
    }
}
