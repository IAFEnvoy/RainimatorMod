package dev.rainimator.mod.renderer.model.wing;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.entity.LivingEntity;

@Environment(EnvType.CLIENT)
public class WingsOfSalvationModel<T extends LivingEntity> extends WingEntityModel<T> {
    public final ModelPart leftWing;
    public final ModelPart rightWing;

    public WingsOfSalvationModel(ModelPart root) {
        super(root);
        this.leftWing = root.getChild("leftWing");
        this.rightWing = root.getChild("rightWing");
    }

    public static TexturedModelData createLayer() {
        ModelData meshdefinition = new ModelData();
        ModelPartData partdefinition = meshdefinition.getRoot();
        Dilation cubedeformation = new Dilation(1.0F);
        partdefinition.addChild("leftWing",
                ModelPartBuilder.create().uv(0, 0).cuboid(0F, 0.0F, -30F, 1.0F, 16.0F, 32.0F, cubedeformation),
                ModelTransform.of(-16F, 19F, 0F, -0.1F, 1.57F, -0.75F));
        partdefinition.addChild("rightWing",
                ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-0.75F, 0.0F, -30.75F, 1.0F, 16.0F, 32.0F, cubedeformation),
                ModelTransform.of(16F, 19F, 0F, -0.1F, -1.57F, 0.75F));
        return TexturedModelData.of(meshdefinition, 64, 16);
    }
}
