package com.rainimator.rainimatormod.item.sword;

import com.google.common.collect.Lists;
import com.rainimator.rainimatormod.RainimatorMod;
import com.rainimator.rainimatormod.registry.ModEffects;
import com.rainimator.rainimatormod.registry.ModItems;
import com.rainimator.rainimatormod.registry.util.ModCreativeTab;
import com.rainimator.rainimatormod.registry.util.TierBase;
import com.rainimator.rainimatormod.util.MiscUtil;
import com.rainimator.rainimatormod.util.ParticleUtil;
import com.rainimator.rainimatormod.util.Timeout;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.apache.commons.lang3.tuple.Triple;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class RainSwordItem extends SwordItem {
    public RainSwordItem() {
        super(TierBase.of(2000, 4.0F, 11.0F, 0, 20, ModItems.SUPER_SPPARIES), 3, -2.0F, ModCreativeTab.createProperty());
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack itemstack, @NotNull LivingEntity entity, @NotNull LivingEntity sourceentity) {
        boolean ret_val = super.hurtEnemy(itemstack, entity, sourceentity);
        if (Math.random() < 0.1D)
            if (entity instanceof LivingEntity)
                if (!entity.level.isClientSide()) {
                    entity.addEffect(new MobEffectInstance(ModEffects.ICEPEOPLE.get(), 100, 0));
                    entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 0));
                }
        return ret_val;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level world, @NotNull Player entity, @NotNull InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);

        Vec3 _center = entity.position();
        List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, (new AABB(_center, _center)).inflate(7.0D), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
        for (Entity entityiterator : _entfound) {
            if (!(entityiterator instanceof LivingEntity _livEnt)) continue;
            if (_livEnt.getMainHandItem().getItem() == ModItems.RAIN_SWORD.get()) {
                entityiterator.hurt(DamageSource.GENERIC, 0.0F);
                continue;
            }
            if (entity.isShiftKeyDown()) {
                if (entityiterator instanceof Mob _entity)
                    _entity.getNavigation().stop();
                MiscUtil.playSound(world, _center.x, _center.y, _center.z, new ResourceLocation(RainimatorMod.MOD_ID, "rain_sword_skill"), 1.0F, 1.0F);

                List<Triple<Integer, Integer, Integer>> places = Lists.newArrayList(
                        Triple.of(0, 0, 0),
                        Triple.of(1, 0, 0),
                        Triple.of(-1, 0, 0),
                        Triple.of(0, 0, 1),
                        Triple.of(0, 0, -1),
                        Triple.of(0, 1, 0),
                        Triple.of(1, 1, 0),
                        Triple.of(-1, 1, 0),
                        Triple.of(0, 1, 1),
                        Triple.of(0, 1, -1),
                        Triple.of(0, 2, 0)
                );
                for (Triple<Integer, Integer, Integer> place : places)
                    world.setBlock(new BlockPos(entityiterator.getX() + place.getLeft(), entityiterator.getY() + place.getMiddle(), entityiterator.getZ() + place.getRight()), Blocks.ICE.defaultBlockState(), 3);
                if (!_livEnt.level.isClientSide()) {
                    _livEnt.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 540, 4));
                    _livEnt.addEffect(new MobEffectInstance(ModEffects.ICEPEOPLE.get(), 500, 0));
                }
                if (entity instanceof Player)
                    entity.getCooldowns().addCooldown(ar.getObject().getItem(), 1200);

                Timeout.create(500, () -> {
                    MiscUtil.playSound(world, _center.x, _center.y, _center.z, new ResourceLocation(RainimatorMod.MOD_ID, "rain_sword_skill_2"), 5.0F, 1.0F);
                    for (Triple<Integer, Integer, Integer> place : places)
                        world.setBlock(new BlockPos(entityiterator.getX() + place.getLeft(), entityiterator.getY() + place.getMiddle(), entityiterator.getZ() + place.getRight()), Blocks.AIR.defaultBlockState(), 3);
                    entityiterator.hurt(DamageSource.MAGIC, 5.0F);
                });
            }
        }
        return ar;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemstack, Level world, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(new TextComponent("§9这把剑可以把神力转换成§b极寒之力§9释放"));
        list.add(new TextComponent("§b凛冬之怒：§3当使用者潜心加右键时，可将周围§e7*7§3范围内的所有实体冻结并冰封[时间较长，属于绝对控制]，冰封期间会使目标冻伤并持续受到冰冻伤害，在融冰时会使目标受到较大额外魔法伤害"));

        list.add(new TextComponent("§e极寒风暴：§a当使用者手持这把剑时，会在自身周围§e7*7§a范围内形成一个§b极寒领域§a，在领域中的生物会被减速和削弱作战能力，在邻域内§9冻伤§a效果会逐渐增加，当§9冻伤§a效果每达到§65§a层时目标会受到一次§e极寒风暴§a的伤害§c[在邻域待的越久受到伤害的可能性越大]"));

        list.add(new TextComponent("§c能力：侵蚀§4[使用者使用这把剑打击目标，有概率使目标减速并直接受到一次§e极寒风暴§4的伤害]"));

        list.add(new TextComponent("§8能力：冰心§7[当使用者和这把剑一同作战时，使用者将永远不会被点燃]"));
    }


    @Override
    public boolean onEntitySwing(ItemStack itemstack, LivingEntity entity) {
        boolean ret_val = super.onEntitySwing(itemstack, entity);
        double x = entity.getX(), y = entity.getY(), z = entity.getZ();
        if (Math.random() < 0.2D)
            ParticleUtil.spawnCircleParticles(entity.level, ParticleTypes.ENCHANT, x, y, z, 3, 0, 50);
        return ret_val;
    }


    @Override
    public void inventoryTick(@NotNull ItemStack itemstack, @NotNull Level world, @NotNull Entity entity, int slot, boolean selected) {
        super.inventoryTick(itemstack, world, entity, slot, selected);
        if (selected) {
            entity.clearFire();

            Vec3 _center = entity.position();
            List<Entity> _ent_found = world.getEntitiesOfClass(Entity.class, (new AABB(_center, _center)).inflate(7.0D), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
            for (Entity entityIterator : _ent_found) {
                if (!(entityIterator instanceof LivingEntity _livEnt)) continue;
                if (_livEnt.getMainHandItem().getItem() == ModItems.RAIN_SWORD.get()) {
                    if (itemstack.hurt(0, new Random(), null)) {
                        itemstack.shrink(1);
                        itemstack.setDamageValue(0);
                    }
                    continue;
                }
                if (!_livEnt.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)) {
                    if (!_livEnt.level.isClientSide()) {
                        _livEnt.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 0));
                        _livEnt.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 60, 0));
                    }
                    entityIterator.setTicksFrozen(2);
                    if (Math.random() < 0.04D) {
                        if (!_livEnt.level.isClientSide())
                            _livEnt.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 1));
                        entityIterator.setTicksFrozen(4);
                        Runnable callback = () -> {
                            if (!_livEnt.level.isClientSide())
                                _livEnt.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 2));
                            entityIterator.setTicksFrozen(6);
                        };
                        Timeout.create(60, callback);
                        Timeout.create(120, callback);
                        Timeout.create(180, () -> {
                            if (!_livEnt.level.isClientSide()) {
                                _livEnt.addEffect(new MobEffectInstance(ModEffects.ICEPEOPLE.get(), 100, 0));
                                _livEnt.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 4));
                            }
                            MiscUtil.playSound(world, _center.x, _center.y, _center.z, new ResourceLocation("block.conduit.activate"), 1.0F, 1.0F);
                            entityIterator.setTicksFrozen(10);
                        });
                    }
                }
            }
        }
    }
}
