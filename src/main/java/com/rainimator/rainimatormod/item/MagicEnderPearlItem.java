package com.rainimator.rainimatormod.item;

import com.rainimator.rainimatormod.registry.ModParticleTypes;
import com.rainimator.rainimatormod.registry.util.FoilItemBase;
import com.rainimator.rainimatormod.registry.util.ModCreativeTab;
import com.rainimator.rainimatormod.util.SoundUtil;
import com.rainimator.rainimatormod.util.Timeout;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Random;

public class MagicEnderPearlItem extends FoilItemBase {
    public MagicEnderPearlItem() {
        super(p -> p.tab(ModCreativeTab.items).durability(40).fireResistant().rarity(Rarity.RARE));
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level world, @NotNull Player entity, @NotNull InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
        ItemStack itemstack = ar.getObject();
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();

        if (!world.isClientSide())
            world.playSound(null, new BlockPos(x, y, z), Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.ender_pearl.throw"))), SoundSource.NEUTRAL, 1.0F, 1.0F);

        if (world instanceof ServerLevel _level)
            _level.sendParticles((ParticleOptions) ModParticleTypes.PURPLELIGHT.get(), x, y, z, 30, 0.5D, 1.0D, 0.5D, 0.02D);
        if (entity.isSprinting()) {
            Level projectileLevel = entity.level;
            if (!projectileLevel.isClientSide()) {
                Projectile _entityToSpawn = new ThrownEnderpearl(EntityType.ENDER_PEARL, projectileLevel);
                _entityToSpawn.setOwner(entity);
                _entityToSpawn.setPos(entity.getX(), entity.getEyeY() - 0.1D, entity.getZ());
                _entityToSpawn.shoot((entity.getLookAngle()).x, (entity.getLookAngle()).y, (entity.getLookAngle()).z, 2.0F, 0.0F);
                projectileLevel.addFreshEntity(_entityToSpawn);
            }

            entity.getCooldowns().addCooldown(itemstack.getItem(), 100);

            if (itemstack.hurt(1, new Random(), null)) {
                itemstack.shrink(1);
                itemstack.setDamageValue(0);
            }

        } else {
            Level projectileLevel = entity.level;
            if (!projectileLevel.isClientSide()) {
                Projectile _entityToSpawn = new ThrownEnderpearl(EntityType.ENDER_PEARL, projectileLevel);
                _entityToSpawn.setOwner(entity);
                _entityToSpawn.setPos(entity.getX(), entity.getEyeY() - 0.1D, entity.getZ());
                _entityToSpawn.shoot((entity.getLookAngle()).x, (entity.getLookAngle()).y, (entity.getLookAngle()).z, 1.5F, 0.0F);
                projectileLevel.addFreshEntity(_entityToSpawn);
            }

            if (entity instanceof Player)
                entity.getCooldowns().addCooldown(itemstack.getItem(), 80);

            if (itemstack.hurt(1, new Random(), null)) {
                itemstack.shrink(1);
                itemstack.setDamageValue(0);
            }
        }
        if (entity.isShiftKeyDown()) {

            Level projectileLevel = entity.level;
            if (!projectileLevel.isClientSide()) {
                Projectile _entityToSpawn = new ThrownEnderpearl(EntityType.ENDER_PEARL, projectileLevel);
                _entityToSpawn.setOwner(entity);
                _entityToSpawn.setPos(entity.getX(), entity.getEyeY() - 0.1D, entity.getZ());
                _entityToSpawn.shoot((entity.getLookAngle()).x, (entity.getLookAngle()).y, (entity.getLookAngle()).z, 1.5F, 0.0F);
                projectileLevel.addFreshEntity(_entityToSpawn);
            }

            entity.getCooldowns().addCooldown(itemstack.getItem(), 300);

            if (itemstack.hurt(1, new Random(), null)) {
                itemstack.shrink(1);
                itemstack.setDamageValue(0);
            }

            Timeout.create(200, () -> {
                entity.teleportTo(x, y, z);
                if ((Entity) entity instanceof ServerPlayer _serverPlayer)
                    _serverPlayer.connection.teleport(x, y, z, entity.getYRot(), entity.getXRot());
                SoundUtil.playSound(world, x, y, z, new ResourceLocation("entity.enderman.teleport"), 1.0F, 1.0F);
                if (world instanceof ServerLevel) {
                    ((ServerLevel) world).sendParticles((ParticleOptions) ModParticleTypes.PURPLELIGHT.get(), x, y, z, 50, 0.5D, 1.0D, 0.5D, 0.02D);
                }
            });
        }
        return ar;
    }
}
