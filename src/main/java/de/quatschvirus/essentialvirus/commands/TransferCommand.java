package de.quatschvirus.essentialvirus.commands;

import de.quatschvirus.essentialvirus.Main;
import de.quatschvirus.essentialvirus.commands.basecommands.PaidCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TransferCommand extends PaidCommand {

    public TransferCommand() {
        super(1);
    }

    @Override
    public void function(Player player, Command command, String label, String[] args) {
        Player target = Bukkit.getPlayer(args[0]);
        ItemStack item = player.getInventory().getItemInMainHand();
        player.getInventory().clear(player.getInventory().getHeldItemSlot());
        target.getInventory().addItem(item);
        String itemTypeName = item.getType().name().toLowerCase().replace('_', ' ');
        StringBuilder itemName = new StringBuilder();
        for (String word : itemTypeName.split(" ")) {
            itemName.append(word.substring(0, 1).toUpperCase()).append(word.substring(1)).append(" ");
        }
        player.sendMessage(Main.getPrefix() + ChatColor.GOLD + "Du hast " + args[0] + " " + item.getAmount() + " " + itemName + "gegeben");
        target.sendMessage(Main.getPrefix() + ChatColor.GOLD + player.getDisplayName() + " hat dir " + item.getAmount() + " " + itemName + "gegeben");
    }

    @Override
    public boolean check(Player player, Command command, String label, String[] args) {
        if (args.length != 1) {
            player.sendMessage(Main.getPrefix() + ChatColor.RED + "Verwendung: /transfer <player to transfer to>");
            return false;
        }
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            player.sendMessage(Main.getPrefix() + ChatColor.RED + args[0] + " ist kein Spieler oder ist nicht online!");
            return false;
        }
        return true;
    }
}
