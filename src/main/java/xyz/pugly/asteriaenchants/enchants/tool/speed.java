package xyz.pugly.asteriaenchants.enchants.tool;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import xyz.pugly.asteriaenchants.AsteriaEnchants;
import xyz.pugly.asteriaenchants.ConfigHandler;
import xyz.pugly.asteriaenchants.enchants.Enchant;
import xyz.pugly.asteriaenchants.enchants.EnchantData;
import xyz.pugly.asteriaenchants.enchants.Trigger;

import java.util.Collection;
import java.util.Random;

public class TelekinesisEnchant extends Enchant {

    EnchantData data = new EnchantData(ConfigHandler.getEnchant("speed"), Trigger.BLOCK_BREAK);
    private final Random random = new Random(); // Create a Random object

    @Override
    public void breakTrigger(BlockBreakEvent event, int level) {
        Player player = event.getPlayer();
        Block block = event.getBlock();

        // 20% chance to give Speed 3 for 10 seconds
        if (random.nextInt(100) < 10*level) { // 10% chance per level
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 2)); // Speed 3 (2 is the amplifier I THINK), 200 ticks = 10 seconds
        }
    }

}
