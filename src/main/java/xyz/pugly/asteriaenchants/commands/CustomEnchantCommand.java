package xyz.pugly.asteriaenchants.commands;

import dev.jorel.commandapi.annotations.Alias;
import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.Subcommand;
import dev.jorel.commandapi.annotations.arguments.AIntegerArgument;
import dev.jorel.commandapi.annotations.arguments.AStringArgument;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.pugly.asteriaenchants.enchants.Enchant;
import xyz.pugly.asteriaenchants.enchants.EnchantHandler;

@Command("customenchant")
@Alias("ce")
@Permission("asteriaenchants.ce")
public class CustomEnchantCommand {
    // TODO change messages

    @Default
    public static void ce(CommandSender sender) {
        help(sender);
    }

    @Subcommand("help")
    public static void help(CommandSender sender) {
        // TODO
    }

    @Subcommand("list")
    public static void list(Player player) {
        // TODO
    }

    @Subcommand("give")
    @Permission("asteriaenchants.give")
    public static void give(Player player, @AStringArgument String enchant, @AIntegerArgument int level) {
        Enchant e = EnchantHandler.getEnchant(enchant);
        if (e == null) {
            player.sendMessage("Invalid enchant");
            return;
        }

        if (level < 1 || level > e.getData().getMaxLevel()) {
            player.sendMessage("Invalid level");
            return;
        }

        player.getInventory().addItem(e.getEnchantBook(level));
        player.sendMessage("Gave enchant book");
    }
}
