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

public class Modelmagic_blue_diamond_sword<T extends Entity>        extends EntityModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("rainimator", "modelmagic_blue_diamond_sword"), "main");
    public final ModelPart group;

    public Modelmagic_blue_diamond_sword(ModelPart root) {
        this.group = root.getChild("group");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition group = partdefinition.addOrReplaceChild("group",
                CubeListBuilder.create().texOffs(15, 3).addBox(-1.3F, -10.0F, 9.0F, 1.6F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(31, 5).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(31, 3)
                        .addBox(-1.0F, -2.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(20, 10).addBox(-1.3F, -2.0F, 1.0F, 1.6F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(3, 31)
                        .addBox(-1.0F, -1.0F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(10, 20).addBox(-1.3F, -3.0F, 1.0F, 1.6F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(20, 8)
                        .addBox(-1.3F, -3.0F, 2.0F, 1.6F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(20, 6).addBox(-1.3F, -2.0F, 2.0F, 1.6F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(30, 28)
                        .addBox(-1.0F, -1.0F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(30, 26).addBox(-1.0F, -2.0F, 3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(20, 30)
                        .addBox(-1.0F, -3.0F, 4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(16, 30).addBox(-1.0F, -4.0F, 5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(10, 30)
                        .addBox(-1.0F, -5.0F, 6.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(6, 30).addBox(-1.0F, -6.0F, 7.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 30)
                        .addBox(-1.0F, -7.0F, 8.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(27, 29).addBox(-1.0F, -8.0F, 9.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(23, 29)
                        .addBox(-1.0F, -9.0F, 10.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(29, 17).addBox(-1.0F, -10.0F, 11.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(29, 15)
                        .addBox(-1.0F, -11.0F, 12.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(13, 29).addBox(-1.0F, -12.0F, 13.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(3, 29)
                        .addBox(-1.0F, -13.0F, 14.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(28, 24).addBox(-1.0F, -14.0F, 15.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(28, 22)
                        .addBox(-1.0F, -15.0F, 16.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(28, 20).addBox(-1.0F, -16.0F, 17.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(18, 22)
                        .addBox(-1.2F, -17.0F, 18.0F, 1.4F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(20, 26).addBox(-1.0F, -16.0F, 18.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(26, 18)
                        .addBox(-1.0F, -19.0F, 19.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 22).addBox(-1.2F, -16.0F, 19.0F, 1.4F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(26, 14)
                        .addBox(-1.0F, -15.0F, 19.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(15, 21).addBox(-1.2F, -15.0F, 20.0F, 1.4F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(10, 26)
                        .addBox(-1.0F, -14.0F, 20.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(6, 26).addBox(-1.0F, -14.0F, 21.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 26)
                        .addBox(-1.0F, -15.0F, 21.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(25, 25).addBox(-1.0F, -16.0F, 20.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(5, 21)
                        .addBox(-1.2F, -20.0F, 15.0F, 1.4F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(15, 23).addBox(-1.0F, -20.0F, 16.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(23, 13)
                        .addBox(-1.0F, -17.0F, 19.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(25, 23).addBox(-1.0F, -20.0F, 14.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(20, 20)
                        .addBox(-1.2F, -21.0F, 14.0F, 1.4F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(25, 21).addBox(-1.0F, -21.0F, 15.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(13, 25)
                        .addBox(-1.0F, -21.0F, 13.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(25, 11).addBox(-1.0F, -22.0F, 13.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(25, 9)
                        .addBox(-1.0F, -22.0F, 14.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(25, 7).addBox(-1.0F, -21.0F, 19.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(20, 18)
                        .addBox(-1.2F, -21.0F, 20.0F, 1.4F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(25, 5).addBox(-1.0F, -20.0F, 20.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(25, 3)
                        .addBox(-1.0F, -22.0F, 20.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(25, 1).addBox(-1.0F, -23.0F, 21.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(20, 14)
                        .addBox(-1.2F, -23.0F, 22.0F, 1.4F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(22, 24).addBox(-1.0F, -22.0F, 22.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(18, 24)
                        .addBox(-1.0F, -23.0F, 23.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(20, 12).addBox(-1.2F, -24.0F, 23.0F, 1.4F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(10, 24)
                        .addBox(-1.0F, -24.0F, 22.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(6, 24).addBox(-1.0F, -24.0F, 24.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 24)
                        .addBox(-1.0F, -25.0F, 24.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(23, 19).addBox(-1.0F, -25.0F, 23.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(23, 17)
                        .addBox(-1.0F, -25.0F, 22.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(23, 15).addBox(-1.0F, -23.0F, 24.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(20, 16)
                        .addBox(-1.2F, -22.0F, 21.0F, 1.4F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(3, 25).addBox(-1.0F, -21.0F, 21.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(26, 16)
                        .addBox(-1.0F, -19.0F, 15.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(16, 26).addBox(-1.0F, -20.0F, 18.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(20, 16)
                        .addBox(-1.2F, -20.0F, 19.0F, 1.4F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(12, 22).addBox(-1.2F, -19.0F, 16.0F, 1.4F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(20, 28)
                        .addBox(-1.0F, -18.0F, 15.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(16, 28).addBox(-1.0F, -17.0F, 14.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(28, 12)
                        .addBox(-1.0F, -16.0F, 13.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(28, 10).addBox(-1.0F, -15.0F, 12.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(10, 28)
                        .addBox(-1.0F, -14.0F, 11.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(28, 8).addBox(-1.0F, -13.0F, 10.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(28, 6)
                        .addBox(-1.0F, -12.0F, 9.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(6, 28).addBox(-1.0F, -11.0F, 8.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(28, 4)
                        .addBox(-1.0F, -10.0F, 7.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(28, 2).addBox(-1.0F, -9.0F, 6.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(28, 0)
                        .addBox(-1.0F, -8.0F, 5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 28).addBox(-1.0F, -7.0F, 4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(27, 27)
                        .addBox(-1.0F, -6.0F, 3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(23, 27).addBox(-1.0F, -5.0F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(13, 27)
                        .addBox(-1.0F, -4.0F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(3, 27).addBox(-1.0F, -3.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(20, 4)
                        .addBox(-1.3F, -3.0F, 3.0F, 1.6F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(20, 2).addBox(-1.3F, -4.0F, 3.0F, 1.6F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(20, 0)
                        .addBox(-1.3F, -4.0F, 2.0F, 1.6F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 20).addBox(-1.3F, -4.0F, 4.0F, 1.6F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(15, 19)
                        .addBox(-1.3F, -5.0F, 4.0F, 1.6F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(5, 19).addBox(-1.3F, -5.0F, 3.0F, 1.6F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(10, 18)
                        .addBox(-1.3F, -5.0F, 5.0F, 1.6F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 18).addBox(-1.3F, -6.0F, 5.0F, 1.6F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(15, 17)
                        .addBox(-1.3F, -6.0F, 4.0F, 1.6F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(5, 17).addBox(-1.3F, -6.0F, 6.0F, 1.6F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(10, 16)
                        .addBox(-1.3F, -7.0F, 6.0F, 1.6F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 16).addBox(-1.3F, -7.0F, 5.0F, 1.6F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(15, 15)
                        .addBox(-1.3F, -7.0F, 7.0F, 1.6F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(15, 13).addBox(-1.3F, -8.0F, 7.0F, 1.6F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(15, 11)
                        .addBox(-1.3F, -8.0F, 6.0F, 1.6F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(15, 9).addBox(-1.3F, -8.0F, 8.0F, 1.6F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(15, 7)
                        .addBox(-1.3F, -9.0F, 8.0F, 1.6F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(15, 5).addBox(-1.3F, -9.0F, 7.0F, 1.6F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(5, 15)
                        .addBox(-1.3F, -9.0F, 9.0F, 1.6F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(15, 1).addBox(-1.3F, -10.0F, 8.0F, 1.6F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(10, 14)
                        .addBox(-1.3F, -10.0F, 10.0F, 1.6F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 14).addBox(-1.3F, -11.0F, 9.0F, 1.6F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(5, 13)
                        .addBox(-1.3F, -11.0F, 10.0F, 1.6F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(10, 12).addBox(-1.3F, -11.0F, 11.0F, 1.6F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 12)
                        .addBox(-1.3F, -12.0F, 11.0F, 1.6F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(5, 11).addBox(-1.3F, -12.0F, 10.0F, 1.6F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(10, 10)
                        .addBox(-1.3F, -12.0F, 12.0F, 1.6F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(10, 8).addBox(-1.3F, -13.0F, 12.0F, 1.6F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(10, 6)
                        .addBox(-1.3F, -13.0F, 11.0F, 1.6F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(10, 4).addBox(-1.3F, -13.0F, 13.0F, 1.6F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(10, 2)
                        .addBox(-1.3F, -14.0F, 13.0F, 1.6F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(10, 0).addBox(-1.3F, -14.0F, 12.0F, 1.6F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 10)
                        .addBox(-1.3F, -14.0F, 14.0F, 1.6F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(5, 9).addBox(-1.3F, -15.0F, 14.0F, 1.6F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 8)
                        .addBox(-1.3F, -15.0F, 13.0F, 1.6F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(5, 7).addBox(-1.3F, -15.0F, 15.0F, 1.6F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 6)
                        .addBox(-1.3F, -16.0F, 15.0F, 1.6F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(5, 5).addBox(-1.3F, -16.0F, 14.0F, 1.6F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(5, 3)
                        .addBox(-1.3F, -16.0F, 16.0F, 1.6F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(5, 1).addBox(-1.3F, -17.0F, 16.0F, 1.6F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 4)
                        .addBox(-1.3F, -17.0F, 15.0F, 1.6F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 2).addBox(-1.3F, -17.0F, 17.0F, 1.6F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(3, 23)
                        .addBox(-1.2F, -18.0F, 17.0F, 1.4F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(0, 0).addBox(-1.3F, -18.0F, 16.0F, 1.6F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(31, 1)
                        .addBox(-1.0F, -18.0F, 18.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(20, 18).addBox(-1.2F, -19.0F, 18.0F, 1.4F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(30, 30)
                        .addBox(-1.0F, -19.0F, 17.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
                PartPose.offset(8.0F, 24.0F, -8.0F));
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.group.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public void setupAnim(@NotNull T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }
}