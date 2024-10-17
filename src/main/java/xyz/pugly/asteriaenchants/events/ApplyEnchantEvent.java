package xyz.pugly.asteriaenchants.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import xyz.pugly.asteriaenchants.enchants.Enchant;

public class ApplyEnchantEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    private final Player player;
    private final ItemStack item;
    private final Enchant enchant;
    private final int level;
    private boolean cancelled;

    public ApplyEnchantEvent(Player player, ItemStack item, Enchant enchant, int level) {
        super(true);
        this.player = player;
        this.item = item;
        this.enchant = enchant;
        this.level = level;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        this.cancelled = b;
    }

    public Player getPlayer() {
        return player;
    }

    public ItemStack getItem() {
        return item;
    }

    public Enchant getEnchant() {
        return enchant;
    }

    public int getLevel() {
        return level;
    }
}
