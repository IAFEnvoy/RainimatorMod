package com.rainimator.rainimatormod.item.sword;

import com.rainimator.rainimatormod.RainimatorMod;
import com.rainimator.rainimatormod.registry.util.IRainimatorInfo;
import com.rainimator.rainimatormod.registry.util.ModCreativeTab;
import com.rainimator.rainimatormod.registry.util.SwordItemBase;
import com.rainimator.rainimatormod.registry.util.TierBase;
import com.rainimator.rainimatormod.util.Episode;
import com.rainimator.rainimatormod.util.ParticleUtil;
import com.rainimator.rainimatormod.util.SoundUtil;
import com.rainimator.rainimatormod.util.Timeout;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.Random;

public class NetherSpearItem extends SwordItemBase implements IRainimatorInfo, ICurioItem {
    public NetherSpearItem() {
        super(TierBase.of(3000, 0.0F, 11.0F, 0, 25), 3, -2.2F, ModCreativeTab.createProperty().fireResistant());
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack itemstack, @NotNull LivingEntity entity, @NotNull LivingEntity sourceentity) {
        boolean retval = super.hurtEnemy(itemstack, entity, sourceentity);
        if (entity instanceof Mob _entity)
            _entity.setTarget(sourceentity);
        if (Math.random() < 0.2D)
            entity.setSecondsOnFire(10);
        if (Math.random() < 0.2D)
            sourceentity.setHealth(((sourceentity instanceof LivingEntity) ? sourceentity.getHealth() : -1.0F) + Mth.nextInt(new Random(), 1, 4));
        return retval;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level world, @NotNull Player entity, @NotNull InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
        double x = entity.getX();
        final double y = entity.getY();
        double z = entity.getZ();
        ItemStack itemstack = ar.getObject();
        if (entity.isShiftKeyDown())
            SoundUtil.playSound(world, x, y, z, new ResourceLocation(RainimatorMod.MOD_ID, "naeus_sword_1"), 5.0F, 1.0F);
        BlockPos pos1 = entity.level.clip(new ClipContext(entity.getEyePosition(1.0F), entity.getEyePosition(1.0F).add(entity.getViewVector(1.0F).scale(2.0D)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos();
        if (world instanceof ServerLevel _level) {
            LightningBolt entityToSpawn = EntityType.LIGHTNING_BOLT.create(_level);
            if (entityToSpawn != null) {
                entityToSpawn.moveTo(Vec3.atBottomCenterOf(new BlockPos(pos1.getX(), y, pos1.getZ())));
                entityToSpawn.setVisualOnly(true);
                _level.addFreshEntity(entityToSpawn);
            }
        }

        world.setBlock(new BlockPos(pos1.getX(), y, pos1.getZ()), Blocks.FIRE.defaultBlockState(), 3);

        Runnable callback = () -> {
            BlockPos pos = entity.level.clip(new ClipContext(entity.getEyePosition(1.0F), entity.getEyePosition(1.0F).add(entity.getViewVector(1.0F).scale(12.0D)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos();
            if (world instanceof ServerLevel _level) {
                LightningBolt entityToSpawn = EntityType.LIGHTNING_BOLT.create(_level);
                if (entityToSpawn != null) {
                    entityToSpawn.moveTo(Vec3.atBottomCenterOf(new BlockPos(pos.getX(), y, pos.getZ())));
                    entityToSpawn.setVisualOnly(true);
                    _level.addFreshEntity(entityToSpawn);
                }
            }
            world.setBlock(new BlockPos(pos.getX(), y, pos.getZ()), Blocks.FIRE.defaultBlockState(), 3);
        };
        Timeout.create(2, callback);
        Timeout.create(4, callback);
        Timeout.create(6, callback);
        Timeout.create(8, callback);
        Timeout.create(10, callback);
        Timeout.create(12, callback);
        Timeout.create(14, callback);
        Timeout.create(16, callback);
        Timeout.create(18, callback);
        Timeout.create(20, callback);

        if (entity instanceof Player)
            entity.getCooldowns().addCooldown(itemstack.getItem(), 1200);

        return ar;
    }

    @Override
    public boolean onEntitySwing(ItemStack itemstack, LivingEntity entity) {
        boolean retval = super.onEntitySwing(itemstack, entity);
        if (Math.random() < 0.2D)
            ParticleUtil.spawnCircleParticles(entity.level, ParticleTypes.FLAME, entity.getX(), entity.getY(), entity.getZ(), 4, 0, 50);
        return retval;
    }

    @Override
    public void inventoryTick(@NotNull ItemStack itemstack, @NotNull Level world, @NotNull Entity entity, int slot, boolean selected) {
        super.inventoryTick(itemstack, world, entity, slot, selected);
        if (selected && entity instanceof LivingEntity _livEnt && !_livEnt.level.isClientSide()) {
            if (((entity instanceof LivingEntity) ? _livEnt.getArmorValue() : 0) <= 10)
                _livEnt.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 3));
            else {
                if (((entity instanceof LivingEntity) ? _livEnt.getArmorValue() : 0) <= 20)
                    _livEnt.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 2));
                else {
                    if (((entity instanceof LivingEntity) ? _livEnt.getArmorValue() : 0) <= 30)
                        _livEnt.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 1));
                    else {
                        if (((entity instanceof LivingEntity) ? _livEnt.getArmorValue() : 0) <= 40)
                            _livEnt.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 0));
                    }
                }
            }
        }
    }

    @Override
    public Episode getEpisode() {
        return Episode.HardPillToSwallow;
    }
}
