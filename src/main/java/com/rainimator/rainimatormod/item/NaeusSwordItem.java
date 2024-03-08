package com.rainimator.rainimatormod.item;

import com.rainimator.rainimatormod.RainimatorMod;
import com.rainimator.rainimatormod.registry.util.ModCreativeTab;
import com.rainimator.rainimatormod.registry.util.TierBase;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
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
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class NaeusSwordItem extends SwordItem {
    public NaeusSwordItem() {
        super(TierBase.of(4000, 4.0F, 9.0F, 0, 10), 3, -2.0F, ModCreativeTab.createProperty().fireResistant());
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack itemstack, @NotNull LivingEntity entity, @NotNull LivingEntity sourceEntity) {
        boolean ret_val = super.hurtEnemy(itemstack, entity, sourceEntity);
        if (!entity.level.isClientSide())
            entity.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 0));
        entity.setSecondsOnFire(5);
        return ret_val;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level world, @NotNull Player entity, @NotNull InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
        Vec3 _center = entity.position();
        List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, (new AABB(_center, _center)).inflate(6.0D), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
        for (Entity entityIterator : _entfound) {
            if (entity.isShiftKeyDown()) {
                if (entityIterator instanceof Player)
                    entity.hurt(DamageSource.GENERIC, 0.0F);
                else {
                    entityIterator.hurt(DamageSource.MAGIC, 5.0F);
                    if (entityIterator instanceof LivingEntity _entity)
                        if (!_entity.level.isClientSide())
                            _entity.addEffect(new MobEffectInstance(MobEffects.POISON, 300, 1));
                    entityIterator.setSecondsOnFire(10);
                    if (world instanceof Level) {
                        if (!world.isClientSide())
                            world.playSound(null, new BlockPos(_center), Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(RainimatorMod.MOD_ID,"naeus_sword_1"))), SoundSource.NEUTRAL, 1.0F, 1.0F);
                        else
                            world.playLocalSound(_center.x, _center.y, _center.z, Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(RainimatorMod.MOD_ID,"naeus_sword_1"))), SoundSource.NEUTRAL, 1.0F, 1.0F, false);
                    }
                    if (entity instanceof Player)
                        entity.getCooldowns().addCooldown(ar.getObject().getItem(), 600);
                }
            }
        }
        return ar;
    }
}