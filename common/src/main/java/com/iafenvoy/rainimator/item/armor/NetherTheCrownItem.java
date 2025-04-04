package com.iafenvoy.rainimator.item.armor;

import com.iafenvoy.neptune.accessory.Accessory;
import com.iafenvoy.rainimator.entity.WitheredSkeletonsEntity;
import com.iafenvoy.rainimator.registry.RainimatorItemGroups;
import com.iafenvoy.rainimator.registry.RainimatorItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.*;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.util.Comparator;
import java.util.List;

public class NetherTheCrownItem extends DecoratingArmorItem implements Accessory {
    public NetherTheCrownItem() {
        super(ArmorItem.Type.HELMET, new Item.Settings().arch$tab(RainimatorItemGroups.MAIN));
    }

    @Override
    public void tick(ItemStack stack, LivingEntity entity) {
        if (entity == null) return;
        Vec3d _center = new Vec3d(entity.getX(), entity.getY(), entity.getZ());
        List<Entity> _entfound = entity.getWorld().getEntitiesByClass(Entity.class, (new Box(_center, _center)).expand(16.0D), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.squaredDistanceTo(_center))).toList();
        for (Entity entityiterator : _entfound) {
            if (entity.getEquippedStack(EquipmentSlot.HEAD).getItem() == RainimatorItems.NETHER_THE_CROWN.get()) {
                if (entityiterator instanceof WitheredSkeletonsEntity _entity) {
                    _entity.getNavigation().stop();
                    continue;
                }
                if (entityiterator instanceof WitherSkeletonEntity _entity) {
                    _entity.getNavigation().stop();
                    continue;
                }
                if (entityiterator instanceof PiglinEntity _entity) {
                    _entity.getNavigation().stop();
                    continue;
                }
                if (entityiterator instanceof PiglinBruteEntity _entity) {
                    _entity.getNavigation().stop();
                    continue;
                }
                if (entityiterator instanceof ZombifiedPiglinEntity _entity) {
                    _entity.getNavigation().stop();
                    continue;
                }
                if (entityiterator instanceof ZoglinEntity _entity) {
                    _entity.getNavigation().stop();
                }
            }
        }
    }
}
