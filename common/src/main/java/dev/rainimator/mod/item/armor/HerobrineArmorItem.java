package dev.rainimator.mod.item.armor;

import com.iafenvoy.neptune.object.item.ArmorMaterialUtil;
import dev.rainimator.mod.registry.RainimatorItemGroups;
import dev.rainimator.mod.registry.RainimatorItems;
import net.minecraft.item.ArmorItem;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class HerobrineArmorItem extends ArmorItem {
    public HerobrineArmorItem(Type slot) {
        super(ArmorMaterialUtil.of("herobrine_armors", new int[]{13, 15, 16, 11}, 20, new int[]{0, 0, 20, 0}, 17, Registries.SOUND_EVENT.get(Identifier.tryParse("item.armor.equip_diamond")), 5.0F, 0.0F, RainimatorItems.SUPER_RUBY::get, RainimatorItems.HEROBRINE_CHESTPLATE::get), slot, new Settings().arch$tab(RainimatorItemGroups.MAIN));
    }
}
