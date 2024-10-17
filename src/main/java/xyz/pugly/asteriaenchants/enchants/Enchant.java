package xyz.pugly.asteriaenchants.enchants;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import xyz.pugly.asteriaenchants.AsteriaEnchants;

import java.util.List;

public abstract class Enchant implements Listener {

    public void attackTrigger(EntityDamageByEntityEvent event, int level) {
    }

    public void breakTrigger(BlockBreakEvent event, int level) {
    }

    public void timeTrigger(Player player, int level) {
    }

    public void damageTrigger(EntityDamageByEntityEvent event, int level) {
    }

    public void fishTrigger(PlayerFishEvent event, int level) {
    }

    public void enchantTrigger(Player player, ItemStack item, int level) {
    }

    public EnchantData data;
    public EnchantData getData() {
        return data;
    }

    public ItemStack toItem() {
        ItemStack is = new ItemStack(data.getMaterial());
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(data.getName());
        im.setLore(List.of(data.getDescription(), data.getRarity()));
        is.setItemMeta(im);
        return is;
    }

    public ItemStack apply(ItemStack item, int level) {
        ItemMeta im = item.getItemMeta();

        List<String> lore = im.getLore();
        lore.add("§7" + data.getName() + " " + level);
        im.setLore(lore);

        NamespacedKey key = new NamespacedKey(AsteriaEnchants.getInstance(), "enchanted");
        im.getPersistentDataContainer().set(key, PersistentDataType.BOOLEAN, true);

        key = new NamespacedKey("asenchant", data.getId());
        im.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, level);

        item.setItemMeta(im);
        return item;
    }

    public ItemStack getEnchantBook(int level) {
        ItemStack is = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(data.getName() + " " + level);
        im.setLore(List.of(data.getDescription(), data.getRarity(), "§7Click on an item to apply this enchantment"));
        im.getPersistentDataContainer().set(new NamespacedKey("aeenchantbook", "enchant"), PersistentDataType.STRING, data.getId());
        im.getPersistentDataContainer().set(new NamespacedKey("aeenchantbook", "level"), PersistentDataType.INTEGER, level);
        is.setItemMeta(im);
        return is;
    }
}
