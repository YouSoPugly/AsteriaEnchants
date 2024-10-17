package xyz.pugly.asteriaenchants;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.annotations.Command;
import mc.obliviate.inventory.InventoryAPI;
import mc.obliviate.inventory.configurable.ConfigurableGuiCache;
import mc.obliviate.inventory.configurable.GuiConfigurationTable;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.pugly.asteriaenchants.commands.AsteriaEnchantsCommand;
import xyz.pugly.asteriaenchants.commands.CustomEnchantCommand;
import xyz.pugly.asteriaenchants.enchants.EnchantHandler;
import xyz.pugly.asteriaenchants.listeners.onInventoryClick;

import java.io.File;

public final class AsteriaEnchants extends JavaPlugin {

    private static AsteriaEnchants instance;

    @Override
    public void onEnable() {
        instance = this;

        // TODO temp delete old config
        File f = new File(getDataFolder(), "config.yml");
        f.delete();

        saveDefaultConfig();

        ConfigHandler.loadConfig(getConfig());

        // Obliviate GUI
        new InventoryAPI(this).init();
        ConfigurableGuiCache.resetCaches();
        GuiConfigurationTable.setDefaultConfigurationTable(new GuiConfigurationTable(getConfig()));

        // Command API
        CommandAPI.onEnable();
        CommandAPI.registerCommand(AsteriaEnchantsCommand.class);
        CommandAPI.registerCommand(CustomEnchantCommand.class);

        // Enchants
        Bukkit.getPluginManager().registerEvents(new EnchantHandler(), this);
        Bukkit.getScheduler().runTaskTimer(this, EnchantHandler.getTimer(), 0, 20);
        EnchantHandler.loadEnchants();

        Bukkit.getPluginManager().registerEvents(new onInventoryClick(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static AsteriaEnchants getInstance() {
        return instance;
    }

    public static void reload() {
        instance.reloadConfig();
        ConfigHandler.loadConfig(instance.getConfig());
    }
}
