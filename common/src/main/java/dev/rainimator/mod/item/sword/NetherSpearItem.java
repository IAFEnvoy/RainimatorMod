package dev.rainimator.mod.item.sword;

import com.iafenvoy.neptune.object.ParticleUtil;
import com.iafenvoy.neptune.object.SoundUtil;
import com.iafenvoy.neptune.object.item.SwordItemBase;
import com.iafenvoy.neptune.object.item.ToolMaterialUtil;
import com.iafenvoy.neptune.util.RandomHelper;
import com.iafenvoy.neptune.util.Timeout;
import dev.rainimator.mod.RainimatorMod;
import dev.rainimator.mod.data.component.ManaData;
import dev.rainimator.mod.data.config.ServerConfig;
import dev.rainimator.mod.impl.ComponentManager;
import dev.rainimator.mod.registry.RainimatorItemGroups;
import dev.rainimator.mod.registry.RainimatorSounds;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

public class NetherSpearItem extends SwordItemBase {
    public NetherSpearItem() {
        super(ToolMaterialUtil.of(3000, 0.0F, 11.0F, 0, 25), 3, -2.2F, new Settings().fireproof().arch$tab(RainimatorItemGroups.MAIN));
    }

    @Override
    public boolean postHit(ItemStack itemtack, LivingEntity entity, LivingEntity sourceentity) {
        boolean retval = super.postHit(itemtack, entity, sourceentity);
        if (entity instanceof MobEntity _entity)
            _entity.setTarget(sourceentity);
        if (Math.random() < 0.2D)
            entity.setOnFireFor(10);
        if (Math.random() < 0.2D)
            sourceentity.setHealth(((sourceentity instanceof LivingEntity) ? sourceentity.getHealth() : -1.0F) + RandomHelper.nextInt(1, 4));
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
        if (entity.isSneaking() && data.tryUseMana(entity, ServerConfig.getInstance().nether_spear)) {
            SoundUtil.playSound(world, x, y, z, RainimatorSounds.NAEUS_SWORD_1.get(), 5.0F, 1.0F);
            BlockPos pos1 = entity.getWorld().raycast(new RaycastContext(entity.getCameraPosVec(1.0F), entity.getCameraPosVec(1.0F).add(entity.getRotationVec(1.0F).multiply(2.0D)), RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, entity)).getBlockPos();
            if (world instanceof ServerWorld _level) {
                LightningEntity entityToSpawn = EntityType.LIGHTNING_BOLT.create(_level);
                if (entityToSpawn != null) {
                    entityToSpawn.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(new BlockPos(pos1.getX(), (int) y, pos1.getZ())));
                    entityToSpawn.setCosmetic(true);
                    _level.spawnEntity(entityToSpawn);
                }
            }

            world.setBlockState(new BlockPos(pos1.getX(), (int) y, pos1.getZ()), Blocks.FIRE.getDefaultState(), 3);

            Runnable callback = () -> {
                BlockPos pos = entity.getWorld().raycast(new RaycastContext(entity.getCameraPosVec(1.0F), entity.getCameraPosVec(1.0F).add(entity.getRotationVec(1.0F).multiply(12.0D)), RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, entity)).getBlockPos();
                if (world instanceof ServerWorld _level) {
                    LightningEntity entityToSpawn = EntityType.LIGHTNING_BOLT.create(_level);
                    if (entityToSpawn != null) {
                        entityToSpawn.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(new BlockPos(pos.getX(), (int) y, pos.getZ())));
                        entityToSpawn.setCosmetic(true);
                        _level.spawnEntity(entityToSpawn);
                    }
                }
                world.setBlockState(new BlockPos(pos.getX(), (int) y, pos.getZ()), Blocks.FIRE.getDefaultState(), 3);
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

            if (entity instanceof PlayerEntity)
                entity.getItemCooldownManager().set(itemtack.getItem(), 1200);
        }

        return ar;
    }

    @Override
    public boolean onSwingHand(ItemStack itemtack, World world, double x, double y, double z) {
        boolean retval = super.onSwingHand(itemtack, world, x, y, z);
        if (Math.random() < 0.2D)
            ParticleUtil.spawnCircleParticles(world, ParticleTypes.FLAME, x, y, z, 4, 0, 50);
        return retval;
    }

    @Override
    public void inventoryTick(ItemStack itemtack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(itemtack, world, entity, slot, selected);
        if (selected && entity instanceof LivingEntity _livEnt && !_livEnt.getWorld().isClient) {
            if (_livEnt.getArmor() <= 10)
                _livEnt.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 100, 3));
            else if (_livEnt.getArmor() <= 20)
                _livEnt.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 100, 2));
            else if (_livEnt.getArmor() <= 30)
                _livEnt.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 100, 1));
            else if (_livEnt.getArmor() <= 40)
                _livEnt.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 100, 0));
        }
    }
}
