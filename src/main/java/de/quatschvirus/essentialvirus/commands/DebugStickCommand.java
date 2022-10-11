package de.quatschvirus.essentialvirus.commands;

import de.quatschvirus.essentialvirus.Main;
import de.quatschvirus.essentialvirus.commands.basecommands.PlayerCommand;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DebugStickCommand extends PlayerCommand {
    @Override
    public void function(Player player, Command command, String label, String[] args) {
        if (player.getInventory().firstEmpty() < 0) {
            player.sendMessage(Main.getPrefix() + ChatColor.RED + "Du hast keinen Platz im Inventar!");
            return;
        }
        player.getInventory().addItem(new ItemStack(Material.DEBUG_STICK));
    }
}
