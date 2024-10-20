package xyz.pugly.asteriaenchants.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import xyz.pugly.asteriaenchants.events.AEPlayerFishEvent;

import java.util.Iterator;
import java.util.List;

public class onPlayerFish implements Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onPlayerFish(PlayerFishEvent event) {
        if (event.getState() != PlayerFishEvent.State.CAUGHT_FISH)
            return;

        Item item = (Item) event.getCaught();
        ItemStack itemStack = item.getItemStack();

        AEPlayerFishEvent fishEvent = new AEPlayerFishEvent(event.getPlayer(), List.of(itemStack), event.getExpToDrop());
        Bukkit.getPluginManager().callEvent(fishEvent);
        if (fishEvent.isCancelled())
            event.setCancelled(true);

        Iterator<ItemStack> iterator = fishEvent.getItems().iterator();
        item.setItemStack(iterator.next());
        
        while (iterator.hasNext()) {
            ItemStack is = iterator.next();
            if (is.getAmount() == 0)
                iterator.remove();
            else
                ((Item) item.copy(item.getLocation())).setItemStack(is);
        }
    }

}
