package xyz.pugly.asteriaenchants.enchants.armor;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.pugly.asteriaenchants.ConfigHandler;
import xyz.pugly.asteriaenchants.enchants.Enchant;
import xyz.pugly.asteriaenchants.enchants.EnchantData;
import xyz.pugly.asteriaenchants.enchants.Trigger;

public class BolsterEnchant extends Enchant {

    EnchantData data = new EnchantData(ConfigHandler.getEnchant("reinforced"), Trigger.ENCHANT);

    @Override
    public void enchantTrigger(Player p, ItemStack i, int level) {
        i.getItemMeta().getAttributeModifiers(Attribute.GENERIC_MAX_HEALTH).add(new AttributeModifier("asenchant.reinforced", level, AttributeModifier.Operation.ADD_NUMBER));
    }

}
