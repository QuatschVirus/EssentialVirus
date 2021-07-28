package de.quatschvirus.essentialvirus.commands;

import de.quatschvirus.essentialvirus.Main;
import de.quatschvirus.essentialvirus.utils.Money;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class TransferCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Main.getPrefix() + ChatColor.RED + "Dieser Befehl kann nur von Spielern verwendet werden!");
            return true;
        }
        Player player = (Player) sender;
        if (args.length != 1) {
            sender.sendMessage(Main.getPrefix() + ChatColor.RED + "Verwendung: /transfer <player to transfer to>");
            return true;
        }
        if (Money.get(player) < 1) {
            sender.sendMessage(Main.getPrefix() + ChatColor.RED + "Du hast nicht genug Geld auf dem Konto!");
            return true;
        }
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage(Main.getPrefix() + ChatColor.RED + args[0] + " ist kein Spieler oder ist nicht online!");
            return true;
        }
        Money.remove(player, 1);
        ItemStack item = player.getInventory().getItemInMainHand();
        player.getInventory().remove(item);
        target.getInventory().addItem(item);
        String itemTypeName = item.getType().name().toLowerCase().replace('_', ' ');
        String itemName = "";
        for (String word : itemTypeName.split(" ")) {
            itemName += word.substring(0, 1).toUpperCase() + word.substring(1) + " ";
        }
        sender.sendMessage(Main.getPrefix() + ChatColor.GOLD + "Du hast " + args[0] + " " + item.getAmount() + " " + itemName + "gegeben");
        target.sendMessage(Main.getPrefix() + ChatColor.GOLD + player.getDisplayName() + " hat dir " + item.getAmount() + " " + itemName + "gegeben");
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length > 1) {
            return new ArrayList<String>();
        } else {
            return null;
        }
    }
}
