package dev.rainimator.mod.item.sword;

import com.google.common.collect.Lists;
import dev.rainimator.mod.RainimatorMod;
import dev.rainimator.mod.data.component.ManaData;
import dev.rainimator.mod.data.config.ServerConfig;
import dev.rainimator.mod.impl.ComponentManager;
import dev.rainimator.mod.item.util.SwordItemBase;
import dev.rainimator.mod.item.util.ToolMaterialUtil;
import dev.rainimator.mod.registry.RainimatorEffects;
import dev.rainimator.mod.registry.RainimatorItemGroups;
import dev.rainimator.mod.registry.RainimatorItems;
import dev.rainimator.mod.registry.util.IRainimatorInfo;
import dev.rainimator.mod.util.*;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.apache.commons.lang3.tuple.Triple;

import java.util.Comparator;
import java.util.List;

public class RainSwordItem extends SwordItemBase implements IRainimatorInfo {
    private static final List<Triple<Integer, Integer, Integer>> places = Lists.newArrayList(
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

    public RainSwordItem() {
        super(ToolMaterialUtil.of(2000, 4.0F, 11.0F, 0, 20, RainimatorItems.SUPER_SAPPHIRE), 3, -2.0F, new Settings().arch$tab(RainimatorItemGroups.MAIN));
    }

    @Override
    public boolean postHit(ItemStack itemtack, LivingEntity entity, LivingEntity sourceentity) {
        boolean ret_val = super.postHit(itemtack, entity, sourceentity);
        if (Math.random() < 0.1D)
            if (entity instanceof LivingEntity)
                if (!entity.getWorld().isClient()) {
                    entity.addStatusEffect(new StatusEffectInstance(RainimatorEffects.ICE_PEOPLE.get(), 100, 0));
                    entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 0));
                }
        return ret_val;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity entity, Hand hand) {
        TypedActionResult<ItemStack> ar = super.use(world, entity, hand);
        Vec3d _center = entity.getPos();
        ManaData data = ComponentManager.getManaData(entity);
        if (entity.isSneaking() && data.tryUseMana(ServerConfig.getInstance().rain_sword)) {
            List<Entity> _entfound = world.getEntitiesByClass(Entity.class, (new Box(_center, _center)).expand(7.0D), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.squaredDistanceTo(_center))).toList();
            for (Entity entityiterator : _entfound) {
                if (!(entityiterator instanceof LivingEntity _livEnt)) continue;
                if (_livEnt.getMainHandStack().getItem() == RainimatorItems.RAIN_SWORD) {
                    entityiterator.damage(DamageUtil.build(entity, DamageTypes.GENERIC), 0.0F);
                    continue;
                }
                if (entityiterator instanceof MobEntity _entity)
                    _entity.getNavigation().stop();
                SoundUtil.playSound(world, _center.x, _center.y, _center.z, new Identifier(RainimatorMod.MOD_ID, "rain_sword_skill"), 1.0F, 1.0F);

                for (Triple<Integer, Integer, Integer> place : places)
                    world.setBlockState(new BlockPos((int) (entityiterator.getX() + place.getLeft()), (int) (entityiterator.getY() + place.getMiddle()), (int) (entityiterator.getZ() + place.getRight())), Blocks.ICE.getDefaultState(), 3);
                if (!_livEnt.getWorld().isClient()) {
                    _livEnt.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 540, 4));
                    _livEnt.addStatusEffect(new StatusEffectInstance(RainimatorEffects.ICE_PEOPLE.get(), 500, 0));
                }
                if (entity instanceof PlayerEntity)
                    entity.getItemCooldownManager().set(ar.getValue().getItem(), 1200);

                Timeout.create(500, () -> {
                    SoundUtil.playSound(world, _center.x, _center.y, _center.z, new Identifier(RainimatorMod.MOD_ID, "rain_sword_skill_2"), 5.0F, 1.0F);
                    for (Triple<Integer, Integer, Integer> place : places)
                        world.setBlockState(new BlockPos((int) (entityiterator.getX() + place.getLeft()), (int) (entityiterator.getY() + place.getMiddle()), (int) (entityiterator.getZ() + place.getRight())), Blocks.AIR.getDefaultState(), 3);
                    entityiterator.damage(DamageUtil.build(entity, DamageTypes.MAGIC), 5.0F);
                });
            }
        }
        return ar;
    }

    @Override
    public boolean onSwingHand(ItemStack itemtack, World world, double x, double y, double z) {
        boolean ret_val = super.onSwingHand(itemtack, world, x, y, z);
        if (Math.random() < 0.2D)
            ParticleUtil.spawnCircleParticles(world, ParticleTypes.ENCHANT, x, y, z, 3, 0, 50);
        return ret_val;
    }


    @Override
    public void inventoryTick(ItemStack itemtack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(itemtack, world, entity, slot, selected);
        if (selected) {
            entity.extinguish();

            Vec3d _center = entity.getPos();
            List<Entity> _ent_found = world.getEntitiesByClass(Entity.class, (new Box(_center, _center)).expand(7.0D), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.squaredDistanceTo(_center))).toList();
            for (Entity entityIterator : _ent_found) {
                if (!(entityIterator instanceof LivingEntity _livEnt)) continue;
                if (_livEnt.getMainHandStack().getItem() == RainimatorItems.RAIN_SWORD) {
                    if (itemtack.damage(0, world.getRandom(), null)) {
                        itemtack.decrement(1);
                        itemtack.setDamage(0);
                    }
                    continue;
                }
                if (!_livEnt.hasStatusEffect(StatusEffects.SLOWNESS)) {
                    if (!_livEnt.getWorld().isClient()) {
                        _livEnt.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 60, 0));
                        _livEnt.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 60, 0));
                    }
                    entityIterator.setFrozenTicks(2);
                    if (Math.random() < 0.04D) {
                        if (!_livEnt.getWorld().isClient())
                            _livEnt.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 60, 1));
                        entityIterator.setFrozenTicks(4);
                        Runnable callback = () -> {
                            if (!_livEnt.getWorld().isClient())
                                _livEnt.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 60, 2));
                            entityIterator.setFrozenTicks(6);
                        };
                        Timeout.create(60, callback);
                        Timeout.create(120, callback);
                        Timeout.create(180, () -> {
                            if (!_livEnt.getWorld().isClient()) {
                                _livEnt.addStatusEffect(new StatusEffectInstance(RainimatorEffects.ICE_PEOPLE.get(), 100, 0));
                                _livEnt.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 4));
                            }
                            SoundUtil.playSound(world, _center.x, _center.y, _center.z, new Identifier("block.conduit.activate"), 1.0F, 1.0F);
                            entityIterator.setFrozenTicks(10);
                        });
                    }
                }
            }
        }
    }

    @Override
    public Episode getEpisode() {
        return Episode.ColdAsIce;
    }
}
