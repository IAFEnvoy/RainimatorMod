package dev.rainimator.mod.data.config;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ConfigLoader {
    private static final Gson GSON = new Gson();

    public static <T> T load(Class<T> clazz, String path, T defaultValue) {
        try {
            FileInputStream stream = new FileInputStream(path);
            InputStreamReader reader = new InputStreamReader(stream);
            return GSON.fromJson(reader, clazz);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            try {
                FileUtils.write(new File(path), GSON.toJson(defaultValue), StandardCharsets.UTF_8);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return defaultValue;
        }
    }
}
