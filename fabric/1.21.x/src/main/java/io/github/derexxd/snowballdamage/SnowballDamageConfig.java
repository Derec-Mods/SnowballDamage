package io.github.derexxd.snowballdamage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SnowballDamageConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static SnowballDamageConfig INSTANCE = new SnowballDamageConfig();

    public boolean snowballs = true;
    public boolean eggs = true;
    public float damage = 0.0F;

    public static void load() {
        Path path = FabricLoader.getInstance().getConfigDir().resolve("snowballdamage.json");
        SnowballDamageConfig config = new SnowballDamageConfig();
        try {
            if (Files.exists(path)) {
                JsonObject json = JsonParser.parseString(Files.readString(path)).getAsJsonObject();
                if (json.has("snowballs")) {
                    config.snowballs = json.get("snowballs").getAsBoolean();
                }
                if (json.has("eggs")) {
                    config.eggs = json.get("eggs").getAsBoolean();
                }
                if (json.has("damage")) {
                    config.damage = json.get("damage").getAsFloat();
                }
            }
            Files.createDirectories(path.getParent());
            Files.writeString(path, GSON.toJson(config));
        } catch (IOException e) {
            SnowballDamage.LOGGER.error("Failed to load config", e);
        }
        INSTANCE = config;
    }
}
