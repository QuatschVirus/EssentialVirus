package de.quatschvirus.essentialvirus.commands.modCommands;

import de.quatschvirus.essentialvirus.Main;
import de.quatschvirus.essentialvirus.commands.basecommands.OPCommand;
import de.quatschvirus.essentialvirus.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class BlockTpCommand extends OPCommand {

    private final YamlConfiguration config = Config.getConfig();

    @Override
    public void function(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            sender.sendMessage(Main.getPrefix() + ChatColor.RED + "Gebe bitte mindestens einen Spielernamen an!");
            return;
        }

        ArrayList<String> blocked = new ArrayList<>();
        if (config.contains("TPBlock")) {
            blocked = (ArrayList<String>) config.getStringList("TPBlock");
        }

        for (String playerName : args) {
            Player player = Bukkit.getPlayer(playerName);
            if (player != null) {
                if (blocked.contains(player.getUniqueId().toString())) {
                    blocked.remove(player.getUniqueId().toString());
                    sender.sendMessage(Main.getPrefix() + ChatColor.DARK_AQUA + playerName + ChatColor.RESET + " wurde der Zugriff auf Teleportation entsperrt!");
                } else {
                    blocked.add(player.getUniqueId().toString());
                    sender.sendMessage(Main.getPrefix() + ChatColor.DARK_AQUA + playerName + ChatColor.RESET + " wurde der Zugriff auf Teleportation gesperrt!");
                }
            }
        }
        config.set("TPBlock", blocked);
    }
}
