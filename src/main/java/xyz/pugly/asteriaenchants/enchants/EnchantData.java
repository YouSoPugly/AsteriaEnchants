package xyz.pugly.asteriaenchants.enchants;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EnchantData {

    private String id;
    private String name;
    private int maxLevel;
    private String rarity;
    private String description;
    private Material item;
    private Trigger trigger;
    private Set<EnchantType> types;


    public EnchantData(String id, String name, int maxLevel, String rarity, String description, Material item, Trigger trigger, EnchantType... types) {
        this.id = id;
        this.name = name;
        this.maxLevel = maxLevel;
        this.rarity = rarity;
        this.description = description;
        this.item = item;
        this.trigger = trigger;
        this.types = new HashSet<>(List.of(types));
    }

    public EnchantData(ConfigurationSection section, Trigger trigger) {
        this.id = section.getName();
        this.name = section.getString("name");
        this.maxLevel = section.getInt("max-level");
        this.rarity = section.getString("rarity");
        this.description = section.getString("description");
        this.item = Material.valueOf(section.getString("item").toUpperCase());
        this.trigger = trigger;
        this.types = new HashSet<>();
        List<String> types = section.getStringList("types");
        for(String type : types) {
            this.types.add(EnchantType.valueOf(type.toUpperCase()));
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public String getRarity() {
        return rarity;
    }

    public Trigger getTrigger() {
        return trigger;
    }

    public Material getMaterial() {
        return item;
    }

    public String getDescription() {
        return description;
    }

    public Set<EnchantType> getTypes() {
        return types;
    }
}
