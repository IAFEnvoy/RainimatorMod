package com.rainimator.rainimatormod.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;

public class ModelCustomModel<T extends Entity> extends EntityModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("rainimator", "model_custom_model"), "main");
    public final ModelPart Head;

    public ModelCustomModel(ModelPart root) {
        this.Head = root.getChild("Head");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition Head = partdefinition.addOrReplaceChild("Head",
                CubeListBuilder.create().texOffs(0, 21).addBox(-5.0F, -9.0F, -5.0F, 10.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 35).addBox(-5.0F, -10.0F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(22, 16)
                        .addBox(-3.0F, -10.0F, -5.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 34).addBox(4.0F, -10.0F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(5, 24)
                        .addBox(-1.0F, -11.0F, -5.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 16).addBox(-0.8F, -10.0F, -5.2F, 1.6F, 1.4F, 0.2F, new CubeDeformation(0.0F)).texOffs(0, 18)
                        .addBox(-0.8F, -8.6F, -5.1F, 1.6F, 0.5F, 0.1F, new CubeDeformation(0.0F)).texOffs(7, 0).addBox(-1.4F, -10.0F, -5.1F, 0.6F, 1.4F, 0.1F, new CubeDeformation(0.0F)).texOffs(3, 0)
                        .addBox(0.8F, -10.0F, -5.1F, 0.6F, 1.4F, 0.1F, new CubeDeformation(0.0F)).texOffs(11, 11).addBox(-5.0F, -8.0F, -4.0F, 1.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(12, 2)
                        .addBox(4.0F, -8.0F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(22, 2).addBox(-4.0F, -8.0F, 4.0F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(22, 0)
                        .addBox(-5.0F, -9.0F, 4.0F, 10.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 10).addBox(4.0F, -9.0F, -4.0F, 1.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(0, 0)
                        .addBox(-5.0F, -9.0F, -4.0F, 1.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(34, 32).addBox(4.0F, -10.0F, -3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 30)
                        .addBox(4.0F, -10.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(20, 34).addBox(4.0F, -10.0F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(11, 16)
                        .addBox(4.0F, -10.0F, 3.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(2, 15).addBox(-5.0F, -10.0F, 3.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(34, 18)
                        .addBox(-5.0F, -10.0F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(11, 34).addBox(-5.0F, -10.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(7, 34)
                        .addBox(-5.0F, -10.0F, -3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(31, 33).addBox(2.0F, -10.0F, 4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(27, 33)
                        .addBox(-3.0F, -10.0F, 4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(22, 18).addBox(-1.0F, -10.0F, 4.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1047F, 0.0873F, 0.0F));
        PartDefinition cube_r1 = Head.addOrReplaceChild("cube_r1",
                CubeListBuilder.create().texOffs(4, 6).addBox(-8.0F, -38.0F, 1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(15, 16).addBox(-7.0F, -38.0F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(24, 21)
                        .addBox(-6.0F, -39.0F, 3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(28, 18).addBox(-5.0F, -40.0F, 4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(31, 19)
                        .addBox(-3.0F, -42.0F, 5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(15, 0).addBox(-4.0F, -42.0F, 4.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(4, 10)
                        .addBox(-5.0F, -41.0F, 3.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(11, 0).addBox(-6.0F, -40.0F, 2.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(4, 0)
                        .addBox(-7.0F, -40.0F, 1.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(31, 4).addBox(-8.0F, -39.0F, 0.0F, 1.0F, 11.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(19, 21)
                        .addBox(-7.0F, -35.0F, -2.0F, 1.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(11, 11).addBox(-6.0F, -31.0F, -3.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(8, 24)
                        .addBox(-6.0F, -33.0F, -2.0F, 1.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(14, 4).addBox(-6.0F, -31.0F, 1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 3.1416F, 0.0F));
        PartDefinition cube_r2 = Head.addOrReplaceChild("cube_r2",
                CubeListBuilder.create().texOffs(26, 31).addBox(15.0F, -40.0F, 4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(30, 31).addBox(16.0F, -39.0F, 3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(20, 32)
                        .addBox(19.0F, -37.0F, 0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(13, 24).addBox(18.0F, -38.0F, 1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(32, 21)
                        .addBox(17.0F, -38.0F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(23, 33).addBox(13.0F, -42.0F, 5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(26, 11)
                        .addBox(14.0F, -42.0F, 4.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(15, 11).addBox(15.0F, -41.0F, 3.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(22, 4)
                        .addBox(16.0F, -40.0F, 2.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 10).addBox(17.0F, -40.0F, 1.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(16, 31)
                        .addBox(18.0F, -39.0F, 0.0F, 1.0F, 11.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 0).addBox(2.0F, -38.0F, -0.5F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 0)
                        .addBox(19.0F, -38.0F, -0.5F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 24).addBox(17.0F, -35.0F, -2.0F, 1.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(22, 11)
                        .addBox(16.0F, -31.0F, -3.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(26, 4).addBox(16.0F, -31.0F, 1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(27, 21)
                        .addBox(16.0F, -33.0F, -2.0F, 1.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(11.0F, 24.0F, 0.0F, 0.0F, 3.1416F, 0.0F));
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(@NotNull T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    @Override
    public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.Head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}