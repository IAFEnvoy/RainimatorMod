package dev.rainimator.mod.item.tool;

import dev.rainimator.mod.data.component.ManaData;
import dev.rainimator.mod.data.config.ServerConfig;
import dev.rainimator.mod.impl.ComponentManager;
import dev.rainimator.mod.item.util.ToolMaterialUtil;
import dev.rainimator.mod.registry.RainimatorItemGroups;
import dev.rainimator.mod.registry.RainimatorItems;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Comparator;
import java.util.List;

public class IntelligenceTomahawkItem extends AxeItem {
    public IntelligenceTomahawkItem() {
        super(ToolMaterialUtil.of(8000, 4.0F, 8.0F, 1, 5, RainimatorItems.SAPPHIRE), 1.0F, -2.2F, new Settings().arch$tab(RainimatorItemGroups.MAIN));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity entity, Hand hand) {
        TypedActionResult<ItemStack> ar = super.use(world, entity, hand);
        ManaData data = ComponentManager.getManaData(entity);
        if (!data.tryUseMana(entity,ServerConfig.getInstance().intelligence_tomahawk))
            return ar;
        ItemStack itemtack = ar.getValue();
        Vec3d _center = new Vec3d(entity.getX(), entity.getY(), entity.getZ());
        List<Entity> _entfound = world.getEntitiesByClass(Entity.class, (new Box(_center, _center)).expand(2.5D), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.squaredDistanceTo(_center))).toList();
        for (Entity entityiterator : _entfound) {
            if (entityiterator instanceof PlayerEntity) {
                if (itemtack.damage(0, entity.getRandom(), null)) {
                    itemtack.decrement(1);
                    itemtack.setDamage(0);
                }
                continue;
            }
            if (world instanceof ServerWorld _level) {
                LightningEntity entityToSpawn = EntityType.LIGHTNING_BOLT.create(_level);
                if (entityToSpawn != null) {
                    entityToSpawn.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(new BlockPos((int) entityiterator.getX(), (int) entityiterator.getY(), (int) entityiterator.getZ())));
                    entityToSpawn.setCosmetic(true);
                    _level.spawnEntity(entityToSpawn);
                }
            }
            world.setBlockState(new BlockPos((int) entityiterator.getX(), (int) entityiterator.getY(), (int) entityiterator.getZ()), Blocks.FIRE.getDefaultState(), 3);
            entity.getItemCooldownManager().set(itemtack.getItem(), 600);
        }
        return ar;
    }
}
