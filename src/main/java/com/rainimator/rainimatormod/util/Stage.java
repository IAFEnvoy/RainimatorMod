package com.rainimator.rainimatormod.util;

import com.rainimator.rainimatormod.RainimatorMod;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;

public enum Stage {
    First, Second;

    public interface StagedEntity {
        Stage getStage();
    }

    public static class StagedEntityTextureProvider {
        private final HashMap<Stage, String> textureIds = new HashMap<>();

        public StagedEntityTextureProvider(String... textures) {
            Stage[] stages = Stage.values();
            for (int i = 0; i < stages.length && i < textures.length; i++)
                this.textureIds.put(stages[i], textures[i]);
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
    }

    public static StagedEntityTextureProvider ofProvider(String... textures) {
        return new StagedEntityTextureProvider(textures);
    }
}
