package com.rainimator.rainimatormod.entity;

import com.rainimator.rainimatormod.registry.ModEntities;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;
import org.jetbrains.annotations.NotNull;

@OnlyIn(value = Dist.CLIENT, _interface = ItemSupplier.class)
public class DaryllEntityProjectile
        extends AbstractArrow implements ItemSupplier {
    public DaryllEntityProjectile(PlayMessages.SpawnEntity packet, Level world) {
        super(ModEntities.DARYLL_PROJECTILE.get(), world);
    }

    public DaryllEntityProjectile(EntityType<? extends DaryllEntityProjectile> type, Level world) {
        super(type, world);
    }

    public DaryllEntityProjectile(EntityType<? extends DaryllEntityProjectile> type, double x, double y, double z, Level world) {
        super(type, x, y, z, world);
    }

    public DaryllEntityProjectile(EntityType<? extends DaryllEntityProjectile> type, LivingEntity entity, Level world) {
        super(type, entity, world);
    }

    @Override
    public @NotNull Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket( this);
    }

    @Override
    protected void doPostHurtEffects(@NotNull LivingEntity livingEntity) {
        super.doPostHurtEffects(livingEntity);
        livingEntity.setArrowCount(livingEntity.getArrowCount() - 1);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public @NotNull ItemStack getItem() {
        return new ItemStack( Items.ENCHANTED_GOLDEN_APPLE);
    }

    @Override
    protected @NotNull ItemStack getPickupItem() {
        return new ItemStack( Items.ENCHANTED_GOLDEN_APPLE);
    }
}