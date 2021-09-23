package de.quatschvirus.essentialvirus.commands.basecommands;

import de.quatschvirus.essentialvirus.Main;
import de.quatschvirus.essentialvirus.utils.Money;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class PaidCommand implements CommandExecutor {

    private int price;

    public PaidCommand(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Main.getPrefix() + ChatColor.RED + "Dieser Befehl kann nur von Spielern benutzt werden!");
            return true;
        }

        Player player = (Player) sender;

        if (Money.get(player) < this.price) {
            sender.sendMessage(Main.getPrefix() + ChatColor.RED + "Du hast nicht genug Geld auf dem Konto!");
            return true;
        }
        if (check(player, command, label, args)) {
            Money.remove(player, price);
            function(player, command, label, args);
        }
        return false;
    }

    public abstract void function(Player player, Command command, String label, String[] args);
    public abstract boolean check(Player player, Command command, String label, String[] args);
}
