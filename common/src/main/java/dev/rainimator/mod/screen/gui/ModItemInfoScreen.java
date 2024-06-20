package dev.rainimator.mod.screen.gui;

import dev.rainimator.mod.RainimatorMod;
import dev.rainimator.mod.util.ComponentUtil;
import dev.rainimator.mod.util.Episode;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

@Environment(EnvType.CLIENT)
public class ModItemInfoScreen extends Screen {
    private final ItemInfo info;
    private final Screen parent;
    private int leftPos;
    private int topPos;

    public ModItemInfoScreen(Text text, ItemInfo info, Screen parent) {
        super(text);
        this.info = info;
        this.parent = parent;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        this.textRenderer.draw(I18n.translate(this.info.type.getId() + "." + RainimatorMod.MOD_ID + "." + this.info.internalId), this.leftPos - 20, this.topPos - 30, -1, false, context.getMatrices().peek().getPositionMatrix(), context.getVertexConsumers(), TextRenderer.TextLayerType.NORMAL, -1, 0);
        this.textRenderer.draw(I18n.translate("gui.rainimator.item_info.appear") + this.info.episode.getName(), this.leftPos - 20, this.topPos - 20, -1, false, context.getMatrices().peek().getPositionMatrix(), context.getVertexConsumers(), TextRenderer.TextLayerType.NORMAL, -1, 0);
        String[] details = I18n.translate("gui." + RainimatorMod.MOD_ID + ".detail." + this.info.internalId).split("\n");
        int i = -40;
        for (String s : details) {
            List<MutableText> componentList = ComponentUtil.splitText(Text.literal(s), 180, this.textRenderer);
            for (MutableText component : componentList) {
                this.textRenderer.draw(component, this.leftPos + 190, this.topPos + i, -1, false, context.getMatrices().peek().getPositionMatrix(), context.getVertexConsumers(), TextRenderer.TextLayerType.NORMAL, -1, 0);
                i += 10;
            }
            i += 5;
        }
        this.textRenderer.draw(Text.literal("Image By Rainimator @Rainolaguer").fillStyle(Style.EMPTY.withBold(true).withUnderline(true).withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.youtube.com/@Rainimator"))),
                this.leftPos - 20, this.topPos + 125, -1, false, context.getMatrices().peek().getPositionMatrix(), context.getVertexConsumers(), TextRenderer.TextLayerType.NORMAL, -1, 0);
    }

    @Override
    public boolean keyPressed(int key, int b, int c) {
        if (key == 256) {
            this.close();
            return true;
        }
        return super.keyPressed(key, b, c);
    }

    @Override
    public void close() {
        if (this.parent == null) super.close();
        else if (this.client != null)
            this.client.setScreen(this.parent);
    }

    @Override
    public void init() {
        super.init();
        this.leftPos = (this.width - 300) / 2;
        this.topPos = (this.height - 100) / 2;
        if (this.info == null) return;

        this.addDrawable(new TexturedButtonWidget(this.leftPos - 20, this.topPos - 10,//Location
                200, 130, //Size
                0, 0, //Texture Start
                0,//DiffTex
                new Identifier(RainimatorMod.MOD_ID, "textures/item_info/" + this.info.itemId + ".jpg"),
                200, 130,//Texture size
                e -> {//Do nothing
                }));
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

    public record ItemInfo(String internalId, String itemId, InfoType type, Episode episode) {
    }
}