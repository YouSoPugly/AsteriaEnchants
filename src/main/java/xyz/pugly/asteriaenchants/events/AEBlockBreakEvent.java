package xyz.pugly.asteriaenchants.events;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class AEBlockBreakEvent extends BlockBreakEvent implements Cancellable {

    public static final HandlerList handlers = new HandlerList();

    private final Block block;
    private final Player player;
    private final boolean breakFurther;
    private final Collection<ItemStack> drops;
    private boolean dropItems;

    private boolean cancelled = false;

    public AEBlockBreakEvent(@NotNull Block block, @NotNull Player player, boolean breakFurther, Collection<ItemStack> drops) {
        super(block, player);
        this.block = block;
        this.player = player;
        this.breakFurther = breakFurther;
        this.drops = drops;
        this.dropItems = true;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean shouldBreakFurther() {
        return breakFurther;
    }

    public Collection<ItemStack> getDrops() {
        return drops;
    }

    public void setDrops(Collection<ItemStack> drops) {
        this.drops.clear();
        this.drops.addAll(drops);
    }

    public void addDrop(ItemStack drop) {
        this.drops.add(drop);
    }

    public void removeDrop(ItemStack drop) {
        this.drops.remove(drop);
    }

    public boolean shouldDropItems() {
        return dropItems;
    }

    public void setDropItems(boolean dropItems) {
        this.dropItems = dropItems;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }
}
