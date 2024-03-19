package com.rainimator.rainimatormod.renderer;

import com.rainimator.rainimatormod.util.Stage;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import org.jetbrains.annotations.NotNull;

public class EntityRendererBase<T extends Mob> extends HumanoidMobRenderer<T, HumanoidModel<T>> {
    private final Stage.StagedEntityTextureProvider textureId;

    public EntityRendererBase(EntityRendererProvider.Context context, Stage.StagedEntityTextureProvider textureId) {
        super(context, new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER)), 0.5F);
        this.textureId = textureId;
        this.addLayer(new HumanoidArmorLayer<>(this, new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)), new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR))));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull T entity) {
        if (entity instanceof Stage.StagedEntity stagedEntity)
            return this.textureId.getTexture(stagedEntity.getStage());
        return this.textureId.getTexture();
    }
}
