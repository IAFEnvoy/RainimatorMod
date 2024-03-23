package com.rainimator.rainimatormod.item.sword;

import com.rainimator.rainimatormod.RainimatorMod;
import com.rainimator.rainimatormod.registry.ModEffects;
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
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.DragonFireball;
import net.minecraft.world.entity.projectile.EvokerFangs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public class SeizingShadowHalberdItem extends SwordItemBase implements IRainimatorInfo {
    public SeizingShadowHalberdItem() {
        super(TierBase.of(2000, 0.0F, 13.0F, 0, 20), 3, -2.2F, ModCreativeTab.createProperty().fireResistant());
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack itemstack, @NotNull LivingEntity entity, @NotNull LivingEntity sourceentity) {
        boolean retval = super.hurtEnemy(itemstack, entity, sourceentity);
        if (Math.random() < 0.1D)
            if (!entity.level.isClientSide())
                entity.addEffect(new MobEffectInstance(ModEffects.SHADOW_EROSION.get(), 200, 0));
        return retval;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level world, @NotNull Player entity, @NotNull InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();
        ItemStack itemstack = ar.getObject();
        {
            final Vec3 _center = new Vec3(x, y, z);
            List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(12 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
            for (Entity entityiterator : _entfound) {
                if ((entityiterator instanceof LivingEntity _livEnt && _livEnt.hasEffect(ModEffects.SHADOW_EROSION.get()))) {
                    if ((Entity) entity instanceof Player _player)
                        _player.getCooldowns().addCooldown(itemstack.getItem(), 300);

                    BlockPos pos = entity.level.clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(3)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity)).getBlockPos();
                    BlockPos pos1 = entity.level.clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(1)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos();
                    BlockPos pos2 = entity.level.clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(2)), ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, entity)).getBlockPos();

                    entityiterator.teleportTo(pos.getX(), y, pos.getZ());
                    if (entityiterator instanceof ServerPlayer _serverPlayer)
                        _serverPlayer.connection.teleport(pos.getX(), y, pos.getZ(), entityiterator.getYRot(), entityiterator.getXRot());

                    if ((LevelAccessor) world instanceof ServerLevel _level) {
                        Entity entityToSpawn = new EvokerFangs(EntityType.EVOKER_FANGS, _level);
                        entityToSpawn.moveTo(pos1.getX(), y, pos1.getZ(), ((LevelAccessor) world).getRandom().nextFloat() * 360F, 0);
                        world.addFreshEntity(entityToSpawn);
                    }
                    Runnable callback = () -> {
                        if (world instanceof ServerLevel _level) {
                            Entity entityToSpawn = new EvokerFangs(EntityType.EVOKER_FANGS, _level);
                            entityToSpawn.moveTo(pos2.getX(), y, pos2.getZ(), world.getRandom().nextFloat() * 360F, 0);
                            world.addFreshEntity(entityToSpawn);
                            Entity entityToSpawn1 = new EvokerFangs(EntityType.EVOKER_FANGS, _level);
                            entityToSpawn1.moveTo(pos1.getX(), y, pos2.getZ(), world.getRandom().nextFloat() * 360F, 0);
                            world.addFreshEntity(entityToSpawn1);
                            Entity entityToSpawn2 = new EvokerFangs(EntityType.EVOKER_FANGS, _level);
                            entityToSpawn2.moveTo(pos2.getX(), y, pos1.getZ(), world.getRandom().nextFloat() * 360F, 0);
                            world.addFreshEntity(entityToSpawn2);
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
                if (entity.isShiftKeyDown()) {
                    if ((Entity) entity instanceof Player _player)
                        _player.getCooldowns().addCooldown(itemstack.getItem(), 300);
                    SoundUtil.playSound(world, x, y, z, new ResourceLocation("entity.evoker.cast_spell"), 1, 1);
                    Runnable callback = () -> {
                        if (world instanceof ServerLevel _level)
                            for (int i = -2; i <= 2; i += 2)
                                for (int j = -2; j <= 2; j += 2) {
                                    Entity entityToSpawn = new EvokerFangs(EntityType.EVOKER_FANGS, _level);
                                    entityToSpawn.moveTo(x + i, y, z + j, world.getRandom().nextFloat() * 360F, 0);
                                    world.addFreshEntity(entityToSpawn);
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
        }
        return ar;
    }

    @Override
    public @NotNull InteractionResult useOn(@NotNull UseOnContext context) {
        super.useOn(context);
        LevelAccessor world = context.getLevel();
        double x = context.getClickedPos().getX();
        double y = context.getClickedPos().getY();
        double z = context.getClickedPos().getZ();
        Player entity = context.getPlayer();
        ItemStack itemstack = context.getItemInHand();
        if (entity != null) {
            if (entity.isShiftKeyDown()) {
                SoundUtil.playSound((Level) world,x,y,z,new ResourceLocation(RainimatorMod.MOD_ID,"fire_soul"), 1, 1);
                if (world instanceof ServerLevel _level)
                    _level.sendParticles(ParticleTypes.DRAGON_BREATH, x, y, z, 500, 0, 20, 0, 0.0001);
                entity.getCooldowns().addCooldown(itemstack.getItem(), 300);
                Timeout.create(60,()->{
                    SoundUtil.playSound((Level) world,x,y,z,new ResourceLocation("entity.ender_dragon.shoot"), 1, 1);
                    if (world instanceof ServerLevel _level)
                        _level.sendParticles(ParticleTypes.END_ROD, x, y, z, 200, 0, 10, 0, 0.001);
                    if (world instanceof ServerLevel projectileLevel) {
                        AbstractHurtingProjectile _entityToSpawn = new DragonFireball(EntityType.DRAGON_FIREBALL, projectileLevel);
                        _entityToSpawn.xPower = 0;
                        _entityToSpawn.yPower = -0.5;
                        _entityToSpawn.zPower = 0;
                        _entityToSpawn.setPos(x, (y + 5), z);
                        _entityToSpawn.shoot(0, (-1), 0, 1, 0);
                        projectileLevel.addFreshEntity(_entityToSpawn);
                    }
                });
            } else {
                if (world instanceof ServerLevel _level) {
                    Entity entityToSpawn = new EvokerFangs(EntityType.EVOKER_FANGS, _level);
                    entityToSpawn.moveTo(x, (y + 1), z, world.getRandom().nextFloat() * 360F, 0);
                    world.addFreshEntity(entityToSpawn);
                }
                entity.getCooldowns().addCooldown(itemstack.getItem(), 20);
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean onEntitySwing(ItemStack itemstack, LivingEntity entity) {
        boolean retval = super.onEntitySwing(itemstack, entity);
        if (Math.random() < 0.2D)
            ParticleUtil.spawnCircleParticles(entity.level, ParticleTypes.END_ROD, entity.getX(), entity.getY(), entity.getZ(), 4, 0, 50);
        return retval;
    }

    @Override
    public Episode getEpisode() {
        return Episode.AlreadyDead;
    }
}
