package de.quatschvirus.essentialvirus.commands.basecommands;

import de.quatschvirus.essentialvirus.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public abstract class OPCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage(Main.getPrefix() + ChatColor.RED + "Du darfst diesen Befehl nicht benutzen!");
            return false;
        }
        function(sender, command, label, args);
        return false;
    }

    public abstract void function(CommandSender sender, Command command, String label, String[] args);
}
