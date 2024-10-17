package xyz.pugly.asteriaenchants.enchants;

import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;

public enum EnchantType {

    WEAPON,
    AXE,
    SWORD,
    TOOL,
    PICKAXE,
    SHOVEL,
    HOE,
    FISHING_ROD,
    ARMOR,
    HELMET,
    CHESTPLATE,
    LEGGINGS,
    BOOTS,
    BOW,
    HAND,
    ANY;

    public static boolean isAny(ItemStack item) {
        return isHand(item) || isArmor(item);
    }

    public static boolean isHand(ItemStack item) {
        return isWeapon(item) || isTool(item) || isRod(item) || isBow(item);
    }

    public static boolean isWeapon(ItemStack item) {
        return isAxe(item) || isSword(item);
    }

    public static boolean isAxe(ItemStack item) {
        return Tag.ITEMS_AXES.isTagged(item.getType());
    }

    public static boolean isSword(ItemStack item) {
        return Tag.ITEMS_SWORDS.isTagged(item.getType());
    }

    public static boolean isTool(ItemStack item) {
        return isAxe(item) || isPickaxe(item) || isShovel(item) || isHoe(item);
    }

    public static boolean isPickaxe(ItemStack item) {
        return Tag.ITEMS_PICKAXES.isTagged(item.getType());
    }

    public static boolean isShovel(ItemStack item) {
        return Tag.ITEMS_SHOVELS.isTagged(item.getType());
    }

    public static boolean isHoe(ItemStack item) {
        return Tag.ITEMS_HOES.isTagged(item.getType());
    }

    public static boolean isRod(ItemStack item) {
        return item.getType().equals(Material.FISHING_ROD);
    }

    public static boolean isBow(ItemStack item) {
        return item.getType().equals(Material.BOW);
    }

    public static boolean isArmor(ItemStack item) {
        return isHelmet(item) || isChestplate(item) || isLeggings(item) || isBoots(item);
    }

    public static boolean isHelmet(ItemStack item) {
        return Tag.ITEMS_HEAD_ARMOR.isTagged(item.getType());
    }

    public static boolean isChestplate(ItemStack item) {
        return Tag.ITEMS_CHEST_ARMOR.isTagged(item.getType());
    }

    public static boolean isLeggings(ItemStack item) {
        return Tag.ITEMS_LEG_ARMOR.isTagged(item.getType());
    }

    public static boolean isBoots(ItemStack item) {
        return Tag.ITEMS_FOOT_ARMOR.isTagged(item.getType());
    }

    public static Set<EnchantType> getApplicable(ItemStack item) {
        Set<EnchantType> enchantTypes = new HashSet<>();

        if (isAny(item))
            enchantTypes.add(ANY);
        if (isWeapon(item))
            enchantTypes.add(WEAPON);
        if (isAxe(item))
            enchantTypes.add(AXE);
        if (isSword(item))
            enchantTypes.add(SWORD);
        if (isTool(item))
            enchantTypes.add(TOOL);
        if (isPickaxe(item))
            enchantTypes.add(PICKAXE);
        if (isShovel(item))
            enchantTypes.add(SHOVEL);
        if (isHoe(item))
            enchantTypes.add(HOE);
        if (isRod(item))
            enchantTypes.add(FISHING_ROD);
        if (isArmor(item))
            enchantTypes.add(ARMOR);
        if (isHelmet(item))
            enchantTypes.add(HELMET);
        if (isChestplate(item))
            enchantTypes.add(CHESTPLATE);
        if (isLeggings(item))
            enchantTypes.add(LEGGINGS);
        if (isBoots(item))
            enchantTypes.add(BOOTS);
        if (isBow(item))
            enchantTypes.add(BOW);

        return enchantTypes;
    }
}
