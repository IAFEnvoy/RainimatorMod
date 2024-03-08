package com.rainimator.rainimatormod.item.sword;

import com.rainimator.rainimatormod.registry.ModEffects;
import com.rainimator.rainimatormod.registry.util.ModCreativeTab;
import com.rainimator.rainimatormod.registry.util.TierBase;
import com.rainimator.rainimatormod.util.ParticleUtil;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SeizingshadowhalberdItem extends SwordItem {
    public SeizingshadowhalberdItem() {
        super(TierBase.of(2000, 0.0F, 13.0F, 0, 20), 3, -2.2F, ModCreativeTab.createProperty().fireResistant());
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack itemstack, @NotNull LivingEntity entity, @NotNull LivingEntity sourceentity) {
        boolean retval = super.hurtEnemy(itemstack, entity, sourceentity);
        if (Math.random() < 0.1D)
            if (!entity.level.isClientSide())
                entity.addEffect(new MobEffectInstance(ModEffects.SHADOWEROSION.get(), 200, 0));
        return retval;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level world, @NotNull Player entity, @NotNull InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
        //TODO: Fail to decompile
//        Seizingshadowhalberd_skill_2Procedure.execute((LevelAccessor)world, entity.getX(), entity.getY(), entity.getZ(), (Entity)entity, (ItemStack)ar.getObject());
        return ar;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemstack, Level world, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(new TextComponent("§7拥有双重神力的武器"));
        list.add(new TextComponent("§d龙震波：§5当使用者潜行时右键地面可标记某一处位置，3秒后在标记处召唤一颗§d龙息弹§5打击范围内目标"));

        list.add(new TextComponent("§7灾厄降临，§9当使用者潜行时右键，可在周围召唤§7唤魔者之牙§9法阵打击周围§16*6§9范围内的生物"));

        list.add(new TextComponent("§b精准打击：§3使用者右键地面可召唤§7唤魔者之牙§3对指定位置生物造成一次伤害"));

        list.add(new TextComponent("§e离心牵引：§6果目标被§5影蚀诅咒，§6使用者右键可将被诅咒生物拉至身前并召唤§7唤魔者之牙§6打击目标"));

        list.add(new TextComponent("§a回溯：§2使用者使用此武器时所有技能CD减半"));
        list.add(new TextComponent("§d影蚀：§5使用者使用此武器攻击目标时有概率诅咒目标，使目标漂浮！"));
    }

    @Override
    public @NotNull InteractionResult useOn(@NotNull UseOnContext context) {
        super.useOn(context);
        //TODO: Fail to decompile
//        Seizingshadowhalberd_skill_3Procedure.execute((LevelAccessor)context.getLevel(), context.getClickedPos().getX(), context.getClickedPos().getY(), context.getClickedPos().getZ(), (Entity)context.getPlayer(), context.getItemInHand());
        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean onEntitySwing(ItemStack itemstack, LivingEntity entity) {
        boolean retval = super.onEntitySwing(itemstack, entity);
        if (Math.random() < 0.2D)
            ParticleUtil.spawnCircleParticles(entity.level, ParticleTypes.END_ROD, entity.getX(), entity.getY(), entity.getZ(), 4, 0, 50);
        return retval;
    }
}
