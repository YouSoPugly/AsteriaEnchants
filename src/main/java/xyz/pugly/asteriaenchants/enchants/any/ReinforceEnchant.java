package xyz.pugly.asteriaenchants.enchants.any;

import xyz.pugly.asteriaenchants.ConfigHandler;
import xyz.pugly.asteriaenchants.enchants.Enchant;
import xyz.pugly.asteriaenchants.enchants.EnchantData;
import xyz.pugly.asteriaenchants.enchants.Trigger;

public class ReinforceEnchant extends Enchant {

    EnchantData data = new EnchantData(ConfigHandler.getEnchant("reinforced"), Trigger.TIME);

}
