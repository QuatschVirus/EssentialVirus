package de.quatschvirus.essentialvirus.commands.modCommands;

import de.quatschvirus.essentialvirus.Main;
import de.quatschvirus.essentialvirus.commands.basecommands.OPCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class AnnounceShutdownCommand extends OPCommand {

    @Override
    public void function(CommandSender sender, Command command, String label, String[] args) {
        switch (args[0].toLowerCase()) {
            default ->
                    Main.getInstance().getServer().broadcastMessage(Main.getPrefix() + ChatColor.RED + "Der Server fährt in " + args[0] + " Minuten herunter!!!");
            case "1" ->
                    Main.getInstance().getServer().broadcastMessage(Main.getPrefix() + ChatColor.RED + "Der Server fährt in einer Minute herunter!!!");
            case "0" ->
                    Main.getInstance().getServer().broadcastMessage(Main.getPrefix() + ChatColor.RED + "Der Server fährt jetzt herunter!!!");
        }
    }
}
