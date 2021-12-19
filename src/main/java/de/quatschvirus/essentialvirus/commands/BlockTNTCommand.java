package de.quatschvirus.essentialvirus.commands;

import de.quatschvirus.essentialvirus.Main;
import de.quatschvirus.essentialvirus.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class BlockTNTCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage(Main.getPrefix() + ChatColor.RED + "Du darfst diesen Befehl nicht benutzen!");
            return false;
        }
        if (args.length < 1) {
            sender.sendMessage(Main.getPrefix() + ChatColor.RED + "Gebe bitte mindestens einen Spielernamen an!");
            return false;
        }
        ArrayList<String> blockedPlayers = new ArrayList<>();
        if (Config.contains("TNTBlock")) {
            blockedPlayers = (ArrayList<String>) Config.getStringList("TNTBlock");
        }
        for (String playerName : args) {
            Player player = Bukkit.getPlayer(playerName);
            if (player != null) {
                if (blockedPlayers.contains(player.getUniqueId().toString())) {
                    blockedPlayers.remove(player.getUniqueId().toString());
                    sender.sendMessage(Main.getPrefix() + ChatColor.DARK_AQUA + playerName + ChatColor.RESET + " wurde der Zugriff auf TNT entsperrt!");
                } else {
                    blockedPlayers.add(player.getUniqueId().toString());
                    sender.sendMessage(Main.getPrefix() + ChatColor.DARK_AQUA + playerName + ChatColor.RESET + " wurde der Zugriff auf TNT gesperrt!");
                }
            }
        }
        Config.set("TNTBlock", blockedPlayers);
        return false;
    }
}
