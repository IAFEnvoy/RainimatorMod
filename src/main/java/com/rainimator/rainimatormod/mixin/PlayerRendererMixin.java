package com.rainimator.rainimatormod.mixin;

import com.rainimator.rainimatormod.renderer.WingsOfSalvationLayer;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@OnlyIn(Dist.CLIENT)
@Mixin(PlayerRenderer.class)
public abstract class PlayerRendererMixin extends LivingEntityRenderer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
    public PlayerRendererMixin(EntityRendererProvider.Context rainimator$context, boolean p_174558_) {
        super(rainimator$context, new PlayerModel<>(rainimator$context.bakeLayer(p_174558_ ? ModelLayers.PLAYER_SLIM : ModelLayers.PLAYER), p_174558_), 0.5F);
    }

    @Inject(method = "<init>", at = @At("TAIL"), locals = LocalCapture.CAPTURE_FAILHARD)
    public void postConstructor(EntityRendererProvider.Context p_174557_, boolean p_174558_, CallbackInfo ci) {
        this.addLayer(new WingsOfSalvationLayer<>(this, p_174557_.getModelSet()));
    }
}
