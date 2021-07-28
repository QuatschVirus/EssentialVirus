package de.quatschvirus.essentialvirus.commands;

import de.quatschvirus.essentialvirus.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AnnounceShutdownCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        switch (args[0].toLowerCase()) {
            default:
                Main.getInstance().getServer().broadcastMessage(Main.getPrefix() + ChatColor.RED + "Der Server fährt in " + args[0] + " Minuten herrunter!!!");
                break;
            case "1":
                Main.getInstance().getServer().broadcastMessage(Main.getPrefix() + ChatColor.RED + "Der Server fährt in einer Minute herrunter!!!");
                break;
            case "0":
                Main.getInstance().getServer().broadcastMessage(Main.getPrefix() + ChatColor.RED + "Der Server fährt jetzt herrunter!!!");
                break;
        }
        return false;
    }
}
