package dev.rainimator.mod.registry;

import com.iafenvoy.neptune.fraction.Fraction;
import dev.rainimator.mod.RainimatorMod;
import net.minecraft.util.Identifier;

public class RainimatorFractions {
    public static final Fraction FROST = new Fraction(Identifier.of(RainimatorMod.MOD_ID, "frost"), () -> RainimatorBanners.FROST, (buf, player) -> {
    });
    public static final Fraction UNDEAD = new Fraction(Identifier.of(RainimatorMod.MOD_ID, "undead"), () -> RainimatorBanners.UNDEAD, (buf, player) -> {

    });
    public static final Fraction NETHER = new Fraction(Identifier.of(RainimatorMod.MOD_ID, "nether"), () -> RainimatorBanners.NETHER, (buf, player) -> {

    });
    public static final Fraction ENDER = new Fraction(Identifier.of(RainimatorMod.MOD_ID, "ender"), () -> RainimatorBanners.DRAGONSPIRE, (buf, player) -> {

    });

    public static void init() {
    }
}
