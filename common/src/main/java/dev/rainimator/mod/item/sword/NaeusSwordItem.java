package dev.rainimator.mod.item.sword;

import dev.rainimator.mod.RainimatorMod;
import dev.rainimator.mod.data.component.ManaData;
import dev.rainimator.mod.data.config.ServerConfig;
import dev.rainimator.mod.impl.ComponentManager;
import dev.rainimator.mod.item.util.SwordItemBase;
import dev.rainimator.mod.item.util.ToolMaterialUtil;
import dev.rainimator.mod.registry.RainimatorItemGroups;
import dev.rainimator.mod.util.DamageUtil;
import dev.rainimator.mod.util.SoundUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Comparator;
import java.util.List;

public class NaeusSwordItem extends SwordItemBase {
    public NaeusSwordItem() {
        super(ToolMaterialUtil.of(4000, 4.0F, 9.0F, 0, 10), 3, -2.0F, new Settings().fireproof().arch$tab(RainimatorItemGroups.MAIN));
    }

    @Override
    public boolean postHit(ItemStack itemtack, LivingEntity entity, LivingEntity sourceEntity) {
        boolean ret_val = super.postHit(itemtack, entity, sourceEntity);
        if (!entity.getWorld().isClient())
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 100, 0));
        entity.setOnFireFor(5);
        return ret_val;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity entity, Hand hand) {
        TypedActionResult<ItemStack> ar = super.use(world, entity, hand);
        Vec3d _center = entity.getPos();
        ManaData data = ComponentManager.getManaData(entity);
        if (entity.isSneaking() && data.tryUseMana(entity, ServerConfig.getInstance().naeus_sword)) {
            List<Entity> _entfound = world.getEntitiesByClass(Entity.class, (new Box(_center, _center)).expand(6.0D), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.squaredDistanceTo(_center))).toList();
            for (Entity entityIterator : _entfound) {
                if (entityIterator instanceof PlayerEntity)
                    entity.damage(DamageUtil.build(entity, DamageTypes.GENERIC), 0.0F);
                else {
                    entityIterator.damage(DamageUtil.build(entity, DamageTypes.MAGIC), 5.0F);
                    if (entityIterator instanceof LivingEntity _entity)
                        if (!_entity.getWorld().isClient())
                            _entity.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 300, 1));
                    entityIterator.setOnFireFor(10);
                    SoundUtil.playSound(world, _center.x, _center.y, _center.z, Identifier.of(RainimatorMod.MOD_ID, "naeus_sword_1"), 1.0F, 1.0F);
                    if (entity instanceof PlayerEntity)
                        entity.getItemCooldownManager().set(ar.getValue().getItem(), 600);
                }
            }
        }
        return ar;
    }
}