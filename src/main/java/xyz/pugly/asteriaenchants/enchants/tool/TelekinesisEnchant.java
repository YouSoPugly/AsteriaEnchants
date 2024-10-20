package xyz.pugly.asteriaenchants.enchants.tool;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import xyz.pugly.asteriaenchants.AsteriaEnchants;
import xyz.pugly.asteriaenchants.ConfigHandler;
import xyz.pugly.asteriaenchants.enchants.Enchant;
import xyz.pugly.asteriaenchants.enchants.EnchantData;
import xyz.pugly.asteriaenchants.enchants.Priority;
import xyz.pugly.asteriaenchants.enchants.Trigger;
import xyz.pugly.asteriaenchants.events.AEBlockBreakEvent;

import java.util.Collection;
import java.util.HashMap;

public class TelekinesisEnchant extends Enchant {

    EnchantData data = new EnchantData(ConfigHandler.getEnchant("telekinesis"), Trigger.BLOCK_BREAK);
    Priority priority = Priority.HIGHEST;

    @Override
    public void breakTrigger(AEBlockBreakEvent event, int level) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        ItemStack item = player.getInventory().getItemInMainHand();

        event.setDropItems(false);
        event.getDrops().forEach(drop -> {
            HashMap<Integer, ItemStack> excess = player.getInventory().addItem(drop);

        });
    }

}
