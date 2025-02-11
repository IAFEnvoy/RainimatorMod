package com.iafenvoy.rainimator.item;

import com.iafenvoy.neptune.object.SoundUtil;
import com.iafenvoy.neptune.object.item.FoilItemBase;
import com.iafenvoy.neptune.util.Timeout;
import com.iafenvoy.rainimator.registry.RainimatorItemGroups;
import com.iafenvoy.rainimator.registry.RainimatorParticles;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class MagicEnderPearlItem extends FoilItemBase {
    public MagicEnderPearlItem() {
        super(p -> p.maxDamage(40).fireproof().rarity(Rarity.RARE).arch$tab(RainimatorItemGroups.ITEM));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity entity, Hand hand) {
        TypedActionResult<ItemStack> ar = super.use(world, entity, hand);
        ItemStack itemtack = ar.getValue();
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();

        SoundUtil.playSound(world, x, y, z, SoundEvents.ENTITY_ENDER_PEARL_THROW, 1, 1);

        if (world instanceof ServerWorld _level)
            _level.spawnParticles(RainimatorParticles.PURPLE_LIGHT.get(), x, y, z, 30, 0.5D, 1.0D, 0.5D, 0.02D);
        if (entity.isSprinting()) {
            World projectileLevel = entity.getWorld();
            if (!projectileLevel.isClient) {
                ProjectileEntity _entityToSpawn = new EnderPearlEntity(EntityType.ENDER_PEARL, projectileLevel);
                _entityToSpawn.setOwner(entity);
                _entityToSpawn.setPosition(entity.getX(), entity.getEyeY() - 0.1D, entity.getZ());
                _entityToSpawn.setVelocity((entity.getRotationVector()).x, (entity.getRotationVector()).y, (entity.getRotationVector()).z, 2.0F, 0.0F);
                projectileLevel.spawnEntity(_entityToSpawn);
            }

            entity.getItemCooldownManager().set(itemtack.getItem(), 100);

            if (itemtack.damage(1, entity.getRandom(), null)) {
                itemtack.decrement(1);
                itemtack.setDamage(0);
            }

        } else {
            World projectileLevel = entity.getWorld();
            if (!projectileLevel.isClient) {
                ProjectileEntity _entityToSpawn = new EnderPearlEntity(EntityType.ENDER_PEARL, projectileLevel);
                _entityToSpawn.setOwner(entity);
                _entityToSpawn.setPosition(entity.getX(), entity.getEyeY() - 0.1D, entity.getZ());
                _entityToSpawn.setVelocity((entity.getRotationVector()).x, (entity.getRotationVector()).y, (entity.getRotationVector()).z, 1.5F, 0.0F);
                projectileLevel.spawnEntity(_entityToSpawn);
            }

            if (entity instanceof PlayerEntity)
                entity.getItemCooldownManager().set(itemtack.getItem(), 80);

            if (itemtack.damage(1, entity.getRandom(), null)) {
                itemtack.decrement(1);
                itemtack.setDamage(0);
            }
        }
        if (entity.isSneaking()) {

            World projectileLevel = entity.getWorld();
            if (!projectileLevel.isClient) {
                ProjectileEntity _entityToSpawn = new EnderPearlEntity(EntityType.ENDER_PEARL, projectileLevel);
                _entityToSpawn.setOwner(entity);
                _entityToSpawn.setPosition(entity.getX(), entity.getEyeY() - 0.1D, entity.getZ());
                _entityToSpawn.setVelocity((entity.getRotationVector()).x, (entity.getRotationVector()).y, (entity.getRotationVector()).z, 1.5F, 0.0F);
                projectileLevel.spawnEntity(_entityToSpawn);
            }

            entity.getItemCooldownManager().set(itemtack.getItem(), 300);

            if (itemtack.damage(1, entity.getRandom(), null)) {
                itemtack.decrement(1);
                itemtack.setDamage(0);
            }

            Timeout.create(200, () -> {
                entity.requestTeleport(x, y, z);
                if ((Entity) entity instanceof ServerPlayerEntity _serverPlayer)
                    _serverPlayer.networkHandler.requestTeleport(x, y, z, entity.getYaw(), entity.getPitch());
                SoundUtil.playSound(world, x, y, z, SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
                if (world instanceof ServerWorld) {
                    ((ServerWorld) world).spawnParticles(RainimatorParticles.PURPLE_LIGHT.get(), x, y, z, 50, 0.5D, 1.0D, 0.5D, 0.02D);
                }
            });
        }
        return ar;
    }
}
