package xyz.pugly.asteriaenchants.integrations;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import xyz.pugly.asteriaenchants.AsteriaEnchants;
import xyz.pugly.asteriaenchants.ConfigHandler;

import java.util.Collection;

public class IntegrationHandler {

    private static boolean towny = false;
    private static boolean worldGuard = false;


    public static void loadIntegrations() {
        if (Bukkit.getPluginManager().isPluginEnabled("WorldGuard") && ConfigHandler.checkIntegration("worldguard")) {
            worldGuard = true;
            AsteriaEnchants.log("WorldGuard integration enabled");
        }

        if (Bukkit.getPluginManager().isPluginEnabled("Towny") && ConfigHandler.checkIntegration("towny")) {
            towny = true;
            AsteriaEnchants.log("Towny integration enabled");
        }
    }

    public static boolean canBreakBlock(Block block, Player player) {
        if (towny && !TownyAdvanced.canBreakBlock(block, player))
            return false;

        if (worldGuard && !WorldGuard.canBreakBlock(block, player))
            return false;

        return true;
    }

    public static boolean canDamage(Player attacker, Player victim) {
        // Check if the attacker can damage the victim
        return true;
    }

}
