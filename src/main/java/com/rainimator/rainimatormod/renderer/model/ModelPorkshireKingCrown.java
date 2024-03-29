package com.rainimator.rainimatormod.renderer.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.rainimator.rainimatormod.RainimatorMod;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;

public class ModelPorkshireKingCrown<T extends Entity> extends EntityModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(RainimatorMod.MOD_ID, "model_porkshire_king_crown"), "main");
    public final ModelPart Head;

    public ModelPorkshireKingCrown(ModelPart root) {
        this.Head = root.getChild("Head");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition Head = partdefinition.addOrReplaceChild("Head",
                CubeListBuilder.create().texOffs(0, 21).addBox(-5.0F, -9.0F, -5.0F, 10.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 21).addBox(-5.0F, -7.0F, -5.0F, 10.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 35)
                        .addBox(-5.0F, -10.0F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(22, 16).addBox(-3.0F, -10.0F, -5.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 34)
                        .addBox(4.0F, -10.0F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 44).addBox(-1.0F, -10.0F, -5.2F, 2.0F, 2.0F, 0.2F, new CubeDeformation(0.0F)).texOffs(0, 18)
                        .addBox(-0.8F, -8.6F, -5.1F, 1.6F, 0.5F, 0.1F, new CubeDeformation(0.0F)).texOffs(-1, 17).addBox(-1.0F, -11.0F, -5.1F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(7, 0)
                        .addBox(-1.4F, -10.0F, -5.1F, 0.6F, 1.4F, 0.1F, new CubeDeformation(0.0F)).texOffs(8, 45).addBox(-4.0F, -8.6F, -5.1F, 1.0F, 1.0F, 0.1F, new CubeDeformation(0.0F)).texOffs(7, 45)
                        .addBox(3.0F, -8.6F, -5.1F, 1.0F, 1.0F, 0.1F, new CubeDeformation(0.0F)).texOffs(3, 0).addBox(0.8F, -10.0F, -5.1F, 0.6F, 1.4F, 0.1F, new CubeDeformation(0.0F)).texOffs(11, 11)
                        .addBox(-5.0F, -8.0F, -4.0F, 1.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(11, 11).addBox(-5.0F, -7.0F, -4.0F, 1.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(12, 2)
                        .addBox(4.0F, -8.0F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(12, 2).addBox(4.0F, -7.0F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(22, 2)
                        .addBox(-4.0F, -8.0F, 4.0F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(22, 2).addBox(-4.0F, -7.0F, 4.0F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(22, 0)
                        .addBox(-5.0F, -9.0F, 4.0F, 10.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 10).addBox(4.0F, -9.0F, -4.0F, 1.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(0, 0)
                        .addBox(-5.0F, -9.0F, -4.0F, 1.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(34, 32).addBox(4.0F, -10.0F, -3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 30)
                        .addBox(4.0F, -10.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(20, 34).addBox(4.0F, -10.0F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(11, 16)
                        .addBox(4.0F, -10.0F, 3.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(2, 15).addBox(-5.0F, -10.0F, 3.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(34, 18)
                        .addBox(-5.0F, -10.0F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(11, 34).addBox(-5.0F, -10.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(7, 34)
                        .addBox(-5.0F, -10.0F, -3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(31, 33).addBox(2.0F, -10.0F, 4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(27, 33)
                        .addBox(-3.0F, -10.0F, 4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(22, 18).addBox(-1.0F, -10.0F, 4.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, -0.1047F, 0.0873F, 0.0F));
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.Head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public void setupAnim(@NotNull T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }
}