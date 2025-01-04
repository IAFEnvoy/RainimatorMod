package dev.rainimator.mod.entity;

import com.iafenvoy.neptune.object.EntityUtil;
import com.iafenvoy.neptune.object.SoundUtil;
import com.iafenvoy.neptune.object.VecUtil;
import com.iafenvoy.neptune.object.entity.StagedMonsterEntityBase;
import com.iafenvoy.neptune.render.Stage;
import com.iafenvoy.neptune.util.RandomHelper;
import com.iafenvoy.neptune.util.Timeout;
import dev.rainimator.mod.RainimatorMod;
import dev.rainimator.mod.registry.RainimatorEffects;
import dev.rainimator.mod.registry.RainimatorEntities;
import dev.rainimator.mod.registry.RainimatorItems;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public class HerobrineEntity extends StagedMonsterEntityBase {
    public static final Stage.StagedEntityTextureProvider texture = Stage.ofProvider(RainimatorMod.MOD_ID, "herobrine_1", "herobrine_2").setEyeTextureId("textures/entities/him_eye.png");
    private final ServerBossBar bossInfo = new ServerBossBar(this.getDisplayName(), BossBar.Color.RED, BossBar.Style.PROGRESS);
    private boolean hasSpawnBlackBone = false;

    public HerobrineEntity(EntityType<HerobrineEntity> type, World level) {
        this(type, level, Stage.First);
    }

    public HerobrineEntity(EntityType<HerobrineEntity> type, World world, Stage stage) {
        super(type, world, EntityGroup.UNDEAD, stage);
        this.experiencePoints = 0;
        this.setPersistent();
        switch (stage) {
            case First -> {
                this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(RainimatorItems.HEROBRINE_TOMAHAWK.get()));
                this.equipStack(EquipmentSlot.OFFHAND, new ItemStack(Items.AIR));
            }
            case Second -> {
                this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(RainimatorItems.BLUE_DIAMOND_SWORD.get()));
                this.equipStack(EquipmentSlot.OFFHAND, new ItemStack(RainimatorItems.BLUE_DIAMOND_SWORD.get()));
            }
        }
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        DefaultAttributeContainer.Builder builder = MobEntity.createMobAttributes();
        builder = builder.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3D);
        builder = builder.add(EntityAttributes.GENERIC_MAX_HEALTH, 200.0D);
        builder = builder.add(EntityAttributes.GENERIC_ARMOR, 25.0D);
        builder = builder.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0D);
        builder = builder.add(EntityAttributes.GENERIC_FOLLOW_RANGE, 64.0D);
        builder = builder.add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 10.0D);
        builder = builder.add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 1.0D);
        return builder;
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.2D, true) {
            protected double getSquaredMaxAttackDistance(LivingEntity entity) {
                return (this.mob.getWidth() * this.mob.getWidth() + entity.getWidth());
            }
        });
        this.targetSelector.add(3, (new RevengeGoal(this)).setGroupRevenge());
        this.goalSelector.add(4, new WanderAroundGoal(this, 1.0D));
        this.goalSelector.add(5, new LookAroundGoal(this));
        this.goalSelector.add(6, new SwimGoal(this));
    }

    @Override
    public boolean canImmediatelyDespawn(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        Vec3d _center = new Vec3d(x, y, z);
        List<Entity> _entfound = this.getWorld().getEntitiesByClass(Entity.class, (new Box(_center, _center)).expand(4.5D), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.squaredDistanceTo(_center))).toList();
        for (Entity entityiterator : _entfound) {
            if (this.hasStatusEffect(RainimatorEffects.ICE_PEOPLE.get())) {
                SoundUtil.playSound(this.getWorld(), this.getX(), this.getY(), this.getZ(), Identifier.of(RainimatorMod.MOD_ID, "him_skill"), 1.0F, 1.0F);
                this.requestTeleport(x, y + 4.0D, z);
                continue;
            }
            if (this.hasStatusEffect(StatusEffects.POISON) || this.hasStatusEffect(StatusEffects.WITHER) || this.hasStatusEffect(RainimatorEffects.SOUL_DEATH.get())) {
                this.clearStatusEffects();
                continue;
            }
            if (this.hasStatusEffect(RainimatorEffects.STUNNED.get())) {
                if (!this.getWorld().isClient()) {
                    this.addStatusEffect(new StatusEffectInstance(RainimatorEffects.PURIFICATION.get(), 200, 0));
                    this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 200, 3));
                }
                continue;
            }
            if (Math.random() < 0.1D) {
                SoundUtil.playSound(this.getWorld(), this.getX(), this.getY(), this.getZ(), Identifier.of(RainimatorMod.MOD_ID, "him_skill"), 1.0F, 1.0F);
                if (this.getWorld() instanceof ServerWorld _level && Math.random() < 0.01D)
                    EntityUtil.lightening(_level, entityiterator.getX(), entityiterator.getY(), entityiterator.getZ());
                this.getWorld().setBlockState(VecUtil.createBlockPos(entityiterator.getX(), entityiterator.getY(), entityiterator.getZ()), Blocks.FIRE.getDefaultState(), 3);
                if (entityiterator instanceof LivingEntity _entity)
                    if (!_entity.getWorld().isClient())
                        _entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 1200, 2));
            } else if (Math.random() < 0.05D) {
                SoundUtil.playSound(this.getWorld(), this.getX(), this.getY(), this.getZ(), Identifier.of(RainimatorMod.MOD_ID, "him_skill"), 1.0F, 1.0F);
                if (this.getWorld() instanceof ServerWorld _level)
                    _level.spawnParticles(ParticleTypes.END_ROD, x, y, z, 15, 0.5D, 0.5D, 0.5D, 0.5D);
                this.getNavigation().startMovingTo(x + RandomHelper.nextInt(-2, 2), y, z + RandomHelper.nextInt(-2, 2), 20.0D);
                if (!this.getWorld().isClient())
                    this.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 300, 0));
            }
            if (this.hasStatusEffect(StatusEffects.SPEED))
                if (!this.getWorld().isClient()) {
                    this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100, 2));
                    this.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 200, 0));
                }
            if (this.getHealth() <= 100.0F)
                if (!this.getWorld().isClient())
                    this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 200, 2));
        }
        return super.damage(source, amount);
    }

    @Override
    public boolean isInvulnerableTo(DamageSource damageSource) {
        if (damageSource.isOf(DamageTypes.FALL))
            return true;
        if (damageSource.isOf(DamageTypes.DROWN))
            return true;
        if (damageSource.isOf(DamageTypes.LIGHTNING_BOLT))
            return true;
        if (damageSource.isOf(DamageTypes.EXPLOSION))
            return true;
        if (damageSource.isOf(DamageTypes.WITHER))
            return true;
        if (damageSource.isOf(DamageTypes.WITHER_SKULL))
            return true;
        return super.isInvulnerableTo(damageSource);
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason reason, EntityData livingdata, NbtCompound tag) {
        EntityData retval = super.initialize(world, difficulty, reason, livingdata, tag);
        if (world instanceof ServerWorld _level)
            EntityUtil.lightening(_level, this.getX(), this.getY(), this.getZ());
        if (!world.isClient() && world.getServer() != null && this.getStage() == Stage.First)
            world.getServer().getPlayerManager().broadcast(Text.translatable("entity.rainimator.herobrine.stage1"), false);
        if (world.getDifficulty() != Difficulty.PEACEFUL) {
            Runnable callback = () -> {
                if (this.isAlive())
                    SoundUtil.playSound(this.getWorld(), this.getX(), this.getY(), this.getZ(), Identifier.of(RainimatorMod.MOD_ID, "him_music_boss"), 1, 1);
            };
            Timeout.create(0, callback);
            Timeout.create(5720, callback);
            Timeout.create(11440, callback);
            Timeout.create(17160, callback);
            Timeout.create(22880, callback);
            Timeout.create(28600, callback);
            Timeout.create(34320, callback);
        }

        return retval;
    }

    @Override
    public void baseTick() {
        super.baseTick();
        if (!this.getWorld().isClient()) {
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 80, 0));
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 80, 1));
            if (!this.isAlive()) {
                SoundUtil.stopSound(this.getWorld(), Identifier.of(RainimatorMod.MOD_ID, "him_music_boss"));
                if (this.getStage() == Stage.First) {
                    if (this.getHealth() < 50) {
                        if (this.getOffHandStack().getItem() == Blocks.AIR.asItem()) {
                            this.getNavigation().stop();
                            this.requestTeleport(this.getX(), this.getY(), this.getZ());
                            ItemStack _setstack = new ItemStack(RainimatorItems.SOUL_PEOPLE.get());
                            _setstack.setCount(1);
                            this.setStackInHand(Hand.OFF_HAND, _setstack);
                            if (!this.getWorld().isClient()) {
                                this.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 4));
                                this.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100, 4));
                            }
                            if (this.getWorld() instanceof ServerWorld _level) {
                                LightningEntity entityToSpawn = EntityType.LIGHTNING_BOLT.create(_level);
                                if (entityToSpawn != null) {
                                    entityToSpawn.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(new BlockPos((int) this.getX(), (int) this.getY(), (int) this.getZ())));
                                    entityToSpawn.setCosmetic(true);
                                    _level.spawnEntity(entityToSpawn);
                                }
                            }
                            this.getWorld().setBlockState(new BlockPos((int) this.getX(), (int) this.getY(), (int) this.getZ()), Blocks.FIRE.getDefaultState(), 3);
                            if (!this.getWorld().isClient() && this.getServer() != null)
                                this.getServer().getCommandManager().executeWithPrefix(this.getCommandSource().withSilent().withLevel(4),
                                        "summon firework_rocket ~ ~1 ~ {LifeTime:4,FireworksItem:{itemId:firework_rocket,Count:1,tag:{Fireworks:{Flight:1,Explosions:[{Type:0,Flicker:1,Trail:0,Colors:[I;8073150],FadeColors:[I;2437522]}]}}}}");
                            SoundUtil.playSound(this.getWorld(), this.getX(), this.getY(), this.getZ(), Identifier.of(RainimatorMod.MOD_ID, "fire_soul"), 5, 1);

                            if (this.getWorld() instanceof ServerWorld _level)
                                _level.spawnParticles(ParticleTypes.SOUL, this.getX(), this.getY(), this.getZ(), 400, 3, 4, 3, 0.002);

                            Runnable callback = () -> {
                                this.getNavigation().stop();
                                this.requestTeleport(this.getX(), this.getY(), this.getZ());
                                if (this.getWorld() instanceof ServerWorld _level) {
                                    LightningEntity entityToSpawn = EntityType.LIGHTNING_BOLT.create(_level);
                                    if (entityToSpawn != null) {
                                        entityToSpawn.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(new BlockPos((int) this.getX(), (int) this.getY(), (int) this.getZ())));
                                        entityToSpawn.setCosmetic(true);
                                        _level.spawnEntity(entityToSpawn);
                                    }
                                }
                                this.getWorld().setBlockState(new BlockPos((int) this.getX(), (int) this.getY(), (int) this.getZ()), Blocks.FIRE.getDefaultState(), 3);
                                SoundUtil.playSound(this.getWorld(), this.getX(), this.getY(), this.getZ(), Identifier.tryParse("item.totem.use"), 5, 1);
                                if (this.getWorld() instanceof ServerWorld _level)
                                    _level.spawnParticles(ParticleTypes.SOUL_FIRE_FLAME, this.getX(), this.getY(), this.getZ(), 300, 2, 3, 2, 0.002);
                                if (!this.getWorld().isClient() && this.getServer() != null)
                                    this.getServer().getCommandManager().executeWithPrefix(this.getCommandSource().withSilent().withLevel(4),
                                            "summon firework_rocket ~ ~1 ~ {LifeTime:4,FireworksItem:{itemId:firework_rocket,Count:1,tag:{Fireworks:{Flight:1,Explosions:[{Type:1,Flicker:1,Trail:0,Colors:[I;2437522],FadeColors:[I;2651799]}]}}}}");
                            };
                            Timeout.create(30, callback);
                            Timeout.create(60, callback);
                            Timeout.create(80, () -> SoundUtil.stopSound(this.getWorld(), Identifier.of(RainimatorMod.MOD_ID, "him_music_boss")));
                            Timeout.create(90, () -> {
                                this.getNavigation().stop();
                                this.requestTeleport(this.getX(), this.getY(), this.getZ());
                                if (!this.getWorld().isClient())
                                    this.discard();
                                if (this.getWorld() instanceof ServerWorld _level) {
                                    LightningEntity entityToSpawn = EntityType.LIGHTNING_BOLT.create(_level);
                                    if (entityToSpawn != null) {
                                        entityToSpawn.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(new BlockPos((int) this.getX(), (int) this.getY(), (int) this.getZ())));
                                        entityToSpawn.setCosmetic(true);
                                        _level.spawnEntity(entityToSpawn);
                                    }
                                }
                                this.getWorld().setBlockState(new BlockPos((int) this.getX(), (int) this.getY(), (int) this.getZ()), Blocks.FIRE.getDefaultState(), 3);
                                SoundUtil.playSound(this.getWorld(), this.getX(), this.getY(), this.getZ(), Identifier.tryParse("entity.wither.spawn"), 5, 1);
                                if (this.getWorld() instanceof ServerWorld _level)
                                    _level.spawnParticles(ParticleTypes.TOTEM_OF_UNDYING, this.getX(), this.getY(), this.getZ(), 300, 2, 3, 2, 0.05);
                                if (!this.getWorld().isClient() && this.getServer() != null)
                                    this.getServer().getCommandManager().executeWithPrefix(this.getCommandSource().withSilent().withLevel(4),
                                            "summon firework_rocket ~ ~1 ~ {LifeTime:4,FireworksItem:{itemId:firework_rocket,Count:1,tag:{Fireworks:{Flight:1,Explosions:[{Type:2,Flicker:1,Trail:0,Colors:[I;6719955],FadeColors:[I;15790320]}]}}}}");
                                this.setHealth(this.getHealth() + 20);
                                if (this.getWorld() instanceof ServerWorld _level) {
                                    MobEntity entityToSpawn = new HerobrineEntity(RainimatorEntities.HEROBRINE.get(), _level, Stage.Second);
                                    entityToSpawn.refreshPositionAndAngles(this.getX(), (this.getY() + 1), this.getZ(), this.getWorld().getRandom().nextFloat() * 360F, 0);
                                    entityToSpawn.initialize(_level, this.getWorld().getLocalDifficulty(entityToSpawn.getBlockPos()), SpawnReason.MOB_SUMMONED, null, null);
                                    this.getWorld().spawnEntity(entityToSpawn);
                                }
                            });
                        }
                    }
                }
            } else if (this.getStage() == Stage.Second && this.getHealth() <= this.getMaxHealth() / 4 && !this.hasSpawnBlackBone) {
                this.hasSpawnBlackBone = true;
                this.getNavigation().stop();
                this.requestTeleport(this.getX(), this.getY(), this.getZ());

                this.getMainHandStack().addEnchantment(Enchantments.SHARPNESS, 5);
                this.getOffHandStack().addEnchantment(Enchantments.SHARPNESS, 5);
                SoundUtil.playSound(this.getWorld(), this.getX(), this.getY(), this.getZ(), Identifier.tryParse("entity.wither.spawn"), 1, 1);
                if (!this.getWorld().isClient() && this.getServer() != null)
                    this.getServer().getCommandManager().executeWithPrefix(this.getCommandSource().withSilent().withLevel(4),
                            "summon firework_rocket ~ ~1 ~ {LifeTime:4,FireworksItem:{itemId:firework_rocket,Count:1,tag:{Fireworks:{Flight:1,Explosions:[{Type:1,Flicker:1,Trail:0,Colors:[I;2651799],FadeColors:[I;6719955]}]}}}}");

                if (this.getWorld() instanceof ServerWorld _level) {
                    _level.spawnParticles(ParticleTypes.SOUL, this.getX(), this.getY(), this.getZ(), 400, 2, 3, 2, 0.002);
                    LightningEntity entityToSpawn = EntityType.LIGHTNING_BOLT.create(_level);
                    if (entityToSpawn != null) {
                        entityToSpawn.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(new BlockPos((int) this.getX(), (int) this.getY(), (int) this.getZ())));
                        entityToSpawn.setCosmetic(true);
                        _level.spawnEntity(entityToSpawn);
                    }
                }
                this.getWorld().setBlockState(new BlockPos((int) this.getX(), (int) this.getY(), (int) this.getZ()), Blocks.FIRE.getDefaultState(), 3);
                if (this.getWorld() instanceof ServerWorld _level) {
                    MobEntity entityToSpawn = new BlackBoneEntity(RainimatorEntities.BLACKBONE.get(), _level);
                    entityToSpawn.refreshPositionAndAngles(this.getX(), (this.getY() + 3), this.getZ(), this.getWorld().getRandom().nextFloat() * 360F, 0);
                    entityToSpawn.initialize(_level, this.getWorld().getLocalDifficulty(entityToSpawn.getBlockPos()), SpawnReason.MOB_SUMMONED, null, null);
                    this.getWorld().spawnEntity(entityToSpawn);
                }
                if (!this.getWorld().isClient() && this.getWorld().getServer() != null)
                    this.getWorld().getServer().getPlayerManager().broadcast(Text.translatable("entity.rainimator.blackbone.summon"), false);
                Timeout.create(40, () -> SoundUtil.stopSound(this.getWorld(), Identifier.of(RainimatorMod.MOD_ID, "blackbone_boss_music")));
                if (!this.getWorld().isClient() && this.getWorld().getServer() != null)
                    this.getWorld().getServer().getPlayerManager().broadcast(Text.translatable("entity.rainimator.herobrine.summon1"), false);
            }
        }
    }

    @Override
    public boolean canUsePortals() {
        return false;
    }

    @Override
    public void onStartedTrackingBy(ServerPlayerEntity player) {
        super.onStartedTrackingBy(player);
        this.bossInfo.addPlayer(player);
    }

    @Override
    public void onStoppedTrackingBy(ServerPlayerEntity player) {
        super.onStoppedTrackingBy(player);
        this.bossInfo.removePlayer(player);
    }

    @Override
    public void mobTick() {
        super.mobTick();
        this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
    }

    @Override
    public SoundEvent getAmbientSound() {
        return Registries.SOUND_EVENT.get(Identifier.of(RainimatorMod.MOD_ID, "him"));
    }

    @Override
    public SoundEvent getHurtSound(@NotNull DamageSource ds) {
        return Registries.SOUND_EVENT.get(Identifier.tryParse("entity.generic.hurt"));
    }

    @Override
    public SoundEvent getDeathSound() {
        return Registries.SOUND_EVENT.get(Identifier.tryParse("entity.generic.death"));
    }
}