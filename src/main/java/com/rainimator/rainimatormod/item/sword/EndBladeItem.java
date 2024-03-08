package com.rainimator.rainimatormod.item.sword;

import com.rainimator.rainimatormod.registry.ModItems;
import com.rainimator.rainimatormod.registry.ModParticleTypes;
import com.rainimator.rainimatormod.registry.util.ModCreativeTab;
import com.rainimator.rainimatormod.registry.util.TierBase;
import com.rainimator.rainimatormod.util.MiscUtil;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class EndBladeItem extends SwordItem {
    public EndBladeItem() {
        super(TierBase.of(2000, 4.0F, 9.0F, 1, 15, ModItems.SUPER_SPPARIES.get(), Items.ENDER_EYE), 3, -2.2F, ModCreativeTab.createProperty());
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level world, @NotNull Player entity, @NotNull InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();
        ItemStack itemstack = ar.getObject();
        double ender_1 = 0.0D;
        if (entity.isShiftKeyDown()) {
            entity.teleportTo(entity.level
                    .clip(new ClipContext(entity.getEyePosition(1.0F), entity.getEyePosition(1.0F).add(entity.getViewVector(1.0F).scale(ender_1 + 6.0D)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity)).getBlockPos().getX(), entity.level
                    .clip(new ClipContext(entity.getEyePosition(1.0F), entity.getEyePosition(1.0F).add(entity.getViewVector(1.0F).scale(ender_1)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity)).getBlockPos().getY(), entity.level
                    .clip(new ClipContext(entity.getEyePosition(1.0F), entity.getEyePosition(1.0F).add(entity.getViewVector(1.0F).scale(ender_1 + 6.0D)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity)).getBlockPos().getZ());
            if ((Entity) entity instanceof ServerPlayer _serverPlayer)
                _serverPlayer.connection.teleport(entity.level
                        .clip(new ClipContext(entity.getEyePosition(1.0F), entity.getEyePosition(1.0F).add(entity.getViewVector(1.0F).scale(ender_1 + 6.0D)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity)).getBlockPos()
                        .getX(), entity.level
                        .clip(new ClipContext(entity.getEyePosition(1.0F), entity.getEyePosition(1.0F).add(entity.getViewVector(1.0F).scale(ender_1)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity)).getBlockPos().getY(), entity.level
                        .clip(new ClipContext(entity.getEyePosition(1.0F), entity.getEyePosition(1.0F).add(entity.getViewVector(1.0F).scale(ender_1 + 6.0D)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity)).getBlockPos()
                        .getZ(), entity
                        .getYRot(), entity.getXRot());
            MiscUtil.playSound(world, x, y, z, new ResourceLocation("entity.enderman.teleport"), 1.0F, 1.0F);
            if (world instanceof ServerLevel _level) {
                _level.sendParticles((ParticleOptions) ModParticleTypes.PURPLELIGHT.get(), x, y, z, 50, 0.5D, 0.0D, 0.5D, 0.2D);
                entity.getCooldowns().addCooldown(itemstack.getItem(), 300);
            }
        }
        return ar;
    }
}

