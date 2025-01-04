package dev.rainimator.mod.entity;

import com.iafenvoy.neptune.object.SoundUtil;
import dev.rainimator.mod.registry.RainimatorEntities;
import dev.rainimator.mod.registry.RainimatorItems;
import dev.rainimator.mod.registry.RainimatorParticles;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

import java.util.Random;

public class EndStaffEntity extends PersistentProjectileEntity implements FlyingItemEntity {
    public EndStaffEntity(EntityType<? extends EndStaffEntity> type, World world) {
        super(type, world);
    }

    public EndStaffEntity(EntityType<? extends EndStaffEntity> type, LivingEntity entity, World world) {
        super(type, entity, world);
    }

    public static void doHit(WorldAccess world, double x, double y, double z) {
        if (world instanceof World _level)
            if (!_level.isClient())
                _level.createExplosion(null, x, y, z, 3.0F, World.ExplosionSourceType.NONE);
        if (world instanceof ServerWorld _level)
            _level.spawnParticles(RainimatorParticles.LIGHTENING_ARC.get(), x, y, z, 250, 0.5D, 1.0D, 0.5D, 0.5D);
    }

    public static EndStaffEntity shoot(World world, LivingEntity entity, Random random, float power, double damage, int knockback) {
        EndStaffEntity entityarrow = new EndStaffEntity(RainimatorEntities.END_STAFF.get(), entity, world);
        entityarrow.setVelocity((entity.getRotationVec(1.0F)).x, (entity.getRotationVec(1.0F)).y, (entity.getRotationVec(1.0F)).z, power * 2.0F, 0.0F);
        entityarrow.setSilent(true);
        entityarrow.setCritical(true);
        entityarrow.setDamage(damage);
        entityarrow.setPunch(knockback);
        entityarrow.setOnFireFor(100);
        world.spawnEntity(entityarrow);
        SoundUtil.playPlayerSound(world, entity.getX(), entity.getY(), entity.getZ(), Identifier.tryParse("entity.arrow.shoot"), 1.0F, 1.0F / (random.nextFloat() * 0.5F + 1.0F) + power / 2.0F);
        return entityarrow;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public ItemStack getStack() {
        return new ItemStack(Items.FIRE_CHARGE);
    }

    @Override
    protected ItemStack asItemStack() {
        return new ItemStack(RainimatorItems.MAGIC_STAR.get());
    }

    @Override
    protected void onHit(LivingEntity entity) {
        super.onHit(entity);
        entity.setStuckArrowCount(entity.getStuckArrowCount() - 1);
    }

    @Override
    public void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        doHit(this.getWorld(), this.getX(), this.getY(), this.getZ());
    }

    @Override
    public void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        doHit(this.getWorld(), blockHitResult.getBlockPos().getX(), blockHitResult.getBlockPos().getY(), blockHitResult.getBlockPos().getZ());
    }

    @Override
    public void tick() {
        super.tick();
        if (this.inGround)
            this.discard();
    }
}