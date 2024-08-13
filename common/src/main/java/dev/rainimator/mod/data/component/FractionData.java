package dev.rainimator.mod.data.component;

import dev.rainimator.mod.data.fraction.Fraction;
import net.minecraft.nbt.NbtCompound;

public class FractionData {
    private Fraction fraction;

    public FractionData() {
        fraction = Fraction.NONE;
    }

    public void encode(NbtCompound tag) {
        tag.putString("fraction", this.fraction.name());
    }

    public void decode(NbtCompound tag) {
        this.fraction = Fraction.getByName(tag.getString("fraction"));
    }

    public Fraction getFraction() {
        return fraction;
    }

    public void setFraction(Fraction fraction) {
        this.fraction = fraction;
    }
}
