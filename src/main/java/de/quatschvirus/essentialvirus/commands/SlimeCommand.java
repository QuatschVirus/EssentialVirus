package de.quatschvirus.essentialvirus.commands;

import de.quatschvirus.essentialvirus.Main;
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
        if (args.length > 0) {
            sender.sendMessage(Main.getPrefix() + ChatColor.RED + "Verwendung: /slime");
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
            player.sendMessage(Main.getPrefix() + ChatColor.AQUA + " This " + ChatColor.UNDERLINE + "is" + ChatColor.RESET + ChatColor.AQUA + " a slime chunk.");
        } else {
            player.sendMessage(Main.getPrefix() + ChatColor.AQUA + " This is " + ChatColor.UNDERLINE + "not" + ChatColor.RESET + ChatColor.AQUA + " a slime chunk.");
        }
        return false;
    }
}
