package com.rainimator.rainimatormod.item.armor;

import com.rainimator.rainimatormod.RainimatorMod;
import com.rainimator.rainimatormod.entity.WitheredSkeletonsEntity;
import com.rainimator.rainimatormod.registry.ModItems;
import com.rainimator.rainimatormod.registry.util.ModCreativeTab;
import com.rainimator.rainimatormod.renderer.model.ModelNetherCrown;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.monster.Zoglin;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.IItemRenderProperties;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Consumer;

public class NetherTheCrownItem extends ArmorItem {
    public NetherTheCrownItem(EquipmentSlot slot, Item.Properties properties) {
        super(new ArmorMaterial() {
            @Override
            public int getDurabilityForSlot(@NotNull EquipmentSlot slot) {
                (new int[4])[0] = 13;
                (new int[4])[1] = 15;
                (new int[4])[2] = 16;
                (new int[4])[3] = 11;
                return new int[]{13, 15, 16, 11}[slot.getIndex()] * 30;
            }

            @Override
            public int getDefenseForSlot(@NotNull EquipmentSlot slot) {
                return new int[]{0, 0, 0, 5}[slot.getIndex()];
            }

            @Override
            public int getEnchantmentValue() {
                return 15;
            }

            @Override
            public @NotNull SoundEvent getEquipSound() {
                return Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("")));
            }

            @Override
            public @NotNull Ingredient getRepairIngredient() {
                return Ingredient.of();
            }

            @Override
            public @NotNull String getName() {
                return "nether_the_crown";
            }

            @Override
            public float getToughness() {
                return 0.0F;
            }

            @Override
            public float getKnockbackResistance() {
                return 0.0F;
            }
        }, slot, properties);
    }


    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return RainimatorMod.MOD_ID + ":textures/models/armor/nether_the_crown.png";
    }


    public void onArmorTick(ItemStack itemstack, Level world, Player entity) {
        if (entity == null) {
            return;
        }
        Vec3 _center = new Vec3(entity.getX(), entity.getY(), entity.getZ());
        List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, (new AABB(_center, _center)).inflate(16.0D), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
        for (Entity entityiterator : _entfound) {
            if (entity.getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.NETHER_THE_CROWN_HELMET.get()) {
                if (entityiterator instanceof WitheredSkeletonsEntity) {
                    Mob _entity = (Mob) entityiterator;
                    _entity.getNavigation().stop();
                    continue;
                }
                if (entityiterator instanceof WitherSkeleton) {
                    Mob _entity = (Mob) entityiterator;
                    _entity.getNavigation().stop();
                    continue;
                }
                if (entityiterator instanceof Piglin) {
                    Mob _entity = (Mob) entityiterator;
                    _entity.getNavigation().stop();
                    continue;
                }
                if (entityiterator instanceof PiglinBrute) {
                    Mob _entity = (Mob) entityiterator;
                    _entity.getNavigation().stop();
                    continue;
                }
                if (entityiterator instanceof ZombifiedPiglin) {
                    Mob _entity = (Mob) entityiterator;
                    _entity.getNavigation().stop();
                    continue;
                }
                if (entityiterator instanceof Zoglin) {
                    Mob _entity = (Mob) entityiterator;
                    _entity.getNavigation().stop();
                }
            }
        }
    }

    public static class Helmet extends NetherTheCrownItem {
        public Helmet() {
            super(EquipmentSlot.HEAD, ModCreativeTab.createProperty().fireResistant());
        }

        public void initializeClient(Consumer<IItemRenderProperties> consumer) {
            consumer.accept(new IItemRenderProperties() {
                public HumanoidModel<LivingEntity> getArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
                    HumanoidModel<LivingEntity> armorModel = new HumanoidModel<>(new ModelPart(Collections.emptyList(), Map.of("head", (new ModelNetherCrown<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelNetherCrown.LAYER_LOCATION))).Head, "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "body", new ModelPart(
                            Collections.emptyList(), Collections.emptyMap()), "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_arm", new ModelPart(
                            Collections.emptyList(), Collections.emptyMap()), "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_leg", new ModelPart(
                            Collections.emptyList(), Collections.emptyMap()))));
                    armorModel.crouching = living.isShiftKeyDown();
                    armorModel.riding = defaultModel.riding;
                    armorModel.young = living.isBaby();
                    return armorModel;
                }
            });
        }
    }
}
