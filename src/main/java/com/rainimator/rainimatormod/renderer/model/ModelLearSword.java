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

public class ModelLearSword<T extends Entity> extends EntityModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(RainimatorMod.MOD_ID, "modellearsword"), "main");
    public final ModelPart Body;

    public ModelLearSword(ModelPart root) {
        this.Body = root.getChild("Body");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition diamond_sword_20_r1 = Body.addOrReplaceChild("diamond_sword_20_r1",
                CubeListBuilder.create().texOffs(16, 14).addBox(11.0F, -25.0F, 8.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(24, 31).addBox(3.0F, -26.0F, 8.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(26, 21)
                        .addBox(10.0F, -26.0F, 8.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(12, 0).addBox(4.0F, -27.0F, 8.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(30, 10)
                        .addBox(9.0F, -27.0F, 8.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(26, 26).addBox(7.0F, -30.0F, 8.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(28, 31)
                        .addBox(9.0F, -30.0F, 8.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(4, 12).addBox(6.0F, -31.0F, 8.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(8, 12)
                        .addBox(8.0F, -31.0F, 8.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 0).addBox(5.0F, -32.0F, 8.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(27, 16)
                        .addBox(9.0F, -32.0F, 8.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(15, 18).addBox(4.0F, -33.0F, 8.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(33, 4)
                        .addBox(-1.0F, -34.0F, 8.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(4, 19).addBox(3.0F, -34.0F, 8.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(29, 25)
                        .addBox(-2.0F, -35.0F, 8.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(8, 19).addBox(2.0F, -35.0F, 8.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(19, 18)
                        .addBox(1.0F, -36.0F, 8.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(20, 0).addBox(0.0F, -37.0F, 8.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(4, 0)
                        .addBox(-3.0F, -38.0F, 8.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-13.0F, -6.0F, 0.0F, 0.0F, 0.0F, 1.309F));
        PartDefinition diamond_sword_19_r1 = Body.addOrReplaceChild("diamond_sword_19_r1",
                CubeListBuilder.create().texOffs(20, 6).addBox(6.0F, -14.0F, 8.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(20, 32).addBox(-2.0F, -15.0F, 8.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 29)
                        .addBox(5.0F, -15.0F, 8.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(30, 27).addBox(-1.0F, -16.0F, 8.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(4, 31)
                        .addBox(4.0F, -16.0F, 8.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(12, 29).addBox(2.0F, -19.0F, 8.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(32, 31)
                        .addBox(4.0F, -19.0F, 8.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(12, 12).addBox(1.0F, -20.0F, 8.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 16)
                        .addBox(3.0F, -20.0F, 8.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(4, 4).addBox(0.0F, -21.0F, 8.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(19, 29)
                        .addBox(4.0F, -21.0F, 8.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(22, 10).addBox(-1.0F, -22.0F, 8.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(33, 9)
                        .addBox(-6.0F, -23.0F, 8.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 23).addBox(-2.0F, -23.0F, 8.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(30, 14)
                        .addBox(-7.0F, -24.0F, 8.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(12, 23).addBox(-3.0F, -24.0F, 8.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(23, 16)
                        .addBox(-4.0F, -25.0F, 8.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(22, 23).addBox(-5.0F, -26.0F, 8.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(8, 4)
                        .addBox(-8.0F, -27.0F, 8.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-13.0F, -6.0F, 0.0F, 0.0F, 0.0F, 0.7418F));
        PartDefinition diamond_sword_18_r1 = Body.addOrReplaceChild("diamond_sword_18_r1",
                CubeListBuilder.create().texOffs(24, 0).addBox(0.0F, 2.0F, 8.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(33, 16).addBox(-8.0F, 1.0F, 8.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(30, 0)
                        .addBox(-1.0F, 1.0F, 8.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(8, 31).addBox(-7.0F, 0.0F, 8.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(16, 31)
                        .addBox(-2.0F, 0.0F, 8.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(30, 5).addBox(-4.0F, -3.0F, 8.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 34)
                        .addBox(-2.0F, -3.0F, 8.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(16, 0).addBox(-5.0F, -4.0F, 8.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(16, 7)
                        .addBox(-3.0F, -4.0F, 8.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 8).addBox(-6.0F, -5.0F, 8.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(29, 19)
                        .addBox(-2.0F, -5.0F, 8.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(16, 24).addBox(-7.0F, -6.0F, 8.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 0)
                        .addBox(-12.0F, -7.0F, 8.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(4, 25).addBox(-8.0F, -7.0F, 8.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(30, 22)
                        .addBox(-13.0F, -8.0F, 8.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(8, 25).addBox(-9.0F, -8.0F, 8.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(26, 4)
                        .addBox(-10.0F, -9.0F, 8.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(26, 10).addBox(-11.0F, -10.0F, 8.0F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(8, 8)
                        .addBox(-14.0F, -11.0F, 8.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-13.0F, -6.0F, 0.0F, 0.0F, 0.0F, 0.2182F));
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(@NotNull T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    @Override
    public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}