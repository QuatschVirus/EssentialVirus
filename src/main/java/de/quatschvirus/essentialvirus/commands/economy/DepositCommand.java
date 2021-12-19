package de.quatschvirus.essentialvirus.commands.economy;

import de.quatschvirus.essentialvirus.Main;
import de.quatschvirus.essentialvirus.economy.Money;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DepositCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Main.getPrefix() + ChatColor.RED + "Dieser Befehl kann nur von Spielern verwendet werden!");
            return true;
        }
        Player player = (Player) sender;
        if (args.length != 1) {
            sender.sendMessage(Main.getPrefix() + ChatColor.RED + "Verwendung: /deposit <amount>");
            return true;
        }
        int amount = Integer.parseInt(args[0]);
        int emeraldCount = 0;
        for (ItemStack item : player.getInventory()) {
            if (item != null) {
                if (item.getType().equals(Material.EMERALD)) {
                    emeraldCount += item.getAmount();
                }
            }
        }
        if (emeraldCount < amount) {
            sender.sendMessage(Main.getPrefix() + ChatColor.RED + "Du hast nicht genug Smaragde im Inventar!");
            return true;
        }
        player.getInventory().remove(Material.EMERALD);
        player.getInventory().addItem(new ItemStack(Material.EMERALD, emeraldCount - amount));
        Money.add(player, amount);
        sender.sendMessage(Main.getPrefix() + ChatColor.GOLD + "Du hast " + amount + "€ eingezahlt");
        return false;
    }
}
