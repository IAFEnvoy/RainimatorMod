package com.rainimator.rainimatormod.item.sword;

import com.rainimator.rainimatormod.registry.ModItems;
import com.rainimator.rainimatormod.registry.util.ModCreativeTab;
import com.rainimator.rainimatormod.registry.util.TierBase;
import com.rainimator.rainimatormod.util.MiscUtil;
import com.rainimator.rainimatormod.util.Timeout;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class FallensoulaxeItem extends SwordItem {
    public FallensoulaxeItem() {
        super(TierBase.of(1000, 0.0F, 6.0F, 0, 10), 3, -2.3F, ModCreativeTab.createProperty().fireResistant());
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack itemstack, @NotNull LivingEntity entity, @NotNull LivingEntity sourceentity) {
        boolean retval = super.hurtEnemy(itemstack, entity, sourceentity);
        if (!entity.level.isClientSide())
            entity.addEffect(new MobEffectInstance(MobEffects.WITHER, 80, 0));
        return retval;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level world, @NotNull Player entity, @NotNull InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
        final double x = entity.getX();
        final double y = entity.getY();
        final double z = entity.getZ();
        ItemStack itemstack = ar.getObject();
        Vec3 _center = new Vec3(x, y, z);
        List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, (new AABB(_center, _center)).inflate(5.0D), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
        for (Entity entityiterator : _entfound) {
            if (entity.isShiftKeyDown()) {
                LivingEntity _livEnt = (LivingEntity) entityiterator;
                if (((entityiterator instanceof LivingEntity) ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == ModItems.FALLENSOULAXE.get()) {
                    if (itemstack.hurt(0, new Random(), null)) {
                        itemstack.shrink(1);
                        itemstack.setDamageValue(0);
                    }
                    continue;
                }
                if (entityiterator instanceof LivingEntity) {
                    LivingEntity _entity = (LivingEntity) entityiterator;
                    if (!_entity.level.isClientSide())
                        _entity.addEffect(new MobEffectInstance(MobEffects.WITHER, 60, 0));
                }

                entityiterator.teleportTo(x, y + 1.0D, z);
                if (entityiterator instanceof ServerPlayer _serverPlayer)
                    _serverPlayer.connection.teleport(x, y + 1.0D, z, entityiterator.getYRot(), entityiterator.getXRot());

                if (entityiterator instanceof LivingEntity) {
                    LivingEntity _entity = (LivingEntity) entityiterator;
                    if (!_entity.level.isClientSide())
                        _entity.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 50, 4));
                }

                for (double i = 2; i <= 5; i++) {
                    double finalI = i;
                    Timeout.create((int) i * 2 - 2, () -> {
                        entityiterator.teleportTo(x, y + finalI, z);
                        if (entityiterator instanceof ServerPlayer _serverPlayer)
                            _serverPlayer.connection.teleport(x, y + finalI, z, entityiterator.getYRot(), entityiterator.getXRot());
                    });
                }
                MiscUtil.playSound(world, x, y, z, new ResourceLocation("entity.wither.spawn"), 10.0F, 1.0F);
                if (world instanceof ServerLevel _level)
                    _level.sendParticles((ParticleOptions) ParticleTypes.SOUL, x, y, z, 100, 3.0D, 4.0D, 3.0D, 1.0E-4D);
                entity.getCooldowns().addCooldown(itemstack.getItem(), 600);
            }
        }
        return ar;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public boolean isFoil(@NotNull ItemStack itemstack) {
        return true;
    }
}
