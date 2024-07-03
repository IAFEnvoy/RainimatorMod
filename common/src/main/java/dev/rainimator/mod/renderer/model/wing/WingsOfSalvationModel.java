package dev.rainimator.mod.renderer.model.wing;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.entity.LivingEntity;

@Environment(EnvType.CLIENT)
public class WingsOfSalvationModel<T extends LivingEntity> extends WingEntityModel<T> {
    public WingsOfSalvationModel() {
        super();
        this.textureWidth = 64;
        this.textureHeight = 16;

        this.leftWing.setTextureOffset(0, 0).addCuboid(0F, 0.0F, -30F, 1.0F, 16.0F, 32.0F);
        this.setRotationAngle(this.leftWing, -16F, 19F, 0F, -0.1F, 1.57F, -0.75F);

        this.rightWing.setTextureOffset(0, 0).addCuboid(-0.75F, 0.0F, -30.75F, 1.0F, 16.0F, 32.0F);
        this.setRotationAngle(this.rightWing, 16F, 19F, 0F, -0.1F, -1.57F, 0.75F);
    }

    public void setRotationAngle(ModelPart bone, float pivotX, float pivotY, float pivotZ, float x, float y, float z) {
        bone.pivotX = pivotX;
        bone.pivotY = pivotY;
        bone.pivotZ = pivotZ;
        bone.pitch = x;
        bone.yaw = y;
        bone.roll = z;
    }
}
