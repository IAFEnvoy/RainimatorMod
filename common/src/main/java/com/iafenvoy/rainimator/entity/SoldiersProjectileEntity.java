package com.iafenvoy.rainimator.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class SoldiersProjectileEntity extends PersistentProjectileEntity implements FlyingItemEntity {
    public SoldiersProjectileEntity(EntityType<? extends SoldiersProjectileEntity> type, World world) {
        super(type, world);
    }

    public SoldiersProjectileEntity(EntityType<? extends SoldiersProjectileEntity> type, LivingEntity entity, World world) {
        super(type, entity, world);
    }

    @Override
    protected void onHit(LivingEntity livingEntity) {
        super.onHit(livingEntity);
        livingEntity.setStuckArrowCount(livingEntity.getStuckArrowCount() - 1);
        this.remove(RemovalReason.DISCARDED);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.inGround) this.remove(RemovalReason.DISCARDED);
    }

    @Override
    public ItemStack getStack() {
        return new ItemStack(Items.ENDER_PEARL);
    }

    @Override
    protected ItemStack asItemStack() {
        return new ItemStack(Items.ENDER_PEARL);
    }
}