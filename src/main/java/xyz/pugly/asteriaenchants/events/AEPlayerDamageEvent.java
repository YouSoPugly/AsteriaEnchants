package xyz.pugly.asteriaenchants.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class AEPlayerDamageEvent extends Event implements Cancellable {

    public static final HandlerList HANDLERS = new HandlerList();

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    private boolean cancelled = false;
    private final Player attacker;
    private final Entity victim;
    private double damage;
    private final boolean applyEnchants;

    public AEPlayerDamageEvent(Player attacker, Entity victim, double damage, boolean applyEnchants) {
        super(true);
        this.attacker = attacker;
        this.victim = victim;
        this.damage = damage;
        this.applyEnchants = applyEnchants;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        cancelled = b;
    }

    public Player getAttacker() {
        return attacker;
    }

    public Entity getVictim() {
        return victim;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public boolean shouldApplyEnchants() {
        return applyEnchants;
    }
}
