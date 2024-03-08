package com.rainimator.rainimatormod.entity;

import com.rainimator.rainimatormod.registry.ModEntities;
import com.rainimator.rainimatormod.registry.ModItems;
import com.rainimator.rainimatormod.registry.ModParticleTypes;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.protocol.Packet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Random;

@OnlyIn(value = Dist.CLIENT, _interface = ItemSupplier.class)
public class EndSatffEntity extends AbstractArrow implements ItemSupplier {
    public EndSatffEntity(PlayMessages.SpawnEntity packet, Level world) {
        super(ModEntities.END_SATFF.get(), world);
    }

    public EndSatffEntity(EntityType<? extends EndSatffEntity> type, Level world) {
        super(type, world);
    }

    public EndSatffEntity(EntityType<? extends EndSatffEntity> type, double x, double y, double z, Level world) {
        super(type, x, y, z, world);
    }

    public EndSatffEntity(EntityType<? extends EndSatffEntity> type, LivingEntity entity, Level world) {
        super(type, entity, world);
    }

    @Override
    public @NotNull Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public @NotNull ItemStack getItem() {
        return new ItemStack(Items.FIRE_CHARGE);
    }

    @Override
    protected @NotNull ItemStack getPickupItem() {
        return new ItemStack(ModItems.MAGIC_STARD.get());
    }

    @Override
    protected void doPostHurtEffects(@NotNull LivingEntity entity) {
        super.doPostHurtEffects(entity);
        entity.setArrowCount(entity.getArrowCount() - 1);
    }

    @Override
    public void onHitEntity(@NotNull EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        doHit(this.level, getX(), getY(), getZ());
    }

    @Override
    public void onHitBlock(@NotNull BlockHitResult blockHitResult) {
        super.onHitBlock(blockHitResult);
        doHit(this.level, blockHitResult.getBlockPos().getX(), blockHitResult.getBlockPos().getY(), blockHitResult.getBlockPos().getZ());
    }

    public static void doHit(LevelAccessor world, double x, double y, double z) {
        if (world instanceof Level) { Level _level = (Level)world; if (!_level.isClientSide())
            _level.explode(null, x, y, z, 3.0F, Explosion.BlockInteraction.NONE);  }
        if (world instanceof ServerLevel) { ServerLevel _level = (ServerLevel)world;
            _level.sendParticles((ParticleOptions) ModParticleTypes.LIGHTINGARC.get(), x, y, z, 250, 0.5D, 1.0D, 0.5D, 0.5D); }

    }

    @Override
    public void tick() {
        super.tick();
        if (this.inGround)
            discard();
    }

    public static EndSatffEntity shoot(Level world, LivingEntity entity, Random random, float power, double damage, int knockback) {
        EndSatffEntity entityarrow = new EndSatffEntity(ModEntities.END_SATFF.get(), entity, world);
        entityarrow.shoot((entity.getViewVector(1.0F)).x, (entity.getViewVector(1.0F)).y, (entity.getViewVector(1.0F)).z, power * 2.0F, 0.0F);
        entityarrow.setSilent(true);
        entityarrow.setCritArrow(true);
        entityarrow.setBaseDamage(damage);
        entityarrow.setKnockback(knockback);
        entityarrow.setSecondsOnFire(100);
        world.addFreshEntity(entityarrow);
        world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.arrow.shoot"))), SoundSource.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.5F + 1.0F) + power / 2.0F);
        return entityarrow;
    }

    public static EndSatffEntity shoot(LivingEntity entity, LivingEntity target) {
        EndSatffEntity entityarrow = new EndSatffEntity(ModEntities.END_SATFF.get(), entity, entity.level);
        double dx = target.getX() - entity.getX();
        double dy = target.getY() + target.getEyeHeight() - 1.1D;
        double dz = target.getZ() - entity.getZ();
        entityarrow.shoot(dx, dy - entityarrow.getY() + Math.hypot(dx, dz) * 0.20000000298023224D, dz, 2.4F, 12.0F);
        entityarrow.setSilent(true);
        entityarrow.setBaseDamage(7.0D);
        entityarrow.setKnockback(0);
        entityarrow.setCritArrow(true);
        entityarrow.setSecondsOnFire(100);
        entity.level.addFreshEntity(entityarrow);
        entity.level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.arrow.shoot"))), SoundSource.PLAYERS, 1.0F, 1.0F / ((new Random()).nextFloat() * 0.5F + 1.0F));
        return entityarrow;
    }
}