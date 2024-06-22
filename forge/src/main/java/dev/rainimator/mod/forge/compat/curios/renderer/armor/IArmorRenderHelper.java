package dev.rainimator.mod.forge.compat.curios.renderer.armor;

import net.minecraft.client.model.Model;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;


// From net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer
public interface IArmorRenderHelper {
    /**
     * Helper method for rendering a specific armor model, comes after setting visibility.
     *
     * <p>This primarily handles applying glint and the correct {@link RenderLayer}
     *
     * @param matrices        the matrix stack
     * @param vertexConsumers the vertex consumer provider
     * @param light           packed lightmap coordinates
     * @param stack           the item stack of the armor item
     * @param model           the model to be rendered
     * @param texture         the texture to be applied
     */
    static void renderPart(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, ItemStack stack, Model model, Identifier texture) {
        VertexConsumer vertexConsumer = ItemRenderer.getArmorGlintConsumer(vertexConsumers, RenderLayer.getArmorCutoutNoCull(texture), false, stack.hasGlint());
        model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1, 1, 1, 1);
    }
}
