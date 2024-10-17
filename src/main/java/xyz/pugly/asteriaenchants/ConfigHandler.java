package xyz.pugly.asteriaenchants;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigHandler {

    private static int maxCount = 0;
    private static String levelWeightEquation = "";

    private static ConfigurationSection enchants;

    public static void loadConfig(FileConfiguration config) {
        maxCount = config.getInt("max-count");
        levelWeightEquation = config.getString("level-weight-equation");

        enchants = config.getConfigurationSection("enchants");
    }

    public static int getMaxCount() {
        return maxCount;
    }

    public static ConfigurationSection getEnchant(String id) {
        return enchants.getConfigurationSection(id);
    }

}
