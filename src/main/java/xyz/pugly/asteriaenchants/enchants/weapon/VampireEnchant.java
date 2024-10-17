package xyz.pugly.asteriaenchants.enchants.weapon;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import xyz.pugly.asteriaenchants.AsteriaEnchants;
import xyz.pugly.asteriaenchants.ConfigHandler;
import xyz.pugly.asteriaenchants.enchants.Enchant;
import xyz.pugly.asteriaenchants.enchants.EnchantData;
import xyz.pugly.asteriaenchants.enchants.Trigger;

public class VampireEnchant extends Enchant {

    EnchantData data = new EnchantData(ConfigHandler.getEnchant("vampire"), Trigger.DAMAGE_DEALT);

    @Override
    public void attackTrigger(EntityDamageByEntityEvent event, int level) {
        Player player = (Player) event.getDamager();
        if (Math.random() < 0.1 * level) {
            player.setHealth(player.getHealth() + 4);
        }
    }
}
