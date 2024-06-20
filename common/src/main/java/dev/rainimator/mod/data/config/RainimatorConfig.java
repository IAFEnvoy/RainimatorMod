package dev.rainimator.mod.data.config;

public class RainimatorConfig {
    private static RainimatorConfig INSTANCE = null;

    public FractionType fraction = FractionType.OFF;
    public int manaHudX = 0;
    public int manaHudY = 0;

    public static RainimatorConfig getInstance() {
        if (INSTANCE == null)
            INSTANCE = ConfigLoader.load(RainimatorConfig.class, "./config/rainimator/main.json", new RainimatorConfig());
        return INSTANCE;
    }
}
