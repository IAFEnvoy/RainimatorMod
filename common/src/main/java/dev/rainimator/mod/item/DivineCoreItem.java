package dev.rainimator.mod.item;

import dev.rainimator.mod.data.component.ManaData;
import dev.rainimator.mod.data.config.ServerConfig;
import dev.rainimator.mod.impl.ComponentManager;
import dev.rainimator.mod.item.util.FoilItemBase;
import dev.rainimator.mod.registry.RainimatorItemGroups;
import dev.rainimator.mod.util.RandomHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class DivineCoreItem extends FoilItemBase {
    public DivineCoreItem() {
        super(p -> p.maxDamage(100).rarity(Rarity.UNCOMMON).arch$tab(RainimatorItemGroups.ITEM));
    }

    @Override
    public UseAction getUseAction(ItemStack itemtack) {
        return UseAction.BLOCK;
    }

    @Override
    public int getEnchantability() {
        return 10;
    }

    @Override
    public int getMaxUseTime(ItemStack itemtack) {
        return 1;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity entity, Hand hand) {
        TypedActionResult<ItemStack> ar = super.use(world, entity, hand);
        ManaData data = ComponentManager.getManaData(entity);
        if (!data.tryUseMana(entity, ServerConfig.getInstance().divine_core)) return ar;
        ItemStack itemtack = ar.getValue();

        if (entity.getHealth() == entity.getMaxHealth()) {
            if (itemtack.damage(0, entity.getRandom(), null)) {
                itemtack.decrement(1);
                itemtack.setDamage(0);
            }
        } else {
            entity.setHealth(entity.getHealth() + RandomHelper.nextInt(1, 4));
            if (itemtack.damage(1, entity.getRandom(), null)) {
                itemtack.decrement(1);
                itemtack.setDamage(0);
            }
        }
        return ar;
    }
}
