package com.rainimator.rainimatormod.item;

import com.rainimator.rainimatormod.RainimatorMod;
import com.rainimator.rainimatormod.registry.ModParticleTypes;
import com.rainimator.rainimatormod.registry.util.ModCreativeTab;
import com.rainimator.rainimatormod.registry.util.TierBase;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class BlackdeathswordItem extends SwordItem {
    public BlackdeathswordItem() {
        super(TierBase.of(1000, 0.0F, 9.0F, 0, 30), 3, -1.7F, ModCreativeTab.createProperty().fireResistant());
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack itemstack, @NotNull LivingEntity entity, @NotNull LivingEntity sourceentity) {
        boolean retval = super.hurtEnemy(itemstack, entity, sourceentity);
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();
        if (entity instanceof Mob _entity)
            _entity.setTarget(sourceentity);
        if (Math.random() < 0.7D) {
            if (!entity.level.isClientSide()) {
                entity.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 2));
                entity.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 2));
            }
            if (Math.random() < 0.7D) {
                if (!entity.level.isClientSide())
                    entity.level.playSound(null, new BlockPos(x, y, z), Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(RainimatorMod.MOD_ID, "black_death_sword_skills"))), SoundSource.NEUTRAL, 4.0F, 1.0F);
                else
                    entity.level.playLocalSound(x, y, z, Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(RainimatorMod.MOD_ID, "black_death_sword_skills"))), SoundSource.NEUTRAL, 4.0F, 1.0F, false);

            } else {
                if (!entity.level.isClientSide())
                    entity.level.playSound(null, new BlockPos(x, y, z), Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(RainimatorMod.MOD_ID, "black_death_sword_skill_3"))), SoundSource.NEUTRAL, 4.0F, 1.0F);
                else
                    entity.level.playLocalSound(x, y, z, Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(RainimatorMod.MOD_ID, "black_death_sword_skill_3"))), SoundSource.NEUTRAL, 4.0F, 1.0F, false);
            }
            if (entity.level instanceof ServerLevel _level)
                _level.sendParticles((ParticleOptions) ModParticleTypes.FLOWERWRITE.get(), x, y, z, 50, 0.5D, 1.0D, 0.5D, 0.1D);
        } else {
            if (!sourceentity.level.isClientSide())
                sourceentity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 300, 1));
        }

        return retval;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemstack, Level world, @NotNull List<Component> list, @NotNull TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(new TextComponent("§8攻击目标有概率使目标§7凋零§8并§a中毒，§8连续使用黑死剑会使使用者饥饿"));

        list.add(new TextComponent("§8虽然这把武器会保护主人免死，但§b蓝钻石§8的武器和工具会完美压制这股力量"));

        list.add(new TextComponent("§c并且当使用者死亡时，这把武器也会消逝"));
        list.add(new TextComponent("§a当武器破裂时，其使用者也会消逝"));
        list.add(new TextComponent("§c注意：§b如果目标绝对伤害压制这把剑，也可以打破这把剑的不死结界！"));
    }

    @Override
    public boolean onEntitySwing(ItemStack itemstack, LivingEntity entity) {
        boolean retval = super.onEntitySwing(itemstack, entity);
        double r, num, angel;
        if (Math.random() < 0.2D) {
            num = 50.0D;
            r = 3.0D;
            angel = 0.0D;
            for (int index0 = 0; index0 < (int) num; index0++) {
                if (entity.level instanceof ServerLevel _level)
                    _level.sendParticles((ParticleOptions) ParticleTypes.SOUL, entity.getX() + r * Math.cos(angel), entity.getY() + 1.0D, entity.getZ() + r * Math.sin(angel), 1, 0.0D, 0.0D, 0.0D, 0.0D);
                angel += 360.0D / num;
            }
        }
        return retval;
    }
}
