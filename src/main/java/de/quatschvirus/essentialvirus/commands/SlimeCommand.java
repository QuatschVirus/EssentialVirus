package de.quatschvirus.essentialvirus.commands;

import de.quatschvirus.essentialvirus.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Random;

public class SlimeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Main.getPrefix() + ChatColor.RED + "Dieser Befehl kann nur von Spielern verwendet werden!");
            return true;
        }
        Player player = (Player) sender;
        if (args.length > 1) {
            sender.sendMessage(Main.getPrefix() + ChatColor.RED + "Verwendung: /slime oder /slime search");
            return true;
        }
        if (args.length > 0) {
            if (args[0].equals("search")) {
                int bounds = player.getClientViewDistance();
                if (bounds > Bukkit.getViewDistance()) {
                    bounds = Bukkit.getViewDistance();
                }
                for (int x = 0; x <= bounds; x++) {
                    for (int z = 0; z <= bounds; z++) {
                        long seed = player.getWorld().getSeed();
                        int xPosition = player.getWorld().getChunkAt(player.getLocation()).getX() + x;
                        int zPosition = player.getWorld().getChunkAt(player.getLocation()).getZ() + z;
                        Random random = new Random(seed +
                                ((long) xPosition * xPosition * 0x4c1906) +
                                ((long) xPosition * 0x5ac0db) +
                                ((long) zPosition * zPosition) * 0x4307a7L +
                                ((long) zPosition * 0x5f24f) ^ 0x3ad8025f);
                        if (random.nextInt(10) == 0) {
                            player.sendMessage(Main.getPrefix() + ChatColor.AQUA + "Der Chunk " + xPosition + " " + zPosition + " ist ein Slimechunk.");
                            return false;
                        }
                        xPosition = player.getWorld().getChunkAt(player.getLocation()).getX() - x;
                        zPosition = player.getWorld().getChunkAt(player.getLocation()).getZ() - z;
                        random = new Random(seed +
                                ((long) xPosition * xPosition * 0x4c1906) +
                                ((long) xPosition * 0x5ac0db) +
                                ((long) zPosition * zPosition) * 0x4307a7L +
                                ((long) zPosition * 0x5f24f) ^ 0x3ad8025f);
                        if (random.nextInt(10) == 0) {
                            player.sendMessage(Main.getPrefix() + ChatColor.AQUA + "Der Chunk " + xPosition + " " + zPosition + " ist ein Slimechunk.");
                            return false;
                        }
                    }
                }
                player.sendMessage(Main.getPrefix() + ChatColor.RED + "Es wurde kein Chunk in deiner Umgebung gefunden");
            } else {
                sender.sendMessage(Main.getPrefix() + ChatColor.RED + "Verwendung: /slime oder /slime search");
            }
            return true;
        }
        long seed = player.getWorld().getSeed();
        int xPosition = player.getWorld().getChunkAt(player.getLocation()).getX();
        int zPosition = player.getWorld().getChunkAt(player.getLocation()).getZ();
        Random random = new Random(seed +
                ((long) xPosition * xPosition * 0x4c1906) +
                ((long) xPosition * 0x5ac0db) +
                ((long) zPosition * zPosition) * 0x4307a7L +
                ((long) zPosition * 0x5f24f) ^ 0x3ad8025f);
        if (random.nextInt(10) == 0) {
            player.sendMessage(Main.getPrefix() + ChatColor.AQUA + "Dies " + ChatColor.UNDERLINE + "ist" + ChatColor.RESET + ChatColor.AQUA + " ein Slimechunk.");
        } else {
            player.sendMessage(Main.getPrefix() + ChatColor.AQUA + " Dies ist " + ChatColor.UNDERLINE + "kein" + ChatColor.RESET + ChatColor.AQUA + " ein Slimechunk.");
        }
        return false;
    }
}
