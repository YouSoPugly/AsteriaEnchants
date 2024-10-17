package xyz.pugly.asteriaenchants.enchants.tool;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import xyz.pugly.asteriaenchants.AsteriaEnchants;
import xyz.pugly.asteriaenchants.ConfigHandler;
import xyz.pugly.asteriaenchants.enchants.Enchant;
import xyz.pugly.asteriaenchants.enchants.EnchantData;
import xyz.pugly.asteriaenchants.enchants.Trigger;

import java.util.Collection;

public class TelekinesisEnchant extends Enchant {

    EnchantData data = new EnchantData(ConfigHandler.getEnchant("telekinesis"), Trigger.BLOCK_BREAK);

    @Override
    public void breakTrigger(BlockBreakEvent event, int level) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        ItemStack item = player.getInventory().getItemInMainHand();

        Collection<ItemStack> drops = player.getInventory().addItem(block.getDrops(item).toArray(new ItemStack[0])).values();
        for (ItemStack drop : drops) {
            player.getWorld().dropItem(player.getLocation(), drop);
        }
    }

}
