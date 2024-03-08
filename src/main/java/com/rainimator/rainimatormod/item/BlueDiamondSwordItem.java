package com.rainimator.rainimatormod.item;

import com.rainimator.rainimatormod.registry.ModItems;
import com.rainimator.rainimatormod.registry.util.ModCreativeTab;
import com.rainimator.rainimatormod.registry.util.TierBase;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BlueDiamondSwordItem extends SwordItem {
    public BlueDiamondSwordItem() {
        super(TierBase.of(3000, 4.0F, 15.0F, 0, 30, ModItems.BLUEDIAMOND), 3, -2.0F, ModCreativeTab.createProperty().fireResistant());
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack itemstack, @NotNull LivingEntity entity, @NotNull LivingEntity sourceentity) {
        boolean retval = super.hurtEnemy(itemstack, entity, sourceentity);
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();
        if (entity instanceof LivingEntity)
            if (entity.hasEffect(MobEffects.GLOWING) && Math.random() < 0.4D)
                entity.hurt(DamageSource.MAGIC, 5.0F);
        if (Math.random() < 0.1D) {
            if (entity.level instanceof ServerLevel _level) {
                LightningBolt entityToSpawn = EntityType.LIGHTNING_BOLT.create(_level);
                if (entityToSpawn != null) {
                    entityToSpawn.moveTo(Vec3.atBottomCenterOf(new BlockPos(x, y, z)));
                    entityToSpawn.setVisualOnly(true);
                    _level.addFreshEntity(entityToSpawn);
                }
            }
            entity.level.setBlock(new BlockPos(x, y, z), Blocks.FIRE.defaultBlockState(), 3);
        }

        return retval;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level world, @NotNull Player entity, @NotNull InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
        //TODO: Fail to decompile, message as follows
        // INTERNAL ERROR //
//        BluediamondswordskillsProcedure.execute((LevelAccessor) world, entity.getX(), entity.getY(), entity.getZ(), (Entity) entity, (ItemStack) ar.getObject());
        return ar;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemstack, Level world, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(new TextComponent("§e这把剑可以把神力转化为不同形式的能量放出，对不同的生物有者不同的效果"));

        list.add(new TextComponent("§c当使用者潜行右键时可释放神力审判周围§b8*8§c范围内的生物"));
        list.add(new TextComponent("§b雷霆诅咒：§3当周围生物为§6节肢生物§3时，技能释放形式为§b雷霆万钧§3，标记并点燃范围内所有目标，在接下来一段时间对每个目标进行§e5§3次雷击[若目标死亡，则取消之]"));

        list.add(new TextComponent("§a脉冲诅咒：§2当周围生物为§4UNDEFINED§2时，技能释放形式为§a脉冲爆破§2，标记并点燃范围内所有目标，在接下来一段时间对每个目标进行§e5§2次爆破[若目标死亡，则取消之]"));

        list.add(new TextComponent("§9波动诅咒：§1当周围生物为§7亡灵生物§1或§5暴民§1时，技能释放形式为§9波动异变§1，标记并点燃范围内所有目标，将§9波动异能§1注入生物体内，使生物§9失重§1，同时使生物缓慢聚集在一起数秒后在中心发动§9波动爆炸"));

        list.add(new TextComponent("§b能力：雷击§3[使用者每次使用这把剑击中目标有概率召唤一道雷电打击目标]"));
        list.add(new TextComponent("§d能力：魔切§5[当目标被标记时，使用者每次使用这把剑击中目标有概率造成额外魔法伤害]"));
    }

    @Override
    public boolean onEntitySwing(ItemStack itemstack, LivingEntity entity) {
        boolean retval = super.onEntitySwing(itemstack, entity);
        double r, num, angel;
        if (Math.random() < 0.2D) {
            num = 50.0D;
            r = 2.0D;
            angel = 0.0D;
            for (int index0 = 0; index0 < (int) num; index0++) {
                if (entity.level instanceof ServerLevel _level)
                    _level.sendParticles(ParticleTypes.SOUL_FIRE_FLAME, entity.getX() + r * Math.cos(angel), entity.getY() + 1.0D, entity.getZ() + r * Math.sin(angel), 1, 0.0D, 0.0D, 0.0D, 0.0D);
                angel += 360.0D / num;
            }
        }
        return retval;
    }
}