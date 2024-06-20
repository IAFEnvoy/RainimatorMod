package dev.rainimator.mod.renderer.model;

import dev.rainimator.mod.RainimatorMod;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ModelNetherKing1<T extends Entity> extends EntityModel<T> {
    public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new Identifier(RainimatorMod.MOD_ID, "modelnether_king_1"), "main");
    public final ModelPart Head;

    public ModelNetherKing1(ModelPart root) {
        this.Head = root.getChild("Head");
    }

    public static TexturedModelData createBodyLayer() {
        ModelData meshdefinition = new ModelData();
        ModelPartData partdefinition = meshdefinition.getRoot();
        ModelPartData Head = partdefinition.addChild("Head",
                ModelPartBuilder.create().uv(0, 21).cuboid(-5.0F, -9.0F, -5.0F, 10.0F, 2.0F, 1.0F, new Dilation(0.0F)).uv(0, 35).cuboid(-5.0F, -10.0F, -5.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(22, 16)
                        .cuboid(-3.0F, -10.0F, -5.0F, 6.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(34, 34).cuboid(4.0F, -10.0F, -5.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(0, 16)
                        .cuboid(-0.8F, -10.0F, -5.2F, 1.6F, 1.4F, 0.2F, new Dilation(0.0F)).uv(0, 18).cuboid(-0.8F, -8.6F, -5.1F, 1.6F, 0.5F, 0.1F, new Dilation(0.0F)).uv(0, 38)
                        .cuboid(-1.4F, -10.0F, -5.1F, 0.6F, 1.4F, 0.1F, new Dilation(0.0F)).uv(0, 38).cuboid(0.8F, -10.0F, -5.1F, 0.6F, 1.4F, 0.1F, new Dilation(0.0F)).uv(11, 11)
                        .cuboid(-5.0F, -8.0F, -4.0F, 1.0F, 1.0F, 9.0F, new Dilation(0.0F)).uv(12, 2).cuboid(4.0F, -8.0F, -4.0F, 1.0F, 1.0F, 8.0F, new Dilation(0.0F)).uv(22, 2)
                        .cuboid(-4.0F, -8.0F, 4.0F, 9.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(22, 0).cuboid(-5.0F, -9.0F, 4.0F, 10.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(0, 10)
                        .cuboid(4.0F, -9.0F, -4.0F, 1.0F, 1.0F, 9.0F, new Dilation(0.0F)).uv(0, 0).cuboid(-5.0F, -9.0F, -4.0F, 1.0F, 1.0F, 9.0F, new Dilation(0.0F)).uv(34, 32)
                        .cuboid(4.0F, -10.0F, -3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(34, 30).cuboid(4.0F, -10.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 34)
                        .cuboid(4.0F, -10.0F, 1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(11, 16).cuboid(4.0F, -10.0F, 3.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)).uv(2, 15)
                        .cuboid(-5.0F, -10.0F, 3.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)).uv(34, 18).cuboid(-5.0F, -10.0F, 1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(11, 34)
                        .cuboid(-5.0F, -10.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(7, 34).cuboid(-5.0F, -10.0F, -3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(31, 33)
                        .cuboid(2.0F, -10.0F, 4.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(27, 33).cuboid(-3.0F, -10.0F, 4.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(22, 18)
                        .cuboid(-1.0F, -10.0F, 4.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.of(0.0F, 1.0F, 0.0F, -0.1047F, 0.0873F, 0.0F));
        ModelPartData cube_r1 = Head.addChild("cube_r1",
                ModelPartBuilder.create().uv(4, 6).cuboid(-8.0F, -38.0F, 1.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)).uv(15, 16).cuboid(-7.0F, -38.0F, 2.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(24, 21)
                        .cuboid(-6.0F, -39.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(28, 18).cuboid(-5.0F, -40.0F, 4.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(31, 19)
                        .cuboid(-3.0F, -42.0F, 5.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(15, 0).cuboid(-4.0F, -42.0F, 4.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)).uv(4, 10)
                        .cuboid(-5.0F, -41.0F, 3.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)).uv(11, 0).cuboid(-6.0F, -40.0F, 2.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)).uv(4, 0)
                        .cuboid(-7.0F, -40.0F, 1.0F, 1.0F, 5.0F, 1.0F, new Dilation(0.0F)).uv(31, 4).cuboid(-8.0F, -39.0F, 0.0F, 1.0F, 11.0F, 1.0F, new Dilation(0.0F)).uv(19, 21)
                        .cuboid(-7.0F, -35.0F, -2.0F, 1.0F, 8.0F, 3.0F, new Dilation(0.0F)).uv(11, 11).cuboid(-6.0F, -31.0F, -3.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)).uv(8, 24)
                        .cuboid(-6.0F, -33.0F, -2.0F, 1.0F, 7.0F, 3.0F, new Dilation(0.0F)).uv(14, 4).cuboid(-6.0F, -31.0F, 1.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, 3.1416F, 0.0F));
        ModelPartData cube_r2 = Head.addChild("cube_r2",
                ModelPartBuilder.create().uv(26, 31).cuboid(15.0F, -40.0F, 4.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(30, 31).cuboid(16.0F, -39.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 32)
                        .cuboid(19.0F, -37.0F, 0.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(13, 24).cuboid(18.0F, -38.0F, 1.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)).uv(32, 21)
                        .cuboid(17.0F, -38.0F, 2.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(23, 33).cuboid(13.0F, -42.0F, 5.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(26, 11)
                        .cuboid(14.0F, -42.0F, 4.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)).uv(15, 11).cuboid(15.0F, -41.0F, 3.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)).uv(22, 4)
                        .cuboid(16.0F, -40.0F, 2.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)).uv(0, 10).cuboid(17.0F, -40.0F, 1.0F, 1.0F, 5.0F, 1.0F, new Dilation(0.0F)).uv(16, 31)
                        .cuboid(18.0F, -39.0F, 0.0F, 1.0F, 11.0F, 1.0F, new Dilation(0.0F)).uv(0, 0).cuboid(2.0F, -38.0F, -0.5F, 1.0F, 8.0F, 1.0F, new Dilation(0.0F)).uv(0, 0)
                        .cuboid(19.0F, -38.0F, -0.5F, 1.0F, 8.0F, 1.0F, new Dilation(0.0F)).uv(0, 24).cuboid(17.0F, -35.0F, -2.0F, 1.0F, 8.0F, 3.0F, new Dilation(0.0F)).uv(22, 11)
                        .cuboid(16.0F, -31.0F, -3.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)).uv(26, 4).cuboid(16.0F, -31.0F, 1.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)).uv(27, 21)
                        .cuboid(16.0F, -33.0F, -2.0F, 1.0F, 7.0F, 3.0F, new Dilation(0.0F)),
                ModelTransform.of(11.0F, 24.0F, 0.0F, 0.0F, 3.1416F, 0.0F));
        return TexturedModelData.of(meshdefinition, 64, 64);
    }

    @Override
    public void render(MatrixStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.Head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }
}