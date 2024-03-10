package com.rainimator.rainimatormod.item.sword;

import com.rainimator.rainimatormod.RainimatorMod;
import com.rainimator.rainimatormod.registry.ModItems;
import com.rainimator.rainimatormod.registry.util.ModCreativeTab;
import com.rainimator.rainimatormod.registry.util.TierBase;
import com.rainimator.rainimatormod.util.MiscUtil;
import com.rainimator.rainimatormod.util.ParticleUtil;
import com.rainimator.rainimatormod.util.Timeout;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;

public class BlackBoneTheBladeItem extends SwordItem {
    public BlackBoneTheBladeItem() {
        super(TierBase.of(1500, 0.0F, 7.0F, 0, 10, ModItems.RUBY), 3, -2.4F, ModCreativeTab.createProperty().fireResistant());
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack itemstack, @NotNull LivingEntity entity, @NotNull LivingEntity sourceentity) {
        boolean retval = super.hurtEnemy(itemstack, entity, sourceentity);
        if (entity instanceof Mob _entity)
            if (sourceentity instanceof LivingEntity)
                _entity.setTarget(sourceentity);
        entity.setSecondsOnFire(8);
        if (Math.random() < 0.1D)
            entity.hurt(DamageSource.MAGIC, Mth.nextInt(new Random(), 1, 3));
        if (Math.random() < 0.3D)
            sourceentity.setHealth(sourceentity.getHealth() + Mth.nextInt(new Random(), 2, 5));
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
            MiscUtil.playSound(world, x, y, z, new ResourceLocation(RainimatorMod.MOD_ID, "blackbone_living"), 10.0F, 1.0F);
            if (world instanceof ServerLevel _level)
                _level.sendParticles((ParticleOptions) ParticleTypes.ELECTRIC_SPARK, x, y, z, 25, 1.0D, 1.0D, 1.0D, 1.0D);
            if (!world.isClientSide())
                world.explode(null, entity.level.clip(new ClipContext(entity.getEyePosition(1.0F), entity.getEyePosition(1.0F).add(entity.getViewVector(1.0F).scale(2.0D)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getX(), y + 1.0D, entity.level
                        .clip(new ClipContext(entity.getEyePosition(1.0F), entity.getEyePosition(1.0F).add(entity.getViewVector(1.0F).scale(2.0D)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getZ(), 1.0F, Explosion.BlockInteraction.NONE);

            entity.getCooldowns().addCooldown(itemstack.getItem(), 700);

            Runnable callback = () -> {
                if (world instanceof Level) {
                    if (!world.isClientSide())
                        world.explode(null, entity.level
                                .clip(new ClipContext(entity.getEyePosition(1.0F), entity.getEyePosition(1.0F).add(entity.getViewVector(1.0F).scale(12.0D)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getX(), y + 1.0D, entity.level

                                .clip(new ClipContext(entity.getEyePosition(1.0F), entity.getEyePosition(1.0F).add(entity.getViewVector(1.0F).scale(12.0D)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos().getZ(), 2.0F, Explosion.BlockInteraction.NONE);
                }
            };
            Timeout.create(5, callback);
            Timeout.create(10, callback);
            Timeout.create(15, callback);
            Timeout.create(20, callback);
            Timeout.create(25, callback);
            Timeout.create(30, callback);
            Timeout.create(35, callback);
        }
        return ar;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemstack, Level world, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(new TextComponent("§4拥有§c烈焰神力§4的武器，攻击目标可使目标着火，攻击着火目标会对目标造成额外的魔法伤害并有概率回复使用者的生命值"));

        list.add(new TextComponent("§4这把武器被赋予了§c地狱诅咒§4，使用者无法将这把武器的附魔祛除，§a这把武器的前三个附魔属性属于绑定"));

        list.add(new TextComponent("§4这把武器会根据自身拥有的 §c抢夺，§9耐久，§b经验修补§4的附魔级数，来对应强化自身攻击力"));

        list.add(new TextComponent("§4这把武器排斥它§b不认可§4的所有附魔属性，包括§e(横扫之刃，更高等级的锋利/亡灵杀手/节肢杀手，火焰附加！)"));

        list.add(new TextComponent("§a当使用者使用这把武器潜行右键时可使用技能§c地狱冲击波§a！"));
    }

    @Override
    public boolean onEntitySwing(ItemStack itemstack, LivingEntity entity) {
        boolean retval = super.onEntitySwing(itemstack, entity);
        if (Math.random() < 0.2D)
            ParticleUtil.spawnCircleParticles(entity.level, ParticleTypes.LAVA, entity.getX(), entity.getY(), entity.getZ(), 4, 0, 50);
        return retval;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public boolean isFoil(@NotNull ItemStack itemstack) {
        return true;
    }
}
