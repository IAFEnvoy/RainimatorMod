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
public class ModelLearSword<T extends Entity> extends EntityModel<T> {
    public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new Identifier(RainimatorMod.MOD_ID, "modellearsword"), "main");
    public final ModelPart Body;

    public ModelLearSword(ModelPart root) {
        this.Body = root.getChild("Body");
    }

    public static TexturedModelData createBodyLayer() {
        ModelData meshdefinition = new ModelData();
        ModelPartData partdefinition = meshdefinition.getRoot();
        ModelPartData Body = partdefinition.addChild("Body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        ModelPartData diamond_sword_20_r1 = Body.addChild("diamond_sword_20_r1",
                ModelPartBuilder.create().uv(16, 14).cuboid(11.0F, -25.0F, 8.0F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F)).uv(24, 31).cuboid(3.0F, -26.0F, 8.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)).uv(26, 21)
                        .cuboid(10.0F, -26.0F, 8.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)).uv(12, 0).cuboid(4.0F, -27.0F, 8.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)).uv(30, 10)
                        .cuboid(9.0F, -27.0F, 8.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)).uv(26, 26).cuboid(7.0F, -30.0F, 8.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)).uv(28, 31)
                        .cuboid(9.0F, -30.0F, 8.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)).uv(4, 12).cuboid(6.0F, -31.0F, 8.0F, 1.0F, 6.0F, 1.0F, new Dilation(0.0F)).uv(8, 12)
                        .cuboid(8.0F, -31.0F, 8.0F, 1.0F, 6.0F, 1.0F, new Dilation(0.0F)).uv(0, 0).cuboid(5.0F, -32.0F, 8.0F, 1.0F, 7.0F, 1.0F, new Dilation(0.0F)).uv(27, 16)
                        .cuboid(9.0F, -32.0F, 8.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)).uv(15, 18).cuboid(4.0F, -33.0F, 8.0F, 1.0F, 5.0F, 1.0F, new Dilation(0.0F)).uv(33, 4)
                        .cuboid(-1.0F, -34.0F, 8.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(4, 19).cuboid(3.0F, -34.0F, 8.0F, 1.0F, 5.0F, 1.0F, new Dilation(0.0F)).uv(29, 25)
                        .cuboid(-2.0F, -35.0F, 8.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(8, 19).cuboid(2.0F, -35.0F, 8.0F, 1.0F, 5.0F, 1.0F, new Dilation(0.0F)).uv(19, 18)
                        .cuboid(1.0F, -36.0F, 8.0F, 1.0F, 5.0F, 1.0F, new Dilation(0.0F)).uv(20, 0).cuboid(0.0F, -37.0F, 8.0F, 1.0F, 5.0F, 1.0F, new Dilation(0.0F)).uv(4, 0)
                        .cuboid(-3.0F, -38.0F, 8.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.of(-13.0F, -6.0F, 0.0F, 0.0F, 0.0F, 1.309F));
        ModelPartData diamond_sword_19_r1 = Body.addChild("diamond_sword_19_r1",
                ModelPartBuilder.create().uv(20, 6).cuboid(6.0F, -14.0F, 8.0F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F)).uv(20, 32).cuboid(-2.0F, -15.0F, 8.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)).uv(0, 29)
                        .cuboid(5.0F, -15.0F, 8.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)).uv(30, 27).cuboid(-1.0F, -16.0F, 8.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)).uv(4, 31)
                        .cuboid(4.0F, -16.0F, 8.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)).uv(12, 29).cuboid(2.0F, -19.0F, 8.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)).uv(32, 31)
                        .cuboid(4.0F, -19.0F, 8.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)).uv(12, 12).cuboid(1.0F, -20.0F, 8.0F, 1.0F, 6.0F, 1.0F, new Dilation(0.0F)).uv(0, 16)
                        .cuboid(3.0F, -20.0F, 8.0F, 1.0F, 6.0F, 1.0F, new Dilation(0.0F)).uv(4, 4).cuboid(0.0F, -21.0F, 8.0F, 1.0F, 7.0F, 1.0F, new Dilation(0.0F)).uv(19, 29)
                        .cuboid(4.0F, -21.0F, 8.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)).uv(22, 10).cuboid(-1.0F, -22.0F, 8.0F, 1.0F, 5.0F, 1.0F, new Dilation(0.0F)).uv(33, 9)
                        .cuboid(-6.0F, -23.0F, 8.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(0, 23).cuboid(-2.0F, -23.0F, 8.0F, 1.0F, 5.0F, 1.0F, new Dilation(0.0F)).uv(30, 14)
                        .cuboid(-7.0F, -24.0F, 8.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(12, 23).cuboid(-3.0F, -24.0F, 8.0F, 1.0F, 5.0F, 1.0F, new Dilation(0.0F)).uv(23, 16)
                        .cuboid(-4.0F, -25.0F, 8.0F, 1.0F, 5.0F, 1.0F, new Dilation(0.0F)).uv(22, 23).cuboid(-5.0F, -26.0F, 8.0F, 1.0F, 5.0F, 1.0F, new Dilation(0.0F)).uv(8, 4)
                        .cuboid(-8.0F, -27.0F, 8.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.of(-13.0F, -6.0F, 0.0F, 0.0F, 0.0F, 0.7418F));
        ModelPartData diamond_sword_18_r1 = Body.addChild("diamond_sword_18_r1",
                ModelPartBuilder.create().uv(24, 0).cuboid(0.0F, 2.0F, 8.0F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F)).uv(33, 16).cuboid(-8.0F, 1.0F, 8.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)).uv(30, 0)
                        .cuboid(-1.0F, 1.0F, 8.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)).uv(8, 31).cuboid(-7.0F, 0.0F, 8.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)).uv(16, 31)
                        .cuboid(-2.0F, 0.0F, 8.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)).uv(30, 5).cuboid(-4.0F, -3.0F, 8.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)).uv(0, 34)
                        .cuboid(-2.0F, -3.0F, 8.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)).uv(16, 0).cuboid(-5.0F, -4.0F, 8.0F, 1.0F, 6.0F, 1.0F, new Dilation(0.0F)).uv(16, 7)
                        .cuboid(-3.0F, -4.0F, 8.0F, 1.0F, 6.0F, 1.0F, new Dilation(0.0F)).uv(0, 8).cuboid(-6.0F, -5.0F, 8.0F, 1.0F, 7.0F, 1.0F, new Dilation(0.0F)).uv(29, 19)
                        .cuboid(-2.0F, -5.0F, 8.0F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F)).uv(16, 24).cuboid(-7.0F, -6.0F, 8.0F, 1.0F, 5.0F, 1.0F, new Dilation(0.0F)).uv(34, 0)
                        .cuboid(-12.0F, -7.0F, 8.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(4, 25).cuboid(-8.0F, -7.0F, 8.0F, 1.0F, 5.0F, 1.0F, new Dilation(0.0F)).uv(30, 22)
                        .cuboid(-13.0F, -8.0F, 8.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(8, 25).cuboid(-9.0F, -8.0F, 8.0F, 1.0F, 5.0F, 1.0F, new Dilation(0.0F)).uv(26, 4)
                        .cuboid(-10.0F, -9.0F, 8.0F, 1.0F, 5.0F, 1.0F, new Dilation(0.0F)).uv(26, 10).cuboid(-11.0F, -10.0F, 8.0F, 1.0F, 5.0F, 1.0F, new Dilation(0.0F)).uv(8, 8)
                        .cuboid(-14.0F, -11.0F, 8.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.of(-13.0F, -6.0F, 0.0F, 0.0F, 0.0F, 0.2182F));
        return TexturedModelData.of(meshdefinition, 64, 64);
    }

    @Override
    public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    @Override
    public void render(MatrixStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}