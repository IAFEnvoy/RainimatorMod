package dev.rainimator.mod.renderer;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3f;


public interface IArmorRenderHelper {
    //From trinkets
    static void translateToChest(MatrixStack matrices, PlayerEntityModel<AbstractClientPlayerEntity> model, AbstractClientPlayerEntity player) {
        if (player.isInSneakingPose() && !model.riding && !player.isSwimming()) {
            matrices.translate(0.0F, 0.2F, 0.0F);
            matrices.multiply(Vec3f.POSITIVE_X.getRadialQuaternion(model.body.pitch));
        }
        matrices.multiply(Vec3f.POSITIVE_Y.getRadialQuaternion(model.body.yaw));
        matrices.translate(0.0F, 0.4F, -0.16F);
    }
}
