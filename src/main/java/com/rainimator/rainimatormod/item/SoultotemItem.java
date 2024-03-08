package com.rainimator.rainimatormod.item;

import com.rainimator.rainimatormod.registry.util.FoilItemBase;
import com.rainimator.rainimatormod.registry.util.ModCreativeTab;
import com.rainimator.rainimatormod.util.Timeout;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class SoultotemItem extends FoilItemBase {
    public SoultotemItem() {
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
            if (!world.isClientSide())
                world.playSound(null, new BlockPos(x, y, z), Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.anvil.land"))), SoundSource.NEUTRAL, 5.0F, 1.0F);
            else
                world.playLocalSound(x, y, z, Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.anvil.land"))), SoundSource.NEUTRAL, 5.0F, 1.0F, false);

            if (world instanceof ServerLevel _level) {
                _level.sendParticles((ParticleOptions) ParticleTypes.END_ROD, x, y, z, 100, 3.0D, 4.0D, 3.0D, 0.002D);
            }
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
            if (!world.isClientSide())
                world.playSound(null, new BlockPos(x, y, z), Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.anvil.land"))), SoundSource.NEUTRAL, 5.0F, 1.0F);
            else
                world.playLocalSound(x, y, z, Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.anvil.land"))), SoundSource.NEUTRAL, 5.0F, 1.0F, false);

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
        //TODO: Fail to decompile Soultotem_skillProcedure.java
//        if (selected)
//            Soultotem_skill_4Procedure.execute(world, entity.getX(), entity.getY(), entity.getZ());
//        Soultotem_skillProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ(), entity);
    }
}
