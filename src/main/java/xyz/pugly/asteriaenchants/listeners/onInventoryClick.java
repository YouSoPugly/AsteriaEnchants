package xyz.pugly.asteriaenchants.listeners;

import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import xyz.pugly.asteriaenchants.AsteriaEnchants;
import xyz.pugly.asteriaenchants.ConfigHandler;
import xyz.pugly.asteriaenchants.enchants.Enchant;
import xyz.pugly.asteriaenchants.enchants.EnchantHandler;
import xyz.pugly.asteriaenchants.enchants.EnchantType;
import xyz.pugly.asteriaenchants.events.ApplyEnchantEvent;

public class onInventoryClick implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getView().getType() != InventoryType.PLAYER)
            return;

        ItemStack held = e.getCursor();
        if (held == null)
            return;

        ItemStack clicked = e.getCurrentItem();
        if (clicked == null)
            return;

        ItemMeta heldMeta = held.getItemMeta();
        if (heldMeta == null)
            return;

        NamespacedKey enchantKey = new NamespacedKey("aeenchantbook", "enchant");
        NamespacedKey levelKey = new NamespacedKey("aeenchant", "level");
        if (!heldMeta.getPersistentDataContainer().has(enchantKey) && !heldMeta.getPersistentDataContainer().has(levelKey))
            return;

        Enchant enchant = EnchantHandler.getEnchant(heldMeta.getPersistentDataContainer().get(enchantKey, PersistentDataType.STRING));
        int level = heldMeta.getPersistentDataContainer().get(levelKey, PersistentDataType.INTEGER);

        if (enchant == null)
            return;

        if (!EnchantType.getApplicable(clicked).stream().anyMatch(enchantType -> enchant.getData().getTypes().contains(enchantType)))
            return;

        ItemMeta clickedMeta = clicked.getItemMeta();
        int enchantCount = 0;
        if (clickedMeta != null) {
            PersistentDataContainer pdc = clickedMeta.getPersistentDataContainer();
            for (NamespacedKey key : pdc.getKeys()) {
                if (key.getNamespace().equals("aeenchant")) {
                    enchantCount++;
                }
            }
        }

        if (enchantCount >= ConfigHandler.getMaxCount())
            return;

        ApplyEnchantEvent event = new ApplyEnchantEvent((Player) e.getWhoClicked(), clicked, enchant, level);
        AsteriaEnchants.getInstance().getServer().getPluginManager().callEvent(event);
        if (event.isCancelled())
            return;

        e.setCancelled(true);
        e.getView().setCursor(null);
        e.getView().setItem(e.getSlot(), enchant.apply(clicked, level));
        Player player = (Player) e.getWhoClicked();
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
    }



}
