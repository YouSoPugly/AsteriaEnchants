package xyz.pugly.asteriaenchants.enchants;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import xyz.pugly.asteriaenchants.AsteriaEnchants;
import xyz.pugly.asteriaenchants.enchants.any.ReinforceEnchant;
import xyz.pugly.asteriaenchants.enchants.armor.BolsterEnchant;
import xyz.pugly.asteriaenchants.enchants.tool.TelekinesisEnchant;
import xyz.pugly.asteriaenchants.enchants.tool.pickaxe.VeinMinerEnchant;
import xyz.pugly.asteriaenchants.enchants.weapon.DrainEnchant;
import xyz.pugly.asteriaenchants.enchants.weapon.VampireEnchant;
import xyz.pugly.asteriaenchants.events.AEBlockBreakEvent;
import xyz.pugly.asteriaenchants.events.AEPlayerDamageEvent;
import xyz.pugly.asteriaenchants.events.AEPlayerFishEvent;
import xyz.pugly.asteriaenchants.events.ApplyEnchantEvent;
import xyz.pugly.asteriaenchants.events.EnchantTriggerEvent;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class EnchantHandler implements Listener {

    private static HashMap<Trigger, HashMap<String, Enchant>> enchants = new HashMap<>();

    public static void registerEnchant(String id, Enchant enchant) {
        if (enchant.getData() == null)
            return;

        HashMap<String, Enchant> triggerMap = enchants.getOrDefault(enchant.getData().getTrigger(), new HashMap<>());

        triggerMap.put(id, enchant);

        enchants.put(enchant.getData().getTrigger(), triggerMap);
    }

    public static void clearEnchants() {
        enchants.clear();
    }

    public static void loadEnchants() {
        clearEnchants();
        // Weapon Enchants
        registerEnchant("vampire", new VampireEnchant());
        registerEnchant("drain", new DrainEnchant());
        // Sword Enchants
        // Axe Enchants

        // Armor Enchants
        registerEnchant("bolster", new BolsterEnchant());
        // Helmet Enchants
        // Chestplate Enchants
        // Leggings Enchants
        // Boots Enchants

        // Tool Enchants
        registerEnchant("telekinesis", new TelekinesisEnchant());
        // Pickaxe Enchants
        registerEnchant("veinminer", new VeinMinerEnchant());
        // Axe Enchants
        // Shovel Enchants
        // Hoe Enchants
        // Fishing Rod Enchants

        // Bow Enchants

        // Any Enchants
        registerEnchant("reinforce", new ReinforceEnchant());

    }

    public static HashMap<String, Enchant> getAllEnchants() {
        HashMap<String, Enchant> allEnchants = new HashMap<>();
        for (HashMap<String, Enchant> triggerMap : enchants.values()) {
            allEnchants.putAll(triggerMap);
        }
        return allEnchants;
    }

    public static HashMap<String, Enchant> getEnchants(Trigger trigger) {
        return enchants.get(trigger);
    }

    public static Enchant getEnchant(String id) {
        for (HashMap<String, Enchant> triggerMap : enchants.values()) {
            if (triggerMap.containsKey(id)) {
                return triggerMap.get(id);
            }
        }
        return null;
    }

    public static HashMap<Enchant, Integer> getItemEnchants(ItemStack is) {
        if (!is.hasItemMeta()) {
            return null;
        }

        ItemMeta im = is.getItemMeta();
        PersistentDataContainer pdc = im.getPersistentDataContainer();

        NamespacedKey key = new NamespacedKey(AsteriaEnchants.getInstance(), "enchanted");
        if (!pdc.has(key, PersistentDataType.BOOLEAN)) {
            return null;
        }

        HashMap<Enchant, Integer> itemEnchants = new HashMap<>();
        Set<NamespacedKey> keys = pdc.getKeys();

        for (NamespacedKey k : keys) {
            if (k.getNamespace().equals("asenchant")) {
                String enchant = k.getKey();
                int level = pdc.get(k, PersistentDataType.INTEGER);
                Enchant e = getEnchant(enchant);
                if (e != null) {
                    itemEnchants.put(e, level);
                }
            }
        }

        return itemEnchants;
    }

    private static Collection<Enchant> sortEnchants(Collection<Enchant> enchants) {
        return enchants.stream().sorted((e1, e2) -> {
            if (e1.getPriority() == e2.getPriority()) {
                return e1.getData().getName().compareTo(e2.getData().getName());
            }
            return e1.getPriority().compareTo(e2.getPriority());
        }).toList();
    }

    // Trigger Handlers
    // TODO: Extract repeated code into methods
    // TODO: Allow for multiple triggers per enchant

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public static void onEnchant(ApplyEnchantEvent event) {
        EnchantTriggerEvent e = new EnchantTriggerEvent(event.getPlayer(), event.getEnchant());
        Bukkit.getPluginManager().callEvent(e);
        if (e.isCancelled())
            return;
        event.getEnchant().enchantTrigger(event.getPlayer(), event.getItem(), event.getLevel());
    }

    public static Runnable getTimer() {
        return () -> {
            for (Player p : Bukkit.getOnlinePlayers()) {

                ItemStack[] armor = p.getInventory().getArmorContents();
                for (ItemStack is : armor) {
                    if (!is.hasItemMeta())
                        continue;

                    HashMap<Enchant, Integer> enchants = getItemEnchants(is);
                    if (enchants == null)
                        continue;

                    for (Enchant enchant : sortEnchants(enchants.keySet())) {
                        if (enchant.getData().getTrigger() == Trigger.TIME) {
                            EnchantTriggerEvent e = new EnchantTriggerEvent(p, enchant);
                            Bukkit.getPluginManager().callEvent(e);
                            if (e.isCancelled())
                                continue;
                            enchant.timeTrigger(p, enchants.get(enchant));
                        }
                    }
                }

                ItemStack is = p.getInventory().getItemInMainHand();
                if (!is.hasItemMeta())
                    continue;

                HashMap<Enchant, Integer> enchants = getItemEnchants(is);
                if (enchants == null)
                    continue;

                for (Enchant enchant : sortEnchants(enchants.keySet())) {
                    if (enchant.getData().getTrigger() == Trigger.TIME) {
                        EnchantTriggerEvent e = new EnchantTriggerEvent(p, enchant);
                        Bukkit.getPluginManager().callEvent(e);
                        if (e.isCancelled())
                            continue;
                        enchant.timeTrigger(p, enchants.get(enchant));
                    }
                }
            }
        };
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public static void onAttack(AEPlayerDamageEvent event) {
        Player player = event.getAttacker();
        if (!player.getInventory().getItemInMainHand().hasItemMeta())
            return;

        HashMap<Enchant, Integer> enchants = getItemEnchants(player.getInventory().getItemInMainHand());
        if (enchants == null)
            return;

        for (Enchant enchant : sortEnchants(enchants.keySet())) {
            if (enchant.getData().getTrigger() == Trigger.DAMAGE_DEALT) {
                EnchantTriggerEvent e = new EnchantTriggerEvent(player, enchant);
                Bukkit.getPluginManager().callEvent(e);
                if (e.isCancelled())
                    continue;
                enchant.attackTrigger(event, enchants.get(enchant));
            }
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public static void onDamage(AEPlayerDamageEvent event) {
        if (!(event.getVictim() instanceof Player))
            return;

        Player player = (Player) event.getVictim();
        if (!player.getInventory().getItemInMainHand().hasItemMeta())
            return;

        HashMap<Enchant, Integer> enchants = getItemEnchants(player.getInventory().getItemInMainHand());
        if (enchants == null)
            return;

        for (Enchant enchant : sortEnchants(enchants.keySet())) {
            if (enchant.getData().getTrigger() == Trigger.DAMAGE_TAKEN) {
                EnchantTriggerEvent e = new EnchantTriggerEvent(player, enchant);
                Bukkit.getPluginManager().callEvent(e);
                if (e.isCancelled())
                    continue;
                enchant.damageTrigger(event, enchants.get(enchant));
            }
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public static void onMine(AEBlockBreakEvent event) {
        Player player = event.getPlayer();
        if (!player.getInventory().getItemInMainHand().hasItemMeta())
            return;

        HashMap<Enchant, Integer> enchants = getItemEnchants(player.getInventory().getItemInMainHand());
        if (enchants == null)
            return;

        for (Enchant enchant : sortEnchants(enchants.keySet())) {
            if (enchant.getData().getTrigger() == Trigger.BLOCK_BREAK) {
                EnchantTriggerEvent e = new EnchantTriggerEvent(player, enchant);
                Bukkit.getPluginManager().callEvent(e);
                if (e.isCancelled())
                    continue;
                enchant.breakTrigger(event, enchants.get(enchant));
            }
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public static void onFish(AEPlayerFishEvent event) {
        Player player = event.getPlayer();
        if (!player.getInventory().getItemInMainHand().hasItemMeta())
            return;

        HashMap<Enchant, Integer> enchants = getItemEnchants(player.getInventory().getItemInMainHand());
        if (enchants == null)
            return;

        for (Enchant enchant : sortEnchants(enchants.keySet())) {
            if (enchant.getData().getTrigger() == Trigger.FISH) {
                EnchantTriggerEvent e = new EnchantTriggerEvent(player, enchant);
                Bukkit.getPluginManager().callEvent(e);
                if (e.isCancelled())
                    continue;
                enchant.fishTrigger(event, enchants.get(enchant));
            }
        }
    }



}
