package dev.rainimator.mod.data.fraction;

import com.iafenvoy.neptune.util.function.consumer.Consumer2;
import dev.rainimator.mod.registry.RainimatorBanners;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;

import java.util.HashMap;
import java.util.Map;

public record Fraction(String name, ItemStack banner, Consumer2<PacketByteBuf, PlayerEntity> abilityHandler) {
    private static final Map<String, Fraction> BY_NAME = new HashMap<>();
    public static final Fraction NONE = new Fraction("none", new ItemStack(Items.WHITE_BANNER), (buf, player) -> {
    });
    public static final Fraction FROST = new Fraction("frost", RainimatorBanners.FROST, (buf, player) -> {
    });
    public static final Fraction UNDEAD = new Fraction("undead", RainimatorBanners.UNDEAD, (buf, player) -> {

    });
    public static final Fraction NETHER = new Fraction("nether", RainimatorBanners.NETHER, (buf, player) -> {

    });
    public static final Fraction ENDER = new Fraction("ender", RainimatorBanners.DRAGONSPIRE, (buf, player) -> {

    });

    public Fraction(String name, ItemStack banner, Consumer2<PacketByteBuf, PlayerEntity> abilityHandler) {
        this.name = name;
        this.banner = banner;
        this.abilityHandler = abilityHandler;
        BY_NAME.put(name, this);
    }

    public static Fraction getByName(String name) {
        return BY_NAME.getOrDefault(name, NONE);
    }
}
