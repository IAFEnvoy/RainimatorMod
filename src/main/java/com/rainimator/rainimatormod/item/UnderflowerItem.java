package com.rainimator.rainimatormod.item;

import com.rainimator.rainimatormod.RainimatorMod;
import com.rainimator.rainimatormod.registry.util.FoilItemBase;
import com.rainimator.rainimatormod.registry.util.ModCreativeTab;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class UnderflowerItem extends FoilItemBase {
    public UnderflowerItem() {
        super(p -> p.tab(ModCreativeTab.items).stacksTo(1).rarity(Rarity.UNCOMMON));
    }

    @Override
    public @NotNull InteractionResult useOn(@NotNull UseOnContext context) {
        super.useOn(context);
        Level world = context.getLevel();
        double x = context.getClickedPos().getX();
        double y = context.getClickedPos().getY();
        double z = context.getClickedPos().getZ();
        Player entity = context.getPlayer();
        ItemStack itemstack = context.getItemInHand();
        if (entity != null) {
            if (entity.isShiftKeyDown()) {
                if (!world.isClientSide())
                    world.playSound(null, new BlockPos(x, y, z), Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(RainimatorMod.MOD_ID, "underflower_1"))), SoundSource.NEUTRAL, 1.0F, 1.0F);
                else
                    world.playLocalSound(x, y, z, Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(RainimatorMod.MOD_ID, "underflower_1"))), SoundSource.NEUTRAL, 1.0F, 1.0F, false);

                BlockPos _bp = new BlockPos(x, y + 1.0D, z);
                BlockState _bs = Blocks.SOUL_FIRE.defaultBlockState();
                world.setBlock(_bp, _bs, 3);
                entity.getCooldowns().addCooldown(itemstack.getItem(), 400);
            } else {
                if (!world.isClientSide())
                    world.playSound(null, new BlockPos(x, y, z), Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(RainimatorMod.MOD_ID, "underflower_1"))), SoundSource.NEUTRAL, 1.0F, 1.0F);
                else
                    world.playLocalSound(x, y, z, Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(RainimatorMod.MOD_ID, "underflower_1"))), SoundSource.NEUTRAL, 1.0F, 1.0F, false);

                BlockPos _bp = new BlockPos(x, y + 1.0D, z);
                BlockState _bs = Blocks.FIRE.defaultBlockState();
                world.setBlock(_bp, _bs, 3);
                entity.getCooldowns().addCooldown(itemstack.getItem(), 200);
            }
        }
        return InteractionResult.SUCCESS;
    }
}
