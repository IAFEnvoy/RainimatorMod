package dev.rainimator.mod.item.sword;

import dev.rainimator.mod.RainimatorMod;
import dev.rainimator.mod.data.component.ManaData;
import dev.rainimator.mod.data.config.ServerConfig;
import dev.rainimator.mod.impl.ComponentManager;
import dev.rainimator.mod.registry.util.FoilSwordItemBase;
import dev.rainimator.mod.registry.util.ToolMaterialUtil;
import dev.rainimator.mod.registry.RainimatorItemGroups;
import dev.rainimator.mod.registry.RainimatorItems;
import dev.rainimator.mod.registry.util.IRainimatorInfo;
import dev.rainimator.mod.util.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

public class BlackBoneTheBladeItem extends FoilSwordItemBase implements IRainimatorInfo {
    public BlackBoneTheBladeItem() {
        super(ToolMaterialUtil.of(1500, 0.0F, 7.0F, 0, 10, RainimatorItems.RUBY), 3, -2.4F, new Settings().fireproof().arch$tab(RainimatorItemGroups.MAIN));
    }

    @Override
    public boolean postHit(ItemStack itemtack, LivingEntity entity, LivingEntity sourceentity) {
        boolean retval = super.postHit(itemtack, entity, sourceentity);
        if (entity instanceof MobEntity _entity)
            if (sourceentity instanceof LivingEntity)
                _entity.setTarget(sourceentity);
        entity.setOnFireFor(8);
        if (Math.random() < 0.1D)
            entity.damage(DamageUtil.build(sourceentity, DamageTypes.MAGIC), RandomHelper.nextInt(1, 3));
        if (Math.random() < 0.3D)
            sourceentity.setHealth(sourceentity.getHealth() + RandomHelper.nextInt(2, 5));
        return retval;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity entity, Hand hand) {
        TypedActionResult<ItemStack> ar = super.use(world, entity, hand);
        double x = entity.getX();
        final double y = entity.getY();
        double z = entity.getZ();
        ItemStack itemtack = ar.getValue();
        ManaData data = ComponentManager.getManaData(entity);
        if (entity.isSneaking() && data.tryUseMana(entity, ServerConfig.getInstance().blackbone_the_blade)) {
            SoundUtil.playSound(world, x, y, z, Identifier.of(RainimatorMod.MOD_ID, "blackbone_living"), 10.0F, 1.0F);
            if (world instanceof ServerWorld _level)
                _level.spawnParticles(ParticleTypes.ELECTRIC_SPARK, x, y, z, 25, 1.0D, 1.0D, 1.0D, 1.0D);
            if (!world.isClient())
                world.createExplosion(null, entity.getWorld().raycast(new RaycastContext(entity.getCameraPosVec(1.0F), entity.getCameraPosVec(1.0F).add(entity.getRotationVec(1.0F).multiply(2.0D)), RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, entity)).getBlockPos().getX(), y + 1.0D,
                        entity.getWorld().raycast(new RaycastContext(entity.getCameraPosVec(1.0F), entity.getCameraPosVec(1.0F).add(entity.getRotationVec(1.0F).multiply(2.0D)), RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, entity)).getBlockPos().getZ(), 1.0F, World.ExplosionSourceType.NONE);

            entity.getItemCooldownManager().set(itemtack.getItem(), 700);

            Runnable callback = () -> {
                if (world instanceof World) {
                    if (!world.isClient())
                        world.createExplosion(null,
                                entity.getWorld().raycast(new RaycastContext(entity.getCameraPosVec(1.0F), entity.getCameraPosVec(1.0F).add(entity.getRotationVec(1.0F).multiply(12.0D)), RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, entity)).getBlockPos().getX(), y + 1.0D,
                                entity.getWorld().raycast(new RaycastContext(entity.getCameraPosVec(1.0F), entity.getCameraPosVec(1.0F).add(entity.getRotationVec(1.0F).multiply(12.0D)), RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, entity)).getBlockPos().getZ(), 2.0F, World.ExplosionSourceType.NONE);
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
    public boolean onSwingHand(ItemStack itemtack, World world, double x, double y, double z) {
        boolean retval = super.onSwingHand(itemtack, world, x, y, z);
        if (Math.random() < 0.2D)
            ParticleUtil.spawnCircleParticles(world, ParticleTypes.LAVA, x, y, z, 4, 0, 50);
        return retval;
    }

    @Override
    public Episode getEpisode() {
        return Episode.WeAreTheDanger;
    }
}
