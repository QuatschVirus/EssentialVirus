package de.quatschvirus.essentialvirus.commands;

import de.quatschvirus.essentialvirus.Main;
import de.quatschvirus.essentialvirus.utils.Money;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Main.getPrefix() + ChatColor.RED + "Dieser Befehl kann nur von Spielern verwendet werden!");
            return true;
        }
        Player player = (Player) sender;
        if (args.length != 2) {
            sender.sendMessage(Main.getPrefix() + ChatColor.RED + "Verwendung: /playerteleport <player to teleport> <player to teleport to>");
            return true;
        }
        if (Money.get(player) < 1) {
            sender.sendMessage(Main.getPrefix() + ChatColor.RED + "Du hast nicht genug Geld auf deinem Konto!");
            return true;
        }
        Player target = Bukkit.getPlayer(args[0]);
        Player destination = Bukkit.getPlayer(args[1]);
        if (target == null) {
            sender.sendMessage(Main.getPrefix() + ChatColor.RED + args[0] + " ist kein Spieler oder ist nicht online!");
            return true;
        }
        if (destination == null) {
            sender.sendMessage(Main.getPrefix() + ChatColor.RED + args[1] + " ist kein Spieler oder ist nicht online!");
            return true;
        }
        Money.remove(player, 1);
        target.teleport(destination);
        sender.sendMessage(Main.getPrefix() + ChatColor.GOLD + args[0] + " wurde zu " + args[1] + " teleportiert");
        target.sendMessage(Main.getPrefix() + ChatColor.GOLD + player.getDisplayName() + " hat dich zu " + args[1] + " teleportiert");
        destination.sendMessage(Main.getPrefix() + ChatColor.GOLD + player.getDisplayName() + " hat " + args[0] + " zu dir teleportiert");
        return false;
    }
}
