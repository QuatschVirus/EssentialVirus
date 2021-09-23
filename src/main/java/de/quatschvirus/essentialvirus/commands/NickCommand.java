package de.quatschvirus.essentialvirus.commands;

import de.quatschvirus.essentialvirus.Main;
import de.quatschvirus.essentialvirus.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NickCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Main.getPrefix() + ChatColor.RED + "Dieser Befehl kann nur von Spielern benutzt werden!");
            return true;
        }

        Player runner = (Player) sender;

        switch (args.length) {
            case 0: {
                if(Config.contains("nick." + runner.getUniqueId())) {
                    if(Config.get("nick." + runner.getUniqueId()) == null) {
                        runner.sendMessage(Main.getPrefix() + ChatColor.RED + "Du hast momentan keinen Nicknamen!");
                    } else {
                        runner.sendMessage(Main.getPrefix() + "Dein Nickname ist " + ChatColor.GREEN + Config.get("nick." + runner.getUniqueId()));
                    }
                } else {
                    runner.sendMessage(Main.getPrefix() + ChatColor.RED + "Du hast momentan keinen Nicknamen!");
                }
                break;
            }

            case 1: {
                if(!runner.hasPermission("essentialvirus.nick.nickself")) {
                    runner.sendMessage(Main.getPrefix() + ChatColor.RED + "Du hast nicht die benötigten Berechtigungen, um dich umzubennenen!");
                    return true;
                }
                for (OfflinePlayer player: Bukkit.getWhitelistedPlayers()) {
                    if (args[0].equals(player.getName())) {
                        runner.sendMessage(Main.getPrefix() + ChatColor.RED + "Du kannst dicht nicht nennen wie ein anderer Spieler auf diesem Server!");
                        return true;
                    }
                }
                if (args[0].equalsIgnoreCase("reset")) {
                    runner.setDisplayName(runner.getName());
                    runner.setPlayerListName(runner.getName());
                    runner.setCustomName(runner.getName());
                    Config.set("nick." + runner.getUniqueId(), null);
                    runner.sendMessage(Main.getPrefix() + "Dein Nickname wurde erfolgreich zurückgesetzt!");
                    break;

                }
                runner.setDisplayName(args[0]);
                runner.setPlayerListName(args[0]);
                runner.setCustomName(args[0]);
                Config.set("nick." + runner.getUniqueId(), args[0]);
                runner.sendMessage(Main.getPrefix() + "Dein Nickname wurde erfolgreich auf " + ChatColor.GREEN + args[0] + ChatColor.RESET + " gesetzt!");
                break;
            }

            case 2: {
                if(!runner.hasPermission("essentialvirus.nick.nickother")) {
                    runner.sendMessage(Main.getPrefix() + ChatColor.RED + "Du hast nicht die benötigten Berechtigungen, um andere umzubennenen!");
                    return true;
                }

                Player target = Bukkit.getPlayer(args[0]);
                if(target == null) {
                    runner.sendMessage(Main.getPrefix() + ChatColor.RED + "Der Spieler, den du umbennen willst, existiert nicht!");
                    return true;
                }
                if(!target.isOnline()) {
                    runner.sendMessage(Main.getPrefix() + ChatColor.RED + "Der Spieler, den du umbennen willst, ist nicht online!");
                    return true;
                }
                if (args[1].equals("reset")) {
                    target.setDisplayName(target.getName());
                    target.setPlayerListName(target.getName());
                    target.setCustomName(target.getName());
                    Config.set("nick." + target.getUniqueId(), null);
                    runner.sendMessage(Main.getPrefix() + "Der Nickname von " + args[0] + " wurde erfolgreich zurückgesetzt!");
                    break;
                }
                target.setDisplayName(args[1]);
                target.setPlayerListName(args[1]);
                target.setCustomName(args[1]);
                Config.set("nick." + runner.getUniqueId(), args[1]);
                target.sendMessage(Main.getPrefix() + "Der Nickname von " + ChatColor.GREEN + args[0] + ChatColor.RESET + " wurde erfolgreich auf " + ChatColor.GREEN + args[1] + ChatColor.RESET + " gesetzt!");
                break;


            }

            default: {
                sender.sendMessage(Main.getPrefix() + ChatColor.RED + "Verwendung:\n" +
                        "/nick zum einsehen deines Nickanmens\n" +
                        "/nick <name> um deinen eigenen Nicknamen zu ändern\n" +
                        "/nick <player> <name> um den Nicknamen eines anderen Spielers zu ändern\n" +
                        "/nick reset um deinen Nicknamen zu entfernen");
            }
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length > 3) {
            return new ArrayList<>();
        }
        return null;
    }
}
