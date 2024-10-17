package xyz.pugly.asteriaenchants.commands;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.Subcommand;
import org.bukkit.command.CommandSender;
import xyz.pugly.asteriaenchants.AsteriaEnchants;

@Command("asteriaenchants")
public class AsteriaEnchantsCommand {

    @Default
    @Permission("asteriaenchants.use")
    public static void onCommand(CommandSender sender) {
        help(sender);
    }

    @Subcommand("help")
    @Permission("asteriaenchants.use")
    public static void help(CommandSender sender) {
        // TODO
    }

    @Subcommand("reload")
    @Permission("asteriaenchants.reload")
    public static void reload(CommandSender sender) {
        AsteriaEnchants.reload();
        sender.sendMessage("Reloaded config");
    }

}
