package de.quatschvirus.essentialvirus.commands.modCommands;

import de.quatschvirus.essentialvirus.Main;
import de.quatschvirus.essentialvirus.commands.basecommands.OPCommand;
import de.quatschvirus.essentialvirus.economy.Money;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MoneyCommand extends OPCommand implements TabCompleter {
    @Override
    public void function(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2) {
            sender.sendMessage(Main.getPrefix() + ChatColor.RED + "Nicht genug Argumente!");
            return;
        }
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage(Main.getPrefix() + ChatColor.RED + "Dieser Spieler exisiert nicht!");
            return;
        }
        int amount = Integer.parseInt(args[1]);
        if ((Money.get(target) + amount) < 0) {
            sender.sendMessage(Main.getPrefix() + ChatColor.RED + "Der Spieler darf nicht weniger als 0€ besitzen!");
            return;
        }
        Money.add(target, amount);
        sender.sendMessage(Main.getPrefix() + "Der Spieler " + args[0] + " hat nun " + Money.get(target) + "€");
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 1) {
            return new ArrayList<>();
        }
        return null;
    }
}
