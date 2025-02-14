package com.iafenvoy.rainimator.renderer.model.wing;

import net.minecraft.client.model.*;
import net.minecraft.entity.LivingEntity;

public class LeatherWingsModel<T extends LivingEntity> extends WingEntityModel<T> {
    private final ModelPart leftWing03;
    private final ModelPart rightWing03;

    public LeatherWingsModel(ModelPart root) {
        super(root);
        ModelPart leftWing01 = root.getChild("leftWing").getChild("leftWing01");
        ModelPart leftWing02 = leftWing01.getChild("leftWing02");
        this.leftWing03 = leftWing02.getChild("leftWing03");
        ModelPart rightWing01 = root.getChild("rightWing").getChild("rightWing01");
        ModelPart rightWing02 = rightWing01.getChild("rightWing02");
        this.rightWing03 = rightWing02.getChild("rightWing03");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = getModelData();
        ModelPartData modelPartData = modelData.getRoot();

        ModelPartData modelPartData1 = modelPartData.getChild("leftWing").addChild("leftWing01", ModelPartBuilder.create().uv(21, 0)
                        .cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 4.0F, 5.0F),
                ModelTransform.of(-6.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.436332F));
        ModelPartData modelPartData2 = modelPartData1.addChild("leftWing02", ModelPartBuilder.create().uv(0, 47)
                        .cuboid(-0.5F, 0.0F, -1.5F, 1.0F, 2.0F, 8.0F),
                ModelTransform.of(-0.5F, 0.0F, 3.5F, 0.6109F, 0.0F, 0.0F));
        ModelPartData modelPartData3 = modelPartData2.addChild("leftWing03", ModelPartBuilder.create().uv(39, 0)
                        .cuboid(-0.5F, -1.1F, -0.5F, 1.0F, 2.0F, 8.0F),
                ModelTransform.of(0.0F, 1.0F, 6.5F, -0.5672F, 0.3054F, 0.0F));
        ModelPartData modelPartData4 = modelPartData3.addChild("leftWing04", ModelPartBuilder.create().uv(0, 0)
                        .cuboid(-0.6F, -0.8F, -4.0F, 1.0F, 1.0F, 19.0F),
                ModelTransform.of(0.0F, 0.0F, 7.5F, -0.7418F, 0.0F, 0.0F));
        modelPartData4.addChild("leftWing04Leather", ModelPartBuilder.create().uv(0, 28)
                        .cuboid(0.0F, 0.0F, 0.0F, 0.0F, 5.0F, 14.0F),
                ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        ModelPartData modelPartData5 = modelPartData3.addChild("leftWingStrut01", ModelPartBuilder.create().uv(21, 0)
                        .cuboid(-0.6F, -0.5F, -0.5F, 1.0F, 1.0F, 16.0F),
                ModelTransform.of(0.0F, 1.0F, 7.0F, -1.0036F, 0.0F, 0.0F));
        modelPartData5.addChild("leftWingStrut01Leather", ModelPartBuilder.create().uv(0, 22)
                        .cuboid(-0.05F, 0.0F, 0.0F, 0.0F, 5.0F, 14.0F),
                ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        ModelPartData modelPartData6 = modelPartData3.addChild("leftWingStrut02", ModelPartBuilder.create().uv(21, 0)
                        .cuboid(-0.6F, -0.5F, -0.5F, 1.0F, 1.0F, 16.0F),
                ModelTransform.of(0.0F, 1.0F, 6.0F, -1.309F, 0.0F, 0.0F));
        modelPartData6.addChild("leftWingStrut02Leather", ModelPartBuilder.create().uv(0, 4)
                        .cuboid(-0.1F, 0.0F, -1.0F, 0.0F, 6.0F, 16.0F),
                ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        ModelPartData modelPartData7 = modelPartData3.addChild("leftWingStrut03", ModelPartBuilder.create().uv(21, 0)
                        .cuboid(-0.6F, -0.5F, -0.5F, 1.0F, 1.0F, 16.0F),
                ModelTransform.of(0.0F, 1.0F, 5.0F, -1.6581F, 0.0F, 0.0F));
        modelPartData7.addChild("leftWingStrut03Leather", ModelPartBuilder.create().uv(0, 12)
                        .cuboid(-0.15F, 0.0F, 0.0F, 0.0F, 6.0F, 15.0F),
                ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        ModelPartData modelPartData8 = modelPartData2.addChild("leftStrutLowest", ModelPartBuilder.create().uv(12, 0)
                        .cuboid(-1.1F, 0.0F, -0.5F, 1.0F, 12.0F, 1.0F),
                ModelTransform.of(0.5F, 1.0F, 2.0F, -0.3491F, 0.0F, 0.0F));
        modelPartData8.addChild("leftStrutLowestLeather01", ModelPartBuilder.create().uv(0, 0)
                        .cuboid(0.0F, -1.0F, 0.0F, 0.0F, 12.0F, 6.0F),
                ModelTransform.pivot(-0.5F, 0.0F, 0.0F));
        modelPartData8.addChild("leftStrutLowestLeather02", ModelPartBuilder.create().uv(28, 28)
                        .cuboid(0.0F, -1.0F, -9.0F, 0.0F, 12.0F, 9.0F),
                ModelTransform.of(-0.5F, 0.0F, 0.0F, 0.0F, -0.2618F, 0.0F));
        ModelPartData modelPartData9 = modelPartData.getChild("rightWing").addChild("rightWing01", ModelPartBuilder.create().uv(21, 0)
                        .cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 4.0F, 5.0F, true),
                ModelTransform.of(6.0F, 0.0F, 0.0F, 0.0F, -1.5708F, -0.436332F));
        ModelPartData modelPartData10 = modelPartData9.addChild("rightWing02", ModelPartBuilder.create().uv(0, 47)
                        .cuboid(-0.5F, 0.0F, -1.5F, 1.0F, 2.0F, 8.0F, true),
                ModelTransform.of(0.5F, 0.0F, 3.5F, 0.6109F, 0.0F, 0.0F));
        ModelPartData modelPartData11 = modelPartData10.addChild("rightWing03", ModelPartBuilder.create().uv(39, 0)
                        .cuboid(-0.5F, -1.1F, -0.5F, 1.0F, 2.0F, 8.0F, true),
                ModelTransform.of(0.0F, 1.0F, 6.5F, -0.5672F, -0.3054F, 0.0F));
        ModelPartData modelPartData12 = modelPartData11.addChild("rightWing04", ModelPartBuilder.create().uv(0, 0)
                        .cuboid(-0.4F, -0.8F, -4.0F, 1.0F, 1.0F, 19.0F, true),
                ModelTransform.of(0.0F, 0.0F, 7.5F, -0.7418F, 0.0F, 0.0F));
        modelPartData12.addChild("rightWing04Leather", ModelPartBuilder.create().uv(0, 28)
                        .cuboid(0.0F, 0.0F, 0.0F, 0.0F, 5.0F, 14.0F, true),
                ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        ModelPartData modelPartData13 = modelPartData11.addChild("rightWingStrut01", ModelPartBuilder.create().uv(21, 0)
                        .cuboid(-0.4F, -0.5F, -0.5F, 1.0F, 1.0F, 16.0F, true),
                ModelTransform.of(0.0F, 1.0F, 7.0F, -1.0036F, 0.0F, 0.0F));
        modelPartData13.addChild("rightWingStrut01Leather", ModelPartBuilder.create().uv(0, 22)
                        .cuboid(0.05F, 0.0F, 0.0F, 0.0F, 5.0F, 14.0F, true),
                ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        ModelPartData modelPartData14 = modelPartData11.addChild("rightWingStrut02", ModelPartBuilder.create().uv(21, 0)
                        .cuboid(-0.4F, -0.5F, -0.5F, 1.0F, 1.0F, 16.0F, true),
                ModelTransform.of(0.0F, 1.0F, 6.0F, -1.309F, 0.0F, 0.0F));
        modelPartData14.addChild("rightWingStrut02Leather", ModelPartBuilder.create().uv(0, 4)
                        .cuboid(0.1F, 0.0F, -1.0F, 0.0F, 6.0F, 16.0F, true),
                ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        ModelPartData modelPartData15 = modelPartData11.addChild("rightWingStrut03", ModelPartBuilder.create().uv(21, 0)
                        .cuboid(-0.4F, -0.5F, -0.5F, 1.0F, 1.0F, 16.0F, true),
                ModelTransform.of(0.0F, 1.0F, 5.0F, -1.6581F, 0.0F, 0.0F));
        modelPartData15.addChild("rightWingStrut03Leather", ModelPartBuilder.create().uv(0, 12)
                        .cuboid(0.15F, 0.0F, 0.0F, 0.0F, 6.0F, 15.0F, true),
                ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        ModelPartData modelPartData16 = modelPartData10.addChild("rightStrutLowest", ModelPartBuilder.create().uv(12, 0)
                        .cuboid(0.1F, 0.0F, -0.5F, 1.0F, 12.0F, 1.0F, true),
                ModelTransform.of(-0.5F, 1.0F, 2.0F, -0.3491F, 0.0F, 0.0F));
        modelPartData16.addChild("rightStrutLowestLeather01", ModelPartBuilder.create().uv(0, 0)
                        .cuboid(0.0F, -1.0F, 0.0F, 0.0F, 12.0F, 6.0F, true),
                ModelTransform.pivot(0.5F, 0.0F, 0.0F));
        modelPartData16.addChild("rightStrutLowestLeather02", ModelPartBuilder.create().uv(28, 28)
                        .cuboid(0.0F, -1.0F, -9.0F, 0.0F, 12.0F, 9.0F, true),
                ModelTransform.of(0.5F, 0.0F, 0.0F, 0.0F, 0.2618F, 0.0F));

        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        super.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
        if (this.state == State.IDLE || this.state == State.CROUCHING)
            this.leftWing03.pitch = (float) Math.toRadians(-60);
        if (this.state == State.FLYING)
            this.leftWing03.pitch = (float) Math.toRadians(-32.5);
        this.rightWing03.pitch = this.leftWing03.pitch;
    }
}
