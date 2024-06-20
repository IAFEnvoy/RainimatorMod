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
public class ModelPorkshireKingCrown<T extends Entity> extends EntityModel<T> {
    public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new Identifier(RainimatorMod.MOD_ID, "model_porkshire_king_crown"), "main");
    public final ModelPart Head;

    public ModelPorkshireKingCrown(ModelPart root) {
        this.Head = root.getChild("Head");
    }

    public static TexturedModelData createBodyLayer() {
        ModelData meshdefinition = new ModelData();
        ModelPartData partdefinition = meshdefinition.getRoot();
        ModelPartData Head = partdefinition.addChild("Head",
                ModelPartBuilder.create().uv(0, 21).cuboid(-5.0F, -9.0F, -5.0F, 10.0F, 2.0F, 1.0F, new Dilation(0.0F)).uv(0, 21).cuboid(-5.0F, -7.0F, -5.0F, 10.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(0, 35)
                        .cuboid(-5.0F, -10.0F, -5.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(22, 16).cuboid(-3.0F, -10.0F, -5.0F, 6.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(34, 34)
                        .cuboid(4.0F, -10.0F, -5.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(0, 44).cuboid(-1.0F, -10.0F, -5.2F, 2.0F, 2.0F, 0.2F, new Dilation(0.0F)).uv(0, 18)
                        .cuboid(-0.8F, -8.6F, -5.1F, 1.6F, 0.5F, 0.1F, new Dilation(0.0F)).uv(-1, 17).cuboid(-1.0F, -11.0F, -5.1F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(7, 0)
                        .cuboid(-1.4F, -10.0F, -5.1F, 0.6F, 1.4F, 0.1F, new Dilation(0.0F)).uv(8, 45).cuboid(-4.0F, -8.6F, -5.1F, 1.0F, 1.0F, 0.1F, new Dilation(0.0F)).uv(7, 45)
                        .cuboid(3.0F, -8.6F, -5.1F, 1.0F, 1.0F, 0.1F, new Dilation(0.0F)).uv(3, 0).cuboid(0.8F, -10.0F, -5.1F, 0.6F, 1.4F, 0.1F, new Dilation(0.0F)).uv(11, 11)
                        .cuboid(-5.0F, -8.0F, -4.0F, 1.0F, 1.0F, 9.0F, new Dilation(0.0F)).uv(11, 11).cuboid(-5.0F, -7.0F, -4.0F, 1.0F, 1.0F, 9.0F, new Dilation(0.0F)).uv(12, 2)
                        .cuboid(4.0F, -8.0F, -4.0F, 1.0F, 1.0F, 8.0F, new Dilation(0.0F)).uv(12, 2).cuboid(4.0F, -7.0F, -4.0F, 1.0F, 1.0F, 8.0F, new Dilation(0.0F)).uv(22, 2)
                        .cuboid(-4.0F, -8.0F, 4.0F, 9.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(22, 2).cuboid(-4.0F, -7.0F, 4.0F, 9.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(22, 0)
                        .cuboid(-5.0F, -9.0F, 4.0F, 10.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(0, 10).cuboid(4.0F, -9.0F, -4.0F, 1.0F, 1.0F, 9.0F, new Dilation(0.0F)).uv(0, 0)
                        .cuboid(-5.0F, -9.0F, -4.0F, 1.0F, 1.0F, 9.0F, new Dilation(0.0F)).uv(34, 32).cuboid(4.0F, -10.0F, -3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(34, 30)
                        .cuboid(4.0F, -10.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 34).cuboid(4.0F, -10.0F, 1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(11, 16)
                        .cuboid(4.0F, -10.0F, 3.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)).uv(2, 15).cuboid(-5.0F, -10.0F, 3.0F, 1.0F, 1.0F, 2.0F, new Dilation(0.0F)).uv(34, 18)
                        .cuboid(-5.0F, -10.0F, 1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(11, 34).cuboid(-5.0F, -10.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(7, 34)
                        .cuboid(-5.0F, -10.0F, -3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(31, 33).cuboid(2.0F, -10.0F, 4.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(27, 33)
                        .cuboid(-3.0F, -10.0F, 4.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(22, 18).cuboid(-1.0F, -10.0F, 4.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.of(0.0F, 1.0F, 0.0F, -0.1047F, 0.0873F, 0.0F));
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