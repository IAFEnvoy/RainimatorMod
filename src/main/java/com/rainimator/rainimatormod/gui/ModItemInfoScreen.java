package com.rainimator.rainimatormod.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.rainimator.rainimatormod.RainimatorMod;
import com.rainimator.rainimatormod.gui.inventory.ModItemInfoMenu;
import com.rainimator.rainimatormod.util.ComponentUtil;
import com.rainimator.rainimatormod.util.Episode;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ModItemInfoScreen extends AbstractContainerScreen<ModItemInfoMenu> implements MenuAccess<ModItemInfoMenu> {
    private final ItemInfo info;

    public ModItemInfoScreen(ModItemInfoMenu container, Inventory inventory, Component text, ItemInfo info) {
        super(container, inventory, text);
        this.imageWidth = 300;
        this.imageHeight = 100;
        this.info = info;
    }

    @Override
    public void render(@NotNull PoseStack ms, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(ms);
        super.render(ms, mouseX, mouseY, partialTicks);
        Minecraft.getInstance().font.drawShadow(ms, I18n.get(this.info.type.getId() + ".rainimator." + this.info.internalId), this.leftPos - 20, this.topPos - 30, -1);
        Minecraft.getInstance().font.drawShadow(ms, I18n.get("gui.rainimator.item_info.appear") + this.info.episode.getName(), this.leftPos - 20, this.topPos - 20, -1);
        String[] details = I18n.get("gui.rainimator.detail." + this.info.internalId).split("\n");
        int i = 0;
        for (String s : details) {
            List<TextComponent> componentList = ComponentUtil.splitText(new TextComponent(s), 180, Minecraft.getInstance().font);
            for (TextComponent component : componentList) {
                Minecraft.getInstance().font.drawShadow(ms, component, this.leftPos + 190, this.topPos - 40 + i * 10, -1);
                i++;
            }
        }
        this.renderTooltip(ms, mouseX, mouseY);
    }

    @Override
    protected void renderBg(@NotNull PoseStack ms, float partialTicks, int gx, int gy) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableBlend();
    }

    @Override
    public boolean keyPressed(int key, int b, int c) {
        if (key == 256) {
            if (this.minecraft != null && this.minecraft.player != null)
                this.minecraft.player.closeContainer();
            return true;
        }
        return super.keyPressed(key, b, c);
    }

    @Override
    protected void renderLabels(@NotNull PoseStack poseStack, int mouseX, int mouseY) {
    }

    @Override
    public void onClose() {
        super.onClose();
        Minecraft.getInstance().keyboardHandler.setSendRepeatsToGui(false);
    }

    @Override
    public void init() {
        super.init();
        if (this.minecraft != null)
            this.minecraft.keyboardHandler.setSendRepeatsToGui(true);
        if (this.info == null) return;

        this.addRenderableOnly(new ImageButton(this.leftPos - 20, this.topPos - 10,//Location
                200, 130, //Size
                0, 0, //Texture Start
                0,//DiffTex
                new ResourceLocation(RainimatorMod.MOD_ID, "textures/item_info/" + this.info.itemId + ".jpg"),
                200, 130,//Texture size
                e -> {//Do nothing
                }));
    }

    public record ItemInfo(String internalId, String itemId, InfoType type, Episode episode) {
    }

    public enum InfoType {
        Entity("entity"), Item("item");
        private final String id;

        InfoType(String id) {
            this.id = id;
        }

        public String getId() {
            return this.id;
        }
    }
}