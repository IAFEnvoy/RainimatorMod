package com.iafenvoy.rainimator.config;

public class ClientConfig {
    private static final ClientConfig INSTANCE = ConfigLoader.load(ClientConfig.class, "./config/rainimator/main.json", new ClientConfig());

    public int manaHudX = 0;
    public int manaHudY = 0;

    public static ClientConfig getInstance() {
        return INSTANCE;
    }
}
