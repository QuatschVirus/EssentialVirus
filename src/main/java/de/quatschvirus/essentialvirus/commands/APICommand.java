package de.quatschvirus.essentialvirus.commands;

import de.quatschvirus.essentialvirus.Main;
import de.quatschvirus.essentialvirus.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;

public class APICommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!player.isOp()) {
                sender.sendMessage(Main.getPrefix() + ChatColor.RED + "Dieser Befehl kann nur von Admins benutzt werden");
                return true;
            }
        }
        if (args.length > 0) {
            //noinspection SwitchStatementWithTooFewBranches
            switch (args[0]) {
                case "update": {
                    if (args.length == 2) {
                        long delay = 0;
                        switch (args[1].substring(args[1].length() - 1).toLowerCase()) {
                            case "s": {
                                Bukkit.broadcastMessage(Main.getPrefix() + ChatColor.RED + "Der Server wird in " + args[1].substring(0, args[1].length() - 1) + " Sekunden geupdatet!");
                                delay = Integer.parseInt(args[1].substring(0, args[1].length() - 1)) * 20L;
                                break;
                            }

                            case "m": {
                                Bukkit.broadcastMessage(Main.getPrefix() + ChatColor.RED + "Der Server wird in " + args[1].substring(0, args[1].length() - 1) + " Minuten geupdatet!");
                                delay = Integer.parseInt(args[1].substring(0, args[1].length() - 1)) * 60 * 20L;
                                break;
                            }

                            case "h": {
                                Bukkit.broadcastMessage(Main.getPrefix() + ChatColor.RED + "Der Server wird in " + args[1].substring(0, args[1].length() - 1) + " Stunden geupdatet!");
                                delay = Integer.parseInt(args[1].substring(0, args[1].length() - 1)) * 60 * 60 * 20L;
                                break;
                            }
                        }
                        new BukkitRunnable() {
                            public void run() {
                                for (Player player : Bukkit.getOnlinePlayers()) {
                                    player.kickPlayer("Das Plugin wird geupdated. Bitte warte ca. 5 Sekunden, bevor du den Server wieder betrittst");
                                }
                                Config.set("timer.wasRunning", Main.getInstance().getTimer().isRunning());

                                Bukkit.reload();
                                Main.getInstance().getTimer().setRunning((boolean) Config.get("timer.wasRunning"));
                            }
                        }.runTaskLater(Main.getInstance(), delay);
                    }

                    break;
                }
            }
        }
        return false;
    }
}
