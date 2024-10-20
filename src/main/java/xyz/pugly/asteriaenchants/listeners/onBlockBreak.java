package xyz.pugly.asteriaenchants.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import xyz.pugly.asteriaenchants.events.AEBlockBreakEvent;

public class onBlockBreak implements Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public static void onBreak(BlockBreakEvent e) {
        AEBlockBreakEvent event = new AEBlockBreakEvent(e.getBlock(), e.getPlayer(), true, e.getBlock().getDrops());
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            e.setCancelled(true);
        }
    }

}
