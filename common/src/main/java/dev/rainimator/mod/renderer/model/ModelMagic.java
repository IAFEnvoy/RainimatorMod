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
public class ModelMagic<T extends Entity> extends EntityModel<T> {
    public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new Identifier(RainimatorMod.MOD_ID, "modelmagic"), "main");
    public final ModelPart Head;

    public ModelMagic(ModelPart root) {
        this.Head = root.getChild("Head");
    }

    public static TexturedModelData createBodyLayer() {
        ModelData meshdefinition = new ModelData();
        ModelPartData partdefinition = meshdefinition.getRoot();
        ModelPartData Head = partdefinition.addChild("Head", ModelPartBuilder.create(), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.1047F, 0.0873F, 0.0F));
        ModelPartData cube_r1 = Head.addChild("cube_r1",
                ModelPartBuilder.create().uv(0, 5).cuboid(-1.0F, -12.0F, 3.5F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F)).uv(0, 0).cuboid(-1.0F, -22.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)).uv(0, 29)
                        .cuboid(-3.0F, -19.0F, -3.0F, 6.0F, 6.0F, 6.0F, new Dilation(0.0F)).uv(0, 14).cuboid(-5.0F, -14.0F, -5.0F, 10.0F, 4.0F, 10.0F, new Dilation(0.0F)).uv(0, 0)
                        .cuboid(-6.0F, -10.0F, -6.0F, 12.0F, 2.0F, 12.0F, new Dilation(0.0F)),
                ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 3.1416F, 0.0F));
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