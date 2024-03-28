package com.rainimator.rainimatormod.util;

import com.rainimator.rainimatormod.RainimatorMod;
import com.rainimator.rainimatormod.renderer.EntityRendererBase;
import com.rainimator.rainimatormod.renderer.EntityWithEyeRendererBase;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;

import java.util.HashMap;

public enum Stage {
    First, Second;

    public interface StagedEntity {
        Stage getStage();
    }

    public static class StagedEntityTextureProvider {
        private final HashMap<Stage, String> textureIds = new HashMap<>();
        private String eyeTextureId = null;

        public StagedEntityTextureProvider(String... textures) {
            Stage[] stages = Stage.values();
            for (int i = 0; i < stages.length && i < textures.length; i++)
                this.textureIds.put(stages[i], textures[i]);
        }

        public StagedEntityTextureProvider setEyeTextureId(String eyeTextureId) {
            this.eyeTextureId = eyeTextureId;
            return this;
        }

        public ResourceLocation getTexture() {
            return this.getTexture(Stage.First);
        }

        public ResourceLocation getTexture(Stage stage) {
            return new ResourceLocation(RainimatorMod.MOD_ID, "textures/entities/" + this.getTextureId(stage) + ".png");
        }

        private String getTextureId(Stage stage) {
            if (this.textureIds.containsKey(stage))
                return this.textureIds.get(stage);
            if (this.textureIds.size() == 0)
                return "";
            return this.textureIds.get(Stage.First);
        }

        public <T extends Mob> EntityRendererBase<T> createRenderer(EntityRendererProvider.Context context) {
            if (this.eyeTextureId == null)
                return new EntityRendererBase<>(context, this);
            return new EntityWithEyeRendererBase<>(context, this, this.eyeTextureId);
        }
    }

    public static StagedEntityTextureProvider ofProvider(String... textures) {
        return new StagedEntityTextureProvider(textures);
    }
}
