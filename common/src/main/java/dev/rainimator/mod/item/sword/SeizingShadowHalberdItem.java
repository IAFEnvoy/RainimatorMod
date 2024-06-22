package dev.rainimator.mod.item.sword;

import dev.rainimator.mod.RainimatorMod;
import dev.rainimator.mod.data.component.ManaData;
import dev.rainimator.mod.data.config.ServerConfig;
import dev.rainimator.mod.impl.ComponentManager;
import dev.rainimator.mod.registry.util.SwordItemBase;
import dev.rainimator.mod.registry.util.ToolMaterialUtil;
import dev.rainimator.mod.registry.RainimatorEffects;
import dev.rainimator.mod.registry.RainimatorItemGroups;
import dev.rainimator.mod.registry.util.IRainimatorInfo;
import dev.rainimator.mod.util.Episode;
import dev.rainimator.mod.util.ParticleUtil;
import dev.rainimator.mod.util.SoundUtil;
import dev.rainimator.mod.util.Timeout;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.EvokerFangsEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.DragonFireballEntity;
import net.minecraft.entity.projectile.ExplosiveProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

import java.util.Comparator;
import java.util.List;

public class SeizingShadowHalberdItem extends SwordItemBase implements IRainimatorInfo {
    public SeizingShadowHalberdItem() {
        super(ToolMaterialUtil.of(2000, 0.0F, 13.0F, 0, 20), 3, -2.2F, new Settings().fireproof().arch$tab(RainimatorItemGroups.MAIN));
    }

    @Override
    public boolean postHit(ItemStack itemtack, LivingEntity entity, LivingEntity sourceentity) {
        boolean retval = super.postHit(itemtack, entity, sourceentity);
        if (Math.random() < 0.1D)
            if (!entity.getWorld().isClient())
                entity.addStatusEffect(new StatusEffectInstance(RainimatorEffects.SHADOW_EROSION.get(), 200, 0));
        return retval;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity entity, Hand hand) {
        TypedActionResult<ItemStack> ar = super.use(world, entity, hand);
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();
        ItemStack itemtack = ar.getValue();

        ManaData data = ComponentManager.getManaData(entity);
        if (!data.tryUseMana(entity, ServerConfig.getInstance().seizing_shadow_halberd))
            return ar;
        final Vec3d _center = new Vec3d(x, y, z);
        List<Entity> _entfound = world.getEntitiesByClass(Entity.class, new Box(_center, _center).expand(12 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.squaredDistanceTo(_center))).toList();
        for (Entity entityiterator : _entfound) {
            if ((entityiterator instanceof LivingEntity _livEnt && _livEnt.hasStatusEffect(RainimatorEffects.SHADOW_EROSION.get()))) {
                if ((Entity) entity instanceof PlayerEntity _player)
                    _player.getItemCooldownManager().set(itemtack.getItem(), 300);

                BlockPos pos = entity.getWorld().raycast(new RaycastContext(entity.getCameraPosVec(1f), entity.getCameraPosVec(1f).add(entity.getRotationVec(1f).multiply(3)), RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, entity)).getBlockPos();
                BlockPos pos1 = entity.getWorld().raycast(new RaycastContext(entity.getCameraPosVec(1f), entity.getCameraPosVec(1f).add(entity.getRotationVec(1f).multiply(1)), RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, entity)).getBlockPos();
                BlockPos pos2 = entity.getWorld().raycast(new RaycastContext(entity.getCameraPosVec(1f), entity.getCameraPosVec(1f).add(entity.getRotationVec(1f).multiply(2)), RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, entity)).getBlockPos();

                entityiterator.requestTeleport(pos.getX(), y, pos.getZ());
                if (entityiterator instanceof ServerPlayerEntity _serverPlayer)
                    _serverPlayer.networkHandler.requestTeleport(pos.getX(), y, pos.getZ(), entityiterator.getYaw(), entityiterator.getPitch());

                if ((WorldAccess) world instanceof ServerWorld _level) {
                    Entity entityToSpawn = new EvokerFangsEntity(EntityType.EVOKER_FANGS, _level);
                    entityToSpawn.refreshPositionAndAngles(pos1.getX(), y, pos1.getZ(), ((WorldAccess) world).getRandom().nextFloat() * 360F, 0);
                    world.spawnEntity(entityToSpawn);
                }
                Runnable callback = () -> {
                    if (world instanceof ServerWorld _level) {
                        Entity entityToSpawn = new EvokerFangsEntity(EntityType.EVOKER_FANGS, _level);
                        entityToSpawn.refreshPositionAndAngles(pos2.getX(), y, pos2.getZ(), world.getRandom().nextFloat() * 360F, 0);
                        world.spawnEntity(entityToSpawn);
                        Entity entityToSpawn1 = new EvokerFangsEntity(EntityType.EVOKER_FANGS, _level);
                        entityToSpawn1.refreshPositionAndAngles(pos1.getX(), y, pos2.getZ(), world.getRandom().nextFloat() * 360F, 0);
                        world.spawnEntity(entityToSpawn1);
                        Entity entityToSpawn2 = new EvokerFangsEntity(EntityType.EVOKER_FANGS, _level);
                        entityToSpawn2.refreshPositionAndAngles(pos2.getX(), y, pos1.getZ(), world.getRandom().nextFloat() * 360F, 0);
                        world.spawnEntity(entityToSpawn2);
                    }
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
            }
            if (entity.isSneaking()) {
                if ((Entity) entity instanceof PlayerEntity _player)
                    _player.getItemCooldownManager().set(itemtack.getItem(), 300);
                SoundUtil.playSound(world, x, y, z, Identifier.tryParse("entity.evoker.cast_spell"), 1, 1);
                Runnable callback = () -> {
                    if (world instanceof ServerWorld _level)
                        for (int i = -2; i <= 2; i += 2)
                            for (int j = -2; j <= 2; j += 2) {
                                Entity entityToSpawn = new EvokerFangsEntity(EntityType.EVOKER_FANGS, _level);
                                entityToSpawn.refreshPositionAndAngles(x + i, y, z + j, world.getRandom().nextFloat() * 360F, 0);
                                world.spawnEntity(entityToSpawn);
                            }
                };
                Timeout.create(0, callback);
                Timeout.create(4, callback);
                Timeout.create(8, callback);
                Timeout.create(12, callback);
                Timeout.create(16, callback);
                Timeout.create(20, callback);
            }
        }
        return ar;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        super.useOnBlock(context);
        WorldAccess world = context.getWorld();
        double x = context.getBlockPos().getX();
        double y = context.getBlockPos().getY();
        double z = context.getBlockPos().getZ();
        PlayerEntity entity = context.getPlayer();
        ItemStack itemtack = context.getStack();
        if (entity != null) {
            if (entity.isSneaking()) {
                SoundUtil.playSound((World) world, x, y, z, Identifier.of(RainimatorMod.MOD_ID, "fire_soul"), 1, 1);
                if (world instanceof ServerWorld _level)
                    _level.spawnParticles(ParticleTypes.DRAGON_BREATH, x, y, z, 500, 0, 20, 0, 0.0001);
                entity.getItemCooldownManager().set(itemtack.getItem(), 300);
                Timeout.create(60, () -> {
                    SoundUtil.playSound((World) world, x, y, z, Identifier.tryParse("entity.ender_dragon.shoot"), 1, 1);
                    if (world instanceof ServerWorld _level)
                        _level.spawnParticles(ParticleTypes.END_ROD, x, y, z, 200, 0, 10, 0, 0.001);
                    if (world instanceof ServerWorld projectileLevel) {
                        ExplosiveProjectileEntity _entityToSpawn = new DragonFireballEntity(EntityType.DRAGON_FIREBALL, projectileLevel);
                        _entityToSpawn.powerX = 0;
                        _entityToSpawn.powerY = -0.5;
                        _entityToSpawn.powerZ = 0;
                        _entityToSpawn.setPosition(x, (y + 5), z);
                        _entityToSpawn.setVelocity(0, (-1), 0, 1, 0);
                        projectileLevel.spawnEntity(_entityToSpawn);
                    }
                });
            } else {
                if (world instanceof ServerWorld _level) {
                    Entity entityToSpawn = new EvokerFangsEntity(EntityType.EVOKER_FANGS, _level);
                    entityToSpawn.refreshPositionAndAngles(x, (y + 1), z, world.getRandom().nextFloat() * 360F, 0);
                    world.spawnEntity(entityToSpawn);
                }
                entity.getItemCooldownManager().set(itemtack.getItem(), 20);
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public boolean onSwingHand(ItemStack itemtack, World world, double x, double y, double z) {
        boolean retval = super.onSwingHand(itemtack, world, x, y, z);
        if (Math.random() < 0.2D)
            ParticleUtil.spawnCircleParticles(world, ParticleTypes.END_ROD, x, y, z, 4, 0, 50);
        return retval;
    }

    @Override
    public Episode getEpisode() {
        return Episode.AlreadyDead;
    }
}
