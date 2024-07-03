package dev.rainimator.mod.mixin;

import dev.rainimator.mod.renderer.WingsOfSalvationRenderer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntityRenderer.class)
public abstract class PlayerEntityRendererMixin<T extends LivingEntity, M extends EntityModel<T>> extends LivingEntityRenderer<T, M> {
    public PlayerEntityRendererMixin(EntityRenderDispatcher dispatcher, M model, float shadowRadius) {
        super(dispatcher, model, shadowRadius);
    }

    @Inject(method = "<init>(Lnet/minecraft/client/render/entity/EntityRenderDispatcher;Z)V", at = @At("RETURN"))
    private void addRenderers(EntityRenderDispatcher dispatcher, boolean slim, CallbackInfo ci) {
        this.addFeature(new WingsOfSalvationRenderer<>(this, this.model));
    }
}
