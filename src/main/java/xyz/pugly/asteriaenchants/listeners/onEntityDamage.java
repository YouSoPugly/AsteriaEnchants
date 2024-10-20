package xyz.pugly.asteriaenchants.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import xyz.pugly.asteriaenchants.events.AEPlayerDamageEvent;

public class onEntityDamage implements Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player))
            return;

        Player player = (Player) event.getDamager();
        AEPlayerDamageEvent damageEvent = new AEPlayerDamageEvent(player, event.getEntity(), event.getDamage(), true);
        player.getServer().getPluginManager().callEvent(damageEvent);
        if (damageEvent.isCancelled())
            event.setCancelled(true);
        else
            event.setDamage(damageEvent.getDamage());
    }

}
