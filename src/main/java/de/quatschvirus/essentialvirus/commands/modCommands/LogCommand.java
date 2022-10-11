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

public class LogCommand extends OPCommand {
    @Override
    public void function(CommandSender sender, Command command, String label, String[] args) {

        final YamlConfiguration config = Config.getConfig();
        
        if (args.length < 1) {
            sender.sendMessage(Main.getPrefix() + ChatColor.RED + "Gebe bitte mindestens einen Spielernamen an!");
            return;
        }

        ArrayList<String> logPlayers = new ArrayList<>();
        if (config.contains("TPBlock")) {
            logPlayers = (ArrayList<String>) config.getStringList("log.players");
        }

        for (String playerName : args) {
            Player player = Bukkit.getPlayer(playerName);
            if (player != null) {
                if (logPlayers.contains(player.getUniqueId().toString())) {
                    logPlayers.remove(player.getUniqueId().toString());
                    sender.sendMessage(Main.getPrefix() + ChatColor.DARK_AQUA + playerName + ChatColor.RESET + " wurde vom Watchlog entfernt!");
                } else {
                    logPlayers.add(player.getUniqueId().toString());
                    sender.sendMessage(Main.getPrefix() + ChatColor.DARK_AQUA + playerName + ChatColor.RESET + " wurde zum Watchlog hinzugefügt!");
                }
            }
        }
        config.set("log.players", logPlayers);
    }
}
