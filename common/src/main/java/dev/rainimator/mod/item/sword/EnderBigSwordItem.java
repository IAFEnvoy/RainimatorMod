package dev.rainimator.mod.item.sword;

import dev.rainimator.mod.data.component.ManaData;
import dev.rainimator.mod.data.config.ServerConfig;
import dev.rainimator.mod.impl.ComponentManager;
import dev.rainimator.mod.registry.util.SwordItemBase;
import dev.rainimator.mod.registry.util.ToolMaterialUtil;
import dev.rainimator.mod.registry.RainimatorItemGroups;
import dev.rainimator.mod.registry.RainimatorItems;
import dev.rainimator.mod.registry.RainimatorParticles;
import dev.rainimator.mod.util.SoundUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

public class EnderBigSwordItem extends SwordItemBase {
    public EnderBigSwordItem() {
        super(ToolMaterialUtil.of(2000, 4.0F, 9.0F, 1, 15, RainimatorItems.SUPER_SAPPHIRE, () -> Items.ENDER_EYE), 3, -2.2F, new Settings().arch$tab(RainimatorItemGroups.MAIN));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity entity, Hand hand) {
        TypedActionResult<ItemStack> ar = super.use(world, entity, hand);
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();
        ItemStack itemtack = ar.getValue();
        double ender_1 = 0.0D;
        ManaData data = ComponentManager.getManaData(entity);
        if (entity.isSneaking() && data.tryUseMana(entity, ServerConfig.getInstance().ender_big_sword)) {
            BlockPos blockPos1 = entity.getWorld().raycast(new RaycastContext(entity.getCameraPosVec(1.0F), entity.getCameraPosVec(1.0F).add(entity.getRotationVec(1.0F).multiply(ender_1 + 6.0D)), RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, entity)).getBlockPos();
            BlockPos blockPos2 = entity.getWorld().raycast(new RaycastContext(entity.getCameraPosVec(1.0F), entity.getCameraPosVec(1.0F).add(entity.getRotationVec(1.0F).multiply(ender_1)), RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, entity)).getBlockPos();
            entity.requestTeleport(blockPos1.getX(), blockPos2.getY(), blockPos1.getZ());
            if ((Entity) entity instanceof ServerPlayerEntity _serverPlayer)
                _serverPlayer.networkHandler.requestTeleport(blockPos1.getX(), blockPos2.getY(), blockPos1.getZ(), entity.getYaw(), entity.getPitch());
            SoundUtil.playSound(world, x, y, z, Identifier.tryParse("entity.enderman.teleport"), 1.0F, 1.0F);
            if (world instanceof ServerWorld _level) {
                _level.spawnParticles(RainimatorParticles.PURPLE_LIGHT.get(), x, y, z, 50, 0.5D, 0.0D, 0.5D, 0.2D);
                entity.getItemCooldownManager().set(itemtack.getItem(), 300);
            }
        }
        return ar;
    }
}

