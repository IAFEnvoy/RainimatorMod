package dev.rainimator.mod.screen.gui;

import dev.architectury.networking.NetworkManager;
import dev.rainimator.mod.ModConstants;
import dev.rainimator.mod.RainimatorMod;
import dev.rainimator.mod.screen.handler.EnderBookSkillScreenHandler;
import io.netty.buffer.Unpooled;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

@Environment(EnvType.CLIENT)
public class EnderBookSkillScreen extends HandledScreen<EnderBookSkillScreenHandler> {
    public EnderBookSkillScreen(EnderBookSkillScreenHandler handler, PlayerInventory inventory, Text text) {
        super(handler, inventory, text);
    }

    @Override
    public boolean keyPressed(int key, int b, int c) {
        if (key == 256) {
            if (this.client != null && this.client.player != null)
                this.client.player.closeHandledScreen();
            return true;
        }
        return super.keyPressed(key, b, c);
    }

    @Override
    public void init() {
        super.init();
        int leftPos = (this.width - 300) / 2;
        int topPos = (this.height - 100) / 2;

        this.addDrawableChild(new TexturedButtonWidget(leftPos + 32, topPos + 19, 64, 64, 0, 0, 64, new Identifier(RainimatorMod.MOD_ID, "textures/screens/atlas/overworld.png"), 64, 128, e -> NetworkManager.sendToServer(ModConstants.ENDER_BOOK_SKILL_PACKET_ID, new PacketByteBuf(Unpooled.buffer()).writeString(World.OVERWORLD.getValue().toString()))));
        this.addDrawableChild(new TexturedButtonWidget(leftPos + 118, topPos + 19, 64, 64, 0, 0, 64, new Identifier(RainimatorMod.MOD_ID, "textures/screens/atlas/nether.png"), 64, 128, e -> NetworkManager.sendToServer(ModConstants.ENDER_BOOK_SKILL_PACKET_ID, new PacketByteBuf(Unpooled.buffer()).writeString(World.NETHER.getValue().toString()))));
        this.addDrawableChild(new TexturedButtonWidget(leftPos + 203, topPos + 19, 64, 64, 0, 0, 64, new Identifier(RainimatorMod.MOD_ID, "textures/screens/atlas/end.png"), 64, 128, e -> NetworkManager.sendToServer(ModConstants.ENDER_BOOK_SKILL_PACKET_ID, new PacketByteBuf(Unpooled.buffer()).writeString(World.END.getValue().toString()))));
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
    }
}