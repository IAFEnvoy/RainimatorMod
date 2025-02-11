package com.iafenvoy.rainimator.config;

public class ClientConfig {
    private static ClientConfig INSTANCE = null;

    public int manaHudX = 0;
    public int manaHudY = 0;

    public static ClientConfig getInstance() {
        if (INSTANCE == null)
            INSTANCE = ConfigLoader.load(ClientConfig.class, "./config/rainimator/main.json", new ClientConfig());
        return INSTANCE;
    }
}
