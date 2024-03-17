package com.rainimator.rainimatormod.network;

import com.rainimator.rainimatormod.util.Timeout;
import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.network.protocol.game.ClientboundLevelEventPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerAbilitiesPacket;
import net.minecraft.network.protocol.game.ClientboundUpdateMobEffectPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

@EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class EnderBookSkillButtonMessage {
    private final int buttonID;
    private final int x;
    private final int y;
    private final int z;
    public EnderBookSkillButtonMessage(FriendlyByteBuf buffer) {
        this.buttonID = buffer.readInt();
        this.x = buffer.readInt();
        this.y = buffer.readInt();
        this.z = buffer.readInt();
    }

    public EnderBookSkillButtonMessage(int buttonID, int x, int y, int z) {
        this.buttonID = buttonID;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static void buffer(EnderBookSkillButtonMessage message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.buttonID);
        buffer.writeInt(message.x);
        buffer.writeInt(message.y);
        buffer.writeInt(message.z);
    }

    public static void handler(EnderBookSkillButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            ServerPlayer serverPlayer = context.getSender();
            int buttonID = message.buttonID;
            int x = message.x;
            int y = message.y;
            int z = message.z;
            if (serverPlayer != null)
                handleButtonAction(serverPlayer, buttonID, x, y, z);
        });
        context.setPacketHandled(true);
    }

    public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z) {
        Level world = entity.level;
        if (!world.hasChunk(SectionPos.blockToSectionCoord(x), SectionPos.blockToSectionCoord(z)))
            return;
        if (buttonID == 0) {
            boolean finished = false;
            if (entity.level.dimension() == Level.END || entity.level.dimension() == Level.NETHER) {
                entity.closeContainer();
                if (entity instanceof ServerPlayer _player) {
                    if (!_player.level.isClientSide()) {
                        ResourceKey<Level> destinationType = Level.OVERWORLD;
                        if (_player.level.dimension() == destinationType) {
                            finished = true;
                        } else {
                            ServerLevel nextLevel = _player.server.getLevel(destinationType);
                            if (nextLevel != null) {
                                _player.connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.WIN_GAME, 0.0F));
                                _player.teleportTo(nextLevel, _player.getX(), _player.getY(), _player.getZ(), _player.getYRot(), _player.getXRot());
                                _player.connection.send(new ClientboundPlayerAbilitiesPacket(_player.getAbilities()));
                                for (MobEffectInstance _effectinstance : _player.getActiveEffects())
                                    _player.connection.send(new ClientboundUpdateMobEffectPacket(_player.getId(), _effectinstance));
                                _player.connection.send(new ClientboundLevelEventPacket(1032, BlockPos.ZERO, 0, false));
                            }
                        }
                    }
                }
                if (!finished) {
                    if (!entity.level.isClientSide())
                        entity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 500, 1));
                    entity.getCooldowns().addCooldown(entity.getMainHandItem().getItem(), 600);
                }
            } else if (entity.level.dimension() == Level.OVERWORLD) {
                entity.closeContainer();
                if (!entity.level.isClientSide())
                    entity.displayClientMessage(new TranslatableComponent("item.rainimator.enderbook.error.overworld"), true);
            }
        }
        if (buttonID == 1) {
            boolean finished = false;
            if (entity.level.dimension() == Level.END) {
                entity.closeContainer();
                if (entity instanceof ServerPlayer _player) {
                    if (!_player.level.isClientSide()) {
                        ResourceKey<Level> destinationType = Level.NETHER;
                        if (_player.level.dimension() == destinationType) {
                            finished = true;
                        } else {
                            ServerLevel nextLevel = _player.server.getLevel(destinationType);
                            if (nextLevel != null) {
                                _player.connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.WIN_GAME, 0.0F));
                                _player.teleportTo(nextLevel, _player.getX(), _player.getY(), _player.getZ(), _player.getYRot(), _player.getXRot());
                                _player.connection.send(new ClientboundPlayerAbilitiesPacket(_player.getAbilities()));
                                for (MobEffectInstance _effectinstance : _player.getActiveEffects())
                                    _player.connection.send(new ClientboundUpdateMobEffectPacket(_player.getId(), _effectinstance));
                                _player.connection.send(new ClientboundLevelEventPacket(1032, BlockPos.ZERO, 0, false));
                            }
                        }
                    }
                }
                if (!finished) {
                    if (!entity.level.isClientSide())
                        entity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 500, 1));
                    entity.getCooldowns().addCooldown(entity.getMainHandItem().getItem(), 600);
                }
            } else if (entity.level.dimension() == Level.OVERWORLD) {
                entity.closeContainer();
                if (entity instanceof ServerPlayer _player) {
                    if (!_player.level.isClientSide()) {
                        ResourceKey<Level> destinationType = Level.NETHER;
                        if (_player.level.dimension() == destinationType) {
                            finished = true;
                        } else {
                            ServerLevel nextLevel = _player.server.getLevel(destinationType);
                            if (nextLevel != null) {
                                _player.connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.WIN_GAME, 0.0F));
                                _player.teleportTo(nextLevel, _player.getX(), _player.getY(), _player.getZ(), _player.getYRot(), _player.getXRot());
                                _player.connection.send(new ClientboundPlayerAbilitiesPacket(_player.getAbilities()));
                                for (MobEffectInstance _effectinstance : _player.getActiveEffects())
                                    _player.connection.send(new ClientboundUpdateMobEffectPacket(_player.getId(), _effectinstance));
                                _player.connection.send(new ClientboundLevelEventPacket(1032, BlockPos.ZERO, 0, false));
                            }
                        }
                    }
                }
                if (!finished) {
                    if (!entity.level.isClientSide())
                        entity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 500, 1));
                    entity.getCooldowns().addCooldown(entity.getMainHandItem().getItem(), 600);
                    Timeout.create(10, () -> {
                        entity.teleportTo(x, (double) y + 120.0D, z);
                        if ((Entity) entity instanceof ServerPlayer _serverPlayer)
                            _serverPlayer.connection.teleport(x, (double) y + 120.0D, z, entity.getYRot(), entity.getXRot());
                        world.setBlock(new BlockPos(x, y, (double) z), Blocks.AIR.defaultBlockState(), 3);
                        world.setBlock(new BlockPos(x, (double) y + 1.0D, z), Blocks.AIR.defaultBlockState(), 3);
                        world.setBlock(new BlockPos(x, (double) y - 1.0D, z), Blocks.AIR.defaultBlockState(), 3);
                    });
                }
            } else if (entity.level.dimension() == Level.NETHER) {
                entity.closeContainer();
                if (!entity.level.isClientSide())
                    entity.displayClientMessage(new TranslatableComponent("item.rainimator.enderbook.error.nether"), true);
            }
        }
        if (buttonID == 2) {
            if (entity.level.dimension() == Level.OVERWORLD || entity.level.dimension() == Level.NETHER) {
                if (entity instanceof Player)
                    entity.closeContainer();
                if (entity instanceof ServerPlayer _player) {
                    if (!_player.level.isClientSide()) {
                        ResourceKey<Level> destinationType = Level.END;
                        if (_player.level.dimension() == destinationType)
                            return;
                        ServerLevel nextLevel = _player.server.getLevel(destinationType);
                        if (nextLevel != null) {
                            _player.connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.WIN_GAME, 0.0F));
                            _player.teleportTo(nextLevel, _player.getX(), _player.getY(), _player.getZ(), _player.getYRot(), _player.getXRot());
                            _player.connection.send(new ClientboundPlayerAbilitiesPacket(_player.getAbilities()));
                            for (MobEffectInstance _effectinstance : _player.getActiveEffects())
                                _player.connection.send(new ClientboundUpdateMobEffectPacket(_player.getId(), _effectinstance));
                            _player.connection.send(new ClientboundLevelEventPacket(1032, BlockPos.ZERO, 0, false));
                        }
                    }
                }
                if (!entity.level.isClientSide())
                    entity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 500, 1));
                entity.getCooldowns().addCooldown(entity.getMainHandItem().getItem(), 600);
                Timeout.create(10, () -> {
                    entity.teleportTo(x, (double) y + 150.0D, z);
                    if ((Entity) entity instanceof ServerPlayer _serverPlayer)
                        _serverPlayer.connection.teleport(x, (double) y + 150.0D, z, entity.getYRot(), entity.getXRot());
                    world.setBlock(new BlockPos(x, y, (double) z), Blocks.AIR.defaultBlockState(), 3);
                    world.setBlock(new BlockPos(x, (double) y + 1.0D, z), Blocks.AIR.defaultBlockState(), 3);
                    world.setBlock(new BlockPos(x, (double) y - 1.0D, z), Blocks.AIR.defaultBlockState(), 3);
                });
            } else if (entity.level.dimension() == Level.END) {
                entity.closeContainer();
                if (!entity.level.isClientSide())
                    entity.displayClientMessage(new TranslatableComponent("item.rainimator.enderbook.error.end"), true);
            }
        }
    }

    @SubscribeEvent
    public static void registerMessage(FMLCommonSetupEvent event) {
        NetworkManager.addNetworkMessage(EnderBookSkillButtonMessage.class, EnderBookSkillButtonMessage::buffer, EnderBookSkillButtonMessage::new, EnderBookSkillButtonMessage::handler);
    }
}