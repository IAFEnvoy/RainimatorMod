package dev.rainimator.mod.item.sword;

import com.iafenvoy.neptune.object.DamageUtil;
import com.iafenvoy.neptune.object.ParticleUtil;
import com.iafenvoy.neptune.object.SoundUtil;
import com.iafenvoy.neptune.object.item.SwordItemBase;
import com.iafenvoy.neptune.object.item.ToolMaterialUtil;
import com.iafenvoy.neptune.util.RandomHelper;
import com.iafenvoy.neptune.util.Timeout;
import dev.rainimator.mod.data.component.ManaData;
import dev.rainimator.mod.data.config.ServerConfig;
import dev.rainimator.mod.impl.ComponentManager;
import dev.rainimator.mod.registry.RainimatorItemGroups;
import dev.rainimator.mod.registry.RainimatorItems;
import dev.rainimator.mod.registry.RainimatorParticles;
import dev.rainimator.mod.registry.RainimatorSounds;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;

public class ZecanirnTheBladeItem extends SwordItemBase {
    public ZecanirnTheBladeItem() {
        super(ToolMaterialUtil.of(1500, 4.0F, 11.0F, 0, 20, RainimatorItems.SUPER_SAPPHIRE::get, RainimatorItems.SUPER_RUBY::get), 3, -2.2F, new Settings().arch$tab(RainimatorItemGroups.MAIN));
    }

    @Override
    public boolean postHit(ItemStack itemtack, LivingEntity entity, LivingEntity sourceentity) {
        boolean ret_val = super.postHit(itemtack, entity, sourceentity);
        if (entity instanceof MobEntity _entity) _entity.setTarget(sourceentity);
        if (sourceentity.getMainHandStack().getItem() == RainimatorItems.ZECANIRN_THE_BLADE.get())
            if (Math.random() < 0.9D) {
                if (entity.getWorld() instanceof ServerWorld _level)
                    _level.spawnParticles(RainimatorParticles.PURPLE_LIGHT.get(), entity.getX(), entity.getY(), entity.getZ(), 50, 0.5D, 1.0D, 0.5D, 0.2D);
                SoundUtil.playSound(entity.getWorld(), entity.getX(), entity.getY(), entity.getZ(), RainimatorSounds.SWORD_TELEPORT.get(), 4.0F, 1.0F);

                BlockPos blockPos = entity.getWorld().raycast(new RaycastContext(entity.getCameraPosVec(1.0F), entity.getCameraPosVec(1.0F).add(entity.getRotationVec(1.0F).multiply(-2.0D)), RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, entity)).getBlockPos();
                sourceentity.requestTeleport(blockPos.getX(), entity.getY() + 0.5D, blockPos.getZ());
                if (sourceentity instanceof ServerPlayerEntity _serverPlayer)
                    _serverPlayer.networkHandler.requestTeleport(blockPos.getX(), entity.getY() + 0.5D, blockPos.getZ(), sourceentity.getYaw(), sourceentity.getPitch());

                entity.getWorld().breakBlock(new BlockPos((int) sourceentity.getX(), (int) entity.getY(), (int) sourceentity.getZ()), false);
                entity.getWorld().breakBlock(new BlockPos((int) sourceentity.getX(), (int) (entity.getY() + 1.0D), (int) sourceentity.getZ()), false);
                entity.getWorld().breakBlock(new BlockPos((int) sourceentity.getX(), (int) (entity.getY() + 2.0D), (int) sourceentity.getZ()), false);

                if (itemtack.damage(RandomHelper.nextInt(1, 5), entity.getRandom(), null)) {
                    itemtack.decrement(1);
                    itemtack.setDamage(0);
                }
            } else {
                entity.damage(DamageUtil.build(entity, DamageTypes.MAGIC), 3.0F);
                if (entity instanceof LivingEntity) {
                    if (!entity.getWorld().isClient)
                        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 100, 0));
                }

                if (itemtack.damage(RandomHelper.nextInt(1, 5), entity.getRandom(), null)) {
                    itemtack.decrement(1);
                    itemtack.setDamage(0);
                }
                Timeout.create(100, () -> {
                    if (!entity.getWorld().isClient)
                        entity.getWorld().createExplosion(null, entity.getX(), entity.getY() + 1.0D, entity.getZ(), 1.0F, World.ExplosionSourceType.NONE);
                });
            }
        return ret_val;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity entity, Hand hand) {
        TypedActionResult<ItemStack> ar = super.use(world, entity, hand);
        ManaData data = ComponentManager.getManaData(entity);
        if (entity.isSneaking() && data.tryUseMana(entity, ServerConfig.getInstance().zecanirn_the_blade)) {
            Vec3d _center = entity.getPos();
            List<Entity> _entfound = world.getEntitiesByClass(Entity.class, (new Box(_center, _center)).expand(8.0D), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.squaredDistanceTo(_center))).toList();
            for (Entity entityiterator : _entfound) {
                if (!(entityiterator instanceof LivingEntity _livEnt)) continue;
                if (_livEnt.getMainHandStack().getItem() == RainimatorItems.ZECANIRN_THE_BLADE.get()) {
                    if (entity instanceof PlayerEntity) entity.getItemCooldownManager().set(ar.getValue().getItem(), 0);
                    continue;
                }
                if (entity.getHealth() > 5.0F) {
                    entity.damage(DamageUtil.build(entity, DamageTypes.MAGIC), 5.0F);
                    ItemStack _setstack = new ItemStack(Blocks.AIR);
                    _setstack.setCount(1);
                    entity.setStackInHand(Hand.OFF_HAND, _setstack);
                    entity.getInventory().markDirty();
                    entityiterator.damage(DamageUtil.build(entity, DamageTypes.MAGIC), 10.0F);
                    if (world instanceof ServerWorld _level)
                        _level.spawnParticles(RainimatorParticles.PURPLE_LIGHT.get(), entity.getX(), entity.getY(), entity.getZ(), 40, 0.5D, 0.5D, 0.5D, 0.5D);
                    SoundUtil.playSound(world, entity.getX(), entity.getY(), entity.getZ(), RainimatorSounds.BLACK_DEATH_SWORD_SKILL.get(), 4.0F, 1.0F);

                    Runnable callback = () -> {
                        entity.requestTeleport(entity.getX(), entity.getY(), entity.getZ());
                        if ((Entity) entity instanceof ServerPlayerEntity _serverPlayer)
                            _serverPlayer.networkHandler.requestTeleport(entity.getX(), entity.getY(), entity.getZ(), entity.getYaw(), entity.getPitch());

                        entityiterator.damage(DamageUtil.build(entity, DamageTypes.MAGIC), 15.0F);
                        if (world instanceof ServerWorld _level) {
                            for (double z = -7; z <= 7; z++)
                                _level.spawnParticles(RainimatorParticles.PURPLE_LIGHT.get(), entity.getX(), entity.getY(), entity.getZ() + z, 20, 0.2D, 0.5D, 0.2D, 0.2D);
                            for (double x = -7; x <= 7; x++)
                                _level.spawnParticles(RainimatorParticles.PURPLE_LIGHT.get(), entity.getX() + x, entity.getY(), entity.getZ(), 20, 0.2D, 0.5D, 0.2D, 0.2D);
                        }
                        SoundUtil.playSound(world, entity.getX(), entity.getY(), entity.getZ(), RainimatorSounds.BLACK_DEATH_SWORD_SKILL.get(), 4.0F, 1.0F);
                    };
                    Timeout.create(5, callback);
                    Timeout.create(10, callback);
                    Timeout.create(15, callback);
                    Timeout.create(20, callback);
                    Timeout.create(25, callback);
                    entity.getItemCooldownManager().set(ar.getValue().getItem(), 1200);
                    continue;
                }
                if (!entity.getWorld().isClient)
                    entity.sendMessage(Text.translatable("item.rainimator.zecanirn_the_blade.error.health"), true);
            }
        }
        return ar;
    }

    @Override
    public boolean onSwingHand(ItemStack itemtack, World world, double x, double y, double z) {
        boolean ret_val = super.onSwingHand(itemtack, world, x, y, z);
        if (Math.random() < 0.2D)
            ParticleUtil.spawnCircleParticles(world, RainimatorParticles.PURPLE_LIGHT.get(), x, y, z, 4, 0, 50);
        return ret_val;
    }
}
