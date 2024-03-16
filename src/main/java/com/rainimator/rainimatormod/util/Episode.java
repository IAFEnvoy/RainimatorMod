package com.rainimator.rainimatormod.util;

public enum Episode {
    TheStruggle("The Struggle", Type.Series1),
    ColdAsIce("Cold As Ice", Type.Series1),
    BeginAgain("Begin Again", Type.Special),
    WeAreTheDanger("We Are The Danger", Type.Series1),
    JustSoYouKnow("Just So You Know", Type.Series1),
    Goodbye("Goodbye", Type.Series1),
    WingsOfSalvation("Wings Of Salvation", Type.Series2),
    HardPillToSwallow("Hard Pill To Swallow", Type.Series2),
    WitherHeart("Wither Heart", Type.Series2),
    EnderWish("Ender Wish", Type.Series2),
    WishingDead("Wishing Dead", Type.Series2),
    Falling("Falling", Type.Series2),
    ColdAsIceRemake("Cold As Ice Remake", Type.Series2),
    Nightmares("Nightmares", Type.Special),
    Poison("Poison", Type.Special),
    BeAfraid("Be Afraid", Type.Series3),
    Eternal("Eternal", Type.Series3),
    GottaGetOuttaHere("Gotta Get Outta Here", Type.Series3),
    ClearSkies("Clear Skies", Type.Series3),
    ToTheVoid("To The Void", Type.Special),
    BackIntoDarkness("Back Into Darkness", Type.Series3),
    AlreadyDead("Already Dead", Type.Series3),
    PreachToTheChoir("Preach To The Choir", Type.Series3),
    WeAreTheDangerXL("We Are The Danger XL", Type.Series3),
    TheBrave("The Brave", Type.EnderKingdom),
    ITryToday("I Try Today", Type.Series4);
    private final String name;
    private final Type type;

    Episode(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public Type getType() {
        return this.type;
    }

    enum Type {
        Series1, Series2, Series3, Series4, Special, EnderKingdom
    }
}
