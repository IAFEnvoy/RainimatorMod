package com.rainimator.rainimatormod.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.rainimator.rainimatormod.RainimatorMod;
import com.rainimator.rainimatormod.network.EnderBookSkillButtonMessage;
import com.rainimator.rainimatormod.network.NetworkManager;
import com.rainimator.rainimatormod.world.inventory.EnderBookSkillMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class EnderBookSkillScreen extends AbstractContainerScreen<EnderBookSkillMenu> {
    private static final HashMap<String, Object> guiState = EnderBookSkillMenu.guiState;

    private final int x;
    private final int y;
    private final int z;
    private final Player entity;
    ImageButton image_button_ground;
    ImageButton image_button_nether;
    ImageButton image_button_ender;
    public EnderBookSkillScreen(EnderBookSkillMenu container, Inventory inventory, Component text) {
        super(container, inventory, text);
        this.x = container.x;
        this.y = container.y;
        this.z = container.z;
        this.entity = container.entity;
        this.imageWidth = 300;
        this.imageHeight = 100;
    }

    public boolean isPauseScreen() {
        return true;
    }

    @Override
    public void render(@NotNull PoseStack ms, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(ms);
        super.render(ms, mouseX, mouseY, partialTicks);
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
    public void containerTick() {
        super.containerTick();
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
        this.image_button_ground = new ImageButton(this.leftPos + 32, this.topPos + 19, 64, 64, 0, 0, 64, new ResourceLocation(RainimatorMod.MOD_ID, "textures/screens/atlas/imagebutton_ground.png"), 64, 128, e -> {
            NetworkManager.PACKET_HANDLER.sendToServer(new EnderBookSkillButtonMessage(0, this.x, this.y, this.z));

            EnderBookSkillButtonMessage.handleButtonAction(this.entity, 0, this.x, this.y, this.z);
        });

        guiState.put("button:imagebutton_ground", this.image_button_ground);
        this.addRenderableWidget(this.image_button_ground);
        this.image_button_nether = new ImageButton(this.leftPos + 118, this.topPos + 19, 64, 64, 0, 0, 64, new ResourceLocation(RainimatorMod.MOD_ID, "textures/screens/atlas/imagebutton_nether.png"), 64, 128, e -> {
            NetworkManager.PACKET_HANDLER.sendToServer(new EnderBookSkillButtonMessage(1, this.x, this.y, this.z));

            EnderBookSkillButtonMessage.handleButtonAction(this.entity, 1, this.x, this.y, this.z);
        });

        guiState.put("button:imagebutton_nether", this.image_button_nether);
        this.addRenderableWidget(this.image_button_nether);
        this.image_button_ender = new ImageButton(this.leftPos + 203, this.topPos + 19, 64, 64, 0, 0, 64, new ResourceLocation(RainimatorMod.MOD_ID, "textures/screens/atlas/imagebutton_ender.png"), 64, 128, e -> {
            NetworkManager.PACKET_HANDLER.sendToServer(new EnderBookSkillButtonMessage(2, this.x, this.y, this.z));

            EnderBookSkillButtonMessage.handleButtonAction(this.entity, 2, this.x, this.y, this.z);
        });

        guiState.put("button:imagebutton_ender", this.image_button_ender);
        this.addRenderableWidget(this.image_button_ender);
    }
}