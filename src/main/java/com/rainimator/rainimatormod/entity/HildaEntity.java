package com.rainimator.rainimatormod.entity;

import com.rainimator.rainimatormod.registry.ModEntities;
import com.rainimator.rainimatormod.registry.ModItems;
import com.rainimator.rainimatormod.util.SpawnBiome;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Random;

@Mod.EventBusSubscriber
public class HildaEntity extends Monster implements RangedAttackMob {
    @SubscribeEvent
    public static void addLivingEntityToBiomes(BiomeLoadingEvent event) {
        if (SpawnBiome.SPAWN_BIOMES.contains(event.getName()))
            event.getSpawns().getSpawner(MobCategory.UNDERGROUND_WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(ModEntities.HILDA.get(), 10, 1, 1));
    }

    public HildaEntity(PlayMessages.SpawnEntity packet, Level world) {
        this(ModEntities.HILDA.get(), world);
    }

    public HildaEntity(EntityType<HildaEntity> type, Level world) {
        super(type, world);
        this.xpReward = 0;
        this.setNoAi(false);
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ModItems.SOLDIERS_WARHAMMER.get()));
        this.setItemSlot(EquipmentSlot.OFFHAND, new ItemStack(ModItems.BLUEDIAMONDSHIELD.get()));
    }

    @Override
    public @NotNull Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, false, false));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2D, false) {
            protected double getAttackReachSqr(@NotNull LivingEntity entity) {
                return (this.mob.getBbWidth() * this.mob.getBbWidth() + entity.getBbWidth());
            }
        });
        this.goalSelector.addGoal(3, new RandomStrollGoal(this, 1.0D));
        this.targetSelector.addGoal(4, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(5, new OpenDoorGoal(this, true));
        this.goalSelector.addGoal(6, new OpenDoorGoal(this, false));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(8, new FloatGoal(this));
        this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.25D, 20, 10.0F) {
            public boolean canContinueToUse() {
                return this.canUse();
            }
        });
    }

    @Override
    public @NotNull MobType getMobType() {
        return MobType.UNDEFINED;
    }

    @Override
    public SoundEvent getHurtSound(@NotNull DamageSource ds) {
        return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.hurt"));
    }

    @Override
    public SoundEvent getDeathSound() {
        return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.death"));
    }

    @Override
    public SpawnGroupData finalizeSpawn(@NotNull ServerLevelAccessor world, @NotNull DifficultyInstance difficulty, @NotNull MobSpawnType reason, @Nullable SpawnGroupData livingdata, @Nullable CompoundTag tag) {
        SpawnGroupData retval = super.finalizeSpawn(world, difficulty, reason, livingdata, tag);
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        if (Math.random() < 0.1D) {
            if (world instanceof ServerLevel _level) {
                SoldiersEntity soldiersEntity = new SoldiersEntity(ModEntities.SOLDIERS.get(), _level);
                soldiersEntity.moveTo(x + Mth.nextInt(new Random(), -2, 2), y + 2.0D, z + Mth.nextInt(new Random(), -2, 2), world.getRandom().nextFloat() * 360.0F, 0.0F);
                soldiersEntity.finalizeSpawn(_level, world.getCurrentDifficultyAt(soldiersEntity.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
                world.addFreshEntity(soldiersEntity);
            }
        } else if (Math.random() < 0.1D) {
            if (world instanceof ServerLevel _level) {
                AgethaEntity agethaEntity = new AgethaEntity(ModEntities.AGETHA.get(), _level);
                agethaEntity.moveTo(x + Mth.nextInt(new Random(), -2, 2), y + 2.0D, z + Mth.nextInt(new Random(), -2, 2), world.getRandom().nextFloat() * 360.0F, 0.0F);
                agethaEntity.finalizeSpawn(_level, world.getCurrentDifficultyAt(agethaEntity.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
                world.addFreshEntity(agethaEntity);
            }
        } else if (Math.random() < 0.1D && world instanceof ServerLevel _level) {
            ArcherEntity archerEntity = new ArcherEntity(ModEntities.ARCHER.get(), _level);
            archerEntity.moveTo(x + Mth.nextInt(new Random(), -2, 2), y + 2.0D, z + Mth.nextInt(new Random(), -2, 2), world.getRandom().nextFloat() * 360.0F, 0.0F);
            archerEntity.finalizeSpawn(_level, world.getCurrentDifficultyAt(archerEntity.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
            world.addFreshEntity(archerEntity);
        }
        return retval;
    }

    @Override
    public void performRangedAttack(LivingEntity target, float flval) {
        HildaEntityProjectile entityarrow = new HildaEntityProjectile(ModEntities.HILDA_PROJECTILE.get(), this, this.level);
        double d0 = target.getY() + target.getEyeHeight() - 1.1D;
        double d1 = target.getX() - this.getX();
        double d3 = target.getZ() - this.getZ();
        entityarrow.shoot(d1, d0 - entityarrow.getY() + Math.sqrt(d1 * d1 + d3 * d3) * 0.20000000298023224D, d3, 1.6F, 12.0F);
        this.level.addFreshEntity(entityarrow);
    }

    public static void init() {
        SpawnPlacements.register(ModEntities.HILDA.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (entityType, world, reason, pos, random) ->
                (world.getFluidState(pos.below()).is(FluidTags.WATER) && world.getBlockState(pos.above()).is(Blocks.WATER) && pos.getY() >= world.getSeaLevel() - 13 && pos.getY() <= world.getSeaLevel()));
    }


    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3D);
        builder = builder.add(Attributes.MAX_HEALTH, 80.0D);
        builder = builder.add(Attributes.ARMOR, 25.0D);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 1.0D);
        builder = builder.add(Attributes.FOLLOW_RANGE, 64.0D);
        builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 1.0D);
        builder = builder.add(Attributes.ATTACK_KNOCKBACK, 1.0D);
        return builder;
    }
}