package xyz.pugly.asteriaenchants.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import xyz.pugly.asteriaenchants.enchants.Enchant;

public class EnchantTriggerEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    private final Player player;
    private final Enchant enchant;
    private boolean cancelled = false;

    public EnchantTriggerEvent(Player player, Enchant enchant) {
        super(false);
        this.player = player;
        this.enchant = enchant;
    }

    public Player getPlayer() {
        return player;
    }

    public Enchant getEnchant() {
        return enchant;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        cancelled = b;
    }
}
