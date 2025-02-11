package dev.rainimator.mod.item.sword;

import com.iafenvoy.neptune.object.DamageUtil;
import com.iafenvoy.neptune.object.*;
import com.iafenvoy.neptune.object.item.SwordItemBase;
import com.iafenvoy.neptune.object.item.ToolMaterialUtil;
import com.iafenvoy.neptune.util.RandomHelper;
import com.iafenvoy.neptune.util.Timeout;
import dev.rainimator.mod.data.ManaData;
import dev.rainimator.mod.config.ServerConfig;
import dev.rainimator.mod.impl.ComponentManager;
import dev.rainimator.mod.registry.RainimatorItemGroups;
import dev.rainimator.mod.registry.RainimatorItems;
import dev.rainimator.mod.registry.RainimatorParticles;
import dev.rainimator.mod.registry.RainimatorSounds;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

import java.util.Comparator;
import java.util.List;

public class BlueDiamondSwordItem extends SwordItemBase {
    public BlueDiamondSwordItem() {
        super(ToolMaterialUtil.of(3000, 4.0F, 15.0F, 0, 30, RainimatorItems.BLUE_DIAMOND::get), 3, -2.0F, new Settings().fireproof().arch$tab(RainimatorItemGroups.ITEM));
    }

    @Override
    public boolean postHit(ItemStack itemtack, LivingEntity entity, LivingEntity sourceentity) {
        boolean retval = super.postHit(itemtack, entity, sourceentity);
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();
        if (entity instanceof LivingEntity)
            if (entity.hasStatusEffect(StatusEffects.GLOWING) && Math.random() < 0.4D)
                entity.damage(DamageUtil.build(sourceentity, DamageTypes.MAGIC), 5.0F);
        if (Math.random() < 0.1D) {
            if (entity.getWorld() instanceof ServerWorld _level) {
                LightningEntity entityToSpawn = EntityType.LIGHTNING_BOLT.create(_level);
                if (entityToSpawn != null) {
                    entityToSpawn.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(new BlockPos((int) x, (int) y, (int) z)));
                    entityToSpawn.setCosmetic(true);
                    _level.spawnEntity(entityToSpawn);
                }
            }
            entity.getWorld().setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.FIRE.getDefaultState(), 3);
        }

        return retval;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity entity, Hand hand) {
        TypedActionResult<ItemStack> ar = super.use(world, entity, hand);
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();
        ItemStack itemtack = ar.getValue();
        final Vec3d _center = new Vec3d(x, y, z);
        ManaData data = ComponentManager.getManaData(entity);
        if (entity.isSneaking() && data.tryUseMana(entity, ServerConfig.getInstance().blue_diamond_sword)) {
            List<Entity> _entfound = world.getEntitiesByClass(Entity.class, new Box(_center, _center).expand(16 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.squaredDistanceTo(_center))).toList();
            for (Entity entityiterator : _entfound) {
                if ((entityiterator instanceof LivingEntity _livEnt ? _livEnt.getMainHandStack() : ItemStack.EMPTY).getItem() == RainimatorItems.BLUE_DIAMOND_SWORD.get()) {
                    if (itemtack.damage(0, entity.getRandom(), null)) {
                        itemtack.decrement(1);
                        itemtack.setDamage(0);
                    }
                } else {
                    if (entityiterator instanceof LivingEntity _livEnt && _livEnt.getGroup() == EntityGroup.ARTHROPOD) {
                        if (world instanceof ServerWorld _level)
                            _level.spawnParticles(ParticleTypes.END_ROD, x, y, z, 50, 0.5, 0.5, 0.5, 0.2);
                        SoundUtil.playSound(world, x, y, z, RainimatorSounds.BLUED_DIAMOND_SKILL_1.get(), 5, 1);
                        if (!_livEnt.getWorld().isClient)
                            _livEnt.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 1200, 0));
                        entityiterator.setOnFireFor(10);
                        entity.getItemCooldownManager().set(itemtack.getItem(), 1000);
                        if (!entity.getWorld().isClient)
                            entity.sendMessage(Text.translatable("item.rainimator.blue_diamond_sword.skill1"), true);
                        if (world.isClient)
                            MinecraftClient.getInstance().gameRenderer.showFloatingItem(itemtack);
                        Runnable callback = () -> {
                            if (entityiterator.isAlive()) {
                                if (world instanceof ServerWorld _level)
                                    EntityUtil.lightening(_level, entityiterator.getX(), entityiterator.getY(), entityiterator.getZ());
                                world.setBlockState(VecUtil.createBlockPos(entityiterator.getX(), entityiterator.getY(), entityiterator.getZ()), Blocks.FIRE.getDefaultState(), 3);
                                entityiterator.damage(DamageUtil.build(entity, DamageTypes.MAGIC), 5);
                            }
                        };
                        Timeout.create(40, callback);
                        Timeout.create(80, callback);
                        Timeout.create(120, callback);
                        Timeout.create(160, callback);
                        Timeout.create(200, callback);
                    } else if ((entityiterator instanceof LivingEntity _livEnt && _livEnt.getGroup() == EntityGroup.DEFAULT)) {
                        if ((WorldAccess) world instanceof ServerWorld _level)
                            _level.spawnParticles(ParticleTypes.END_ROD, x, y, z, 50, 0.5, 0.5, 0.5, 0.2);
                        SoundUtil.playSound(world, x, y, z, RainimatorSounds.BLUE_DIAMOND_SKILL_2.get(), 5, 1);
                        if (!_livEnt.getWorld().isClient)
                            _livEnt.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 1200, 0));
                        entityiterator.setOnFireFor(10);
                        entity.getItemCooldownManager().set(itemtack.getItem(), 1000);
                        if (!entity.getWorld().isClient)
                            entity.sendMessage(Text.translatable("item.rainimator.blue_diamond_sword.skill2"), true);
                        if (world.isClient)
                            MinecraftClient.getInstance().gameRenderer.showFloatingItem(itemtack);

                        Runnable callback1 = () -> {
                            if (entityiterator.isAlive()) {
                                if (!world.isClient)
                                    world.createExplosion(null, (entityiterator.getX()), (entityiterator.getY()), (entityiterator.getZ()), 2, World.ExplosionSourceType.NONE);
                                entityiterator.damage(DamageUtil.build(entity, DamageTypes.MAGIC), 5);
                            }
                        };
                        Timeout.create(40, callback1);
                        Timeout.create(80, callback1);
                        Timeout.create(120, callback1);
                        Timeout.create(160, callback1);
                        Timeout.create(200, callback1);
                    } else if (entityiterator instanceof LivingEntity _livEnt && (_livEnt.getGroup() == EntityGroup.UNDEAD || _livEnt.getGroup() == EntityGroup.ILLAGER)) {
                        entityiterator.setOnFireFor(10);
                        if (!_livEnt.getWorld().isClient) {
                            _livEnt.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 1200, 0));
                            _livEnt.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 160, 2));
                            _livEnt.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 160, 0));
                        }
                        SoundUtil.playSound(world, x, y, z, RainimatorSounds.BLUE_DIAMOND_SKILL_3.get(), 5, 1);
                        if (world instanceof ServerWorld _level)
                            _level.spawnParticles(ParticleTypes.END_ROD, x, y, z, 50, 0.5, 0.5, 0.5, 0.2);
                        entity.getItemCooldownManager().set(itemtack.getItem(), 1000);
                        if (!entity.getWorld().isClient)
                            entity.sendMessage(Text.translatable("item.rainimator.blue_diamond_sword.skill3"), true);
                        if (world.isClient)
                            MinecraftClient.getInstance().gameRenderer.showFloatingItem(itemtack);
                        Runnable callback1 = () -> {
                            if (world instanceof ServerWorld _level)
                                _level.spawnParticles(RainimatorParticles.FLOWER_WHITE.get(), entityiterator.getX(), entityiterator.getY(), entityiterator.getZ(), 50, 0.5, 2, 0.5, 0.2);
                        };
                        Runnable callback2 = () -> {
                            entityiterator.requestTeleport(x + RandomHelper.nextDouble(-1, 1), y + 2, z + RandomHelper.nextDouble(-1, 1));
                            if (entityiterator instanceof ServerPlayerEntity _serverPlayer)
                                _serverPlayer.networkHandler.requestTeleport(x + RandomHelper.nextDouble(-1, 1), y + 2, z + RandomHelper.nextDouble(-1, 1), entityiterator.getYaw(), entityiterator.getPitch());
                        };
                        Runnable callback3 = () -> {
                            SoundUtil.playSound(world, x, y, z, RainimatorSounds.BLUE_DIAMOND_SKILL_4.get(), 5, 1);
                            callback1.run();
                            entityiterator.requestTeleport(x + RandomHelper.nextDouble(-1, 1), y + 2, z + RandomHelper.nextDouble(-1, 1));
                            if (entityiterator instanceof ServerPlayerEntity _serverPlayer)
                                _serverPlayer.networkHandler.requestTeleport(x + RandomHelper.nextDouble(-1, 1), y + 2, z + RandomHelper.nextDouble(-1, 1), entityiterator.getYaw(), entityiterator.getPitch());
                        };
                        Timeout.create(20, callback3);
                        Timeout.create(40, callback3);
                        Timeout.create(60, callback1);
                        Timeout.create(80, callback1);
                        Timeout.create(100, callback1);
                        Timeout.create(120, callback1);
                        Timeout.create(140, callback1);
                        Timeout.create(155, callback2);
                        Timeout.create(160, () -> {
                            if (entityiterator.isAlive()) {
                                if (world instanceof ServerWorld _level) {
                                    LightningEntity entityToSpawn = EntityType.LIGHTNING_BOLT.create(_level);
                                    if (entityToSpawn != null) {
                                        entityToSpawn.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(new BlockPos((int) entityiterator.getX(), (int) entityiterator.getY(), (int) entityiterator.getZ())));
                                        entityToSpawn.setCosmetic(true);
                                        _level.spawnEntity(entityToSpawn);
                                    }
                                }
                                if (!world.isClient)
                                    world.createExplosion(null, entityiterator.getX(), entityiterator.getY(), entityiterator.getZ(), 8, World.ExplosionSourceType.NONE);
                                if (world instanceof ServerWorld _level)
                                    _level.spawnParticles(RainimatorParticles.LIGHTENING_ARC.get(), entityiterator.getX(), entityiterator.getY(), entityiterator.getZ(), 50, 0.5, 1, 0.5, 0.2);
                            }
                        });
                    }
                }
            }
        }
        return ar;
    }

    @Override
    public boolean onSwingHand(ItemStack itemtack, World world, double x, double y, double z) {
        boolean retval = super.onSwingHand(itemtack, world, x, y, z);
        if (Math.random() < 0.2D)
            ParticleUtil.spawnCircleParticles(world, ParticleTypes.SOUL_FIRE_FLAME, x, y, z, 2, 0, 50);
        return retval;
    }
}