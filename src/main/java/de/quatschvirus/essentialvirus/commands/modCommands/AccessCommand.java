package de.quatschvirus.essentialvirus.commands.modCommands;

import de.quatschvirus.essentialvirus.Main;
import de.quatschvirus.essentialvirus.commands.basecommands.OPCommand;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AccessCommand extends OPCommand {
    @Override
    public void function(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Main.getPrefix() + ChatColor.RED + "Dieser Befehl kann nur von Spielern benutzt werden!");
            return;
        }
        Player player = (Player) sender;
        if (args.length >= 2) {
            switch (args[0]) {
                case "bInv": {

                }
            }
        }
    }
}
