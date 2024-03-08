package com.rainimator.rainimatormod.renderer;

import com.rainimator.rainimatormod.RainimatorMod;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;
import org.jetbrains.annotations.NotNull;

public class EntityWithEyeRendererBase<T extends Mob> extends EntityRendererBase<T> {
    public EntityWithEyeRendererBase(EntityRendererProvider.Context context, String textureId, String eyeTextureId) {
        super(context, textureId);
        this.addLayer(new EyesLayer<>(this) {
            public @NotNull RenderType renderType() {
                return RenderType.eyes(new ResourceLocation(RainimatorMod.MOD_ID, "textures/entities/" + eyeTextureId + ".png"));
            }
        });
    }
}
