package xyz.pugly.asteriaenchants.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class AEPlayerFishEvent extends Event implements Cancellable {

    public static final HandlerList handlers = new HandlerList();

    @Override
    public @NotNull HandlerList getHandlers() {
        return null;
    }

    private boolean cancelled = false;

    private final Player player;
    private final Collection<ItemStack> items;
    private int exp;

    public AEPlayerFishEvent(Player player, Collection<ItemStack> items, int exp) {
        super(true);
        this.player = player;
        this.items = items;
        this.exp = exp;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        cancelled = b;
    }

    public Player getPlayer() {
        return player;
    }

    public Collection<ItemStack> getItems() {
        return items;
    }

    public void setItems(Collection<ItemStack> items) {
        this.items.clear();
        this.items.addAll(items);
    }

    public void addItem(ItemStack item) {
        this.items.add(item);
    }

    public void removeItem(ItemStack item) {
        this.items.remove(item);
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
}
