package dev.rainimator.mod.network;

import com.iafenvoy.neptune.util.Timeout;
import dev.architectury.networking.NetworkManager;
import dev.architectury.utils.Env;
import dev.rainimator.mod.ModConstants;
import dev.rainimator.mod.data.component.FractionData;
import dev.rainimator.mod.impl.ComponentManager;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ServerNetworkHelper {
    public static void register() {
        NetworkManager.registerReceiver(NetworkManager.Side.C2S, ModConstants.ENDER_BOOK_SKILL_PACKET_ID, (buf, context) -> {
            if (context.getEnvironment() == Env.CLIENT) return;
            ServerPlayerEntity player = (ServerPlayerEntity) context.getPlayer();
            String target = buf.readString(100);
            Identifier identifier = Identifier.tryParse(target);
            assert identifier != null;
            RegistryKey<World> dimension;
            if (identifier.equals(World.OVERWORLD.getValue())) dimension = World.OVERWORLD;
            else if (identifier.equals(World.NETHER.getValue())) dimension = World.NETHER;
            else if (identifier.equals(World.END.getValue())) dimension = World.END;
            else throw new UnsupportedOperationException();
            if (player.getWorld().getRegistryKey() == dimension)
                player.sendMessage(Text.translatable("item.rainimator.ender_book.error"), false);
            else {
                ServerWorld nextLevel = player.server.getWorld(dimension);
                if (nextLevel != null) {
                    player.teleport(nextLevel, player.getX(), player.getY(), player.getZ(), player.getYaw(), player.getPitch());
                    BlockPos blockBelow = new BlockPos((int) player.getX(), (int) (player.getY() - 1), (int) player.getZ());
                    if (nextLevel.getBlockState(blockBelow).getBlock() == Blocks.AIR) {
                        nextLevel.setBlockState(blockBelow, Blocks.DIRT.getDefaultState(), 3);
                        Timeout.create(200, () -> nextLevel.setBlockState(blockBelow, Blocks.AIR.getDefaultState(), 3));
                    }
                    player.sendMessage(Text.translatable("item.rainimator.ender_book.success"), false);
                }
            }
        });
        NetworkManager.registerReceiver(NetworkManager.Side.C2S, ModConstants.FRACTION_ABILITY_PACKET_ID, (buf, context) -> {
            PlayerEntity player = context.getPlayer();
            FractionData data = ComponentManager.getFractionData(player);
            if (data != null) data.getFraction().abilityHandler().accept(buf, player);
        });
    }
}
