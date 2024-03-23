package com.rainimator.rainimatormod.item;

import com.rainimator.rainimatormod.registry.ModItems;
import com.rainimator.rainimatormod.registry.util.FoilItemBase;
import com.rainimator.rainimatormod.registry.util.ModCreativeTab;
import com.rainimator.rainimatormod.util.SoundUtil;
import com.rainimator.rainimatormod.util.Timeout;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public class SoulTotemItem extends FoilItemBase {
    public SoulTotemItem() {
        super(p -> p.tab(ModCreativeTab.items).stacksTo(1).rarity(Rarity.UNCOMMON));
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level world, @NotNull Player entity, @NotNull InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
        ItemStack itemstack = ar.getObject();
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();

        if (entity.isOnGround()) {
            if (world.isClientSide())
                (Minecraft.getInstance()).gameRenderer.displayItemActivation(itemstack);
            SoundUtil.playSound(world, x, y, z, new ResourceLocation("block.anvil.land"), 5.0F, 1.0F);
            if (world instanceof ServerLevel _level)
                _level.sendParticles((ParticleOptions) ParticleTypes.END_ROD, x, y, z, 100, 3.0D, 4.0D, 3.0D, 0.002D);
            if (!entity.level.isClientSide())
                entity.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 140, 4));
            entity.getCooldowns().addCooldown(itemstack.getItem(), 400);

            Timeout.create(100, () -> {
                if (!entity.level.isClientSide())
                    entity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 300, 1));
            });
        } else {
            if (((LevelAccessor) world).isClientSide())
                (Minecraft.getInstance()).gameRenderer.displayItemActivation(itemstack);
            SoundUtil.playSound(world, x, y, z, new ResourceLocation("block.anvil.land"), 5.0F, 1.0F);
            if (world instanceof ServerLevel _level)
                _level.sendParticles((ParticleOptions) ParticleTypes.END_ROD, x, y, z, 100, 3.0D, 4.0D, 3.0D, 0.002D);
            if (!entity.level.isClientSide())
                entity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 400, 0));
            entity.getCooldowns().addCooldown(itemstack.getItem(), 600);
        }
        return ar;
    }

    @Override
    public void inventoryTick(@NotNull ItemStack itemstack, @NotNull Level world, @NotNull Entity entity, int slot, boolean selected) {
        super.inventoryTick(itemstack, world, entity, slot, selected);
        if (selected) {
            Vec3 _center = new Vec3(entity.getX(), entity.getY(), entity.getZ());
            List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(5.0d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
            for (Entity entity1 : _entfound) {
                if ((LevelAccessor) world instanceof Level _lvl) {
                    if (_lvl.isDay()) {
                        if (entity1 instanceof Player _entity) {
                            if (!_entity.level.isClientSide())
                                _entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100, 0));
                        } else if (entity1 instanceof Monster _entity2)
                            if (!_entity2.level.isClientSide())
                                _entity2.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 0));
                    }
                }
            }
        }
        if ((entity instanceof Player _playerHasItem && _playerHasItem.getInventory().contains(new ItemStack(ModItems.SOUL_TOTEM.get())))) {
            if (world.isDay()) {
                LivingEntity _entity = (LivingEntity) entity;
                if (!_entity.level.isClientSide())
                    _entity.addEffect(new MobEffectInstance(MobEffects.SATURATION, 100, 1));
                if (Math.random() < 0.005)
                    if (!world.isClientSide())
                        world.addFreshEntity(new ExperienceOrb(world, entity.getX(), entity.getY(), entity.getZ(), 1));
            }
        }
    }
}
