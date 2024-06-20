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
public class ModelModel<T extends Entity> extends EntityModel<T> {
    public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new Identifier(RainimatorMod.MOD_ID, "modelmodel"), "main");
    public final ModelPart Head;

    public ModelModel(ModelPart root) {
        this.Head = root.getChild("Head");
    }

    public static TexturedModelData createBodyLayer() {
        ModelData meshdefinition = new ModelData();
        ModelPartData partdefinition = meshdefinition.getRoot();
        ModelPartData Head = partdefinition.addChild("Head",
                ModelPartBuilder.create().uv(0, 4).cuboid(-3.0F, -11.0F, -7.0F, 10.0F, 3.0F, 2.0F, new Dilation(0.0F)).uv(10, 9).cuboid(6.0F, -12.0F, 5.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(10, 9)
                        .cuboid(-4.0F, -12.0F, 5.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 20).cuboid(8.0F, -14.0F, -2.0F, 1.0F, 7.0F, 3.0F, new Dilation(0.0F)).uv(24, 4)
                        .cuboid(8.0F, -12.0F, 1.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)).uv(24, 4).cuboid(8.0F, -12.0F, -3.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)).uv(20, 9)
                        .cuboid(9.0F, -16.0F, -2.0F, 1.0F, 8.0F, 3.0F, new Dilation(0.0F)).uv(0, 9).cuboid(10.0F, -24.0F, -1.5F, 1.0F, 16.0F, 2.0F, new Dilation(0.0F)).uv(6, 9)
                        .cuboid(11.0F, -23.0F, -1.5F, 1.0F, 14.0F, 2.0F, new Dilation(0.0F)).uv(12, 9).cuboid(12.0F, -22.0F, -2.5F, 1.0F, 12.0F, 2.5F, new Dilation(0.0F)).uv(11, 24)
                        .cuboid(13.0F, -20.0F, -2.0F, 1.0F, 8.0F, 1.0F, new Dilation(0.0F)).uv(20, 9).cuboid(10.0F, -16.0F, -2.0F, 1.0F, 8.0F, 3.0F, new Dilation(0.0F)).uv(24, 4)
                        .cuboid(9.0F, -24.0F, -0.5F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)).uv(24, 4).cuboid(8.0F, -25.0F, 0.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)).uv(17, 9)
                        .cuboid(8.0F, -24.0F, 1.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.of(0.0F, 0.0F, 0.0F, -0.1047F, 0.0873F, 0.0F));
        ModelPartData cube_r1 = Head.addChild("cube_r1",
                ModelPartBuilder.create().uv(17, 9).cuboid(-8.0F, -25.0F, -20.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)).uv(24, 4).cuboid(-8.0F, -26.0F, -21.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)).uv(24, 4)
                        .cuboid(-7.0F, -25.0F, -21.5F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)).uv(20, 9).cuboid(-6.0F, -17.0F, -23.0F, 1.0F, 8.0F, 3.0F, new Dilation(0.0F)).uv(11, 24)
                        .cuboid(-3.0F, -21.0F, -23.0F, 1.0F, 8.0F, 1.0F, new Dilation(0.0F)).uv(12, 9).cuboid(-4.0F, -23.0F, -23.5F, 1.0F, 12.0F, 2.5F, new Dilation(0.0F)).uv(6, 9)
                        .cuboid(-5.0F, -24.0F, -22.5F, 1.0F, 14.0F, 2.0F, new Dilation(0.0F)).uv(0, 9).cuboid(-6.0F, -25.0F, -22.5F, 1.0F, 16.0F, 2.0F, new Dilation(0.0F)).uv(20, 9)
                        .cuboid(-7.0F, -17.0F, -23.0F, 1.0F, 8.0F, 3.0F, new Dilation(0.0F)).uv(24, 4).cuboid(-8.0F, -13.0F, -24.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)).uv(24, 4)
                        .cuboid(-8.0F, -13.0F, -20.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)).uv(20, 20).cuboid(-8.0F, -15.0F, -23.0F, 1.0F, 7.0F, 3.0F, new Dilation(0.0F)),
                ModelTransform.of(-12.0F, 1.0F, -22.0F, 0.0F, 3.1416F, 0.0F));
        ModelPartData cube_r2 = Head.addChild("cube_r2",
                ModelPartBuilder.create().uv(4, 9).cuboid(5.0F, -9.0F, 2.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(4, 9).cuboid(5.0F, -9.0F, 4.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(4, 9)
                        .cuboid(5.0F, -9.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(4, 9).cuboid(5.0F, -9.0F, -2.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(4, 9)
                        .cuboid(5.0F, -9.0F, -4.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(4, 9).cuboid(3.0F, -9.0F, -6.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(4, 9)
                        .cuboid(1.0F, -9.0F, -6.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(4, 9).cuboid(-1.0F, -9.0F, -6.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(4, 9)
                        .cuboid(-3.0F, -9.0F, -6.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(0, 0).cuboid(-6.0F, -8.0F, -6.0F, 12.0F, 3.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.of(2.0F, -3.0F, 0.0F, 0.0F, 3.1416F, 0.0F));
        ModelPartData cube_r3 = Head.addChild("cube_r3",
                ModelPartBuilder.create().uv(4, 9).cuboid(-5.0F, -9.0F, -5.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(4, 9).cuboid(-5.0F, -9.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(4, 9)
                        .cuboid(-5.0F, -9.0F, -3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(4, 9).cuboid(-5.0F, -9.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(4, 9)
                        .cuboid(-5.0F, -9.0F, 1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(4, 9).cuboid(-5.0F, -9.0F, 5.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.of(3.0F, -3.0F, 0.0F, 0.0F, 3.1416F, 0.0F));
        ModelPartData cube_r4 = Head.addChild("cube_r4",
                ModelPartBuilder.create().uv(4, 9).cuboid(-3.0F, -9.0F, 4.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(4, 9).cuboid(-2.0F, -9.0F, 4.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(4, 9)
                        .cuboid(1.0F, -9.0F, 4.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(4, 9).cuboid(0.0F, -10.0F, 4.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(4, 9)
                        .cuboid(-1.0F, -10.0F, 4.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(4, 9).cuboid(-1.0F, -9.0F, 4.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(4, 9)
                        .cuboid(0.0F, -9.0F, 4.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(15, 24).cuboid(2.0F, -9.0F, 4.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.of(2.0F, -3.0F, -2.0F, 0.0F, 3.1416F, 0.0F));
        ModelPartData cube_r5 = Head.addChild("cube_r5", ModelPartBuilder.create().uv(0, 0).cuboid(-5.0F, -8.0F, -6.0F, 12.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, -3.0F, 0.0F, 0.0F, 1.5708F, 0.0F));
        ModelPartData cube_r6 = Head.addChild("cube_r6", ModelPartBuilder.create().uv(0, 0).cuboid(-7.0F, -8.0F, -5.0F, 12.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, -3.0F, 0.0F, 0.0F, -1.5708F, 0.0F));
        return TexturedModelData.of(meshdefinition, 64, 64);
    }

    @Override
    public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    @Override
    public void render(MatrixStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.Head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}