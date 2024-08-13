package dev.rainimator.mod.registry;

import dev.architectury.event.events.client.ClientTickEvent;
import dev.architectury.networking.NetworkManager;
import dev.architectury.registry.client.keymappings.KeyMappingRegistry;
import dev.rainimator.mod.ModConstants;
import dev.rainimator.mod.RainimatorMod;
import io.netty.buffer.Unpooled;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.network.PacketByteBuf;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class RainimatorKeyBindings {
    public static final KeyBinding FRACTION_ABILITY = new KeyBinding("key." + RainimatorMod.MOD_ID + ".fraction_ability", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_X, "category." + RainimatorMod.MOD_ID);

    public static void register() {
        KeyMappingRegistry.register(FRACTION_ABILITY);
        ClientTickEvent.CLIENT_POST.register(client -> {
            if (FRACTION_ABILITY.wasPressed())
                NetworkManager.sendToServer(ModConstants.FRACTION_ABILITY_PACKET_ID, new PacketByteBuf(Unpooled.buffer()));
        });
    }
}
