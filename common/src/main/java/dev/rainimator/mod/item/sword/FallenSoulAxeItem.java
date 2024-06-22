package dev.rainimator.mod.item.sword;

import dev.rainimator.mod.data.component.ManaData;
import dev.rainimator.mod.data.config.ServerConfig;
import dev.rainimator.mod.impl.ComponentManager;
import dev.rainimator.mod.item.util.FoilSwordItemBase;
import dev.rainimator.mod.item.util.ToolMaterialUtil;
import dev.rainimator.mod.registry.RainimatorItemGroups;
import dev.rainimator.mod.registry.RainimatorItems;
import dev.rainimator.mod.util.SoundUtil;
import dev.rainimator.mod.util.Timeout;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Comparator;
import java.util.List;

public class FallenSoulAxeItem extends FoilSwordItemBase {
    public FallenSoulAxeItem() {
        super(ToolMaterialUtil.of(1000, 0.0F, 6.0F, 0, 10), 3, -2.3F, new Settings().fireproof().arch$tab(RainimatorItemGroups.MAIN));
    }

    @Override
    public boolean postHit(ItemStack itemtack, LivingEntity entity, LivingEntity sourceentity) {
        boolean retval = super.postHit(itemtack, entity, sourceentity);
        if (!entity.getWorld().isClient())
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 80, 0));
        return retval;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity entity, Hand hand) {
        TypedActionResult<ItemStack> ar = super.use(world, entity, hand);
        final double x = entity.getX();
        final double y = entity.getY();
        final double z = entity.getZ();
        ItemStack itemtack = ar.getValue();
        Vec3d _center = new Vec3d(x, y, z);
        ManaData data = ComponentManager.getManaData(entity);
        if (entity.isSneaking() && data.tryUseMana(entity, ServerConfig.getInstance().fallen_soul_axe)) {
            List<Entity> _entfound = world.getEntitiesByClass(Entity.class, (new Box(_center, _center)).expand(5.0D), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.squaredDistanceTo(_center))).toList();
            for (Entity entityiterator : _entfound) {
                LivingEntity _livEnt = (LivingEntity) entityiterator;
                if (((entityiterator instanceof LivingEntity) ? _livEnt.getMainHandStack() : ItemStack.EMPTY).getItem() == RainimatorItems.FALLEN_SOUL_AXE.get()) {
                    if (itemtack.damage(0, entity.getRandom(), null)) {
                        itemtack.decrement(1);
                        itemtack.setDamage(0);
                    }
                    continue;
                }
                if (entityiterator instanceof LivingEntity _entity)
                    if (!_entity.getWorld().isClient())
                        _entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 60, 0));

                entityiterator.requestTeleport(x, y + 1.0D, z);
                if (entityiterator instanceof ServerPlayerEntity _serverPlayer)
                    _serverPlayer.networkHandler.requestTeleport(x, y + 1.0D, z, entityiterator.getYaw(), entityiterator.getPitch());

                if (entityiterator instanceof LivingEntity _entity)
                    if (!_entity.getWorld().isClient())
                        _entity.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 50, 4));

                for (double i = 2; i <= 5; i++) {
                    double finalI = i;
                    Timeout.create((int) i * 2 - 2, () -> {
                        entityiterator.requestTeleport(x, y + finalI, z);
                        if (entityiterator instanceof ServerPlayerEntity _serverPlayer)
                            _serverPlayer.networkHandler.requestTeleport(x, y + finalI, z, entityiterator.getYaw(), entityiterator.getPitch());
                    });
                }
                SoundUtil.playSound(world, x, y, z, Identifier.tryParse("entity.wither.spawn"), 10.0F, 1.0F);
                if (world instanceof ServerWorld _level)
                    _level.spawnParticles(ParticleTypes.SOUL, x, y, z, 100, 3.0D, 4.0D, 3.0D, 1.0E-4D);
                entity.getItemCooldownManager().set(itemtack.getItem(), 600);
            }
        }
        return ar;
    }
}
