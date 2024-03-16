package com.rainimator.rainimatormod.renderer.model.wing;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.rainimator.rainimatormod.RainimatorMod;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class WingsOfSalvationModel<T extends LivingEntity> extends EntityModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(RainimatorMod.MOD_ID, "wings_of_salvation"), "main");
    public final ModelPart leftWing;
    public final ModelPart rightWing;
    public State state = State.IDLE;

    public WingsOfSalvationModel(ModelPart root) {
        this.leftWing = root.getChild("leftWing");
        this.rightWing = root.getChild("rightWing");
    }

    public static LayerDefinition createLayer(){
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        CubeDeformation cubedeformation = new CubeDeformation(1.0F);
        partdefinition.addOrReplaceChild("leftWing",
                CubeListBuilder.create().texOffs(0,0).mirror().addBox(0.0F, 0.0F, 0.0F, 1.0F, 16.0F, 32.0F,cubedeformation),
                PartPose.offsetAndRotation(-16F, 19F, 1F, 0F, 1.57F, -0.75F));
        partdefinition.addOrReplaceChild("rightWing",
                CubeListBuilder.create().texOffs(0,0).addBox(0.0F, 0.0F, 0.0F, 1.0F, 16.0F, 32.0F,cubedeformation),
                PartPose.offsetAndRotation(16F, 19F, 0F, 0F, -1.57F, 0.75F));
//        partdefinition.addOrReplaceChild("leftWing", CubeListBuilder.create().texOffs(0, 0).addBox(-10.0F, 0.0F, 0.0F, 10.0F, 20.0F, 2.0F, cubedeformation), PartPose.offsetAndRotation(5.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
//        partdefinition.addOrReplaceChild("rightWing", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, 0.0F, 0.0F, 10.0F, 20.0F, 2.0F, cubedeformation), PartPose.offsetAndRotation(-5.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.state = State.IDLE;
        float a = 0.125F;
        float b = 0.1F;
        float k = 0.3F;
        float l = -0.7F;
        float m = -1.0F;
        float n = 0.0F;

        if (entity.isFallFlying()) {
            this.state = State.FLYING;
            float o = 1.0F;
            Vec3 vec3d = entity.getDeltaMovement();

            if (vec3d.y < 0.0D) {
                Vec3 vec3d2 = vec3d.normalize();
                o = 1.0F - (float) Math.pow(-vec3d2.y, 1.5D);
            }

            k = o * 0.35F + (1.0F - o) * k;
            l = o * -1.6F + (0.3F - o) * l;

            if (entity.zza > 0) {
                a = 0.4F;
                b = 1.0F;
            }
        } else if (entity.isShiftKeyDown()) {
            this.state = State.CROUCHING;
            k = 0.7F;
            m = 0.0F;
            n = 0.09F;
        }

        k += Math.sin(entity.tickCount * a) * b;
        this.rightWing.x = 7.0F;
        this.rightWing.y = m;

        if (entity instanceof AbstractClientPlayer player) {
            player.elytraRotX = player.elytraRotX + (k - player.elytraRotX) * 0.1F;
            player.elytraRotY = player.elytraRotY + (n - player.elytraRotY) * 0.1F;
            player.elytraRotZ = player.elytraRotZ + (l - player.elytraRotZ) * 0.1F;
            this.rightWing.xRot = player.elytraRotX;
            this.rightWing.yRot = player.elytraRotY;
            this.rightWing.zRot = player.elytraRotZ;
        } else {
            this.rightWing.xRot = k;
            this.rightWing.yRot = n;
            this.rightWing.zRot = l;
        }

        this.leftWing.x = -this.rightWing.x;
        this.leftWing.y = this.rightWing.y;
        this.leftWing.xRot = this.rightWing.xRot;
        this.leftWing.yRot = -this.rightWing.yRot;
        this.leftWing.zRot = -this.rightWing.zRot;
    }

    @Override
    public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.leftWing.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightWing.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public enum State {
        IDLE, CROUCHING, FLYING
    }
}
