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
public class PatrickEntityProjectile
        extends AbstractArrow implements ItemSupplier {
    public PatrickEntityProjectile(PlayMessages.SpawnEntity packet, Level world) {
        super(ModEntities.PATRICK_PROJECTILE.get(), world);
    }

    public PatrickEntityProjectile(EntityType<? extends PatrickEntityProjectile> type, Level world) {
        super(type, world);
    }

    public PatrickEntityProjectile(EntityType<? extends PatrickEntityProjectile> type, LivingEntity entity, Level world) {
        super(type, entity, world);
    }

    @Override
    public @NotNull Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void doPostHurtEffects(@NotNull LivingEntity livingEntity) {
        super.doPostHurtEffects(livingEntity);
        livingEntity.setArrowCount(livingEntity.getArrowCount() - 1);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public @NotNull ItemStack getItem() {
        return new ItemStack(Items.ENDER_PEARL);
    }

    @Override
    protected @NotNull ItemStack getPickupItem() {
        return new ItemStack(Items.ENDER_PEARL);
    }
}