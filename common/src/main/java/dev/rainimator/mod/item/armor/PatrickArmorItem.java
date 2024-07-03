package dev.rainimator.mod.item.armor;

import dev.rainimator.mod.data.config.ServerConfig;
import dev.rainimator.mod.registry.RainimatorItems;
import dev.rainimator.mod.registry.util.ArmorMaterialUtil;
import dev.rainimator.mod.registry.util.ArmorWithTickItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class PatrickArmorItem extends ArmorWithTickItem {
    public PatrickArmorItem(EquipmentSlot slot) {
        super(ArmorMaterialUtil.of("patrick_armor", new int[]{13, 15, 16, 11}, 25, new int[]{3, 8, 12, 5}, 20, Registry.SOUND_EVENT.get(Identifier.tryParse("item.armor.equip_diamond")), 3.0F, 0.0F), slot, new Settings());
    }

    @Override
    public void onArmorTick(World world, PlayerEntity entity) {
        if (entity == null) return;
        if (!entity.world.isClient() && ServerConfig.getInstance().enableArmorEffect &&
                entity.getEquippedStack(EquipmentSlot.HEAD).getItem() == RainimatorItems.PATRICK_HELMET.get() &&
                entity.getEquippedStack(EquipmentSlot.CHEST).getItem() == RainimatorItems.PATRICK_CHESTPLATE.get() &&
                entity.getEquippedStack(EquipmentSlot.LEGS).getItem() == RainimatorItems.PATRICK_LEGGINGS.get() &&
                entity.getEquippedStack(EquipmentSlot.FEET).getItem() == RainimatorItems.PATRICK_BOOTS.get()) {
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 80, 1));
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 80, 1));
        }
    }
}
