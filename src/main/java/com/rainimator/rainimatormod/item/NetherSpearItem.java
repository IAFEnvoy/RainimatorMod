package com.rainimator.rainimatormod.item;

import com.rainimator.rainimatormod.RainimatorMod;
import com.rainimator.rainimatormod.registry.util.ModCreativeTab;
import com.rainimator.rainimatormod.registry.util.TierBase;
import com.rainimator.rainimatormod.util.Timeout;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class NetherSpearItem extends SwordItem {
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
        if (entity.isShiftKeyDown()) {
            if (!world.isClientSide())
                world.playSound(null, new BlockPos(x, y, z), Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(RainimatorMod.MOD_ID, "naeus_sword_1"))), SoundSource.NEUTRAL, 5.0F, 1.0F);
            else
                world.playLocalSound(x, y, z, Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(RainimatorMod.MOD_ID, "naeus_sword_1"))), SoundSource.NEUTRAL, 5.0F, 1.0F, false);
        }

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
    public void appendHoverText(@NotNull ItemStack itemstack, Level world, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(new TextComponent("§c杀戮之影：§4使用者的护甲值越低得到的§c地狱之力(力量BUFF)§4加成越高[最高得到§b4§4级的BUFF加成]"));

        list.add(new TextComponent("§b雷诀：§4持有§c下界长枪§4的使用者潜行时右键可使用[Neaus的怨念之力]发动§b雷诀§4，向前方召唤数道雷电攻击并引燃目标，对同一直线上的生物具有穿透效果"));

        list.add(new TextComponent("§e战神庇护：§c下界长枪§4会不同程度地强化自身所拥有的附魔属性，同时有概率获得其他方面属性的加成，使使用者在战斗中所向披靡"));

        list.add(new TextComponent("§4能力：嗜血§7[使用者使用这把枪攻击目标时，有概率吸收目标生命值治疗自身]"));

        list.add(new TextComponent("§c能力：淬火§5[使用者使用这把枪攻击目标时有概率点燃目标，攻击点燃的目标有概率使其受到额外的火焰伤害]"));
    }

    @Override
    public boolean onEntitySwing(ItemStack itemstack, LivingEntity entity) {
        boolean retval = super.onEntitySwing(itemstack, entity);
        double num, r, angel;
        if (Math.random() < 0.2D) {
            num = 50.0D;
            r = 4.0D;
            angel = 0.0D;
            for (int index0 = 0; index0 < (int) num; index0++) {
                if (entity.level instanceof ServerLevel _level)
                    _level.sendParticles((ParticleOptions) ParticleTypes.FLAME, entity.getX() + r * Math.cos(angel), entity.getY() + 1.0D, entity.getZ() + r * Math.sin(angel), 1, 0.0D, 0.0D, 0.0D, 0.0D);
                angel += 360.0D / num;
            }
        }
        return retval;
    }

    @Override
    public void inventoryTick(@NotNull ItemStack itemstack, @NotNull Level world, @NotNull Entity entity, int slot, boolean selected) {
        super.inventoryTick(itemstack, world, entity, slot, selected);
        if (selected) {
            LivingEntity _livEnt = (LivingEntity) entity;
            if (((entity instanceof LivingEntity) ? _livEnt.getArmorValue() : 0) <= 10) {
                LivingEntity _entity = (LivingEntity) entity;
                if (!_entity.level.isClientSide())
                    _entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 3));
            } else {
                LivingEntity livingEntity = (LivingEntity) entity;
                if (((entity instanceof LivingEntity) ? livingEntity.getArmorValue() : 0) <= 20) {
                    LivingEntity _entity = (LivingEntity) entity;
                    if (!_entity.level.isClientSide())
                        _entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 2));
                } else {
                    LivingEntity livingEntity1 = (LivingEntity) entity;
                    if (((entity instanceof LivingEntity) ? livingEntity1.getArmorValue() : 0) <= 30) {
                        LivingEntity _entity = (LivingEntity) entity;
                        if (!_entity.level.isClientSide())
                            _entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 1));
                    } else {
                        LivingEntity livingEntity2 = (LivingEntity) entity;
                        if (((entity instanceof LivingEntity) ? livingEntity2.getArmorValue() : 0) <= 40) {
                            LivingEntity _entity = (LivingEntity) entity;
                            if (!_entity.level.isClientSide())
                                _entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 0));
                        }
                    }
                }
            }

        }
    }
}
