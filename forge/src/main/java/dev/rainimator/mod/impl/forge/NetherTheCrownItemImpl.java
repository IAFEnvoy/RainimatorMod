package dev.rainimator.mod.impl.forge;

import dev.rainimator.mod.entity.WitheredSkeletonsEntity;
import dev.rainimator.mod.impl.NetherTheCrownItem;
import dev.rainimator.mod.registry.RainimatorItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.Comparator;
import java.util.List;

public class NetherTheCrownItemImpl extends NetherTheCrownItem implements ICurioItem {
    public NetherTheCrownItemImpl() {
        super();
    }

    public static NetherTheCrownItem create() {
        return new NetherTheCrownItemImpl();
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        ICurioItem.super.curioTick(slotContext, stack);
        LivingEntity entity = slotContext.entity();
        if (entity == null) return;

        Vec3d _center = new Vec3d(entity.getX(), entity.getY(), entity.getZ());
        List<Entity> _entfound = entity.getWorld().getEntitiesByClass(Entity.class, (new Box(_center, _center)).expand(16.0D), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.squaredDistanceTo(_center))).toList();
        for (Entity entityiterator : _entfound) {
            if (entity.getEquippedStack(EquipmentSlot.HEAD).getItem() == RainimatorItems.NETHER_THE_CROWN.get()) {
                if (entityiterator instanceof WitheredSkeletonsEntity) {
                    MobEntity _entity = (MobEntity) entityiterator;
                    _entity.getNavigation().stop();
                    continue;
                }
                if (entityiterator instanceof WitherSkeletonEntity) {
                    MobEntity _entity = (MobEntity) entityiterator;
                    _entity.getNavigation().stop();
                    continue;
                }
                if (entityiterator instanceof PiglinEntity) {
                    MobEntity _entity = (MobEntity) entityiterator;
                    _entity.getNavigation().stop();
                    continue;
                }
                if (entityiterator instanceof PiglinBruteEntity) {
                    MobEntity _entity = (MobEntity) entityiterator;
                    _entity.getNavigation().stop();
                    continue;
                }
                if (entityiterator instanceof ZombifiedPiglinEntity) {
                    MobEntity _entity = (MobEntity) entityiterator;
                    _entity.getNavigation().stop();
                    continue;
                }
                if (entityiterator instanceof ZoglinEntity) {
                    MobEntity _entity = (MobEntity) entityiterator;
                    _entity.getNavigation().stop();
                }
            }
        }
    }
}
