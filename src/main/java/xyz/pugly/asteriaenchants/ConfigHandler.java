package xyz.pugly.asteriaenchants;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;

public class ConfigHandler {

    private static int maxCount = 0;
    private static int time = 0;
    private static String levelWeightEquation = "";
    private static HashMap<String, Boolean> integrationsMap = new HashMap<>();

    private static ConfigurationSection enchants;

    public static void loadConfig(FileConfiguration config) {
        maxCount = config.getInt("max-count");
        levelWeightEquation = config.getString("level-weight-equation");
        time = config.getInt("time");

        enchants = config.getConfigurationSection("enchants");

        integrationsMap.put("worldguard", config.getBoolean("integrations.worldguard"));
        integrationsMap.put("towny", config.getBoolean("integrations.towny"));
    }

    public static int getMaxCount() {
        return maxCount;
    }

    public static int getTime() {
        return time;
    }

    public static ConfigurationSection getEnchant(String id) {
        return enchants.getConfigurationSection(id);
    }

    public static boolean checkIntegration(String integration) {
        return integrationsMap.get(integration);
    }

}
