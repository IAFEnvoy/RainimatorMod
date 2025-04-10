package com.iafenvoy.rainimator.item.tool;

import com.iafenvoy.neptune.object.DamageUtil;
import com.iafenvoy.neptune.object.item.ToolMaterialUtil;
import com.iafenvoy.rainimator.registry.RainimatorItemGroups;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.item.*;

public class TopazTools {
    private static void onPostHit(LivingEntity entity, LivingEntity sourceentity) {
        if (Math.random() < 0.5D) {
            entity.setOnFireFor(5);
            entity.damage(DamageUtil.build(sourceentity, DamageTypes.IN_FIRE), 2.0F);
        }
    }

    public static class Axe extends AxeItem {
        public Axe() {
            super(ToolMaterialUtil.of(1500, 10.0F, 8.0F, 3, 20), 1.0F, -2.2F, new Settings().arch$tab(RainimatorItemGroups.ITEM));
        }

        @Override
        public boolean postHit(ItemStack itemtack, LivingEntity entity, LivingEntity sourceentity) {
            boolean ret_val = super.postHit(itemtack, entity, sourceentity);
            onPostHit(entity, sourceentity);
            return ret_val;
        }
    }

    public static class Hoe extends HoeItem {
        public Hoe() {
            super(ToolMaterialUtil.of(1000, 10.0F, 2.0F, 3, 20), 0, -2.2F, new Settings().arch$tab(RainimatorItemGroups.ITEM));
        }

        @Override
        public boolean postHit(ItemStack itemtack, LivingEntity entity, LivingEntity sourceentity) {
            boolean retval = super.postHit(itemtack, entity, sourceentity);
            onPostHit(entity, sourceentity);
            return retval;
        }
    }

    public static class Pickaxe extends PickaxeItem {
        public Pickaxe() {
            super(ToolMaterialUtil.of(1500, 10.0F, 4.0F, 3, 20), 1, -2.2F, new Settings().arch$tab(RainimatorItemGroups.ITEM));
        }

        @Override
        public boolean postHit(ItemStack itemtack, LivingEntity entity, LivingEntity sourceentity) {
            boolean retval = super.postHit(itemtack, entity, sourceentity);
            onPostHit(entity, sourceentity);
            return retval;
        }
    }

    public static class Shovel extends ShovelItem {
        public Shovel() {
            super(ToolMaterialUtil.of(1000, 10.0F, 3.0F, 3, 20), 1.0F, -2.2F, new Settings().arch$tab(RainimatorItemGroups.ITEM));
        }

        @Override
        public boolean postHit(ItemStack itemtack, LivingEntity entity, LivingEntity sourceentity) {
            boolean retval = super.postHit(itemtack, entity, sourceentity);
            onPostHit(entity, sourceentity);
            return retval;
        }
    }

    public static class Sword extends SwordItem {
        public Sword() {
            super(ToolMaterialUtil.of(1000, 0.0F, 6.0F, 0, 20), 3, -2.0F, new Settings().arch$tab(RainimatorItemGroups.ITEM));
        }

        @Override
        public boolean postHit(ItemStack itemtack, LivingEntity entity, LivingEntity sourceentity) {
            boolean retval = super.postHit(itemtack, entity, sourceentity);
            onPostHit(entity, sourceentity);
            return retval;
        }
    }
}
