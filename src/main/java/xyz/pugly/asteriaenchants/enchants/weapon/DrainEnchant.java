package xyz.pugly.asteriaenchants.enchants.weapon;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import xyz.pugly.asteriaenchants.ConfigHandler;
import xyz.pugly.asteriaenchants.enchants.Enchant;
import xyz.pugly.asteriaenchants.enchants.EnchantData;
import xyz.pugly.asteriaenchants.enchants.Trigger;

public class DrainEnchant extends Enchant {

    EnchantData data = new EnchantData(ConfigHandler.getEnchant("drain"), Trigger.DAMAGE_DEALT);

    @Override
    public void attackTrigger(EntityDamageByEntityEvent event, int level) {
        if (!(event.getEntity() instanceof Player))
            return;

        Player p = (Player) event.getEntity();
        if (Math.random() < 0.1 * level)
            p.setFoodLevel(p.getFoodLevel() - 1);
    }

}
