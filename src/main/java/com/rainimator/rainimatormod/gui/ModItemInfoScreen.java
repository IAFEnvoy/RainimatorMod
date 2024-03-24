package com.rainimator.rainimatormod.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import com.rainimator.rainimatormod.RainimatorMod;
import com.rainimator.rainimatormod.util.ComponentUtil;
import com.rainimator.rainimatormod.util.Episode;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ModItemInfoScreen extends Screen {
    private final ItemInfo info;
    private final Screen parent;
    private int leftPos;
    private int topPos;

    public ModItemInfoScreen(Component text, ItemInfo info, Screen parent) {
        super(text);
        this.info = info;
        this.parent = parent;
    }

    @Override
    public void render(@NotNull PoseStack ms, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(ms);
        super.render(ms, mouseX, mouseY, partialTicks);
        Minecraft.getInstance().font.drawShadow(ms, I18n.get(this.info.type.getId() + "." + RainimatorMod.MOD_ID + "." + this.info.internalId), this.leftPos - 20, this.topPos - 30, -1);
        Minecraft.getInstance().font.drawShadow(ms, I18n.get("gui.rainimator.item_info.appear") + this.info.episode.getName(), this.leftPos - 20, this.topPos - 20, -1);
        String[] details = I18n.get("gui." + RainimatorMod.MOD_ID + ".detail." + this.info.internalId).split("\n");
        int i = -40;
        for (String s : details) {
            List<TextComponent> componentList = ComponentUtil.splitText(new TextComponent(s), 180, Minecraft.getInstance().font);
            for (TextComponent component : componentList) {
                Minecraft.getInstance().font.drawShadow(ms, component, this.leftPos + 190, this.topPos + i, -1);
                i += 10;
            }
            i += 5;
        }
        Minecraft.getInstance().font.drawShadow(ms,
                new TextComponent("Image By Rainimator @Rainolaguer").withStyle(Style.EMPTY.withBold(true).withUnderlined(true).withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.youtube.com/@Rainimator"))),
                this.leftPos - 20, this.topPos + 125, -1);
    }

    @Override
    public boolean keyPressed(int key, int b, int c) {
        if (key == 256) {
            this.onClose();
            return true;
        }
        return super.keyPressed(key, b, c);
    }

    @Override
    public void onClose() {
        Minecraft.getInstance().keyboardHandler.setSendRepeatsToGui(false);
        if (this.parent == null) super.onClose();
        else Minecraft.getInstance().setScreen(this.parent);
    }

    @Override
    public void init() {
        super.init();
        this.leftPos = (this.width - 300) / 2;
        this.topPos = (this.height - 100) / 2;
        Minecraft.getInstance().keyboardHandler.setSendRepeatsToGui(true);
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