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

public class Modelmodel<T extends Entity>        extends EntityModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("rainimator", "modelmodel"), "main");
    public final ModelPart Head;

    public Modelmodel(ModelPart root) {
        this.Head = root.getChild("Head");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition Head = partdefinition.addOrReplaceChild("Head",
                CubeListBuilder.create().texOffs(0, 4).addBox(-3.0F, -11.0F, -7.0F, 10.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(10, 9).addBox(6.0F, -12.0F, 5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(10, 9)
                        .addBox(-4.0F, -12.0F, 5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(20, 20).addBox(8.0F, -14.0F, -2.0F, 1.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(24, 4)
                        .addBox(8.0F, -12.0F, 1.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(24, 4).addBox(8.0F, -12.0F, -3.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(20, 9)
                        .addBox(9.0F, -16.0F, -2.0F, 1.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(0, 9).addBox(10.0F, -24.0F, -1.5F, 1.0F, 16.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(6, 9)
                        .addBox(11.0F, -23.0F, -1.5F, 1.0F, 14.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(12, 9).addBox(12.0F, -22.0F, -2.5F, 1.0F, 12.0F, 2.5F, new CubeDeformation(0.0F)).texOffs(11, 24)
                        .addBox(13.0F, -20.0F, -2.0F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(20, 9).addBox(10.0F, -16.0F, -2.0F, 1.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(24, 4)
                        .addBox(9.0F, -24.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(24, 4).addBox(8.0F, -25.0F, 0.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(17, 9)
                        .addBox(8.0F, -24.0F, 1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.1047F, 0.0873F, 0.0F));
        PartDefinition cube_r1 = Head.addOrReplaceChild("cube_r1",
                CubeListBuilder.create().texOffs(17, 9).addBox(-8.0F, -25.0F, -20.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(24, 4).addBox(-8.0F, -26.0F, -21.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(24, 4)
                        .addBox(-7.0F, -25.0F, -21.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(20, 9).addBox(-6.0F, -17.0F, -23.0F, 1.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(11, 24)
                        .addBox(-3.0F, -21.0F, -23.0F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(12, 9).addBox(-4.0F, -23.0F, -23.5F, 1.0F, 12.0F, 2.5F, new CubeDeformation(0.0F)).texOffs(6, 9)
                        .addBox(-5.0F, -24.0F, -22.5F, 1.0F, 14.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 9).addBox(-6.0F, -25.0F, -22.5F, 1.0F, 16.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(20, 9)
                        .addBox(-7.0F, -17.0F, -23.0F, 1.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(24, 4).addBox(-8.0F, -13.0F, -24.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(24, 4)
                        .addBox(-8.0F, -13.0F, -20.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(20, 20).addBox(-8.0F, -15.0F, -23.0F, 1.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-12.0F, 1.0F, -22.0F, 0.0F, 3.1416F, 0.0F));
        PartDefinition cube_r2 = Head.addOrReplaceChild("cube_r2",
                CubeListBuilder.create().texOffs(4, 9).addBox(5.0F, -9.0F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(4, 9).addBox(5.0F, -9.0F, 4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(4, 9)
                        .addBox(5.0F, -9.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(4, 9).addBox(5.0F, -9.0F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(4, 9)
                        .addBox(5.0F, -9.0F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(4, 9).addBox(3.0F, -9.0F, -6.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(4, 9)
                        .addBox(1.0F, -9.0F, -6.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(4, 9).addBox(-1.0F, -9.0F, -6.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(4, 9)
                        .addBox(-3.0F, -9.0F, -6.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 0).addBox(-6.0F, -8.0F, -6.0F, 12.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(2.0F, -3.0F, 0.0F, 0.0F, 3.1416F, 0.0F));
        PartDefinition cube_r3 = Head.addOrReplaceChild("cube_r3",
                CubeListBuilder.create().texOffs(4, 9).addBox(-5.0F, -9.0F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(4, 9).addBox(-5.0F, -9.0F, 3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(4, 9)
                        .addBox(-5.0F, -9.0F, -3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(4, 9).addBox(-5.0F, -9.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(4, 9)
                        .addBox(-5.0F, -9.0F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(4, 9).addBox(-5.0F, -9.0F, 5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(3.0F, -3.0F, 0.0F, 0.0F, 3.1416F, 0.0F));
        PartDefinition cube_r4 = Head.addOrReplaceChild("cube_r4",
                CubeListBuilder.create().texOffs(4, 9).addBox(-3.0F, -9.0F, 4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(4, 9).addBox(-2.0F, -9.0F, 4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(4, 9)
                        .addBox(1.0F, -9.0F, 4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(4, 9).addBox(0.0F, -10.0F, 4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(4, 9)
                        .addBox(-1.0F, -10.0F, 4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(4, 9).addBox(-1.0F, -9.0F, 4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(4, 9)
                        .addBox(0.0F, -9.0F, 4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(15, 24).addBox(2.0F, -9.0F, 4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(2.0F, -3.0F, -2.0F, 0.0F, 3.1416F, 0.0F));
        PartDefinition cube_r5 = Head.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -8.0F, -6.0F, 12.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -3.0F, 0.0F, 0.0F, 1.5708F, 0.0F));
        PartDefinition cube_r6 = Head.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -8.0F, -5.0F, 12.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -3.0F, 0.0F, 0.0F, -1.5708F, 0.0F));
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