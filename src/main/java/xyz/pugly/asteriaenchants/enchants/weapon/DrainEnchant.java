package xyz.pugly.asteriaenchants.enchants.weapon;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import xyz.pugly.asteriaenchants.ConfigHandler;
import xyz.pugly.asteriaenchants.enchants.Enchant;
import xyz.pugly.asteriaenchants.enchants.EnchantData;
import xyz.pugly.asteriaenchants.enchants.Trigger;
import xyz.pugly.asteriaenchants.events.AEPlayerDamageEvent;

public class DrainEnchant extends Enchant {

    EnchantData data = new EnchantData(ConfigHandler.getEnchant("drain"), Trigger.DAMAGE_DEALT);

    private static final double CHANCE_PER_LEVEL = 0.1;

    @Override
    public void attackTrigger(AEPlayerDamageEvent event, int level) {
        if (!(event.getVictim() instanceof Player))
            return;

        Player p = (Player) event.getVictim();
        if (Math.random() < CHANCE_PER_LEVEL * level)
            p.setFoodLevel(p.getFoodLevel() - 1);
    }

}
