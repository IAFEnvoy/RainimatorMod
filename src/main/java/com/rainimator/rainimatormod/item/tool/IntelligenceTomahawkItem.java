package com.rainimator.rainimatormod.item.tool;

import com.rainimator.rainimatormod.registry.ModItems;
import com.rainimator.rainimatormod.registry.util.ModCreativeTab;
import com.rainimator.rainimatormod.registry.util.TierBase;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class IntelligenceTomahawkItem extends AxeItem {
    public IntelligenceTomahawkItem() {
        super(TierBase.of(8000, 4.0F, 8.0F, 1, 5, ModItems.SAPPRIES), 1.0F, -2.2F, ModCreativeTab.createProperty());
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level world, @NotNull Player entity, @NotNull InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
        ItemStack itemstack = ar.getObject();
        Vec3 _center = new Vec3(entity.getX(), entity.getY(), entity.getZ());
        List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, (new AABB(_center, _center)).inflate(2.5D), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
        for (Entity entityiterator : _entfound) {
            if (entityiterator instanceof Player) {
                if (itemstack.hurt(0, new Random(), null)) {
                    itemstack.shrink(1);
                    itemstack.setDamageValue(0);
                }
                continue;
            }
            if (world instanceof ServerLevel _level) {
                LightningBolt entityToSpawn = EntityType.LIGHTNING_BOLT.create(_level);
                if (entityToSpawn != null) {
                    entityToSpawn.moveTo(Vec3.atBottomCenterOf(new BlockPos(entityiterator.getX(), entityiterator.getY(), entityiterator.getZ())));
                    entityToSpawn.setVisualOnly(true);
                    _level.addFreshEntity(entityToSpawn);
                }
            }
            world.setBlock(new BlockPos(entityiterator.getX(), entityiterator.getY(), entityiterator.getZ()), Blocks.FIRE.defaultBlockState(), 3);
            entity.getCooldowns().addCooldown(itemstack.getItem(), 600);
        }
        return ar;
    }
}
