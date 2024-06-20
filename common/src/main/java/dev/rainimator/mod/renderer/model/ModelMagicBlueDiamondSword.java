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
public class ModelMagicBlueDiamondSword<T extends Entity> extends EntityModel<T> {
    public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new Identifier(RainimatorMod.MOD_ID, "modelmagic_blue_diamond_sword"), "main");
    public final ModelPart group;

    public ModelMagicBlueDiamondSword(ModelPart root) {
        this.group = root.getChild("group");
    }

    public static TexturedModelData createBodyLayer() {
        ModelData meshdefinition = new ModelData();
        ModelPartData partdefinition = meshdefinition.getRoot();
        ModelPartData group = partdefinition.addChild("group",
                ModelPartBuilder.create().uv(15, 3).cuboid(-1.3F, -10.0F, 9.0F, 1.6F, 1.0F, 1.0F, new Dilation(0.0F)).uv(31, 5).cuboid(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(31, 3)
                        .cuboid(-1.0F, -2.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 10).cuboid(-1.3F, -2.0F, 1.0F, 1.6F, 1.0F, 1.0F, new Dilation(0.0F)).uv(3, 31)
                        .cuboid(-1.0F, -1.0F, 1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(10, 20).cuboid(-1.3F, -3.0F, 1.0F, 1.6F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 8)
                        .cuboid(-1.3F, -3.0F, 2.0F, 1.6F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 6).cuboid(-1.3F, -2.0F, 2.0F, 1.6F, 1.0F, 1.0F, new Dilation(0.0F)).uv(30, 28)
                        .cuboid(-1.0F, -1.0F, 2.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(30, 26).cuboid(-1.0F, -2.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 30)
                        .cuboid(-1.0F, -3.0F, 4.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(16, 30).cuboid(-1.0F, -4.0F, 5.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(10, 30)
                        .cuboid(-1.0F, -5.0F, 6.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(6, 30).cuboid(-1.0F, -6.0F, 7.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(0, 30)
                        .cuboid(-1.0F, -7.0F, 8.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(27, 29).cuboid(-1.0F, -8.0F, 9.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(23, 29)
                        .cuboid(-1.0F, -9.0F, 10.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(29, 17).cuboid(-1.0F, -10.0F, 11.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(29, 15)
                        .cuboid(-1.0F, -11.0F, 12.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(13, 29).cuboid(-1.0F, -12.0F, 13.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(3, 29)
                        .cuboid(-1.0F, -13.0F, 14.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(28, 24).cuboid(-1.0F, -14.0F, 15.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(28, 22)
                        .cuboid(-1.0F, -15.0F, 16.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(28, 20).cuboid(-1.0F, -16.0F, 17.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(18, 22)
                        .cuboid(-1.2F, -17.0F, 18.0F, 1.4F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 26).cuboid(-1.0F, -16.0F, 18.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(26, 18)
                        .cuboid(-1.0F, -19.0F, 19.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(0, 22).cuboid(-1.2F, -16.0F, 19.0F, 1.4F, 1.0F, 1.0F, new Dilation(0.0F)).uv(26, 14)
                        .cuboid(-1.0F, -15.0F, 19.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(15, 21).cuboid(-1.2F, -15.0F, 20.0F, 1.4F, 1.0F, 1.0F, new Dilation(0.0F)).uv(10, 26)
                        .cuboid(-1.0F, -14.0F, 20.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(6, 26).cuboid(-1.0F, -14.0F, 21.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(0, 26)
                        .cuboid(-1.0F, -15.0F, 21.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(25, 25).cuboid(-1.0F, -16.0F, 20.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(5, 21)
                        .cuboid(-1.2F, -20.0F, 15.0F, 1.4F, 1.0F, 1.0F, new Dilation(0.0F)).uv(15, 23).cuboid(-1.0F, -20.0F, 16.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(23, 13)
                        .cuboid(-1.0F, -17.0F, 19.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(25, 23).cuboid(-1.0F, -20.0F, 14.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 20)
                        .cuboid(-1.2F, -21.0F, 14.0F, 1.4F, 1.0F, 1.0F, new Dilation(0.0F)).uv(25, 21).cuboid(-1.0F, -21.0F, 15.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(13, 25)
                        .cuboid(-1.0F, -21.0F, 13.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(25, 11).cuboid(-1.0F, -22.0F, 13.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(25, 9)
                        .cuboid(-1.0F, -22.0F, 14.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(25, 7).cuboid(-1.0F, -21.0F, 19.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 18)
                        .cuboid(-1.2F, -21.0F, 20.0F, 1.4F, 1.0F, 1.0F, new Dilation(0.0F)).uv(25, 5).cuboid(-1.0F, -20.0F, 20.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(25, 3)
                        .cuboid(-1.0F, -22.0F, 20.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(25, 1).cuboid(-1.0F, -23.0F, 21.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 14)
                        .cuboid(-1.2F, -23.0F, 22.0F, 1.4F, 1.0F, 1.0F, new Dilation(0.0F)).uv(22, 24).cuboid(-1.0F, -22.0F, 22.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(18, 24)
                        .cuboid(-1.0F, -23.0F, 23.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 12).cuboid(-1.2F, -24.0F, 23.0F, 1.4F, 1.0F, 1.0F, new Dilation(0.0F)).uv(10, 24)
                        .cuboid(-1.0F, -24.0F, 22.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(6, 24).cuboid(-1.0F, -24.0F, 24.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(0, 24)
                        .cuboid(-1.0F, -25.0F, 24.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(23, 19).cuboid(-1.0F, -25.0F, 23.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(23, 17)
                        .cuboid(-1.0F, -25.0F, 22.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(23, 15).cuboid(-1.0F, -23.0F, 24.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 16)
                        .cuboid(-1.2F, -22.0F, 21.0F, 1.4F, 1.0F, 1.0F, new Dilation(0.0F)).uv(3, 25).cuboid(-1.0F, -21.0F, 21.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(26, 16)
                        .cuboid(-1.0F, -19.0F, 15.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(16, 26).cuboid(-1.0F, -20.0F, 18.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 16)
                        .cuboid(-1.2F, -20.0F, 19.0F, 1.4F, 1.0F, 1.0F, new Dilation(0.0F)).uv(12, 22).cuboid(-1.2F, -19.0F, 16.0F, 1.4F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 28)
                        .cuboid(-1.0F, -18.0F, 15.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(16, 28).cuboid(-1.0F, -17.0F, 14.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(28, 12)
                        .cuboid(-1.0F, -16.0F, 13.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(28, 10).cuboid(-1.0F, -15.0F, 12.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(10, 28)
                        .cuboid(-1.0F, -14.0F, 11.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(28, 8).cuboid(-1.0F, -13.0F, 10.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(28, 6)
                        .cuboid(-1.0F, -12.0F, 9.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(6, 28).cuboid(-1.0F, -11.0F, 8.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(28, 4)
                        .cuboid(-1.0F, -10.0F, 7.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(28, 2).cuboid(-1.0F, -9.0F, 6.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(28, 0)
                        .cuboid(-1.0F, -8.0F, 5.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(0, 28).cuboid(-1.0F, -7.0F, 4.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(27, 27)
                        .cuboid(-1.0F, -6.0F, 3.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(23, 27).cuboid(-1.0F, -5.0F, 2.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(13, 27)
                        .cuboid(-1.0F, -4.0F, 1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(3, 27).cuboid(-1.0F, -3.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 4)
                        .cuboid(-1.3F, -3.0F, 3.0F, 1.6F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 2).cuboid(-1.3F, -4.0F, 3.0F, 1.6F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 0)
                        .cuboid(-1.3F, -4.0F, 2.0F, 1.6F, 1.0F, 1.0F, new Dilation(0.0F)).uv(0, 20).cuboid(-1.3F, -4.0F, 4.0F, 1.6F, 1.0F, 1.0F, new Dilation(0.0F)).uv(15, 19)
                        .cuboid(-1.3F, -5.0F, 4.0F, 1.6F, 1.0F, 1.0F, new Dilation(0.0F)).uv(5, 19).cuboid(-1.3F, -5.0F, 3.0F, 1.6F, 1.0F, 1.0F, new Dilation(0.0F)).uv(10, 18)
                        .cuboid(-1.3F, -5.0F, 5.0F, 1.6F, 1.0F, 1.0F, new Dilation(0.0F)).uv(0, 18).cuboid(-1.3F, -6.0F, 5.0F, 1.6F, 1.0F, 1.0F, new Dilation(0.0F)).uv(15, 17)
                        .cuboid(-1.3F, -6.0F, 4.0F, 1.6F, 1.0F, 1.0F, new Dilation(0.0F)).uv(5, 17).cuboid(-1.3F, -6.0F, 6.0F, 1.6F, 1.0F, 1.0F, new Dilation(0.0F)).uv(10, 16)
                        .cuboid(-1.3F, -7.0F, 6.0F, 1.6F, 1.0F, 1.0F, new Dilation(0.0F)).uv(0, 16).cuboid(-1.3F, -7.0F, 5.0F, 1.6F, 1.0F, 1.0F, new Dilation(0.0F)).uv(15, 15)
                        .cuboid(-1.3F, -7.0F, 7.0F, 1.6F, 1.0F, 1.0F, new Dilation(0.0F)).uv(15, 13).cuboid(-1.3F, -8.0F, 7.0F, 1.6F, 1.0F, 1.0F, new Dilation(0.0F)).uv(15, 11)
                        .cuboid(-1.3F, -8.0F, 6.0F, 1.6F, 1.0F, 1.0F, new Dilation(0.0F)).uv(15, 9).cuboid(-1.3F, -8.0F, 8.0F, 1.6F, 1.0F, 1.0F, new Dilation(0.0F)).uv(15, 7)
                        .cuboid(-1.3F, -9.0F, 8.0F, 1.6F, 1.0F, 1.0F, new Dilation(0.0F)).uv(15, 5).cuboid(-1.3F, -9.0F, 7.0F, 1.6F, 1.0F, 1.0F, new Dilation(0.0F)).uv(5, 15)
                        .cuboid(-1.3F, -9.0F, 9.0F, 1.6F, 1.0F, 1.0F, new Dilation(0.0F)).uv(15, 1).cuboid(-1.3F, -10.0F, 8.0F, 1.6F, 1.0F, 1.0F, new Dilation(0.0F)).uv(10, 14)
                        .cuboid(-1.3F, -10.0F, 10.0F, 1.6F, 1.0F, 1.0F, new Dilation(0.0F)).uv(0, 14).cuboid(-1.3F, -11.0F, 9.0F, 1.6F, 1.0F, 1.0F, new Dilation(0.0F)).uv(5, 13)
                        .cuboid(-1.3F, -11.0F, 10.0F, 1.6F, 1.0F, 1.0F, new Dilation(0.0F)).uv(10, 12).cuboid(-1.3F, -11.0F, 11.0F, 1.6F, 1.0F, 1.0F, new Dilation(0.0F)).uv(0, 12)
                        .cuboid(-1.3F, -12.0F, 11.0F, 1.6F, 1.0F, 1.0F, new Dilation(0.0F)).uv(5, 11).cuboid(-1.3F, -12.0F, 10.0F, 1.6F, 1.0F, 1.0F, new Dilation(0.0F)).uv(10, 10)
                        .cuboid(-1.3F, -12.0F, 12.0F, 1.6F, 1.0F, 1.0F, new Dilation(0.0F)).uv(10, 8).cuboid(-1.3F, -13.0F, 12.0F, 1.6F, 1.0F, 1.0F, new Dilation(0.0F)).uv(10, 6)
                        .cuboid(-1.3F, -13.0F, 11.0F, 1.6F, 1.0F, 1.0F, new Dilation(0.0F)).uv(10, 4).cuboid(-1.3F, -13.0F, 13.0F, 1.6F, 1.0F, 1.0F, new Dilation(0.0F)).uv(10, 2)
                        .cuboid(-1.3F, -14.0F, 13.0F, 1.6F, 1.0F, 1.0F, new Dilation(0.0F)).uv(10, 0).cuboid(-1.3F, -14.0F, 12.0F, 1.6F, 1.0F, 1.0F, new Dilation(0.0F)).uv(0, 10)
                        .cuboid(-1.3F, -14.0F, 14.0F, 1.6F, 1.0F, 1.0F, new Dilation(0.0F)).uv(5, 9).cuboid(-1.3F, -15.0F, 14.0F, 1.6F, 1.0F, 1.0F, new Dilation(0.0F)).uv(0, 8)
                        .cuboid(-1.3F, -15.0F, 13.0F, 1.6F, 1.0F, 1.0F, new Dilation(0.0F)).uv(5, 7).cuboid(-1.3F, -15.0F, 15.0F, 1.6F, 1.0F, 1.0F, new Dilation(0.0F)).uv(0, 6)
                        .cuboid(-1.3F, -16.0F, 15.0F, 1.6F, 1.0F, 1.0F, new Dilation(0.0F)).uv(5, 5).cuboid(-1.3F, -16.0F, 14.0F, 1.6F, 1.0F, 1.0F, new Dilation(0.0F)).uv(5, 3)
                        .cuboid(-1.3F, -16.0F, 16.0F, 1.6F, 1.0F, 1.0F, new Dilation(0.0F)).uv(5, 1).cuboid(-1.3F, -17.0F, 16.0F, 1.6F, 1.0F, 1.0F, new Dilation(0.0F)).uv(0, 4)
                        .cuboid(-1.3F, -17.0F, 15.0F, 1.6F, 1.0F, 1.0F, new Dilation(0.0F)).uv(0, 2).cuboid(-1.3F, -17.0F, 17.0F, 1.6F, 1.0F, 1.0F, new Dilation(0.0F)).uv(3, 23)
                        .cuboid(-1.2F, -18.0F, 17.0F, 1.4F, 1.0F, 1.0F, new Dilation(0.0F)).uv(0, 0).cuboid(-1.3F, -18.0F, 16.0F, 1.6F, 1.0F, 1.0F, new Dilation(0.0F)).uv(31, 1)
                        .cuboid(-1.0F, -18.0F, 18.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)).uv(20, 18).cuboid(-1.2F, -19.0F, 18.0F, 1.4F, 1.0F, 1.0F, new Dilation(0.0F)).uv(30, 30)
                        .cuboid(-1.0F, -19.0F, 17.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)),
                ModelTransform.pivot(8.0F, 24.0F, -8.0F));
        return TexturedModelData.of(meshdefinition, 64, 64);
    }

    @Override
    public void render(MatrixStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.group.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }
}