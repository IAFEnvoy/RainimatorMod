package com.rainimator.rainimatormod.item.sword;

import com.rainimator.rainimatormod.registry.util.ModCreativeTab;
import com.rainimator.rainimatormod.registry.util.TierBase;
import com.rainimator.rainimatormod.util.MiscUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class EnderbigswordItem extends SwordItem {
    public EnderbigswordItem() {
        super(TierBase.of(2000, 0.0F, 6.0F, 0, 25), 3, -2.0F, ModCreativeTab.createProperty().fireResistant());
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack itemstack, @NotNull LivingEntity entity, @NotNull LivingEntity sourceentity) {
        boolean retval = super.hurtEnemy(itemstack, entity, sourceentity);
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();
        if (entity instanceof Mob _entity)
            _entity.setTarget(sourceentity);
        boolean hurted = true;
        if (Math.random() < 0.3D)
            entity.hurt(DamageSource.MAGIC, 4.0F);
        else if (Math.random() < 0.3D)
            entity.hurt(DamageSource.MAGIC, 6.0F);
        else if (Math.random() < 0.3D)
            entity.hurt(DamageSource.MAGIC, 8.0F);
        else if (Math.random() < 0.3D)
            entity.hurt(DamageSource.MAGIC, 10.0F);
        else hurted = false;
        if (hurted) {
            MiscUtil.playSound(entity.level, x, y, z, new ResourceLocation("block.anvil.land"), 5.0F, 1.0F);
            if (itemstack.hurt(1, new Random(), null)) {
                itemstack.shrink(1);
                itemstack.setDamageValue(0);
            }
        }
        if (Math.random() < 0.25D && sourceentity instanceof Player _player)
            _player.giveExperiencePoints(Mth.nextInt(new Random(), 10, 25));
        return retval;
    }
}
