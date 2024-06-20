package dev.rainimator.mod.renderer.model.wing;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.*;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class WingEntityModel<T extends LivingEntity> extends AnimalModel<T> {
    public final ModelPart rightWing;
    public final ModelPart leftWing;
    public State state = State.IDLE;

    public WingEntityModel(ModelPart root) {
        this.rightWing = root.getChild("rightWing");
        this.leftWing = root.getChild("leftWing");
    }

    public static ModelData getModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        modelPartData.addChild("rightWing", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 5.0F, 0.0F));
        modelPartData.addChild("leftWing", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 5.0F, 0.0F));

        return modelData;
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.state = State.IDLE;
        float a = 0.125F;
        float b = 0.1F;
        float k = 0.4F;
        float l = -0.5F;
        float m = -1.0F;
        float n = 0.0F;

        if (entity.isFallFlying()) {
            this.state = State.FLYING;
            float o = 1.0F;
            Vec3d vec3d = entity.getVelocity();

            if (vec3d.y < 0.0D) {
                Vec3d vec3d2 = vec3d.normalize();
                o = 1.0F - (float) Math.pow(-vec3d2.y, 1.5D);
            }

            k = o * 0.35F + (1.0F - o) * k;
            l = o * -1.6F + (0.3F - o) * l;

            if (entity.forwardSpeed > 0) {
                a = 0.4F;
                b = 1.0F;
            }
        } else if (entity.isInSneakingPose()) {
            this.state = State.CROUCHING;
            k = 0.7F;
            m = 0.0F;
            n = 0.09F;
        }

        k += MathHelper.sin(entity.age * a) * b;
        this.leftWing.pivotX = 3.0F;
        this.leftWing.pivotY = m;
        this.leftWing.pivotZ = -2.7F;

        if (entity instanceof AbstractClientPlayerEntity player) {
            player.elytraPitch = (player.elytraPitch + (n - player.elytraPitch) * 0.1F);
            player.elytraYaw = (player.elytraYaw + (k - player.elytraYaw) * 0.1F);
            player.elytraRoll = (player.elytraRoll + (l - player.elytraRoll) * 0.1F);
            this.leftWing.pitch = player.elytraPitch;
            this.leftWing.yaw = player.elytraYaw + 0.9F;
            this.leftWing.roll = player.elytraRoll + 0.55F;
        } else {
            this.leftWing.pitch = k;
            this.leftWing.roll = l;
            this.leftWing.yaw = n;
        }

        this.rightWing.pivotX = -this.leftWing.pivotX;
        this.rightWing.yaw = -this.leftWing.yaw;
        this.rightWing.pivotY = this.leftWing.pivotY;
        this.rightWing.pitch = this.leftWing.pitch;
        this.rightWing.roll = -this.leftWing.roll;
        this.rightWing.pivotZ = this.leftWing.pivotZ;
    }

    @Override
    protected Iterable<ModelPart> getHeadParts() {
        return ImmutableList.of();
    }

    @Override
    protected Iterable<ModelPart> getBodyParts() {
        return ImmutableList.of(this.rightWing, this.leftWing);
    }

    public enum State {
        IDLE, CROUCHING, FLYING
    }
}