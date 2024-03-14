package com.rainimator.rainimatormod.renderer;

import com.ibm.icu.impl.Pair;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.rainimator.rainimatormod.armor.KingNormalCrownItem;
import com.rainimator.rainimatormod.model.ModelKingNormalCrown;
import com.rainimator.rainimatormod.registry.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;
import top.theillusivec4.curios.api.SlotTypePreset;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class CrownLayer {
    private static final HashMap<Item, Pair<Supplier<ModelPart>, ResourceLocation>> crownRenderer = new HashMap<>();
    private static final Supplier<ModelPart> createEmpty = () -> new ModelPart(Collections.emptyList(), Collections.emptyMap());

    public static void render(LivingEntity entity, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        if (crownRenderer.size() == 0) initPoseConsumers();
        if (entity.isSpectator() || entity.isInvisible() || !entity.isAlive()) return;
        Optional<SlotResult> result = CuriosApi.getCuriosHelper().findCurio(entity, SlotTypePreset.HEAD.getIdentifier(), 0);
        if (result.isEmpty()) return;
        Item item = result.get().stack().getItem();
        if (!crownRenderer.containsKey(item)) return;
        Pair<Supplier<ModelPart>, ResourceLocation> p = crownRenderer.get(item);
        HumanoidModel<LivingEntity> armorModel = new HumanoidModel<>(new ModelPart(Collections.emptyList(), Map.of(
                "head", p.first.get(),
                "hat", createEmpty.get(),
                "body", createEmpty.get(),
                "right_arm", createEmpty.get(),
                "left_arm", createEmpty.get(),
                "right_leg", createEmpty.get(),
                "left_leg", createEmpty.get())));
        armorModel.crouching = entity.isShiftKeyDown();
        armorModel.riding = entity.getVehicle() != null;
        armorModel.young = entity.isBaby();
        VertexConsumer vertexconsumer = ItemRenderer.getArmorFoilBuffer(bufferSource, RenderType.armorCutoutNoCull(p.second), false, result.get().stack().hasFoil());
        armorModel.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }

    public static void initPoseConsumers() {
        crownRenderer.put(ModItems.KINGNOMALCROWN_HELMET.get(), Pair.of(
                () -> new ModelKingNormalCrown<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelKingNormalCrown.LAYER_LOCATION)).Head,
                KingNormalCrownItem.TEXTURE
        ));
    }
}
