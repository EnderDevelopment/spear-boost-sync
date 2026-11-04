package com.enderdevelopment.autokeybind.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class AutokeybindConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve("autokeybind.json");

    public int keybindCode = 88; // Default to X key

    public static AutokeybindConfig load() {
        File configFile = CONFIG_PATH.toFile();
        if (!configFile.exists()) {
            return new AutokeybindConfig();
        }

        try (FileReader reader = new FileReader(configFile)) {
            return GSON.fromJson(reader, AutokeybindConfig.class);
        } catch (IOException e) {
            e.printStackTrace();
            return new AutokeybindConfig();
        }
    }

    public void save() {
        File configFile = CONFIG_PATH.toFile();
        try (FileWriter writer = new FileWriter(configFile)) {
            GSON.toJson(this, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}